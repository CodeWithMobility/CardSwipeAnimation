package com.manu.cardswipeanimation;

/**
 * Created by manu on 1/6/2018.
 */

public class CardModel {
    public CardModel(String bgColor, String cardColor) {
        this.bgColor = bgColor;
        this.cardColor = cardColor;
    }

    public String getBgColor() {
        return bgColor;
    }

    public String getCardColor() {
        return cardColor;
    }

    String bgColor;
    String cardColor;
}
