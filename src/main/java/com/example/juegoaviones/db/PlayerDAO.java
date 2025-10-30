package com.example.juegoaviones.db;

import com.example.juegoaviones.model.PlayerScore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PlayerDAO
 * Clase encargada de manejar las operaciones de base de datos relacionadas con la tabla PLAYER_SCORES.
 * Permite insertar, listar y eliminar registros.
 */
public class PlayerDAO {

    /**
     * Inserta un nuevo puntaje en la base de datos Oracle.
     *
     * @param score objeto PlayerScore con los datos del jugador
     * @throws SQLException si ocurre un error en la conexión o la sentencia SQL
     */
    public void insertScore(PlayerScore score) throws SQLException {
        String sql = "INSERT INTO PLAYER_SCORES (PLAYER_NAME, SCORE, PLAY_TIME_SECONDS) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, score.getPlayerName());
            ps.setInt(2, score.getScore());
            ps.setInt(3, score.getPlayTimeSeconds());
            ps.executeUpdate();

            System.out.println("✅ Puntuación guardada correctamente para el jugador: " + score.getPlayerName());
        }
    }

    /**
     * Obtiene todos los puntajes almacenados en la base de datos.
     *
     * @return lista de objetos PlayerScore
     * @throws SQLException si ocurre un error en la conexión o lectura
     */
    public List<PlayerScore> getAllScores() throws SQLException {
        List<PlayerScore> scores = new ArrayList<>();
        String sql = "SELECT PLAYER_NAME, SCORE, PLAY_TIME_SECONDS, PLAY_DATE FROM PLAYER_SCORES ORDER BY SCORE DESC";

        try (Connection conn = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PlayerScore score = new PlayerScore(
                        rs.getString("PLAYER_NAME"),
                        rs.getInt("SCORE"),
                        rs.getInt("PLAY_TIME_SECONDS")
                );
                score.setPlayDate(rs.getTimestamp("PLAY_DATE"));
                scores.add(score);
            }
        }
        return scores;
    }

    /**
     * Elimina todos los registros de la tabla PLAYER_SCORES.
     *
     * @throws SQLException si ocurre un error en la conexión o la sentencia SQL
     */
    public void clearScores() throws SQLException {
        String sql = "DELETE FROM PLAYER_SCORES";

        try (Connection conn = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASS);
             Statement stmt = conn.createStatement()) {

            int rows = stmt.executeUpdate(sql);
            System.out.println("🗑️ Registros eliminados: " + rows);
        }
    }
}

