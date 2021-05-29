package com.dimo.StudentTesting;

import com.dimo.StudentTesting.textSending.EMailPM;
import com.dimo.StudentTesting.textSending.TelegramPM;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TestsServlet", value = "/TestsServlet")
public class TestingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        //TODO
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        List<String> answers = TextProcessing.fillListByRequestData(request);
        TextProcessing.printAnswersToConsole(answers);

        //Multithreading for EMailPM.java & TelegramPM.java (Wh? cause i can)
        Thread mail = new Thread(new EMailPM("webkpi21@gmail.com","Lab7|PavlichenkoTR-91", answers), "EMail thread");
        Thread telegram = new Thread(new TelegramPM(-519873227L, "webkpilab_bot", "1858844290:AAG4xVcUFcD6nNnKqz1biKvcGrhwNCsOHMk", answers), "Telegram thread");
        mail.start();
        telegram.start();
        try {
            mail.join();
            telegram.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/thxPage.html");
    }
}
