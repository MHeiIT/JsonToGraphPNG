package at.deppn;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import com.google.gson.Gson;

import at.deppn.types.DateUser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;

/**
 * Hello world!
 *
 */
public class App extends Application {
	
	public static String path1;
	public static String path2;
	
    public static void main(String[] args) {
    	path1 = args[0];
    	path2 = args[1];
    	
    	args = new String[args.length];
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("time");
        yAxis.setLabel("points");
        //creating the chart
        LineChart<Number, Number> lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("points history");
        //defining a series
        
        DateUser[] du = getUsers(path1);
        
        for (int i = 0; i < du[0].getUsers().length-1;i++) {
        	XYChart.Series series = new XYChart.Series();
        	series.setName(du[0].getUsers()[i].getName());
        	for (int a = du.length; a > 0;a--) {
        		series.getData().add(new XYChart.Data(du.length-a, du[a-1].getUsers()[i].getPoints()));
            }
            lineChart.setAnimated(false);
            lineChart.getData().add(series);
        }
        
        
        Scene scene = new Scene(lineChart, 800, 600);
        saveAsPng(scene, path2);
        System.out.println("Saved");
    }
    
    public void saveAsPng(Scene scene, String path) {
        WritableImage image = scene.snapshot(null);
        File file = new File(path);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public DateUser[] getUsers(String path) {
		Gson gson = new Gson();
		
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));

			DateUser[] userarray = gson.fromJson(reader, DateUser[].class);
			System.out.println("read");
			return userarray;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
