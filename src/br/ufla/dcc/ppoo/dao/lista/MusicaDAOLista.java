package br.ufla.dcc.ppoo.dao.lista;
import br.ufla.dcc.ppoo.modelo.Musica;
import java.util.ArrayList;
import java.util.List;

/** 
 * Implementação da lista de músicas, que é um atributo da Classe Usuário
 *
 * @author alisson-vilaca
 */
public class MusicaDAOLista {    
    
    // lista em em memória das musicas de um usuario cadastradas
    private List<Musica> listaMusica;

   /**
    * Constrói o objeto lista de musicas
    */ 
    public MusicaDAOLista(){
        listaMusica = new ArrayList<Musica>();                
    }
     
    /**
     * Retorna a lista de músicas do usuário 
     * 
     * @return lista de musicas do usuário
     */
    public List<Musica> obterListaMusica() {
        return listaMusica;
    }        
    
    /**
     * Faz a verificação se a música já está na lista do usuário atual
     * 
     * @param musica Musica a ser verificada
     * @param lista Lista de músicas do usuário atual
     * @return 
     */
    public boolean comparaMusicas (Musica musica, List<Musica> lista){
        for (Musica u : lista) {
            if ((musica.obterTitulo().equals(u.obterTitulo()))
                    && (musica.obterAno() == u.obterAno()) 
                    && (musica.obterArtista().equals(u.obterArtista())) 
                    && (musica.obterGenero().equals(u.obterTitulo()))
                    && (musica.obterLetra().equals(u.obterLetra()))
                    ) {
                return true;
            }
        }        
        return false;
    }
    
    /**
     * Adiciona a música passada na lista
     * 
     * @param musica Musica que será adicionada na lista
     */
//    @Override
    public void adicionarMusica(Musica musica) {
        listaMusica.add(musica);
    }
    
    /**
     * Edita os dados de uma musica recebida
     * 
     * @param musica Musica a ser alterada
     * @param indice Índice da música na lista
     */
    public void editarMusica(Musica musica, int indice) {
        listaMusica.set(indice, musica);        
    }
    
    /**
     * Deleta uma musica recebida
     * 
     * @param indice Índice da música na lista de músicas do usuário
     */
    public void deletarMusica(int indice) {
        listaMusica.remove(indice);
    }        
    
}
