# Mostrando Imagens e Figuras Geométricas


### Mostrando Imagens

Um dos ponto mais interessantes do JavaFX é como ele facilitou a criação de aplicações, assim podemos fazer algumas coisas antes dificeis da forma mais fácil possível.

Um exemplo é carregar imagens na aplicação. Temos somente dois passos a serem seguidos nosse caso:

* **Carregar a imagem**: Para isso usamos a classe **javafx.scene.image.Image**. Perceba que essa classe é uma representação alto nível de uma imagem, sendo que a mesma pode vir de um **java.io.InputStream**, um arquivo do seu computador e até mesmo um endereço da internet, sem você se preocupar com qualquer outra coisa;
* **Mostrar a imagem**: Visualizamos a imagem através da classe **javafx.scene.image.ImageView**. Repetimos o mesmo discurso para dizer que ImageView é um Node e é ele que adicionamos à nossa aplicação, aplicamos efeitos, animações etc (calma, iremos mostrar tudo isso em breve).
Após saber disso, você entenderá perfeitamente que com três linhas de código podemos ter uma imagem em uma aplicação JavaFX. Mas código só daqui a pouco :)

### Forma Geométricas
Todas as formas geométricas do JavaFX, adivinhe, também são Nodes e podem sofrer efeitos, transformações e animações, além de ter diversas propriedades em comum. Você pode ver as figuras geométricas oferecidas pelo JavaFX no pacote javafx.scene.shape. Todas as figuras geométricas herdam de Shape (que por sua vez herda de... Node), portanto temos propriedades comuns só para as figuras geométricas, entre as quais destacamos:
fill: O preenchimento da figura geométrica. Esse é um parâmetro do tipo Paint, que representa ou uma cor simples(exato: azul, verde, rosa...) ou um gradiente complexo;
stroke: Também do tipo Paint, mas se aplica a linha que envolve a forma geométrica;
strokeWidth: A largura da "stroke".