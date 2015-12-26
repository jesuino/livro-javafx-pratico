# Mostrando Imagens e Figuras Geométricas

Mostrando Imagens
Mais uma vez devo repetir que fazer as coisas em JavaFX é muito simples, principalmente mostrar imagem. Para quem veio do Swing, sabe que até isso era difícil como podermos ver nesse post do site da DevMedia. Por outro lado, no moderno JavaFX necessitamos, mais uma vez, seguir dois simples passos:
Carregar a imagem. Para isso usamos a classe Image. Perceba que essa classe é uma representação alto nível de uma imagem, sendo que a mesma pode vir de um InputStream, um arquivo do seu computador e até mesmo uma URL da internet, sem você se preocupar com qualquer outra coisa;
Mostrar a imagem. Visualizamos a imagem através da classe ImageView. Repetimos o mesmo discurso para dizer que ImageView é um Node e é ele que adicionamos à nossa aplicação, aplicamos efeitos, animações etc (calma, iremos mostrar tudo em posts futuros).
Após saber disso, você entenderá perfeitamente que com três linhas de código podemos ter uma imagem em uma aplicação JavaFX. Mas código só daqui a pouco :)
Forma Geométricas
Todas as formas geométricas do JavaFX, adivinhe, também são Nodes e podem sofrer efeitos, transformações e animações, além de ter diversas propriedades em comum. Você pode ver as figuras geométricas oferecidas pelo JavaFX no pacote javafx.scene.shape. Todas as figuras geométricas herdam de Shape (que por sua vez herda de... Node), portanto temos propriedades comuns só para as figuras geométricas, entre as quais destacamos:
fill: O preenchimento da figura geométrica. Esse é um parâmetro do tipo Paint, que representa ou uma cor simples(exato: azul, verde, rosa...) ou um gradiente complexo;
stroke: Também do tipo Paint, mas se aplica a linha que envolve a forma geométrica;
strokeWidth: A largura da "stroke".
Hora de Código
Ufa, hora de se divertir. No código atual iremos mostrar a carga de uma imagem e algumas figuras geométricas, ficando a seu cargo baixar o projeto anexado e fazer algo mais elaborado e divertido. Se formos explorar todas as propriedades de cada forma geométrica, estaríamos sendo redundantes, verbosos e chatos e programação se aprende com ação e não exposição :). 
Lembre-se: se você se sentir perdido, leia os posts anteriores para maiores informações. Abaixo o código e em seguida a explicação dos pontos mais interessantes:

package main;

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


public class GraficosImagens extends Application {

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

  Text textoInformativo = new Text(
    "Algumas figuras Geométricas desenhadas com JavaFX");
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
  componentes.getChildren().addAll(visualizadorImagem, textoInformativo,
    retangulo, circulo, circuloBranco, elipse); // 10
  Scene cena = new Scene(componentes, 360, 150);
  palco.setTitle("Gráficos e Imagens em JavaFX");
  palco.setScene(cena);
  palco.show();
 }
}

Nessa linha carregamos nossa imagem usando uma URL da internet. Lembrando que essa não é a única maneira, podemos também usar um arquivo ou qualquer coisas que forneça um InputStream;
Agora criamos o nó da imagem e informamos que imagem será mostrada no construtor;
Usamos uma propriedade nova nessa linha e ela nem é só do ImageView, é da classe Node! Com o translateX informamos a posição X(imagine o posicionamento como um plano cartesiano) de um componente no pai. O método set serve para configurar um valor, você já viu o uso dele em classes como o Stage;
Fazemos o mesmo descrito em 3, mas agora para a posição Y. ATENÇÃO: Posicionar componentes assim não é legal de ser feito em aplicações grandes, vamos mostrar em breve os gerenciadores de leiaute do JavaFX;
Aqui já estamos criando uma forma geométrica, um Retângulo (Rectangle). De acordo com a forma criada, teremos parâmetros que devem ser informados. No caso do retângulo, informamos a largura e a altura;
Mudamos a cor padrão da forma geométrica (preta) para verde. Note que aqui usamos uma constante da classe Color;
É informado a cor da linha que envolve essa forma geométrica, nesse caso: preta;
Também aumentamos a largura (ou grossura) da linha;
Outra novidade nesse código: a classe Group. Como o próprio nome disse, usamos ela para agrupar outros nós(classes que herdam de javafx.scene.Node). Ela é um nó de nós, ou seja, podemos aplicar efeito, animar, transformar que isso será aplicado a todos os nós;
Finalmente usamos um novo método dos filhos do grupo que é adicionar todos os nossos nós de uma vez. Podemos fazer isso, ou adicionar um por um.
Abaixo vejam como a aplicação ficou:



Olha, está começando a ficar interessante o negócio, hein?