package ru.sberbank.homework.bedarev;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;

public class ValueChecker {

    public List<String> checkBinaryOperation(String cmd) {
        String[] derrivedExpr = cmd.split(" ");
        List<String> valuesOfExpr = new ArrayList<>();
        Pattern pattern = Pattern.
                compile("^(?i)((?:[-+])?(?:(?:0[0-7_]+)|(?:0b[0-1_]+)|(?:0x[0-9a-f_]+)|(?:[\\d_]+\\.?[\\d_]*))(?:[lfd])?) " +
                        "([*+\\-/]) " +
                        "((?:[-+])?(?:(?:0[0-7_]+)|(?:0b[0-1_]+)|(?:0x[0-9a-f_]+)|(?:[\\d_]+\\.?[\\d_]*))(?:[lfd])?)");


        if (!cmd.matches(".* [*+\\-/] .*")) {
            valuesOfExpr.add("error > wrong expression");
            return valuesOfExpr;
        }

        if (!derrivedExpr[0].matches("(?i)" +
                "((?:[-+])?(?:(?:0[0-7_]+)|(?:0b[0-1_]+)|(?:0x[0-9a-f_]+)|(?:[\\d_]+\\.?[\\d_]*))(?:[lfd])?)")){
            valuesOfExpr.add("error > " + derrivedExpr[0]);
            return valuesOfExpr;
        }

        if (!derrivedExpr[1].matches("([*+\\-/])")){
            valuesOfExpr.add("error > " + derrivedExpr[1]);
            return valuesOfExpr;
        }

        if (!derrivedExpr[2].matches("(?i)" +
                "((?:[-+])?(?:(?:0[0-7_]+)|(?:0b[0-1_]+)|(?:0x[0-9a-f_]+)|(?:[\\d_]+\\.?[\\d_]*))(?:[lfd])?)")){
            valuesOfExpr.add("error > " + derrivedExpr[2]);
            return valuesOfExpr;
        }

        valuesOfExpr = patternCheck(pattern, cmd);
        return valuesOfExpr;
    }

    public List<String> checkUnaryOperation(String cmd) {
        List<String> valuesOfExpr = new ArrayList<>();
        String[] derrivedExpr = cmd.split(" ");
        Pattern pattern = Pattern.compile("^(?i)([*+\\-/]) " +
                "((?:[-+])?(?:(?:0[0-7_]+)|(?:0b[0-1_]+)|(?:0x[0-9a-f_]+)|(?:[\\d_]+\\.?[\\d_]*))(?:[lfd])?)$");

        if (!cmd.matches("[*+\\-/] .*")) {
            valuesOfExpr.add("error > wrong expression");
            return valuesOfExpr;
        }

        if (!derrivedExpr[1].matches("(?i)((?:[-+])?(?:(?:0[0-7_]+)|(?:0b[0-1_]+)|(?:0x[0-9a-f_]+)|(?:[\\d_]+\\.?[\\d_]*))(?:[lfd])?)")) {
            valuesOfExpr.add("error > " + derrivedExpr[1]);
            return valuesOfExpr;
        }

        valuesOfExpr = patternCheck(pattern,  cmd);
        return valuesOfExpr;

    }

    //получает паттерн и колличество групп в паттерне, строку в которой будет производится поиск и сообщение ошибки
    public List<String> patternCheck(Pattern pattern,  String cmd) {
        List<String> executedValues = new ArrayList<>();
        String delUnderline;
        Matcher m = pattern.matcher(cmd);

        if (m.find()) {
            for (int i=1; i<=m.groupCount(); i++) {
                delUnderline = m.group(i).toLowerCase().replace("_","");
                executedValues.add(delUnderline);
            }
        }
        return executedValues;
    }

    public String checkZeroAtTheEnd(String input) {
        if (input.matches("[+-]?\\d+.00")) {
            return input.substring(0, input.length() - 3);
        }
        if (input.matches("[+-]?\\d+.\\d+0")) {
            return input.substring(0, input.length() - 1);
        }

        return input;

    }
}