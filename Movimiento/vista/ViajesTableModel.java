package Movimiento.vista;

import Movimiento.controlador.CamionProceso;
import Movimiento.controlador.HiloCamion;
import Movimiento.modelo.Camion;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ViajesTableModel extends AbstractTableModel {
    List<CamionProceso> hilos;
    private String[] columnNames = {"Camion", "Dejo", "Llevo"};

    public ViajesTableModel(List<CamionProceso> camiones){
         this.hilos = camiones;
    }
    
    public void addRow(CamionProceso hilo){
        int row = hilos.size();
        hilos.add(hilo);
        fireTableRowsInserted(row, row);
    }
    
    public void removeRow(CamionProceso hilo) throws InterruptedException{
        hilos.remove(hilo);
        int row = hilos.size();
        fireTableRowsDeleted(row, row);
    }

    public void removeRow(int idx) throws InterruptedException{
        CamionProceso hilo = hilos.get(idx);
        hilo.cancel(true);
        System.out.println("Camion " + hilo.getCamion().getId() + " detenido...");
        this.removeRow(hilo);
    }

    @Override
    public String getColumnName(int columnIndex){
         return columnNames[columnIndex];
    }

    @Override     
    public int getRowCount() {
        return hilos.size();
    }

    @Override        
    public int getColumnCount() {
        return 3; 
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        CamionProceso hilo = hilos.get(rowIndex);
        Camion camion = hilo.getCamion();
        switch (columnIndex) {
            case 0: 
                return "Camion " + camion.getId()+": "+ camion.getOrigen();
            case 1:
                return Integer.toString(camion.getDejo());
            case 2:
                return Integer.toString(camion.getCantidadCarga());
           }
           return null;
   }

   @Override
   public Class<?> getColumnClass(int columnIndex){
          switch (columnIndex){
             case 0:
               return String.class;
             case 1:
               return String.class;
             case 2:
               return String.class;
             }
             return null;
      }
 }
