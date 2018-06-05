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
import java.util.Objects;

/**
 * Esta classe representa o objeto jogador.
 * @author Mateus Miranda
 * @author Tiago Moura
 */
public class Jogador implements Serializable{ //A classe implementa serializable para que possa ser armazenada em arquivo binário.
    private MaoDeCarta mao = new MaoDeCarta();
    private String user;
    private String senha;
    private int jogosVencidos;
    private int pontuacaoGeral;

    /**
     * Esse método retorna o user do jogador.
     * @return String - User do jogador.
     */
    public String getUser() {
        return user;
    }

    /**
     * Esse método define o user do jogador.
     * @param user String - Nome do user.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Esse método retorna a senha do jogador.
     * @return String - Senha do jogador.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Esse método define a senha do jogador.
     * @param senha String - Senha do jogador.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Esse método retorna a quantidade de jogos vecidos pelo jogador.
     * @return int - Quantidade de jogos vencidos pelo jogador
     */
    public int getJogosVencidos() {
        return jogosVencidos;
    }

    /**
     * Esse método contabiliza as vitórias do jogador.
     */
    public void atualizarJogosVencidos() {
        jogosVencidos++;
    }
    
    /**
     * Esse método retorna a pontuação geral acumulado pelo jogador.
     * @return int - Pontuação geral do jogador.
     */
    public int getPontuacaoGeral() {
        return pontuacaoGeral;
    }

    /**
     * Esse método contabiliza a pontuação geral do jogdador somando sempre com
     * o valor da mão do mesmo com o qual o jogador venceu a partida.
     * @see MaoDeCarta#getValorDaMao() 
     */
    public void atualizarPontuacaoGeral() {
        pontuacaoGeral = pontuacaoGeral + mao.getValorDaMao();
    }
 
    /**
     * Esse método retorna a mão de cartas do jogador.
     * @return MaoDeCarta - Mao de cartas do jogador.
     */
    public MaoDeCarta getMao() {
        return mao;
    }
    
    /**
     * Esse método cria uma nova mão para o jogador a cada partida finalizada.
     */
    public void zerarMao(){
        mao = new MaoDeCarta();
    }
    
    /**
     * Método usado inteiramente para a realização dos testes de unidade.
     * Como é um método próprio da generalização dos objetos (Object), é um
     * polimorfismo por sobrescrita. 
     * @deprecated 
     * @param n int - Valor a ser somado com a pontuação geral.
     */ 
    public void setPontucaoGeral(int n){
        pontuacaoGeral = pontuacaoGeral + n;
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
        final Jogador other = (Jogador) obj;
        if (this.jogosVencidos != other.jogosVencidos) {
            return false;
        }
        if (this.pontuacaoGeral != other.pontuacaoGeral) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.mao, other.mao)) {
            return false;
        }
        return true;
    }    
}
