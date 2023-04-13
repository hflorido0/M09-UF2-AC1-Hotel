package model;

public class Tecnico {
    private int id;
    private Habitacion habitacion;

    public Tecnico (int id) {
        this.id = id;
    }

    public void setHabitacion (Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public int getId() {
        return id;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void terminar () {
        this.habitacion = null;
    }
}
