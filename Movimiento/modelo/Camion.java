package Movimiento.modelo;

public class Camion {
    private long id;
    private int cantidadCarga;
    private int dejo;
    private String origen;
    private String destino;
    private int tiempoLlegada; // en milisegundos
    private int tiempoSalida; // en milisegundos
    
    public Camion (long id, int carga, String origen, String destino){
        this.id = id;
        this.cantidadCarga = carga;
        this.dejo = 0;
        this.origen = origen;
        this.destino = destino;
//        this.tiempoLlegada = tiempoLlegada;
//        this.tiempoSalida = tiempoSalida;
    }
    
    public String[] obtenerInformacion() {
        String [] data = {"Camion: "+ getId(), null, null};
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
     * @return the tiempoLlegada
     */
//    public int getTiempoLlegada() {
//        return tiempoLlegada;
//    }

    /**
     * @param tiempoLlegada the tiempoLlegada to set
     */
//    public void setTiempoLlegada(int tiempoLlegada) {
//        this.tiempoLlegada = tiempoLlegada;
//    }

    /**
     * @return the tiempoSalida
     */
//    public int getTiempoSalida() {
//        return tiempoSalida;
//    }

    /**
     * @param tiempoSalida the tiempoSalida to set
     */
//    public void setTiempoSalida(int tiempoSalida) {
//        this.tiempoSalida = tiempoSalida;
//    }

    /**
     * @return the numeroPasajeros
     */
    public int getCantidadCarga() {
        return cantidadCarga;
    }

//    public int getNumeroPasajeros() {
//        return numeroPasajeros;
//    }
    /**
     * @return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @return the dejo
     */
    public int getDejo() {
        return dejo;
    }

    /**
     * @param dejo the dejo to set
     */
    public void setDejo(int dejo) {
        this.dejo = dejo;
    }
    
}

