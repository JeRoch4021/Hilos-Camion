package Movimiento.modelo;

import java.util.Random;

public class Camion {
    private long id;
    private int lleva;
    private int deja;
    private CentralCamioneraEnum origen;
    private CentralCamioneraEnum destino;
    private boolean enMatehuala;
    private boolean descargado;
    private boolean cargado;

    public Camion(int carga, CentralCamioneraEnum origen, CentralCamioneraEnum destino) {
        Random random = new Random();
        this.id = random.nextInt(9000) + 1000;
        this.lleva = 0;
        this.deja = carga;
        this.origen = origen;
        this.destino = destino;
        this.enMatehuala = false;
        this.descargado = false;
        this.cargado = true;
    }

    public String getNombreCamion() {
        return "Camion: " + this.getId();
    }

    public String[] obtenerInformacion() {
        String[] data = {"Camion: " + getId(), null, null};
        return data;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the numeroPasajeros
     */
    public int getCantidadCarga() {
        return getLleva();
    }

    /**
     * @return the origen
     */
    public CentralCamioneraEnum getOrigen() {
        return origen;
    }

    /**
     * @return the destino
     */
    public CentralCamioneraEnum getDestino() {
        return destino;
    }

    public int getLleva() {
        return lleva;
    }

    public void setLleva(int lleva) {
        this.lleva = lleva;
    }

    public int getDeja() {
        return deja;
    }

    public void setDeja(int deja) {
        this.deja = deja;
    }

    public boolean estaEnMatehuala() {
        return this.enMatehuala;
    }

    public void setEnMatehuala(boolean enMatehuala) {
        this.enMatehuala = enMatehuala;
    }

    public boolean isDescargado() {
        return descargado;
    }

    public void setDescargado(boolean descargado) {
        this.descargado = descargado;
    }

    public boolean isCargado() {
        return cargado;
    }

    public void setCargado(boolean cargado) {
        this.cargado = cargado;
    }
}

