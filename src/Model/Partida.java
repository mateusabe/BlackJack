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

import Controller.ControllerBaralho;
import Controller.ControllerCroupier;
import Controller.ControllerJogador;
import java.util.Scanner;

/**
 * Essa classe é responsável por iniciar o jogo e gerar a interface de linha de comando.
 * @author Mateus Miranda
 * @author Tiago Moura
 */

public class Partida {
    private boolean validar = true;
    private ControllerJogador[] jogador;
    
    /**
     * Esse construtor é utilizado para a realização de testes de unidade. O outro
     * construtor é utilizado para o jogo.
     * @deprecated 
     * @param n int - Inteiro que diferencia um construtor do outro (polimorfismo por sobrecarga).
     */
    public Partida(int n){
        
    }
    
    /**
     * Esse construtor inicializa o jogo, imprimindo na tela o menu e as 
     * primeiras opções de ação.
     */
    public Partida(){
        String resposta;
        Scanner read = new Scanner(System.in);
        
        System.out.println("------------------------------------------BLACKJACK-----"
                + "-------------------------------------"); 
        System.out.println("\nDigite o número correspondente ao comando que deseja executar:\n");
        while(validar){
            System.out.println("(1)Cadastrar jogador\n(2)Nova partida\n(3)Como jogar\n(4)Mostrar baralho na ordem de saída do último jogo\n(5)Mostrar baralho ordenado do último jogo\n");
            resposta = read.nextLine();
            if(null == resposta){
                System.out.println("\n[Resposta inválida].\n");
            }
            else switch (resposta) {
                case "1":
                    cadastrarJogador();
                    break;
                case "2":
                    validar = false;
                    break;
                case "3":
                    regras();
                    break;
                case "4":
                    System.out.println("");
                    imprimirBaralhoDoUltimoJogo(resposta);
                    System.out.println("\n");
                    break;
                case "5":
                    System.out.println("");
                    imprimirBaralhoDoUltimoJogo(resposta);
                    System.out.println("\n");
                    break;
                default:
                    System.out.println("\n[Resposta inválida].\n");
                    break;
            }
        }
        
    }
    
    /**
     * Esse método é utilizado apenas para a realização de testes de unidade.
     * @deprecated 
     * @param aux ControllerJogador[] - Objeto que contém os jogadores da partida.
     */
    public void setJogadores(ControllerJogador[] aux){
        jogador = aux;
    }
    
    /**
     * Este método inicializa o jogo. Nele, é definido a quantidade de jogadores
     * que participarão da rodada, e nele é feito também o login dos jogadores.
     */
    public void inicioPartida(){
        Scanner ler = new Scanner(System.in);
        int resposta = 0;
        
        while(resposta == 0){
            System.out.println("\nDigite a quantidade de jogadores:");
            resposta = ler.nextInt();
            if(resposta>5 || resposta<1){
                resposta = 0;
                System.out.println("\n[A quantidade máxima de jogadores é 5]\n[A quantidade mínima de jogadores é 1]");
            }
        }
     
        jogador = new ControllerJogador[resposta];
        
        for(int i=0; i<jogador.length; i++)
            jogador[i] = new ControllerJogador();
        
        for(int i=0; i<jogador.length; i++){
            jogador[i].setJogador(login(1));
            if(jogador[i].getJogador() == null){
                System.out.println("\n[Jogador não existente]");
                i--;
            }
        }
        rodada();
        
    }
    
