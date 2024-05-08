# Hilos-Camion

[Uploading HiloCamion.java…]()package Movimiento.controlador;

import Movimiento.modelo.Camion;
import Movimiento.vista.ViajesTableModel;
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
//    DefaultTableModel lista_origen;
//    DefaultTableModel lista_destino;
    
    public void regresarATablaOrigen() throws InterruptedException{
//        System.out.println("Viaje sale en " + this.bus.getTiempoSalida() + " minutos");
//        GuardarSalida = this.bus.getTiempoSalida();
//        GuardarLlegada = this.bus.getTiempoLlegada();
//        while (GuardarSalida > 0) {
//            HiloCamion.sleep(1000);
//            GuardarSalida--;
//            this.bus.setTiempoSalida(GuardarSalida);
//            this.destino_lista.fireTableDataChanged();
//        }
//        System.out.println("Viaje iniciado: " + Arrays.toString(bus.obtenerInformacion()));
//        while (GuardarLlegada > 0) {
//            HiloCamion.sleep(1000);
//            GuardarLlegada--;
//            this.bus.setTiempoLlegada(GuardarLlegada);
//            this.destino_lista.fireTableDataChanged();
//        }

        while (guardarSalir > 0) {
            guardarSalir--;
            HiloCamion.sleep(1000);
        }
        while (guardarLlegar > 0) {
            guardarLlegar--;
            HiloCamion.sleep(1000);
        }
        this.camion.setDejo(this.camion.getCantidadCarga());
        GestionarProgreso.numero(this.camion.getDejo());
        this.destino_lista.removeRow(this);
        this.origen_lista.addRow(this);
        this.origen_lista.fireTableDataChanged();
        this.destino_lista.fireTableDataChanged();
    }
    
    public HiloCamion(int carga, String origen, String destino, ViajesTableModel origen_lista, ViajesTableModel destino_lista) {
        this.origen_lista = origen_lista;
        this.destino_lista = destino_lista;
//        this.lista_origen = lista_origen;
//        this.lista_destino = lista_destino;
        camion = new Camion(this.getId(), carga, origen, destino);
    }

    @Override
    public void run() {
        try {
            //synchronized (this) {
                int salir = 2;
                guardarSalir = salir;
                int llegar = 2;
                guardarLlegar = llegar;
                while (salir > 0) {
                    salir--;
                    HiloCamion.sleep(1000);
                }
                while (llegar > 0) {
                    llegar--;
                    HiloCamion.sleep(1000);
                }
                this.camion.setDejo(this.camion.getCantidadCarga());

                traerValor.setValorDejado(this.camion.getDejo());
                traerValor.acumularValores();
                this.destino_lista.addRow(this);
                this.origen_lista.removeRow(this);
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


# Metodo donde se inicializa el hilo y el metodo donde comienza el hilo

public void escuchas() {
        botones_accion[0].addActionListener((ActionEvent e) -> {
            int carga = Integer.parseInt(JOptionPane.showInputDialog("¿Cuál sera su carga?"));
            
            viajesHilos = new HiloCamion(carga, "leon", "monterrey", modelo_leon, modelo_monterrey);
            modelo_leon.addRow(viajesHilos);
            
});

botones_accion[6].addActionListener(new ActionListener() {
            int sumar = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                for (HiloCamion viaje_leon : viajes_leon) {
                    if (viaje_leon.camion.getDejo()>=0 || !viaje_leon.isAlive()) {
                        #Cuando intento empezar un nuevo hilo despues de inicializarlo en
                        # boton[0], el metodo falla y me aroja una excepcion de AWT-
                        #EventQueue-0
                        
                        viaje_leon.start();
                    } else {
                        JOptionPane.showMessageDialog(null, "Aviso, un camion ya ha partido");
                        break; 
                    }
                }
                
                for (HiloCamion viaje_monterrey : viajes_monterrey) {
                    if (viaje_monterrey.camion.getDejo()>=0 && viaje_monterrey.isAlive()) {
                        viaje_monterrey.start();
                    } else {
                        JOptionPane.showMessageDialog(null, "Aviso, un camion ya ha partido");
                        break; 
                    }
            }
        });
