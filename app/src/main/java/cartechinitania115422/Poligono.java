package cartechinitania115422;


import java.util.*;

import java.awt.Color;
import java.awt.Point;

/**
 * La classe Poligono è una sottoclasse della classe Spezzata.
   Essa aggiunge l’attributo colore che corrisponde al colore di riempimento.
   Essa può essere creata tramite il comando logo POLYGON oppure tramite il metodo creaPoligono(Linea linea) di Spezzata.
   Il colore del poligono è stabilito  dall’attributo colore Area di cursore se creato tramite il metodo creaPoligono(Linea linea),
   altrimenti se creato tramite il comando logo POLYGON allora viene preso dal parametro del comando stesso.
 */

public class Poligono extends Spezzata {

    private Color colore;

    
    /** 
     * @return Color
     */
    public Color getColore() {
        return colore;
    }

    
    /** 
     * @param colore
     */
    public void setColore(Color colore) {
        this.colore = colore;
    }

    /**
     * Il costruttore genera un poligono partendo da una spezzata aperta e la linea
     * che la chiude
     * 
     * @param s
     * @param l
     */
    public Poligono(Spezzata s, Linea l) {
        this.linee = new ArrayList<Linea>(s.linee);
        this.colore = Color.white;
        addLinea(l);
    }

    /**
     * 
     * @param vertici
     * @param numLatiPoligono
     * @param colore
     */

    public Poligono(List<String[]> vertici, int numLatiPoligono, Color colore)
    {
        this.colore = colore;
        Spezzata s1 = new Spezzata();
        Point p1 = new Point();
        Point p2 = new Point();
        Color c1 = Color.black;
        int spessore = 1;

        for (int i = 0; i < numLatiPoligono; i++) {
            p1 = new Point(Integer.parseInt(vertici.get(i)[0]), Integer.parseInt(vertici.get(i)[1]));
            p2 = null;
            spessore = Integer.parseInt(vertici.get(i)[5]);
            c1 = new Color(Integer.parseInt(vertici.get(i)[2]), Integer.parseInt(vertici.get(i)[3]),
                    Integer.parseInt(vertici.get(i)[4]));

            if (i + 1 < numLatiPoligono) {
                p2 = new Point(Integer.parseInt(vertici.get(i + 1)[0]), Integer.parseInt(vertici.get(i + 1)[1]));
                Linea l1 = new Linea(p1, p2, spessore, c1);
                s1.addLinea(l1);
            }
        }

        Point iniziale = new Point(Integer.parseInt(vertici.get(0)[0]), Integer.parseInt(vertici.get(0)[1]));
        Linea finale = new Linea(p1, iniziale, spessore, c1);

        linee = new ArrayList<Linea>(s1.linee);
        addLinea(finale);
    }

    /**
     * Facilita la scrittura del file di output.
     */
    @Override
    public String toString() {

        String ris = "POLYGON " + getLinee().size() + " " + getColore().getRed() + " " + getColore().getGreen() + " " + getColore().getBlue() + "\n";

        for(Linea linea : linee)
        {
            ris += linea.getEstremo1().getX() + " " + linea.getEstremo1().getY() + " " + linea.getColore().getRed() + " " + linea.getColore().getGreen() + " " + linea.getColore().getBlue() + " " + linea.getSpessore() + "\n";
        }
        
        return ris;
    }

}
