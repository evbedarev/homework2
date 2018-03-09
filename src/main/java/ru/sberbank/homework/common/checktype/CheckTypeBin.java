package ru.sberbank.homework.common.checktype;

import ru.sberbank.homework.common.CheckValue;

import java.util.regex.Pattern;

public class CheckTypeBin extends CheckTypeFloat {
    CheckValue checkValue = new CheckValue();

    public CheckTypeBin(String stringNum) {
        super(stringNum);
    }

    public Double check() {
        stringNum = checkValue.patternCheck(Pattern.compile("0b([01]+)"), stringNum).get(0);
        return isMaxValue(Long.parseLong(stringNum,2));
    }
}
