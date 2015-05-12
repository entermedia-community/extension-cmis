package org.netevolved.cmis;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;

import org.apache.chemistry.opencmis.server.impl.CmisRepositoryContextListener;

import com.openedit.ModuleManager;
import com.openedit.OpenEditException;
import com.openedit.WebPageRequest;
import com.openedit.generators.BaseGenerator;
import com.openedit.generators.Output;
import com.openedit.page.Page;

public class CMISGenerator extends BaseGenerator{

	
	
	protected EnterMediaCmisServiceFactory fieldFactory;
	protected ModuleManager fieldModuleManager;
	
	public ModuleManager getModuleManager() {
		return fieldModuleManager;
	}

	public void setModuleManager(ModuleManager inModuleManager) {
		fieldModuleManager = inModuleManager;
	}

	

	@Override
	public void generate(WebPageRequest inContext, Page inPage, Output inOut)
			throws OpenEditException {
		//make sure we have set the factory.  Other servlets rely on this.
		if(fieldFactory == null){
			fieldFactory = new EnterMediaCmisServiceFactory();
			inContext.getRequest().getServletContext().setAttribute(CmisRepositoryContextListener.SERVICES_FACTORY, fieldFactory);
			
		}
		try {
			String inServlet = inContext.findValue("servlet");
			
			HttpServlet servlet = (HttpServlet) inContext.getRequest().getServletContext().getServlet(inServlet);
			if(servlet == null){
				servlet = (HttpServlet) getModuleManager().getBean(inServlet);
				//ServletRegistration r = inContext.getRequest().getServletContext().addServlet(inServlet,servlet);
				//servlet.setInitParameter("cmisVersion", inContext.findValue("cmisversion"));
				
			}
			servlet.service(inContext.getRequest(), inContext.getResponse());
		} catch (ServletException e) {
			throw new OpenEditException(e);
		} catch (IOException e) {
			throw new OpenEditException(e);
		}
	}

	
	
	
	
	
}
