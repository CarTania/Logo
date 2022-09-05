package cartechinitania115422;


import java.io.*;
import java.util.*;

/**
 * La classe Parser ha la responsabilità di leggere il file sorgente scritto nel linguaggio LOGO caricandolo in una lista di stringhe che verrà poi utilizzato dalla classe Interprete.
   Durante la lettura del file, vengono saltate le righe vuote e tutti i comandi letti vengono trasformati in maiuscolo (case insensitive).
 */


public class Parser {


    private List<String[]> righe;

    
    /** 
     * @return List<String[]>
     */
    public List<String[]> getRighe() {
        return righe;
    }

    private String filePath;

    public Parser()
    {
        righe = new ArrayList<String[]>();
        filePath = null;
    }

    /**
     * Parse del file logo in una directory
     * @param filePath
     * 
    */
    public Parser(String filePath)
    {
        this();
        this.filePath = filePath;
    }

    /**
     * Lettura del file sorgente.
     * @throws Exception
     */

    public void parse() throws Exception
    {
        String riga = "";
        if (!new File(filePath).isAbsolute())
        filePath= new File("").getAbsolutePath().concat("\\src\\main\\java\\cartechinitania115422\\"+ filePath);
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        
        while ((riga = br.readLine()) != null) 
        {
            if(!riga.isEmpty())
                righe.add(riga.toUpperCase().split(" "));
        }

        br.close();
    }
    /**
     * Override del toString che restituisce il file sorgente. 
     */

    @Override
    public String toString()
    {
        String result="";

        for(String[] s : righe)
        {
            for(String s1 : s)
            {
                result += s1 + " ";
            }

            result += "\n";
        }
        return result;
    }
}
