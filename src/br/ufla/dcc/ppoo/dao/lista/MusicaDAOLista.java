package br.ufla.dcc.ppoo.dao.lista;
import br.ufla.dcc.ppoo.dao.MusicaDAO;
import br.ufla.dcc.ppoo.modelo.Musica;
import br.ufla.dcc.ppoo.modelo.Playlist;
import br.ufla.dcc.ppoo.modelo.Usuario;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** 
 * Implementação da lista de músicas, que é um atributo da Classe Musica
 *
 * @author alisson-vilaca
 */
public class MusicaDAOLista implements MusicaDAO, Serializable{    
    
    // instância única da classe (Padrão de Projeto Singleton)
    private static MusicaDAOLista instancia;
    
    // lista em em memória das musicas de um usuario cadastradas
    private final List<Musica> listaMusica;

   /**
    * Constrói o objeto lista de musicas. 
    * 
    * @param auxiliar Envio um parâmetro para que o este 
    * construtor seja chamado quando o método obterInstancia é usado. Não 
    * encontrei uma forma de chamar este construtor de outra maneira.
    */ 
    private MusicaDAOLista(){
        listaMusica = carregarDadosMusicas();  
    }
     
    /**
     * Retorna a instância única da classe (Padrão de Projeto Singleton)
     * 
     * @return A instância única da classe
     */
    public static MusicaDAOLista obterInstancia() {
        if (instancia == null) {
            instancia = new MusicaDAOLista();
        }
        return instancia;
    }

    /**
     * Retorna a lista de músicas do usuário 
     * Pular Pesquisar nos Fóruns
     * @param login
     * @return lista de musicas do usuário
     */
    public List<Musica> obterListaMusica(Usuario login) {
       List<Musica> lista = new ArrayList<>() ;
        for (Musica u : listaMusica) {
            if (u.obterUsuario().obterLogin().equals(login.obterLogin())) {
                lista.add(u);
            }
        }        
        return lista;
    }        
    
    /**
     * Faz a verificação se a música já está na lista do usuário atual
     * 
     * @param musica Musica a ser verificada
     * @return true para musicas com titulo e login iguais
     */
    @Override
    public boolean comparaMusicas (Musica musica){
        for (Musica u : listaMusica) {
            if(musica.comparaMusicas(u)){
                return true;                
            }            
        }        
        return false;
    }
    
    /**
     * Adiciona a música passada na lista
     * 
     * @param musica Musica que será adicionada na lista
     */
    @Override
    public void adicionarMusica(Musica musica) {
        listaMusica.add(musica);
    }
    
    /**
     * Edita os dados de uma musica recebida
     * 
     * @param musica Musica a ser alterada
     * @param selecionada NOme da musica que será alterada
     * @param login login do usuario atual
     */
    @Override
    public void editarMusica(Musica musica, String selecionada, Usuario login) {       
        for (Musica u : listaMusica) {
            if (u.obterTitulo().equals(selecionada) && (u.obterUsuario().obterLogin().equals(login.obterLogin()))){
                u.setAno(musica.obterAno());
                u.setArtista(musica.obterArtista());
                u.setGenero(musica.obterGenero());
                u.setTitulo(musica.obterTitulo());
                u.setUsuario(musica.obterUsuario());
                u.setLetra(musica.obterLetra());
            }
        }
    }
    
    /**
     * Deleta uma musica recebida
     * 
     * @param titulo Titulo da música que será removida
     * @param login Login do usuario atual
     */
  //  @Override
    public void deletarMusica(String titulo, Usuario login) {
        //Tive que usar o iterator para que não ocorresse o erro ConcurrentModificationException
        for (Iterator<Musica> i = listaMusica.iterator(); i.hasNext();) {
          Musica u = i.next();
          if (u.obterTitulo().equals(titulo) && u.obterUsuario().obterLogin().equals(login.obterLogin())) {
            i.remove();
          }
        }
    }      
    
    /**
     * Marca uma musica
     * @param u
     * @param login 
     */
    public void marcar(Musica u, Usuario login) {
        for (Musica m : listaMusica) {
            if (u.obterTitulo().equals(m.obterTitulo()) && m.obterUsuario().obterLogin().equals(login.obterLogin())) {
                m.marcar();
            }            
        }
    }
    /**
     * Desmarca musicas após serem editadas
     * @param u
     * @param login 
     */
    public void desmarcar(Musica u, Usuario login) {
        for (Musica m : listaMusica) {
            if (u.obterTitulo().equals(m.obterTitulo()) && m.obterUsuario().obterLogin().equals(login.obterLogin())) {
                m.desmarcar();
            }            
        }
    }
    
