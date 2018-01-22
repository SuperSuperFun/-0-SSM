package com.wangli.controller;

import com.wangli.service.UserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wangli
 */
@Controller
public class WordController {
    @Autowired
    private UserService userService;

    @RequestMapping("/test/toWord")
    public void toWord(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
        File file = createDoc();

        res.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        BufferedInputStream bis = null;
        BufferedOutputStream out = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String now = sdf.format(new Date());

        long fileLength = file.length();
        res.setContentType("application/msword");
        //设置默认文件名，后缀需要加上.doc
        res.setHeader("Content-disposition", "attachment; filename="
                .concat(String.valueOf(URLEncoder.encode("测试的-系统导出时间" + now + ".doc", "utf-8"))));
        res.setHeader("Content-Length", String.valueOf(fileLength));

        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            out = new BufferedOutputStream(res.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
                out.write(buff, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(bis);
            close(out);
        }
    }

    private File createDoc() {
        //创建数据
        Map<String, Object> dataMap = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String now = sdf.format(new Date());
        List<?> eList = userService.getAllUsers();
        dataMap.put("test1", "测试的");
        dataMap.put("test2", now);
        dataMap.put("eList", eList);
        //获取模板
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(this.getClass(), "/tpl");
        Template t = null;

        String name = "temp"+(int)(Math.random()*1000)+".doc";
        File file = new File(name);

        try {
            t = configuration.getTemplate("模板.xml");
            t.setOutputEncoding("UTF-8");

            Writer out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            t.process(dataMap, out);
            close(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    private void close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
