package Movimiento.controlador;

import Movimiento.modelo.Camion;
import Movimiento.modelo.CentralCamioneraEnum;
import Movimiento.vista.ViajesTableModel;

import javax.swing.*;
import java.util.Queue;

public class CamionProceso extends SwingWorker<Void, Void> {
    private boolean pausado = false;
    private Camion camion;
    Queue<CamionProceso> matehuala_camiones_leon;
    Queue<CamionProceso> matehuala_camiones_mty;

    public CamionProceso(Camion camion, Queue<CamionProceso> matehuala_camiones_leon, Queue<CamionProceso> matehuala_camiones_mty) {
        super();
        this.camion = camion;
        this.matehuala_camiones_leon = matehuala_camiones_leon;
        this.matehuala_camiones_mty = matehuala_camiones_mty;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (!isCancelled()) {
            if (!pausado) {
                Thread.sleep(3000);
                System.out.println("Camion " + camion.getId() + " - " + camion.getOrigen().toString() + " ejecutandose...");
                if (!this.camion.estaEnMatehuala()) {
                    this.descargar();
                } else {
                    this.cargar();
                    if (this.camion.isCargado() && this.getCamion().isDescargado()) {
                        System.out.println("Camion " + camion.getId() + " - " + camion.getOrigen().toString() + " yendose de matehuala con carga...");
                        this.camion.setEnMatehuala(false);
                    }
                }
            }
        }
        return null;
    }

    public void pausa() {
        pausado = true;
        System.out.println("Camion " + camion.getId() + " - " + camion.getOrigen().toString() + " pausado...");
    }

    public void reanudar() {
        pausado = false;
        System.out.println("Camion " + camion.getId() + " - " + camion.getOrigen().toString() + " reanudado...");
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

    public void descargar() {
        if (this.getCamion().getOrigen() == CentralCamioneraEnum.LEON) {
            this.matehuala_camiones_leon.add(this);
        } else {
            this.matehuala_camiones_mty.add(this);
        }

        this.getCamion().setEnMatehuala(true);
        this.getCamion().setCargado(false);
        System.out.println("Camion " + camion.getId() + " - " + camion.getOrigen().toString() + " en matehuala...");
    }

    public void cargar() {
        if (this.camion.getOrigen() == CentralCamioneraEnum.LEON) {
            this.cargar(this.matehuala_camiones_mty);
        } else {
            cargar(this.matehuala_camiones_leon);
        }
    }

    public void cargar(Queue<CamionProceso> matehuala_camiones) {
        if (matehuala_camiones.size()>0) {
            CamionProceso camionHilo = matehuala_camiones.remove();
            System.out.println("Camion " + camion.getId() + " - " + camion.getOrigen().toString() + " cargando de camion de "+ camionHilo.getCamion().getOrigen().toString());

            this.getCamion().setLleva(camionHilo.getCamion().getDeja());
            this.getCamion().setCargado(true);
            camionHilo.getCamion().setDescargado(true);
            return;
        }

        System.out.println("Camion " + camion.getId() + " - " + camion.getOrigen().toString() + " aun esperando carga en matehuala ...");
    }
}

