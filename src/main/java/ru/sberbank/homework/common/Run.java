package ru.sberbank.homework.common;
import ru.sberbank.homework.common.calculate.*;
import ru.sberbank.homework.common.checktype.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Run {
    private Double numOne;
    private Double numTwo;
    private char mathOper;
    private ValueStorage valueStorage = new ValueStorage(); //Класс хранения результата и вычислений
    private Scanner in  = new Scanner(System.in);
    private CheckValue checkValue = new CheckValue();
    private List<String> valuesOfExpr = new ArrayList<>();

    void run () {
        String input;

        for (; ;) {
            input = in.nextLine();
            if (input.equals("")) {
                break;
            }
            if (valueStorage.getRunAtFirstTime()) {
                valueStorage.setRunAtFirstTime(false);
                runFirstTime(input);
            } else {
                runSecondTime(input);
            }
        }
    }

    void runFirstTime(String input) {
        valuesOfExpr = checkValue.checkFirstExpression(input);
        if (!(valuesOfExpr.get(0).matches("error.*"))) {
            numOne = checkType(valuesOfExpr.get(0)).check();
            numTwo = checkType(valuesOfExpr.get(2)).check();
            mathOper = valuesOfExpr.get(1).charAt(0);

            valueStorage.setResult(expr(mathOper).calc(numOne,numTwo));
            System.out.println(valuesOfExpr.get(0) + valuesOfExpr.get(1) +
                    valuesOfExpr.get(2) + "=" + valueStorage.getResult());
        } else {
            System.out.println(valuesOfExpr.get(0));
        }
    }

    void runSecondTime(String input) {
        Double oldValueStorage;

        if (input.matches(" ?[*/+\\-].*")) {
            valuesOfExpr = checkValue.checkSecondExpression(input);
            if (!(valuesOfExpr.get(0).matches("error.*"))) {
                numOne = checkType(valuesOfExpr.get(1)).check();
                mathOper = valuesOfExpr.get(0).charAt(0);
                oldValueStorage = valueStorage.getResult();
                valueStorage.setResult(expr(mathOper).calc(numOne,valueStorage.getResult()));
                System.out.println(oldValueStorage + valuesOfExpr.get(0) +
                        valuesOfExpr.get(1) + "=" + valueStorage.getResult());
            } else {
                System.out.println(valuesOfExpr.get(0));
            }
        }
        else {
            runFirstTime(input);
        }
    }

//ENUM
    CalculateExpr.Operation expr (char mathOper) {
        for (CalculateExpr.Operation operation:CalculateExpr.Operation.values()) {
            if (operation.symbol == mathOper) {
                return operation;
            }
        }
        return CalculateExpr.Operation.PLUS;
    }


    static VerifyType checkType(String num) {
        if (num.matches("\\d+\\.?\\d*[fF]$")) {
            return new CheckTypeFloat(num);
        }
        if (num.matches("^0[0-7]+$")) {
            return new CheckTypeOcta(num);
        }
        if (num.matches("^0b[0-1]+$")) {
            return new CheckTypeBin(num);
        }
        if (num.matches("^0x[0-9a-fA-F]+$")) {
            return new CheckTypeHex(num);
        }
        if (num.matches("^.*[lL]$")) {
            return new CheckTypeLong(num);
        }
//        if (num.matches("^[^0-9]$")) {
//            return new CheckTypeChar(num);
//        }
        return new CheckTypeNum(num);
    }

}