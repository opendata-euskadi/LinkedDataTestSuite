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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "Este campo no se puede dejar en blanco");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comentario", "Este campo no se puede dejar en blanco");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "baseUri", "Este campo no se puede dejar en blanco");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pathUri", "Este campo no se puede dejar en blanco");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idParametro", "Este campo no se puede dejar en blanco");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valorParametro", "Este campo no se puede dejar en blanco");
		Junit junit = (Junit) target;
		
	}

}
