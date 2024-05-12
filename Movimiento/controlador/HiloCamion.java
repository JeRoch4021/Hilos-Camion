package Movimiento.controlador;

import Movimiento.modelo.Camion;
import Movimiento.vista.ViajesTableModel;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloCamion extends Thread{
//    private final long ID;
//    private final int carga_camion;
//    Modelo modelo = new Modelo();
    public Camion camion;
    TrasladarValores traerValor = new TrasladarValores();
    private ViajesTableModel origen_lista;
    private ViajesTableModel destino_lista;
    private int guardarSalir = 0;
    private int guardarLlegar = 0;
    private JProgressBar progreso;
//    DefaultTableModel lista_origen;
//    DefaultTableModel lista_destino;
    
    public void regresarATablaOrigen() throws InterruptedException{
        
        HiloCamion.sleep(3000);
        this.interrupt();
        this.camion.setDejo(this.camion.getCantidadCarga());
        this.origen_lista.fireTableDataChanged();
        this.destino_lista.fireTableDataChanged();
        GestionarProgreso.actualizarProgresoBarraCamiones(this.camion.getDejo(), this.progreso);

    }

    public void agregar_barra_progreso(JProgressBar progreso) {
        this.progreso = progreso;
    }

    public HiloCamion(int carga, String origen, String destino, ViajesTableModel origen_lista, ViajesTableModel destino_lista, ThreadGroup grupo) {
        super(grupo, origen);
        this.origen_lista = origen_lista;
        this.destino_lista = destino_lista;
//        camion = new Camion(this.getId(), carga, origen, destino);

    }

    @Override
    public void run() {
        try {
            //synchronized (this) {
//                int salir = 2;
//                guardarSalir = salir;
//                int llegar = 2;
//                guardarLlegar = llegar;
//                while (salir > 0) {
//                    salir--;
//                    HiloCamion.sleep(1000);
//                }
//                while (llegar > 0) {
//                    llegar--;
//                    HiloCamion.sleep(1000);
//                }
                HiloCamion.sleep(3000);
                
                this.camion.setDejo(this.camion.getCantidadCarga());

                traerValor.setValorDejado(this.camion.getDejo());
                traerValor.acumularValores();
//                this.destino_lista.addRow(this);
//                this.origen_lista.removeRow(this);
                this.origen_lista.fireTableDataChanged();
                this.destino_lista.fireTableDataChanged();
                
                regresarATablaOrigen();
            //}
            
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloCamion.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Viaje sale en "+this.bus.getTiempoSalida()+" minutos");
//            int salida = this.bus.getTiempoSalida();
//            GuardarSalida = this.bus.getTiempoSalida();
//            int tiempo_llegada = this.bus.getTiempoLlegada();
//            GuardarLlegada = this.bus.getTiempoLlegada();
//            while(salida>0){
//                AutobusViaje.sleep(1000);
//                salida--;
//                this.bus.setTiempoSalida(salida);
//                this.origen_lista.fireTableDataChanged();
//            }
//            //System.out.println("Viaje iniciado: "+ Arrays.toString(bus.obtenerInformacion()));
//            while(tiempo_llegada>0){
//                AutobusViaje.sleep(1000);
//                tiempo_llegada--;
//                this.bus.setTiempoLlegada(tiempo_llegada);
//                this.origen_lista.fireTableDataChanged();
//            }
//            AutobusViaje.sleep(this.bus.getTiempoSalida() * 1000);
//            
//            AutobusViaje.sleep(this.bus.getTiempoLlegada()* 1000);
            //System.out.println("Viaje finalizado: "+ Arrays.toString(bus.obtenerInformacion()));
//            this.bus.setDejo(this.bus.getCantidadCarga());
//            this.destino_lista.addRow(this);
//            this.origen_lista.removeRow(this);
//            this.origen_lista.fireTableDataChanged();
//            this.destino_lista.fireTableDataChanged();
//            
//            regresarATablaOrigen();
//            System.out.println("Viaje sale en "+this.bus.getTiempoSalida()+" minutos");
//            int salida = this.bus.getTiempoSalida();
//            GuardarSalida = this.bus.getTiempoSalida();
//            int tiempo_llegada = this.bus.getTiempoLlegada();
//            GuardarLlegada = this.bus.getTiempoLlegada();
//            while(salida>0){
//                HiloCamion.sleep(1000);
//                salida--;
//                this.bus.setTiempoSalida(salida);
//                this.origen_lista.fireTableDataChanged();
//            }
//            //System.out.println("Viaje iniciado: "+ Arrays.toString(bus.obtenerInformacion()));
//            while(tiempo_llegada>0){
//                HiloCamion.sleep(1000);
//                tiempo_llegada--;
//                this.bus.setTiempoLlegada(tiempo_llegada);
//                this.origen_lista.fireTableDataChanged();
//            }
//            HiloCamion.sleep(this.bus.getTiempoSalida() * 1000);
//            
//            HiloCamion.sleep(this.bus.getTiempoLlegada()* 1000);
            //System.out.println("Viaje finalizado: "+ Arrays.toString(bus.obtenerInformacion()));
//            this.bus.setDejo(this.bus.getCantidadCarga());
//            this.destino_lista.addRow(this);
//            this.origen_lista.removeRow(this);
//            this.origen_lista.fireTableDataChanged();
//            this.destino_lista.fireTableDataChanged();
//            
//            regresarATablaOrigen();
        
        }
    
    }
}
