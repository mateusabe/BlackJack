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

/**
 * Essa classe representa a mão de cartas do jogador e do croupier.
 * @author Mateus Mirando
 * @author Tiago Moura
 */


public class MaoDeCarta implements Serializable{  //A classe implementa serializable para que possa ser armazenada em arquivo binário.
    private Carta[] cartas = new Carta[11];
    private int quantidadeDeCartas;
    int valorTotal;

    /**
     * Esse método retorna o array de cartas da classe.
     * @return Carta[] - Cartas da mão de cartas.
     */
    public Carta[] getCartas() {
        return cartas;
    }
    
    /**
     * Esse método existe apenas para realização de testes de unidade.
     * @deprecated 
     * @param n - Valor a ser somado com o valor total.
     */
    public void setValorTotal(int n){
        valorTotal = valorTotal + n;
    }
    
    /**
     * Esse método retorna o valor da mão de cartas.
     * @return int - Valor das cartas somadas.
     */
    public int getValorDaMao(){
        return valorTotal;
    }
    
    /**
     * Esse método atualiza o valor da mão de cartas cada vez que o jogador/
     * recebe uma carta. Ele também verifica se há um ás na mão para que o valor 
     * do mesmo possa ser modificado de forma que seja mais vantajoso para o jogador.
     * @see #as() 
     */
    public void valorDaMao(){
        as();
        valorTotal = valorTotal + cartas[quantidadeDeCartas].getValor();
    }
    
    /**
     * Esse método verifica se há um as na mao de jogador e determina qual valor
     * esse as deve assumir para que seja mais vantajoso para o jogador.
     */
    public void as(){
        int quantidadeDeAs = 0;
        for(int i=0; i<quantidadeDeCartas+1; i++){
            if(cartas[i].getId()==1)
                quantidadeDeAs++;
            if(valorTotal<=10 && cartas[i].getId()==1){
                cartas[i].setValor(11);
            }
            else if(valorTotal<=10 && cartas[quantidadeDeCartas].getId()==1){
                cartas[quantidadeDeCartas].setValor(11);
            }
            else if(valorTotal<=10 && (valorTotal+cartas[quantidadeDeCartas].getValor())<21 
                    & cartas[quantidadeDeCartas].getId() == 1){
                cartas[quantidadeDeCartas].setValor(11);
            }
            else if(valorTotal>10 && valorTotal + 
                    cartas[quantidadeDeCartas].getValor()>21 && cartas[i].getId()==1 && 
                    cartas[i].getValor()==11){
                cartas[i].setValor(1);
                valorTotal = valorTotal - 10;
            }
        }
    }
    
    /**
     * Esse método armazena cartas no array da classe. Contabilizando a quantidade
     * de cartas sempre que é chamado.
     * @param carta Carta - Objeto armazenado no array da presente na classe.
     */
    public void setCartas(Carta carta) {
        cartas[quantidadeDeCartas] = carta;
        valorDaMao();
        quantidadeDeCartas++;
    }

    /**
     * Esse método retorna a quantidade de cartas na mão do jogador.
     * @return int - Quantidade de cartas na mão do jogador.
     */
    public int getQuantidadeDeCartas() {
        return quantidadeDeCartas;
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
        final MaoDeCarta other = (MaoDeCarta) obj;
        if (this.quantidadeDeCartas != other.quantidadeDeCartas) {
            return false;
        }
        if (this.valorTotal != other.valorTotal) {
            return false;
        }
        return Arrays.deepEquals(this.cartas, other.cartas);
    }
    
    
    
}
