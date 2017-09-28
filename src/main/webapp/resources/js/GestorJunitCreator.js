var numPruebas = 1;
var numParametros = 1;

function anadirPrueba() {
	numPruebas++;
	var objTo = $("#Pruebas")
			.append(
					"<div class=\"PruebasCategorias\" id=\"pruebasSelectorTipoPrueba"
							+ numPruebas
							+ "\">"
							+ "<select class=\"selectpicker form-control\""
							+ "id=\""
							+ numPruebas
							+ "\" onchange=\"setTipoAssert(id)\">"
							+ "<option>-</option>"
							+ "<option>Equals</option>"
							+ "<option>Not Equals</option>"
							+ "<option>AssertTrue</option>"
							+ "<option>AssertFalse</option>"
							+ "</select>"
							+ "<select class=\"selectpicker form-control\""
							+ "id=\"pruebaSelectorArgumento"
							+ numPruebas
							+ "\">"
							+ "<option>-</option>"
							+ "<option value=\"Equals\" disabled=\"true\">requestBean.getStatus()</option>"
							+ "<option value=\"Assert\" disabled=\"true\">requestBean.getLocation()</option>"
							+ "<option value=\"Assert\" disabled=\"true\">requestBean.getResponseString()</option>"
							+ "</select>" + "</div>");
	if (numPruebas == 2) {
		var objTo = $("#botonAnadirPruebas")
		objTo
				.append("<button type=\"button\" class=\"btn btn-primary btn-circle\" id=\"botonEliminarPruebas\" onclick=\"eliminarPrueba()\">"
						+ "<span class=\"glyphicon glyphicon-minus\"></span>"
						+ "</button>");
	}
}

function eliminarPrueba() {
	console.log(numPruebas);
	var objTo = $("#botonEliminarPruebas");
	var pruebaElement = $("#pruebasSelectorTipoPrueba" + numPruebas);
	pruebaElement.remove();
	// Se elimina el boton eliminar
	if (numPruebas == 2) {
		console.log("aqui")
		objTo.remove();
	}
	numPruebas--;
}

function setTipoAssert(id) {
	var pruebaSelectorArgumento = $("#pruebaSelectorArgumento" + id);
	var pruebaElement = $("#pruebasSelectorTipoPrueba" + id);
	var elementoSeleccionado = $("#" + id + " option:selected").text();
	var elementoComparacionAnterior;
	console.log(elementoSeleccionado);
	reiniciarBloquePruebas(id);
	// Parte correspondiente a los asserts
	if (elementoSeleccionado == "AssertTrue"
			|| elementoSeleccionado == "AssertFalse") {
		$("#pruebaSelectorArgumento" + id).children("option[value^=Assert]").prop(
				"disabled", false);
		pruebaElement
				.append("<select class=\"selectpicker form-control\""
						+ "id=\"valorFormaComparacionAssert"
						+ id
						+ "\">"
						+ "<option>-</option>"
						+ "<option>Contains</option>"
						+ "<option>Not contains</option>"
						+ "</select>"
						+ "<input type=\"text\" class=\"form-control\" id=\"valorComparacionAssert"
						+ id + "\" placeholder=\"Elemento a probar\">");
		console.log("entra");
		pruebaSelectorArgumento.prop("disabled", false)
		// Parte correspondiente a los equals
	} else if (elementoSeleccionado == "Equals"
			|| elementoSeleccionado == "Not Equals") {
		$("#pruebaSelectorArgumento" + id).children("option[value^=Equals]").prop(
				"disabled", false);
		pruebaElement
				.append("<input type=\"text\" class=\"form-control\" id=\"valorComparacionEquals"
						+ id + "\" placeholder=\"Introduce valor esperado\">");
	} 
		//else {
//		$("#valorComparacion" + id).remove();
//		pruebaSelectorArgumento.prop("disabled", true);
//	}
}

function reiniciarBloquePruebas(id) {
	elementoComparacionAnterior = $("#valorComparacionAssert" + id).remove();
	elementoComparacionAnterior = $("#valorFormaComparacionAssert" + id)
			.remove();
	elementoComparacionAnterior = $("#valorComparacionEquals" + id).remove();
	$("#pruebaSelectorArgumento" + id).prop('selectedIndex', 0);
	$("#pruebasSelectorTipoPruebas" + id).children("option[value^=Equals]").prop(
			"disabled", true);
	$("#pruebaSelectorArgumento" + id).children("option[value^=Equals]").prop(
			"disabled", true);
	$("#pruebaSelectorArgumento" + id).children("option[value^=Assert]").prop(
			"disabled", true);
}

function anadirParametro() {
	numParametros++;
	var objTo = $("#Parametros")
			.append(
					"<div id=\"parametro"
							+ numParametros
							+ "\" class=\"parametro\" style=\"display: inline-flex;\">"
							+ "<input type=\"text\" class=\"form-control\""
							+ "placeholder=\"Introduce su nombre\"><input "
							+ "type=\"text\" class=\"form-control\""
							+ "placeholder=\"Introduce su valor\">" + "</div>");
	if (numParametros == 2) {
		var objTo = $("#botonAnadirParametros")
		objTo
				.append("<button type=\"button\" class=\"btn btn-primary btn-circle\" id=\"botonEliminarParametros\" onclick=\"eliminarParametro()\">"
						+ "<span class=\"glyphicon glyphicon-minus\"></span>"
						+ "</button>");
	}
}

function eliminarParametro() {
	console.log(numParametros);
	var objTo = $("#botonEliminarParametros");
	$("#parametro" + numParametros).remove();
	// Se elimina el boton eliminar
	if (numParametros == 2) {
		console.log("aqui")
		objTo.remove();
	}
	numParametros--;
}