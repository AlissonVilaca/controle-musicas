package br.ufla.dcc.ppoo.modelo;

import java.util.ArrayList;
import java.util.List;

/** Representa uma playlist no sistema
 *
 * @author alisson-vilaca
 */
public class Playlist {
    private String nome;
    
    private Usuario usuario;
    
    private List<String> listaPalavras;
    
    private List<Musica> listaMusicas;
    
    public Playlist(String nome, Usuario usuario,  List<String> listaPalavras, List<Musica> listaMusicas){
        this.usuario = usuario;
        this.nome = nome;
        this.listaPalavras = listaPalavras; 
        this.listaMusicas = listaMusicas;
    }

    public String getNome() {
        return nome;
    }
/*
    public void setNome(String nome) {
        this.nome = nome;
    }
*/
    public Usuario getUsuario() {
        return usuario;
    }
/*
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
*/
    public List<String> getPalavras() {
        return listaPalavras;
    }
    
    public List<Musica> getMusicas() {
        return listaMusicas;
    }
/*
    public void setListaPalavras(List<String> listaPalavras) {
        this.listaPalavras = listaPalavras;
    }*/
    
}
