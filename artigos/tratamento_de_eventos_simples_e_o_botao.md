# Tratamento de eventos simples e o botão

Vamos continuar falando de controles de interface e esse capítulo será dedicado a somente um componente: o Botão. O motivo disso é que para falar de botão, devemos falar de tratamento de evento, logo, precisaremos focar nisso, pois esse conhecimento é muito utilizado em qualquer aplicação JavaFX.

## Tratamento de evento

Tratar evento é determinar qual código será executado de acordo com uma ação do usuário. O usuário interage com sua aplicação através da interface gráfica, e dela ele faz diversas coisas: passa o cursor do mouse sobre um componente, arrasta coisas, clica, move o cursor para dentro ou para fora de um componente, entre outros. Qualquer componente é passível de sobre ações desse tipo por parte do usuário, logo, na classe **Node** podemos tratar qualquer um desses eventos. Nesse capítulo, no entanto, não iremos falar de todos eles, mas só de um que é o evento de ação. Quando o usuário clica em um botão, ele dispara esse evento. Iremos apresentar o botão, fazer um programa simples com ele e explicar como tratar o clique em um botão. Quando entrarmos em detalhes na classe Node iremos mostrar como tratar cada um desses outros eventos

## Botões e Ouvintes

Botões estão em todos os lugares, com certeza você deve ter clicado em algum botão hoje! No JavaFX, um botão pode ser usado através da classe Button. No entanto, simplesmente adicionar um botão na cena não ajuda em muita coisa, você deve informar para ele o que deve ser feito assim que um usuário clicar no mesmo. Isso é feito através de ouvintes de evento, que são classes que implementam uma determinada interface.

No caso de botão, temos o atributo *onAction* que é do tipo da interface **EventHandler**. Essa interface exige que você implemente o método *handle*, que é será chamado quando você clicar no botão. Em resumo, você deve criar uma classe que implementa essa interface e informar ao botão uma instância dessa classe para que o botão chame o método quando o usuário do seu programa clicar nele! O tipo do evento pode ser parametrizado usando Genéricos - assim o tipo especificado pelo genérico será o tipo do parämetro recebido no método *handle*.

Felizmente com o Java 8 a gente não precisa escrever muito código, podemos usar expressões **Lambda**! No final desse livro você deverá encontrar um apêndice que fala sobre Java 8 e as expressões lambdas.

Nossa, muita informação! Mas isso ficará melhor nas próximas linhas, pois mostremos exemplos de código e tudo ficará mais claro.

### Clique em mim!

Vamos começar mostrando um exemplo do clássico clique em mim para assim demonstrar o tratamento mais simples possível de evento. A nossa classe ouvinte, ou tratadora de evento se encontra abaixo e em seguida uma breve explicação do código.

```java
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TratadorEvento implements EventHandler { // 1


 @Override
 public void handle(ActionEvent evento) { // 2
  System.out.println("Evento tratado por uma classe externa");
 }

}
```

1. Conforme já falamos, a classe destinada a tratar eventos deve implementar a Interface EventHandler. Nesse caso também fica claro o uso de Genéricos, onde devemos informar um tipo de classe que herda de **Event**, no caso informamos o **ActionEvent**, pois é o que o botão requer;

2. Esse é o método que será chamado quando clicarmos no botão.O parâmetro evento será enviado pelo gerador do evento, no caso o botão, e deles poderíamos saber tudo de quem gerou o evento.

Mas de quem esse tratador de evento trata eventos? O próximo passo é informar para um botão qual será o seu tratador de evento. Veja abaixo que uso duas linhas para isso e basicamente criamos um botão e informamos quem será o tratador de evento através do método *setOnAction*.

Ótimo, já sabemos tratar eventos, mas e se você não quiser criar uma classe para toda vez que quiser tratar um clique? Você pode utilizar um classe anônima ou as modernas expressões lambdas.

Para ilustrar tudo isso, criamos uma simples aplicação com três botões e as três formas de se registar um tratador de evento. O código abaixo mostra essa aplicação e também explica diretamente no comentário!


```java
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Se quisermos que essa classe trate evento, ela deve herdar de EventHandler
public class Principal extends Application implements EventHandler {

 public static void main(String[] args) {
  launch();
 }

 @Override
 public void start(Stage palco) throws Exception {
  VBox raiz = new VBox(20);
  raiz.setAlignment(Pos.CENTER);
  raiz.setTranslateY(5);

  Button botao1 = new Button("Clique em mim! (Tratador externo)");
  Button botao2 = new Button("Clique em mim! (Class Anônima)");
  Button botao3 = new Button("Clique em mim! (Própria classe)");

  // usamos a classe TratadorEvento para cuidar dos eventos
  botao1.setOnAction(new TratadorEvento());
  // Criando uma instância de uma classe anônima para tratar evento
  botao2.setOnAction(new EventHandler() {

   @Override
   public void handle(ActionEvent evento) {
    System.out.println("Evento tratado por uma classe anônima!");
   }
  });
  // o botão 3 usa essa própria classe para tratar seus eventos
  botao3.setOnAction(this);

  raiz.getChildren().addAll(botao1, botao2, botao3);

  Scene cena = new Scene(raiz, 300, 200);
  palco.setTitle("Tratando eventos");
  palco.setScene(cena);
  palco.show();

 }

 @Override
 public void handle(ActionEvent evento) {
  System.out.println("Evento tratado na próxima classe!");
 }
}
```

A aplicação final bem como o que será impresso no console é mostrado na imagem abaixo