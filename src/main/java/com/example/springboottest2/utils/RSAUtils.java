package com.example.springboottest2.utils;


import cn.hutool.crypto.asymmetric.RSA;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class RSAUtils {

    /**
    * @Author: Wls
    * @Date: 0:17 2020/5/2
    * @Description: 从项目的资源文件夹下读取key文件，参数为文件名，返回经过 Base64 解码后的密钥
    */
//    public static byte[] getKeyFromFile(String file)  {
//        ClassPathResource classPathResource=new ClassPathResource(file);
//        if (!new File(classPathResource.getPath()).exists()) {
//
//        }
//        InputStream inputStream= null;
//        try {
//            inputStream = classPathResource.getInputStream();
//            ByteArrayOutputStream bio=new ByteArrayOutputStream(1024);
//            byte[] bytes=new byte[1024];
//            int n;
//            while ((n=inputStream.read(bytes))!=-1){
//                bio.write(bytes,0,n);
//            }
//            return Base64.decodeBase64(bio.toByteArray());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//    * @Author: Wls
//    * @Date: 0:19 2020/5/2
//    * @Description: 在项目资源文件夹下生成公私密钥对文件
//    */
//    public static void generateRsaKeyPair() throws IOException {
//        RSA rsa=new RSA();
//        FileOutputStream outputStream;
//
//
//
//        ClassPathResource priPath=new ClassPathResource("key.pri");
//        ClassPathResource pubPath=new ClassPathResource("key.pub");
//
//
//
//        outputStream=new FileOutputStream(priPath.getPath());
//        outputStream.write(rsa.getPrivateKeyBase64().getBytes());
//
//        outputStream=new FileOutputStream(pubPath.getPath());
//        outputStream.write(rsa.getPublicKeyBase64().getBytes());
//
//        outputStream.close();
//
//    }


}
