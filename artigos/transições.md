### O que são transições?

Transições são um conjunto de classes da API do JavaFX  que  permitem que se crie animações. Com ela nós podemos modificar o valor de uma propriedade em um dado tempo. Por exemplo, você poderia fazer um objeto mover da posição X 0 até a posição X 150 em 2 segundos e isso iria criar uma animação.

Após configurar os parâmetros corretamente, você pode decidir quando a transição começa e até parar antes da mesma terminar. Ou seja, você pode, por exemplo, utilizar um botão para disparar o início da transição e outro para parar:

![](/imagens/telas/transicoes.png)

### Transições disponíveis na API do JavaFX

As classes de transição oferecidades no JavaFX ficam no pacote [javafx.animation](http://docs.oracle.com/javafx/2/api/javafx/animation/package-frame.html) e as seguintes transições estão disponíveis para uso:

* [FadeTransition](http://docs.oracle.com/javafx/2/api/javafx/animation/FadeTransition.html): Muda a opacidade\(tranparência\) de um nó. O FadeTransition permite variar essa opacidade em um dado tempo. Por exemplo, você pode configurar uma transição para em 1 segundo variar a opacidade de um nó\(um botão, uma imagem, uma figura geométrica, etc\) de 0 até 1 e isso fará com que o mesmo passe de invisível até ser completamente visível no tempo de 1 segundo;
* [FillTransition](http://docs.oracle.com/javafx/2/api/javafx/animation/FillTransition.html): Com a FillTransition nós podemos mudar a cor de preenchimento de um objetvo. Se um objeto tem o preenchimento com a cor azul e você usa a transição para mudar a cor de azul para rosa em um segundo, isso significa que a cor de preenchimento do mesmo irá iniciar totalmente azul e no espaço de tempo de 1 segundo irá se tornar completamente rosa, dando um efeito bastante interessante;
* [RotateTransition](http://docs.oracle.com/javafx/2/api/javafx/animation/RotateTransition.html): Uma das propriedades mais comuns em um nó é a **rotate** \(rotação\). A RotateTransition mudará a rotação de um objeto de acordo com o tempo configurado. Por exemplo, você pode rotacionar um objeto de 0º até 90º em 2 segundos e após iniciar essa tranisção, ele irá ter a rotação modificada gradualmente até que o tempo termine;
* [ScaleTransition](http://docs.oracle.com/javafx/2/api/javafx/animation/ScaleTransition.html): A ScaleTransition server para você modificar a escala de um objeto, seja a altura ou a largura. Em outras palavras, você pode fazer a escala de um objeto ser modificada de X para X^2  em um dado intervalo de tempo;
* [TranslateTransition](http://docs.oracle.com/javafx/2/api/javafx/animation/TranslateTransition.html): Similarmente ao que foi falado na introdução desse artigo, a TranslateTransition irá modificar a posição X ou Y de um objeto em um dado intervalo de tempo;
* [StrokeTransition](http://docs.oracle.com/javafx/2/api/javafx/animation/StrokeTransition.html): A StrokeTransition é muito similar à FillTransition, no entanto, nessa transição a modificação não é do preenchimento, mas sim da linha que contorna o mesmo;
* [PathTransition](http://docs.oracle.com/javafx/2/api/javafx/animation/PathTransition.html): Embora complexa, a PathTransition é muito útil, pois permite que muitas ações sejam tomadas de acordo com o objeto [Path](http://docs.oracle.com/javafx/2/api/javafx/scene/shape/Path.html)passado;

  A API do JavaFX também disponibiliza transições que permitem que várias transições sejam "tocadas" ao mesmo tempo, são elas:

* [SequentialTransition](http://docs.oracle.com/javafx/2/api/javafx/animation/SequentialTransition.html): A SequentialTransition server para adicionar diversas transições para serem "tocadas" de forma sequencial, ou seja, uma após a outra. Por exemplo, se adicionamos a transição t1, t2, t3 em uma SequentialTransition e tocar ela, t1 será ativada, em sequência será ativada t2 e por fim iremos fechar com t3; Se quisermos adicionar um tempo de espera entre as transições, podemos usar a [PauseTransition](http://docs.oracle.com/javafx/2/api/javafx/animation/PauseTransition.html), que é um tipo especial de transição cuja função é simplesmente "dar um tempo" antes da próxima transição da sequência;

* [ParallelTransition](http://docs.oracle.com/javafx/2/api/javafx/animation/ParallelTransition.html): A ParallelTransition atua de forma semelhante à SequentialTransition, mas toca todas as transições em paralelo, sendo que o tempo de duração será a da transição mais longa.

_**Lembre-se que todo componentes em uma aplicação JavaFX herda de \(é um\) nó e todos os nós tem propriedades em comum como rotação, posição X e Y, opacidade, etc;**_

### Transições na prática

Como muitos já devem ter imaginado, temos uma classe comum para todas as transições, a [classe Transition](http://docs.oracle.com/javafx/2/api/javafx/animation/Transition.html).  Abaixo temos uma explicação de seus principais métodos:

**play\(\)**: Começa a "tocar" a transição. Se a transição estava pausada antes, chamar esse método fará com que a transição volte a tocar do ponto onde tinha parada. Similar ao play, temos a **playFromStart, **que começa uma transição desde o começo e **playFrom\(Duration\),** onde podemos informar um ponto para a transição começar a tocar;

**pause\(\)**: Para a transição em um dado momento, sendo que ao chamar **play,** a transição irá começar no momento parado por ** pause;**

**stop\(\):** Para a transição por completo. A próxima vez que chamarmos **play** a transição irá iniciar do ponto inicial;

**setOnFinished\(EventHandler&lt;ActionEvent&gt;\):** Esse método é interessante! Com ele podemos informar uma ação que você deseja que seja feita assim que a transição termina de "tocar";

**setAutoReverse\(boolean value\):** Se chamarmos esse método e informar o valor **true** para **value,** iremos fazer com que a transição "volte", ou seja, se a transição for fazer um nó ir da posição X 0 para a posição 10, ao chamar **setAutoReverse\(true\),** o mesmo irá até a posição 10 e irá voltar para a posição 0 invés de parar na posição X 10;

**setNode\(Node\):** Aqui é onde falamos qual será nosso alvo ou o nó que será manipulado por essa transição;

** Outros métodos:** Há outros métodos e atributos interessantes\(acessados através de métodos\) que você queria dar uma olhada. Para isso veja a documentação da classe que Transition veio, a [Animation](http://docs.oracle.com/javafx/2/api/javafx/animation/Animation.html).

### Exemplo de uso

Vamos agora explorar uma pequena aplicação que irá mostrar as funcionalidades das principais transições. Você pode encontrar o código da classe no arquivo AprendendoTransicoes.java. Veja nossa aplicação de exemplo:

![](/imagens/telas/appTransicoes.png)

Para criar as transições, usamos uma classe chamada **FabricaTransicao.**.  Nessa classe há um método chamado _fazerTransicao_ que, dado um [enum](http://docs.oracle.com/javase/tutorial/java/javaOO/enum.html), duração e um nó, sempre cria uma nova transição:

```java
public static class FabricaTransicao {

    public static enum Transicoes {
        FADE, TRANSLATE, SCALE, FILL, ROTATE
    }

    public static Transition fazerTransicao(Transicoes transicao, double duracaoSegundos, Node alvo) {
        Duration duracao = new Duration(duracaoSegundos * 1000);
        Transition t = null;

        switch (transicao) {
        case FADE:
            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.setDuration(duracao);
            fadeTransition.setNode(alvo);
            t = fadeTransition;
            break;
        case FILL:
            FillTransition fillTransition = new FillTransition();
            fillTransition.setFromValue(Color.RED);
            fillTransition.setToValue(Color.DARKGREEN);
            fillTransition.setDuration(duracao);
            fillTransition.setShape((Shape) alvo);
            t = fillTransition;
            break;
        case ROTATE:
            RotateTransition rotateTransition = new RotateTransition();
            rotateTransition.setByAngle(360);
            rotateTransition.setDuration(duracao);
            rotateTransition.setNode(alvo);
            t = rotateTransition;
            break;
        case SCALE:
            ScaleTransition scaleTransition = new ScaleTransition();
            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(4);
            scaleTransition.setToY(4);
            scaleTransition.setDuration(duracao);
            scaleTransition.setNode(alvo);
            t = scaleTransition;
            break;
        case TRANSLATE:
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setToX(600);
            translateTransition.setToY(250);
            translateTransition.setDuration(duracao);
            translateTransition.setNode(alvo);
            t = translateTransition;
            break;
        }
        t.setAutoReverse(true);
        t.setCycleCount(2);
        return t;
    }
}
```

Isso obviamente não é "bonito", mas esse código não utiliza as melhores práticas de codificação, ele é somente para demonstrar as transições. As 5 transições aí criadas mostram como usamos os métodos mais básicos da transição. Primeiramente temos um atributo que é do tipo **Transition**, ou seja, uma classe abstrata, então de acordo com o valor do enum criamos uma transição concreta e atribuimos à [transição abstrata](http://docs.oracle.com/javafx/2/api/javafx/animation/Transition.html).

Note a repetição de uso dos métodos _setNode_ e _setDuration_. Esses métodos estão em quase todas as transições, mas não em todos, por isso eles não puderam ser definidos na [classe abstrata](http://docs.oracle.com/javase/tutorial/java/IandI/abstract.html). já nas seguintes duas linhas em destaque, temos dois métodos que são da classe abstrata\(_setAutoReverse_ e _setCycleCount_\), logo não precisamos repetir a chamada do mesmo para cada transição criada.Esses dois métodos simplesmente irão fazer com que a transição mude o atributo do nó e volte para o valor original. Para isso precisamos ter dois ciclos \(ciclos são quantas vezes a transição será tocada\).

Os botões na parte acima da aplicação são do tipo "ToggleButton" e são parte de um grupo. Esse grupo é gerado baseado no seguinte Enum que você pode ver declarado na classe **FabricaTransicao** mostrado acima. O Enum contém as transições que a Fábrica suporta e de acordo com os valores dele, a gente gera os botões. Veja o código abaixo:

```java
private HBox criaPainelSuperior() {
    HBox hbTopo = new HBox(10);
    hbTopo.setSpacing(10);
    hbTopo.setAlignment(Pos.CENTER);
    Transicoes[] transicoes = Transicoes.values();
    // grupo para todas as transições
    botoesTransicao = new ToggleGroup();
    Stream.of(transicoes).map(t -> {
        ToggleButton tb = new ToggleButton(t.name());
        tb.setUserData(t);
        tb.setToggleGroup(botoesTransicao);
        return tb;
    }).forEach(hbTopo.getChildren()::add);
    botoesTransicao.getToggles().get(0).setSelected(true);
    return hbTopo;
}
```

Perceba que através do método _setUserData_ nós adicionamos a cada botão o valor do enum que esse botão representa e usamos esse valor para fabricar nossa transição.  Quando você clica no botão "Tocar", a "action" do mesmo será usar a fábrica para produzir uma transição de acordo com o botão selecionado, configurar o comportamento dos botões para que os mesmos não fiquem ativos quando a transição estiver tocando\(ou que fiquem ativos só quando a transição estiver tocando\). Veja a ação do botão Tocar:

```java
btnTocar.setOnAction(e -> acaoTocar(btnParar, btnTocar, btnPausar, btnAjusta));
// mais
private void acaoTocar(final Button btnParar, final Button btnTocar, final Button btnPausar,
        final Button btnAjusta) {
    // antes de tocar, pegamos a mais nova transição selecionada
    Transicoes t = (Transicoes) botoesTransicao.getSelectedToggle().getUserData();
    transicaoAtual = FabricaTransicao.fazerTransicao(t, sldTempo.getValue(), alvo);
    // lógicas de habilitação dos botões, temos que setar todas as
    // vezes pq trocamos as transições
    btnParar.disableProperty().bind(transicaoAtual.statusProperty().isNotEqualTo(Status.RUNNING));
    btnTocar.disableProperty().bind(transicaoAtual.statusProperty().isEqualTo(Status.RUNNING));
    btnPausar.disableProperty().bind(transicaoAtual.statusProperty().isNotEqualTo(Status.RUNNING));
    btnAjusta.disableProperty().bind(transicaoAtual.statusProperty().isEqualTo(Status.RUNNING));
    sldTempo.disableProperty().bind(transicaoAtual.statusProperty().isEqualTo(Status.RUNNING));
    System.out.println("Tocando transição " + t);
    transicaoAtual.play();
}
```

Os outros botões irão controlar a transição \(parar e pausar\) e um serve para "ajustar" o texto quando a transição é parada no meio. Veja o método que é chamado quando clicamos nesse botão:

```java
private void criaNoAlvo() {
 // configurar coisas do texto alvo...
 alvo = new Text("** Transições **");
 alvo.setFont(new Font(60));
 // efeitinsss
 Reflection efeito = new Reflection();
 efeito.setFraction(0.7);
 alvo.setEffect(efeito);
 alvo.setFill(Color.RED);
 raiz.setCenter(alvo);
}
```

O "slider" no canto direito será usado para que possamos selecionar o tempo total da transição.  Ao criar a transição, pegamos o valor dele e enviamos para o método de criação. Veja a criação e o uso do slider:

```java
// criando o slider
sldTempo = new Slider(1, 10, 5);
//... criando a transição
transicaoAtual = FabricaTransicao.fazerTransicao(t, sldTempo.getValue(), alvo);
```

Terminamos aqui nossa seção sobre transições. Lembrando que a aplicação discutida acima foi demonstrada em um [vídeo no youtube.](https://www.youtube.com/watch?v=x3F1R-kDMEQ)

