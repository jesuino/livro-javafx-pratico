# Um "Olá Mundo" com JavaFX

Nesse primeiro artigo vamos mostrar um passo a passo de como executar sua primeira aplicação usando JavaFX! A versão usada é a que vem no Java 8. 

*Antes de prosseguir, confira se você instalou o JDK 8 da Oracle corretamente na sua máquina e também o Eclipse!*

Ótimo! Já temos tudo o que precisamos. O próximo passo agora é abrir o Eclipse e criar um projeto do tipo **Maven Project**. Nesse projeto iremos criar um pacote e nele uma classe Java. Veja abaixo uma explicação mais detalhada (nomes em negrito e itálico entre parêntesis foram os que eu usei):

1. Acesse o menu ***File -> New -> Maven Project***, marque a opção "Create a simple project (skip archetype selection)", preencha os valores para **groupId**(***org.javafxpratico***), **artifact-id**(***javafx-pratico***), **version**(***1.0***)  e clique em **Finish**;
2. Clique com o botão direto sobre o seu projeto e acesse o menu ***New -> Package***, dê um nome (***javafxpratico***) e em seguida clique em **Finish**;
3. Clique com o botão direito no pacote main e acesse o menu ***New -> Class***. Dê um nome para a classe (eu usei ***OlaMundoJavaFX***) e clique em **Finish**.


Pronto! É nesse mesmo projeto que iremos explorar o JavaFX. O código da classe OlaMundoJavaFX deve se parecer com o seguinte:

