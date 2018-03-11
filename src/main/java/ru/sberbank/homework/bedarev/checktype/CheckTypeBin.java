package ru.sberbank.homework.bedarev.checktype;

import ru.sberbank.homework.bedarev.ValueChecker;

import java.util.regex.Pattern;

public class CheckTypeBin extends CheckTypeFloat {
    ValueChecker valueChecker = new ValueChecker();

    public CheckTypeBin(String stringNum) {
        super(stringNum);
    }

    public Double check() {
        stringNum = valueChecker.patternCheck(Pattern.compile("0b([01]+)"), stringNum).get(0);
        return isMaxValue(Long.parseLong(stringNum,2));
    }
}
