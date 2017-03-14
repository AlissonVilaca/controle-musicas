package br.ufla.dcc.ppoo.dao;

import br.ufla.dcc.ppoo.modelo.Musica;
import br.ufla.dcc.ppoo.modelo.Playlist;
import br.ufla.dcc.ppoo.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alisson-vilaca
 */
public interface MusicaDAO {
    
    /**
     * Faz a verificação se a música já está na lista do usuário atual
     * 
     * @param musica Musica a ser verificada
     * @return true para musicas com titulo e login iguais
     */
     public boolean comparaMusicas (Musica musica);
     
     /**
     * Adiciona a música passada na lista
     * 
     * @param musica Musica que será adicionada na lista
     */

     public void adicionarMusica(Musica musica);
      
     /**
     * Edita os dados de uma musica recebida
     * 
     * @param musica Musica a ser alterada
     * @param selecionada NOme da musica que será alterada
     * @param login login do usuario atual
     */
    public void editarMusica(Musica musica, String selecionada, Usuario login);
    
    /**
     * Deleta uma musica recebida
     * 
     * @param titulo Titulo da música que será removida
     * @param login Login do usuario atual
     */
    public void deletarMusica(String titulo, Usuario login);
        
    /**
     * Retorna a lista de músicas do usuário 
     * 
     * @param login
     * @return lista de musicas do usuário
     */
    public List<Musica> obterListaMusica(Usuario login);
    
    /**
     * Marca uma musica
     * @param u
     * @param login 
     */
    public void marcar(Musica u, Usuario login);    
    
    /**
     * Obtem lista de música marcadas
     * @return 
     */
    public List<Musica> obterSelecionadas();
    
    /**
     * Obtem lista de musicas nao marcadas
     * @param login
     * @return 
     */
    public List<Musica> obterListaMusicasNaoMarcadas(Usuario login);
    
    /**
     * obtem índice de determinada musica em uma lista
     * @param list
     * @param j
     * @return 
     */
    public int obterIndice(List<Musica> list, String j);
    
    /**
     * Marca as musicas de uma playlist para edição
     * @param p 
     */
    public void marcarMusicas (Playlist p);
    
    /**
     * Desmarca musicas após serem editadas
     * @param u
     * @param login 
     */
    public void desmarcar(Musica u, Usuario login);
    
    /**
     * Obtem lista de músicas marcadas de um usuário
     * @param login
     * @return 
     */
    public List<Musica> obterListaMusicasMarcadas(Usuario login);
    
    /**
     * Desmarca todas as músicas
     */
    public void desmarcarMusicas ();
    
    /**
     * Valida se a quantidade de músicas é suficiente para a playlist
     * @return 
     */
    public boolean musicasInsuficientes();
    
    /**
     * Faz a busca de uma musica pelo nome e usuario passados
     * @param u
     * @param atual
     * @return 
     */
    public Musica buscaMusicasPeloNome(Musica u,Usuario atual);
    
    /**
     * Verifica seuma musica ja esta cadastrada para o usuario do parâmetro
     * @param musica
     * @param atual
     * @return 
     */
    public boolean verificaMusicas(Musica musica, Usuario atual);
    
    /**
     * Carrega os dados das Musicas de um arquivo binário
     */
    public void salvarDadosMusicas ();
    
    /**
     * Salva os dados das Musicas em um arquivo binário
     */
    public ArrayList<Musica> carregarDadosMusicas();
}