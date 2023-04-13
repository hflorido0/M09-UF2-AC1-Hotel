package controller;

import view.PanelHotel;

public class DiasController implements Runnable{

    private PanelHotel panelHotel;

    public DiasController(PanelHotel panelHotel) {
        this.panelHotel = panelHotel;
    }

    @Override
    public void run() {
        while (this.panelHotel.getHotel().isOpen()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            this.panelHotel.getHotel().incrementarDia();
            panelHotel.actualizar();
        }
    }
}
