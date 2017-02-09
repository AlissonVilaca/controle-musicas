package br.ufla.dcc.ppoo.dao.lista;

import br.ufla.dcc.ppoo.dao.PlaylistDAO;
import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.modelo.Musica;
import br.ufla.dcc.ppoo.modelo.Playlist;
import br.ufla.dcc.ppoo.modelo.Usuario;
import br.ufla.dcc.ppoo.servicos.GerenciadorMusicas;
import br.ufla.dcc.ppoo.util.Utilidades;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/** 
 * Implementação da lista de PLaylists, que é um atributo da Classe Playlist
 *
 * @author alisson-vilaca
 */
public class PlaylistDAOLista implements PlaylistDAO {
    // instância única da classe (Padrão de Projeto Singleton)
    private static PlaylistDAOLista instancia;
    
    // lista em em memória das playlists cadastradas
    private final List<Playlist> listaPlaylist;
    //playlist temporaria usada para adicionar e editar playlists
    private Playlist playlistSendoEdiatada; 
   //playlist temporaria usada para guardar a playlist que o Usuario quer que seja exibida
    private Playlist exibida;
    // referência para o gerenciador de músicas
    private final GerenciadorMusicas gerenciadorMusicas;
    //variavel usada para controlar a tela de Minhas de Playlists, para inicializar 
    // a tela corretamente, quando é importada uma música
    private boolean importou;
    
    
    /**
    * Constrói o objeto lista de playlists. 
    * 
    * @param auxiliar Envio um parâmetro para que o este 
    * construtor seja chamado quando o método obterInstancia é usado. Não 
    * encontrei uma forma de chamar este construtor de outra maneira.
    */ 
    private PlaylistDAOLista(String auxiliar){
        listaPlaylist = new ArrayList<Playlist>();   
        playlistSendoEdiatada = new Playlist("", new Usuario(), null, null,false);        
        exibida = new Playlist("", new Usuario(), null, null,false); 
        importou = false;
        gerenciadorMusicas = new GerenciadorMusicas();
    }
    
    /**
     * Retorna a instância única da classe (Padrão de Projeto Singleton)
     * 
     * @return A instância única da classe
     */
    public static PlaylistDAOLista obterInstancia() {
        if (instancia == null) {
            instancia = new PlaylistDAOLista("auxiliar");
        }
        return instancia;
    }
    
    /**
     * Retorna a lista de playlists do usuário 
     * 
     * @param login
     * @return lista de musicas do usuário
     */
    public List<Playlist> obterListaPlaylist(Usuario login) {
       List<Playlist> lista = new ArrayList<>() ;
        for (Playlist u : listaPlaylist) {
            if (u.getUsuario() == login) {
                lista.add(u);
            }
        }        
        return lista;
    }
    
    /**
     * Retorna playliste de um usuario
     * @param login
     * @param selecionada
     * @return 
     */
    public Playlist getPlaylist(Usuario login, String selecionada) {
        for (Playlist u : listaPlaylist) {
            if (u.getNome().equals(selecionada) && (u.getUsuario() == login)){
                return (u);
            }
        }   
        return null; 
    }
    
    /**
     * Adiciona Playlist na lista geral
     * @param m 
     */
    public void adicionarPlaylist(List<Musica> m) {
        listaPlaylist.add(new Playlist(playlistSendoEdiatada.getNome(), playlistSendoEdiatada.getUsuario(), playlistSendoEdiatada.getPalavras(), m, playlistSendoEdiatada.isVisilidade()));
    }
    
    /**
     * Edita uma playlist
     * @param m
     * @param selecionada 
     */
    public void editarPlaylist(List<Musica> m, String selecionada,boolean publi) {
        for (Playlist u : listaPlaylist) {
            if (u.getNome().equals(selecionada) 
                    && (u.getUsuario()== playlistSendoEdiatada.getUsuario())){                
                u.setNome(playlistSendoEdiatada.getNome());
                u.setListaMusicas(m);
                u.setListaPalavras(playlistSendoEdiatada.getPalavras());
                u.setVisilidade(publi);
            }
        }        
    }
    
    /**
     * Seta uma playlist na playlist de edicao
     * @param p 
     */
    public void setarEditada(Playlist p){
        playlistSendoEdiatada = p;       
    }
    
    /**
     * Retorna a playlist temporaria
     * @return 
     */
    public Playlist obterPlaylistTemporaria(){
        return playlistSendoEdiatada;
    }
    
    /**
     * Remove uma playlist passada no sistema
     * @param titulo Titulo da música a ser removida
     */
    public void removerPlaylist(String titulo, Usuario login){
        //Tive que usar o iterator para que não ocorresse o erro ConcurrentModificationException
        for (Iterator<Playlist> i = listaPlaylist.iterator(); i.hasNext();) {
          Playlist u = i.next();
          if (u.getNome().equals(titulo) && (u.getUsuario()== login)) {
            i.remove();
          }
        }
    }
    
    /**
     * Compara se uma playlist está sendo editada
     * @return 
     */
    public boolean comparaPlaylist () {
        for (Playlist u : listaPlaylist) {
                if(playlistSendoEdiatada.comparaPlaylist(u)){
                    return true;                
                }            
            }        
        return false; 
    }
    
    /**
     * Remove palavra da lista de palavras da playlist
     * @param titulo 
     */
    public void removerPalavra(int indice){
        playlistSendoEdiatada.getPalavras().remove(indice);
    }
    
