package com.dimo.StudentTesting.textSending;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.List;

public class TelegramPM extends TelegramLongPollingBot implements IPostman, Runnable {
    private final long chatId;
    private final String botName;
    private final String botToken;
    private final List<String> answersList;

    public TelegramPM(long chatID, String botName, String botToken, List<String> answersList) {
        this.chatId = chatID;
        this.botName = botName;
        this.botToken = botToken;
        this.answersList = answersList;
    }

    @Override
    public void run() {
        sendText();
    }

    public void sendText() {
        SendMessage message = new SendMessage();

        message.enableMarkdown(true);
        message.setChatId(chatId);
        message.setText(ListToNormalizedString(answersList));
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        System.out.println("Answers was successfully send to Telegram");
    }

    @Override
    public void onUpdateReceived(Update update) {
        String input = update.getMessage().getText();
        SendMessage output = new SendMessage();

        //Some random functional
        if (input.equals("/chatid")) {
            Long chatId = update.getMessage().getChatId();
            System.out.println("chatId = " + chatId);
            output.setText("chatid is  = " + chatId);
        }

        output.setChatId(update.getMessage().getChatId());
        try {
            execute(output);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return botName;
    }

    public String getBotToken() {
        return botToken;
    }
}