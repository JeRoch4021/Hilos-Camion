package Movimiento.controlador;

import Movimiento.modelo.Camion;
import Movimiento.vista.ViajesTableModel;

import javax.swing.*;

public class CamionProceso extends SwingWorker<Void, Void> {
    private boolean pausado = false;
    private Camion camion;

    public CamionProceso(Camion camion) {
        super();
        this.camion = camion;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (!isCancelled()) {
            if (!pausado) {
                Thread.sleep(3000);
                System.out.println("Camion " + camion.getId() + " ejecutandose...");
            }
        }
        return null;
    }

    public void pausa() {
        pausado = true;
        System.out.println("Camion " + camion.getId() + " pausado...");
    }

    public void reanudar() {
        pausado = false;
        System.out.println("Camion " + camion.getId() + " reanudado...");
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public boolean estaPausado() {
        return pausado;
    }
}

