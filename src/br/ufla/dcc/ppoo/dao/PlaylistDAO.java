package br.ufla.dcc.ppoo.dao;

import br.ufla.dcc.ppoo.modelo.Musica;
import br.ufla.dcc.ppoo.modelo.Playlist;
import br.ufla.dcc.ppoo.modelo.Usuario;
import java.util.List;

/**
 *
 * @author alisson-vilaca
 */
public interface PlaylistDAO {
   /**
     * Retorna a lista de playlists do usuário 
     * 
     * @param login
     * @return lista de musicas do usuário
     */
    public List<Playlist> obterListaPlaylist(Usuario login);
    
    /**
     * Retorna playliste de um usuario
     * @param login
     * @param selecionada
     * @return 
     */
    public Playlist getPlaylist(Usuario login, String selecionada);

    /**
     * Adiciona Playlist na lista geral
     * @param m 
     */
    public void adicionarPlaylist(List<Musica> m);
    
    /**
     * Seta uma playlist na playlist de edicao
     * @param p 
     */
    public void setarEditada(Playlist p);
    
    /**
     * Retorna a playlist temporaria
     * @return 
     */
    public Playlist obterPlaylistTemporaria();
   
    /**
     * Edita uma playlist
     * @param m
     * @param selecionada 
     */
    public void editarPlaylist(List<Musica> m, String selecionada,boolean publi);
   
    /**
     * Remove uma Playlist
     * @param titulo
     * @param u 
     */
    public void removerPlaylist(String titulo, Usuario u);
   
    /**
     * Faz a comparação se
     * @return 
     */
    public boolean comparaPlaylist ();
    
    /**
     * Remove palavra da lista de palavras da playlist
     * @param titulo 
     */
    public void removerPalavra(int indice);
    
    /**
     * Adiciona palavra da lista de palavras da playlist
     * @param titulo 
     */
    public void adicionarPalavra(String palavra);
    
    /**
     * Faz a Busca de PLaylists com as palavras digitadas pelo usuario
     * @param titulo 
     */
    public List<Playlist> buscaPlaylists(List<String> palavra);
    
    /**
     * Seta a Playlist que o Usuario selecionou para ser exibida
     * @param titulo 
     */
    public void setarExibida(Playlist palavra);
    
    /**
     * Zera a Playlist "Exibida"
     * @param titulo 
     */
    public void zerarExibida();
    
    /**
     * Retorna a Playlist "Exibida"
     * @param titulo 
     */
    public String getPlaylistExibida();
    
    public boolean isImportou();
    
    public void setImportou(boolean importou);
    
    public void arrumaMusicasImportadas(Usuario atual);
    
    public Playlist getExibida();
    
    public List<Musica> getMusicasImportadas(Usuario atual);
}