package com.example.juegoaviones.model;

import java.sql.Timestamp;

/**
 * PlayerScore
 * Representa un registro de puntuación de un jugador.
 * Contiene el nombre, puntuación, tiempo de juego y la fecha en que se registró.
 */
public class PlayerScore {

    private String playerName;
    private int score;
    private int playTimeSeconds;
    private Timestamp playDate;

    // === Constructores ===

    public PlayerScore() {}

    public PlayerScore(String playerName, int score, int playTimeSeconds) {
        this.playerName = playerName;
        this.score = score;
        this.playTimeSeconds = playTimeSeconds;
    }

    // === Getters y Setters ===

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPlayTimeSeconds() {
        return playTimeSeconds;
    }

    public void setPlayTimeSeconds(int playTimeSeconds) {
        this.playTimeSeconds = playTimeSeconds;
    }

    public Timestamp getPlayDate() {
        return playDate;
    }

    public void setPlayDate(Timestamp playDate) {
        this.playDate = playDate;
    }

    // === Métodos auxiliares ===

    @Override
    public String toString() {
        return "PlayerScore{" +
                "playerName='" + playerName + '\'' +
                ", score=" + score +
                ", playTimeSeconds=" + playTimeSeconds +
                ", playDate=" + playDate +
                '}';
    }
}