    /**
     * Adiciona palavra da lista de palavras da playlist
     * @param titulo 
     */
    public void adicionarPalavra(String palavra){
        playlistSendoEdiatada.getPalavras().add(palavra);
    }
    
     /**
     * Faz a Busca de PLaylists com as palavras digitadas pelo usuario
     * @param titulo 
     */
    public List<Playlist> buscaPlaylists(List<String> palavra){
        List<Playlist> lista = new ArrayList<Playlist>();
        for ( String s : palavra){
            for (Playlist p : listaPlaylist) {
                if(p.isVisilidade()){    
                    if(p.getNome().equals(s)){
                        if (!lista.contains(p)){
                            lista.add(p);
                        }                    
                    }
                    for (String sg : p.getPalavras()) {
                        if (sg.equals(s)){
                            if (!lista.contains(p)){
                                lista.add(p);
                            }   
                        }
                    }
                }
            }                                    
        }
        return lista;
    }
    
    /**
     * Seta a Playlist que o Usuario selecionou para ser exibida
     * @param titulo 
     */
    public void setarExibida(Playlist palavra){
        exibida.setNome(palavra.getNome());   
        exibida.setListaPalavras(palavra.getPalavras());
        exibida.setListaMusicas(palavra.getMusicas());
        exibida.setUsuario(palavra.getUsuario());
        exibida.setVisilidade(palavra.isVisilidade());
        exibida.setUsuariosQueAvaliaram(palavra.getUsuariosQueAvaliaram());
        exibida.setPontuacao(palavra.getPontuacao());
    }
    
    /**
     * Zera a Playlist "Exibida"
     * @param titulo 
     */
    public void zerarExibida(){
       exibida.setNome("");
       exibida.setListaMusicas(null);
       exibida.setListaPalavras(null);
       exibida.setUsuario(null);
       exibida.setVisilidade(false);               
    }
    
    /**
     * Retorna a Playlist "Exibida"
     * 
     */
    public String getPlaylistExibida(){
        String texto = new String();
        String pont = "";
        if (exibida.getPontuacao() >999 && exibida.getPontuacao() <1000000) {
            int i = exibida.getPontuacao()/1000;
            pont = i+"k";
        } else if (exibida.getPontuacao() > 999999){
            int i = exibida.getPontuacao()/1000000;
            pont = i+"mi";
        } else {
            pont = ""+exibida.getPontuacao();
        }
            texto = "Lista: " + exibida.getNome() + "\t\t" + pont +
                    "\nAutor: " + exibida.getUsuario().obterNome() +
                    "\nPalavras-chave: ";
            for (int i = 0; i < exibida.getPalavras().size(); i++) {
                texto += exibida.getPalavras().get(i);
                if (i == exibida.getPalavras().size()-1){
                    texto+=".\n";
                } else {
                    texto+=", "; 
                }
            }
            texto += "Lista de músicas:\n";
            for (int i = 0; i < exibida.getMusicas().size(); i++) {
                int j = i+1;
                texto+= "     " + j +"."+exibida.getMusicas().get(i).obterTitulo()+
                        " (" + exibida.getMusicas().get(i).obterArtista() + ")\n";
            }                          
        return texto;
    }

    /**
     * Returna se a playlist está sendo importada
     * @return 
     */
    public boolean isImportou() {
        return importou;
    }

    /**
     * Seta a playlist como importada ou não
     * @param importou 
     */
    public void setImportou(boolean importou) {
        this.importou = importou;
    }  
    
    /**
     * Adiciona as músicas da playlist importada no usuario atual
     * @param atual 
     */
    public void arrumaMusicasImportadas(Usuario atual) {                                
        for (Musica u : exibida.getMusicas()){
            if (!gerenciadorMusicas.verificaMusicas(u,atual)){
                try {
                    gerenciadorMusicas.cadastrarMusica(new Musica(u.obterTitulo(), 
                            u.obterArtista(), u.obterAno(),
                            u.obterGenero(), u.obterLetra(), atual));
                } catch (Exception ex) {
                    Logger.getLogger(PlaylistDAOLista.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
        }
    } 
    
    /**
     * Retorna a lista que vai ser importada
     * @return 
     */
    public Playlist getExibida() {
        return exibida;
    }
    
    /**
     * Retorna as musicas que foram importadas na lista geral de músicas para serem
     * adicionadas na playlist
     * @param atual
     * @return 
     */
    public List<Musica> getMusicasImportadas(Usuario atual){
        List<Musica> mu= new ArrayList<>();
        for (Musica u : exibida.getMusicas()){
            mu.add(gerenciadorMusicas.buscaMusicasPeloNome(u,atual));
        }        
        
        return mu;    
    }
    
    /**
     * Adiciona a pontuacao informada à playlist atual;
     * @param pont 
     */
    public void pontuar(int pont,Usuario atual){
        boolean avaliou = false;
        for (Usuario u : exibida.getUsuariosQueAvaliaram()){
            if (u == atual){
                avaliou = true;
            }
        }
        if (avaliou){
            Utilidades.msgErro(I18N.erroUsuarioJaAvaliou());
        } else {
            for (Playlist p : listaPlaylist) {
                if (p.getNome().equals(exibida.getNome()) && p.getUsuario() == exibida.getUsuario()){
                    p.pontuar(pont);
                    p.adicionarUsuario(atual);
                }
            }            
            exibida.pontuar(pont);
        }        

    } 
}
