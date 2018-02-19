package ru.sberbank.homework.common.checktype;

public class CheckTypeLong extends CheckTypeFloat {
    public Double check(String num) {
        return isMaxValue(Long.parseLong(num.substring(0, num.length() - 1))); //Удаляем в конце lL
    }
}
