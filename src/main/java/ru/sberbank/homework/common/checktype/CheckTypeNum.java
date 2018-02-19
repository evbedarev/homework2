package ru.sberbank.homework.common.checktype;

public class CheckTypeNum extends CheckTypeFloat {

    public Double check(String num) {
        return isMaxValue(Long.parseLong(num,10));
    }
}
