package ru.sberbank.homework.common;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckValue {

    public String[] check (String cmd) {

        String[] groups = new String[3];
        String[] derrivedExpr = cmd.split(" ");
        Pattern pattern3 = Pattern.compile("^((?:0[0-7]+)|(?:0b[0-1]+)|(?:0x[0-9a-fA-F]+)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?)) ([*+\\-/]) ((?:0[0-7]+$)|(?:0b[0-1]+$)|(?:0x[0-9a-fA-F]+$)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?$))");




        if (!cmd.matches(".* [*+\\-/] .*")) {
            groups[0] = "error > wrong expression";
            return groups;
        }

        if (!derrivedExpr[0].matches("((?:0[0-7]+)|(?:0b[0-1]+)|(?:0x[0-9a-fA-F]+)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?))")){
            groups[0] = "error > " + derrivedExpr[0];
            return groups;

        }

        if (!derrivedExpr[1].matches("([*+\\-/])")){
            groups[0] = "error > " + derrivedExpr[1];
            return groups;

        }

        if (!derrivedExpr[2].matches("((?:0[0-7]+$)|(?:0b[0-1]+$)|(?:0x[0-9a-fA-F]+$)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?))")){
            groups[0] = "error > " + derrivedExpr[2];
            return groups;

        }

        groups = patternCheck(pattern3, cmd);
        return groups;

    }

    public String[] checkSecondValue (String cmd) {
        String[] groups = new String[3];
        String[] derrivedExpr = cmd.split(" ");
        Pattern pattern = Pattern.compile("^([*+\\-/]) ((?:.)|(?:0[0-7]+)|(?:0b[0-1]+)|(?:0x[0-9a-fA-F]+)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?))$");

        if (!cmd.matches("[*+\\-/] .*")) {
            groups[0] = "error > wrong expression";
            return groups;
        }

        if (!derrivedExpr[1].matches("((?:0[0-7]+$)|(?:0b[0-1]+$)|(?:0x[0-9a-fA-F]+$)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?))")) {
            groups[0] = "error > " + derrivedExpr[1];
            return groups;
        }

        groups = patternCheck(pattern,  cmd);
        if (groups[0].matches("Error.*")) {
            return groups;
        }
        return groups;

    }

    //получает паттерн и колличество групп в паттерне, строку в которой будет производится поиск и сообщение ошибки
    public String[] patternCheck(Pattern pat1,  String cmd) {
        String[] groups = new String[3];
        Matcher m = pat1.matcher(cmd);

        if (m.find()) {
            for (int i=1; i<=m.groupCount(); i++) {
                groups[i-1] = m.group(i);
            }
        }
        return groups;
    }

    public String checkZeroAtTheEnd(String input) {
        if (input.matches("\\d+.0")) {
            return input.substring(0, input.length() - 2);
        } else return input;
    }
}


//        groups = retArr(pattern1, cmd, "error > " + derrivedExpr[0]);
//        if (groups[0].matches("error.*")) {
//            return groups;
//        }
//
//        groups = retArr(pattern2, cmd, "error > " + derrivedExpr[1]);
//        if (groups[0].matches("error.*")) {
//            return groups;
//        }
//
//        groups = retArr(pattern3, cmd, "error > wrong expr at second number");
//        if (groups[0].matches("error.*")) {
//            return groups;
//        }
//
//        return groups;
//        Pattern pattern1 = Pattern.compile("^((?:0[0-7]+)|(?:0b[0-1]+)|(?:0x[0-9a-fA-F]+)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?)) .*");
//        Pattern pattern2 = Pattern.compile("^((?:0[0-7]+)|(?:0b[0-1]+)|(?:0x[0-9a-fA-F]+)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?)) ([*+\\-/]).*");