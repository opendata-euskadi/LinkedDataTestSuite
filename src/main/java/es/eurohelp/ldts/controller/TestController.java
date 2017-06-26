package es.eurohelp.ldts.controller;


import java.lang.annotation.Annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ssantamariap
 */

@Controller
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	public static int runningGroupID = 0;
	
	private ModelAndView mav;
	
	@RequestMapping(value = "/test", method=RequestMethod.GET)
	public ModelAndView test() {
		
		List<String> allTest = new ArrayList<>();
		
		mav = new ModelAndView("/all");
		
		Method [] methods = LodTest.class.getMethods();
		Annotation [] annotations;
		for(Method method:methods){
			annotations = method.getAnnotations();
			
			for(Annotation annotation:annotations){
				if(annotation.annotationType().toString().equals("interface org.junit.Test")){
					allTest.add(method.getName());
				}
			}	
			
		}
		
		mav.addObject("lista", allTest);
		
		
		
		/*
		logger.info("TestController: test");
		
		JUnitCore jUnitCore = new JUnitCore();
		//Result result = jUnitCore.run(LodTest.class); EJECUTA TODOS LOS TEST
		Request request;
		request = Request.method(LodTest.class, "GETSPARQLHTML200");//EJECUTA POR NOMBRE
		Result result = jUnitCore.run(request);
		
		
		logger.info("Test ejecutados: " + result.getRunCount());
		logger.info("Fallos registrados: " + result.getFailureCount());
		logger.info("Tiempo de ejecución: " + result.getRunTime());
		*/
		
		
		
		return mav;
		
	}
	
	@RequestMapping(value="/execute", method = RequestMethod.POST)
	public ModelAndView run(@RequestParam("test") ArrayList<String> lista){
		
		System.out.println("*-*-*-*-*-*-*TEST QUE LLEGAN AL CONTROLADOR-*-*-*-*-*-*-*-*");
		System.out.println(lista.toString());
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		mav = new ModelAndView("/results");
		
		long runTime = 0;
		int fails = 0;
		int nTest = 0;
		
		JUnitCore jUnitCore = new JUnitCore();
		Request request;
		Result result;
		
		for(String test:lista){
			
			request = Request.method(LodTest.class, test);
			
			result = jUnitCore.run(request);
			
			nTest = nTest + result.getRunCount();
			fails = fails + result.getFailureCount();
			runTime = runTime + result.getRunTime();
			
		}
		
		mav.addObject("nTest", nTest);
		mav.addObject("fails", fails);
		mav.addObject("runTime", runTime);
		
		runningGroupID++;
		
		return mav;
		
	}
	
	@RequestMapping(value="/report")
	public String report(){
		return "report";
	}

}
