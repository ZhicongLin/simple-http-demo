package com.example.demo.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Description:
 *
 * @author linzc
 * @version 1.0
 *
 * <pre>
 * 修改记录:
 * 修改后版本        修改人     修改日期        修改内容
 * 2020/7/2.1    linzc       2020/7/2     Create
 * </pre>
 * @date 2020/7/2
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestParam {
    private String key;
    private String key2;
    private TestParam testParam;

    public TestParam(String key, String key2) {
        this.key = key;
        this.key2 = key2;
    }
}