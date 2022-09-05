package cartechinitania115422;


import java.util.HashMap;

/**
 * Classe che contiene tutti i comandi Logo (hashmap dei comandi) 
 */
public class Comandi extends HashMap<String,Integer> {
     
    public Comandi()
    {
        put("FORWARD", 0);
        put("BACKWARD", 1);
        put("RIGHT", 2);
        put("LEFT", 3);
        put("CLEARSCREEN", 4);
        put("HOME", 5);
        put("PENUP", 6);
        put("PENDOWN", 7);
        put("SETPENCOLOR", 8);
        put("SETFILLCOLOR", 9);
        put("SETSCREENCOLOR", 10);
        put("SETPENSIZE",11);
        put("RIPETI", 12);
        put("SIZE",13);
        put("LINE", 14);
        put("POLYGON", 15); 
    }


/**
 * Per aggiungere nuove funzionalità come previsto dal progetto.
 */
    public void aggiungiComando(String comando)
    {
        put(comando,size());
    }

    /**
     * ricerca il comando interno dell'HashMap se trovato restituisce il valore associato alla chiave
     * altrimenti restituisce -1.
     * @param comando
     * @return Integer
     */

    public Integer ricercaComando(String comando)
    {
       return getOrDefault(comando, -1);
    }

}
