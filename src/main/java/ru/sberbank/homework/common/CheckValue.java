package ru.sberbank.homework.common;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;

public class CheckValue {

    public List<String> checkFirstExpression(String cmd) {
        String[] derrivedExpr = cmd.split(" ");
        List<String> valuesOfExpr = new ArrayList<>();
        Pattern pattern3 = Pattern.
                compile("^((?:0[0-7]+)|(?:0b[0-1]+)|(?:0x[0-9a-fA-F]+)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?)) ([*+\\-/]) ((?:0[0-7]+$)|(?:0b[0-1]+$)|(?:0x[0-9a-fA-F]+$)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?$))");


        if (!cmd.matches(".* [*+\\-/] .*")) {
            valuesOfExpr.add("error > wrong expression");
            return valuesOfExpr;
        }

        if (!derrivedExpr[0].matches("((?:0[0-7]+)|(?:0b[0-1]+)|(?:0x[0-9a-fA-F]+)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?))")){
            valuesOfExpr.add("error > " + derrivedExpr[0]);
            return valuesOfExpr;
        }

        if (!derrivedExpr[1].matches("([*+\\-/])")){
            valuesOfExpr.add("error > " + derrivedExpr[1]);
            return valuesOfExpr;
        }

        if (!derrivedExpr[2].matches("((?:0[0-7]+$)|(?:0b[0-1]+$)|(?:0x[0-9a-fA-F]+$)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?))")){
            valuesOfExpr.add("error > " + derrivedExpr[1]);
            return valuesOfExpr;
        }

        valuesOfExpr = patternCheck(pattern3, cmd);
        return valuesOfExpr;
    }

    public List<String> checkSecondExpression(String cmd) {
        List<String> valuesOfExpr = new ArrayList<>();
        String[] derrivedExpr = cmd.split(" ");
        Pattern pattern = Pattern.compile("^([*+\\-/]) ((?:.)|(?:0[0-7]+)|(?:0b[0-1]+)|(?:0x[0-9a-fA-F]+)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?))$");

        if (!cmd.matches("[*+\\-/] .*")) {
            valuesOfExpr.add("error > wrong expression");
            return valuesOfExpr;
        }

        if (!derrivedExpr[1].matches("((?:0[0-7]+$)|(?:0b[0-1]+$)|(?:0x[0-9a-fA-F]+$)|(?:(?:[-+])?\\d+\\.?\\d*(?:[lLfF])?))")) {
            valuesOfExpr.add("error > " + derrivedExpr[1]);
            return valuesOfExpr;
        }

        valuesOfExpr = patternCheck(pattern,  cmd);
        return valuesOfExpr;

    }

    //получает паттерн и колличество групп в паттерне, строку в которой будет производится поиск и сообщение ошибки
    public List<String> patternCheck(Pattern pattern,  String cmd) {
        List<String> executedValues = new ArrayList<>();
        Matcher m = pattern.matcher(cmd);

        if (m.find()) {
            for (int i=1; i<=m.groupCount(); i++) {
                executedValues.add(m.group(i));
            }
        }
        return executedValues;
    }

    public String checkZeroAtTheEnd(String input) {
        if (input.matches("\\d+.0")) {
            return input.substring(0, input.length() - 2);
        } else return input;
    }
}