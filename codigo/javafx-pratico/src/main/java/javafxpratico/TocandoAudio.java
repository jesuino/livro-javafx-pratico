package javafxpratico;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TocandoAudio extends Application {

	// Áudio pego do seguinte site: http://sampleswap.org/mp3/song.php?id=105
	private String AUDIO_URL = "http://sampleswap.org/mp3/artist/5101/Peppy--The-Firing-Squad_YMXB-160.mp3";

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage palco) throws Exception {
		AudioClip clip = new AudioClip(AUDIO_URL);// 1
		clip.play(); // 2
		StackPane raiz = new StackPane();
		raiz.getChildren().add(new Text("Tocando Música ")); // 3
		Scene cena = new Scene(raiz, 600, 100);
		palco.setTitle("Tocando Audio em JavaFX");
		palco.setScene(cena);
		palco.show();

	}
}