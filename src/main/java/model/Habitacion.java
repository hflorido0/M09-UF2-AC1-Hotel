package model;

import utils.EstadoHabitacion;
import utils.TipoHabitacion;

import java.util.Objects;

public class Habitacion implements Comparable<Habitacion> {
    private int numero;
    private EstadoHabitacion disponible;
    private TipoHabitacion tipo;
    private int capacidad;
    private double precioNoche;

    public Habitacion(int numero, EstadoHabitacion disponible, TipoHabitacion tipo, int capacidad, double precioNoche) {
        this.numero = numero;
        this.disponible = disponible;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.precioNoche = precioNoche;
    }

    public int getNumero() {
        return numero;
    }

    public int getTipo() {
        return tipo.getTamano();
    }

    public EstadoHabitacion getDisponible() {
        return disponible;
    }

    public void setDisponible(EstadoHabitacion disponible) {
        this.disponible = disponible;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habitacion that = (Habitacion) o;
        return numero == that.numero && disponible == that.disponible && capacidad == that.capacidad &&
                Double.compare(that.precioNoche, precioNoche) == 0 && tipo == that.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public int compareTo(Habitacion o) {
        return this.numero - o.getNumero();
    }

    public void arreglar() {
        System.out.println("ARREGLANDO HAB: " + this.getNumero());
    }
}
