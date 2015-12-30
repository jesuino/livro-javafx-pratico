package javafxpratico;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PraticaVBox extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox caixaVertical = new VBox(); // 1
		caixaVertical.setSpacing(5); // 2
		caixaVertical.setAlignment(Pos.CENTER); // 3

		// 4
		caixaVertical.setTranslateX(10);
		caixaVertical.setTranslateY(20);

		// 5
		Rectangle retanguloAzul = new Rectangle(40, 20);
		Rectangle retanguloVerde = new Rectangle(40, 20);
		Rectangle retanguloVermelho = new Rectangle(40, 20);

		retanguloAzul.setFill(Color.BLUE);
		retanguloVerde.setFill(Color.GREEN);
		retanguloVermelho.setFill(Color.RED);

		// 6
		caixaVertical.getChildren().addAll(retanguloAzul, retanguloVerde, retanguloVermelho);
		stage.setScene(new Scene(caixaVertical, 230, 250));
		stage.show();
		stage.setTitle("Testando VBox");
	}

}
