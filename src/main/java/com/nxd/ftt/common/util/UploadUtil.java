package com.nxd.ftt.common.util;

import com.nxd.ftt.common.entity.BaseFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * UploadUtil
 *
 * @author OldMountain
 * @date 2018/5/30
 */
public class UploadUtil {

    /**
     * 文件上传
     *
     * @param request
     * @param rootFolder 文件上传根目录
     * @param folder 文件保存位置
     * @return
     * @throws IOException
     */
    public static List<BaseFile> upload(HttpServletRequest request, String rootFolder, String folder) throws IOException {
        MultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        String path = folder + File.separator + DateUtils.formateNowDay("yyyyMMdd");
        File file = new File(rootFolder, path);
        List<BaseFile> pathList = new ArrayList<>();
        if (!file.exists()) {
            file.mkdirs();
        }
        if (multipartResolver.isMultipart(request)) {
            MultipartRequest multipartRequest = (MultipartRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            while (fileNames.hasNext()) {
                MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
                if (multipartFile != null) {
                    String name = multipartFile.getOriginalFilename();
                    String etx = FTools.getEtx(name);
                    File newFile = new File(file, UuidUtil.get32UUID() + "." + etx);
                    multipartFile.transferTo(newFile);
                    BaseFile baseFile = new BaseFile(FTools.getFileName(name), multipartFile.getSize(), path + File.separator + newFile.getName(), etx);
                    pathList.add(baseFile);
                }
            }
        }
        return pathList;
    }
}
