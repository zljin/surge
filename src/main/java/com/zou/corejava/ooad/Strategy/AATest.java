package com.zou.corejava.ooad.Strategy;

import java.util.Arrays;
import java.util.List;

/**
 * @author leonard
 * @Description
 * @date 2021-09-16 15:11
 */
public class AATest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("one", "two", "three");
        for (int i = 0; i < list.size(); i++) {
            ReportStrategyEnum
                    .getInstance(list.get(i))
                    .getHandler()
                    .run(list.get(i));
        }
    }
}
