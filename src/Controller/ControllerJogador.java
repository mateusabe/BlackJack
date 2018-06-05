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

import Model.*;

/**
 * Essa classe serve para que o objeto jogador possua um bom encapsulamento e 
 * para que a comunicação entre o jogo e o objeto sejam apenas através de métodos.
 * @author Mateus Miranda
 * @author Tiago Moura
 */
public class ControllerJogador {
    private ControllerCroupier croupier;
    private Jogador jogador = new Jogador();
    private int quantidadeDeCartas;
    private int stand;
    
    /**
     * Esse método define o croupier do jogador.
     * @param croupier - Croupier do jogador.
     */
    public void setCroupier(ControllerCroupier croupier){
        this.croupier = croupier;
    }
    
    /**
     * Esse método funciona como um 'cavar' cartas. Ele pega as cartas que estão
     * no baralho do croupier.
     */
    public void hit(){
        if(quantidadeDeCartas == 0 || quantidadeDeCartas == 1 ){
            for(int i=0; i<2; i++){
                jogador.getMao().setCartas(croupier.distribuir());
                croupier.receber();
                quantidadeDeCartas++;
            }
            return;
        }
        jogador.getMao().setCartas(croupier.distribuir());
        quantidadeDeCartas++;
    }
    
    /**
     * Esse método imprime na tela todas as cartas do jogador.
     */
    public void mostrarCartas(){
        Carta[] cartas = jogador.getMao().getCartas();
        for(int i=0; i<quantidadeDeCartas;i++)
            System.out.println(cartas[i].toString());
    }
    
    /**
     * Esse método retorna o inteiro stand, que indica se o jogador já parou de
     * pedir cartas.
     * @return int - Indica se o jogador já parou de pedir cartas.
     */
    public int getStand(){
        return stand;
    }
    
    /**
     * Esse método retorna o objeto do tipo jogador da classe.
     * @return Jogador - Objeto do tipo jogador da classe.
     */
    public Jogador getJogador(){
        return jogador;
    }
    
    /**
     * Esse método define o jogador da classe.
     * @param jogador Jogador - Jogador da classe.
     */
    public void setJogador(Jogador jogador){
        this.jogador = jogador;
    }
    
    /**
     * Esse método retorna o valor da mao de cartas do jogador.
     * @return int - Valor da mao de cartas do jogador.
     * @see Jogador#getMao() 
     * @see MaoDeCarta#getValorDaMao() 
     */
    public int getValorDaMaoJogador(){
        return jogador.getMao().getValorDaMao();
    }
    
    /**
     * Esse método reinicializa o croupier da classe e zera a quantidade de cartas
     * e o valor da variável stand no final de cada partida.
     */
    public void zerarJogador(){
       quantidadeDeCartas = 0;
       croupier = new ControllerCroupier();
       stand = 0;
    }
    
    /**
     * Esse método define o valor de stand como 2.
     */
    public void jogadorStand(){
        stand = 2;
    }
    
    /**
     * Esse método retorna se é verdadeiro ou falso que o valor da mao do jogador
     * é menor que 21.
     * @return Boolean - Se é verdadeiro ou falso que o valor da mao do jogador 
     * é menor que 21.
     * @see Jogador#getMao() 
     * @see MaoDeCarta#getValorDaMao() 
     */
    public boolean limiteValorDeMao(){
        return jogador.getMao().getValorDaMao() < 21;
    }
}
