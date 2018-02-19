package ru.sberbank.homework.checker;

import ru.sberbank.homework.common.CheckValue;
import ru.sberbank.homework.common.ValueStorage;
import ru.sberbank.homework.common.calculate.Calculate;
import ru.sberbank.homework.common.calculate.CalculateExpr;
import ru.sberbank.homework.common.checktype.*;

import java.util.Scanner;

public class Run {
    private Double numOne;
    Double numTwo;
    private char mathOper;
    private Calculate calculate;  //Интерфейс вычислений
    private ValueStorage valueStorage = new ValueStorage(); //Класс хранения результата и вычислений
    private Scanner in  = new Scanner(System.in);
    CheckValue checkValue = new CheckValue();
    private String[] arr;

    void run () {
        String input;

        for (; ;) {
            input = in.nextLine();
            if (input.equals("")) {break;}
            if (valueStorage.getRunAtFirstTime()) {
                valueStorage.setRunAtFirstTime(false);
                runFirstTime(input);
            } else {runSecondTime(input);}
        }
    }

    void runFirstTime(String input) {
        arr = checkValue.check(input);
        if (!(arr[0].matches("Error.*"))) {
            numOne = checkType(arr[0]).check(arr[0]);
            numTwo = checkType(arr[2]).check(arr[2]);
            mathOper = arr[1].charAt(0);
            valueStorage.setResult(expr(mathOper).calc(numOne,numTwo));
            System.out.println(arr[0] + arr[1] + arr[2] + "=" + valueStorage.getResult());
        } else {
            System.out.println(arr[0]);
        }
    }

    void runSecondTime(String input) {

        arr = checkValue.checkSecondValue(input);
        if (arr[0].matches("[*/+\\-]")) {
            if (!(arr[0].matches("Error.*"))) {
                numOne = checkType(arr[1]).check(arr[1]);
                mathOper = arr[0].charAt(0);
                valueStorage.setResult(expr(mathOper).calc(numOne,valueStorage));
                System.out.println(valueStorage.getResult() + arr[0] + arr[1] + "=" + valueStorage.getResult());
            } else {
                System.out.println(arr[0]);
            }
        }
        else {runFirstTime(input);}
    }


    CalculateExpr.Operation expr (char mathOper) {
        if (mathOper=='+') return CalculateExpr.Operation.PLUS;
        else if (mathOper=='-') return CalculateExpr.Operation.MINUS;
        else if (mathOper=='*') return CalculateExpr.Operation.MULTI;
        else return CalculateExpr.Operation.DEV;
    }


    static VerifyType checkType(String num) {
        if (num.matches("\\d+\\.?\\d*[fF]$")) { return new CheckTypeFloat();}
        else if (num.matches("^0[0-7]+$")) {return new CheckTypeOcta();}
        else if (num.matches("^0b[0-1]+$")) {return new CheckTypeBin();}
        else if (num.matches("^0x[0-9a-fA-F]+$")) {return new CheckTypeHex();}
        else if (num.matches("^.*[lL]$")) {return new CheckTypeLong();}
        else if (num.matches("^[^0-9]$")) {return new CheckTypeChar();}
        else {return new CheckTypeNum();}
    }





}