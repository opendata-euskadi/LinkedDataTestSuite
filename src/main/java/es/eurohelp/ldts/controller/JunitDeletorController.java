package es.eurohelp.ldts.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import es.eurohelp.ldts.Junit;
import es.eurohelp.ldts.XML;

@Controller
@RequestMapping(value = "/junitdeletor")
public class JunitDeletorController {
	@RequestMapping(method = RequestMethod.GET)
	public String verFormulario() {
		return "junitCreator";
	}

	@RequestMapping(value = "/borrarJunit", method = RequestMethod.POST)
	public String procesarFormularioValidado (Junit junit, BindingResult result) throws ParserConfigurationException, SAXException, IOException {
		if (result.hasErrors()) {
			System.err.println(result.getFieldError());
			return "junitCreator";
		} else {
			XML xml = new XML();
			xml.createFile(junit);
			return "home";
		}
	}

	@ModelAttribute("junit")
	public Junit populateForm() {
		return new Junit();
	}

}
