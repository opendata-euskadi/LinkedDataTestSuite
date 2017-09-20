/**
 * 
 */
package es.eurohelp.ldts;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.eurohelp.ldts.controller.TestController;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
 * @author grozadilla
 * @author ssantamariap
 */
public class ReportManager {

	static final Logger logger = LoggerFactory.getLogger(ReportManager.class);
	private static ReportManager INSTANCE = null;
	
	public synchronized static ReportManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ReportManager();
		}
		return INSTANCE;
	}
	
	/**
	 * Create a LinkedDataRequest tests reports 
	 * @param testList the tests
	 * @throws Exception 
	 */
	public void createReport(List<LinkedDataRequestBean> testList) throws Exception{
		
		LinkedDataRequestBean bean;
		
		Iterator<LinkedDataRequestBean> iterator = testList.iterator();
		
		while (iterator.hasNext()){
			
			bean = iterator.next();
			
			//Cleaning testList before generating report (removing elements of past executions)
			if(bean.getRunningGroupID() < TestController.runningGroupID){
				iterator.remove();
			}
						
		}
		
		Configuration cfg = new Configuration(new Version(2, 3, 20));

        // Where do we load the templates from:
        //cfg.setClassForTemplateLoading(ExtractorManager.class, "resources");
        //cfg.setDirectoryForTemplateLoading(new File("/src/main/resources"));
        // Some other recommended settings:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 2. Proccess template(s)
        //
        // You will do this for several times in typical applications.
        // 2.1. Prepare the template input:

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("title", "Linked Data Test Suite Report");
        
        //Setting system date & time for the report.
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date date = new Date();
        String dateTimeView = dateFormat.format(date);
        input.put("dateTime", dateTimeView);
        
        testList.sort(new NameComparator());
        
        input.put("tests", testList);

        // 2.2. Get the template
        
        cfg.setClassForTemplateLoading(this.getClass(), "/templates/");//Added new folder

        Template template = cfg.getTemplate("schema_template.ftl");

        // 2.3. Generate the output

        // Write output to the console
        //Writer consoleWriter = new OutputStreamWriter(System.out);
        //template.process(input, consoleWriter);

        //New path as view
        String resultsPath = PropertiesManager.getInstance().getProperty("lod.report.view");
        
        // For the sake of example, also write output into a file:
        
        Writer fileWriter = new FileWriter(new File(resultsPath + "report.jsp"));
        try{
                template.process(input, fileWriter);
        } finally {
                fileWriter.close();
        }
          
	}	
}

class NameComparator implements Comparator<LinkedDataRequestBean> {
    @Override
    public int compare(LinkedDataRequestBean a, LinkedDataRequestBean b) {
        return a.getMethod().compareTo(b.getMethod());
    }
}
