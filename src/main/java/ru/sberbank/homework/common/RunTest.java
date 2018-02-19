package ru.sberbank.homework.common;

public class RunTest extends Run implements Calculator {
    public String calculate (String userInput) {
        for (; ;) {
            if (userInput.equals("")) {break;}
            if (valueStorage.getRunAtFirstTime()) {
                valueStorage.setRunAtFirstTime(false);
                runFirstTime(userInput);
            } else {runSecondTime(userInput);}
        }
        if (!(arr[0].matches("Error.*"))) {
            return arr[0] + arr[1] + arr[2] + "=" + valueStorage.getResult();
        } else {
            return arr[0];
        }
    }

    void runFirstTime(String input) {
        arr = checkValue.check(input);
        numOne = checkType(arr[0]).check(arr[0]);
        numTwo = checkType(arr[2]).check(arr[2]);
        mathOper = arr[1].charAt(0);
        expr(numOne, numTwo, mathOper);
        System.out.println(arr[0] + arr[1] + arr[2] + "=" + valueStorage.getResult());
    }

    void runSecondTime(String input) {

        arr = checkValue.checkSecondValue(input);
        if (arr[0].matches("[*/+\\-]")) {
            if (!(arr[0].matches("Error.*"))) {
                numOne = checkType(arr[1]).check(arr[1]);
                mathOper = arr[0].charAt(0);
                expr(numOne, mathOper);
                System.out.println(valueStorage.getResult() + arr[0] + arr[1] + "=" + valueStorage.getResult());
            } else {
                System.out.println(arr[0]);
            }
        }
        else {runFirstTime(input);}
    }
}
