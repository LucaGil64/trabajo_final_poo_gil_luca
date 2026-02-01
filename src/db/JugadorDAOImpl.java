package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class JugadorDAOImpl implements DAO<Jugador> {

    private Connection conexion = GestorDeConexion.getInstancia().getConexion();

    @Override
    public void insertar(Jugador jugador) {
        String sql = "INSERT INTO jugadores(nombre,puntos) VALUES(?, ?)";
        try (PreparedStatement pstmt = this.conexion.prepareStatement(sql)) {
            pstmt.setString(1, jugador.getNombre());
            pstmt.setInt(2, jugador.getPuntos());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar el jugador: " + e.getMessage());
        }
    }


    @Override
    public ArrayList<Jugador> top10() {
        String sql = "SELECT * FROM jugadores";
        ArrayList<Jugador> jugadores = new ArrayList<>();
        // Para consultas sin parámetros, un Statement simple es suficiente.
        try (Statement stmt = this.conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Jugador jugador = new Jugador();
                jugador.setId(rs.getInt("id"));
                jugador.setNombre(rs.getString("nombre"));
                jugador.setPuntos(rs.getInt("puntos"));
                jugadores.add(jugador);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el top 10 de jugadores: " + e.getMessage());
        }


        Collections.sort(jugadores, (j1, j2) -> j2.getPuntos() - j1.getPuntos());

        int cantidad;
        if (jugadores.size() >= 10) cantidad = 10;
        else cantidad = jugadores.size();

        ArrayList<Jugador> jugadoresTop10 = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            jugadoresTop10.add(jugadores.get(i));
        }

        return jugadoresTop10;
    }

    @Override
    public void clear() {
        String sql = "DELETE FROM jugadores";
        try (PreparedStatement pstmt = this.conexion.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar a los jugadores");
        }
    }
}

