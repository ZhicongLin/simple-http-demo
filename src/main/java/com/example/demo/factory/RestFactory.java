package com.example.demo.factory;

import java.io.InputStream;
import java.util.Map;

import org.simpleframework.http.annotation.PathParam;
import org.simpleframework.http.annotation.RestBody;
import org.simpleframework.http.annotation.RestClient;
import org.simpleframework.http.annotation.RestMapping;
import org.simpleframework.http.annotation.RestMethod;
import org.simpleframework.http.annotation.RestParam;
import org.simpleframework.http.annotation.RestURL;

@RestClient(filter = DemoRestFilter.class, url = "http://localhost:8081")
public interface RestFactory {

    @RestMapping
    String get2(@RestURL String url, RestMethod method, @RestParam("key") String key, @RestParam("key2") Integer[] key2);
    @RestMapping
    String get3(@RestURL String url, @RestParam Map<String, Object> param);
    @RestMapping(url = "/get3", method = RestMethod.POST)
    String get4(@RestParam TestParam param);

    @RestMapping(method = RestMethod.PUT, url = "/put/{size}")
    String put(@PathParam("size") int i);

    @RestMapping(method = RestMethod.PUT, url = "/put/{testParam.key}")
    String put2(@PathParam("testParam.key") TestParam param);

    @RestMapping(method = RestMethod.POST, url = "/post")
    AbcTest post(@RestBody AbcTest abcTest);

    @RestMapping(url = "/upload2", method = RestMethod.POST)
    int fileUpload(@RestParam("file") InputStream inputStream, @RestParam("fileName") String originalFilename);

    @RestMapping(url = "/load2", method = RestMethod.STREAM)
    InputStream getFile();
}
