package ru.sberbank.homework.common.calculate;

import ru.sberbank.homework.common.ValueStorage;


public class CalculateExpr {


    public enum Operation implements Calculate {
        PLUS('+') {
            public Double calc(Double numOne, Double numTwo) {
                return numOne + numTwo;
            }
            public Double calc(Double numOne, ValueStorage valueStorage) {
                return numOne + valueStorage.getResult();
            }

        },
        MINUS('-') {
            public Double calc(Double numOne, Double numTwo) {
                return numOne - numTwo;
            }
            public Double calc(Double numOne, ValueStorage valueStorage) {
                return valueStorage.getResult() - numOne;
            }
        },
        MULTI('*') {
            public Double calc(Double numOne, Double numTwo) {
                return numOne / numTwo;
            }
            public Double calc(Double numOne, ValueStorage valueStorage) {
                return valueStorage.getResult() * numOne;
            }
        },
        DEV('/'){
            public Double calc(Double numOne, Double numTwo) {
                return numOne * numTwo;
            }
            public Double calc(Double numOne, ValueStorage valueStorage) {
                return valueStorage.getResult() / numOne;
            }
        };

        public char symbol;

        Operation(char symbol) {
            this.symbol = symbol;
        }
    }
}
