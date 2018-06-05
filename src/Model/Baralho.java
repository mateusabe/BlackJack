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

package Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

/**
 * Esta classe representa o baralho utilizado no jogo.
 * @author Mateus Miranda
 * @author Tiago Moura
 */
public class Baralho implements Serializable{ //A classe implementa serializable para que possa ser armazenada em arquivo binário.
    private Carta[] cartas;

    /**
     * Este construtor cria as cartas do array de Carta da classe. As cartas inicialmente
     * estão ordenadas pelo naipe e id.
     * @param array Carta[] - Cartas do baralho.
     */
    public Baralho(Carta[] array) {
        int id = 1;
        int valor = 1;

        for (int i = 0; i < array.length; i++) {
            array[i] = new Carta();
            array[i].setId(id);
            array[i].setValor(valor);

            id++;
            valor++;

            if (i >= 0 & i <= 12) {
                array[i].setNaipe(1);
            } else if (i >= 13 & i <= 25) {
                array[i].setNaipe(2);
            } else if (i >= 26 & i <= 38) {
                array[i].setNaipe(3);
            } else {
                array[i].setNaipe(4);
            }
            
            if(array[i].getId() == 11||array[i].getId() == 12||array[i].getId() == 13)
                array[i].setValor(10);

            if (id > 13) {
                id = 1;
                valor = 1;
            }
        }
        cartas = array;
    }

    /**
     * Esse método utiliza um objeto do tipo Random para embaralhar as cartas do
     * baralho. 
     * @see Random#nextInt() 
     */
    public void embaralhar() {
        int cartasEmbaralhar = cartas.length - 1;
        Random embaralho = new Random();

        while (cartasEmbaralhar > 0) {
            int aux = embaralho.nextInt(cartasEmbaralhar);
            Carta temp = cartas[cartasEmbaralhar];
            cartas[cartasEmbaralhar] = cartas[aux];
            cartas[aux] = temp;
            cartasEmbaralhar--;
        }
    }
    
    /**
     * Esse método retorna o array de cartas do baralho.
     * @return Carta[] - Array de cartas do baralho.
     */
    public Carta[] getCartas() {
        return cartas;
    }
    
    /**
     * Esse método ordena um array de cartas, tendo como critérios de ordenação
     * os id e naipe da carta.
     * @param array Carta[] - Array a ser ordenado.
     * @return Carta[] - Array ordenado.
     * @see Carta#compareTo(java.lang.Object) 
     */
    public Carta[] SelectSort(Carta[] array){
        for(int i=0; i<array.length; i++){ 
            int minIndex = i; 
            for(int j=i+1; j<array.length; j++){
                if(array[j].compareTo(array[minIndex]) == -1){ 
                    minIndex= j;  
                } 
            } 
            Carta carta = array[i];
            array[i] = array[minIndex];
            array[minIndex] = carta;
        }
        return array;
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
        final Baralho other = (Baralho) obj;
        if (!Arrays.deepEquals(this.cartas, other.cartas)) {
            return false;
        }
        return true;
    }
    
    
}
