package br.ufla.dcc.ppoo.servicos;

import br.ufla.dcc.ppoo.dao.MusicaDAO;
import br.ufla.dcc.ppoo.dao.PlaylistDAO;
import br.ufla.dcc.ppoo.dao.lista.MusicaDAOLista;
import br.ufla.dcc.ppoo.dao.lista.PlaylistDAOLista;
import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.modelo.Musica;
import br.ufla.dcc.ppoo.modelo.Playlist;
import br.ufla.dcc.ppoo.modelo.Usuario;
import br.ufla.dcc.ppoo.seguranca.SessaoUsuario;
import java.util.ArrayList;
import java.util.List;
import sun.nio.cs.ext.GB18030;

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
    //    return playlist.getPlaylist(login,selecionada).getMusicas();
    }
    
    public void cadastrarPlaylist(List<String> lista, String nome, Usuario login) {
        playlist.adicionarPlaylist(lista,nome,login,gerenciadorMusicas.obterSelecionadas());                
    }
}
