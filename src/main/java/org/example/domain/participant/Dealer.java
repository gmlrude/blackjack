package org.example.domain.participant;

public class Dealer extends Participant {
    public boolean shouldDrawMore() {
        return getScore() <= 16;
    }
}
