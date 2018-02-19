package ru.sberbank.homework.common.checktype;

import java.util.regex.Pattern;

public class CheckTypeOcta extends CheckTypeBin {

    public Double check(String num) {
        stringNum = checkValue.retArr(Pattern.compile("0([0-7]+)"),2, num, "Error")[0];
        return isMaxValue(Long.parseLong(stringNum,8));

    }
}