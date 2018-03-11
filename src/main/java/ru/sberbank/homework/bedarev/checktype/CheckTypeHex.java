package ru.sberbank.homework.bedarev.checktype;

import java.util.regex.Pattern;

public class CheckTypeHex extends CheckTypeBin {

    public CheckTypeHex(String stringNum) {
        super(stringNum);
    }

    public Double check() {
        stringNum = valueChecker.patternCheck(Pattern.compile("0x([0-9a-fA-F]+)"), stringNum).get(0);
        System.out.println(stringNum);
        return isMaxValue(Long.parseLong(stringNum,16));
    }
}