/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufla.dcc.ppoo.dao;

import br.ufla.dcc.ppoo.modelo.Musica;
import br.ufla.dcc.ppoo.modelo.Usuario;
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

}