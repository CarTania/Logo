package cartechinitania115422;

import javafx.scene.paint.Color;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * La classe App è una sottoclasse di Application.
 * Essa ha la responsabilità di visualizzare a video le figure geometriche create in Area.
 */

public class App extends Application {


    private Pane root;
    private Area area;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * converte l’oggetto poligono in Polygon.
     * @param p
     * @return Polygon
     */

    public Polygon aggiungiPoligono(Poligono p)
    {
            if (area == null)
            {
                area= new Area();
            }

        Polygon polygon = new Polygon();

        Double[] array = new Double[p.linee.size() * 2];

        int i = 0;

        for (Linea linea : p.linee) {
            double x = linea.getEstremo1().getX();
            double y = linea.getEstremo1().getY();

            array[i++] = x;
            array[i++] = area.getAltezza()-y;
        }

        polygon.getPoints().addAll(array);

        java.awt.Color colore = p.getColore();
        Color paint = new Color(colore.getRed() / 255, colore.getGreen() / 255, colore.getBlue() / 255, colore.getAlpha() / 255);
        polygon.setFill(paint);

        return polygon;
    }

    /**
     * Converte l'oggetto Linea in in Line
     * @param linea
     * @return Line
     */

    public Line aggiungiLinea(Linea linea)
    {
        Line line = new Line();

        double startX = linea.getEstremo1().getX();
        double startY = linea.getEstremo1().getY();
        double endX = linea.getEstremo2().getX();
        double endY = linea.getEstremo2().getY();
        java.awt.Color colore = linea.getColore();
        Color paint = new Color(colore.getRed()/255, colore.getGreen()/255, colore.getBlue()/255, colore.getAlpha()/255);
        double spessore = linea.getSpessore();

        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);
        line.setStroke(paint);
        line.setStrokeWidth(spessore);

        return line;
    }

    /**
     * Manda in esecuzione i file Logo e li visualizza.
     */
    
    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Logo");
        primaryStage.setResizable(false);

        area = new Area();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleziona il file Logo");
        Interprete interprete = new Interprete();
        Alert alert = null;

        try
        {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Logo files (*.logo)", "*.logo");
            fileChooser.getExtensionFilters().add(extFilter);

            interprete = new Interprete(area, fileChooser.showOpenDialog(primaryStage).getAbsolutePath());
            interprete.interpreta();

            render();

            //Salvo il file di output;
            String path = fileChooser.showSaveDialog(primaryStage).getAbsolutePath();
            area.output(path);

            Scene scene = new Scene(root, area.getBase(), area.getAltezza());
            java.awt.Color colore = area.getColoreSfondo();
            Color paint = new Color(colore.getRed() / 255, colore.getGreen() / 255, colore.getBlue() / 255,
                    colore.getAlpha() / 255);
            scene.setFill(paint);
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch(NullPointerException ex)
        {
            alert = new Alert(AlertType.ERROR, "Selezionare un file", ButtonType.CLOSE);
            alert.show();
        }catch(CommandNotFoundException ex)
        {
            alert = new Alert(AlertType.ERROR, ex.getMessage(), ButtonType.CLOSE);
            alert.show();
        }catch(Exception ex)
        {
            alert = new Alert(AlertType.ERROR, ex.getMessage(), ButtonType.CLOSE);
            alert.show();
        }
        
    }

    /**
     * Converte tutte le spezzate e i poligoni di area in oggetti di JavaFx e li aggiunge al Pane.
     */

    public void render()
    {
        root = new Pane();

        ArrayList<Polygon> polygons = new ArrayList<Polygon>();
        ArrayList<Line> lines = new ArrayList<Line>();

        for(Poligono p : area.getPoligoni())
        {
            Polygon polygon = aggiungiPoligono(p);
            java.awt.Color colore = p.getColore();
            polygon.setFill(new Color(colore.getRed()/255,colore.getGreen()/255,colore.getBlue()/255, colore.getAlpha()/255));
            polygons.add(polygon);
        }

        for(Spezzata spezzata : area.getSpezzate())
        {
            for(Linea linea : spezzata.getLinee())
            {
                Line line = aggiungiLinea(linea);
                lines.add(line);
            }
        }

        root.getChildren().addAll(lines);
        root.getChildren().addAll(polygons);
    }

}