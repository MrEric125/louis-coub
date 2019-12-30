package com.source;

import java.util.HashMap;
import java.util.Map;

/**
 * @author John·Louis
 * @date create in 2019/12/21
 * description:
 *
 * 问题点：
 * 1. hashMap中计算hash值的方式
 * 2. 位运算规则
 * 3. hashMap resize()
 * 4. hashMap 懒加载
 * 5. tableSizeFor() 初始化容量
 * 6. treeifyBin() 方法
 *
 */
public class HashMapTest {

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("a", "b");

    }
}
