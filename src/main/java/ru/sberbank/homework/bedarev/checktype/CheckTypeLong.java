package ru.sberbank.homework.bedarev.checktype;

public class CheckTypeLong extends CheckTypeFloat {

    public CheckTypeLong(String stringNum) {
        super(stringNum);
    }

    public Double check() {
        return isMaxValue(Long.parseLong(stringNum.substring(0, stringNum.length() - 1))); //Удаляем в конце lL
    }
}
