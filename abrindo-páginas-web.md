### Abrindo e renderizando páginas WEB em uma aplicação JavaFX

Nessa postagem vamos falar sobre como carregar páginas WEB dentro de sua aplicação JavaFX. Mais uma vez: essa é uma tarefa muito fácil em JavaFX!

### Um conto de duas classes: WebView e WebEngine

Vamos à parte teórica primeiramente e relembrar que tudo que é visual em uma aplicação JavaFX herda da classe  Node, que [abordamos anteriomente](http://aprendendo-javafx.blogspot.com/2013/10/a-classe-node-e-seus-principais.html). O [WebView](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/web/WebView.html) é a classe que  você vai usar para mostrar o conteúdo de uma página e ele herda de Node, ou seja, é passível de [efeitos](http://aprendendo-javafx.blogspot.com.br/2014/05/adicionando-efeitos-sua-aplicacao.html), [animações](http://aprendendo-javafx.blogspot.com/2013/09/criando-animacoes-usando-as-transicoes.html), [CSS](http://aprendendo-javafx.blogspot.com.br/2014/07/adicionando-estilo-sua-aplicacao-com-css.html), transformações, entre outros.

Já a classe [WebEngine](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/web/WebEngine.html) é onde manipulamos a [DOM](http://tableless.com.br/tenha-o-dom/) da página, executamos javascript, entre outros. É simplesmente o browser  em sí, baseado no [WebKit](https://pt.wikipedia.org/wiki/WebKit), o mesmo motor de navegadores famosos, como o Chrome.

A relação entre essas classes é simples: O WebView usa uma instância de WebEngine e cria a representação visual de um conteúdo HTML na sua aplicação JavaFX. Mas como usar os mesmo?

### Usando WebView

O uso de WEBView consiste em três passos:

* Instanciar a classe _**javafx.scene.web.WebView**_;
* Acessar a **WebEngine** usando o método _getEngine\_e usar o método \_load_ para carregar a página WEB;
* Adicionar o WebView ao Scenegraph da sua aplicação.

  Em código, isso fica assim:

```java
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.scene.Scene;

public class SimplesWEBView extends Application {

    @Override
    public void start(Stage s) {
        // O nó em sí, que será mostrado
        WebView webView = new WebView();
        // A engine é do tipo WebEngine
        webView.getEngine().load("http://aprendendo-javafx.blogspot.com");
        // criamos a cena e adicionamos no nosso stage
        s.setScene(new Scene(webView));
        // mostramos
        s.show();
    }    
}
```

O resultado disso é uma aplicação simples que mostra o nosso blog:

![](/imagens/telas/webSimples.png)

Exatamente, meus caros amigos. Foram 3 linhas de código e uma página WEB na sua app JavaFX.

### Uma aplicação um pouco mais elaborada

Agora vamos fazer uma aplicação que mostra alguns componentes JavaFX interagindo com a WEB Engine. Vamos fazer o básico, pois, como quase todos os componentes JavaFX, isso tomaria muitas páginas desse livro que deveria ser o arroz com feijão, não a feijoada, o filé, lazanha...

Nessa aplicação temos uma lista de URLs no lado esquerdo e quando o usuário seleciona uma, temos a mesma carregada no lado direito no nosso WEB View , vejam:

![](/imagens/telas/appWebJavaFX.png)

O funcionamento é simples: a ListView e o WebView ficam em um gerenciador de layout do tipo HBox. Quando o usuário seleciona um elemento na lista, pegamos a URL selecionada e carregamos na WebEngine usando o WebView. Quando a WebEngine está carregando a página, nós desabilitamos a lista. Fácil, não? Vejam o código inteiro:

```java
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
```



