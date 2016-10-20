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
    
    /**
     * Constrói uma música a partir de todos seus atributos.
     * 
     * @param titulo
     * @param artista
     * @param ano
     * @param genero 
     * @param letra 
     */
    public Musica(String titulo, String artista, int ano, String genero, String letra){
        this.titulo = titulo;        
        this.artista = artista;
        this.ano = ano;
        this.genero = genero;    
        this.letra = letra;  
    }

   /* public void setTitulo(String titulo) {
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
*/
    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getAno() {
        return ano;
    }

    public String getGenero() {
        return genero;
    } 
    
    public String getLetra() {
        return letra;
    } 
    
    
}
