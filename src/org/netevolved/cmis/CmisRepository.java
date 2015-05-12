package org.netevolved.cmis;

import java.io.File;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import org.apache.chemistry.opencmis.commons.data.Acl;
import org.apache.chemistry.opencmis.commons.data.AllowableActions;
import org.apache.chemistry.opencmis.commons.data.BulkUpdateObjectIdAndChangeToken;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.data.FailedToDeleteData;
import org.apache.chemistry.opencmis.commons.data.ObjectData;
import org.apache.chemistry.opencmis.commons.data.ObjectInFolderContainer;
import org.apache.chemistry.opencmis.commons.data.ObjectInFolderList;
import org.apache.chemistry.opencmis.commons.data.ObjectList;
import org.apache.chemistry.opencmis.commons.data.ObjectParentData;
import org.apache.chemistry.opencmis.commons.data.Properties;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.definitions.TypeDefinition;
import org.apache.chemistry.opencmis.commons.definitions.TypeDefinitionList;
import org.apache.chemistry.opencmis.commons.enums.BaseTypeId;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisInvalidArgumentException;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ObjectDataImpl;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.RepositoryInfoImpl;
import org.apache.chemistry.opencmis.commons.impl.server.ObjectInfoImpl;
import org.apache.chemistry.opencmis.commons.server.CallContext;
import org.apache.chemistry.opencmis.commons.spi.Holder;
import org.example.cmis.server.FileBridgeUtils;
import org.openedit.entermedia.MediaArchive;

import com.openedit.ModuleManager;

public class CmisRepository {

	protected String fieldCatalogId;
	protected ModuleManager fieldModuleManager;
	protected CallContext fieldCallContext;

	public CallContext getCallContext() {
		return fieldCallContext;
	}

	public void setCallContext(CallContext inCallContext) {
		fieldCallContext = inCallContext;
	}

	public ModuleManager getModuleManager() {
		return fieldModuleManager;
	}

	public void setModuleManager(ModuleManager inModuleManager) {
		fieldModuleManager = inModuleManager;
	}

	protected MediaArchive fieldMediaArchive;

	public MediaArchive getMediaArchive() {
		if (fieldMediaArchive == null) {
			fieldMediaArchive = (MediaArchive) getModuleManager().getBean(getCatalogId(), "mediaArchive");

		}

		return fieldMediaArchive;
	}

	public String getCatalogId() {
		return fieldCatalogId;
	}

	public void setCatalogId(String inCatalogId) {
		fieldCatalogId = inCatalogId;
	}

	public RepositoryInfo getRepositoryInfo(String inCatalogId) {
		// MediaArchive archive = getMediaArchive();
		// RepositoryInfoImpl repositoryInfo = new RepositoryInfoImpl();
		// repositoryInfo.setId(inCatalogId);
		// repositoryInfo.setName("NetEvolved");
		// repositoryInfo.setDescription("NetEvolved");
		//
		// repositoryInfo.setVendorName("NetEvolved");
		// repositoryInfo.setDescription(archive.getCatalogId());

		RepositoryInfoImpl repositoryInfo = new RepositoryInfoImpl();
		// repositoryInfo.set
		repositoryInfo.setId("media/catalogs/public");
		repositoryInfo.setName("media/catalogs/public");
		repositoryInfo.setDescription("media/catalogs/public");

		// exercise 1.1
		// repositoryInfo.setCmisVersionSupported(CmisVersion.CMIS_1_0);

		// exercise 1.2
		repositoryInfo.setProductName("CMIS Connector");
		repositoryInfo.setProductVersion("1.0");
		repositoryInfo.setVendorName("NetEvolved");
		repositoryInfo.setRootFolder("index");

		return repositoryInfo;
	}

	public TypeDefinitionList getTypeChildren(CallContext inContext, String inTypeId, Boolean inIncludePropertyDefinitions, BigInteger inMaxItems, BigInteger inSkipCount) {
		// TODO Auto-generated method stub
		return null;
	}

