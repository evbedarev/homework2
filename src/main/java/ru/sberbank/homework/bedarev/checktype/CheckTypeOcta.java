package ru.sberbank.homework.bedarev.checktype;

import java.util.regex.Pattern;

public class CheckTypeOcta extends CheckTypeBin {

    public CheckTypeOcta(String stringNum) {
        super(stringNum);
    }

    public Double check() {
        return isMaxValue(Long.parseLong(stringNum,8));
    }
}