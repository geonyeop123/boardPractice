package com.boardPractice.util;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.UUID;

public class UploadFileUtils {

    private static final Logger logger =
            LoggerFactory.getLogger(UploadFileUtils.class);

    public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception{

        UUID uuid = UUID.randomUUID();

        String savedName = uuid.toString() + "_" + originalName;

        String savedPath = calcPath(uploadPath);

        File target = new File(uploadPath + savedPath, savedName);

        FileCopyUtils.copy(fileData, target);

        String formatName = originalName.substring(originalName.lastIndexOf(".")+1);

        String uploadedFileName = null;

        if(MediaUtils.getMediaType(formatName) != null){
            uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
        }else{
            uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
        }
        return uploadedFileName;
    }

    private static String makeIcon(String uploadPath, String path, String fileName) throws Exception{
        String iconName = uploadPath + path + File.separator + fileName;

        return iconName.substring(
                uploadPath.length()).replace(File.separatorChar, '/');
    }

    private static String calcPath(String uploadPath){
        LocalDateTime localDateTime = LocalDateTime.now();
        String yearPath = File.separator + localDateTime.getYear();

        String monthPath = yearPath +
                           File.separator +
                           new DecimalFormat("00").format(localDateTime.getMonthValue());

        String datePath = monthPath +
                          File.separator +
                          new DecimalFormat("00").format(localDateTime.getDayOfMonth());

        makeDir(uploadPath, yearPath, monthPath, datePath);

        logger.debug(datePath);

        return datePath;
    }

    private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception{

        // 썸네일 이미지 생성을 위해 소스 이미지 가져오기
        BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));

        // 썸네일 이미지 생성(size : 100)
        BufferedImage destImg = Scalr.resize(sourceImg,
                                              Scalr.Method.AUTOMATIC,
                                              Scalr.Mode.FIT_TO_HEIGHT, 100);

        // 원본 파일과 썸네일 파일을 구분하기 위해 썸네일 파일명에 s_ 붙히기
        String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;

        // 썸네일 객체 생성
        File newFile = new File(thumbnailName);

        // 확장자 명 가져오기
        String formatName = fileName.substring(fileName.lastIndexOf(".")+1);

        // 썸네일 생성
        ImageIO.write(destImg, formatName.toUpperCase(), newFile);

        return thumbnailName.substring(
                uploadPath.length()).replace(File.separatorChar, '/');
    }

    private static void makeDir(String uploadPath, String... paths){
        if(new File(uploadPath + paths[paths.length - 1]).exists()) return;

        for(String path : paths){
            File dirPath = new File(uploadPath + path);
            if(!dirPath.exists()) dirPath.mkdir();
        }
    }
}
