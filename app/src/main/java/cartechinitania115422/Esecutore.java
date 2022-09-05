package cartechinitania115422;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * La classe Esecutore ha la responsabilità di eseguire i comandi passati dall’interprete tramite 
 * il metodo esegui(String [] riga).
 * Il comando ricevuto dall’interprete viene ricercato nell’hashmap dei comandi “mappa” e 
 * successivamente viene restituito il valore corrispondente alla chiave, 
 * ad ogni valore dell’hashmap corrisponde un case dello switch del metodo esegui (String[] riga).
 * Ogni case richiama una funzionalità diversa in base al comando passato.
 */

public class Esecutore {
    
    private int numLatiPoligono;
    private Color colore;
    private Area area;
    private Comandi mappa;

    public Esecutore(Area area)
    {
        
        this.mappa = new Comandi();
        this.area = area;
    }

    public void esegui(String[] riga) throws Exception
    {
        int key = mappa.ricercaComando(riga[0]);
        int param1, param2, param3, param4, param5;

        switch(key)
        {
            case 0:
                param1 = Integer.parseInt(riga[1]);
                area.forward(param1);
                break;

            case 1:
                param1 = Integer.parseInt(riga[1]);
                area.backward(param1);
                break;

            case 2:
                param1 = Integer.parseInt(riga[1]);
                area.getCursore().right(param1);
                break;

            case 3:
                param1 = Integer.parseInt(riga[1]);
                area.getCursore().left(param1);
                break;

            case 4:
                area.clearScreen();
                break;

            case 5:
                area.getCursore().home();
                break;

            case 6:
                area.getCursore().penup();
                break;

            case 7:
                area.getCursore().pendown();
                break;

            case 8:
                param1 = Integer.parseInt(riga[1]); //Red 
                param2 = Integer.parseInt(riga[2]); //Green 
                param3 = Integer.parseInt(riga[3]); //Blue
                area.getCursore().setColoreLinea(param1, param2, param3);
                break;

            case 9:
                param1 = Integer.parseInt(riga[1]);
                param2 = Integer.parseInt(riga[2]);
                param3 = Integer.parseInt(riga[3]);
                area.getCursore().setColoreArea(param1, param2, param3);
                break;

            case 10:
                param1 = Integer.parseInt(riga[1]);
                param2 = Integer.parseInt(riga[2]);
                param3 = Integer.parseInt(riga[3]);
                area.setColoreSfondo(param1, param2, param3);
                break;

            case 11:
                param1 = Integer.parseInt(riga[1]);
                area.getCursore().setPenSize(param1);
                break;
            
            case 12:
                param1 = Integer.parseInt(riga[1]); //num iterazioni
                String comandi="";

                for (String parametro : riga)
                    comandi += parametro + " ";

                comandi = comandi.substring(comandi.indexOf("[") + 1, comandi.indexOf("]"));

                ripeti(param1, comandi);
                break;

            case 13:
                param1 = Integer.parseInt(riga[1]);
                param2 = Integer.parseInt(riga[2]);
                param3 = Integer.parseInt(riga[3]);
                param4 = Integer.parseInt(riga[4]);
                param5 = Integer.parseInt(riga[5]);

                area.size(param1, param2, new Color(param3,param4,param5));
                break;

            case 14:
                Point punto1 = new Point(Integer.parseInt(riga[1]), Integer.parseInt(riga[2]));
                Point punto2 = new Point(Integer.parseInt(riga[3]), Integer.parseInt(riga[4]));
                int spessore = Integer.parseInt(riga[8]);
                colore = new Color(Integer.parseInt(riga[5]), Integer.parseInt(riga[6]), Integer.parseInt(riga[7]));
                Linea l1 = new Linea(punto1,punto2,spessore,colore);
                area.addSpezzata(new Spezzata(l1));
                break;

            case 15:
                numLatiPoligono = Integer.parseInt(riga[1]);
                colore = new Color(Integer.parseInt(riga[2]), Integer.parseInt(riga[3]), Integer.parseInt(riga[4]));
                break;

            default:
                throw new CommandNotFoundException("File non valido.\nRilevati errori di sintassi");
        }

    }

    /**
     * Ripeti esegue una lista di comandi n volte
     */
     
    public void ripeti(int n, String comandi) throws Exception
    {
        List<String> listaComandi = new ArrayList<>();

        boolean flag=false;

        StringBuilder sb = new StringBuilder();

        /*
        String[] stringhe = comandi.split(" ");
        ArrayList<String> comandi = Arrays.asList(stringhe);
        Iterator<String> iterator = comandi.iterator();
        */

        Iterator<String> iterator = Arrays.asList(comandi.split(" ")).iterator();

        while(iterator.hasNext())
        {
            String token = iterator.next();
            if (!token.matches("-?\\d+(\\.\\d+)?")) // Non è un numero
            {
                if (flag) {
                    listaComandi.add(sb.toString());
                    sb.delete(0, sb.length()); //Cancella il contenuto dello string builder
                    flag = false;
                }

                sb.append(token);
                flag = true;
            } else {
                sb.append(" " + token);
                if(!iterator.hasNext())
                    listaComandi.add(sb.toString());
            }
        }

        for(int i=0; i<n; i++)
        {
            for(String riga: listaComandi)
            {
                esegui(riga.split(" "));
            }
        }
        
    }

    /**
     * Crea un nuovo poligono con i vertici
     * @param params
     */

    public void poligono(List<String[]> params) {
        Poligono poligono = new Poligono(params, numLatiPoligono, colore);
        area.addPoligono(poligono);
    }

}
