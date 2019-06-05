package com.spsy.common.service;


import com.spsy.common.utils.FastDFSUtil;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class FastDFSService {

    private static TrackerClient trackerClient;

    private static TrackerServer trackerServer;

    private static StorageServer storageServer;

    private static StorageClient storageClient;


    /**上传工具配置*/
    static {
        try {
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();;
            ClientGlobal.init(filePath);
            trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
            trackerServer = trackerClient.getConnection();
            storageServer= trackerClient.getStoreStorage(trackerServer);
            storageClient = new StorageClient(trackerServer, storageServer);

        } catch (Exception e) {
            log.error("异常信息：[{}]",e.getMessage());
        }
    }


    /**
     * 文件上传
     * @param multipartFile 上传的文件
     * @param valuePairs 文件详细信息 如上传作者
     * @return 文件所在的url
     */
    public static String uploadToFDFS(MultipartFile multipartFile, NameValuePair[] valuePairs) throws IOException {

        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        InputStream inputStream=multipartFile.getInputStream();
        byte[] file_buff = null;

        if(inputStream!=null){
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }

        inputStream.close();

        FastDFSUtil file = new FastDFSUtil(ext, null, fileName, file_buff);

        String[] uploadResults = null;
        try {
            uploadResults = storageClient.upload_file(null,file.getContent(),file.getExt(),valuePairs);
        } catch (Exception e) {
            log.error("异常信息：[{}]",e.getMessage());
        }

        String fileUrl = "";
        for (String url :
                uploadResults) {
            fileUrl += "/" + url;
        }
        return fileUrl;
    }


    /**
     * 删除文件
     * @param fileUrl 文件所在的url
     */
    public static void deletefile(final String fileUrl){
        try {
            final int group1 = storageClient.delete_file(null, fileUrl);
        } catch (IOException e) {
            log.error("异常信息：[{}]",e.getMessage());
        } catch (Exception e) {
            log.error("异常信息：[{}]",e.getMessage());
        }
    }


    /**
     * 文件下载
     * @param fileUrl 文件所在的url
     * @return 文件字节流
     */
    public static byte[] download(final String fileUrl){
        byte[] group1s = null;
        try {
            group1s = storageClient.download_file(null, fileUrl);
        } catch (IOException e) {
            log.error("异常信息：[{}]",e.getMessage());
        } catch (Exception e) {
            log.error("异常信息：[{}]",e.getMessage());
        }
        return group1s;
    }


    /**
     * 获取文件属性
     * @param groupname 文件所在组名
     * @param fileId 文件id
     * @return 文件相关属性
     */
    public Map<String,String> getFileMetadata(final String groupname, final String fileId) {
        try {
            NameValuePair[] metaList = storageClient.get_metadata(groupname,fileId);
            if (metaList != null) {
                HashMap<String,String> map = new HashMap<String, String>();
                for (NameValuePair metaItem : metaList) {
                    map.put(metaItem.getName(),metaItem.getValue());
                }
                return map;
            }
        } catch (Exception e) {
            log.error("异常信息：[{}]",e.getMessage());
        }
        return null;

    }
}
