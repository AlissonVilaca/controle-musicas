package br.ufla.dcc.ppoo.servicos;

import br.ufla.dcc.ppoo.dao.UsuarioDAO;
import br.ufla.dcc.ppoo.dao.lista.UsuarioDAOLista;
import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.modelo.Usuario;
import br.ufla.dcc.ppoo.seguranca.SessaoUsuario;
import java.util.List;

/**
 * Classe que representa a camada de negócios de cadastro de usuários. Permite
 * cadastrar e autenticar um usuário.
 * 
 * @author Paulo Jr. e Julio Alves
 */
public class GerenciadorUsuarios {

    // atributo utilizado como camada de acesso a dados do cadastro de usuários
    private final UsuarioDAO repositorioUsuario;
    // atributo para controle de sessão (autenticação do usuário)
    private final SessaoUsuario sessaoUsuario;
    /**
     * Constroi o gerenciador de usuários, inicializando as camadas de acesso a 
     * dados e de sessão.
     */
    public GerenciadorUsuarios() {
        repositorioUsuario
                = UsuarioDAOLista.obterInstancia();
        sessaoUsuario
                = SessaoUsuario.obterInstancia();
    }

    /**
     * Tenta autenticar o usuário passado no sistema.
     * 
     * @param usuario Usuário a ser autenticado
     * @throws Exception Exceção gerada caso o usuário não possa ser autenticado,
     * ou seja, usuário não existe ou senha incorreta.
     */
    public void autenticarUsuario(Usuario usuario) throws Exception {
        Usuario usuarioCadastrado = 
                repositorioUsuario.obterUsuarioPeloLogin(usuario.obterLogin());
        
        sessaoUsuario.alterarUsuario(usuarioCadastrado, usuario.obterSenha());
    }

    /**
     * Cadastra o usuário passado no sistema.
     * 
     * @param usuario Usuário a ser cadastrado.
     * @throws Exception Exceção gerada caso o usuário já esteja cadastrado.
     */
    public void cadastrarUsuario(Usuario usuario) throws Exception {
        Usuario ret = repositorioUsuario.obterUsuarioPeloLogin(usuario.obterLogin());
        if (ret != null) {
            throw new Exception(I18N.obterErroUsuarioJaCadastrado());
        }
        repositorioUsuario.adicionarUsuario(usuario);
    }
    
    /**
     * Retorna lista de usuarios que tem nome igual às palavras da lista
     * @param palavra lista de palavras
     * @return 
     */
    public List<Usuario> buscaUsuarios(List<String> palavra){
       return repositorioUsuario.buscaUsuarios(palavra);
    }
    
    /**
     * Seta o Usuario que o Usuario selecionou para ser exibido
     */
    public void setarExibido(Usuario usuario){
       repositorioUsuario.setarExibido(usuario);
    }  
    
    /**
     * Retorna o nome do usuario que o Usuario selecionou para ser exibido
     */
    public String getUsuarioExibido(){
       return repositorioUsuario.getUsuarioExibido();
    }  
    
    /**
     * Retorna o Usuario que o Usuario selecionou para ser exibido
     */
    public Usuario getExibido(){
       return repositorioUsuario.getExibido();
    }  
    
    /**
     * Soma a ultima avaliação à avaliação geral do usuario
     * @param u usuario
     * @param pont valor a ser somado
     */
    public void somarAvaliacao(Usuario u, int pont){
        
        repositorioUsuario.somarAvaliacao(u,pont);
    }
    
    /**
     * Carrega os dados dos Usuarios de um arquivo binário
     */    
    public void carregarDadosUsuarios(){
        repositorioUsuario.carregarDadosUsuarios();
    }
    
    /**
     * Salva os dados dos Usuarios em um arquivo binário
     */
    public void salvarDadosUsuarios(){        
        repositorioUsuario.salvarDadosUsuarios();
    }
}
