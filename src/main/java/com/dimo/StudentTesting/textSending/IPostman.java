package com.dimo.StudentTesting.textSending;

import java.util.List;

public interface IPostman {
    void sendText();

    //Creating string ready to been sent by EMail&Telegram
    default String ListToNormalizedString(List<String> answersList) {
        if (answersList == null) {
            throw new NullPointerException();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Dmitriy Pavlichenko, TR-91 | Lab7 \n");
        int counter = 1;
        for (String i: answersList) {
            sb.append(counter++).append(")");
            if (i == null) {
                sb.append("-");
            } else {
                sb.append(i);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
