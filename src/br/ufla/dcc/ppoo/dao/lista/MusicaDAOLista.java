package br.ufla.dcc.ppoo.dao.lista;

import br.ufla.dcc.ppoo.dao.MusicaDAO;
import br.ufla.dcc.ppoo.modelo.Musica;
import java.util.ArrayList;
import java.util.List;

/** Implementação do DAO Musica através de Lista em memória
 *
 * @author alisson-vilaca
 */
public class MusicaDAOLista implements MusicaDAO{
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
     * Método ainda não implementado, Deve adicionar a música passada na lista
     * @param musica musica que será adicionada na lista
     */
    @Override
    public void adicionarMusica(Musica musica) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
