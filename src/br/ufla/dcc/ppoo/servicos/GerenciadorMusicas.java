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
    
    // atributo utilizado como camada de acesso a dados do cadastro do usuario
    private SessaoUsuario sessaoUsuario;
    
    /**
     * Constroi o gerenciador de musicas do usuário logado, inicializando as 
     * camadas de acesso.
     */
    public GerenciadorMusicas(){
        sessaoUsuario = SessaoUsuario.obterInstancia();
    }
    
    public void editarMusica(Musica musica, int indice) throws Exception{        
        boolean ret = sessaoUsuario.obterUsuario().obterMusicas().comparaMusicas(musica, sessaoUsuario.obterUsuario().obterMusicas().obterListaMusica());
        if (ret) {
            throw new Exception(I18N.obterErroMusicaJaCadastrada());
        } else { 
           sessaoUsuario.obterUsuario().obterMusicas().alterarMusica(musica, indice);
            
            
        }      
    
    }
    
     /**
     * Cadastra uma musica passada no sistema.
     * 
     * @param usuario Música a ser cadastrada.
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
    
}
