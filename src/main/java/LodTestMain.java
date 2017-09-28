import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.eurohelp.ldts.HttpManager;
import es.eurohelp.ldts.LinkedDataRequestBean;
import es.eurohelp.ldts.LodTest;
import es.eurohelp.ldts.PropertiesManager;
import es.eurohelp.ldts.XMLUtils;


public class LodTestMain {
	private static final Logger logger = LoggerFactory.getLogger(LodTest.class);
	private static List<LinkedDataRequestBean> tests = new ArrayList<LinkedDataRequestBean>();
	private static int executionCount = 0;

	LinkedDataRequestBean requestBean;
	XMLUtils xmlUtils = new XMLUtils();
	String xmlContainsNode = "";

	@Test
	public final void executejUnit(String pBaseUri, String pMethod, String pAccept, String pPathUri, String pName,
			String pComment, HashMap<String, String> pParametros, ArrayList<String> pTipoPrueba,
			HashMap<String, String> pValoresPrueba) {
		try {
			String baseUri = PropertiesManager.getInstance().getProperty(pBaseUri);
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(pMethod, pAccept, baseUri, pPathUri, pName, pComment, pParametros);
			HttpManager.getInstance().doRequest(requestBean);
			int cont = pTipoPrueba.size();
			Iterator<String> t = pValoresPrueba.keySet().iterator();
			for (String e : pTipoPrueba) {
				String clave = t.next();
				if(e.equals("assertEquals")){
					assertEquals(clave, pValoresPrueba.get(clave));
				}
				else if(e.equals("assertTrue/contains")){
					assertTrue(requestBean.getResponseString().contains("s"));
				}
				else if(e.equals("assertTrue/!contains")){
					assertTrue(!requestBean.getResponseString().contains("s"));
				}
				else if(e.equals("assertFalse/contains")){
					assertFalse(requestBean.getResponseString().contains("s"));
				}
				else if(e.equals("assertFalse/!contains")){
					assertFalse(!requestBean.getResponseString().contains("s"));
				}
				else if(e.equals("assertNotEquals")){
					assertNotEquals(clave, pValoresPrueba);
				}
			}
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}