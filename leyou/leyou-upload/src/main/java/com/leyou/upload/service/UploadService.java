package com.leyou.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：胡锦洪
 * @date ：Created in 2020/2/28 21:33
 * 描述   ：
 */
@Service
public class UploadService {

    private final static List<String> CONTENT_TYPES = Arrays.asList("image/gif","image/jpeg");

    private final static Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    @Autowired
    private FastFileStorageClient storageClient;

    public String uploadImage(MultipartFile file) {
        //校验文件类型
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        if (!CONTENT_TYPES.contains(contentType)){
            LOGGER.info("文件类型不合法:{}",originalFilename);
            return null;
        }
        try{//校验文件内容
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null){
                LOGGER.info("文件内容不合法：{}",originalFilename);
                return null;
            }

            //保存到服务器
//            file.transferTo(new File("E:\\project\\image\\" + originalFilename));
             String s = StringUtils.substringAfterLast(originalFilename, ".");
             StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), s, null);

            //返回url，进行回显
            //return "http://image.leyou.com/" + originalFilename;
           return "http://image.leyou.com/" + storePath.getFullPath();
        }catch (IOException e){
            LOGGER.info("服务器内部异常：" + originalFilename);
            e.printStackTrace();
        }
        return null;
    }
}

    