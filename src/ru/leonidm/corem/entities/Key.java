package ru.leonidm.corem.entities;

import org.bukkit.scoreboard.Score;

public class Key {

    private final Score score;

    public Key(Score score) {
        this.score = score;
    }

    public void setValue(int value) {
        score.setScore(value);
    }

    public void setValue(boolean value) {
        score.setScore(value ? 1 : 0);
    }

    public int getValue() {
        return score.getScore();
    }
}
