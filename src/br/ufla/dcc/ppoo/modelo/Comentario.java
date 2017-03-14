package br.ufla.dcc.ppoo.modelo;

import java.io.Serializable;

/**
 * Classe que representa os comentarios das playlists.
 * @author Pichau
 */
public class Comentario implements Serializable {
    private String comentario;
    private String nomeUsuario;
    private long data;
    
    
    public Comentario (String comentario, String nomeUsuario) {
        this.comentario = comentario;
        this.nomeUsuario = nomeUsuario;
        this.data = System.currentTimeMillis();        
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }
    
    
}
