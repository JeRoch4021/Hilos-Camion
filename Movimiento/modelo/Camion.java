package Movimiento.modelo;

import java.util.Random;

public class Camion {
    private long id;
    private int lleva;
    private int deja;
    private CentralCamioneraEnum origen;
    private CentralCamioneraEnum destino;

    public Camion(int lleva, CentralCamioneraEnum origen, CentralCamioneraEnum destino) {
        Random random = new Random();
        this.id = random.nextInt(9000) + 1000;
        this.lleva = lleva;
        this.deja = 0;
        this.origen = origen;
        this.destino = destino;
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
        return lleva;
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

    /**
     * @return the dejo
     */
    public int getDejo() {
        return deja;
    }

    /**
     * @param dejo the dejo to set
     */
    public void setDejo(int dejo) {
        this.deja = dejo;
    }

}

