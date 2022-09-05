package cartechinitania115422;


public class Main {
    
    public static void main(String[] args)
    { 
        Area area = new Area();
        
        Interprete interprete;

        try{
            interprete = new Interprete(area, "logoProgram.logo");
            interprete.interpreta();
            System.out.println("Cursore: " + area.getCursore());
            System.out.println("Poligono area: " + area.getPoligoni().get(0));
        }catch(Exception ex)
        {
            if (ex instanceof NullPointerException)
                System.out.println("Selezionare un file");
            else
                System.out.println("Rilevati errori nel file");
        }
        
    }
}
