# Controles de interface: Rótulos, campos de texto e outros

Fazendo jus ao seu principal objetivo, JavaFX oferece muitos controles de interface entre simples rótulos para mostrar texto até complexas tabelas com milhares de registros. Nesse artigo vamos mostrar os controles mais básicos de interface que JavaFX oferece.

## Controles de interface

Todos os controles que o JavaFX oferece herdam da classe **Control**. Como alguns já devem ter deduzido, **Control** vai herdar em algum momento de **Node**, mas nesse caso ele antes herda de **Parent**.

```
java.lang.Object

  javafx.scene.Node

    javafx.scene.Parent

      javafx.scene.control.Control
```

Logos todos os controles usufruem com atributos como _height_, _maxHeight_, _width_, _minWidth_, entre outros.

Os controles são muito fáceis de lidar, no entanto, muitos tem suas características especifícas, o que leva a uma complicação maior somente para um capítulo, então iremos separar a abordagem dos controles em vários, nesse iremos abordar "Rótulos, campos de texto,  separadores e controles deslizantes".

## Rótulos, campos de texto,  separadores e controles deslizantes

Rótulos, campos de texto,  separadores e controles deslizantes são os controles mais simples possíveis, deixando para capítulos futuros outros controles que exigem tratamento de evento ou maior conhecimento prévio. Não vamos perder mais tempo e vamos para o código em seguida uma explicação!

```java
package main;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControlesSimples extends Application {

 public static void main(String[] args) {
  launch();
 }

 @Override
 public void start(Stage palco) throws Exception {
  VBox raiz = new VBox(10); // 1
  raiz.setAlignment(Pos.CENTER); // 2

  Label rotuloDemo = new Label("Sou um rótulo de texto!"); // 3
  rotuloDemo.setTooltip(new Tooltip(
    "Esse é um rótulo para mostrar textos de forma simples")); // 4

  TextField campoTexto = new TextField("Digite algo"); // 5
  campoTexto.setTooltip(new Tooltip(
    "Campo de texto para entrada de uma só linha "));

  TextArea areaTexto = new TextArea("Digite algo com várias linhas"); // 6
  areaTexto.setTooltip(new Tooltip(
    "Campo de texto para entrada de múltiplas linhas"));

  Separator separadorHorizontal = new Separator(); // 7
  Separator separadorVertical = new Separator(Orientation.VERTICAL); // 8
  Slider deslizante = new Slider(); // 9
  deslizante.setShowTickLabels(true); // 10
  deslizante.setShowTickMarks(true); // 11
  deslizante
    .setTooltip(new Tooltip(
      "O controle deslizante tem um valor numérico de acordo com sua posição"));

  raiz.getChildren().addAll(rotuloDemo, campoTexto, areaTexto,
    separadorVertical, separadorHorizontal, deslizante);

  Scene cena = new Scene(raiz, 300, 400);
  palco.setTitle("Controles Básicos I");
  palco.setScene(cena);
  palco.show();
 }
}
```

1. Nessa linha introduzimos um novo componente pai, ou seja, um componente que podemos colocar outros dentro dele. Nesse caso a **VBox**, que irá organizar os componentes verticalmente. O construtor informa o espaçamento entre os componentes, no nosso caso 10;
2. Você também pode determinar qual o posicionamento dos componentes dentro da VBox. Você usa o enum Pos para informar qual posicionamento quer. Nessa linha, informamos o posicionamento CENTER, logo todos os componentes serão posicionados no centro da nossa **VBox**;
3. Esse é nosso primeiro controle de interface apresentado um **Label**, ou o rótulo. Esse componente nada mais é do que um texto estático, semelhante ao **Text**, mas herda de Control, o que significa que ele é um controle de interface, no entanto, o usuário não muda o texto do Label diretamente. Como você já deve ter deduzido, o Label é um **Node**, assim como todos os controles e assim como e qualquer componente dentro da Cena do JavaFX. Pense quão poderoso esse detalhe arquitetural é!
4. Os controles tem algumas características em comum e uma delas é a possibilidade de informar um **Tooltip**, que nada mais é um texto demostrativo informado quando mantemos o mouse sobre o componente;
5. Nessa linha criamos um campo de texto, o TextField. Esse campo, ao contrário do Label, permite a modificação do usuário diretamente. Futuramente você pode recuperar esse texto através do método _getText\(\)_;
6. Semelhante ao campo de texto, temos o uma área de texto. Não há muita diferença entre essas duas classes, na verdade a maior diferença é que o **TextArea** permite a entrada de várias linhas e também contém propriedades para trabalhar com essa característica. Detalhe que a **TextArea** e o **TextField** herdam de **TextInputControl**;
7. Nessa linha mostramos um separador horizontal. Ele é sem graça assim mesmo, só serve para separar as coisas. No entanto, lembre-se que ele é um... **Node** e também um controle;
8. Aqui também criamos um separador, mas esse fica na vertical conforme informado pelo Enum **Orientation**;
9. O controle deslizando, ou **Slider**, permite você escolher um valor numérico dentro de uma faixa determinada. De acordo com a posição do controle, você terá um valor numérico que pode ser recuperado depois através do método _getValue\(\)_;
10. Se você quiser que o controle deslizante mostre marcas para indicação relativa de qual valor o Slider tem no momento, você deve chamar o método _setShowTickLabels_ que vai configurar se devem aparecer essas marcas, ou não \(true, false\);
11. Na linha anterior, mostramos como colocar marcas, nessa linha chamamos o método _setShowTickMarks_ e informamos que queremos ver números que indicam o valor do controle deslizante.

Finalmente, olhe como ficou nossa aplicação:

![](/imagens/telas/controlesBasicos1)

