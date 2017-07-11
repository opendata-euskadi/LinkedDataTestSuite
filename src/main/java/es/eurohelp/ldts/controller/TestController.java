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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.eurohelp.ldts.LodTest;

/**
 * @author ssantamariap
 */
@Controller
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	public static int runningGroupID = 0;
	public static int testCount = 0;
	private ModelAndView mav;
	
	/**
	 * 
	 * @return all.jsp (view) with the list of methods that contain the annotation @Test
	 */
	@RequestMapping(value = "/test", method=RequestMethod.GET)
	public ModelAndView test() {
		
		
		logger.info("TestController: method(test)");
		
		List<String> allTest = new ArrayList<>();
		
		mav = new ModelAndView("/all");
		
		//Getting method names from the class to test, filtering by @Test to show all to the user
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

		return mav;
		
	}
	
	/**
	* @param list with the test cases selected by the user
	* @return results.jsp with the execution results
	*/
	@RequestMapping(value="/run", method = RequestMethod.POST)
	public String run(Model model, @RequestParam(value = "test", defaultValue = "null") List<String> lista){
		
		logger.info("TestController: method(run)");
		
		String url = "";
		
		testCount = lista.size();
		
		if (lista.contains("null")){
			
			logger.info("TestController: method(run): null list");
			url = "redirect:/test";
			
		}else{
			
			long runTime = 0;
			int fails = 0;
			int nTest = 0;
			
			JUnitCore jUnitCore = new JUnitCore();
			Request request;
			Result result;
			
			//Executing SINGLE test by method name
			for(String test:lista){
				
				request = Request.method(LodTest.class, test);
				result = jUnitCore.run(request);
				
				nTest = nTest + result.getRunCount();
				fails = fails + result.getFailureCount();
				runTime = runTime + result.getRunTime();
				
			}
			
			model.addAttribute("nTest", nTest);
			model.addAttribute("fails", fails);
			model.addAttribute("runTime", runTime);
			
			runningGroupID++;//for grouping by execution time (needed cause execution is SINGLE)
			
			url = "results";
			
		}
		
		return url;
		
	}
	
	/**
	 * @return report.jsp with detailed view report.
	 */
	@RequestMapping(value="/report")
	public String report(){
		return "report";
	}

}
