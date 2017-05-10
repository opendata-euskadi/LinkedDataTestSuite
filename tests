# Tests contra sistema Linked Data Eurohelp

* solo documentados, hay que pasarlos a código.
* En producción, `http://172.16.0.81:8008/` debería ser `http://{es|eus}.euskadi.eus/` (En local tenemos la aplicación como `ROOT` de tomcat). 
* Hay que revisar la codificación de caractéres.


## SPARQL via formulario web

url: http://172.16.0.81:8008/sparql

grafos y metadatos:

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
PREFIX calidadDelAire: <http://opendata.euskadi.eus/def/medio-ambiente/calidad-del-aire/>
PREFIX medicion:<http://opendata.euskadi.eus/def/medio-ambiente/medicion/>

SELECT ?lugarDondeSeRealizo ?fecha ?valor
WHERE {
	GRAPH <http://opendata.euskadi.eus/dataset/id/calidad-aire-en-euskadi-2017>{
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
PREFIX medicion: <http://opendata.euskadi.eus/def/medio-ambiente/medicion/>
PREFIX calidadAire:<http://opendata.euskadi.eus/def/medio-ambiente/calidad-del-aire/>
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





http://172.16.0.81:8008/X42TLOD_ProxyWar-0.0.1/id/*

# To do




http://172.16.0.81:8008/X42TLOD_ProxyWar-0.0.1
http://172.16.0.81:8008/id/*
http://euskadi.eus/id/*
http://172.16.0.81:8008/def/*
http://172.16.0.81:8008/sparql