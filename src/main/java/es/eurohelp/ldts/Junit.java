package es.eurohelp.ldts;

import java.util.ArrayList;
import java.util.HashMap;

public class Junit {
	private String nombre;
	private String comentario;
	private String baseUri;
	private String pathUri;
	private String accept;
	private String method;
	private HashMap<String, String> listaParametros;
	private ArrayList<String> listaPruebas;

	public String getAccept() {
		return accept;
	}

	public String getBaseUri() {
		return baseUri;
	}

	public HashMap<String, String> getListaParametros() {
		return listaParametros;
	}

	public String getMethod() {
		return method;
	}

	public String getNombre() {
		return nombre;
	}

	public String getComentario() {
		return comentario;
	}

	public String getPathUri() {
		return pathUri;
	}

	public ArrayList<String> getListaPruebas() {
		return listaPruebas;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public void setListaParametros(HashMap<String, String> listaParametros) {
		this.listaParametros = listaParametros;
	}

	public void setListaPruebas(ArrayList<String> listaPruebas) {
		this.listaPruebas = listaPruebas;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPathUri(String pathUri) {
		this.pathUri = pathUri;
	}

}
