//package ru.sberbank.homework.common;
//
//import ru.sberbank.homework.common.calculate.Calculate;
//import ru.sberbank.homework.common.calculate.CalculateExpr;
//import ru.sberbank.homework.common.checktype.*;
//
//import java.util.Scanner;
//
//public class RunTest implements Calculator{
//    private Double numOne;
//    Double numTwo;
//    private char mathOper;
//    private Calculate calculate;  //Интерфейс вычислений
//    private ValueStorage valueStorage = new ValueStorage(); //Класс хранения результата и вычислений
//    private Scanner in  = new Scanner(System.in);
//    CheckValue checkValue = new CheckValue();
//    private String[] arr;
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
//
//    void runFirstTime(String input) {
//        arr = checkValue.checkFirstExpression(input);
//        if (!(arr[0].matches("error.*"))) {
//            numOne = checkType(arr[0]).check(arr[0]);
//            numTwo = checkType(arr[2]).check(arr[2]);
//            mathOper = arr[1].charAt(0);
//            valueStorage.setResult(expr(mathOper).calc(numOne,numTwo));
//            System.out.println(arr[0] + arr[1] + arr[2] + "=" + checkValue.checkZeroAtTheEnd(valueStorage.getResult().toString()));
//        } else {
//            valueStorage.setErrorInExpression(arr[0]);
//            System.out.println(arr[0]);
//        }
//    }
//
//    void runSecondTime(String input) {
//
//        if (input.matches(" ?[*/+\\-].*")) {
//            arr = checkValue.checkSecondExpression(input);
//            if (!(arr[0].matches("error.*"))) {
//                numOne = checkType(arr[1]).check(arr[1]);
//                mathOper = arr[0].charAt(0);
//                valueStorage.setResult(expr(mathOper).calc(numOne,valueStorage.getResult()));
//                System.out.println(valueStorage.getResult() + arr[0] + arr[1] + "=" + checkValue.checkZeroAtTheEnd(valueStorage.getResult().toString()));
//            } else {
//                valueStorage.setErrorInExpression(arr[0]);
//                System.out.println(arr[0]);
//            }
//        }
//        else {runFirstTime(input);}
//    }
//
//    //ENUM
//    CalculateExpr.Operation expr (char mathOper) {
//        for (CalculateExpr.Operation operation:CalculateExpr.Operation.values()) {
//            if (operation.symbol == mathOper) {
//                return operation;
//            }
//        }
//
//        return CalculateExpr.Operation.PLUS;
//    }
//
//
//
//
//    static VerifyType checkType(String num) {
//        if (num.matches("\\d+\\.?\\d*[fF]$")) { return new CheckTypeFloat();}
//        else if (num.matches("^0[0-7]+$")) {return new CheckTypeOcta();}
//        else if (num.matches("^0b[0-1]+$")) {return new CheckTypeBin();}
//        else if (num.matches("^0x[0-9a-fA-F]+$")) {return new CheckTypeHex();}
//        else if (num.matches("^.*[lL]$")) {return new CheckTypeLong();}
//        else if (num.matches("^[^0-9]$")) {return new CheckTypeChar();}
//        else {return new CheckTypeNum();}
//    }
//
//
//}
