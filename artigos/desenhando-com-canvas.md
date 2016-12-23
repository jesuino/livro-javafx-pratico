# Desenhando com Canvas

Mostramos como desenhar [formas geométricas](http://aprendendo-javafx.blogspot.com/2012/07/imagens-e-graficos.html)  com as classes que herdam de Shape no capítulo _**Mostrando Imagens e Figuras Geométricas**_. Essas formas, no entanto, ficam como nós separados no JavaFX. Como faremos se quisermos somente desenhar formas geométricas, textos e outras coisas livremente? Para isso temos o [Canvas](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/canvas/Canvas.html), uma classe muito interessante para desenharmos livremente!

### Canvas

O canvas é simplesmente um nó que representa uma área para desenhar. Isso mesmo, nada mais do que isso. A mágica acontece com  a class [GraphicContext](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/canvas/GraphicsContext.html), que pode ser adquirida assim que criamos um Canvas. Com o _GraphicContext_ você pode desenhar formas geométricas, textos, imagens, mudar cor, aplicar efeito, etc. Veja um exemplo:

![](/imagens/telas/canvas.png)

Para criar essa aplicação usamos o seguinte código:

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class DesenhandoComCanvas extends Application {


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage palco) throws Exception {
        // O construtor do Canvas recebe a largura e a altura
        Canvas canvas = new Canvas(300, 300);
        // O objeto principal do Canvas é o GraphicsContext, que usamos para desenhar
        GraphicsContext ctx = canvas.getGraphicsContext2D();
        // estamos prontos para desenhar coisas! Vamos começar mudando a cor
        ctx.setFill(Color.RED);
        // podemos configurar uma fonte para os textos
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 25));
        // desenhando um texto, o primeiro param é o texto, os seguintes são a pos X e Y
        ctx.fillText("Olá Mundo Canvas", 15, 30);
        // podemos configurar efeitos e novamente trocar a cor
        ctx.setFill(Color.BLUE);
        ctx.setEffect(new BoxBlur());
        ctx.fillText("Olá Mundo Canvas", 15, 60);
        // agora vamos trocar o efeito, cor e desenhar um retângulo(x,y, largura, altura)
        ctx.setEffect(new Reflection());
        ctx.setFill(Color.AQUA);
        ctx.fillRect(15, 90, 240, 20);
        // ou um retângulo sem preenchimento
        ctx.setStroke(Color.GREEN);
        ctx.strokeRect(15, 135, 240, 30);
        // ou circulos (forma oval)
        ctx.setEffect(null);
        ctx.setFill(Color.BROWN);
        ctx.fillOval(15, 175, 90, 25);
        ctx.setStroke(Color.YELLOWGREEN);
        // ou formas ovais sem preenchimento
        ctx.strokeOval(160, 175, 90, 25);
        // ou até desenhar uns poligonos locos, usando diversos pontos X e Y
        double xs[] = {15, 30, 45, 60, 75, 90, 105, 120, 135, 150, 165, 
            180, 195, 210, 225, 240, 255, 270};
        double ys[] = {205, 235, 250, 265, 205, 235, 205, 205, 235, 250, 
            265, 205, 235, 205, 205, 235, 250, 205};
        ctx.setFill(Color.MAGENTA);
        ctx.setEffect(new Reflection());
        ctx.fillPolygon(xs, ys, 18);
        Scene cena = new Scene(new StackPane(canvas), 800, 600);
        palco.setTitle("Canvas");
        palco.setScene(cena);
        palco.show();
    }

}
```

Nesse momento, você deve estar se perguntando qual é a diferença do Canvas para uma aplicação JavaFX comum, já que podemos fazer tudo isso no JavaFX. Bem, o Canvas é uma tela de desenho, é ótimo para animações, jogos ou visualizações fantásticas. Já uma aplicação JavaFX, temos como maior objetivo objetos que se aninham hierarquicamente e são, no geral, estáticos, como essa página WEB. Desenhar essa página com o Canvas seria um buta dor de cabeça, já usar controles e outras características do JavaFX torna o serviço muito mais fácil.

