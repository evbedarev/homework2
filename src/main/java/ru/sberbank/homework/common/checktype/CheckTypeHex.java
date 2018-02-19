package ru.sberbank.homework.common.checktype;

import java.util.regex.Pattern;

public class CheckTypeHex extends CheckTypeBin {

    public Double check(String num) {
        stringNum = checkValue.retArr(Pattern.compile("0x([0-9a-fA-F]+)"),2, num, "Error")[0];
        System.out.println(stringNum);
        return isMaxValue(Long.parseLong(stringNum,16));
    }
}
