package com.ragu.spar;

import java.util.List;

/**
 * Created by nana on 10/9/14.
 */
public class Player {

    private String username;
    List<Card> cards;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public List<Card> getCards()
    {
        return this.cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Player(){}

    public Player(String username)
    {
        this.username = username;
    }

    public void playCard(Card card)
    {
        cards.remove(card);

    }


}
