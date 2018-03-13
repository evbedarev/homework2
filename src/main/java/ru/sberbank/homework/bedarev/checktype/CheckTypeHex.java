package ru.sberbank.homework.bedarev.checktype;

import java.util.regex.Pattern;

public class CheckTypeHex extends CheckTypeBin {

    public CheckTypeHex(String stringNum) {
        super(stringNum);
    }

    public Double check() {
        String replaceOx = stringNum.replace("0x","");
        return isMaxValue(Long.parseLong(replaceOx,16));
    }
}
