package es.eurohelp.ldts;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bsh.EvalError;
import bsh.Interpreter;
import es.eurohelp.ldts.HttpManager;
import es.eurohelp.ldts.LinkedDataRequestBean;
import es.eurohelp.ldts.LodTest;
import es.eurohelp.ldts.PropertiesManager;

public class LodTestMain {
	private static final Logger logger = LoggerFactory.getLogger(LodTest.class);
	private static List<LinkedDataRequestBean> tests = new ArrayList<LinkedDataRequestBean>();
	private static int executionCount = 0;

	LinkedDataRequestBean requestBean;
	//XMLUtils xmlUtils = new XMLUtils();
	String xmlContainsNode = "";

	@Test
	public final void executejUnit(String pName, String pComment, String pBaseUri, String pPathUri, String pAccept,
			String pMethod, String[] pIdParametro, String[] pTipoPrueba, String[] pValorParametro,
			String[] pObjetoPrueba, String[] pValorComparacionEquals, String[] pValorComparacionAssert,
			String[] pValorFormaComparacionAssert) throws EvalError {
		try {
			String baseUri = PropertiesManager.getInstance().getProperty(pBaseUri);
			Map<String, String> parameters = new HashMap<String, String>();
			for (String pId : pIdParametro) {
				for (String pValor : pValorFormaComparacionAssert) {
					parameters.put(pId, pValor);
				}
			}
			requestBean = new LinkedDataRequestBean(pMethod, pAccept, baseUri, pPathUri, pName, pComment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			System.out.println();
			int valorEquals = 0;
			int valorAssert = 0;
			Interpreter interpreter = null;
			for (String tipoPrueba : pTipoPrueba) {
				// si la prueba es tipo Equals
				if (tipoPrueba.equalsIgnoreCase("equals")) {
					System.out.println("es equals");
					interpreter = new Interpreter();
					String query = pTipoPrueba + "("+ pObjetoPrueba + "," + pValorComparacionEquals[valorAssert]
							+ ")";
					System.err.println(query);
					interpreter.eval(query);
					valorEquals++;
				} else {
					System.out.println("es assert");
					interpreter = new Interpreter();
					String query = pTipoPrueba + "(requestBean." + pObjetoPrueba + "." + pValorFormaComparacionAssert
							+ "(" + pValorComparacionAssert[valorAssert] + "))";
					System.err.println(query);
					interpreter.eval(query);
					valorAssert++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}