    /**
     * Este método é p funcionamento do jogo. Nele estão os comandos de pedir carta
     * e parar de pedir carta. Dependendo dos resultados, o jogo pode acabar ainda
     * dentro deste método.
     */
    public void rodada(){
        int maosMaioresQue21 = 0;
        int i;
        int jogadoresStand = 0;
        Scanner ler = new Scanner(System.in);
        int resposta = 0;
        
        ControllerCroupier croupier = new ControllerCroupier();
        ControllerBaralho baralho = croupier.getBaralho();
        baralho.criarBaralho();
        baralho.embaralharBaralho();
        
        for(i=0; i<jogador.length; i++){
            jogador[i].setCroupier(croupier);
        }
        i=0;
        
        do{    
            if(jogador[i].getValorDaMaoJogador() == 21){
                System.out.println(jogador[i].getJogador().getUser() + " venceu com BLACKJACK");
                return;
            }
            if(jogador[i].getStand()==2){
                if(i>=jogador.length-1)
                    i=0;
                else
                    i++;
            }
            
            if(jogadoresStand >= jogador.length){
                break;
            }
            
            if(jogador[i].getValorDaMaoJogador()>21){
                
                if(jogador.length == 1){
                    System.out.println("\nTodas as cartas do Croupier:");
                    croupier.mostrarCartas();
                    System.out.println("valor: " + croupier.getValorDaMaoCroupier() + "\n");
                    System.out.println("\nVocê perdeu.\n");
                    opcoesFinalDeRodada(croupier);
                    return;
                }
                
                int j;   
                for(j=0; j<jogador.length; j++){
                    if(jogador[j].getValorDaMaoJogador() < 21){
                        i=j;
                        break;
                    }
                }
                
                if(i == jogador.length-1 && jogador[jogador.length-1].getValorDaMaoJogador() > 21){
                    maosMaioresQue21 = jogador.length;
                }
            }
            
            if(maosMaioresQue21 + jogadoresStand >= jogador.length)
                break;
            
            if(maosMaioresQue21 >= jogador.length){
                System.out.println("\nTodas as cartas do Croupier:");
                croupier.mostrarCartas();
                System.out.println("valor: " + croupier.getValorDaMaoCroupier() + "\n");
                
                if(jogador.length == 1){
                    System.out.println("Você perdeu.");
                    opcoesFinalDeRodada(croupier); 
                    return;
                }
                
                System.out.println("Não houve vencedores.\n");
                opcoesFinalDeRodada(croupier); 
                return;
            }
            
            while(resposta!=1 & resposta!=2){
                if(jogador[i].getValorDaMaoJogador() == 0){
                    System.out.println("");
                    System.out.println(jogador[i].getJogador().getUser());
                    System.out.print("(1) Hit.");
                    System.out.println("");
                    resposta = ler.nextInt();
                }
                else{
                    System.out.println("");
                    System.out.println(jogador[i].getJogador().getUser());
                    System.out.print("(1) Hit."+"    "+"(2) Stand.");
                    System.out.println("");
                    resposta = ler.nextInt();
                }

                switch (resposta) {
                    case 1:
                        jogador[i].hit();
                        System.out.println("\n" + jogador[i].getJogador().getUser() +"(cartas)" + ":");
                        jogador[i].mostrarCartas();
                        System.out.println("Valor: "+
                                jogador[i].getValorDaMaoJogador() + "\n");
                        System.out.println("\nCartas do Croupier:");
                        croupier.mostrarUmaCarta();
                        break;
                        
                    case 2:
                        jogador[i].jogadorStand();
                        break;
                    default:
                        System.out.println("\n[Resposta Inválida]\n");
                }
            }
            resposta = 0; 
            
            if(jogador[i].getValorDaMaoJogador() == 21)
                break;
            
            if(jogador[i].getStand()==2)
                jogadoresStand++;
            
            if(jogador[i].getValorDaMaoJogador() > 21)
                maosMaioresQue21++;
            
            if(i<jogador.length)
                i++;
            
            if(i>=jogador.length)
                i=0;
        }while(jogador[i]!=null);
        
        finalDaPartida(croupier);
    }
    
