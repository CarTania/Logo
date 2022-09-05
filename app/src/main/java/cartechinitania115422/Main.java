package cartechinitania115422;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    
    
    /** 
     * Classe per eseguire il programma per via testuale
     * @param args
     */
    public static void main(String[] args)
    { 
        Area area = new Area();
        
        Interprete interprete;

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Nome del file in input (file .logo) -> ");
            String filePath = br.readLine();
            interprete = new Interprete(area, filePath);
            interprete.interpreta();
            System.out.print("Nome del file in output (file .logo) -> ");
            filePath = br.readLine();
            interprete.getArea().output(filePath);
            System.out.println("OUTPUT: \n" + Files.readString(Path.of(filePath)));
        }catch(Exception ex)
        {
            if (ex instanceof NullPointerException)
                System.out.println("Selezionare un file");
            else
                System.out.println("Rilevati errori nel file");
        }
        
    }
}
