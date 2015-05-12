package org.netevolved.cmis;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.chemistry.opencmis.commons.data.Acl;
import org.apache.chemistry.opencmis.commons.data.AllowableActions;
import org.apache.chemistry.opencmis.commons.data.BulkUpdateObjectIdAndChangeToken;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.data.ExtensionsData;
import org.apache.chemistry.opencmis.commons.data.FailedToDeleteData;
import org.apache.chemistry.opencmis.commons.data.ObjectData;
import org.apache.chemistry.opencmis.commons.data.ObjectInFolderContainer;
import org.apache.chemistry.opencmis.commons.data.ObjectInFolderList;
import org.apache.chemistry.opencmis.commons.data.ObjectList;
import org.apache.chemistry.opencmis.commons.data.ObjectParentData;
import org.apache.chemistry.opencmis.commons.data.Properties;
import org.apache.chemistry.opencmis.commons.data.RenditionData;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.definitions.TypeDefinition;
import org.apache.chemistry.opencmis.commons.definitions.TypeDefinitionList;
import org.apache.chemistry.opencmis.commons.enums.IncludeRelationships;
import org.apache.chemistry.opencmis.commons.enums.UnfileObject;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ObjectListImpl;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.RepositoryInfoImpl;
import org.apache.chemistry.opencmis.commons.impl.server.AbstractCmisService;
import org.apache.chemistry.opencmis.commons.server.CallContext;
import org.apache.chemistry.opencmis.commons.spi.Holder;
import org.example.cmis.server.FileBridgeRepository;

import com.openedit.ModuleManager;

public class EnterMediaCmisService extends AbstractCmisService {

	private CallContext fieldContext;

	public CallContext getCallContext() {
		return fieldContext;
	}

	public void setCallContext(CallContext inContext) {
		fieldContext = inContext;
	}

	protected ModuleManager fieldModuleManager;

	public CmisRepository getRepository(String inCatalogId) {

		CmisRepository repository = (CmisRepository) getModuleManager()
				.getBean(inCatalogId, "cmisRepository");

		return repository;
	}

	public ModuleManager getModuleManager() {
		return fieldModuleManager;
	}

	public void setModuleManager(ModuleManager inModuleManager) {
		fieldModuleManager = inModuleManager;
	}

	@Override
	public RepositoryInfo getRepositoryInfo(String repositoryId,
			ExtensionsData extension) {
		return getRepository(repositoryId).getRepositoryInfo(repositoryId);

		// throw new CmisObjectNotFoundException("Unknown repository '" +
		// repositoryId + "'!");
	}

	@Override
	public List<RepositoryInfo> getRepositoryInfos(ExtensionsData extension) {
		List<RepositoryInfo> result = new ArrayList<RepositoryInfo>();
		RepositoryInfoImpl repositoryInfo = new RepositoryInfoImpl();

		repositoryInfo.setId("media/catalogs/public");
		repositoryInfo.setName("media/catalogs/public");
		repositoryInfo.setDescription("media/catalogs/public");

		// exercise 1.1
		repositoryInfo.setCmisVersionSupported("1.1");

		// exercise 1.2
		repositoryInfo.setProductName("CMIS Connector");
		repositoryInfo.setProductVersion("1.0");
		repositoryInfo.setVendorName("NetEvolved");
		// for (FileBridgeRepository fsr : repositoryManager.getRepositories())
		// {
		// result.add(fsr.getRepositoryInfo(getContext()));
		// }
		result.add(repositoryInfo);
		return result;
	}

	@Override
	public TypeDefinitionList getTypeChildren(String repositoryId,
			String typeId, Boolean includePropertyDefinitions,
			BigInteger maxItems, BigInteger skipCount, ExtensionsData extension) {
		return getRepository(repositoryId).getTypeChildren(getCallContext(),
				typeId, includePropertyDefinitions, maxItems, skipCount);
	}

	@Override
	public TypeDefinition getTypeDefinition(String repositoryId, String typeId,
			ExtensionsData extension) {
		return getRepository(repositoryId).getTypeDefinition(getCallContext(),
				typeId);
	}

	// @Override
	// public List<TypeDefinitionContainer> getTypeDescendants(String
	// repositoryId, String typeId, BigInteger depth,
	// Boolean includePropertyDefinitions, ExtensionsData extension) {
	// return getRepository(repositoryId).getTypeDescendants(getContext(),
	// typeId,
	// depth, includePropertyDefinitions);
	// }

	// --- navigation service ---

	@Override
	public ObjectInFolderList getChildren(String repositoryId, String folderId,
			String filter, String orderBy, Boolean includeAllowableActions,
			IncludeRelationships includeRelationships, String renditionFilter,
			Boolean includePathSegment, BigInteger maxItems,
			BigInteger skipCount, ExtensionsData extension) {
		return getRepository(repositoryId).getChildren(getCallContext(), folderId,
				filter, includeAllowableActions, includePathSegment, maxItems,
				skipCount, this);
	}

