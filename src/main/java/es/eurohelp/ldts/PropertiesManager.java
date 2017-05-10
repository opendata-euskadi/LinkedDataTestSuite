/**
 * 
 */
package es.eurohelp.ldts;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author grozadilla
 *
 */
public class PropertiesManager {

	private static PropertiesManager INSTANCE = null;
	private Properties prop = null;
	
	/**
	 *  Get a PropertiesManager instance
	 * @return PropertiesManager instance
	 * @throws IOException
	 */
	public synchronized static PropertiesManager getInstance() throws IOException {
		if (INSTANCE == null) {
			INSTANCE = new PropertiesManager();
			INSTANCE.loadProperties();
			
		}
		return INSTANCE;
	}
	
	/**
	 * Loads properties from file
	 * @throws IOException
	 */
	public void loadProperties () throws IOException{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("lod.properties");
		prop = new Properties();
		prop.load(input);
	}
	
	/**
	 * Gets a property value 
	 * @param property the property key
	 * @return the property value
	 */
	public String getProperty(String property){
		return prop.getProperty(property);
	}
}
