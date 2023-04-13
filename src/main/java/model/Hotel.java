package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Hotel {
    private String nombre;
    private Set<Habitacion> habitaciones;

    private List<Tecnico> tecnicos;
    private double dinero;
    private int dia;
    private int personasPerdidas;
    private boolean open;

    public Hotel(String nombre) {
        this.nombre = nombre;
        this.habitaciones = new TreeSet<>();
        this.dinero = 4000;
        this.dia = 1;
        this.personasPerdidas = 0;
        this.open = true;
        this.tecnicos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }
    public Set<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public List<Tecnico> getTecnicos() {
        return tecnicos;
    }

    public boolean isOpen() {
        return open;
    }

    public void close() {
        this.open = false;
    }

    public synchronized double getDinero() {
        return dinero;
    }

    public synchronized void setDinero(double dinero) {
        this.dinero += dinero;
    }

    public int getPersonasPerdidas() {
        return personasPerdidas;
    }
    public void personaPerdida () {
        this.personasPerdidas++;
    }

    public int getDia() {
        return dia;
    }

    public void incrementarDia() {
        this.dia ++;
    }
}