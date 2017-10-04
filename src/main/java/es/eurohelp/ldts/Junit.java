package es.eurohelp.ldts;


public class Junit {
	private String nombre;
	private String comentario;
	private String baseUri;
	private String pathUri;
	private String accept;
	private String method;
	private String[] idParametro;
	private String[] valorParametro;
	private String[] tipoPrueba;
	private String[] objetoPrueba;
	private String[] valorComparacionEquals;
	private String[] valorComparacionAssert;
	private String[] valorFormaComparacionAssert;

	public String[] getObjetoPrueba() {
		return objetoPrueba;
	}

	public String getAccept() {
		return accept;
	}

	public String[] getValorComparacionAssert() {
		return valorComparacionAssert;
	}

	public String[] getValorComparacionEquals() {
		return valorComparacionEquals;
	}

	public String[] getValorFormaComparacionAssert() {
		return valorFormaComparacionAssert;
	}

	public String getBaseUri() {
		return baseUri;
	}

	public String[] getIdParametro() {
		return idParametro;
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

	public String[] getValorParametro() {
		return valorParametro;
	}

	public String[] getTipoPrueba() {
		return tipoPrueba;
	}

	public void setTipoPrueba(String[] tipoPrueba) {
		this.tipoPrueba = tipoPrueba;
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

	public void setIdParametro(String[] idParametro) {
		this.idParametro = idParametro;
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

	public void setValorParametro(String[] valorParametro) {
		this.valorParametro = valorParametro;
	}

	public void setObjetoPrueba(String[] objetoPrueba) {
		this.objetoPrueba = objetoPrueba;
	}

	public void setValorComparacionAssert(String[] valorComparacionAssert) {
		this.valorComparacionAssert = valorComparacionAssert;
	}

	public void setValorComparacionEquals(String[] valorComparacionEquals) {
		this.valorComparacionEquals = valorComparacionEquals;
	}

	public void setValorFormaComparacionAssert(String[] valorFormaComparacionAssert) {
		this.valorFormaComparacionAssert = valorFormaComparacionAssert;
	}
	
}
