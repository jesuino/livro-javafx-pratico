# Mostrando Imagens e Figuras Geométricas


### Mostrando Imagens

Um dos ponto mais interessantes do JavaFX é como ele facilitou a criação de aplicações, assim podemos fazer algumas coisas antes dificeis da forma mais fácil possível.

Um exemplo é carregar imagens na aplicação. Temos somente dois passos a serem seguidos nosse caso:

* **Carregar a imagem**: Para isso usamos a classe **javafx.scene.image.Image**. Perceba que essa classe é uma representação alto nível de uma imagem, sendo que a mesma pode vir de um **java.io.InputStream**, um arquivo do seu computador e até mesmo um endereço da internet, sem você se preocupar com qualquer outra coisa;
* **Mostrar a imagem**: Visualizamos a imagem através da classe **javafx.scene.image.ImageView**. Repetimos o mesmo discurso para dizer que ImageView é um Node e é ele que adicionamos à nossa aplicação, aplicamos efeitos, animações etc (calma, iremos mostrar tudo isso em breve).
Após saber disso, você entenderá perfeitamente que com três linhas de código podemos ter uma imagem em uma aplicação JavaFX. Mas código só daqui a pouco :)

### Forma Geométricas

Todas as formas geométricas do JavaFX, adivinhe, também são Nodes e podem sofrer efeitos, transformações e animações, além de ter diversas propriedades em comum. Você pode ver as figuras geométricas oferecidas pelo JavaFX no pacote **javafx.scene.shape**. Todas as figuras geométricas herdam de **javafx.scene.shape.Shape** (que por sua vez herda de... Node), portanto temos propriedades comuns só para as figuras geométricas, entre as quais destacamos:

* **fill**: O preenchimento da figura geométrica. Esse é um parâmetro do tipo Paint, que representa ou uma cor simples(exato: azul, verde, rosa...) ou um gradiente complexo;
* **stroke**: Também do tipo **javafx.scene.paint.Paint**, mas se aplica a linha que envolve a forma geométrica;
* **strokeWidth**: A largura da "stroke".


### Hora do Código

Ufa, hora de se divertir. No código atual iremos mostrar a carga de uma imagem e algumas figuras geométricas, ficando a seu cargo baixar o projeto anexado e fazer algo mais elaborado e divertido. Se formos explorar todas as propriedades de cada forma geométrica, estaríamos sendo redundantes, verbosos e chatos e programação se aprende com ação e não exposição :) Após o código, temos uma explicação linha a linha de tudo que é feito.

*Usando o projeto anterior, crie uma classe chamada ImagemFigurasGeometricas no pacote javafxpratico*

```java
package javafxpratico;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ImagemFigurasGeometricas extends Application {

	private final String IMG_URL = "http://www.oracle.com/ocom/groups/public/@otn/documents/digitalasset/402460.gif";

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage palco) throws Exception {
		Image imagem = new Image(IMG_URL); // 1
		ImageView visualizadorImagem = new ImageView(imagem); // 2
		visualizadorImagem.setTranslateX(80); // 3
		visualizadorImagem.setTranslateY(5); // 4

		Text textoInformativo = new Text("Algumas figuras Geométricas desenhadas com JavaFX");
		textoInformativo.setTranslateX(30);
		textoInformativo.setTranslateY(70);

		Rectangle retangulo = new Rectangle(100, 40); // 5
		retangulo.setTranslateX(5);
		retangulo.setTranslateY(90);
		retangulo.setFill(Color.GREEN); // 6

		Circle circulo = new Circle(30);
		circulo.setTranslateX(160);
		circulo.setTranslateY(110);
		circulo.setFill(Color.YELLOW);

		Circle circuloBranco = new Circle(30);
		circuloBranco.setTranslateX(240);
		circuloBranco.setTranslateY(110);
		circuloBranco.setStroke(Color.BLACK); // 7
		circuloBranco.setStrokeWidth(3.0); // 8
		circuloBranco.setFill(Color.WHITE);

		Ellipse elipse = new Ellipse(30, 10);
		elipse.setTranslateX(320);
		elipse.setTranslateY(110);
		elipse.setFill(Color.BLUE);

		Group componentes = new Group(); // 9
		componentes.getChildren().addAll(visualizadorImagem, textoInformativo, retangulo, circulo, circuloBranco,
				elipse); // 10
		Scene cena = new Scene(componentes, 400, 150);
		palco.setTitle("Gráficos e Imagens em JavaFX");
		palco.setScene(cena);
		palco.show();
	}
}
```