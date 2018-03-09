package ru.sberbank.homework.common.checktype;

import java.util.regex.Pattern;

public class CheckTypeOcta extends CheckTypeBin {

    public CheckTypeOcta(String stringNum) {
        super(stringNum);
    }

    public Double check() {
        stringNum = checkValue.patternCheck(Pattern.compile("0([0-7]+)"), stringNum).get(0);
        return isMaxValue(Long.parseLong(stringNum,8));

    }
}