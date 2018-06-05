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

/**
 * Essa classe representa as cartas usadas no jogo.
 * @author Mateus Miranda
 * @author Tiago Moura
 */
public class Carta implements Serializable, Comparable{  //A classe implementa serializable para que possa ser armazenada em arquivo binário.
    private int id;
    private int naipe;
    private int valor;

    /**
     * Esse método retorna o id da carta.
     * @return int - Id da carta.
     */
    public int getId() {
        return id;
    }

    /**
     * Esse método define o id da carta.
     * @param id int - Id da carta.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Esse método retorna o naipe da carta.
     * @return int - Naipe da carta.
     */
    public int getNaipe() {
        return naipe;
    }

    /**
     * Esse método define o naipe da carta.
     * @param naipe int - Naipe da carta.
     */
    public void setNaipe(int naipe) {
        this.naipe = naipe;
    }

    /**
     * Esse método retorna o valor da carta.
     * @return int - Valor da carta.
     */
    public int getValor() {
        return valor;
    }

    /**
     * Esse método define o valor da carta.
     * @param valor int - Valor da carta.
     */
    public void setValor(int valor) {
        this.valor = valor;
    }
    
    /**
     * Esse método modifica o toString() do objeto carta, para que a impressão
     * mostre somente o naipe (no formato ♣♦♠♥) e o id. Como é um método da 
     * generalização de objetos (Object), esse método caracteriza-se como um
     * polimorfismo por sobrescrita.
     * @return String - Carta.
     */
    @Override
    public String toString(){
        String nome;
        String naipeTodo;
        switch (this.id) {
            case 11:
                nome = "Valete";
                break;
            case 12:
                nome = "Rainha";
                break;
            case 13:
                nome = "Rei";
                break;
            case 1:
                nome = "Ás";
                break;
            default:
                nome = ""+this.valor;
                break;
        }
        switch (this.naipe) {
            case 1:
                naipeTodo = "♣";
                break;
            case 2:
                naipeTodo = "♠";
                break;
            case 3:
                naipeTodo = "♥";
                break;
            default:
                naipeTodo = "♦";
                break;
        }
        return nome +" de " + naipeTodo;
    }

    /**
     * Esse método modifica o compareTo() do objeto carta, para que a comparação
     * do mesmo seja feita a partir do naipe e id. Como é um método da 
     * generalização de objetos (Object), esse método caracteriza-se como um
     * polimorfismo por sobrescrita.
     * @param o - Objeto a ser comparado.
     * @return int - Valor que indica a relação (maior,menor ou igual) entre os 
     * objetos comparados.
     */
    @Override
    public int compareTo(Object o) {
        if(this.naipe<((Carta)o).naipe){
                return -1;
        }
        else if(this.naipe == ((Carta)o).naipe){
            if(this.id<((Carta)o).id)
                return -1;
            else if(this.id>((Carta)o).id){
                return 1;
            }
        }
        else if(this.naipe>((Carta)o).naipe)
                return 1;
        return 0;
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
        final Carta other = (Carta) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.naipe != other.naipe) {
            return false;
        }
        return this.valor == other.valor;
    }
    
    
}
