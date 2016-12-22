### Introdução ao FXML: Criando interfaces em JavaFX usando XML e o Scene Builder

Até agora nossas postagem utilizaram código Java na criação das [interfaces gráficas\(GUI\)](http://pt.wikipedia.org/wiki/Interface_gr%C3%A1fica_do_utilizador). O uso de Java  pode parecer atrativo a primeira vista, no entanto, o código pode se tornar complexo e de díficil manutenção, mas não é só em Java que podemos criar as telas.

O uso de [XML](http://www.w3schools.com/xml/) é possível no JavaFX para que possamos criar a GUI de uma aplicação e então referenciar o mesmo no código Java. Os XMLs que contém componentes JavaFX são chamados de FXML.

### O modo Java de se fazer interfaces

Digamos que temos uma aplicação que contém um campo de texto, um botão e um campo de texto de leitura com uma saudação ao nome entrado. O código de interface para esse simples programa ficaria como segue:

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DigaOlaComJavaFX extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage palco) throws Exception {
        final Effect r = new Reflection();
        final VBox raiz = new VBox(30);
        final HBox hbTopo = new HBox(5);
        final TextField txtNome = new TextField();
        final Button btnAcao = new Button("Enviar");
        final Label lblMensagem = new Label();
        raiz.setTranslateX(10);
        raiz.setTranslateY(10);
        lblMensagem.setText("Digite seu nome e clique no botão");
        hbTopo.getChildren().addAll(txtNome, btnAcao);
        raiz.getChildren().addAll(hbTopo, lblMensagem);
        lblMensagem.setEffect(r);
        Scene cena = new Scene(raiz, 250, 100);
        palco.setTitle("Aplicação usando código Java");
        palco.setScene(cena);
        palco.show();

        btnAcao.setOnAction(e -> lblMensagem.setText("Olá, " + txtNome.getText() + ", bem vindo!"));
    }
}
```

Essa é uma aplicação simples, temos poucos componentes e um leiaute muito fácil de se montar. Mas e quando temos aplicações cheia de controles e leiaute complexo?

![](/imagens/telas/appjavaSimples.png)

### Usando FXML

Utilizar XML em interface gráfica não é uma novidade. O [Pivot](https://pivot.apache.org/), um framework Java para criação de aplicações desktop, e o [Adobe Flex](http://www.adobe.com/products/flex.html) também utilizam esse conceito. A grande vantagem do uso dessa linguagem declarativa está na possibilidade de usar uma ferramenta para a geração da interface e a possibilidade de modificar o XML sem ter que recompilar a aplicação inteira.

O JavaFX traz suporte ao FXML, uma forma de declarar todos os elementos de interface sem escrever uma linha de código de Java. Veja como o programa que demonstramos anteriormente ficaria com FXML:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<?import java.lang.*?>
<?import java.util.*?>
<?import javafx.scene.control.*?>
<?import javafx.scene.effect.*?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.paint.*?>

<AnchorPane id="AnchorPane" prefHeight="88.0" prefWidth="306.0" xmlns:fx="http://javafx.com/fxml" 
    fx:controller="main.ControleAplicacao">
  <children>
    <TextField layoutX="14.0" layoutY="14.0" prefWidth="200.0" fx:id="txtNome" />
    <Button layoutX="226.0" layoutY="15.0" mnemonicParsing="false" 
      onAction="#atualizaMensagem" text="Clique!" />
    <Label fx:id="lblMensagem" layoutX="14.0" layoutY="44.0" prefHeight="21.0" 
      prefWidth="264.0" text="Digite seu nome e clique no botão">
      <effect>
        <Reflection fraction="0.9" />
      </effect>
    </Label>
  </children>
</AnchorPane>
```

Claro que o FXML também pode ser complexo e gigante, já falamos mais sobre isso. No código Java acima é fácil adicionar ações ao botão e manipular os elementos da interface. Mas como fazer com FXML?

### O controller de um FXML

A lógica e o tratamento de eventos em uma aplicação JavaFX que usa FXML fica em uma classe que referênciamos no próprio FXML, essa classe é chamada de controller. Nessa classe podemos referenciar os elementos declarados no FXML para manipulação deles, lá também declaramos o método que irá tratar os eventos. Veja como fica o FXML completo e pronto para uso com o nosso controller.

O código do controller [ControleAplicacao](https://github.com/jesuino/javafx-basic-tutorials/blob/master/blog-javafx/post-11-fxml/src/main/ControleAplicacao.java) está mais abaixo e ele tem declarado campos correspondentes ao nosso [TextField](http://aprendendo-javafx.blogspot.com.br/2012/07/controles-basicos-de-interface-i.html) e [Label](http://aprendendo-javafx.blogspot.com.br/2012/07/controles-basicos-de-interface-i.html). Notem que usamos a anotação [@FXML](http://docs.oracle.com/javafx/2/api/javafx/fxml/FXML.html) no campo, é ela que informa o nome do mesmo e o JavaFX injeta o campo declarado lá no XML para nosso uso aqui! Veja o método _atualizaMensagem_ também, é ele que é invocado quando clicamos no botão.

```java
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControleAplicacao {

 @FXML
 Label lblMensagem;

 @FXML
 TextField txtNome;

 public void atualizaMensagem() {
  lblMensagem.setText("Olá, " + txtNome.getText() + ", bem vindo!");
 }
}
```

### Executando sua aplicação com FXML

Nesse momento, nossa aplicação está funcionando. Só precisamos carregar o FXML e isso é feito através da classe [FXMLLoader](http://docs.oracle.com/javafx/2/api/javafx/fxml/FXMLLoader.html), que lê o nosso arquivo .fxml e retorna um objeto do tipo [Parent](http://docs.oracle.com/javafx/2/api/javafx/scene/Parent.html). Com ele é possível configurar a raiz da cena da aplicação, veja:

```java
public class DigaOlaComFXML extends Application {

 public static void main(String[] args) {
  launch(); 
 }

 @Override
 public void start(Stage palco) throws Exception {
  URL arquivoFXML = getClass().getResource(
    "./ola-mundo.fxml");
  Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
  palco.setScene(new Scene(fxmlParent, 300, 100));
  palco.setTitle("Olá mundo com FXML");
  palco.show();
 }
}
```

### Criando interfaces visualmente com o Scene Builder

Falamos que com Java o projeto poderia ficar muito complexo, mas você pode perceber que o XML não é lá aquelas facilidades... Felizmente temos uma ferramenta onde podemos arrastar e soltar componentes para desenhar nossa tela. Em seguida, exportamos um arquivo .fxml para uso em nossa aplicação JavaFX! Veja uma imagem da ferramenta.

![](/imagens/telas/scene-builder.png)

Essa ótima ferramenta é mantida pela empresa Gluon e pode ser baixada no seguinte endereço: http://gluonhq.com/labs/scene-builder

