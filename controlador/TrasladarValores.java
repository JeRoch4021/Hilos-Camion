package Movimiento.controlador;

public class TrasladarValores {
    int valorDejado;
    int valor;
    
    public void setValorDejado(int numero) {
        this.valorDejado = numero;
    }
    
    public int getValorDejado() {
        return valorDejado;
    }
    
    public int acumularValores(){
        valor = valor + valorDejado;
        return valor;
    }
}
