package com.zou.corejava.ooad.Strategy;

import lombok.Getter;

/**
 * @author leonard
 * @Description
 * @date 2021-09-16 14:52
 */
public enum ReportStrategyEnum {

    ONE_STRATEGY_RUN("one",ReportStrategyHandler.strategyOne()),
    TWO_STRATEGY_RUN("two",ReportStrategyHandler.strategyTwo()),
    THREE_STRATEGY_RUN("three",ReportStrategyHandler.strategyThree()),
    ;

    @Getter
    private String code;

    @Getter
    private ReportStrategyHandler handler;

    ReportStrategyEnum(String code, ReportStrategyHandler handler) {
        this.code = code;
        this.handler = handler;
    }

    public static ReportStrategyEnum getInstance(String code) {
        ReportStrategyEnum[] enums = values();
        for (ReportStrategyEnum e : enums) {
            if (code.equals(e.getCode())) {
                return e;
            }
        }
        return null;
    }
}
