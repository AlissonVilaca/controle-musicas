package br.ufla.dcc.ppoo.dao;

import br.ufla.dcc.ppoo.modelo.Musica;

/** 
 * Interface do Data Access Object (Padrão de Projeto) da Música
 *
 * @author alisson-vilaca
 */
public interface MusicaDAO {
    
    /**
     * Adiciona uma música na lista do usuário
     * 
     * @param musica 
     */
    public void adicionarMusica(Musica musica);    
    
    /**
     * Edita os dados de uma musica recebida
     * 
     * @param musica Musica a ser alterada
     * @param indice Índice da música na lista
     */
    public void editarMusica(Musica musica, int indice);
    
    /**
     * Deleta os dados de uma musica recebida
     * 
     * @param indice Índice da música na lista
     */
    public void deletarMusica(int indice);    
}