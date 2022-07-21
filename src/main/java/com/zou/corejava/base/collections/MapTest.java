package com.zou.corejava.base.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * Map 双列集合接口
 */
public class MapTest {

    private static Map<String,String> map = new HashMap<>();

    static {
        map.put("code","huawei");
        map.put("price","9899");
        map.put("id","100909");
    }

    public static void main(String[] args) {
        mapTest();
    }



    public static void mapTest(){
        for (String key : map.keySet()) {
            System.out.println("key= "+ key + " and value= " + map.get(key));
        }

        /**
         * Map.Entry可以一次性获得这两个值,比第一种方法快一倍,因为第一种要循环请求
         */
        for(Map.Entry<String,String> entry : map.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

        map.forEach((k,v)->{
            System.out.println(k+":"+v);
        });
    }
}
