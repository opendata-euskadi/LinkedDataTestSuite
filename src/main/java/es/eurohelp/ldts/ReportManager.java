/**
 * 
 */
package es.eurohelp.ldts;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
 * @author grozadilla
 *
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
        input.put("title", "Test suite Report");
        
        input.put("tests", testList);

        // 2.2. Get the template

        Template template = cfg.getTemplate("/src/main/resources/schema_template.ftl");

        // 2.3. Generate the output

        // Write output to the console
        //Writer consoleWriter = new OutputStreamWriter(System.out);
        //template.process(input, consoleWriter);

        String resultsPath = PropertiesManager.getInstance().getProperty("lod.report.path");
        // For the sake of example, also write output into a file:
        Writer fileWriter = new FileWriter(new File(resultsPath + "report.html"));
        try{
                template.process(input, fileWriter);
        } finally {
                fileWriter.close();
        }
	}	
	
	
}