	@Override
	public List<ObjectInFolderContainer> getDescendants(String repositoryId,
			String folderId, BigInteger depth, String filter,
			Boolean includeAllowableActions,
			IncludeRelationships includeRelationships, String renditionFilter,
			Boolean includePathSegment, ExtensionsData extension) {
		return getRepository(repositoryId).getDescendants(getCallContext(),
				folderId, depth, filter, includeAllowableActions,
				includePathSegment, this, false);
	}

	@Override
	public ObjectData getFolderParent(String repositoryId, String folderId,
			String filter, ExtensionsData extension) {
		return getRepository(repositoryId).getFolderParent(getCallContext(),
				folderId, filter, this);
	}

	@Override
	public List<ObjectInFolderContainer> getFolderTree(String repositoryId,
			String folderId, BigInteger depth, String filter,
			Boolean includeAllowableActions,
			IncludeRelationships includeRelationships, String renditionFilter,
			Boolean includePathSegment, ExtensionsData extension) {
		return getRepository(repositoryId).getDescendants(getCallContext(),
				folderId, depth, filter, includeAllowableActions,
				includePathSegment, this, true);
	}

	@Override
	public List<ObjectParentData> getObjectParents(String repositoryId,
			String objectId, String filter, Boolean includeAllowableActions,
			IncludeRelationships includeRelationships, String renditionFilter,
			Boolean includeRelativePathSegment, ExtensionsData extension) {
		return getRepository(repositoryId).getObjectParents(getCallContext(),
				objectId, filter, includeAllowableActions,
				includeRelativePathSegment, this);
	}

	@Override
	public ObjectList getCheckedOutDocs(String repositoryId, String folderId,
			String filter, String orderBy, Boolean includeAllowableActions,
			IncludeRelationships includeRelationships, String renditionFilter,
			BigInteger maxItems, BigInteger skipCount, ExtensionsData extension) {
		ObjectListImpl result = new ObjectListImpl();
		result.setHasMoreItems(false);
		result.setNumItems(BigInteger.ZERO);
		List<ObjectData> emptyList = Collections.emptyList();
		result.setObjects(emptyList);

		return result;
	}

	// --- object service ---

	@Override
	public String create(String repositoryId, Properties properties,
			String folderId, ContentStream contentStream,
			VersioningState versioningState, List<String> policies,
			ExtensionsData extension) {
		ObjectData object = getRepository(repositoryId).create(getCallContext(),
				properties, folderId, contentStream, versioningState, this);

		return object.getId();
	}

	@Override
	public String createDocument(String repositoryId, Properties properties,
			String folderId, ContentStream contentStream,
			VersioningState versioningState, List<String> policies,
			Acl addAces, Acl removeAces, ExtensionsData extension) {
		return getRepository(repositoryId).createDocument(getCallContext(),
				properties, folderId, contentStream, versioningState);
	}

	@Override
	public String createDocumentFromSource(String repositoryId,
			String sourceId, Properties properties, String folderId,
			VersioningState versioningState, List<String> policies,
			Acl addAces, Acl removeAces, ExtensionsData extension) {
		return getRepository(repositoryId).createDocumentFromSource(
				getCallContext(), sourceId, properties, folderId, versioningState);
	}

	@Override
	public String createFolder(String repositoryId, Properties properties,
			String folderId, List<String> policies, Acl addAces,
			Acl removeAces, ExtensionsData extension) {
		return getRepository(repositoryId).createFolder(getCallContext(),
				properties, folderId);
	}

	@Override
	public void deleteObjectOrCancelCheckOut(String repositoryId,
			String objectId, Boolean allVersions, ExtensionsData extension) {
		getRepository(repositoryId).deleteObject(getCallContext(), objectId);
	}

	@Override
	public FailedToDeleteData deleteTree(String repositoryId, String folderId,
			Boolean allVersions, UnfileObject unfileObjects,
			Boolean continueOnFailure, ExtensionsData extension) {
		return getRepository(repositoryId).deleteTree(getCallContext(), folderId,
				continueOnFailure);
	}

	@Override
	public AllowableActions getAllowableActions(String repositoryId,
			String objectId, ExtensionsData extension) {
		return getRepository(repositoryId).getAllowableActions(getCallContext(),
				objectId);
	}

	@Override
	public ContentStream getContentStream(String repositoryId, String objectId,
			String streamId, BigInteger offset, BigInteger length,
			ExtensionsData extension) {
		return getRepository(repositoryId).getContentStream(getCallContext(),
				objectId, offset, length);
	}

	@Override
	public ObjectData getObject(String repositoryId, String objectId,
			String filter, Boolean includeAllowableActions,
			IncludeRelationships includeRelationships, String renditionFilter,
			Boolean includePolicyIds, Boolean includeAcl,
			ExtensionsData extension) {
		return getRepository(repositoryId).getObject(getCallContext(), objectId,
				null, filter, includeAllowableActions, includeAcl, this);
	}

