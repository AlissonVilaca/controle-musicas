package br.ufla.dcc.ppoo.servicos;

import br.ufla.dcc.ppoo.dao.PlaylistDAO;
import br.ufla.dcc.ppoo.dao.lista.PlaylistDAOLista;
import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.modelo.Musica;
import br.ufla.dcc.ppoo.modelo.Playlist;
import br.ufla.dcc.ppoo.modelo.Usuario;
import br.ufla.dcc.ppoo.seguranca.SessaoUsuario;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa a camada de negócios de cadastro de playlists. Permite
 * cadastrar, alterar e excluir uma playlist.
 * 
 * @author alisson-vilaca
 */
public class GerenciadorPlaylists {
    // atributo utilizado para podermos manipular a lista de músicas do usuário
    // logado atualmente
    private final SessaoUsuario sessaoUsuario;
    // objeto usado para recuperar a lista de musicas 
    private final PlaylistDAO playlist ;  
    
    
    
    private final GerenciadorMusicas gerenciadorMusicas;
                                    
    /**
     * Constrói o gerenciador de playlists do usuário logado, inicializando as 
     * camadas de acesso.
     */
    public GerenciadorPlaylists(){
        sessaoUsuario = SessaoUsuario.obterInstancia();
        playlist = PlaylistDAOLista.obterInstancia();
        gerenciadorMusicas = new GerenciadorMusicas();
    }
    
    /**
     * Retorna a lista de playlists do usuário 
     * 
     * @param login
     * @return lista de musicas do usuário
     */
    public List<Playlist> obterLista(Usuario login) {        
        return playlist.obterListaPlaylist(login);
    }
    
    /**
     * Retorna a lista de palavras da playlist selecionada 
     * 
     * @param login
     * @return lista de musicas do usuário
     */
    public List<String> obterPalavras(Usuario login, String selecionada) {        
        Playlist u = playlist.getPlaylist(login,selecionada);
        if (u == null){
            List<String> j =  new ArrayList<>();
            return j;
        } else {
                return u.getPalavras();
        }
    }
    
    /**
     * Retorna a lista músicas da playlist selecionada 
     * 
     * @param login
     * @return lista de musicas do usuário
     */
    public List<Musica> obterMusicas(Usuario login, String selecionada) {        
        Playlist u = playlist.getPlaylist(login,selecionada);
        if (u == null){
            List<Musica> j =  new ArrayList<>();
            return j;
        } else {
                return u.getMusicas();
        }
    }
    
    /**
     * Cadastra uma playlist
     * @throws Exception 
     */
    public void cadastrarPlaylist() throws Exception {
        boolean ret = playlist.comparaPlaylist();
        if (ret) {
            throw new Exception(I18N.obterErroPlaylistJaCadastrada());
        } else {
            playlist.adicionarPlaylist(gerenciadorMusicas.obterSelecionadas());         
        }                       
    }
    
    /**
     * Edita uma playlist
     * @param selecionada
     * @param nome
     * @throws Exception 
     */
    public void editarPlaylist(String selecionada,String nome,boolean publi) throws Exception {        
        playlist.obterPlaylistTemporaria().setNome(nome);
        
        if (playlist.obterPlaylistTemporaria().getNome().equals(selecionada)){
            playlist.editarPlaylist(gerenciadorMusicas.obterSelecionadas(),selecionada,publi);
        }else{
            boolean ret = playlist.comparaPlaylist();
            if (ret) {
                throw new Exception(I18N.obterErroPlaylistComMesmoNome());
            } else { 
                playlist.editarPlaylist(gerenciadorMusicas.obterSelecionadas(),selecionada,publi);                   
            }   
        }               
    }
    
    /**
     * Seta os valores delejados na playlist de edição
     * @param p 
     */
    public void setarEditada(Playlist p){
        playlist.setarEditada(p);       
    }
    
    /**
     * Retorna a playlist temporária
     * @return 
     */
    public Playlist obterPlaylistTemporaria (){
        return playlist.obterPlaylistTemporaria ();
    }
    
    /**
     * Remove uma música passada no sistema
     * @param titulo Titulo da música a ser removida
     */
    public void removerPlaylist(String titulo){
        playlist.removerPlaylist(titulo,sessaoUsuario.obterUsuario());
    } 
    
    /**
     * Remove palavra da lista de palavras da playlist
     * @param titulo 
     */
    public void removerPalavra(int indice){
        playlist.removerPalavra(indice);
    }
    
    /**
     * Adiciona palavra da lista de palavras da playlist
     * @param titulo 
     */
    public void adicionarPalavra(String palavra){
        playlist.adicionarPalavra(palavra);
    }
    
    /**
     * Faz a Busca de PLaylists com as palavras digitadas pelo usuario
     * @param titulo 
     */
    public List<Playlist> buscaPlaylists(List<String> palavra){
       return playlist.buscaPlaylists(palavra);
    }
    
    /**
     * Seta a Playlist que o Usuario selecionou para ser exibida
     * @param titulo 
     */
    public void setarExibida(Playlist palavra){
       playlist.setarExibida(palavra);
    }
    

    /**
     * Zera a Playlist "Exibida"
     * @param titulo 
     */
    public void zerarExibida(){
       playlist.zerarExibida();
    }
   
    /**
     * Retorna a Playlist "Exibida"
     * @param titulo 
     */
    public String getPlaylistExibida(){
       return playlist.getPlaylistExibida();
    }
    
    public boolean isImportou() {
        return playlist.isImportou();
    }

    public void setImportou(boolean importou) {
        playlist.setImportou(importou);
    }
    
    public void arrumaMusicasImportadas(Usuario atual) {
        playlist.arrumaMusicasImportadas(atual);
    }
    
    public Playlist getExibida() {
        return playlist.getExibida();
    }
    
    public List<Musica> getMusicasImportadas(Usuario atual){
        return playlist.getMusicasImportadas(atual);    
    }
}
