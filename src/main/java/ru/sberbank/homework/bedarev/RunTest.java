package ru.sberbank.homework.bedarev;
import ru.sberbank.homework.bedarev.checktype.*;

import java.util.ArrayList;
import java.util.List;

public class RunTest implements Calculator {
    private Double firstOperand;
    private char mathOper;
    private ValueStorage valueStorage = new ValueStorage(); //Класс хранения результата и вычислений
    private ValueChecker valueChecker = new ValueChecker();
    private List<String> valuesOfExpr = new ArrayList<>();
    private String fstElement, secElement, thirdElement;

    public String calculate (String userInput) {
        valueStorage.setErrorInExpression(null);
        valuesOfExpr.clear();
        if (valueStorage.getRunAtFirstTime()) {
            valueStorage.setRunAtFirstTime(false);
            runFirstTime(userInput);
        } else {
            runSecondTime(userInput);
        }

        if (valueStorage.getErrorInExpression()==null) {
            return valueChecker.checkZeroAtTheEnd(valueStorage.getResult().toString());
        } else {
            return valueStorage.getErrorInExpression();
        }
    }

    private void runFirstTime(String input) {
        valuesOfExpr = valueChecker.checkBinaryOperation(input);
        assignValues();

        if (!(fstElement.matches("error.*"))) {
            firstOperand = checkType(fstElement).check();
            Double secOperand = checkType(thirdElement).check();
            mathOper = secElement.charAt(0);

            valueStorage.setResult(expr(mathOper).calc(firstOperand,secOperand));
            System.out.println(fstElement + secElement +
                    thirdElement + "=" + valueStorage.getResult());
        } else {
            System.out.println(fstElement);
            valueStorage.setErrorInExpression(fstElement);
        }
    }

    private void runSecondTime(String input) {
        Double oldValueStorage;

        if (!input.matches("[*/+\\-] .*")) {
            runFirstTime(input);
        }

        if (input.matches("[*/+\\-] .*")) {
            valuesOfExpr = valueChecker.checkUnaryOperation(input);
            assignValues();
        }

        if ((!fstElement.matches("error.*")) && (input.matches(" ?[*/+\\-] .*"))) {
            firstOperand = checkType(secElement).check();
            mathOper = fstElement.charAt(0);
            oldValueStorage = valueStorage.getResult();
            valueStorage.setResult(expr(mathOper).calc(valueStorage.getResult(), firstOperand));
            System.out.println(oldValueStorage + fstElement +
                    secElement + "=" + valueStorage.getResult());
        }

        if ((fstElement.matches("error.*")) && (input.matches(" ?[*/+\\-] .*"))) {
            System.out.println(fstElement);
            valueStorage.setErrorInExpression(fstElement);

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
        return new CheckTypeNum(num);
    }

}



//
//    public String calculate (String userInput) {
//            valueStorage.setErrorInExpression(null);
//            if (valueStorage.getRunAtFirstTime()) {
//                valueStorage.setRunAtFirstTime(false);
//                runFirstTime(userInput);
//            } else {runSecondTime(userInput);}
//
//            if (valueStorage.getErrorInExpression()==null) {
//                return checkValue.checkZeroAtTheEnd(valueStorage.getResult().toString());
//            } else {
//                return valueStorage.getErrorInExpression();
//            }
//    }
