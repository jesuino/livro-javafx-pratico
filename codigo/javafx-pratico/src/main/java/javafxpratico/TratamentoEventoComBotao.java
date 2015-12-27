package javafxpratico;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Se quisermos que essa classe trate evento, ela deve herdar de EventHandler
/**
 * @author wsiqueir
 *
 */
public class TratamentoEventoComBotao extends Application implements EventHandler<ActionEvent> {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage palco) throws Exception {
		VBox raiz = new VBox(20);
		raiz.setAlignment(Pos.CENTER);
		raiz.setTranslateY(5);

		Button botao1 = new Button("Clique em mim! (Tratador externo)");
		Button botao2 = new Button("Clique em mim! (Expressão Lambda)");
		Button botao3 = new Button("Clique em mim! (Própria classe)");

		// usamos a classe TratadorEvento para cuidar dos eventos
		botao1.setOnAction(new TratadorEvento());
		// Usando expressões Lambda para tratar o evento! (muito bom, né)
		botao2.setOnAction(e -> System.out.println("Evento tratado por uma expressão Lambda!"));
		// o botão 3 usa essa própria classe para tratar seus eventos
		botao3.setOnAction(this);

		raiz.getChildren().addAll(botao1, botao2, botao3);

		Scene cena = new Scene(raiz, 300, 200);
		palco.setTitle("Tratando eventos");
		palco.setScene(cena);
		palco.show();

	}

	/*
	 * Como a própria classe TratamentoEventoComBotao implementa a interface
	 * EventHandler, ela mesma pode responder a eventos do botão
	 * 
	 */
	public void handle(ActionEvent evento) {
		System.out.println("Evento tratado na próxima classe!");
	}

	
	/**
	 * Uma classe para tratar os eventos 
	 * poderia ser uma classe externa
	 *
	 */
	public static class TratadorEvento implements EventHandler<ActionEvent> { // 1

		public void handle(ActionEvent evento) { // 2
			System.out.println("Evento tratado por uma classe externa");
		}
	}
}