    /**
     * Obtem lista de música marcadas
     * @return 
     */
    public List<Musica> obterSelecionadas() {
        List<Musica> m = new ArrayList<>();
        for (Musica u : listaMusica) {
            if (u.estaMarcada()){
                m.add(u);
                u.desmarcar();
            }
        }
        return m;
    }
    
    /**
     * Retorna a lista de musicas nao marcadas
     * 
     * @param login
     * @return lista de musicas do usuário
     */
    public List<Musica> obterListaMusicasNaoMarcadas(Usuario login) {
       List<Musica> lista = new ArrayList<>() ;
        for (Musica u : listaMusica) {
            if (u.obterUsuario().obterLogin().equals(login.obterLogin()) && !u.estaMarcada()) {
                lista.add(u);
            }
        }        
        return lista;
    } 
    
    /**
     * Retorna a lista de musicas nao marcadas
     * 
     * @param login
     * @return lista de musicas do usuário
     */
    public List<Musica> obterListaMusicasMarcadas(Usuario login) {
       List<Musica> lista = new ArrayList<>() ;
        for (Musica u : listaMusica) {
            if (u.obterUsuario().obterLogin().equals(login.obterLogin()) && u.estaMarcada()) {
                lista.add(u);
            }
        }        
        return lista;
    }
    
    /**
     * obtem índice de determinada musica em uma lista
     * @param list
     * @param j
     * @return 
     */
    public int obterIndice(List<Musica> list, String j) {        
        for (int i = 0; i < list.size(); i++){         
            if (list.get(i).comparaMusicaComString(j)){
                return i;
            }
        } 
        return -1;
    }
    
    /**
     * Marca as musicas de uma playlist para edição
     * @param p 
     */
    public void marcarMusicas (Playlist p){
        for (Musica m : listaMusica){
            for (Musica u : p.getMusicas()){
                if (u.obterTitulo().equals(m.obterTitulo()) && u.obterUsuario().obterLogin().equals(m.obterUsuario().obterLogin())){
                    m.marcar();
                }
            }            
        }    
    } 
    
    /**
     * Desmarca musicas após serem editadas
     * @param u
     * @param login 
     */
    public void desmarcarMusicas (){
        for (Musica m : listaMusica){
            m.desmarcar();
        }
    }
    
    /**
     * Valida se a quantidade de músicas é suficiente para a playlist
     * @return 
     */
    public boolean musicasInsuficientes(){
        int i = 0;
        for (Musica m : listaMusica){
            if (m.estaMarcada()){
                i++;
            }                
        }
        if (i < 2) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Faz a busca de uma musica pelo nome e usuario passados
     * @param u
     * @param atual
     * @return 
     */
    public Musica buscaMusicasPeloNome(Musica u,Usuario atual){        
        Musica musica;
        for (Musica l : listaMusica) {
            if ((l.obterUsuario().obterLogin().equals(atual.obterLogin())) && l.obterTitulo().equals(u.obterTitulo()) ) {
                return l;
            }
        }   
        return new Musica("testelol", "teste", 0, "teste", "letra", atual);
    }
    
    /**
     * Verifica seuma musica ja esta cadastrada para o usuario do parâmetro
     * @param musica
     * @param atual
     * @return 
     */
    public boolean verificaMusicas(Musica musica, Usuario atual){
        for (Musica u : listaMusica) {
            if(musica.verificaMusicas(u.obterTitulo(),atual)){
                return true;                
            }            
        }        
        return false;
    
    }
    
    /**
     * Salva os dados das Musicas em um arquivo binário
     */
    @Override
    public void salvarDadosMusicas () {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new
            FileOutputStream("Musicas.bin"));            
            oos.writeObject(listaMusica);
            oos.close();
        } catch (Exception e) {}
        
    }
    
    /**
     * Carrega os dados das Musicas de um arquivo binário
     * @return 
     */
    @Override
    public ArrayList<Musica> carregarDadosMusicas() {
        ArrayList<Musica> lista= new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new
            FileInputStream("Musicas.bin"));
            lista = (ArrayList<Musica>) ois.readObject();
            ois.close();           
            return lista;
        } catch (Exception e) {System.out.println("Erro ao carregar dados musica: "+e);}
        return lista;
    }
}
