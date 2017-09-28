package es.eurohelp.ldts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import es.eurohelp.ldts.Junit;
import es.eurohelp.ldts.validator.JunitDataValidator;

@Controller
@RequestMapping(value = "/junit")
public class JunitEditorController {

	@RequestMapping(method = RequestMethod.GET)
	public String verFormulario() {
		return "junitCreator";
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new JunitDataValidator());
	}

	@RequestMapping(value = "/procesarFormulario", method = RequestMethod.POST)
	public String procesarFormularioValidado(@Validated Junit junit, BindingResult result) {

		if (result.hasErrors()) {
			return "junitCreator";
		} else {
			return "home";
		}
	}

	@ModelAttribute("junit")
	public Junit populateForm() {
		return new Junit();
	}
}
