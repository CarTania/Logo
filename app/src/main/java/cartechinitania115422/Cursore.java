package cartechinitania115422;

import java.awt.*;

/**
 * La classe cursore è un oggetto che estende la classe Point (java.awt.point) e implementa l’interfaccia ICursore, essa viene istanziata all’interno della classe Area.
    Ha la responsabilità  di gestire i movimenti del cursore sull’area di disegno.
    Il cursore si muove grazie a dei comandi (forward e backward) e può disegnare una linea o meno a seconda se l’attributo plot è settato (true o false). 
    La linea viene tracciata con angolo definito dall’attributo direzione (default: 0°).
    La linea tracciata ha una spessore (default: 1) e un colore (di default: nero).
    All’inizio del programma è posizionato sulla home (base/2; altezza/2) che corrisponde al punto centrale dell’area di disegno, la cui dimensione di default: è 640 *480.

 */

public class Cursore extends Point {

    /*Posizione centro dello schermo
    */
    private Point home;
    private int direzione;
    /*  true: penna attaccata al foglio (penDown)
        false: penna staccata dal foglio (penUp)
    */
    private boolean plot; 
    private Color coloreLinea; 
    private Color coloreArea;
    private int penSize; //Spessore della linea tracciata dal cursore
    private int base; //Dimensioni dell'area di disegno (DEFAULT: 640x480)
    private int altezza;

    public Point getHome() {
        return home;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    public int getDirezione() {
        return direzione;
    }

    public void setDirezione(int direzione) {
        this.direzione = direzione;
    }

    public boolean isPlot() {
        return plot;
    }

    public void setPlot(boolean plot) {
        this.plot = plot;
    }

    public Color getColoreLinea() {
        return coloreLinea;
    }

    public void setColoreLinea(Color coloreLinea) {
        this.coloreLinea = coloreLinea;
    }

    public void setColoreLinea(int r, int g, int b)
    {
        coloreLinea = new Color(r, g, b);
    }

    public Color getColoreArea() {
        return coloreArea;
    }

    public void setColoreArea(Color coloreArea) {
        this.coloreArea = coloreArea;
    }

    public void setColoreArea(int r, int g, int b)
    {
        coloreArea = new Color(r, g, b);
    }

    public int getPenSize() {
        return penSize;
    }

    public void setPenSize(int penSize) {
        this.penSize = penSize;
    }

    public Cursore(int base, int altezza)
    {
        penSize = 1;
        coloreLinea = Color.black;
        coloreArea = Color.white;
        plot=false;
        direzione=0;
        this.base = base;
        this.altezza = altezza;
        home = new Point(base/2,altezza/2);
        this.setLocation(home);
    }

    /**
     * Sposta il cursore in avanti data "direzione" per una distanza "dist".
     * @param dist
     */

    public void forward(int dist)
    {
        double dx = dist * Math.cos(direzione * Math.PI / 180);
        double dy = dist * Math.sin(direzione * Math.PI / 180);

        //setLocation(getX()+dx, getY()+dy)
        translate((int) dx, (int) dy); 

        if(getX()>base)
            setLocation(base, getY());

        if(getY()>altezza)
            setLocation(getX(), altezza);
        
        if(getX()<0)
            setLocation(0, getY());
        
        if(getY()<0)
            setLocation(getX(), 0);
    }

    /**
     * Utilizza il metodo forward con distanza negativa.
     * @param dist
     */

    public void backward(int dist)
    {
        forward(-dist);
    }

    /**
     * Ruota il cursore in senso antiorario di gradi "direzione".
     * @param angolo
     */
    public void left(int angolo)
    {
        direzione += angolo;
        direzione %= 360;
    }

    /**
     * Ruota il cursore in senso orario di gradi "direzione". 
     * @param angolo
     */
    public void right(int angolo)
    {
        angolo %= 360;
        direzione -= angolo;
        if(direzione<0)
            direzione += 360;
    }

    /**
     * Posiziona il cursore al centro dell'area di disegno.
     */

    public void home()
    {
        setLocation(base/2, altezza/2);
    }

    /**
     * Stacca il pennino dall'area di disegno.
     */

    public void penup()
    {
        plot = false;
    }
    /**
     * Attacca il pennino dall'area di disegno.
     */

    public void pendown() {
        plot = true;
    }

}
