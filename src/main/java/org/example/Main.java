package org.example;

import org.example.controller.BlackjackController;
import org.example.domain.card.Deck;
import org.example.service.Blackjack;

public class Main {
    public static void main(String[] args) {
        Blackjack blackjack = new Blackjack(new Deck());
        new BlackjackController(blackjack).run();
    }
}