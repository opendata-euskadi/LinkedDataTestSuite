# Tests contra sistema Linked Data Eurohelp

* Solo documentados, hay que pasarlos a código.
* En producción, `http://172.16.0.81:8008/` debería ser `http://{es|eus}.euskadi.eus/` (En local tenemos la aplicación como `ROOT` de tomcat). 
* **TODO**: Hay que revisar la codificación de caracteres.
* **TODO**: testear enlaces.
* **owl:sameAs no va!**


## SPARQL via formulario web

url: http://172.16.0.81:8008/sparql

Grafos y metadatos:

```
SELECT DISTINCT ?g ?p ?o WHERE {
  ?g ?p ?o .
  GRAPH ?g {
    ?sub ?pred ?obj
  } 
} 
```

Todos los datos de la calidad del aire:

```
SELECT *
FROM <http://es.euskadi.eus/dataset/id/calidad-aire-en-euskadi-2017>
WHERE {
  ?s ?p ?o
}
```

Descripcion de una observacion:

```
DESCRIBE <http://es.euskadi.eus/id/medio-ambiente/calidad-del-aire/observation/AV-GASTEIZ-2017-01-07>
``` 


Las estaciones donde se realizaron, la fecha y el valor de las mediciones de CO

```
PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>
PREFIX dcterms: <http://purl.org/dc/terms/>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX measure: <http://purl.org/linked-data/sdmx/2009/measure#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX calidadDelAire: <http://es.euskadi.eus/def/medio-ambiente/calidad-del-aire/>
PREFIX medicion:<http://es.euskadi.eus/def/medio-ambiente/medicion/>

SELECT ?lugarDondeSeRealizo ?fecha ?valor
WHERE {
  GRAPH <http://es.euskadi.eus/dataset/id/calidad-aire-en-euskadi-2017>{
      ?q calidadDelAire:medicion ?r.
      ?q geo:location ?lugarDondeSeRealizo.
      ?q dcterms:date ?fecha.
      ?r rdf:type medicion:CO.
      ?r measure:obsValue ?valor .
    }
}
```

Los distintos valores de CO cuando la humedad es mayor a 91.9 


```
PREFIX measure: <http://purl.org/linked-data/sdmx/2009/measure#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX medicion: <http://es.euskadi.eus/def/medio-ambiente/medicion/>
PREFIX calidadAire: <http://es.euskadi.eus/def/medio-ambiente/calidad-del-aire/>
PREFIX dcterms: <http://purl.org/dc/terms/>
SELECT distinct ?medicionCO ?fechaMedicion ?valorCO

WHERE{
    ?medicionesEstacion rdf:type medicion:humedad620.
    ?medicionesEstacion measure:obsValue ?valorHumedad.
    ?medicionesAvGasteiz dcterms:date ?fechaMedicion.
    ?medicionesAvGasteiz calidadAire:medicion ?medicionCO.
    ?medicionCO measure:obsValue ?valorCO.
    ?medicionCO rdf:type medicion:CO.
  FILTER ( ?valorHumedad > (xsd:double (91.9)))
}
ORDER BY DESC(?valorCO)
```

Contratos asociados al departamento de salud y que hayan empezado en el año 2014

```
PREFIX contsem:<http://contsem.unizar.es/def/sector-publico/pproc#>
PREFIX opendata-dpto:<http://es.euskadi.eus/id/sector-publico/departamento/>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
select distinct * where { 
    ?contrato contsem:managingDepartment opendata-dpto:salud.
    ?contrato contsem:formalizedDate ?fecha
    
    FILTER (?fecha > "2014-01-01"^^xsd:date)
}
ORDER BY ASC(?fecha)
```

Contratos de personas con su sueldo, el cargo que ocupan y la fecha en la que empezaron a desempeñar el cargo

```
PREFIX contsem:<http://contsem.unizar.es/def/sector-publico/pproc#>
PREFIX opendata-dpto:<http://es.euskadi.eus/id/sector-publico/departamento/>
PREFIX schema:<http://schema.org/>
PREFIX dbpedia:<http://dbpedia.org/ontology/>
PREFIX purl:<http://purl.org/dc/terms/>
select distinct ?empleado ?sueldo ?cargo ?fechaInicio ?departamento where { 
    ?contrato contsem:managingDepartment ?departamento.
    ?contrato schema:employee ?empleado. 
    ?contrato contsem:ContractEconomicConditions ?sueldo.
    ?contrato dbpedia:occupation ?cargo.
    ?contrato contsem:formalizedDate ?fechaInicio.
}
ORDER BY ASC(?sueldo)
```

Contratos asociados al departamento de "DESARROLLO ECONÓMICO E INFRAESTRUCTURAS"  en los que se cobre mas de 20000€ y el cargo al que corresponde 

```
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX contsem:<http://contsem.unizar.es/def/sector-publico/pproc#>
PREFIX opendata-dpto:<http://es.euskadi.eus/id/sector-publico/departamento/>
PREFIX dbpedia:<http://dbpedia.org/ontology/>
select distinct * where { 
     ?contrato contsem:managingDepartment opendata-dpto:desarrollo-economico-e-infraestructuras. 
     ?contrato dbpedia:occupation ?cargo.
     ?contrato contsem:ContractEconomicConditions ?sueldo. 
    FILTER(?sueldo > (xsd:double(20000)))
}
ORDER BY DESC(?sueldo)
```