	public TypeDefinition getTypeDefinition(CallContext inContext, String inTypeId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectInFolderList getChildren(CallContext inContext, String inFolderId, String inFilter, Boolean inIncludeAllowableActions, Boolean inIncludePathSegment, BigInteger inMaxItems,
			BigInteger inSkipCount, EnterMediaCmisService inEnterMediaCmisService) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ObjectInFolderContainer> getDescendants(CallContext inContext, String inFolderId, BigInteger inDepth, String inFilter, Boolean inIncludeAllowableActions, Boolean inIncludePathSegment,
			EnterMediaCmisService inEnterMediaCmisService, boolean inB) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectData getFolderParent(CallContext inContext, String inFolderId, String inFilter, EnterMediaCmisService inEnterMediaCmisService) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ObjectParentData> getObjectParents(CallContext inContext, String inObjectId, String inFilter, Boolean inIncludeAllowableActions, Boolean inIncludeRelativePathSegment,
			EnterMediaCmisService inEnterMediaCmisService) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectData create(CallContext inContext, Properties inProperties, String inFolderId, ContentStream inContentStream, VersioningState inVersioningState,
			EnterMediaCmisService inEnterMediaCmisService) {
		// TODO Auto-generated method stub
		return null;
	}

	public String createDocument(CallContext inContext, Properties inProperties, String inFolderId, ContentStream inContentStream, VersioningState inVersioningState) {
		// TODO Auto-generated method stub
		return null;
	}

	public String createDocumentFromSource(CallContext inContext, String inSourceId, Properties inProperties, String inFolderId, VersioningState inVersioningState) {
		// TODO Auto-generated method stub
		return null;
	}

	public String createFolder(CallContext inContext, Properties inProperties, String inFolderId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteObject(CallContext inContext, String inObjectId) {
		// TODO Auto-generated method stub

	}

	public FailedToDeleteData deleteTree(CallContext inContext, String inFolderId, Boolean inContinueOnFailure) {
		// TODO Auto-generated method stub
		return null;
	}

	public AllowableActions getAllowableActions(CallContext inContext, String inObjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ContentStream getContentStream(CallContext inContext, String inObjectId, BigInteger inOffset, BigInteger inLength) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectData getObject(CallContext inContext, String inObjectId, Object inObject, String inFilter, Boolean inIncludeAllowableActions, Boolean inIncludeAcl,
			EnterMediaCmisService inEnterMediaCmisService) {
		//boolean userReadOnly = checkUser(inContext, false);

        // check id
        if (inObjectId == null && inObjectId == null) {
            throw new CmisInvalidArgumentException("Object Id must be set.");
        }

        if (inObjectId == null) {
            // this works only because there are no versions in a file system
            // and the object id and version series id are the same
        //	inObjectId = inVersionSeriesId;
        }

        MediaArchive archive = getMediaArchive();
        if(inObjectId.startsWith("index")){
        	 ObjectDataImpl result = new ObjectDataImpl();
        	 
             ObjectInfoImpl objectInfo = new ObjectInfoImpl();
             String  typeId = BaseTypeId.CMIS_FOLDER.value();
             objectInfo.setBaseType(BaseTypeId.CMIS_FOLDER);
             objectInfo.setTypeId(typeId);
             objectInfo.setContentType(null);
             objectInfo.setFileName(null);
             objectInfo.setHasAcl(true);
             objectInfo.setHasContent(false);
             objectInfo.setVersionSeriesId(null);
             objectInfo.setIsCurrentVersion(true);
             objectInfo.setRelationshipSourceIds(null);
             objectInfo.setRelationshipTargetIds(null);
             objectInfo.setRenditionInfos(null);
             objectInfo.setSupportsDescendants(true);
             objectInfo.setSupportsFolderTree(true);
             objectInfo.setSupportsPolicies(false);
             objectInfo.setSupportsRelationships(false);
             objectInfo.setWorkingCopyId(null);
             objectInfo.setWorkingCopyOriginalId(null);
             objectInfo.setName("Index");
             if (inContext.isObjectInfoRequired()) {
                 objectInfo.setObject(result);
                 inEnterMediaCmisService.addObjectInfo(objectInfo);
             }
             return result;
        }
       return null;
    
       
	}

	public ObjectData getObjectByPath(CallContext inContext, String inPath, String inFilter, Boolean inIncludeAllowableActions, Boolean inIncludeAcl, EnterMediaCmisService inEnterMediaCmisService) {
		// TODO Auto-generated method stub
		return null;
	}



	public void moveObject(CallContext inContext, Holder<String> inObjectId, String inTargetFolderId, EnterMediaCmisService inEnterMediaCmisService) {
		// TODO Auto-generated method stub

	}

	public void changeContentStream(CallContext inContext, Holder<String> inObjectId, Boolean inOverwriteFlag, ContentStream inContentStream, boolean inB) {
		// TODO Auto-generated method stub

	}

	public void changeContentStream(CallContext inContext, Holder<String> inObjectId, boolean inOverwriteFlag, ContentStream inContentStream, boolean inB) {
		// TODO Auto-generated method stub

	}

	public void changeContentStream(CallContext inContext, Holder<String> inObjectId, boolean inOverwriteFlag, Object inContentStream, boolean inB) {
		// TODO Auto-generated method stub

	}

	public void updateProperties(CallContext inContext, Holder<String> inObjectId, Properties inProperties, EnterMediaCmisService inEnterMediaCmisService) {
		// TODO Auto-generated method stub

	}

	public List<BulkUpdateObjectIdAndChangeToken> bulkUpdateProperties(CallContext inContext, List<BulkUpdateObjectIdAndChangeToken> inObjectIdAndChangeToken, Properties inProperties,
			EnterMediaCmisService inEnterMediaCmisService) {
		// TODO Auto-generated method stub
		return null;
	}


	public Acl getAcl(CallContext inContext, String inObjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectList query(CallContext inContext, String inStatement, Boolean inIncludeAllowableActions, BigInteger inMaxItems, BigInteger inSkipCount, EnterMediaCmisService inEnterMediaCmisService) {
		// TODO Auto-generated method stub
		return null;
	}

}
