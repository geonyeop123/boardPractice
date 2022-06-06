package com.boardPractice.controller;

import com.boardPractice.util.MediaUtils;
import com.boardPractice.util.UploadFileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

@Controller
public class UploadController {

    private static final Logger logger =
            LoggerFactory.getLogger(UploadController.class);

    private static final String uploadPath = "/Users/yeop/Documents/zzz/upload";

    @GetMapping("/uploadAjax")
    public void uploadAjax(){

    }

    @ResponseBody
    @PostMapping(value = "/uploadAjax", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception{
        logger.debug("originalName : " + file.getOriginalFilename());

        return new ResponseEntity<>(
                UploadFileUtils.uploadFile(uploadPath,
                                          file.getOriginalFilename(),
                                          file.getBytes()),
                HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping("/displayFile")
    public ResponseEntity<byte[]> displayFile(String fileName) throws Exception{
        InputStream in = null;
        ResponseEntity<byte[]> entity = null;

        logger.debug("FILE NAME : " + fileName);

        try{
            String formatName = fileName.substring(fileName.lastIndexOf(".")+1);

            MediaType mediaType = MediaUtils.getMediaType(formatName);

            HttpHeaders headers = new HttpHeaders();

            in = new FileInputStream(uploadPath + fileName);

            if(mediaType != null){
                headers.setContentType(mediaType);
            }else{
                fileName = fileName.substring(fileName.indexOf("_")+1);
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.add("Content-Disposition", "attachment; filename=\""+
                            new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
            }

            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
                    headers,
                    HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        }finally {
            in.close();
        }
        return entity;
    }



    @GetMapping("/uploadForm")
    public void uploadForm(){
    }

    @PostMapping("/uploadForm")
    public void uploadForm(MultipartFile file, Model m)throws Exception{

        logger.debug("originalName : " + file.getOriginalFilename());
        logger.debug("size : " + file.getSize());
        logger.debug("contentType : " + file.getContentType());

        String savedName =
                uploadFile(file.getOriginalFilename(), file.getBytes());

        m.addAttribute("savedName", savedName);
    }

    private String uploadFile(String originalName, byte[] fileData) throws Exception{
        UUID uid = UUID.randomUUID();

        String savedName = uid.toString() + "_" + originalName;

        File target = new File(uploadPath, savedName);

        FileCopyUtils.copy(fileData, target);

        return savedName;
    }



}
