package com.example.demo.factory;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author linzc
 * @version 1.0
 *
 * <pre>
 * 修改记录:
 * 修改后版本        修改人     修改日期        修改内容
 * 2020/6/29.1    linzc       2020/6/29     Create
 * </pre>
 * @date 2020/6/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbcTest implements Serializable {

    private Integer id;
}