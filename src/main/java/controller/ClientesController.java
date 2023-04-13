package controller;

import dao.Reader;
import model.Cliente;
import utils.TipoHabitacion;
import view.PanelHotel;

public class ClientesController implements Runnable {
    private PanelHotel panelHotel;

    public ClientesController(PanelHotel panelHotel) {
        this.panelHotel = panelHotel;
    }

    @Override
    public void run() {
        Reader reader = new Reader("files/clientes.txt");
        String line;

        while ((line = reader.read()) != null) {

            gestionClientes(line);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            panelHotel.actualizar();
        }
        this.panelHotel.getHotel().close();
    }

    public void gestionClientes(String linea) {

        String num = linea.split(" ")[2];

        for (String personas : num.split(",")) {
            Cliente cliente = new Cliente(
                    Integer.parseInt(linea.split(" ")[0]),
                    linea.split(" ")[1],
                    Integer.parseInt(personas),
                    TipoHabitacion.valueOf(linea.split(" ")[3].toLowerCase()),
                    Integer.parseInt(linea.split(" ")[4]),
                    this.panelHotel);

            Thread thread = new Thread(cliente);
            thread.start();
        }
    }
}
