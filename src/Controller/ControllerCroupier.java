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
import java.io.Serializable;

/**
 * Essa classe serve para o objeto croupier possua um bom encapsulamento e para que 
 * a comunicação entre o jogo e o objeto sejam apenas através de métodos.
 * @author Mateus Miranda
 * @author Tiago Moura
 */
public class ControllerCroupier implements Serializable{  //A classe implementa serializable para que possa ser armazenada em arquivo binário.
    private Croupier croupier = new Croupier();
    private ControllerBaralho baralho = new ControllerBaralho();
    private int quantidadeDeCartas;
    private int cartasDistribuidas;
    
    /**
     * Esse método retorna o objeto do tipo ControllerBaralho da classe.
     * @return ControllerBaralho - Objeto do tipo ControllerBaralho da classe.
     */
    public ControllerBaralho getBaralho(){
        return baralho;
    }
     
    /**
     * Esse método retorna o objeto do tipo Croupier da classe.
     * @return Croupier - Objeto do tipo Croupier da classe.
     */
    public Croupier getCroupier(){
        return croupier;
    }
     
    /**
     * Esse método funciona como se fosse um 'cava' cartas. A cada vez que é chamado,
     * os valores da quantidade de cartas e de cartas dstribuídas sãoa atualizadas.
     * @see Croupier#getMao()  
     * @see MaoDeCarta#setCartas(Model.Carta) 
     * @see ControllerBaralho#distribuirCartas() 
     */
    public void receber(){
        while(croupier.getMao().getValorDaMao()<17){
            croupier.getMao().setCartas(getBaralho().distribuirCartas());
            quantidadeDeCartas++;
            cartasDistribuidas++;
        }
    }
     
    /**
     * Esse método serve para distribuir as cartas do baralho. A cada vez que ele
     * é chamado, o valor das cartas distribuídas é atualizado.
     * @return Carta - Carta do indice das cartas distribuidas do array de cartas.
     * @see ControllerBaralho#distribuirCartas() 
     */
    public Carta distribuir(){
        Carta carta = baralho.distribuirCartas();
        cartasDistribuidas++;
        return carta;
    }
    
    /**
     * Esse método imprime na tela uma carta do croupier e oculta a outra, 
     * assim como acontece no blackjack físico.
     * @see Croupier#getMao()
     * @see MaoDeCarta#getCartas() 
     */
    public void mostrarUmaCarta(){
        Carta[] cartas = croupier.getMao().getCartas();
        System.out.println(cartas[0].toString());
        System.out.println("Carta misteriosa.");
    }
    
    /**
     * Esse método imprime na tela todas as cartas do croupier.
     * @see Croupier#getMao()
     * @see MaoDeCarta#getCartas() 
     */
    public void mostrarCartas(){
        Carta[] cartas = croupier.getMao().getCartas();
        for(int i=0; i<quantidadeDeCartas;i++)
            System.out.println(cartas[i].toString());
    }
    
    /**
     * Esse método retorna o valor da mao de cartas do croupier.
     * @return int - Valor da mao de cartas do croupier.
     * @see #getCroupier() 
     * @see Croupier#getMao() 
     * @see MaoDeCarta#getValorDaMao() 
     */
    public int getValorDaMaoCroupier(){
        return getCroupier().getMao().getValorDaMao();
    }
    
}
