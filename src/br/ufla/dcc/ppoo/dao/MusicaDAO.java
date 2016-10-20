package br.ufla.dcc.ppoo.dao;

import br.ufla.dcc.ppoo.modelo.Musica;
import br.ufla.dcc.ppoo.modelo.Usuario;

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
    
}
