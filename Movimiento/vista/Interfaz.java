package Movimiento.vista;

import Movimiento.controlador.GestionarProgreso;
import Movimiento.controlador.HiloCamion;
import Movimiento.controlador.TrasladarValores;
import Movimiento.modelo.Camion;
import Movimiento.vista.ViajesTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public final class Interfaz {
    GestionarProgreso barraProgreso = new GestionarProgreso();
    TrasladarValores traerValor = new TrasladarValores();
    HiloCamion viajesHilos;
    Camion camion;
    List<HiloCamion> viajes_leon;
    List<HiloCamion> viajes_monterrey;
    private JFrame window;
    private JPanel panel_central;
    private JPanel panel_camiones_leon;
    private JPanel panel_camiones_monterrey;
    private JButton[] botones_accion;
    private JProgressBar barra_camiones_leon;
    private JProgressBar barra_camiones_leon_salida;
    private JProgressBar barra_camiones_monterrey;
    private JLabel[] etiquetastexto;
    private String[] encabezados_leon = {"Camiones Leon", "Dejo", "Llevo", "Llegada", "Salida"};
    private JTable camiones_leon;
//    private DefaultTableModel modelo_leon;
    private ViajesTableModel modelo_leon;
    private JScrollPane desplegar_tabla_leon;
    private String[] encabezados_monterrey = {"Camiones MTY", "Dejo", "Llevo", "Llegada", "Salida"};
    private JTable camiones_monterrey;
    private ViajesTableModel modelo_monterrey;
    private JScrollPane desplegar_tabla_monterrey;
    private int contador = 1;
    private int acumular_carga_camiones_leon = 0;
    ThreadGroup grupo_hilos_leon = new ThreadGroup("Viajes Leon");
    ThreadGroup grupo_hilos_monterrey = new ThreadGroup("Viajes Mty");
    boolean paused = false;

    public Interfaz() {
        window = new JFrame();
        panel_central = new JPanel();
        panel_camiones_leon = new JPanel();
        panel_camiones_monterrey = new JPanel();
        botones_accion = new JButton[9];
        for (int i = 0; i < botones_accion.length; i++) {
            botones_accion[i] = new JButton();
        }
        etiquetastexto = new JLabel[13];
        for (int i = 0; i < etiquetastexto.length; i++) {
            etiquetastexto[i] = new JLabel();
        }
        barra_camiones_leon = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
        barra_camiones_monterrey = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
        viajes_leon = new Stack<HiloCamion>(); // lista de hilos 
        viajes_monterrey = new ArrayList<HiloCamion>(); // lista de hilos
        modelo_leon = new ViajesTableModel(viajes_leon);
        camiones_leon = new JTable(modelo_leon);
        desplegar_tabla_leon = new JScrollPane(camiones_leon);
        modelo_monterrey = new ViajesTableModel(viajes_monterrey);
        camiones_monterrey = new JTable(modelo_monterrey);
        desplegar_tabla_monterrey = new JScrollPane(camiones_monterrey);

        atributos();
        armado();
        escuchas();
        mostrar();
    }

    public void atributos() {
        window.setSize(1536, 700);
        window.setTitle("C E N T R A L  C A M I O N E R A");
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel_central.setLayout(null);
        panel_central.setBackground(new Color(4, 15, 129));
        panel_camiones_leon.setBounds(120, 200, 600, 300);
        panel_camiones_leon.setLayout(null);
        panel_camiones_monterrey.setBounds(880, 200, 600, 300);
        panel_camiones_monterrey.setLayout(null);
        for (JButton botones : botones_accion) {
            botones.setFont(new Font("Lucida Sans", Font.BOLD, 23));
        }
        for (JLabel textos : etiquetastexto) {
            textos.setFont(new Font("Lucida Sans", Font.BOLD, 18) {
            });
            textos.setForeground(Color.WHITE);
        }
        botones_accion[0].setText("+");
        botones_accion[0].setBounds(40, 200, 50, 50);
        botones_accion[1].setText("+");
        botones_accion[1].setBounds(800, 200, 50, 50);
        botones_accion[2].setText("-");
        botones_accion[2].setBounds(40, 280, 50, 50);
        botones_accion[3].setText("-");
        botones_accion[3].setBounds(800, 280, 50, 50);
        botones_accion[4].setIcon(Iconos.imagenPause());
        botones_accion[4].setBounds(730, 570, 55, 55);
        botones_accion[5].setIcon(Iconos.imagenReload());
        botones_accion[5].setBounds(850, 570, 55, 55);
        botones_accion[6].setIcon(Iconos.imagenPlay());
        botones_accion[6].setBounds(610, 570, 55, 55);
        etiquetastexto[0].setBounds(120, 505, 100, 30);
        etiquetastexto[0].setText("Total: ");
        etiquetastexto[1].setBounds(880, 505, 100, 30);
        etiquetastexto[1].setText("Total: ");
        etiquetastexto[2].setBounds(290, 505, 100, 30);
        etiquetastexto[2].setText("0");
        etiquetastexto[3].setBounds(410, 505, 100, 30);
        etiquetastexto[3].setText("0");
        etiquetastexto[4].setBounds(1050, 505, 100, 30);
        etiquetastexto[4].setText("0");
        etiquetastexto[5].setBounds(1170, 505, 100, 30);
        etiquetastexto[5].setText("0");
        //barra_camiones_leon.setBounds(40, 40, 380, 30);
        //barra_camiones_monterrey.setBounds(570, 40, 380, 30);
        desplegar_tabla_leon.setBounds(0, 0, 600, 300);
        desplegar_tabla_monterrey.setBounds(0, 0, 600, 300);
    }

    public void armado() {
        window.add(panel_central);
        panel_central.add(panel_camiones_leon);
        panel_central.add(panel_camiones_monterrey);
        for (JButton botones : botones_accion) {
            panel_central.add(botones);
        }
        for (int i = 0; i < etiquetastexto.length; i++) {
            panel_central.add(etiquetastexto[i]);
        }
        panel_central.add(barraProgreso.progresoCamionesLeon(barra_camiones_leon));
        panel_central.add(barraProgreso.progresoCamionesMonterrey(barra_camiones_monterrey));
        panel_camiones_leon.add(desplegar_tabla_leon);
        panel_camiones_monterrey.add(desplegar_tabla_monterrey);
    }

    public void escuchas() {
        botones_accion[0].addActionListener((ActionEvent e) -> {
            int carga = Integer.parseInt(JOptionPane.showInputDialog("¿Cuál sera su carga?"));
            
            viajesHilos = new HiloCamion(carga, "leon", "monterrey", modelo_leon, modelo_monterrey, grupo_hilos_leon);
            modelo_leon.addRow(viajesHilos);
//                modelo_leon.addRow(new HiloCamion(18,"leon", "monterrey", 12, 3, modelo_leon, modelo_monterrey));
//                modelo_leon.addRow(new HiloCamion(22,"leon", "monterrey", 10, 2, modelo_leon, modelo_monterrey));
//                modelo_monterrey.addRow(new HiloCamion(23,"monterrey", "leon", 15, 7, modelo_monterrey, modelo_leon));
            //acumular_carga_camiones_leon = acumular_carga_camiones_leon + carga;
            //barraProgreso.contenidoProgresoCamionesLeon(barra_camiones_leon, acumular_carga_camiones_leon);
            contador++;
        });
        
        botones_accion[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int carga = Integer.parseInt(JOptionPane.showInputDialog("¿Cuál sera su carga?"));
                
                viajesHilos = new HiloCamion(carga, "monterrey", "leon", modelo_monterrey, modelo_leon, grupo_hilos_monterrey);
                modelo_monterrey.addRow(viajesHilos);
            }
        });
        
        botones_accion[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contador--;
                if (camiones_leon.getSelectedRowCount() == 1) {
                    int indice = camiones_leon.getSelectedRow();
//                    String objeto = camiones_leon.getValueAt(indice, 2).toString();
//                    int numero = Integer.parseInt(objeto);
//                    acumular_carga_camiones_leon = acumular_carga_camiones_leon - numero;
//                    barraProgreso.contenidoProgresoCamionesLeon(barra_camiones_leon, acumular_carga_camiones_leon);
                    try {
                        modelo_leon.removeRow(indice);
                    } catch (InterruptedException ex) {
                        System.out.println("Error no se pudo borrara");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error no sea seleccionado nada");
                }
            }
        });
        
        botones_accion[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (camiones_monterrey.getSelectedRowCount() == 1) {
                    int indice = camiones_monterrey.getSelectedRow();
                    try {
                        modelo_monterrey.removeRow(indice);
                    } catch (InterruptedException ex) {
                        System.out.println("Error no se pudo borrara");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error no sea seleccionado nada");
                }
            }
        });

        botones_accion[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!paused) {
                    grupo_hilos_leon.suspend();
                    grupo_hilos_monterrey.suspend();
                    paused = true;
                } else {
                    paused = false;
                    grupo_hilos_leon.resume();
                    grupo_hilos_monterrey.resume();
                }
            }
        });
        
        botones_accion[6].addActionListener(new ActionListener() {
            int sumar = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                for (HiloCamion viaje_leon : viajes_leon) {
                    if (viaje_leon.camion.getDejo()==0 && !viaje_leon.isInterrupted() && viaje_leon.camion.getOrigen() == "leon" && barra_camiones_monterrey.getValue() < 100) {
                        viaje_leon.agregar_barra_progreso(barra_camiones_monterrey);
                        viaje_leon.start();
//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException ex) {
//                            System.out.println("ERROR");
//                        }
                    }
                }
                //etiquetastexto[2].setText(Integer.toString(traerValor.acumularValores()));
                
                for (HiloCamion viaje_monterrey : viajes_monterrey) {
                    if (viaje_monterrey.camion.getDejo()==0 && !viaje_monterrey.isInterrupted() && viaje_monterrey.camion.getOrigen() == "monterrey" && barra_camiones_leon.getValue() < 100) {
                        viaje_monterrey.agregar_barra_progreso(barra_camiones_leon);
                        viaje_monterrey.start();
                    }
                }

//                for (HiloCamion viaje_leon : viajes_leon) {
//                    int valorDejo = Integer.parseInt(modelo_leon.getValueAt(repeticionLeon, 1));
//                    sumarSalidaLeon = sumarSalidaLeon + valorDejo;
//                    repeticionLeon++;
//                }
//                System.out.println("Sumar: "+sumarSalidaLeon);
//                barraProgreso.contenidoProgresoCamionesLeonSalida(barra_camiones_leon_salida, sumarSalidaLeon);
//                for (HiloCamion viaje_monterrey : viajes_monterrey) {
//                }
//                hilo_camion_leon.start();
                 
            }
        });
    }

    public void mostrar() {
        window.setVisible(true);
    }
}
