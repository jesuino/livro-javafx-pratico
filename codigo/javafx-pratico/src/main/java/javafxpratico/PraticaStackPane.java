package javafxpratico;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PraticaStackPane extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage palco) throws Exception {
		StackPane painelEmpilhador = new StackPane();
		Rectangle retangulo = new Rectangle(220, 120);
		retangulo.setFill(Color.GREEN);

		Polygon losango = new Polygon();
		losango.getPoints().addAll(new Double[]{
		     50.0, 50.0,
		     150.0, 0.0,
		     250.0, 50.0,
		     150.0, 100.0,
		     50.0, 50.0 });
		losango.setFill(Color.YELLOW);
		Path arco = new Path();
		MoveTo moveTo = new MoveTo();
		moveTo.setX(0.0);
		moveTo.setY(0.0);

		ArcTo arcTo = new ArcTo();
		arcTo.setX(55.0);
		arcTo.setY(0.0);
		arcTo.setRadiusX(50.0);
		arcTo.setRadiusY(50.0);

		arco.getElements().add(moveTo);
		arco.getElements().add(arcTo);
		arco.setStroke(Color.WHITE);

		arco.setRotate(180);
		arco.setStrokeWidth(5);

		Circle circulo = new Circle(30, Color.BLUE);
		  
		painelEmpilhador.getChildren().addAll(retangulo, losango, circulo, arco);
		
		Scene cena = new Scene(painelEmpilhador, 250, 100); 
		palco.setTitle("Testando StackPane"); 
		palco.setScene(cena); 
		palco.show(); 
	}

}