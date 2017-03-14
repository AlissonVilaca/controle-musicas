package br.ufla.dcc.ppoo.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/** Representa uma playlist no sistema
 *
 * @author alisson-vilaca
 */
public class Playlist implements Serializable {
    //nome da playlist
    private String nome;
    //usuario da playlist
    private Usuario usuario;
    //lista de palavras chaves da play list
    private List<String> listaPalavras;
    //lista de musicas da playlist
    private List<Musica> listaMusicas;
    // visibilidade da paylist
    private boolean visilidade;
    // pontuação da playlist
    private int pontuacao;
    //Lista de Usuarios que já avaliaram
    private List<Usuario> usuariosQueAvaliaram;
    //Lista de comentários da Playlist
    private List<Comentario> listaComentarios;
    
    public Playlist(String nome, Usuario usuario,  List<String> listaPalavras, List<Musica> listaMusicas, boolean visilidade){
        this.usuario = usuario;
        this.nome = nome;
        this.listaPalavras = listaPalavras; 
        this.listaMusicas = listaMusicas;
        this.visilidade = visilidade;
        pontuacao = 0;
        usuariosQueAvaliaram = new ArrayList<Usuario>();
        listaComentarios = new ArrayList<Comentario>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isVisilidade() {
        return visilidade;
    }

    public void setVisilidade(boolean visilidade) {
        this.visilidade = visilidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setListaMusicas(List<Musica> listaMusicas) {
        this.listaMusicas = listaMusicas;
    }

    public List<String> getPalavras() {
        return listaPalavras;
    }
    
    public List<Musica> getMusicas() {
        return Collections.unmodifiableList(listaMusicas);
    }

    public void setListaPalavras(List<String> listaPalavras) {
        this.listaPalavras = listaPalavras;
    }
    
    /**
     * Apaga musica de uma playlist
     * @param m 
     */
    public void apagarMusica(Musica m){
        for (Musica u : listaMusicas){            
            if (u == m){
                listaMusicas.remove(u);
            }
        }    
    }
    
    /**
     * Compara duas playlists
     * @param u
     * @return 
     */
    public boolean comparaPlaylist (Playlist u){
        if ((nome.equals(u.getNome()))      // este if deveria estar na classe musica, nã cabe ao if fazer
                             && (usuario == u.getUsuario())//essa cmparação
                    ) {
            return true;
        } else {            
            return false;
        }           
    }
    
    /**
     * Incrementa a pontuação dalista de acordo com o parametro
     * @param pontuacao 
     */
    public void pontuar(int pontuacao){
        this.pontuacao += pontuacao;    
    }
    
    /**
     * Retorna a pontuação da Lista
     * @return 
     */
    public int getPontuacao() {
        return pontuacao;
    }
    
    /**
     * Adiciona o usuario do parâmetro à lista de usuarios que avaliaram
     * @param u 
     */
    public void adicionarUsuario(Usuario u){
        usuariosQueAvaliaram.add(u);
    }

    public List<Usuario> getUsuariosQueAvaliaram() {
        return usuariosQueAvaliaram;
    }

    public void setUsuariosQueAvaliaram(List<Usuario> usuariosQueAvaliaram) {
        this.usuariosQueAvaliaram = usuariosQueAvaliaram;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
    
    public void comentar(Comentario c) {
        listaComentarios.add(c);
        for (Comentario g : listaComentarios){
            System.out.println(g.getComentario());
        }
    }
    
    public List<Comentario> getComentarios(){        
        return Collections.unmodifiableList(listaComentarios);
    }

    public void setListaComentarios(List<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }
    
    
}
