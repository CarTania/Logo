package cartechinitania115422;

import java.awt.Color;

/**
 * Interfaccia della classe Area.
 */
public interface IArea {
    
    void addSpezzata(Spezzata spezzataUnaLinea);
    void addPoligono(Poligono poligono);
    void forward(int dist);
    void backward(int dist);
    void clearScreen();
    void size(int base, int altezza, Color coloreSfondo);
    void output(String path) throws Exception;
}
