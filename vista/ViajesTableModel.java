package Movimiento.vista;

import Movimiento.controlador.HiloCamion;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ViajesTableModel extends AbstractTableModel {
    List<HiloCamion> viajes;
//    private List<SI> li = new ArrayList();
    private String[] columnNames = {"Camion", "Dejo", "Llevo"};

    public ViajesTableModel(List<HiloCamion> viajes){
         this.viajes = viajes;
    }
    
    public void addRow(HiloCamion viaje){
        int row = viajes.size();
        viajes.add(viaje);
        fireTableRowsInserted(row, row);
    }
    
    public void removeRow(HiloCamion viaje) throws InterruptedException{
        int row = viajes.size();
        viajes.remove(viaje);
        fireTableRowsDeleted(row, row);
    }

    @Override
    public String getColumnName(int columnIndex){
         return columnNames[columnIndex];
    }

    @Override     
    public int getRowCount() {
        return viajes.size();
    }

    @Override        
    public int getColumnCount() {
        return 3; 
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        HiloCamion viaje = viajes.get(rowIndex);
        switch (columnIndex) {
            case 0: 
                return "Camion " + viaje.camion.getId()+": "+ viaje.camion.getOrigen();
//                return "Origen: " + viaje.bus.getOrigen();
            case 1:
                return Integer.toString(viaje.camion.getDejo());
            case 2:
                return Integer.toString(viaje.camion.getCantidadCarga());
//            case 3:
//                return Integer.toString(viaje.bus.getTiempoLlegada());
//            case 4:
//                return Integer.toString(viaje.bus.getTiempoSalida());
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
             case 3:
               return String.class;
             case 4:
               return String.class;
             }
             return null;
      }
 }
