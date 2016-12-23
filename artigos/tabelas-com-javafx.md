###  Usando Tabelas com JavaFX: TableView

 Um dos controles mais utilizados em aplicações cotidianas é a Tabela. Com controles que permitam visualizar dados em forma de tabelas podemos mostrar: lista de funcionários, de produtos, dados já ordenados em planilhas, informações diversas que vêm de algum servidor.

 Nesse aspecto, estamos bem "servidos" com o [TableView](http://docs.oracle.com/javafx/2/api/javafx/scene/control/TableView.html) do JavaFX. A TableView, juntamente com a classe **TableColumn**, facilita visualizações de dados em uma aplicação JavaFX.

 Nesse capítulo iremos mostrar o básico sobre criação de tabelas usando JavaFX.  


###  Visão geral da classe TableView 

 TableView é mais um controle de interface. Para começarmos falando dela, veja a seguinte imagem:

![](/imagens/telas/tabelas.png)

Uma tabela tem colunas e linhas. Quando configuramos as colunas de uma [TableView](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableView.html), temos que também dizer como cada coluna vai representar os dados da tabela.

####  Itens

 Os dados devem ser do tipo da declaração da tabela. Por exemplo, se declaramos uma tabela usando:

**TableView&lt;Pessoa&gt; tabela = new TableView&lt;&gt;\(\);**

 A tabela só irá aceitar dados do tipo **Pessoa: **

**List&lt;Pessoa&gt; pessoas = ....**

**tabela.setItems\(FXCollections.observableArrayList\(pessoas\)\);**

 Caso você tente inserir dados de outro tipo, simplesmente você terá um **erro de compilação**.

####  Colunas

 As colunas são definidades pela classe [TableColumn](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableColumn.html) e devem saber lidar com cada linha da tabela. Isso é feito através de fábricas de células, que são classes que recebem cada item da tabela e retornam uma [célula da tabela](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableCell.html). A fábrica você deve fornecer ou usar algumas colunas que já sabem fabricar as células de um tipo já conhecido. Veja um exemplo onde criamos colunas para as propriedades nome, idade e email de uma pessoa:

```java
colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
colunaIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
```

###  Um exemplo completo de uso da TableView

 Abaixo temos um código simples que mostra como mostrar uma lista de pessoas em uma TableView. Veja que não é nada sofisticado, mas com esse código, você já estará apto a construir suas próprias aplicações que usam tabelas.

```java
package javafxpratico;

import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Tabela extends Application {
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) {
		List<Pessoa> pessoas = Arrays.asList(
				new Pessoa("William", 32, "william@email.com"),
				new Pessoa("Luana", 17, "luana@email.com"), 
				new Pessoa("Maria", 12, "maria@email.com"),
				new Pessoa("João", 15, "joao@email.com"), 
				new Pessoa("Antônio", 28, "antonio@email.com"),
				new Pessoa("Teles", 17, "teles@email.com"), 
				new Pessoa("Eduan", 30, "eduan@email.com"),
				new Pessoa("Gabu", 22, "gabu@email.com"));

		TableView<Pessoa> tabela = new TableView<>();
		TableColumn<Pessoa, String> colunaNome = new TableColumn<>("Nome");
		TableColumn<Pessoa, String> colunaIdade = new TableColumn<>("Idade");
		TableColumn<Pessoa, String> colunaEmail = new TableColumn<>("E-mail");

		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
		colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

		tabela.setItems(FXCollections.observableArrayList(pessoas));
		tabela.getColumns().addAll(colunaNome, colunaIdade, colunaEmail);
		primaryStage.setScene(new Scene(tabela));
		primaryStage.setWidth(250);
		primaryStage.setHeight(300);
		primaryStage.setTitle("Tabelas no JavaFX");
		primaryStage.show();
	}

	public static class Pessoa {

		private String nome;
		private int idade;
		private String email;

		public Pessoa(String nome, int idade, String email) {
			this.nome = nome;
			this.idade = idade;
			this.email = email;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public int getIdade() {
			return idade;
		}

		public void setIdade(int idade) {
			this.idade = idade;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

	}
}
```



