package com.example.administrator.okhttpdemo.javaBeans;

import java.util.HashMap;

public class ResourceInfoClass {
	private String DocumentViewId;
	private String ExtName;
	private HashMap<String, String> ExtendList;
	private String FileId;
	private String FileNameTitle;
	private String StorageConfigName;
	private String HttpPath;

	public String InfoType;
	private String PhysicalPath;
	public int PathID;
	public boolean IsDocument;
	public boolean IsCover;
	public int FileSizeK;
	public boolean CanDocumentView;

	// private String Url;
	// private String SiteInUrl;

	/**
	 * @return the extendList
	 */
	public HashMap<String, String> getExtendList() {
		return ExtendList;
	}

	/**
	 * @param extendList
	 *            the extendList to set
	 */
	public void setExtendList(HashMap<String, String> extendList) {
		ExtendList = extendList;
	}

	/**
	 * @return the documentViewId
	 */
	public String getDocumentViewId() {
		return DocumentViewId;
	}

	/**
	 * @param documentViewId
	 *            the documentViewId to set
	 */
	public void setDocumentViewId(String documentViewId) {
		DocumentViewId = documentViewId;
	}

	/**
	 * @return the canDocumentView
	 */
	public boolean isCanDocumentView() {
		return CanDocumentView;
	}

	/**
	 * @param canDocumentView
	 *            the canDocumentView to set
	 */
	public void setCanDocumentView(boolean canDocumentView) {
		CanDocumentView = canDocumentView;
	}

	/**
	 * @return the isDocument
	 */
	public boolean isIsDocument() {
		return IsDocument;
	}

	/**
	 * @param isDocument
	 *            the isDocument to set
	 */
	public void setIsDocument(boolean isDocument) {
		IsDocument = isDocument;
	}

	/**
	 * @return the isCover
	 */
	public boolean isIsCover() {
		return IsCover;
	}

	/**
	 * @param isCover
	 *            the isCover to set
	 */
	public void setIsCover(boolean isCover) {
		IsCover = isCover;
	}

	/**
	 * @return the infoType
	 */
	public String getInfoType() {
		return InfoType;
	}

	/**
	 * @param infoType
	 *            the infoType to set
	 */
	public void setInfoType(String infoType) {
		InfoType = infoType;
	}

	// /**
	// * @return the url
	// */
	// public String getUrl() {
	// return Url;
	// }
	//
	// /**
	// * @param url
	// * the url to set
	// */
	// public void setUrl(String url) {
	// Url = url;
	// }
	//
	// /**
	// * @return the siteInUrl
	// */
	// public String getSiteInUrl() {
	// return SiteInUrl;
	// }
	//
	// /**
	// * @param siteInUrl
	// * the siteInUrl to set
	// */
	// public void setSiteInUrl(String siteInUrl) {
	// SiteInUrl = siteInUrl;
	// }

	/**
	 * @return the storageConfigName
	 */
	public String getStorageConfigName() {
		return StorageConfigName;
	}

	/**
	 * @param storageConfigName
	 *            the storageConfigName to set
	 */
	public void setStorageConfigName(String storageConfigName) {
		StorageConfigName = storageConfigName;
	}

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return FileId;
	}

	/**
	 * @param fileId
	 *            the fileId to set
	 */
	public void setFileId(String fileId) {
		FileId = fileId;
	}

	/**
	 * @return the pathID
	 */
	public int getPathID() {
		return PathID;
	}

	/**
	 * @param pathID
	 *            the pathID to set
	 */
	public void setPathID(int pathID) {
		PathID = pathID;
	}

	/**
	 * @return the extName
	 */
	public String getExtName() {
		return ExtName;
	}

	/**
	 * @param extName
	 *            the extName to set
	 */
	public void setExtName(String extName) {
		ExtName = extName;
	}

	/**
	 * @return the fileNameTitle
	 */
	public String getFileNameTitle() {
		return FileNameTitle;
	}

	/**
	 * @param fileNameTitle
	 *            the fileNameTitle to set
	 */
	public void setFileNameTitle(String fileNameTitle) {
		FileNameTitle = fileNameTitle;
	}

	/**
	 * @return the fileSizeK
	 */
	public int getFileSizeK() {
		return FileSizeK;
	}

	/**
	 * @param fileSizeK
	 *            the fileSizeK to set
	 */
	public void setFileSizeK(int fileSizeK) {
		FileSizeK = fileSizeK;
	}

	/**
	 * @return the httpPath
	 */
	public String getHttpPath() {
		return HttpPath;
	}

	/**
	 * @param httpPath
	 *            the httpPath to set
	 */
	public void setHttpPath(String httpPath) {
		HttpPath = httpPath;
	}

	/**
	 * @return the physicalPath
	 */
	public String getPhysicalPath() {
		return PhysicalPath;
	}

	/**
	 * @param physicalPath
	 *            the physicalPath to set
	 */
	public void setPhysicalPath(String physicalPath) {
		PhysicalPath = physicalPath;
	}

}