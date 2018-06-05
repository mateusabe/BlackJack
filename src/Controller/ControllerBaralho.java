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

package Controller;

import Model.Baralho;
import Model.Carta;
import java.io.Serializable;
import java.util.Objects;

/**
 * Essa classe serve para que o objeto baralho possua um bom encapsulamento e para que 
 * a comunicação entre o jogo e o objeto sejam apenas através de métodos.
 * @author Mateus Miranda
 * @author Tiago Moura
 */
public class ControllerBaralho implements Serializable{  //A classe implementa serializable para que possa ser armazenada em arquivo binário.
    private Baralho baralho;
    private int indiceCartasDistribuidas = -1;

    /**
     * Esse método retorna o índice da última carta distribúida.
     * @return int - Índice da última carta distribúida.
     */
    public int getIndiceCartasDistribuidas(){
        return indiceCartasDistribuidas;
    }
    
    /**
     * Esse método retorna o baralho dessa classe.
     * @return Baralho - Baralho dessa classe.
     */
    public Baralho getBaralho() {
        return baralho;
    }
    
    /**
     * Esse método inicializa o baralho com um array de Carta de 52 elementos (quantidade
     * de cartas de um baralho sem os corigas).
     * @see Baralho#Baralho(Model.Carta[]) 
     */
    public void criarBaralho(){
        Carta[] cartas = new Carta[52];
        baralho = new Baralho(cartas);
    }
    
    /**
     * Esse método embaralha as cartas do baralho da classe.
     * @see Baralho#embaralhar() 
     */
    public void embaralharBaralho(){
        baralho.embaralhar();   
    }
    
    /**
     * Esse método distribui as cartas do baralho da classe.
     * @return Carta - Carta do indice das cartas distribuidas do array de cartas.
     */
    public Carta distribuirCartas(){
        Carta[] cartas = baralho.getCartas();
        indiceCartasDistribuidas++;
        return cartas[indiceCartasDistribuidas];
    }
    
    /**
     * Método usado inteiramente para a realização dos testes de unidade.
     * Como é um método próprio da generalização dos objetos (Object), é um
     * polimorfismo por sobrescrita. 
     * @deprecated
     * @return boolean - Retorna verdadeiro caso os objetos sejam iguais ou falso
     * caso sejam diferentes.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ControllerBaralho other = (ControllerBaralho) obj;
        if (this.indiceCartasDistribuidas != other.indiceCartasDistribuidas) {
            return false;
        }
        if (!Objects.equals(this.baralho, other.baralho)) {
            return false;
        }
        return true;
    }
    
    
    
}
