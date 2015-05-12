package org.netevolved.cmis;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.chemistry.opencmis.commons.server.CmisServiceFactory;
import org.apache.chemistry.opencmis.server.impl.CmisRepositoryContextListener;

public class CMISContextListener implements ServletContextListener   {

	
	

   

    public void contextInitialized(ServletContextEvent sce) {
      
        CmisServiceFactory factory = null;
    	factory = new EnterMediaCmisServiceFactory();
		sce.getServletContext().setAttribute(CmisRepositoryContextListener.SERVICES_FACTORY, factory);
		
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
