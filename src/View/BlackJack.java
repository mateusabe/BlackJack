/*******************************************************************************
Autores: Mateus de Almeida Miranda e Tiago Moura
Componente Curricular: Mi-Programação
Concluido em: 29/05/2018
Declaramos que este código foi elaborado por nós de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package View;

import Model.Partida;

/**
 * Essa classe contém o main do programa.
 * @author Mateus Miranda
 * @author Tiago Moura
 */
public class BlackJack {

    /**
     * Esse é o main do programa.
     * @param args
     */
    public static void main(String[] args) {
        Partida partida = new Partida();
        partida.inicioPartida();
    }
}
