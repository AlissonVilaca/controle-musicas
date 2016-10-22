package br.ufla.dcc.ppoo.dao.lista;

import br.ufla.dcc.ppoo.dao.MusicaDAO;
import br.ufla.dcc.ppoo.modelo.Musica;
import java.util.ArrayList;
import java.util.List;

/** 
 * Implementação do Data Access Object (Padrão de Projeto) da Musica através de
 * Lista em memória
 *
 * @author alisson-vilaca
 */
public class MusicaDAOLista implements MusicaDAO{
    
    // instância única da classe (Padrão de Projeto Singleton)
    private static MusicaDAOLista instancia;
    
    // lista em em memória das musicas de um usuario cadastradas
    private List<Musica> listaMusica;

   /**
    * Constrói o objeto lista de musicas
    */ 
    public MusicaDAOLista(){
        listaMusica = new ArrayList<Musica>();                
        
        // Dados para teste
        listaMusica.add(new Musica("teste1","teste2",12,"teste3","teste4"));
        listaMusica.add(new Musica("teste2","test4",12,"teste5", "teste5"));
        listaMusica.add(new Musica("teste3","teste5",12,"teste6","teste6"));
        listaMusica.add(new Musica("teste4","teste6",12,"teste7", "teste56"));
        listaMusica.add(new Musica("teste5","teste7",12,"teste8", "fodasse"));
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
     * Edita os dados de uma musica recebida
     * @param musica Musica a ser alterada
     * @param indice Índice da música na lista
     */
    public void alterarMusica(Musica musica, int indice) {
        listaMusica.set(indice, musica);
        
    }
    
    /**
     * Faz a verificação se a música já está na lista do usuário atual
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
     * Método ainda não implementado, Deve adicionar a música passada na lista
     * @param musica musica que será adicionada na lista
     */
    @Override
    public void adicionarMusica(Musica musica) {
        listaMusica.add(musica);
    }
    
    
    
}
