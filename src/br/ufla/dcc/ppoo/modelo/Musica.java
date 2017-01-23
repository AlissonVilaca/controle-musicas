package br.ufla.dcc.ppoo.modelo;

/** Representa uma música no sistema
 *
 * @author alisson-vilaca
 */
public class Musica {    
    // Titulo da música
    private String titulo;    
    // Artista da musica
    private String artista;            
    // Ano de lançamento da música
    private int ano;    
    // Genero da música
    private String genero;
    // Letra da música
    private String letra;
    // Usuario a quem a música pertencem
    private Usuario usuario;// Alterei esse atributo de String para Usuario, faz mais senido para a orientação à objeto
    
    private boolean marcada;
    
    /**
     * Constrói uma música a partir de todos seus atributos.
     * 
     * @param titulo
     * @param artista
     * @param ano
     * @param genero 
     * @param letra 
     * @param usuario Usuario a que a musica pertence
     */
    public Musica(String titulo, String artista, int ano, String genero, String letra, Usuario usuario){
        this.titulo = titulo;        
        this.artista = artista;
        this.ano = ano;
        this.genero = genero;    
        this.letra = letra;  
        this.usuario = usuario;
        marcada = false;
    }

    /**
     * Método utilizado para obter o título da música
     * 
     * @return Título da música
     */
    public String obterTitulo() {
        return titulo;
    }
    
    /**
     * Método utilizado para obter o artista da música
     * 
     * @return Artista da música
     */
    public String obterArtista() {
        return artista;
    }
    
    /**
     * Método utilizado para obter o ano da música
     * 
     * @return Ano da música
     */
    public int obterAno() {
        return ano;
    }

    /**
     * Método utilizado para obter o gênero da música
     * 
     * @return Gênero da música
     */
    public String obterGenero() {
        return genero;
    } 
    
    /**
     * Método utilizado para obter o título da música
     * 
     * @return Título da música
     */
    public String obterLetra() {
        return letra;
    } 
    
    /**
     * Método utilizado para obter o usuário que contém a música
     * 
     * @return Usuario da música
     */
    public Usuario obterUsuario() {
        return usuario;
    } 

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }     
    
    /**
     * Faz a verificação se a música já está na lista do usuário atual
     * 
     * @param musica Musica a ser verificada
     * @return true para musicas com titulo e login iguais
     */
    public boolean comparaMusicas (Musica u){
        if ((titulo.equals(u.obterTitulo()))      // este if deveria estar na classe musica, nã cabe ao if fazer
                             && (usuario == u.obterUsuario())//essa cmparação
                    ) {
            return true;
        } else {            
            return false;
        }        
    }
    
    public void marcar(){
        marcada = true;
    }
    
    public void desmarcar(){
        marcada = false;
    }
    
    public boolean estaMarcada(){
        return marcada;
    }
}
