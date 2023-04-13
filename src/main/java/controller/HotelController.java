package controller;

import dao.Reader;
import model.Habitacion;
import model.Hotel;
import utils.EstadoHabitacion;
import utils.TipoHabitacion;
import view.PanelHotel;

public class HotelController implements Runnable {
    private PanelHotel panelHotel;
    private Hotel hotel;

    public HotelController(PanelHotel panelHotel) {
        this.panelHotel = panelHotel;
        this.hotel = panelHotel.getHotel();
    }

    @Override
    public void run() {
        Reader reader = new Reader("files/habitaciones.txt");
        String line;

        while ((line = reader.read()) != null) {
            this.hotel.getHabitaciones().add(new Habitacion(
                    Integer.parseInt(line.split(" ")[0]),
                    EstadoHabitacion.valueOf(line.split(" ")[1].toUpperCase()),
                    TipoHabitacion.valueOf(line.split(" ")[2].toLowerCase()),
                    Integer.parseInt(line.split(" ")[3]),
                    Double.parseDouble(line.split(" ")[4])));

            panelHotel.actualizar();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
