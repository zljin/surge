package com.zou.corejava.ooad.Strategy;

/**
 * @author leonard
 * @Description
 * @date 2021-09-16 14:51
 */
public interface ReportStrategyHandler {


    void run(String id);


    static ReportStrategyHandler strategyOne(){
        return new ReportStrategyHandler() {
            @Override
            public void run(String id) {
                System.out.println("strategy  "+id+"  finsh");
            }
        };
    }


    static ReportStrategyHandler strategyTwo(){
        return new ReportStrategyHandler() {
            @Override
            public void run(String id) {
                System.out.println("strategy  "+id+"  finsh");
            }
        };
    }

    static ReportStrategyHandler strategyThree(){
        return new ReportStrategyHandler() {
            @Override
            public void run(String id) {
                System.out.println("strategy  "+id+"  finsh");

            }
        };
    }

}
