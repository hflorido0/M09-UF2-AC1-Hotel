package view;

import model.Habitacion;
import model.Hotel;
import model.Tecnico;
import utils.EstadoHabitacion;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class PanelHotel extends JPanel {
    private Hotel hotel;
    private int x = 20;
    private int y = 20;
    private int habitacionWidth = 50;
    private int habitacionHeight = 50;

    public PanelHotel(Hotel hotel) {
        this.hotel = hotel;
        JFrame ventana = new JFrame(hotel.getNombre());
        ventana.add(this);

        this.hotel.getTecnicos().add(new Tecnico(1));
        this.hotel.getTecnicos().add(new Tecnico(2));
        this.hotel.getTecnicos().add(new Tecnico(3));

        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }

    public Hotel getHotel() {
        return hotel;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1200, 700); // Tamaño del panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.setColor(Color.PINK);
        g.fillRect(10, 20, habitacionWidth * 10, habitacionHeight * 2);
        g.setColor(Color.BLACK);
        g.drawRect(10, 20, habitacionWidth * 10, habitacionHeight* 2);
        g.drawString(String.valueOf("HOTEL: " + this.hotel.getNombre()), 20, 40);
        g.drawString(String.valueOf("DIA: " + this.hotel.getDia()), 20, 60);
        g.drawString(String.valueOf("DINERO: " + this.hotel.getDinero()), 20, 80);
        g.drawString(String.valueOf("PERSONAS PERDIDAS: " + this.hotel.getPersonasPerdidas()), 20, 100);

        x = 520;
        for (Tecnico tecnico : hotel.getTecnicos()) {
            g.setColor(tecnico.getHabitacion() != null ? Color.GRAY : Color.WHITE);
            g.fillRect(x, y, habitacionWidth, habitacionHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, habitacionWidth, habitacionHeight);
            g.drawString(String.valueOf(tecnico.getId()), x + 10, 40);
            g.drawString(String.valueOf(tecnico.getHabitacion()), x + 10, 60);
            x += habitacionWidth + 10;
        }

        // Dibujar habitaciones
        int pisoActual = -1;
        int y = 70;

        for (Habitacion habitacion : hotel.getHabitaciones()) {
            int piso = habitacion.getNumero() / 100; // Calcular el número del piso
            if (piso != pisoActual) { // Si cambió el piso, empezar una nueva fila
                pisoActual = piso;
                x = 20;
                y += habitacionHeight + 10;
            }

            g.setColor(habitacion.getDisponible().equals(EstadoHabitacion.DISPONIBLE) ? Color.WHITE :
                    (habitacion.getDisponible().equals(EstadoHabitacion.OCUPADA) ? Color.ORANGE :
                            (habitacion.getDisponible().equals(EstadoHabitacion.AVERIADA) ? Color.RED : Color.CYAN)));
            g.fillRect(x, y, habitacionWidth * habitacion.getTipo(), habitacionHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, habitacionWidth * habitacion.getTipo(), habitacionHeight);
            g.drawString(String.valueOf(habitacion.getNumero() + "(" + habitacion.getCapacidad() + ")"), x + 10, y + 20);
            x += habitacionWidth * habitacion.getTipo() + 10;
        }
    }

    public void actualizar() {
        repaint();
    }
}
