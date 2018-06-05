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

import Controller.ControllerCroupier;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Objects;

/**
*@author Mateus Miranda 
*/

/**
 * Essa classe serve para manipulação de arquivos.
 */

public class Arquivo{
    private File arquivoTexto;
    private Jogador[] list;
    
    /**
     * Esse método recebe como parâmetro um objeto do tipo jogador e um int.
     * Ele armazena o objeto jogador como arquivo binário e utiliza o int
     * para diferenciar se a função que o chama é para registro ou login.
     * @param jogador Jogador - Objeto a ser armazenado.
     * @param n int - Inteiro que serve para diferenciar um login de um registro.
     */
    public void arquivoJogadorSerializar (Jogador jogador, int n){
        try{
            FileOutputStream arquivar 
            = new FileOutputStream("Jogadores/" +jogador.getUser() + ".jogador");
            ObjectOutputStream objArquivado = new ObjectOutputStream(arquivar);
            objArquivado.writeObject((Jogador)jogador);
            objArquivado.flush();
            objArquivado.close();
            arquivar.close();
            if(n==0){
                System.out.println("\nJogador cadastrado.\n");
            }
        }catch(Exception i){
            i.printStackTrace();
        }   
    }
    
    /**
     * Esse método procura um objeto do tipo Jogador, localizado em um diretório específico,
     * a partir da String user do objeto parâmetro. Achando esse o objeto, retorna o próprio,
     * caso não ache, o retorno é nulo.
     * @param jogador Jogador - Objeto em arquivo binário a ser buscado localizado
     * em um diretório específico.
     * @return Jogador - Objeto achado ou nulo. 
     */
    public Jogador arquivoJogadorDesserializar(Jogador jogador){
          try
          {
             FileInputStream fileIn = new FileInputStream("Jogadores/" + jogador.getUser() + ".jogador");
             ObjectInputStream in = new ObjectInputStream(fileIn);
             jogador = (Jogador) in.readObject();
             in.close();
             fileIn.close();
          }catch(IOException | ClassNotFoundException i)
          {
             return null;
          }
          jogador.zerarMao();
          
          return jogador;
    }
    
    /**
     * Este método recebe um objeto do tipo ControllerCroupier como parâmetro para que este possa 
     * ser amarzenado em arquivo binário. O baralho do jogo é criado dentro da classe partida
     * e depois é igualado ao baralho do croupier na mesma classe partida.
     * @param croupier ControllerCroupier - Objeto a ser armazenado em arquivo binário.
     */
    public void arquivoBaralhoSerializar(ControllerCroupier croupier){
        try{
            FileOutputStream arquivar 
            = new FileOutputStream("Baralho/baralho.arquivo");
            ObjectOutputStream objArquivado = new ObjectOutputStream(arquivar);
            objArquivado.writeObject((ControllerCroupier)croupier);
            objArquivado.flush();
            objArquivado.close();
            arquivar.close();
        }catch(IOException i){
            System.out.println("Erro.\n");
        } 
    }
    
    /**
     * Esse método retorna o croupier do último jogo que, consequentemente,
     * possui o baralho do último jogo.
     * @return ControllerCroupier - Objeto com o baralho do último jogo.
     */
    public ControllerCroupier arquivoBaralhoDesserializar(){
        try
          {
             FileInputStream fileIn = new FileInputStream("Baralho/baralho.arquivo");
             ObjectInputStream in = new ObjectInputStream(fileIn);
             ControllerCroupier croupier = (ControllerCroupier) in.readObject();
             in.close();
             fileIn.close();
             return croupier;
          }catch(IOException | ClassNotFoundException i)
          {
              System.out.println("\nO baralho não foi encontrado.\n");
              return null;
          }
    }
    
    /**
     * Este método atualiza e organiza o placar em um arquivo texto. A ordem dos
     * usuários é definida pelas maiores pontuações gerais, ordenada decrescentemente.
     * Depois de ordenado, cada elemento (user,jogos vencidos e pontuação geral)
     * do array de jogadores é escrito no arquivo texto. Sendo que a cada rodada
     * o arquivo texto é deletado e criado novamente de forma atualizada.
     */
    public void atualizarPlacar(){
        arquivoTexto = new File("Placar/placar.txt");
        arquivoTexto.delete();
        File dir = new File("Jogadores/");
        Arquivo arquivo =  new Arquivo();
        String[] jogadores;
        String[] jogador;
        jogadores = dir.list();
        list = new Jogador[jogadores.length];
        
        for (int i = 0; i<jogadores.length; i++) {
            jogador = jogadores[i].split("[.]");
            Jogador player = new Jogador();
            player.setUser(jogador[0]);
            list[i] = player = arquivo.arquivoJogadorDesserializar(player);
        }
        
        list = arquivo.selectSort(list);
        
        try{
            for(int i=0; i <list.length; i++){
                BufferedWriter escrever = new BufferedWriter(new FileWriter(arquivoTexto, true));
                if(arquivoTexto.length()>0){
                    escrever.newLine();                    
                    escrever.append(list[i].getUser()+", "+list[i].getJogosVencidos()+", "+list[i].getPontuacaoGeral());
                    escrever.close();
                }
                else{
                    escrever.write(list[i].getUser()+", "+list[i].getJogosVencidos()+", "+list[i].getPontuacaoGeral());
                    escrever.close();
                }
            }
        }catch(IOException ex){
            System.out.println("Erro.\n");
        }
    }
    
    /**
     * Este método imprime na tela o conteúdo presente no arquivo de texto criado no
     * método atualizarPlacar da classe Arquivo.
     */
    public void imprimirPlacar(){
        System.out.println("\n");
        for(Jogador jogador:list){
            System.out.println(jogador.getUser()+", "+jogador.getJogosVencidos()+", "+jogador.getPontuacaoGeral());
        }
    }
    
    /**
     * Este método é um método de organização que tem como critério a pontuação
     * geral do objeto jogador, organizando de forma decrescente.
     * @param jogadores Jogador[]-Array a ser organizada.
     * @return Jogador[]-Array organizada.
     */
    public Jogador[] selectSort(Jogador[] jogadores){
        for(int i=0; i<jogadores.length; i++){ 
            int minIndex = i; 
            for(int j=i+1; j<jogadores.length; j++){
                if(jogadores[j].getPontuacaoGeral()>jogadores[minIndex].getPontuacaoGeral()){ 
                    minIndex= j;  
                } 
            } 
            Jogador jogador = jogadores[i];
            jogadores[i] = jogadores[minIndex];
            jogadores[minIndex] = jogador;
        }
        return jogadores;
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
        final Arquivo other = (Arquivo) obj;
        if (!Objects.equals(this.arquivoTexto, other.arquivoTexto)) {
            return false;
        }
        return Arrays.deepEquals(this.list, other.list);
    }
    
    
}