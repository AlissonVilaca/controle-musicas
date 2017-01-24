package br.ufla.dcc.ppoo.dao.lista;

import br.ufla.dcc.ppoo.dao.PlaylistDAO;
import br.ufla.dcc.ppoo.modelo.Musica;
import br.ufla.dcc.ppoo.modelo.Playlist;
import br.ufla.dcc.ppoo.modelo.Usuario;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    private Playlist playlistSendoEdiatada; //arrumar
    
    /**
    * Constrói o objeto lista de playlists. 
    * 
    * @param auxiliar Envio um parâmetro para que o este 
    * construtor seja chamado quando o método obterInstancia é usado. Não 
    * encontrei uma forma de chamar este construtor de outra maneira.
    */ 
    private PlaylistDAOLista(String auxiliar){
        listaPlaylist = new ArrayList<Playlist>();   
        playlistSendoEdiatada = new Playlist("", new Usuario(), null, null);
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
        listaPlaylist.add(new Playlist(playlistSendoEdiatada.getNome(), playlistSendoEdiatada.getUsuario(), playlistSendoEdiatada.getPalavras(), m));
    }
    
    /**
     * Edita uma playlist
     * @param m
     * @param selecionada 
     */
    public void editarPlaylist(List<Musica> m, String selecionada) {
        for (Playlist u : listaPlaylist) {
            if (u.getNome().equals(selecionada) 
                    && (u.getUsuario()== playlistSendoEdiatada.getUsuario())){                
                u.setNome(playlistSendoEdiatada.getNome());
                u.setListaMusicas(m);
                u.setListaPalavras(playlistSendoEdiatada.getPalavras());

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
}
