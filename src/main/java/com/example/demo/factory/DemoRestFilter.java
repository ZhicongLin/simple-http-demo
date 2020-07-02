package com.example.demo.factory;

import java.util.Map;
import java.util.UUID;

import org.simpleframework.http.io.AbstractRestFilter;
import org.simpleframework.http.proxy.RestObject;

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
public class DemoRestFilter extends AbstractRestFilter {
    @Override
    public void before(RestObject restObject) {
        restObject.getParams().put("key3", "阿布都");
        restObject.getHttpHeaders().put("Access-Token", UUID.randomUUID().toString());
    }
}