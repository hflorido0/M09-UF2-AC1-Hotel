package utils;

import java.awt.*;

public enum TipoHabitacion {

    sencilla(1),doble(2),suite(3);

    private int tamano;

    TipoHabitacion(int tamano) {
        this.tamano = tamano;
    }

    public int getTamano() {
        return tamano;
    }
}
