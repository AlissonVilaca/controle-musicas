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
    
    public Playlist getPlaylist(Usuario login, String selecionada);

    public void adicionarPlaylist(List<String> lista, String nome, Usuario login, List<Musica> m);
}