    /**
     * Esse método mostra todas as cartas da mao de carta do croupier e calcula o resultado
     * da partida. Mostrando o se houve ou não vitória. 
     * @param croupier  ControllerCroupier - Objeto a ser serializado (armazenado
     * em arquivo binário).
     */
    public void finalDaPartida(ControllerCroupier croupier){
        Arquivo atualizar = new Arquivo();
        int maoCroupier;
        int maoJogador;
        int croupierMaior = 0;
        int pontuacaoIgual = 0;
        ControllerJogador maior = null;
        
        System.out.println("\nTodas as cartas do Croupier:");
        croupier.mostrarCartas();
        System.out.println("valor: " + croupier.getValorDaMaoCroupier() + "\n");
        
        if(jogador.length == 1){            
            if(jogador[0].getValorDaMaoJogador() == 21 & croupier.getCroupier().getMao().getValorDaMao() != 21){
                System.out.println(jogador[0].getJogador().getUser() + " VENCEU COM BLACKJACK");
                jogador[0].getJogador().atualizarJogosVencidos();
                jogador[0].getJogador().atualizarPontuacaoGeral();
                atualizar.arquivoJogadorSerializar(jogador[0].getJogador(),1);
            }
            else if(croupier.getValorDaMaoCroupier() == 21 || (jogador[0].getValorDaMaoJogador()<croupier.getValorDaMaoCroupier() && croupier.getValorDaMaoCroupier()<21) || jogador[0].getValorDaMaoJogador()==croupier.getValorDaMaoCroupier()){
                System.out.println("Voce perdeu.");
            }
            else if((jogador[0].getValorDaMaoJogador()<21 && jogador[0].getValorDaMaoJogador()>croupier.getValorDaMaoCroupier()) || (jogador[0].getValorDaMaoJogador()<21 && croupier.getValorDaMaoCroupier()>21)){
                System.out.println("Voce ganhoooou.");
                jogador[0].getJogador().atualizarJogosVencidos();
                jogador[0].getJogador().atualizarPontuacaoGeral();
                atualizar.arquivoJogadorSerializar(jogador[0].getJogador(),1);
            }
            opcoesFinalDeRodada(croupier);
            return;
        }
        else{
            int j;
            
            for (ControllerJogador jogador1 : jogador) {
                if ((jogador1.getValorDaMaoJogador()>21 || jogador1.getValorDaMaoJogador() < croupier.getValorDaMaoCroupier()) && croupier.getValorDaMaoCroupier()<21) {
                    croupierMaior++;
                }
            }
            
            if(croupierMaior == jogador.length || croupier.getValorDaMaoCroupier() == 21){
                System.out.println("Não houve vencedores.\n");
                opcoesFinalDeRodada(croupier); 
                return;
            }
            
            
            for(ControllerJogador jogador1 : jogador){
                if(jogador1.getValorDaMaoJogador() == 21 & croupier.getValorDaMaoCroupier() != 21){
                    System.out.println(jogador1.getJogador().getUser() + " venceu com BLACKJACK!\n");
                    jogador1.getJogador().atualizarJogosVencidos();
                    jogador1.getJogador().atualizarPontuacaoGeral();
                    atualizar.arquivoJogadorSerializar(jogador1.getJogador(),1);
                    opcoesFinalDeRodada(croupier);
                    return;
                }
            }
            
            for (int i=0; i<jogador.length-1; i++) {
                maoJogador = jogador[i].getJogador().getMao().getValorDaMao();
                maoCroupier = croupier.getCroupier().getMao().getValorDaMao();
                
                if(jogador[i].getValorDaMaoJogador() == jogador[i+1].getValorDaMaoJogador()){
                    pontuacaoIgual++;
                }
    
                if(maoCroupier != 21){
                    maior = new ControllerJogador();
                    for(j=0; j<jogador.length-1; j++){
                        if(jogador[j].getValorDaMaoJogador()<21 && (jogador[j].getValorDaMaoJogador()>maoCroupier || maoCroupier>21) && jogador[j].getValorDaMaoJogador() > jogador[j+1].getValorDaMaoJogador()){
                            maior = jogador[j];
                            opcoesFinalDeRodada(croupier);
                        }
                    }

                    if(j == jogador.length-1){
                        if(maior == null && jogador[j].getValorDaMaoJogador()<21 && (maoCroupier>21 || jogador[j].getValorDaMaoJogador()>maoCroupier)){
                            maior = jogador[j];
                        }
                        else if((maior.getValorDaMaoJogador() < jogador[j].getValorDaMaoJogador() || maoCroupier > 21) && jogador[j].getValorDaMaoJogador()<21)
                            maior = jogador[j];
                    }
                }
            }
            if(maior == null && pontuacaoIgual>0 && croupier.getValorDaMaoCroupier()>21)
                System.out.println("Empate.\n");
            else if(maior == null && croupier.getValorDaMaoCroupier() <= 21)
                System.out.println("Não houve vencedores.\n");
            else if(maior != null && maior.getJogador().getUser() != null)
                System.out.println(maior.getJogador().getUser() + " ganhou\n");
            
            maior.getJogador().atualizarJogosVencidos();
            maior.getJogador().atualizarPontuacaoGeral();
            atualizar.arquivoJogadorSerializar(maior.getJogador(),1);
                
        }
        opcoesFinalDeRodada(croupier);
    }
    
