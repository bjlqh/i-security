package com.lqh.security.demo.controller;


import com.lqh.security.demo.pojo.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Author: liqihua
 * Date: 2019/9/21 22:53
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private static final String FOLDER = "E:/WorkSpace/i-security/i-security/file";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {

        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(FOLDER, System.currentTimeMillis() + ".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //jdk 7 的特性，try(里面会帮你关闭)
        try (InputStream inputStream = new FileInputStream(new File(FOLDER, id + ".txt"));
             OutputStream outputStream = response.getOutputStream();) {

            //下载时,response设置contentType
            response.setContentType("application/x-download");
            //以指定的名字下载出去
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");
            //把文件的内容写到响应里面去
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }
}
