package com.dimo.StudentTesting.textSending;

import java.util.List;

public interface IPostman {
    void sendText();

    //Creating string ready to been sent by EMail&Telegram
    default String ListToNormalizedString(List<String> answersList) {
        final String[] rightAnswers = {"A", "A,B", "A,C", "Rog", "A", "B,C", "Hello"};

        if (answersList == null) {
            throw new NullPointerException();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Dmitriy Pavlichenko, TR-91 | Lab7 \n");
        int arrayIterator = 0;
        int rowCounter = 1;
        int correctCounter = 0;
        for (String i: answersList) {
            sb.append(rowCounter++).append(")");
            if (i == null) {
                sb.append("<skipped> - wrong");
            } else if (rightAnswers[arrayIterator++].equals(i)) {
                sb.append(i).append(" - correct");
                correctCounter++;
            } else {
                sb.append(i).append(" - wrong, correct variant: ").append(rightAnswers[arrayIterator - 1]);
            }
            sb.append("\n");
        }
        sb.append("Total number of correct answers: ").append(correctCounter).append("\n");
        return sb.toString();
    }
}
