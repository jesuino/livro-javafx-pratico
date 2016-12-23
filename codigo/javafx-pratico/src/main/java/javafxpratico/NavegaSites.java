package javafxpratico;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import java.util.stream.Stream;

/**
 *
 * @author william
 */
public class NavegaSites extends Application {
	
	public static void main(String[] args) {
		launch();
	}
    
	final String[] sites = {
		"aprendendo-javaee.blogspot.com",
		"aprendendo-javafx.blogspot.com",
		"aprendendo-javase.blogspot.com",
		"aprendendo-jboss.blogspot.com",
		"jugvale.com",
		"javafx.com",
		"java.com",
		"google.com"
	};

    @Override
    public void start(Stage s) {
    	// criando uma lista visual de Strings, um webView e uma caixinha pra colocar as coisas
	ListView<String> listaSites = new ListView<>();
	WebView webView = new WebView();
        HBox raiz = new HBox(20);

	// Para cada site no array adicionamos na lista visual
	Stream.of(sites).forEach(listaSites.getItems()::add);
	
	// Quando o usuário seleciona um item, carregamos a página
	listaSites.getSelectionModel().selectedItemProperty().addListener(
	    (obs, o, n) -> {
	        if(n != null) webView.getEngine().load("http://www." + n);
	});
	
	// a lista não permite seleção de página quando uma página está sendo carregada
	listaSites.disableProperty().bind(webView.getEngine().getLoadWorker().runningProperty());

	// formalidades...
	raiz.getChildren().addAll(listaSites, webView);
        s.setScene(new Scene(raiz));
        s.show();
    }    
}