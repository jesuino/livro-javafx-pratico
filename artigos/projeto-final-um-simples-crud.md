### Um CRUD com JavaFX

Todo programador já fez\(ou vai fazer\) um [CRUD](https://pt.wikipedia.org/wiki/CRUD) na vida e é, no geral, a forma que aprendemos conceitos básicos de uma tecnologia. Nesse capítulo vamos mostrar um simples CRUD em JavaFX para explorar algumas características da tecnologia. O objetivo é exercitar o que aprendemos para podemos fechar o livro. Sim, senhores e senhoras, estamos chegando n final.

### Definição de CRUD

CRUD é uma sigla que vem das operações básicas que podemos fazer um algo armazenado em uma fonte de dados:

**C**reate: Criar uma nova instância de algo na fonte dos dados;

**R**etrieve: Trazer os dados já armazenados;

**U**pdate: Atualizar dados já armazenados;

**D**elete: Apagar.

### Onde armazenar os dados?

A fonte de dados pode ser um banco de dados\(MySQL, MariaDB, Postgres, etc\), um arquivo \(arquivos em diversos formatos, como CSV\), um Web Service \(que após chamado, salva os dados em algum lugar\) ou até mesmo a própria memória dinâmica do computador \(sendo que os dados se perdem quando a aplicação é fechada\). Aqui vamos usar um arquivo CSV, uma forma simples de armazenar objetos em um arquivo de texto.

O motivo de não usarmos um banco de dados tradicional é que o foco do artigo é mostrar JavaFX. Um banco de dados comum implica em termos que falar de SQL, conexão com o banco, select, etc, isso vai fugir do foco.

### Nosso CRUD

Nossa aplicação simples vai ser um CRUD de Contas. Sim, bills, contas! Pagamos todo mês contas de luz, água, gás, faturas, etc, etc, argh. Para representar a conta, criamos um objeto Java chamado Conta com três atributos: id \(gerado automaticamente para controle interno\), concessionária, descrição e data de vencimento. É isso, simples, não? Em breve mostramos o código dessa classe, mas agoras que conhecemos os campos, veja a telinha que modelamos usando o SceneBuilder que gerou um FXML \(se não sabe o que é isso, veja esse [artigo sobre FXML](http://aprendendo-javafx.blogspot.com.br/2014/03/introducao-ao-fxml-criando-interfaces.html), ou veja o capítulo Usando FXML\).

![](/imagens/telas/crud_app.png)

Começamos com uma [tabela](http://aprendendo-javafx.blogspot.com.br/2014/04/usando-tabelas-com-javafx-tableview.html) com três colunas representando os campos, após a tabela temos os campos de texto para entrada do nome, descrição e um campo para entrada de data do tipo [DatePicker](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/DatePicker.html), que não foi abordado nesse livro, e por fim os botões de ações.

A lógica da aplicação é a seguinte:

* A tabela tem ID **tblContas** e três colunas: **clConc**, **clDesc** e **clVenc**. Elas são populadas com os dados de um objeto do tipo Conta;
* Os campos de texto e o campo de data tem um \(**txtConc**, **txtDesc**, **dpVenc**\) serão injetados no controller para que possamos saber o valor que o usuário entrou;
* Cada um dos botões  ação:

  * **salvar**: salva o objeto de acordo com a informação entrada pelo usuário. Não está habilitado quando um campo está selecionado na tabela;
  * **atualizar:** Só está habilitado quando selecionamos uma linha da coluna e permite atualizar os dados dessa linha \(os campos de entrada de dados vão ser atualizados com o valor selecionado para serem modificados pelo usuário\);
  * **apagar**: apaga a linha selecionada;
  * **limpar**: limpa o campo selecionado atualmente.

  As operações e os elementos da tela ficam na classe **ContasController**. Código que veremos já já.

### **Fazendo as operações com o banco de dados**

As operações em sí com o banco ficam na interface ContasService. Ela contém os métodos **salvar**, que recebe uma instância de conta a ser salva, **atualizar, **  que recebe a conta já salva para ser atualizada, **apagar,** que apaga uma conta e **buscarTodos,** que retorna todas as contas selecionadas. É nessa classe que fazemos as operações.

Todo o código poderia ficar dentro do controller, MAS ISSO É COISA FEIA, temos que definir os métodos em uma interface e, vejam que interessante, usando a capacidade do Java 8 de definir métodos padrões, criamos um método **getInstance** para retornar a implementação que queremos dessa interface. Assim, criamos a clase ContasCSVService, que é uma implementação da interface, e retornamos uma nova instância nesse método! Podemos, obviamente, criar uma interface, por exemplo ** ContasBDService** que faria a mesma coisa, mas que invés de usar um arquivo CSV, se comunica com um banco de dados, a mesma ideia poderia ser aplicada para um arquivo XLS, como **ContasXLSService,** e por aí vai. O código do controller, que os métodos da interface, não iria sofrer nenhuma modificação, pois só precisaríamos trocar a intância de **ContasService** retornada no método **getNewInstance**! \(claro que com [CDI](http://aprendendo-javaee.blogspot.com.br/2012/09/primeiros-passos-com-cdi.html) e outras tecnologias, sequer criar manualmente precisaríamos\). Veja a nossa interface:

![](/imagens/telas/interface_crud.png)

### VAMOS AO CÓDIGO!

Agora, depois desse mooonnnntteee de papo, vamos ao código. Claro que se você leu acima com atenção, vai ser muito simples de entender tudo, mas mesmo assim o código está comentado na medida do possível.

Não vou colocar o todo código nesse capítulo, pois já está muito extenso, mas veja o código da classe Conta, da interface **ContasService** e da classe **ContasController**. Note que você pode encontrar o código no anexo **javafx-pratico.zip ** no pacote **javafxpratico.crud**.

```java
import java.util.Date;

/**
 * 
 * Nossa classe de modelo do objeto que "sofrerá" as operações de CRUD
 * @author wsiqueir
 *
 */
public class Conta {

    private int id;
    private String concessionaria;
    private String descricao;
    private Date dataVencimento;

    // gets e sets omitidos..

}
```

```java
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * 
 * O controller da aplicação, onde a mágica acontece
 * @author wsiqueir
 *
 */
public class ContasController implements Initializable {

    @FXML
    private TableView<Conta> tblContas;
    @FXML
    private TableColumn<Conta, String> clConsc;
    @FXML
    private TableColumn<Conta, String> clDesc;
    @FXML
    private TableColumn<Conta, Date> clVenc;
    @FXML
    private TextField txtConsc;
    @FXML
    private TextField txtDesc;
    @FXML
    private DatePicker dpVencimento;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnApagar;
    @FXML
    private Button btnLimpart;

    private ContasService service;

//     Esse método é chamado ao inicializar a aplicação, igual um construtor. 
// Ele vem da interface Initializable
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        service = ContasService.getNewInstance();
        configuraColunas();
        configuraBindings();
        atualizaDadosTabela();
    }

    // métodos públicos chamados quando o botão é clicado

    public void salvar() {
        Conta c = new Conta();
        pegaValores(c);
        service.salvar(c);
        atualizaDadosTabela();
    }

    public void atualizar() {
        Conta c = tblContas.getSelectionModel().getSelectedItem();
        pegaValores(c);
        service.atualizar(c);
        atualizaDadosTabela();
    }

    public void apagar() {
        Conta c = tblContas.getSelectionModel().getSelectedItem();
        service.apagar(c.getId());
        atualizaDadosTabela();
    }

    public void limpar() {
        tblContas.getSelectionModel().select(null);
        txtConsc.setText("");
        txtDesc.setText("");
        dpVencimento.setValue(null);
    }

    // métodos privados do controller

    // pega os valores entrados pelo usuário e adiciona no objeto conta
    private void pegaValores(Conta c) {
        c.setConcessionaria(txtConsc.getText());
        c.setDescricao(txtDesc.getText());
        c.setDataVencimento(dataSelecionada());
    }

    // método utilitário para pega a data que foi selecionada (que usa o tipo novo do java 8 LocalDateTime)
    private Date dataSelecionada() {
        LocalDateTime time = dpVencimento.getValue().atStartOfDay();
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    // chamado quando acontece alguma operação de atualização dos dados
    private void atualizaDadosTabela() {
        tblContas.getItems().setAll(service.buscarTodas());
        limpar();
    }

    // configura as colunas para mostrar as propriedades da classe Conta
    private void configuraColunas() {
        clConsc.setCellValueFactory(new PropertyValueFactory<>("concessionaria"));
        clDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        clVenc.setCellValueFactory(new PropertyValueFactory<>("dataVencimento"));
    }

    // configura a lógica da tela
    private void configuraBindings() {
        // esse binding só e false quando os campos da tela estão preenchidos
        BooleanBinding camposPreenchidos = txtConsc.textProperty().isEmpty()
                .or(txtDesc.textProperty().isEmpty())
                .or(dpVencimento.valueProperty().isNull());
        // indica se há algo selecionado na tabela
        BooleanBinding algoSelecionado = tblContas.getSelectionModel().selectedItemProperty().isNull();
        // alguns botões só são habilitados se algo foi selecionado na tabela
        btnApagar.disableProperty().bind(algoSelecionado);
        btnAtualizar.disableProperty().bind(algoSelecionado);
        btnLimpart.disableProperty().bind(algoSelecionado);
        // o botão salvar só é habilitado se as informações foram preenchidas e não tem nada na tela
        btnSalvar.disableProperty().bind(algoSelecionado.not().or(camposPreenchidos));
        // quando algo é selecionado na tabela, preenchemos os campos de entrada com os valores para o 
        // usuário editar
        tblContas.getSelectionModel().selectedItemProperty().addListener((b, o, n) -> {
            if (n != null) {
                LocalDate data = null;
                data = n.getDataVencimento().toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
                txtConsc.setText(n.getConcessionaria());
                txtDesc.setText(n.getDescricao());
                dpVencimento.setValue(data);
            }
        });
    }

}
```

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * Uma implementação do ContasService para lidar com arquivo CSV
 * @author wsiqueir
 *
 */
public class ContasCSVService implements ContasService {

    // divisor de colunas no arquivo
    private static final String SEPARADOR = ";";

    // o caminho para o arquivo deve ser selecionado aqui
    private static final Path ARQUIVO_SAIDA = Paths.get("./dados.csv");

    // os dados do arquivo
    private List<Conta> contas;

    // formato de data usado no arquivo
    final SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

    public ContasCSVService() {
        carregaDados();
    }

    @Override
    public void salvar(Conta conta) {
        conta.setId(ultimoId() + 1);
        contas.add(conta);
        salvaDados();
    }


    @Override
    public void atualizar(Conta conta) {
        Conta contaAntiga = buscaPorId(conta.getId());
        contaAntiga.setConcessionaria(conta.getConcessionaria());
        contaAntiga.setDataVencimento(conta.getDataVencimento());
        contaAntiga.setDescricao(conta.getDescricao());
        salvaDados();
    }

    @Override
    public List<Conta> buscarTodas() {
        return contas;
    }

    @Override
    public void apagar(int id) {
        Conta conta = buscaPorId(id);
        contas.remove(conta);
        salvaDados();
    }

    public Conta buscaPorId(int id) {
        return contas.stream().filter(c -> c.getId() == id).findFirst()
                .orElseThrow(() -> new Error("Conta não encontrada"));
    }

    // salva a lista de dados no arquivo, gerando um novo CSV e escrevendo o arquivo completamente
    private void salvaDados() {
        StringBuffer sb = new StringBuffer();
        for (Conta c : contas) {
            String linha = criaLinha(c);
            sb.append(linha);
            sb.append(System.getProperty("line.separator"));
        }
        try {
            Files.delete(ARQUIVO_SAIDA);
            Files.write(ARQUIVO_SAIDA, sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    // o ID mais alto é retornado aqui para continuarmos contando os IDs
    private int ultimoId() {
        return contas.stream().mapToInt(Conta::getId).max().orElse(0);
    }

    // carrega os dados do arquivo para a lista contas
    private void carregaDados() {
        try {
            if(!Files.exists(ARQUIVO_SAIDA)) {
                Files.createFile(ARQUIVO_SAIDA);
            }
            contas = Files.lines(ARQUIVO_SAIDA).map(this::leLinha).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    // transforma uma linha do CSV para o tipo Conta
    private Conta leLinha(String linha) {
        String colunas[] = linha.split(SEPARADOR);
        int id = Integer.parseInt(colunas[0]);
        Date dataVencimento = null;
        try {
            dataVencimento = formatoData.parse(colunas[3]);
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(0);
        }
        Conta conta = new Conta();
        conta.setId(id);
        conta.setConcessionaria(colunas[1]);
        conta.setDescricao(colunas[2]);
        conta.setDataVencimento(dataVencimento);
        return conta;
    }

    // transforma um objeto conta em um arquivo CSV
    private String criaLinha(Conta c) {
        String dataStr = formatoData.format(c.getDataVencimento());
        String idStr = String.valueOf(c.getId());
        String linha = String.join(SEPARADOR, idStr, c.getConcessionaria(), c.getDescricao(),
                dataStr);
        return linha;
    }

}
```

Esse foi nosso projeto de exemplo. Não há nada demais nele, não com o código.

