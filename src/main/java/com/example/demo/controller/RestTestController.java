package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.simpleframework.http.annotation.RestMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.demo.factory.AbcTest;
import com.example.demo.factory.RestFactory;
import com.example.demo.factory.TestParam;


/**
 * Description:
 *
 * @author linzc
 * @version 1.0
 *
 * <pre>
 * 修改记录:
 * 修改后版本        修改人     修改日期        修改内容
 * 2020/6/28.1    linzc       2020/6/28     Create
 * </pre>
 * @date 2020/6/28
 */
@RestController
public class RestTestController {
    @Resource
    private RestFactory restFactory;

    @GetMapping("/")
    public Object get2() {
        final String url = "http://localhost:8081/get?1=1";
        final String s1 = this.restFactory.get2(url, RestMethod.GET, "1", new Integer[]{2, 3, 5});
        this.restFactory.get3(url, new HashMap<String, Object>() {{
            put("key", 2);
            put("key2", "3");
        }});
        this.restFactory.get4(new TestParam("w1", "w2", new TestParam("n1", "n2")));
        final AbcTest post = this.restFactory.post(new AbcTest(1));
        final String put = this.restFactory.put(10);
        final String put2 = this.restFactory.put2(new TestParam("1", "2", new TestParam("3", "20")));
        final JSONArray objects = JSON.parseArray(put);
        List<List<AbcTest>> abcTests = new ArrayList<>();
        for (Object object : objects) {
            JSONArray jo = (JSONArray) object;
            final List<AbcTest> tests = JSON.parseArray(jo.toString(), AbcTest.class);
            abcTests.add(tests);
        }
        return abcTests;
    }

    @PostMapping("/upload")
    public int upload(MultipartFile file) {
        try {
            final InputStream inputStream = file.getInputStream();
            final int i = this.restFactory.fileUpload(inputStream, file.getOriginalFilename());
            System.out.println("2 = " + i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @PostMapping("/upload2")
    public int upload2(MultipartFile file, String fileName) {
        try {
            file.transferTo(new File("D:\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 2;
    }

    @PostMapping("/load")
    public void download() throws IOException {
        final InputStream file = this.restFactory.getFile();
        FileUtils.copyInputStreamToFile(file, new File("D:\\2.dll"));
        file.close();
    }

    @GetMapping("/load2")
    public ResponseEntity<byte[]> download2() throws IOException {
        String filename = "msdia80.dll";
        // 构建File
        File file = new File("d:" + File.separator + filename);
        // ok表示http请求中状态码200
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
        // 内容长度
        builder.contentLength(file.length());
        // application/octet-stream 二进制数据流（最常见的文件下载）
        builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        // 使用URLEncoding.decode对文件名进行解码
        filename = URLEncoder.encode(filename, "UTF-8");
        // 根据浏览器类型，决定处理方式
        builder.header("Content-Disposition", "attacher; filename=" + filename);
        return builder.body(FileUtils.readFileToByteArray(file));
    }

    @GetMapping("/get")
    public Object get(String key, Integer[] key2, String key3, @RequestHeader("access-token") String token) {
        return "key=" + key + ", key2=" + JSON.toJSONString(key2) + ", key3=" + key3 + ", token=" + token;
    }

    @PostMapping("/get3")
    public Object get3(TestParam param, @RequestHeader("access-token") String token) {
        return JSON.toJSONString(param) + ", token=" + token;
    }

    @PostMapping("/post")
    public AbcTest put(@RequestBody AbcTest abcTest) {
        abcTest.setId(abcTest.getId() + 1);
        return abcTest;
    }

    @PutMapping("/put/{size}")
    public List<List<AbcTest>> put(@PathVariable Integer size, String key3) {
        List<List<AbcTest>> testss = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<AbcTest> tests = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                tests.add(new AbcTest(j + 1 + (i * size)));
            }
            testss.add(tests);
        }
        return testss;
    }
}