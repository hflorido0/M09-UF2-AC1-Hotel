package controller;

import dao.Reader;
import model.Habitacion;
import utils.EstadoHabitacion;
import view.PanelHotel;

public class IncidenciasController  implements Runnable{
    private PanelHotel panelHotel;

    public IncidenciasController(PanelHotel panelHotel) {
        this.panelHotel = panelHotel;
    }

    @Override
    public void run() {
        Reader reader = new Reader("files/incidencias.txt");
        String line;

        while ((line = reader.read()) != null) {

            gestionIncidencias(line);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            panelHotel.actualizar();
        }
    }

    public void gestionIncidencias(String linea) {
        int milis = Integer.parseInt(linea.split(" ")[1]);
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        switch (linea.split(" ")[0]) {
            case "P":
                String numHab = linea.split(" ")[2];
                String fin = numHab.split("")[0];
                averiarPlanta(fin);
                break;
            case "A":
                String hab = linea.split(" ")[2];
                Habitacion habitacion = getHabitacionsById(hab);
                if (habitacion != null)
                    habitacion.setDisponible(EstadoHabitacion.AVERIADA);
                break;
            default:
                System.out.println("INSTRUCCION ERRONEA");
                break;

        }
    }

    private Habitacion getHabitacionsById(String hab) {
        for (Habitacion habitacion : this.panelHotel.getHotel().getHabitaciones()) {
            if (habitacion.getNumero() == Integer.parseInt(hab))
                return habitacion;
        }
        System.out.println("ERROR: LA HABITACIÓN NO EXISTE");
        return null;
    }

    private void averiarPlanta(String fin) {
        for (Habitacion habitacion : this.panelHotel.getHotel().getHabitaciones()) {
            if (String.valueOf(habitacion.getNumero()).startsWith(fin)) {
                habitacion.setDisponible(EstadoHabitacion.AVERIADA);
            }
        }
    }
}
