package com.zou.dataobject;

/**
 * @author leonard
 * @date 2022/6/15
 * @Description single test
 */
public class MainClass {
    public static void main(String[] args) {
        IDataset<String> strList = new LinkedDataset<>();
        strList.addFirst("leonard");
        strList.addFirst("kobe");
        strList.addFirst("james");
        strList.addFirst("brown");
        strList.addLast("curry");
        strList.add(3,"kevin");
        System.out.println(strList.toString());
    }
}
