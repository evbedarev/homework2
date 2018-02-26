package ru.sberbank.homework.common;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckValue {

    public String[] check (String cmd) {

        String[] groups;
        Pattern pattern3 = Pattern.compile("^((?:.)|(?:0[0-7]+)|(?:0b[0-1]+)|(?:0x[0-9a-fA-F]+)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?)) ([*+\\-/]) ((?:0[0-7]+$)|(?:0b[0-1]+$)|(?:0x[0-9a-fA-F]+$)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?$))");
        Pattern pattern1 = Pattern.compile("^((?:.)|(?:0[0-7]+)|(?:0b[0-1]+)|(?:0x[0-9a-fA-F]+)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?)).*");
        Pattern pattern2 = Pattern.compile("^((?:.)|(?:0[0-7]+)|(?:0b[0-1]+)|(?:0x[0-9a-fA-F]+)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?)) ([*+\\-/]).*");


        groups = retArr(pattern1, 2, cmd, "Error, wrong expr at first number");
        if (groups[0].matches("Error.*")) {
            return groups;
        }

        groups = retArr(pattern2, 3, cmd, "Error, wrong expr at mathematical operation");
        if (groups[0].matches("Error.*")) {
            return groups;
        }

        groups = retArr(pattern3, 4, cmd, "Error, wrong expr at second number");
        if (groups[0].matches("Error.*")) {
            return groups;
        }

        return groups;

    }

    public String[] checkSecondValue (String cmd) {
        String[] groups;
        Pattern pattern = Pattern.compile("^ *([*+\\-/]) *((?:.)|(?:0[0-7]+)|(?:0b[0-1]+)|(?:0x[0-9a-fA-F]+)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?))$");

        groups = retArr(pattern, 3, cmd, "error > wrong expression");
        if (groups[0].matches("Error.*")) {
            return groups;
        }
        return groups;

    }

    //получает паттерн и колличество групп в паттерне, строку в которой будет производится поиск и сообщение ошибки
    public String[] retArr(Pattern pat1, int val, String cmd, String msg) {

        String[] groups = new String[4];
        Matcher m = pat1.matcher(cmd);

        if (m.find()) {

            for (int i=1; i<val; i++) {
                groups[i-1] = m.group(i);
            }

        } else {
            groups[0] = msg;

        }
        return groups;

    }

    public String checkZeroAtTheEnd(String input) {

        if (input.matches("\\d+.0")) {
//            System.out.println(input);
            Pattern pattern = Pattern.compile("(\\d+).0");
            Matcher m = pattern.matcher(input);
            if (m.find()) {
                return m.group(1);
            } else {return input;}
        } else {return input;}

    }
}

