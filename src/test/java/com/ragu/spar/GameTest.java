package com.ragu.spar;

import com.ragu.spar.exceptions.SparException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by nana on 1/7/15.
 */
public class GameTest {
    public static final String PLAYER = "player1";
    Game game;
    Player player1;

    @Before
    public void setup() throws Exception {
        player1 = new Player(PLAYER);
        game = new Game(player1);
    }

    @Test
    public void testAddPlayer() throws SparException {
        Player player2 = new Player("player2");
        game.addPlayerToGame(player2);
        assertEquals(2,game.getPlayers().size());
        assertEquals(PLAYER,game.getPlayers().get(0).getUsername());
        assertEquals("player2",game.getPlayers().get(1).getUsername());
    }

    @Test
    public void testStartGame() throws SparException {
        Player player2 = new Player("player2");
        game.addPlayerToGame(player2);
        game.startGame();
        assertTrue(game.isGameStarted);
    }

    @Test(expected = SparException.class)
    public void throwsSparExceptionWhenPlayerHasLeadCard() throws SparException, IOException {
        Player player2 = new Player("player2");
        game.addPlayerToGame(player2);
        game.startGame();
        player1.cards.clear();
        player2.cards.clear();
        List<Card> player1Cards = new ArrayList<>();
        player1Cards.add(new Card(CardValue.six, Suit.CLUBS));
        player1Cards.add(new Card(CardValue.ten, Suit.DIAMONDS));
        List<Card> player2Cards = new ArrayList<>();
        player2Cards.add(new Card(CardValue.seven, Suit.CLUBS));
        player2Cards.add(new Card(CardValue.nine, Suit.DIAMONDS));
        player1.setCards(player1Cards);
        player2.setCards(player2Cards);
        game.playCard(player1,player1.cards.get(0));
        game.playCard(player2, player2.cards.get(1));
    }

    @Test
    public void allowIfPlayerDoesNotHaveSameSuit() throws SparException, IOException {
        Player player2 = new Player("player2");
        game.addPlayerToGame(player2);
        game.startGame();
        player1.cards.clear();
        player2.cards.clear();
        List<Card> player1Cards = new ArrayList<>();
        player1Cards.add(new Card(CardValue.six, Suit.CLUBS));
        List<Card> player2Cards = new ArrayList<>();
        player2Cards.add(new Card(CardValue.nine, Suit.DIAMONDS));
        player1.setCards(player1Cards);
        player2.setCards(player2Cards);
        game.playCard(player1,player1.cards.get(0));
        game.playCard(player2, player2.cards.get(0));
    }

    @Test
    public void testPlayFullGame() throws IOException, SparException {
        Player player2 = new Player("player2");
        game.addPlayerToGame(player2);
        game.startGame();
        shareCards(player1, player2);
        while(!(player1.cards.size() == 0 && player2.cards.size() == 0)){
            try {
                game.playCard(player1,player1.cards.get(0));
            } catch (SparException e) {
                game.playCard(player2, player2.cards.get(0));
            }
        }
        assertEquals(player2, game.getWinner());

    }

    private void shareCards(Player player1, Player player2){
        //Clearing random shuffle cards and setting it with fixed cards so test can be deterministic
        player1.cards.clear();
        player2.cards.clear();
        List<Card> player1Cards = new ArrayList<>();
        player1Cards.add(new Card(CardValue.six, Suit.CLUBS));
        player1Cards.add(new Card(CardValue.ten, Suit.DIAMONDS));
        player1Cards.add(new Card(CardValue.queen, Suit.HEARTS));
        player1Cards.add(new Card(CardValue.jack, Suit.SPADES));
        player1Cards.add(new Card(CardValue.king, Suit.DIAMONDS));
        player1.setCards(player1Cards);

        List<Card> player2Cards = new ArrayList<>();
        player2Cards.add(new Card(CardValue.seven, Suit.CLUBS));
        player2Cards.add(new Card(CardValue.nine, Suit.DIAMONDS));
        player2Cards.add(new Card(CardValue.seven, Suit.HEARTS));
        player2Cards.add(new Card(CardValue.queen, Suit.SPADES));
        player2Cards.add(new Card(CardValue.ace, Suit.DIAMONDS));
        player2.setCards(player2Cards);
    }
}