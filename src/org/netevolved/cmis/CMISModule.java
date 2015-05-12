package org.netevolved.cmis;

import org.apache.chemistry.opencmis.server.impl.CmisRepositoryContextListener;
import org.openedit.entermedia.modules.BaseMediaModule;

import com.openedit.ModuleManager;
import com.openedit.WebPageRequest;

public class CMISModule extends BaseMediaModule{

	protected ModuleManager fieldModuleManager;
	

	public ModuleManager getModuleManager() {
		return fieldModuleManager;
	}


	public void setModuleManager(ModuleManager inModuleManager) {
		fieldModuleManager = inModuleManager;
	}


	public void initCMIS(WebPageRequest inReq){
			EnterMediaCmisServiceFactory factory = (EnterMediaCmisServiceFactory) inReq.getRequest().getServletContext().getAttribute(CmisRepositoryContextListener.SERVICES_FACTORY);
			factory.setModuleManager(getModuleManager());
		
	
	}
}
