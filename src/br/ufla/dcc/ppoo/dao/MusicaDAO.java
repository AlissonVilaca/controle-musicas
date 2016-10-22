package br.ufla.dcc.ppoo.dao;

import br.ufla.dcc.ppoo.modelo.Musica;
import java.util.List;

/** Interface do Data Access Object (Padrão de Projeto) da Música
 *
 * @author alisson-vilaca
 */
public interface MusicaDAO {
    
    /**
     * Adiciona uma música na lista do usuário
     * @param musica 
     */
    public void adicionarMusica(Musica musica);
    
    
    /**
     * Edita os dados de uma musica recebida
     * @param musica Musica a ser alterada
     * @param indice Índice da música na lista
     */
    //public void alterarMusica(Musica musica, int indice);
    
}
