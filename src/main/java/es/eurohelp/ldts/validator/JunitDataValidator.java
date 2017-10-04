package es.eurohelp.ldts.validator;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.eurohelp.ldts.Junit;

@Controller
public class JunitDataValidator implements Validator {

	@Override
	public boolean supports(Class<?> clase) {
		return Junit.class.equals(clase);
	}

	@Override
	public void validate(Object target, Errors errors) {
		boolean assertActived = false;
		boolean equalsActived = false;
		Junit junit = (Junit) target;

		for (int i = 0; i < junit.getTipoPrueba().length; i++) {
			if (junit.getTipoPrueba()[i].equalsIgnoreCase("true") || junit.getTipoPrueba()[i].equalsIgnoreCase("false")) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valorComparacionAssert",
						"required.valorComparacionAssert", "Este campo no se puede dejar en blanco");
				if ("-".equals(junit.getValorFormaComparacionAssert())) {
					errors.rejectValue("formaComparacionAssert", "required.formaComparacionAssert",
							"Debes seleccionar una opción");
				}
			} else {
				if (junit.getTipoPrueba()[i].contains("equals")) {
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valorComparacionEquals",
							"required.valorComparacionEquals", "Este campo no se puede dejar en blanco");
				}
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "required.nombre",
				"Este campo no se puede dejar en blanco");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comentario", "required.comentario",
				"Este campo no se puede dejar en blanco");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "baseUri", "required.baseUri",
				"Este campo no se puede dejar en blanco");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pathUri", "required.pathUri",
				"Este campo no se puede dejar en blanco");

		if ("-".equals(junit.getMethod())) {
			errors.rejectValue("method", "required.method", "Debes seleccionar una opción");
		}
		if ("-".equals(junit.getAccept())) {
			errors.rejectValue("accept", "required.accept", "Debes seleccionar una opción");
		}
		if ("-".equals(junit.getObjetoPrueba())) {
			errors.rejectValue("objetoPrueba", "required.objetoPrueba", "Debes seleccionar una opción");
		}
		if ("-".equals(junit.getTipoPrueba())) {
			errors.rejectValue("tipoPrueba", "required.tipoPrueba", "Debes seleccionar una opción");
		}

	}
}