    /**
     * Esse método apresenta as opções de ação no final da rodada. Esse método também
     * atualiza o placar geral do jogo e serializa o croupier.
     * @param croupier ControllerCroupier - Objeto a ser serializado.
     */
    public void opcoesFinalDeRodada(ControllerCroupier croupier){
        arquivarCroupier(croupier);

        Arquivo placar = new Arquivo();
        String   resposta;
        boolean prisao = true;
        Scanner ler = new Scanner(System.in);
        
        placar.atualizarPlacar();
        
        while(prisao){
            for (int i=0; i<jogador.length; i++) {
                    jogador[i].getJogador().zerarMao();
                    jogador[i].zerarJogador();
            }
            
            System.out.println("\n\n(1)Nova partida com os mesmos jogadoes");
            System.out.println("(2)Adicionar novo jogador");
            if(jogador.length>1)
                System.out.println("(3)Excluir jogador da partida");
            System.out.println("(4)Mostrar o baralho da partida anterior na ordem de saída");
            System.out.println("(5)Mostrar o baralho ordenado da partida anterior");
            System.out.println("(6)Mostra placar geral");
            System.out.println("(7)Finalizar jogo\n");
            resposta = ler.nextLine();

            if("1".equals(resposta)){
                rodada();
                break;
            }
            else if("2".equals(resposta) && jogador.length<5){
                ControllerJogador[] aux = new ControllerJogador[jogador.length+1];
                
                for(int i=0; i<jogador.length; i++){
                    aux[i] = jogador[i];
                }
                
                aux[jogador.length] = new ControllerJogador();
                aux[jogador.length].setJogador(login(0));
                
                while(aux[jogador.length].getJogador() == null)
                    if(aux[jogador.length].getJogador() == null){
                        System.out.println("\n[Jogador não existe]");
                        aux[jogador.length].setJogador(login(0));
                    }
                
                aux[jogador.length].setCroupier(croupier);
                jogador = aux;
                rodada();
                break;
            }
            else if("3".equals(resposta) & jogador.length>1){
                Jogador aux = login(1);
                
                int j = 0;
                for(int i=0; i<jogador.length; i++){
                    if(jogador[i].getJogador().getUser().equals(aux.getUser())){
                        ControllerJogador[] aux2 = new ControllerJogador[jogador.length-1];
                        jogador[i] = jogador[jogador.length-1];
                        jogador[jogador.length-1] = null;
                        while(jogador[j] != null){
                            aux2[j]=jogador[j];
                            j++;
                        }
                        jogador = aux2;
                        rodada();
                        return;
                    }
                }
                System.out.println("\n\n[Jogador não encontrado]\n");
            }
            else if("4".equals(resposta)){
                System.out.println("");
                imprimirBaralhoDoUltimoJogo(resposta);
            }
            else if("5".equals(resposta)){
                System.out.println("");
                imprimirBaralhoDoUltimoJogo(resposta);
            }
            else if("6".equals(resposta)){
                placar.imprimirPlacar();
            }
            else if("7".equals(resposta))
                break;
            else{
                System.out.println("\n\n[Resposta inválida]\n");
            }
        }
    }
    
    /**
     * Esse método serve para cadastrar jogadores no programa, serializando objetos
     * do tipo jogador.
     */
    public void cadastrarJogador(){
        Jogador aux = new Jogador();
        Arquivo cadastrar = new Arquivo();
        Scanner ler = new Scanner(System.in);
        
        System.out.println("\nDigite o Username que deseja:");
        aux.setUser(ler.nextLine());
        System.out.println("\nDigite a senha que deseja:");
        aux.setSenha(ler.nextLine());
        
        cadastrar.arquivoJogadorSerializar(aux,0);
    }
 
