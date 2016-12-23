###  Adicionando estilo à sua aplicação com CSS

 Até o momento nesse livro fizemos aplicações e deixamos a "cara" delas a padrão que vem com o JavaFX, ou seja, não nos preocupamos em mudar a aparência das da nossa aplicação. Para fazer isso no JavaFX, não precisamos escrever código Java, mas sim conhecer [CSS \(Cascade Style Sheet\)](http://www.w3schools.com/css/), onde podemos declarativamente configurar a aparência de nossa aplicação.

###  O que é e o que pode ser feito com CSS?

 Com o CSS do javaFX é possível adicionar efeitos, mudar cores, dimensionar e completamente trocar a aparência da aplicação.  É possível também definir a aparência quando o mouse fica sobre o elemento.

 CSS é uma linguagem declarativa onde identificamos os elementos da nossa aplicação e em seguida definimos valores para as propriedades suportadas para aquele componente.

 Essa tecnologia já é utilizada em páginas WEB e para o JavaFX todas as possibilidades de uso do CSS estão descritas em um 

[completo guia de referência](http://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html).

###  Como referênciar os componentes do JavaFX no CSS?

 Os componentes javafx podem ter várias classes CSS que referenciamos para usarmos na declaração do CSS. Assim, no código Java falamos qual a classe daquele componente e no CSS referenciamos a classe com **ponto\("."\)**.  Também podemos dar um id para nosso componente e referenciar a classe dele usando **cerquilha \("\#"\).** Note que o ID deve ser único, já a classe pode ser aplicada a diversos nós.

###  Como carregar CSS no JavaFX?

 O CSS pode ficar em um arquivo separado e ser carregado na [Scene](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html) da aplicação ou podemos adicionar estilo a qualquer classe que estenda de [nó](http://aprendendo-javafx.blogspot.com.br/2014/02/a-classe-node-e-seus-principais.html) \(todas as classes de uma cena no JavaFX\) usando o método **setStyle. **

### **Exemplo**

 Criamos a seguinte aplicação de exemplo para que você possa ver o que abordamos anteriormente na prática.

![](/imagens/telas/appCss.png)

Note que a aplicação está diferente. Isso é por que eu carreguei o arquivo**app.css** na cena:

**cena.getStylesheets\(\).add\("app.css"\);**

```css
.root{
    -fx-base: darkblue;
    -fx-background: lightblue;
}
.button {
    -fx-font-weight: bold;
    -fx-font-size: 15px;
}

.label {
    -fx-font-style: italic;
    -fx-font-size: 9px;
    -fx-text-fill: darkgreen;
}

.titulo {
    -fx-font-style: normal;
    -fx-font-weight: bolder;
    -fx-font-size: 30px;
    -fx-alignment: center;
}
```

  
 Também temos contéudos para o label  **lblTitulo** que têm a classe **titulo,** que referenciamos para modificar o estilo:

No código Java:

**lblTitulo.getStyleClass\(\).add\("titulo"\);**

No CSS você pode ver acima a classe _.titulo._

Notem que alguns componentes já tem uma classe usado pelo próprio JavaFX, mas que você pode modificar como quiser. No lado esquerdo podemos adicionar o CSS que queremos e ao apertar o botão, esse CSS é aplicação no Text do lado direito. 

```java
btnAplicar.setOnAction( event -> {
    txtAlvo.setStyle(txtCSS.getText());
});
```

Por fim, a aplicação acima foi construida com o seguinte código:

```java
package javafxpratico;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CssComJavaFX extends Application {

	String txt = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.  "
			+ "Nunc porta erat id lectus interdum, a pharetra est luctus.   "
			+ "Nulla interdum convallis molestie. In hac habitasse platea   "
			+ "dictumst. Ut ullamcorper ultricies viverra. Quisque blandit  "
			+ "libero in ante sagittis sagittis. Ut gravida nibh quis justo "
			+ "sodales rutrum. Fusce euismod diam diam, vitae vulputate urna"
			+ " placerat vel. ";
	
	public void start(Stage s) {
		// declarações
		BorderPane raiz = new BorderPane();
		HBox pnlCentro = new HBox(50);
		VBox vbEsquerda = new VBox(10);
		VBox vbDireita = new VBox(10);
		Button btnAplicar = new Button("Aplicar CSS");
		TextArea txtCSS = new TextArea();
		Text txtAlvo = new Text(txt);
		Label lblTitulo = new Label("Testando CSS");
		Scene cena = new Scene(raiz, 800, 250);

		// configurando Layout e adicionando componentes
		vbEsquerda.getChildren().addAll(new Label("Entre o CSS aqui"), txtCSS);
		vbDireita.getChildren().addAll(new Label("O texto Alvo"), txtAlvo);

		pnlCentro.getChildren().addAll(vbEsquerda, btnAplicar, vbDireita);
		pnlCentro.setAlignment(Pos.CENTER);

		raiz.setCenter(pnlCentro);
		raiz.setTop(lblTitulo);
		BorderPane.setAlignment(lblTitulo, Pos.CENTER);	

		txtCSS.setMinWidth(350);
		txtAlvo.setWrappingWidth(220);
		btnAplicar.setMinWidth(120);
		btnAplicar.setOnAction( event -> {
			txtAlvo.setStyle(txtCSS.getText());
		});
		// configuramos classes para os nossos labels especiais	
		lblTitulo.getStyleClass().add("titulo");
		// informamos o arquivo principal de CSS	
		cena.getStylesheets().add("app.css");
		s.setScene(cena);
		s.setTitle("Teste de CSS do JavaFX");
		s.show();
	}
	
}
```

Veja como ficaria se aplicarmos um CSS específico:

![](/imagens/telas/cssAplicado.png)





