###  Introdução a gráficos com JavaFX

 Os desenvolvedores de aplicações desktop e applets que vieram antes do JavaFX tinham muito trabalho criando gráficos.

 JavaFX felizmente tem uma API pronta para criação de gráficos de diversos tipos. Nesse breve artigo vamos, através de exemplos, mostrar os gráficos mais simples do JavaFX.

###  A API de gráficos

 As classes da API de gráficos do JavaFX ficam no pacote [javafx.scene.chart](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/chart/package-summary.html). Para cada tipo de gráfico há uma classe \(por exemplo, **PieChart**\) e um tipo de objeto a ser populado com dados para serem exibidos nesse gráfico \(como **PieChart.Data**\).  Lembrando que, assim como todo componente em uma aplicação JavaFX, gráfico [herda de nó](http://aprendendo-javafx.blogspot.com.br/2013/10/a-classe-node-e-seus-principais.html).

 Os principais gráficos são mostrados na imagem abaixo retirada do [tutorial da Oracle](http://docs.oracle.com/javafx/2/charts/chart-overview.htm):![](/imagens/telas/chartsExemploOracle.png)

###  O gráfico de Pizza

 Esse é com certeza o gráfico mais fácil de usar :\) Como dito, cada gráfico tem o seu tipo de dados. Os tipos de dados do gráfico de pizza é o **PieChart.Data**  e esse tipo de dado contém uma String, que é o nome do dado, e um valor double, que é o valor desse dados. Os valores são somados e dividos pela quantidade de dados, assim descobrimos qual a fatia de cado um. Veja como é fácil criar um gráfico com dados fictícios de uma empresa com os ganhos de cada trimestre do anos:

```java
PieChart graficoPizza = new PieChart();
graficoPizza.getData().addAll(new PieChart.Data("Trimestre 1", 11),
  new PieChart.Data("Trimestre 2", 1),
  new PieChart.Data("Trimestre 3", 34),
  new PieChart.Data("Trimestre 5", 12));
graficoPizza.setTitle("Lucros por Trimestre");
graficoPizza.setPrefSize(GRAFICO_LARGURA, GRAFICO_ALTURA);
```

Viu! O que temos acima é:

* **PieChart:** Um nó que representa o gráfico a ser desenhado;
* **PieChart.Data**: os dados do gráfico;

 Por fim podemos adicionar o gráfico a uma cena e teremos o seguinte resultado:

![](/imagens/telas/graficoPizza.png)

###  O gráfico de linha

 O [gráfico de linha](http://docs.oracle.com/javafx/2/api/javafx/scene/chart/LineChart.html) é representado pela classe LineChart e não é dificil de ser usado também, no entanto, os dados já não são tão simples de criar. Ele é um subtipo de gráfico com plano cartesiano X e Y, ou seja,[ XYChart](http://docs.oracle.com/javafx/2/api/javafx/scene/chart/XYChart.html) e o tipo de dados para esse gráfico são o [XYChart.Series](http://docs.oracle.com/javafx/2/api/javafx/scene/chart/XYChart.Series.html). Mas o que vem a ser esse tipo de dados?

 Esse tipo de dado consiste principalmente de valores para X, valores para Y de um dado grupo de dados. Um exemplo seria comparar o lucro de cada produto de uma empresa ao longo do ano.

 O gráfico de linha, no entanto, também exige a definição dos eixos X e Y através da classe [Axis](http://docs.oracle.com/javafx/2/api/javafx/scene/chart/Axis.html).

 Bem, vejamos um exemplo de comparação de lucros por trimestre da empresa separado por produtos:

```java
LineChart graficoLinha = new LineChart<>(
  new CategoryAxis(), new NumberAxis());
final String T1 = "T1";
final String T2 = "T2";
final String T3 = "T3";
final String T4 = "T4";

XYChart.Series prod1 = new XYChart.Series();
prod1.setName("Produto 1");

prod1.getData().add(new XYChart.Data(T1, 5));
prod1.getData().add(new XYChart.Data(T2, -2));
prod1.getData().add(new XYChart.Data(T3, 3));
prod1.getData().add(new XYChart.Data(T4, 15));

XYChart.Series prod2 = new XYChart.Series();
prod2.setName("Produto 2");

prod2.getData().add(new XYChart.Data(T1, -5));
prod2.getData().add(new XYChart.Data(T2, -1));
prod2.getData().add(new XYChart.Data(T3, 12));
prod2.getData().add(new XYChart.Data(T4, 8));

XYChart.Series prod3 = new XYChart.Series();
prod3.setName("Produto 3");

prod3.getData().add(new XYChart.Data(T1, 12));
prod3.getData().add(new XYChart.Data(T2, 15));
prod3.getData().add(new XYChart.Data(T3, 12));
prod3.getData().add(new XYChart.Data(T4, 20));
graficoLinha.getData().addAll(prod1, prod2, prod3);
graficoLinha.setPrefSize(GRAFICO_LARGURA, GRAFICO_ALTURA);
```

O resultado desse código é o seguinte:

![](/imagens/telas/graficoLinha.png)

Notem que o código consiste de agrupamento de dados em tipo de categoria. No nosso caso agrupamos os dados do Produto 1 e a categoria foi determinada pelo trimestre.

###  O gráfico de Barras

 Muito similar ao gráfico de linha, temos o gráfico de barras. Esse gráfico usa o mesmo tipo de dados, mas representa de forma diferente: através de barras. Veja como o exemplo anterior, de linhas, usando o mesmo conjunto de dados, mas em um gráfico de barras:

```java
BarChart graficoBarra = new BarChart<>(
  new CategoryAxis(), new NumberAxis());
// igualzinho ao feito para o gráfico de linha...
graficoBarra.getData().addAll(prod1, prod2, prod3);
graficoBarra.setPrefSize(GRAFICO_LARGURA, GRAFICO_ALTURA);
```

![](/imagens/telas/graficoBarras.png)

Claro que a API é muito avançada. Nesse pequeno livro vamos abordar o básico e cabê ao leitor explorar as APIs mencionadas.

