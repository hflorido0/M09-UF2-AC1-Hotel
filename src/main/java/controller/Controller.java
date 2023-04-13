package controller;

import model.Hotel;
import model.Tecnico;
import view.PanelHotel;

import java.util.TreeSet;

public class Controller {

    private PanelHotel panelHotel;
    private Hotel hotel;

    public void init() {
        this.hotel = new Hotel("Mi Hotel");
        panelHotel = new PanelHotel(this.hotel);

        DiasController diasController = new DiasController(panelHotel);
        Thread threadDias = new Thread(diasController);
        threadDias.start();

        HotelController hotelController = new HotelController(panelHotel);
        Thread thread3 = new Thread(hotelController);
        thread3.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ClientesController clientesController = new ClientesController(panelHotel);
        Thread thread = new Thread(clientesController);
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        IncidenciasController incidenciasController = new IncidenciasController(panelHotel);
        Thread thread2 = new Thread(incidenciasController);
        thread2.start();

        bucleConsola();

    }

    private void bucleConsola() {
        while (this.hotel.isOpen()){
            //Leemos el id del tecnico y la habitación donde va
            //hacemos comprobaciones
            //Se crea un nuevo thread de Tecnico que tarda 2 dias en reparar
        }
    }
}
