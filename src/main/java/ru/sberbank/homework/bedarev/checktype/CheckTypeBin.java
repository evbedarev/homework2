package ru.sberbank.homework.bedarev.checktype;

import ru.sberbank.homework.bedarev.ValueChecker;

import java.util.regex.Pattern;

public class CheckTypeBin extends CheckTypeFloat {
    ValueChecker valueChecker = new ValueChecker();

    public CheckTypeBin(String stringNum) {
        super(stringNum);
    }

    public Double check() {
        String replace0b = stringNum.replace("0b","");
        return isMaxValue(Long.parseLong(replace0b ,2));
    }
}
