package org.tyrest.opendata.storage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.opendata.constants.OpenDataConstants;
import org.tyrest.opendata.storage.constant.QiNiuConstants;
import org.tyrest.opendata.storage.model.QiniuResourceModel;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;

@Component
public class Storage {

	@Value("${qiniuConfigAccessKey}")
	private String qiniuConfigAccessKey;
	
	@Value("${qiniuConfigSecretKey}")
	private String qiniuConfigSecretKey;

	private Auth auth;

	private BucketManager bucketManager;

	private UploadManager uploadManager;

	private synchronized Auth getAuth() {
		if (this.auth == null) {
			this.auth = Auth.create(qiniuConfigAccessKey, qiniuConfigSecretKey);
		}
		return this.auth;
	}

	private synchronized BucketManager getBucketManager() {
		if (this.bucketManager == null) {
			this.bucketManager = new BucketManager(this.getAuth());
		}
		return this.bucketManager;
	}

	public static void main(String[] args) {

	}

	private synchronized UploadManager getUploadManager() {
		if (this.uploadManager == null) {
			this.uploadManager = new UploadManager();
		}
		return this.uploadManager;
	}

	/**
	 * @param spaceName
	 * @return 上传凭证
	 * @throws Exception
	 */
	private String getUpToken(String spaceName) throws Exception {
		String currentToken = Redis.get(spaceName);
		if (ValidationUtil.isEmpty(currentToken)) {
			currentToken = this.getAuth().uploadToken(spaceName);
			Redis.set(currentToken,OpenDataConstants.CACHE_KEY_PREFIX_QINIUTOKEN,spaceName);
		}
		return currentToken;
	}

	/**
	 * 获取所有的空间名列表
	 * 
	 * @return
	 * @throws QiniuException
	 */
	public String[] getSapceNameList() throws QiniuException {
		String[] buckets = this.getBucketManager().buckets();
		return buckets;
	}

	/**
	 * 获取空间下的文件属性
	 * 
	 * @param spaceName
	 * @param fileName
	 * @return
	 * @throws BusinessException
	 * @throws QiniuException
	 */
	public boolean getFileInfo(String spaceName, String fileName) throws BusinessException {
		if (ValidationUtil.isEmpty(spaceName)) {
			throw new BusinessException(QiNiuConstants.SAPCE_NAME_ERROR);
		}
		FileInfo info;
		try {
			info = this.getBucketManager().stat(spaceName, fileName);
			if (!ValidationUtil.isEmpty(info.fsize)) {
				return true;
			}
		} catch (QiniuException e) {
			return false;
		}
		return false;
	}

	/**
	 * 删除指定空间下的文件
	 * 
	 * @param spaceName
	 * @param fileName
	 * @throws QiniuException
	 */
	public void deleteFile(String spaceName, String fileName) throws QiniuException {
		this.getBucketManager().delete(spaceName, fileName);
	}

	public class MyRet {
		public long fsize;
		public String key;
		public String hash;
		public int width;
		public int height;
	}

	/**
	 * @param filePath
	 * @param fileName
	 * @param spaceName
	 * @return
	 * @throws Exception
	 */
	public Response upload(String filePath, String fileName, String spaceName) throws Exception {
		Response response = null;
		try {
			response = this.getUploadManager().put(filePath, fileName, getUpToken(spaceName));
			if (response.statusCode != 200) {
				throw new BusinessException(response.error);
			}
			return response;
		} catch (QiniuException e) {
			response = e.response;
			throw new BusinessException(response.error);
		}
	}

	/**
	 * @param filePath
	 * @param fileName
	 * @param spaceName
	 * @return
	 * @throws Exception
	 */
	public Response upload(File file, String fileName, String spaceName) throws Exception {
		Response response = null;
		try {
			response = this.getUploadManager().put(file, fileName, getUpToken(spaceName));
			if (response.statusCode != 200) {
				throw new BusinessException(response.error);
			}
			return response;
		} catch (QiniuException e) {
			response = e.response;
			throw new BusinessException(response.error);
		}
	}

	/**
	 * @param filePath
	 * @param fileName
	 * @param spaceName
	 * @return
	 * @throws Exception
	 */
	public Response upload(byte[] file, String fileName, String spaceName) throws Exception {

		Response response = null;
		try {
			response = this.getUploadManager().put(file, fileName, getUpToken(spaceName));
			if (response.statusCode != 200) {
				throw new BusinessException(response.error);
			}
			return response;
		} catch (QiniuException e) {
			response = e.response;
			throw new BusinessException(response.error);
		}
	}

	public String flushQiniuToken(String spaceName) throws Exception {
		return this.getUpToken(spaceName);
	}

	public List<QiniuResourceModel> getResourceList(String spaceName, String dir) throws Exception {
		FileListing filelisting = this.getBucketManager().listFiles(spaceName, dir, null,
				QiNiuConstants.QN_GET_FILES_MAX_LIMIT, null);
		List<FileInfo> fileList = Arrays.asList(filelisting.items);
		List<QiniuResourceModel> result = new ArrayList<QiniuResourceModel>();
		QiniuResourceModel currentFile = null;
		for (FileInfo file : fileList) {
			currentFile = new QiniuResourceModel();
			currentFile.setKey(file.key.substring(dir.length() + 1));
			currentFile.setFsize(file.fsize);
			currentFile.setRealFileName(file.key);
			currentFile.setPutTime(file.putTime);
			currentFile.setFileName(file.key);
			result.add(currentFile);
		}
		return result;
	}
}
