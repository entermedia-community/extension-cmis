package org.netevolved.cmis;

import org.example.cmis.server.FileBridgeCmisServiceFactory;
import org.example.cmis.server.FileBridgeRepository;
import org.example.cmis.server.FileBridgeTypeManager;
import org.openedit.entermedia.MediaArchive;
import org.openedit.entermedia.modules.BaseMediaModule;

import com.openedit.ModuleManager;
import com.openedit.WebPageRequest;
import com.openedit.page.Page;
import com.openedit.page.manage.PageManager;

public class CMISModule extends BaseMediaModule{

	protected ModuleManager fieldModuleManager;
	

	public ModuleManager getModuleManager() {
		return fieldModuleManager;
	}


	public void setModuleManager(ModuleManager inModuleManager) {
		fieldModuleManager = inModuleManager;
	}


	public void initCMIS(WebPageRequest inReq){
			//FileBridgeCmisServiceFactory factory = (FileBridgeCmisServiceFactory) inReq.getRequest().getServletContext().getAttribute(CmisRepositoryContextListener.SERVICES_FACTORY);
		
		MediaArchive archive = getMediaArchive(inReq);
		PageManager pm = archive.getPageManager();
		Page target = pm.getPage("/WEB-INF/data/" + archive.getCatalogId() + "/originals/");
		FileBridgeCmisServiceFactory factory = (FileBridgeCmisServiceFactory) CMISContextListener.emFactory;
		factory.setModuleManager(getModuleManager());
		FileBridgeTypeManager typeManager = new FileBridgeTypeManager();
        FileBridgeRepository fsr = new FileBridgeRepository(archive.getCatalogId(), target.getContentItem().getAbsolutePath(), typeManager);
        fsr.setUserReadWrite("test");

        
        factory.addRepository(fsr);
	
	}
}
