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

    /**
     * Constrói música em branco, apenas para ser usada como
     * objeto de manipulação
     */
    public Musica() {}

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
    
}
