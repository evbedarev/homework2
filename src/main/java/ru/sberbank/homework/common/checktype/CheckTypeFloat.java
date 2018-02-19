package ru.sberbank.homework.common.checktype;

public class CheckTypeFloat implements VerifyType {
    String stringNum;

    public Double check(String num) {
        return isMaxValue(Float.valueOf(num).longValue());
    }

    static Double isMaxValue(Long num) {
        if (num < Double.MAX_VALUE) {
            return num.doubleValue();
        } else {
            return Double.MAX_VALUE; //Must be exception
        }

    }
}
