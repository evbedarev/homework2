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
    private String fstElement, secElement, thirdElement;

    void run () {
        String input;

        for (; ;) {
            input = in.nextLine();
            if (input.equals("")) {
                break;
            }
            if (valueStorage.getRunAtFirstTime()) {
                runFirstTime(input);
                valueStorage.setRunAtFirstTime(false);
            } else {
                runSecondTime(input);
            }
        }
    }

    private void assignValues() {
        fstElement = null;
        secElement = null;
        thirdElement = null;

        fstElement = valuesOfExpr.get(0);

        if (valuesOfExpr.size() > 1) {
            secElement = valuesOfExpr.get(1);
        }

        if (valuesOfExpr.size() > 2) {
            thirdElement = valuesOfExpr.get(2);
        }
    }

    private void runFirstTime(String input) {
        valuesOfExpr = checkValue.checkFirstExpression(input);
        assignValues();
        if (!(fstElement.matches("error.*"))) {
            numOne = checkType(fstElement).check();
            numTwo = checkType(thirdElement).check();
            mathOper = secElement.charAt(0);

            valueStorage.setResult(expr(mathOper).calc(numOne,numTwo));
            System.out.println(fstElement + secElement +
                    thirdElement + "=" + valueStorage.getResult());
        } else {
            System.out.println(fstElement);
        }
    }

    private void runSecondTime(String input) {
        Double oldValueStorage;

        if (input.matches(" ?[*/+\\-].*")) {
            valuesOfExpr = checkValue.checkSecondExpression(input);
            assignValues();
        }

        if ((fstElement != null) && (!fstElement.matches("error.*"))) {
            numOne = checkType(secElement).check();
            mathOper = fstElement.charAt(0);
            oldValueStorage = valueStorage.getResult();
            valueStorage.setResult(expr(mathOper).calc(valueStorage.getResult(),numOne));
            System.out.println(oldValueStorage + fstElement +
            secElement + "=" + valueStorage.getResult());
        }

        if ((fstElement.matches("error.*"))) {
            System.out.println(valuesOfExpr.get(0));
        }

        if (!input.matches(" ?[*/+\\-].*")) {
            runFirstTime(input);
        }
    }

//ENUM
    private CalculateExpr.Operation expr (char mathOper) {
        for (CalculateExpr.Operation operation:CalculateExpr.Operation.values()) {
            if (operation.symbol == mathOper) {
                return operation;
            }
        }
        return CalculateExpr.Operation.PLUS;
    }


    private static VerifyType checkType(String num) {
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