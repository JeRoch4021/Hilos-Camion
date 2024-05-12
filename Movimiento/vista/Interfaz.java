package Movimiento.vista;

import Movimiento.controlador.CamionProceso;
import Movimiento.controlador.GestionarProgreso;
import Movimiento.controlador.HiloCamion;
import Movimiento.controlador.TrasladarValores;
import Movimiento.modelo.Camion;
import Movimiento.modelo.CentralCamioneraEnum;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
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
    List<HiloCamion> viajes_leon;
    List<HiloCamion> viajes_monterrey;
    List<CamionProceso> camiones_leon;
    List<CamionProceso> camiones_mty;
    Queue<CamionProceso> matehuala_leon;
    Queue<CamionProceso> matehuala_mty;
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
    private JTable camiones_leon_table;
    private ViajesTableModel modelo_leon;
    private JScrollPane desplegar_tabla_leon;
    private String[] encabezados_monterrey = {"Camiones MTY", "Dejo", "Llevo", "Llegada", "Salida"};
    private JTable camiones_monterrey_table;
    private ViajesTableModel modelo_monterrey;
    private JScrollPane desplegar_tabla_monterrey;
    private int contador = 1;
    private int acumular_carga_camiones_leon = 0;

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
        viajes_leon = new Stack<HiloCamion>();
        viajes_monterrey = new ArrayList<HiloCamion>();
        camiones_leon = new ArrayList<CamionProceso>();
        camiones_mty = new ArrayList<CamionProceso>();
        matehuala_leon = new LinkedList<CamionProceso>();
        matehuala_mty = new LinkedList<CamionProceso>();
        modelo_leon = new ViajesTableModel(camiones_leon);
        camiones_leon_table = new JTable(modelo_leon);
        desplegar_tabla_leon = new JScrollPane(camiones_leon_table);
        modelo_monterrey = new ViajesTableModel(camiones_mty);
        camiones_monterrey_table = new JTable(modelo_monterrey);
        desplegar_tabla_monterrey = new JScrollPane(camiones_monterrey_table);

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
        // agrega camion leon
        botones_accion[0].addActionListener((ActionEvent e) -> {
            int deja = Integer.parseInt(JOptionPane.showInputDialog("¿Cuál sera su carga?"));
            Camion camion = new Camion(deja, CentralCamioneraEnum.LEON, CentralCamioneraEnum.MONTERREY);
            modelo_leon.addRow(new CamionProceso(camion, matehuala_leon, matehuala_mty));
        });

        // agrega camion mty
        botones_accion[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int deja = Integer.parseInt(JOptionPane.showInputDialog("¿Cuál sera su carga?"));
                Camion camion = new Camion(deja, CentralCamioneraEnum.MONTERREY, CentralCamioneraEnum.LEON);
                modelo_monterrey.addRow(new CamionProceso(camion, matehuala_leon, matehuala_mty));
            }
        });

        //remueve camion leon
        botones_accion[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contador--;
                if (camiones_leon_table.getSelectedRowCount() == 1) {
                    int indice = camiones_leon_table.getSelectedRow();
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

        // remueve camion mty
        botones_accion[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (camiones_monterrey_table.getSelectedRowCount() == 1) {
                    int indice = camiones_monterrey_table.getSelectedRow();
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

        // pausa camion hilo
        botones_accion[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(CamionProceso camion : camiones_leon) {
                    if(!camion.estaPausado()) {
                        camion.pausa();
                    }
                }

                for(CamionProceso camion : camiones_mty) {
                    if(!camion.estaPausado()) {
                        camion.pausa();
                    }
                }
            }
        });

        // inicia o reanuda camion hilo
        botones_accion[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (CamionProceso viaje_leon : camiones_leon) {
                    if (viaje_leon.estaPausado()) {
                        viaje_leon.reanudar();
                        continue;
                    }

                    viaje_leon.execute();
                }

                for (CamionProceso viaje_mty : camiones_mty) {
                    if (viaje_mty.estaPausado()) {
                        viaje_mty.reanudar();
                        return;
                    }

                    viaje_mty.execute();
                }
                 
            }
        });
    }

    public void mostrar() {
        window.setVisible(true);
    }

    public void pausarCamiones() {

    }

    public void reanudarcamiones() {

    }
}
