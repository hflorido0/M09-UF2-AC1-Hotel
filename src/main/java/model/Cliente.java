package model;

import utils.EstadoHabitacion;
import utils.TipoHabitacion;
import view.PanelHotel;

public class Cliente implements Runnable {
    private int id;
    private String nombre;
    private int personas;
    private TipoHabitacion tipoHabitacion;

    private int dias;

    private PanelHotel panelHotel;

    public Cliente(int id, String nombre, int personas, TipoHabitacion tipoHabitacion, int dias, PanelHotel panelHotel) {
        this.id = id;
        this.nombre = nombre;
        this.personas = personas;
        this.tipoHabitacion = tipoHabitacion;
        this.dias = dias;
        this.panelHotel = panelHotel;
    }

    public int getId() {
        return id;
    }

    public int getDias() {
        return dias;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPersonas() {
        return personas;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", personas=" + personas +
                ", tipoHabitacion=" + tipoHabitacion +
                '}';
    }

    @Override
    public void run() {
        //System.out.println(this);
        Habitacion habitacion = getHabitacion();
        if (habitacion != null) {
            habitacion.setDisponible(EstadoHabitacion.OCUPADA);
            this.panelHotel.getHotel().setDinero(habitacion.getPrecioNoche());
            //System.out.println("DINERO: " + this.panelHotel.getHotel().getDinero());
            diasEspera(habitacion, habitacion.getPrecioNoche());
        } else {
            //System.out.println("NO HAY ESPACIO");
            this.panelHotel.getHotel().setDinero(-1000);
            //System.out.println("DINERO: " + this.panelHotel.getHotel().getDinero());
            this.panelHotel.getHotel().personaPerdida();
        }
    }

    private boolean diasEspera(Habitacion habitacion, double precioActual) {
        for (int i = 0; i < this.dias; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //Comprobamos i hay una incidencia, de haberla cambiamos de habitación
            if (habitacion.getDisponible().equals(EstadoHabitacion.AVERIADA)) {
                habitacion = getHabitacion();
                if (habitacion == null) {
                    //System.out.println("NO HAY ESPACIO PARA MOVER AL CLIENTE");
                    this.panelHotel.getHotel().setDinero(-1000);
                    this.panelHotel.getHotel().setDinero(precioActual);
                    //System.out.println("DINERO: " + this.panelHotel.getHotel().getDinero());
                    this.panelHotel.getHotel().personaPerdida();
                    return false;
                } else {
                    habitacion.setDisponible(EstadoHabitacion.OCUPADA);
                }
            }
        }

        habitacion.setDisponible(EstadoHabitacion.DISPONIBLE);
        return true;
    }

    private synchronized Habitacion getHabitacion() {
        for (Habitacion habitacion : this.panelHotel.getHotel().getHabitaciones()) {
            if (habitacion.getCapacidad() >= this.personas && habitacion.getTipo() == this.tipoHabitacion.getTamano()
                    && habitacion.getDisponible().equals(EstadoHabitacion.DISPONIBLE)) {
                return habitacion;
            }
        }
        return null;
    }
}
