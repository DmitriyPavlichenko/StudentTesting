package com.dimo.StudentTesting;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

public class TextProcessing {
    //Creating list with all user answers
    public static List<String> fillListByRequestData(HttpServletRequest request) {
        List<String> list = new LinkedList<>();

        list.add(request.getParameter("answer1"));
        list.add(convertArrayToSingleString(request.getParameterValues("answer2")));
        list.add(convertArrayToSingleString(request.getParameterValues("answer3")));
        if ("".equals(request.getParameter("answer4"))) {
            list.add(null);
        } else {
            list.add(request.getParameter("answer4"));
        }
        list.add(request.getParameter("answer5"));
        list.add(convertArrayToSingleString(request.getParameterValues("answer6")));
        if ("".equals(request.getParameter("answer7"))) {
            list.add(null);
        } else {
            list.add(request.getParameter("answer7"));
        }

        return list;
    }

    public static void printAnswersToConsole(List<String> answers) {
        System.out.println("Answers:");
        answers.forEach(i -> System.out.print(i + ";"));
        System.out.println();
    }

    public static String convertArrayToSingleString(String[] stringArray) {
        if (stringArray == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (String s : stringArray) {
            sb.append(s).append(",");
        }

        return sb.substring(0, sb.length() - 1);
    }
}
