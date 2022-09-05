package cartechinitania115422;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * Classe che contiene l'area di disegno (spezzzate, poligoni e il cursore)
 */
public class Area implements IArea{
    
    private Color coloreSfondo;
    private int base;
    private int altezza;
    private Cursore cursore;

    public Cursore getCursore() {
        return cursore;
    }

    public void setCursore(Cursore cursore) {
        this.cursore = cursore;
    }

    private ArrayList<Spezzata> spezzate;
    private ArrayList<Poligono> poligoni;

    public ArrayList<Spezzata> getSpezzate() {
        return spezzate;
    }

    public ArrayList<Poligono> getPoligoni() {
        return poligoni;
    }

    public Color getColoreSfondo() {
        return coloreSfondo;
    }

    public void setColoreSfondo(Color coloreSfondo) {
        this.coloreSfondo = coloreSfondo;
    }

    public void setColoreSfondo(int r, int g, int b) {
        this.coloreSfondo = new Color(r, g, b);
    }    

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getAltezza() {
        return altezza;
    }

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    /**
     * Costruisce l'area di disegno (dimensioni di default: 640x480)
     */
    public Area() {
        this.base = 640;
        this.altezza = 480;
        coloreSfondo = Color.white;
        cursore = new Cursore(base,altezza);
        spezzate = new ArrayList<Spezzata>();
        poligoni = new ArrayList<Poligono>();
    }

    //Controlla se una linea è già presente nell'area o meno all'interno di una spezzata o di un poligono
    public boolean isLinea(Linea linea)
    {   
        for(Spezzata spezzata : spezzate)
        {
            for(Linea lineaSpez : spezzata.getLinee())
            {
                if(lineaSpez.compareTo(linea)==0)
                    return true;
            }
        }

        for (Poligono poligono : poligoni)
        {
            for (Linea lineaPol : poligono.getLinee())
            {
                if (lineaPol.compareTo(linea) == 0)
                    return true;
            }
        }

        return false;
    }

    /**
     * Aggiunge una spezzata composta di una sola linea all'area 
     * @param spezzataUnaLinea
     */
    public void addSpezzata(Spezzata spezzataUnaLinea) {

        boolean flag=false;

        if(!isLinea(spezzataUnaLinea.getLinea(0))) // La linea è già presente nell'area
        {   
            for (Spezzata spezzata : spezzate) 
            {
                //Se la linea viene aggiunta alla spezzata il flag diventa true
                flag = spezzata.addLinea(spezzataUnaLinea.getLinea(0));
    
                //Se la spezzata forma un poligono viene creato un poligono e rimossa la spezzata
                Poligono poligono = spezzata.creaPoligono(spezzataUnaLinea.getLinea(0));

                if (poligono != null) {
                    spezzate.remove(spezzata);
                    poligono.setColore(cursore.getColoreArea());
                    poligoni.add(poligono);
                }

                if (flag)
                    break;
            }

            if (!flag)
                spezzate.add(spezzataUnaLinea);
        }  
    }

    /**
     * Aggiunge un poligono all'area di disegno
     */

    public void addPoligono(Poligono poligono) {

        poligoni.add(poligono);
    }
    
    /**
     * Il metodo forward crea una linea (se non già esistente)  
     */ 
    public void forward(int dist)
    {
        Point p1 = cursore.getLocation();
        cursore.forward(dist);
        Point p2 = cursore.getLocation();
        Linea linea = new Linea(p1,p2,cursore.getPenSize(),cursore.getColoreLinea());
        if(cursore.isPlot() && !isLinea(linea))
            addSpezzata(new Spezzata(linea));
    }

    public void backward(int dist)
    {
        forward(-dist);
    }

    public void clearScreen() {
        spezzate.clear();
        poligoni.clear();
    }

    /**
     * Imposto il colore di sfondo e le dimensioni dell'area di disegno
     */
        
     public void size(int base, int altezza, Color coloreSfondo)
    {
        this.base = base;
        this.altezza = altezza;
        this.coloreSfondo = coloreSfondo;
        cursore.setBase(base); //Le dimensioni dell'area vengono passate al cursore
        cursore.setAltezza(altezza); 
    }

    /**
     * Genera il file in output del percorso stabilito dal "Path".
     */

    public void output(String path) throws Exception
    {
        File file = new File(path);
        FileWriter fos = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fos);
        
        bw.write("SIZE " + base + " " + altezza + " " + coloreSfondo.getRed() + " " + coloreSfondo.getGreen() + " " + coloreSfondo.getBlue() + "\n");

        for(Spezzata spezzata : spezzate)
        {
            for(Linea linea : spezzata.getLinee())
            {
                bw.write(linea.toString());
            }
        }

        for(Poligono poligono : poligoni)
        {
            bw.write(poligono.toString());
        }

        bw.flush(); //Svuoto il buffer
        bw.close(); //Chiudo il file 
    }

}
