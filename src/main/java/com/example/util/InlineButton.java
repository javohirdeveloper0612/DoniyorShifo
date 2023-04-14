package com.example.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InlineButton {

    public static InlineKeyboardButton button(String text,String url, String callBackData) {
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setUrl(url);
        inlineKeyboardButton.setText(text);
        inlineKeyboardButton.setCallbackData(callBackData);

        return inlineKeyboardButton;
    }

    public static List<InlineKeyboardButton> row(InlineKeyboardButton... inlineKeyboardButtons) {
        List<InlineKeyboardButton> row = new LinkedList<>();
        row.addAll(Arrays.asList(inlineKeyboardButtons));
        return row;
    }

    public static List<List<InlineKeyboardButton>> rowList(List<InlineKeyboardButton>... row) {
        List<List<InlineKeyboardButton>> rowList = new LinkedList<>();
        rowList.addAll(Arrays.asList(row));

        return rowList;
    }

    public static InlineKeyboardMarkup keyboardMarkup(List<List<InlineKeyboardButton>> rowList) {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.setKeyboard(rowList);
        return keyboard;
    }
}
