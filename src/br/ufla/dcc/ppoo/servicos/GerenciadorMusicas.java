package br.ufla.dcc.ppoo.servicos;

import br.ufla.dcc.ppoo.dao.lista.MusicaDAOLista;
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
    private final SessaoUsuario sessaoUsuario;
    // objeto usado para recuperar a lista de musicas 
    private final MusicaDAOLista music ; 
    
    /**
     * Constrói o gerenciador de musicas do usuário logado, inicializando as 
     * camadas de acesso.
     */
    public GerenciadorMusicas(){
        sessaoUsuario = SessaoUsuario.obterInstancia();
        music = MusicaDAOLista.obterInstancia();
    }
    
    /**
     * Cadastra uma musica passada no sistema.
     * 
     * @param musica Música a ser cadastrada.
     * @throws Exception Exceção gerada caso a música já esteja cadastrada.
     */
    public void cadastrarMusica(Musica musica) throws Exception{                
        boolean ret = music.comparaMusicas(musica);
        if (ret) {
            throw new Exception(I18N.obterErroMusicaJaCadastrada());
        } else {
            music.adicionarMusica(musica);
        }                
    }
    
    /**
     * Altera uma música passada no sistema
     * 
     * @param musica Música que será alterada
     * @param selecionada Nome da musica a ser alterada.
     * @throws Exception Exceção gerada caso a música nova já exista na lista
     */
    public void alterarMusica(Musica musica, String selecionada) throws Exception{        
        if (musica.obterTitulo().equals(selecionada)){
             music.editarMusica(musica, selecionada, sessaoUsuario.obterUsuario().obterLogin());
        }else{
            boolean ret = music.comparaMusicas(musica);
            if (ret) {
                throw new Exception(I18N.obterErroMusicaJaCadastrada());
            } else { 
               music.editarMusica(musica, selecionada, sessaoUsuario.obterUsuario().obterLogin());
               //sessaoUsuario.obterUsuario().obterMusicas().editarMusica(musica, indice);                        
            }   
        }
    }        
    
    /**
     * Remove uma música passada no sistema
     * @param titulo Titulo da música a ser removida
     */
    public void removerMusica(String titulo){
        music.deletarMusica(titulo,sessaoUsuario.obterUsuario().obterLogin());
       // sessaoUsuario.obterUsuario().obterMusicas().deletarMusica(indice);
    }    
}
