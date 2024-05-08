
package Movimiento.controlador;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class GestionarProgreso {
    private JProgressBar barra_camiones_leon;
    private JProgressBar barra_camiones_monterrey;

    public GestionarProgreso() {
        barra_camiones_leon = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
        barra_camiones_monterrey = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
    }
    
    public JProgressBar progresoCamionesLeon(JProgressBar barra_camiones_leon) {
        barra_camiones_leon.setBounds(40, 130, 680, 30);
        barra_camiones_leon.setStringPainted(true);
        return barra_camiones_leon;
    }

    public JProgressBar contenidoProgresoCamionesLeon(JProgressBar barra_camiones_leon, int carga) {
        barra_camiones_leon.setValue(carga);
        return barra_camiones_leon;
    }
    
    public JProgressBar progresoCamionesMonterrey(JProgressBar barra_camiones_monterrey) {
        barra_camiones_monterrey.setBounds(800, 130, 680, 30);
        barra_camiones_monterrey.setStringPainted(true);
        return barra_camiones_monterrey;
    }

    public JProgressBar contenidoProgresoCamionesMonterrey(JProgressBar barra_camiones_monterrey, int carga) {
        barra_camiones_monterrey.setValue(carga);
        return barra_camiones_monterrey;
    }
    
    public static int numero(int numero){
        return numero;
    }
    
    public static JLabel etiquetaDejarLeon(JLabel etiqueta) {
        etiqueta.setBounds(290, 505, 100, 30);
        return etiqueta;
    }
}
