package cartechinitania115422;


import java.awt.*;

/**
 * La classe Linea rappresenta un segmento sull’area di disegno.
   Essa può essere tracciata direttamente con il comando logo LINE oppure con movimento forward o backward del cursore quando il pennino è attaccato al foglio (isPlot()==true).
   La classe Linea implementa l’interfaccia Comparable<Linea> che ci permette di controllare se due linee sono sovrapposte e della stessa lunghezza.
 */

public class Linea implements Comparable<Linea>{

    private Point estremo1, estremo2;
    private double spessore;
    private Color colore;

    public Point getEstremo1() {
        return estremo1;
    }

    public Point getEstremo2() {
        return estremo2;
    }

    public double getSpessore() {
        return spessore;
    }


    public Color getColore() {
        return colore;
    }


    public Linea()
    {
        this.estremo1 = new Point();
        this.estremo2 = new Point();
        this.spessore = 1;
        this.colore = Color.black;
    }

    public Linea(Point estremo1, Point estremo2, int spessore, Color colore) {
        this();
        this.estremo1 = estremo1;
        this.estremo2 = estremo2;
        this.spessore = spessore;
        this.colore = colore;
    }


    /**
     * Controlla se due linee si sovrappongono e sono della stessa lunghezza.
     */
    @Override
    public int compareTo(Linea o) {
        if(o.getEstremo1().equals(this.estremo1) && o.getEstremo2().equals(this.estremo2) || o.getEstremo2().equals(this.estremo1) && o.getEstremo1().equals(this.estremo2))
            return 0;
        return -1;
    }
/**
 * Facilita la scrittura del file di output.
 */

    @Override
    public String toString() {
        return "LINE " + getEstremo1().getX() + " " + getEstremo1().getY() + " " + getEstremo2().getX() + " " + getEstremo2().getY() + " " + getColore().getRed() + " " + getColore().getGreen() + " " + getColore().getBlue() + " " + getSpessore() + "\n";
    }
    
}
