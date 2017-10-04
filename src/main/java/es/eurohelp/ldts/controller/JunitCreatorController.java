package es.eurohelp.ldts.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import es.eurohelp.ldts.Junit;
import es.eurohelp.ldts.LodTest;
import es.eurohelp.ldts.LodTestMain;
import es.eurohelp.ldts.XML;
import es.eurohelp.ldts.validator.JunitDataValidator;

@Controller
@RequestMapping(value = "/junitcreator")
public class JunitCreatorController {

	@RequestMapping(method = RequestMethod.GET)
	public String verFormulario() {
		return "junitCreator";
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new JunitDataValidator());
	}

	@RequestMapping(value = "/procesarFormulario", method = RequestMethod.POST)
	public String procesarFormularioValidado(@Validated Junit junit, BindingResult result) throws ParserConfigurationException, SAXException, IOException {
		if (result.hasErrors()) {
			System.err.println(result.getFieldError());
			return "junitCreator";
		} else {
			XML xml = new XML();
			xml.createFile(junit);
//			JUnitCore junitCore = new JUnitCore();
//			Result resultado = junitCore.run(LodTestMain.class);
//			System.err.println(resultado.getFailureCount());

			return "home";
		}
	}

	@ModelAttribute("junit")
	public Junit populateForm() {
		return new Junit();
	}
}
