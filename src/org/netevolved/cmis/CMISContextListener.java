package org.netevolved.cmis;

import java.util.HashMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.chemistry.opencmis.commons.server.CmisServiceFactory;
import org.apache.chemistry.opencmis.server.impl.CmisRepositoryContextListener;
import org.example.cmis.server.FileBridgeCmisServiceFactory;

public class CMISContextListener implements ServletContextListener   {

	
	public static CmisServiceFactory emFactory  = null;

   

    public void contextInitialized(ServletContextEvent sce) {
      
        CmisServiceFactory factory = null;
    	factory = new FileBridgeCmisServiceFactory();
		sce.getServletContext().setAttribute(CmisRepositoryContextListener.SERVICES_FACTORY, factory);
		
		HashMap map = new HashMap();
		
		map.put("login.1","test:test");
		map.put("repository.test","/home/ian");
		map.put("repository.test.readwrite","test");
		
		
		factory.init(map);
		emFactory = factory; 
        //sce.getServletContext().setAttribute(SERVICES_FACTORY, factory);
    }

	@Override
	public void contextDestroyed(ServletContextEvent inArg0) {
		// TODO Auto-generated method stub
		
	}

//    public void contextDestroyed(ServletContextEvent sce) {
//        // destroy services factory
//        CmisServiceFactory factory = (CmisServiceFactory) sce.getServletContext().getAttribute(SERVICES_FACTORY);
//        if (factory != null) {
//            try {
//                factory.destroy();
//            } catch (Exception e) {
//                LOG.error("Service factory couldn't be destroyed: {}", e.toString(), e);
//                return;
//            }
//        }
//    }

    
	
}
