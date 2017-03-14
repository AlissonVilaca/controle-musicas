package br.ufla.dcc.ppoo.dao;

import br.ufla.dcc.ppoo.modelo.Usuario;
import java.util.List;

/**
 * Interface do Data Access Object (Padrão de Projeto) do Usuário
 * 
 * @author Paulo Jr. e Julio Alves
 */
public interface UsuarioDAO {
    /**
     * Retorna o usuário a partir de seu login
     * 
     * @param login Login do usuário a ser retornado.
     * @return Usuário correspondente ao login passado.
     */
    public Usuario obterUsuarioPeloLogin(String login);
    
    /**
     * Cadastra o usuário passado.
     * 
     * @param usuario Usuário a ser cadastrado.
     */
    public void adicionarUsuario(Usuario usuario);
    
    /**
     * Retorna lista de usuarios que tem nome igual às palavras da lista
     * @param palavra lista de palavras
     * @return 
     */
    public List<Usuario> buscaUsuarios(List<String> palavra);
    
    /**
     * Seta o Usuario que o Usuario selecionou para ser exibido
     */
    public void setarExibido(Usuario usuario);
    
    /**
     * Retorna o nome do usuario que o Usuario selecionou para ser exibido
     */
    public String getUsuarioExibido();
    
    /**
     * Retorna o Usuario que o Usuario selecionou para ser exibido
     */
    public Usuario getExibido();
    
    /**
     * Soma a ultima avaliação à avaliação geral do usuario
     * @param u usuario
     * @param pont valor a ser somado
     */
    public void somarAvaliacao(Usuario u, int pont);
}
