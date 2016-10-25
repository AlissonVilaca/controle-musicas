package br.ufla.dcc.ppoo.servicos;

import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.modelo.Musica;
import br.ufla.dcc.ppoo.seguranca.SessaoUsuario;

/**
 * Classe que representa a camada de negócios de cadastro de músicas. Permite
 * cadastrar, alterar e excluir uma música.
 * 
 * @author alisson-vilaca
 */
public class GerenciadorMusicas {
    
    // atributo utilizado para podermos manipular a lista de músicas do usuário
    // logado atualmente
    private SessaoUsuario sessaoUsuario;
    
    /**
     * Constrói o gerenciador de musicas do usuário logado, inicializando as 
     * camadas de acesso.
     */
    public GerenciadorMusicas(){
        sessaoUsuario = SessaoUsuario.obterInstancia();
    }
    
    /**
     * Cadastra uma musica passada no sistema.
     * 
     * @param musica Música a ser cadastrada.
     * @throws Exception Exceção gerada caso a música já esteja cadastrada.
     */
    public void cadastrarMusica(Musica musica) throws Exception{
        boolean ret = sessaoUsuario.obterUsuario().obterMusicas().comparaMusicas(musica, sessaoUsuario.obterUsuario().obterMusicas().obterListaMusica());
        if (ret) {
            throw new Exception(I18N.obterErroMusicaJaCadastrada());
        } else {
            sessaoUsuario.obterUsuario().obterMusicas().adicionarMusica(musica);
        }                
    }
    
    /**
     * Altera uma música passada no sistema
     * 
     * @param musica Música que será alterada
     * @param indice Índice da ḿúsica da lista
     * @throws Exception Exceção gerada caso a música nova já exista na lista
     */
    public void alterarMusica(Musica musica, int indice) throws Exception{        
        boolean ret = sessaoUsuario.obterUsuario().obterMusicas().comparaMusicas(musica, sessaoUsuario.obterUsuario().obterMusicas().obterListaMusica());
        if (ret) {
            throw new Exception(I18N.obterErroMusicaJaCadastrada());
        } else { 
           sessaoUsuario.obterUsuario().obterMusicas().editarMusica(musica, indice);                        
        }          
    }        
    
    /**
     * Remove uma música passada no sistema
     * @param indice Índice da lista referente a música a ser removida
     */
    public void removerMusica(int indice){
        sessaoUsuario.obterUsuario().obterMusicas().deletarMusica(indice);
    }    
}