    /**
     * Esse método serve para a realização do login do usuário. Ele é usado também
     * para adicionar ou excluir um jogador em uma partida.
     * @param n int - Inteiro que serve para definir se o método está sendo chamado
     * para adicionar o jogador em uma partida.
     * @return Jogador - Retorna o jogador logado ou nulo caso o jogador não esteja
     * registrado.
     */
    public Jogador login(int n){
        Jogador player = new Jogador();
        Arquivo arquivo = new Arquivo();
        Scanner ler = new Scanner(System.in);
        String respostaSenha;
        String respostaUser;
        
        System.out.print("\nDigite o seu Username: ");
        respostaUser = ler.nextLine();
        player.setUser(respostaUser);
        System.out.print("Digite sua senha: ");
        respostaSenha = ler.nextLine();
        player.setSenha(respostaSenha);
        
        Jogador aux = arquivo.arquivoJogadorDesserializar(player);
     
        if(aux!=null){
            if(!aux.getSenha().equals(respostaSenha)){
                System.out.println("\n[Senha incorreta]");
                login(1);
            }
            
            if(jogador.length>1 && n == 0){
                for (int i=0 ; i<jogador.length; i++) {
                    if (aux.getUser().equals(jogador[i].getJogador().getUser()) && jogador[i] != null) {
                        System.out.println("\nO jogador já está na partida");
                        login(0);
                    }
                }
            }
        }
        
        return aux;
    }
    
    /**
     * Neste método é onde ocorre a serialização do croupier da partida, para que
     * possa ser possível a impressão do baralho na tela da última partida.
     * @param croupier ControllerCroupier - Croupier da última partida.
     * @see Arquivo#arquivoBaralhoSerializar(Controller.ControllerCroupier)
     */
    public void arquivarCroupier(ControllerCroupier croupier){
        Arquivo arquivo = new Arquivo();
        arquivo.arquivoBaralhoSerializar(croupier);
    }
    
    /**
     * Esse método imprime o baralho do último jogo na tela. Ele desserializa
     * o croupier armazenado em arquivo binário e utiliza o baralho do mesmo.
     * @param resposta String - Define se a impressão será ordena pelo valor e pelo
     * naipe da carta ou não.
     */
    public void imprimirBaralhoDoUltimoJogo(String resposta){
        int indice;
        int quebraDeLinha = 0;
        Arquivo arquivo = new Arquivo();
        ControllerCroupier croupier = arquivo.arquivoBaralhoDesserializar();
        ControllerBaralho baralho = croupier.getBaralho();
        indice = baralho.getIndiceCartasDistribuidas()+1;
        Carta[] cartas = new Carta[baralho.getBaralho().getCartas().length - indice];
        Carta[] aux = baralho.getBaralho().getCartas();
        
        for(int i=0; i<cartas.length; i++){
            cartas[i] = aux[indice];
            indice++;
        }
      
        if("5".equals(resposta))
            baralho.getBaralho().SelectSort(cartas);
       
        for(int i=0; i<cartas.length; i++){
            System.out.print(cartas[i].toString() + " ");
            quebraDeLinha++;
            if(quebraDeLinha == 8){
                System.out.println("");
                quebraDeLinha = 0;
            }
        }
    }
    
    /**
     * Imprime as regas do jogo.
     */
    public void regras(){
        System.out.println("\nPara ganhar a partida, você deve obter uma pontuação\n"
                + "maior que a do croupier e menor que 21.\n"
                + "\nHit é o comando para pedir cartas.\n"
                + "Stand é o comando que cessa a petição por cartas "
                + "(é recomendado \nexecutar esse comando quando a sua pontuação"
                + " está próxima de 21). \n\nQuando há mais de um jogador, para ganhar você deve\n"
                + "pontuar mais que o croupier, e mais que seus adversários.\n\n"
                + "E é claro, o jogador que fizer 21 pontos ganha (a não ser que o croupier faça também).\n");
    }
}