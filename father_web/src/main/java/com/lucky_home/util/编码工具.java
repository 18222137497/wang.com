package com.lucky_home.util;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class 编码工具 {
    /**
     * 解码查看
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        File file=new File("src\\main\\webapp\\png");
        File[] files = file.listFiles();
        PrintStream printStream =new PrintStream("src\\main\\resources\\jimgname.txt");
        System.setOut(printStream);
        for (File f : files) {
            String name = f.getName();
            String decode = decode(name);
            System.out.println(decode+"----"+name);//解码名----编码名
        }
    }

    /**
     * 指定文件进行utf-8编码
     * @param ff
     * @param f
     * @throws UnsupportedEncodingException
     */
    public static void encode(File ff,File f) throws UnsupportedEncodingException {
        String name = f.getName();
        String encode = URLEncoder.encode(name, "utf-8");
        f.renameTo(new File(ff + "/" + encode));

    }

    /**
     * utf-8解码
     * @param name
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decode(String name) throws UnsupportedEncodingException {
        String decode = URLDecoder.decode(name, "UTF-8");
        return decode;
    }

}
