package com.ragu.resource;

import java.util.List;

/**
 * Created by nana on 10/9/14.
 */
public class Player {

    String username;
    private List<Card> cards;
    boolean isTurn;
    boolean hasCards;

    public void playCard(Card card)
    {
        cards.remove(card);

    }

    public List<Card> getCards()
    {
        return this.cards;
    }

}
