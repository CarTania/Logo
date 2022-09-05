package cartechinitania115422;


import java.util.*;

/**
 * La classe Interprete ha la responsabilità di analizzare le righe restituite dal Parser
 * e inviarle alla classe Esecutore.
*/

public class Interprete {
    
    private Parser parser;
    private Area area;
    private Esecutore esecutore;

    public void setArea(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }

    public Interprete()
    {
        area = new Area();
        esecutore = new Esecutore(area);
        parser = new Parser();
    }

    public Interprete(Area area, String filePath)
    {
        this();
        this.area = area;
        parser = new Parser(filePath);
        esecutore = new Esecutore(area);
    }

    /**
     * Interpreta le righe del file logo passate dal Parser e richiama il metodo esegui(String[]riga) di Esecutore.
     * @throws Exception
     */

    public void interpreta() throws Exception
    {
        parser.parse();
        int i=-1;
        List<String[]> linee = parser.getRighe();
        List<String[]> param = new ArrayList<String[]>();
        
        for(String[] linea : linee)
        {
            if(linea[0].equals("POLYGON"))
            {
                i = Integer.parseInt(linea[1]);
                esecutore.esegui(linea);
            }else 
            {
                if (i == 0 && param.size() > 0) 
                {
                    esecutore.poligono(param);
                }

                if(i>0)
                {
                    param.add(linea);
                    i--;
                }else
                    esecutore.esegui(linea);
                    
            }
        }
    }

}
