package db;

public class Jugador {

    private int id;
    private String nombre;
    private int puntos;

    public Jugador() {
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }



    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getPuntos() {
        return this.puntos;
    }

}