	@Override
	public ObjectData getObjectByPath(String repositoryId, String path,
			String filter, Boolean includeAllowableActions,
			IncludeRelationships includeRelationships, String renditionFilter,
			Boolean includePolicyIds, Boolean includeAcl,
			ExtensionsData extension) {
		return getRepository(repositoryId).getObjectByPath(getCallContext(), path,
				filter, includeAllowableActions, includeAcl, this);
	}

	@Override
	public Properties getProperties(String repositoryId, String objectId,
			String filter, ExtensionsData extension) {
		ObjectData object = getRepository(repositoryId).getObject(getCallContext(),
				objectId, null, filter, false, false, this);
		return object.getProperties();
	}

	@Override
	public List<RenditionData> getRenditions(String repositoryId,
			String objectId, String renditionFilter, BigInteger maxItems,
			BigInteger skipCount, ExtensionsData extension) {
		return Collections.emptyList();
	}

	@Override
	public void moveObject(String repositoryId, Holder<String> objectId,
			String targetFolderId, String sourceFolderId,
			ExtensionsData extension) {
		getRepository(repositoryId).moveObject(getCallContext(), objectId,
				targetFolderId, this);
	}

	@Override
	public void setContentStream(String repositoryId, Holder<String> objectId,
			Boolean overwriteFlag, Holder<String> changeToken,
			ContentStream contentStream, ExtensionsData extension) {
		getRepository(repositoryId).changeContentStream(getCallContext(), objectId,
				overwriteFlag, contentStream, false);
	}

	@Override
	public void appendContentStream(String repositoryId,
			Holder<String> objectId, Holder<String> changeToken,
			ContentStream contentStream, boolean isLastChunk,
			ExtensionsData extension) {
		getRepository(repositoryId).changeContentStream(getCallContext(), objectId,
				true, contentStream, true);
	}

	@Override
	public void deleteContentStream(String repositoryId,
			Holder<String> objectId, Holder<String> changeToken,
			ExtensionsData extension) {
		getRepository(repositoryId).changeContentStream(getCallContext(), objectId,
				true, null, false);
	}

	@Override
	public void updateProperties(String repositoryId, Holder<String> objectId,
			Holder<String> changeToken, Properties properties,
			ExtensionsData extension) {
		getRepository(repositoryId).updateProperties(getCallContext(), objectId,
				properties, this);
	}

	@Override
	public List<BulkUpdateObjectIdAndChangeToken> bulkUpdateProperties(
			String repositoryId,
			List<BulkUpdateObjectIdAndChangeToken> objectIdAndChangeToken,
			Properties properties, List<String> addSecondaryTypeIds,
			List<String> removeSecondaryTypeIds, ExtensionsData extension) {
		return getRepository(repositoryId).bulkUpdateProperties(getCallContext(),
				objectIdAndChangeToken, properties, this);
	}

	// --- versioning service ---

	@Override
	public List<ObjectData> getAllVersions(String repositoryId,
			String objectId, String versionSeriesId, String filter,
			Boolean includeAllowableActions, ExtensionsData extension) {
		ObjectData theVersion = getRepository(repositoryId).getObject(
				getCallContext(), objectId, versionSeriesId, filter,
				includeAllowableActions, false, this);

		return Collections.singletonList(theVersion);
	}

	@Override
	public ObjectData getObjectOfLatestVersion(String repositoryId,
			String objectId, String versionSeriesId, Boolean major,
			String filter, Boolean includeAllowableActions,
			IncludeRelationships includeRelationships, String renditionFilter,
			Boolean includePolicyIds, Boolean includeAcl,
			ExtensionsData extension) {
		return getRepository(repositoryId).getObject(getCallContext(), objectId,
				versionSeriesId, filter, includeAllowableActions, includeAcl,
				this);
	}

	@Override
	public Properties getPropertiesOfLatestVersion(String repositoryId,
			String objectId, String versionSeriesId, Boolean major,
			String filter, ExtensionsData extension) {
		ObjectData object = getRepository(repositoryId).getObject(getCallContext(),
				objectId, versionSeriesId, filter, false, false, null);

		return object.getProperties();
	}

	// --- ACL service ---

	@Override
	public Acl getAcl(String repositoryId, String objectId,
			Boolean onlyBasicPermissions, ExtensionsData extension) {
		return getRepository(repositoryId).getAcl(getCallContext(), objectId);
	}

	// --- discovery service ---

	@Override
	public ObjectList query(String repositoryId, String statement,
			Boolean searchAllVersions, Boolean includeAllowableActions,
			IncludeRelationships includeRelationships, String renditionFilter,
			BigInteger maxItems, BigInteger skipCount, ExtensionsData extension) {
		return getRepository(repositoryId).query(getCallContext(), statement,
				includeAllowableActions, maxItems, skipCount, this);
	}

}