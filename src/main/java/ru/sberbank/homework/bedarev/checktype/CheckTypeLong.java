package ru.sberbank.homework.bedarev.checktype;

public class CheckTypeLong extends CheckTypeFloat {

    public CheckTypeLong(String stringNum) {
        super(stringNum);
    }

    public Double check() {
        stringNum = stringNum.substring(0, stringNum.length() - 1);

        if (stringNum.matches("[+-]?0b[0-1]+")) {
            return isMaxValue(Long.parseLong(stringNum.replace("0b",""), 2));
        }

        if (stringNum.matches("[+-]?0[0-7]+")) {
            return isMaxValue(Long.parseLong(stringNum, 8));
        }

        if (stringNum.matches("[+-]?0x[0-9a-f]+")) {
            return isMaxValue(Long.parseLong(stringNum.replace("0x",""), 16));
        }

        return isMaxValue(Long.parseLong(stringNum,10));
    }
}
