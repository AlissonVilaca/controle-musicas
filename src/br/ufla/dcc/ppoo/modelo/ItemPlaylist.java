package br.ufla.dcc.ppoo.modelo;

/** Representa um item de uma PLaylist no sistema
 *
 * @author alisson-vilaca
 */
public class ItemPlaylist {
    private Musica musica;
    
    private Playlist playlist;
    
    public ItemPlaylist (Musica musica, Playlist playlist){
        this.musica = musica;
        this.playlist = playlist;
    }
    /*
    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }*/    
    
}
