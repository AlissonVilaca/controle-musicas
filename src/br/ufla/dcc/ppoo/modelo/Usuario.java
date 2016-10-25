package br.ufla.dcc.ppoo.modelo;

import br.ufla.dcc.ppoo.dao.lista.MusicaDAOLista;
import java.util.Arrays;

/**
 * Representa um usuário do sistema.
 * 
 * @author Paulo Jr. e Julio Alves
 */
public class Usuario {
    // login do usuário
    private String login;
    // senha do usuário
    private char[] senha;
    // nome do usuário
    private String nome;
    // Lista de musicas do usuario. Ao invés de criarmos uma lista de músicas
    // que contenha todas as músicas de todos os usuários, resolvemos criar
    // uma lista de musicas como atributo para cada usuário. Dessa forma nós 
    // poderemos fazer o controle das músicas do usuário que está logado 
    // no momento, utilizando um objeto SessãoUsuario na classe  
    // GerenciadorMusicas
    private MusicaDAOLista musicaDAO;
    
    /**
     * Constrói um usuário a partir de seu login, senha e nome.
     * 
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @param nome Nome do usuário.    
     */
    public Usuario(String login, char[] senha, String nome) {
        this.login = login;
        this.senha = Arrays.copyOf(senha, senha.length);
        this.nome = nome;
        musicaDAO = new MusicaDAOLista();                 
    }
    
    /**
     * Constrói usuário em branco, apenas para ser usado como
     * objeto de manipulação
     */
    public Usuario(){}
    
    /**
     * Constrói um usuário a partir de seu login e senha (deixa nome vazio).
     * 
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     */
    public Usuario(String login, char[] senha) {
        this(login, senha, "");
    }

    /**
     * Retorna o login do usuário.
     * 
     * @return O login do usuário.
     */
    public String obterLogin() {
        return login;
    }

    /**
     * Retorna a senha do usuário.
     * 
     * @return A senha do usuário.
     */
    public char[] obterSenha() {
        return senha;
    }

    /**
     * Retorna o nome do usuário.
     * 
     * @return O nome do usuário.
     */
    public String obterNome() {
        return nome;
    }
    
    /**
     * Retorna o objeto DAO da lista de musicas.
     * 
     * @return O nome do usuário.
     */
    public MusicaDAOLista obterMusicas() {
        return musicaDAO;
    }
      
}