## Verificar owl:sameAs

Insertar datos:

```
PREFIX owl:<http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>

<http://euskadi.eus/resource/es/InigoUrkullu> owl:sameAs <http://euskadi.eus/resource/eu/InigoUrkullu> .

<http://euskadi.eus/resource/es/InigoUrkullu> rdfs:comment "Presidente"@es .

<http://euskadi.eus/resource/es/InigoUrkullu> foaf:friendWith <http://spain.es/Rajoy> .

<http://euskadi.eus/resource/eu/InigoUrkullu> rdfs:comment "Lehendakari"@eu .
```

Esta consulta debería devolver dos rdfs:comment (@es y @eu), pero en Blazegraph se puede activar la inferencia o los named graph, pero no los dos a la vez (https://sourceforge.net/p/bigdata/mailman/bigdata-developers/thread/CABf_9zLuxYj7u-WVUB9yMVkR5jCp3uU4%2BBhiz9AOZxRyujP_Aw%40mail.gmail.com/#msg35832536):

```
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
SELECT *
WHERE {
 <http://euskadi.eus/resource/es/InigoUrkullu> rdfs:comment ?o
}
```

El mismo resultado se puede obtener sin inferencia:

```
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
SELECT ?c ?p ?o
WHERE {
 <http://euskadi.eus/resource/es/InigoUrkullu> rdfs:comment ?c
 OPTIONAL {
  <http://euskadi.eus/resource/es/InigoUrkullu> owl:sameAs ?s .
  ?s ?p ?o  
 }
}
```

## HTTP GET recurso con cabecera Accept RDF/XML

http://172.16.0.81:8008/id/sector-publico/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09

Accept: application/rdf+xml

```
<?xml version="1.0" encoding="UTF-8"?>
<rdf:RDF
  xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
  xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
  xmlns:sesame="http://www.openrdf.org/schema/sesame#"
  xmlns:owl="http://www.w3.org/2002/07/owl#"
  xmlns:xsd="http://www.w3.org/2001/XMLSchema#"
  xmlns:fn="http://www.w3.org/2005/xpath-functions#"
  xmlns:foaf="http://xmlns.com/foaf/0.1/"
  xmlns:dc="http://purl.org/dc/elements/1.1/"
  xmlns:hint="http://www.bigdata.com/queryHints#"
  xmlns:bd="http://www.bigdata.com/rdf#"
  xmlns:bds="http://www.bigdata.com/rdf/search#">
    <rdf:Description rdf:about="http://es.euskadi.eus/id/sector-publico/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09">
        <ContractEconomicConditions xmlns="http://contsem.unizar.es/def/sector-publico/pproc#" rdf:datatype="http://www.w3.org/2001/XMLSchema#long">3670496</ContractEconomicConditions>
        <formalizedDate xmlns="http://contsem.unizar.es/def/sector-publico/pproc#" rdf:datatype="http://www.w3.org/2001/XMLSchema#date">2000-03-31</formalizedDate>
        <managingDepartment xmlns="http://contsem.unizar.es/def/sector-publico/pproc#" rdf:resource="http://es.euskadi.eus/id/sector-publico/departamento/desarrollo-economico-e-infraestructuras"/>
        <occupation xmlns="http://dbpedia.org/ontology/" xml:lang="es">jefe grupo inspector pesca</occupation>
        <centro-organico xmlns="http://es.euskadi.eus/def/sector-publico/contrato/" xml:lang="es">dir.pesca y acuicultura</centro-organico>
        <categoria-retributiva xmlns="http://es.euskadi.eus/def/sector-publico/contrato/" rdf:datatype="http://www.w3.org/2001/XMLSchema#double">10.0</categoria-retributiva>
        <cod-centro-destino xmlns="http://es.euskadi.eus/def/sector-publico/contrato/" xml:lang="es">gobierno vasco donostia easo 10</cod-centro-destino>
        <cod-centro-organico xmlns="http://es.euskadi.eus/def/sector-publico/contrato/" rdf:datatype="http://www.w3.org/2001/XMLSchema#long">33300000000</cod-centro-organico>
        <cod-departamento xmlns="http://es.euskadi.eus/def/sector-publico/contrato/" rdf:datatype="http://www.w3.org/2001/XMLSchema#long">3</cod-departamento>
        <cod-puesto xmlns="http://es.euskadi.eus/def/sector-publico/contrato/" rdf:datatype="http://www.w3.org/2001/XMLSchema#double">3024.0</cod-puesto>
        <dotacion xmlns="http://es.euskadi.eus/def/sector-publico/contrato/" rdf:datatype="http://www.w3.org/2001/XMLSchema#long">1</dotacion>
        <fecha-descarga xmlns="http://es.euskadi.eus/def/sector-publico/contrato/" rdf:datatype="http://www.w3.org/2001/XMLSchema#date">2017-03-09</fecha-descarga>
        <perfil-linguistico xmlns="http://es.euskadi.eus/def/sector-publico/contrato/" rdf:datatype="http://www.w3.org/2001/XMLSchema#double">2.0</perfil-linguistico>
        <rdf:type rdf:resource="http://purl.org/cerif/frapo/EmploymentContract"/>
        <modified xmlns="http://purl.org/dc/terms/" rdf:datatype="http://www.w3.org/2001/XMLSchema#date">2016-05-09</modified>
    </rdf:Description>
</rdf:RDF>
```