package br.ufla.dcc.ppoo.dao.lista;

import br.ufla.dcc.ppoo.dao.PlaylistDAO;
import br.ufla.dcc.ppoo.modelo.Musica;
import br.ufla.dcc.ppoo.modelo.Playlist;
import br.ufla.dcc.ppoo.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

/** 
 * Implementação da lista de PLaylists, que é um atributo da Classe Playlist
 *
 * @author alisson-vilaca
 */
public class PlaylistDAOLista implements PlaylistDAO {
    // instância única da classe (Padrão de Projeto Singleton)
    private static PlaylistDAOLista instancia;
    
    // lista em em memória das musicas de um usuario cadastradas
    private final List<Playlist> listaPlaylist;
    
    /**
    * Constrói o objeto lista de playlists. 
    * 
    * @param auxiliar Envio um parâmetro para que o este 
    * construtor seja chamado quando o método obterInstancia é usado. Não 
    * encontrei uma forma de chamar este construtor de outra maneira.
    */ 
    private PlaylistDAOLista(String auxiliar){
        listaPlaylist = new ArrayList<Playlist>();                
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
    
    public Playlist getPlaylist(Usuario login, String selecionada) {
        for (Playlist u : listaPlaylist) {
            if (u.getNome().equals(selecionada) && (u.getUsuario() == login)){
                return (u);
            }
        }   
        return null; 
    }
    
    public void adicionarPlaylist(List<String> lista, String nome, Usuario login, List<Musica> m) {
        listaPlaylist.add(new Playlist(nome, login, lista, m));
    }
}
