package db;

import java.util.ArrayList;

public interface DAO<tipo> {

    public void insertar(tipo t);
    public ArrayList<Jugador> top10();
    public void clear();

}
