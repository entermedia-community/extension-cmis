package org.netevolved.cmis;

import java.math.BigInteger;
import java.util.Map;

import org.apache.chemistry.opencmis.commons.exceptions.CmisPermissionDeniedException;
import org.apache.chemistry.opencmis.commons.impl.server.AbstractServiceFactory;
import org.apache.chemistry.opencmis.commons.server.CallContext;
import org.apache.chemistry.opencmis.commons.server.CmisService;
import org.apache.chemistry.opencmis.server.support.wrapper.CallContextAwareCmisService;
import org.apache.chemistry.opencmis.server.support.wrapper.CmisServiceWrapperManager;
import org.apache.chemistry.opencmis.server.support.wrapper.ConformanceCmisServiceWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.cmis.server.FileBridgeRepositoryManager;
import org.example.cmis.server.FileBridgeTypeManager;
import org.example.cmis.server.FileBridgeUserManager;

import com.openedit.ModuleManager;
import com.openedit.modules.update.SyncToServer;

public class EnterMediaCmisServiceFactory extends AbstractServiceFactory {

	protected ModuleManager fieldModuleManager;
	private CmisServiceWrapperManager wrapperManager;
	public static final Log log = LogFactory.getLog(EnterMediaCmisServiceFactory.class);

	  /** Default maxItems value for getTypeChildren()}. */
    private static final BigInteger DEFAULT_MAX_ITEMS_TYPES = BigInteger.valueOf(50);

    /** Default depth value for getTypeDescendants(). */
    private static final BigInteger DEFAULT_DEPTH_TYPES = BigInteger.valueOf(-1);

    /**
     * Default maxItems value for getChildren() and other methods returning
     * lists of objects.
     */
    private static final BigInteger DEFAULT_MAX_ITEMS_OBJECTS = BigInteger.valueOf(200);

    /** Default depth value for getDescendants(). */
    private static final BigInteger DEFAULT_DEPTH_OBJECTS = BigInteger.valueOf(10);
	
	
	public CmisServiceWrapperManager getWrapperManager(Map parameters) {
		if (wrapperManager == null) {
			wrapperManager = new CmisServiceWrapperManager();
			wrapperManager.addWrappersFromServiceFactoryParameters(parameters);
			wrapperManager.addOuterWrapper(ConformanceCmisServiceWrapper.class,
					DEFAULT_MAX_ITEMS_TYPES, DEFAULT_DEPTH_TYPES,
					DEFAULT_MAX_ITEMS_OBJECTS, DEFAULT_DEPTH_OBJECTS);

		}

		return wrapperManager;
	}

	public void setWrapperManager(CmisServiceWrapperManager inWrapperManager) {
		wrapperManager = inWrapperManager;
	}
	
	
	 public void init(Map<String, String> parameters) {
	        log.info("[CmisServiceFactory] init");

	        // New for Chameleon **
	        wrapperManager = new CmisServiceWrapperManager();
	        wrapperManager.addWrappersFromServiceFactoryParameters(parameters);
	        wrapperManager.addOuterWrapper(ConformanceCmisServiceWrapper.class, DEFAULT_MAX_ITEMS_TYPES,
	                DEFAULT_DEPTH_TYPES, DEFAULT_MAX_ITEMS_OBJECTS, DEFAULT_DEPTH_OBJECTS);

	        // *******
	        // lets print out the parameters for debugging purposes so we can see
	        // what happens to our
	        // custom parameters
	        for (String currentKey : parameters.keySet()) {
	            log.info("[FileBridgeCmisServiceFactory]Key: " + currentKey + " ->Value:" + parameters.get(currentKey));
	        }

	       
	       // readConfiguration(parameters);
	    }
	
	

	public ModuleManager getModuleManager() {
		return fieldModuleManager;
	}

	public void setModuleManager(ModuleManager inModuleManager) {
		fieldModuleManager = inModuleManager;
	}

	@Override
	public CmisService getService(CallContext inArg0) {
		
		EnterMediaCmisService cmisservice = (EnterMediaCmisService) getModuleManager().getBean("cmisService");
		cmisservice.setCallContext(inArg0);
		//CmisService service = (CallContextAwareCmisService) wrapperManager.wrap(cmisservice);
		return cmisservice;
	}

	public synchronized String authenticate(CallContext context) {
		// check user and password
		if (!authenticate(context.getUsername(), context.getPassword())) {
			throw new CmisPermissionDeniedException(
					"Invalid username or password.");
		}

		return context.getUsername();
	}

	private boolean authenticate(String inUsername, String inPassword) {
		return true;// implement with EnterMedia
	}

}