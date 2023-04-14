package com.example.controller;

import com.example.util.InlineButton;
import com.example.util.SendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBotController extends TelegramLongPollingBot {



    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMyChatMember()) {
            ChatMember newChatMember = update.getMyChatMember().getNewChatMember();
            String status = newChatMember.getStatus();
            Long id = update.getMyChatMember().getFrom().getId();

            if (status.equals("kicked")) {
                send(SendMsg.sendMsg(1024661500L,"Mazgi Botni block qildi"));
            } else if (status.equals("member")) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setText("Botni qayta ishga tushirganingizdan xursandmiz");
                sendMessage.setChatId(id);
                send(sendMessage);
            }
            return;
        }

        if (update.hasMessage()){
            Message message = update.getMessage();
                message.setReplyMarkup(InlineButton.keyboardMarkup(InlineButton.rowList(
                        InlineButton.row(InlineButton.button("Join Bot \uD83E\uDD16",
                                "https://t.me/doniyor_shifo_bot",
                                "susbcribe"))
                )));

                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(message.getChatId());
                sendMessage.setReplyMarkup(message.getReplyMarkup());
                sendMessage.setText("Assalomu Aleykum Doniyor shifo klinikasiga xush kelibsiz bizning asosiy botimizga utib oling !");

                send(sendMessage);

            }
    }
    public void send(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String getBotUsername() {
        return "@javaDeveloper_bot";
    }

    @Override
    public String getBotToken() {
        return "5695719156:AAED5gaeDZ12NOLHoXjb6Xy7qZ5CBOPkUBI";    }
}
