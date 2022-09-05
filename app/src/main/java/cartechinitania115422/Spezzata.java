package cartechinitania115422;


import java.awt.*;
import java.util.*;

/**
 * La classe Spezzata è formata da una o più oggetti di tipo linea. 
   Essa inizialmente ha cardinalità uguale a 1, in quanto una singola linea viene gestita come una spezzata dalla classe Area. Ogni qualvolta che viene tracciata una linea vengono controllati se i suoi estremi vanno a concatenarsi ad una spezzata già esistente. 
   La classe Spezzata è la superclasse della classe poligono, quindi nel caso che la linea aggiunta renda la spezzata chiusa viene crea un oggetto di tipo Poligono.
 */

public class Spezzata {

    protected ArrayList<Linea> linee;
    private Point estremo1;
    private Point estremo2;

    public ArrayList<Linea> getLinee() {
        return linee;
    }

    /**
     * restituisce la linea di indice "index".
     * @param index
     * @return Linea
     */

    public Linea getLinea(int index) {
        return linee.get(index);
    }

    public Point getEstremo1() {
        return estremo1;
    }

    public Point getEstremo2() {
        return estremo2;
    }

    public void setEstremo1(Point estremo1) {
        this.estremo1 = estremo1;
    }

    public void setEstremo2(Point estremo2) {
        this.estremo2 = estremo2;
    }

    /**
     * Aggiunge o meno una linea alla spezzata.
     * @param linea
     * @return boolean
     */

    public boolean addLinea(Linea linea) {

        Poligono p = creaPoligono(linea);

        if(p!=null)
            return true;

        if(estremo1.getX() == 0 && estremo1.getY() == 0 && estremo2.getX()==0 && estremo2.getY()==0){
            linee.add(linea);
            estremo1 = linea.getEstremo1();
            estremo2 = linea.getEstremo2();
            return true;  
        }
            
        if (linea.getEstremo1().equals(this.estremo1))
        {
            linee.add(0, linea);
            estremo1 = linea.getEstremo2();
            return true;
        }

        if(linea.getEstremo2().equals(this.estremo1))
        {
            linee.add(0, linea);
            estremo1 = linea.getEstremo1();
            return true;
        }

        if (linea.getEstremo1().equals(this.estremo2)) 
        {
            linee.add(linea);
            estremo2 = linea.getEstremo2();
            return true;
        }

        if(linea.getEstremo2().equals(this.estremo2))
        {
            linee.add(linea);
            estremo2 = linea.getEstremo1();
            return true;
        }      

        return false;
            
    }

    /**
     * Crea o meno un poligono.
     * @param linea
     * @return Poligono
     */

    public Poligono creaPoligono(Linea linea)
    {

        if((estremo1.equals(linea.getEstremo1()) || estremo1.equals(linea.getEstremo2())) && (estremo2.equals(linea.getEstremo1()) || estremo2.equals(linea.getEstremo2())))
            return new Poligono(this,linea);

        return null;
    }

    public Spezzata() {
        linee = new ArrayList<Linea>();
        this.estremo1 = new Point();
        this.estremo2 = new Point();
    }

    /**
     * Il costruttore genera una spezzata partendo da una linea
     * 
     * @param linea
     */
    public Spezzata(Linea linea)
    {
        this();
        linee.add(linea);
        estremo1 = linea.getEstremo1();
        estremo2 = linea.getEstremo2();
    }

    @Override
    public String toString() {
        return "Spezzata [estremo1=" + estremo1.toString() + ", estremo2=" + estremo2.toString() + ", linee=" + linee
                + "]";
    }

}
