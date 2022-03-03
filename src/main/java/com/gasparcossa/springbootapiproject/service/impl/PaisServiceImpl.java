package com.gasparcossa.springbootapiproject.service.impl;

import com.gasparcossa.springbootapiproject.service.PaisService;
import com.gasparcossa.springbootapiproject.repository.PaisRepository;
import com.gasparcossa.springbootapiproject.exception.RecursoNaoEncontradoException;
import com.gasparcossa.springbootapiproject.model.Pais;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe que implementa a classe da camada de servico principal, a classe
 * PaisService.
 */
@Service
// Podemos nao por a anotacao @Transacional pois o Spring Boot faz isso por si
// so.

// Impl no identificador PaisServiceImpl significa Implementation.
public class PaisServiceImpl implements PaisService {

    @Autowired
    private PaisRepository paisRepository;

    public PaisServiceImpl() {
    }

    public PaisServiceImpl(PaisRepository paisRepository) {
        super();
        this.paisRepository = paisRepository;
    }

    /**
     * Salva o pais na Base de dados
     * 
     * @param pais Objecto que contem as informacoes da classe Pais
     * @return Devolve o pais salvo
     **/
    @Override
    public Pais savePais(Pais pais) {
        return paisRepository.save(pais);
    }

    /**
     * 
     * Retorna todos paises da Base de dados
     * 
     * @param null
     * @return Devolve todos paises da Base de dados
     **/
    @Override
    public List<Pais> getAllPaises() {
        return paisRepository.findAll();
    }

    /**
     * Le um pais na Base de dados pelo id
     * 
     * @param id Atributo da classe Pais
     * @return Devolve o pais requerido na Base de dados
     **/
    @Override
    public Pais getPaisById(long id) {
        return paisRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Pais", "Id", id));
    }

    /**
     * Retorna todos paises da Base de dados de acordo com o nome indicada
     * 
     * @param nome Atributo da classe Pais
     * @return Devolve os paises da Base de dados
     **/
    @Override
    public List<Pais> getAllPaisesByNome(String nome) {
        if (!paisRepository.findByNome(nome).isEmpty()) {
            return paisRepository.findByNome(nome);
        }
        throw new RecursoNaoEncontradoException("Pais", "nome", nome);
    }

    /**
     * Retorna todos paises da Base de dados de acordo com a capital indicada
     * 
     * @param capital Atributo da classe Pais
     * @return Devolve os paises na Base de dados
     **/
    @Override
    public List<Pais> getAllPaisesByCapital(String capital) {
        if (!paisRepository.findByCapital(capital).isEmpty()) {
            return paisRepository.findByCapital(capital);
        }
        throw new RecursoNaoEncontradoException("Pais", "capital", capital);
    }

    /**
     * 
     * Retorna todos paises da Base de dados de acordo com a regiao
     * informada
     * 
     * @param subRegiao Atributo da classe Pais
     * @return Devolve os paises na Base de dados
     **/
    @Override
    public List<Pais> getAllPaisesByRegiao(String regiao) {
        if (!paisRepository.findByRegiao(regiao).isEmpty()) {
            return paisRepository.findByRegiao(regiao);
        }
        throw new RecursoNaoEncontradoException("Pais", "regiao", regiao);
    }

    /**
     * Retorna todos paises da Base de dados de acordo com a sub-regiao
     * informada
     * 
     * @param subRegiao Atributo da classe Pais
     * @return Devolve os paises na Base de dados
     **/
    @Override
    public List<Pais> getAllPaisesBySubRegiao(String subRegiao) {
        if (!paisRepository.findBySubRegiao(subRegiao).isEmpty()) {
            return paisRepository.findBySubRegiao(subRegiao);
        }
        throw new RecursoNaoEncontradoException("Pais", "subregiao", subRegiao);
    }

    /**
     * Retorna todos paises na Base de dados de acordo com a area informada
     * 
     * @param area Atributo da classe Pais
     * @return Devolve os paises na Base de dados
     **/
    @Override
    public List<Pais> getAllPaisesByArea(double area) {
        if (!paisRepository.findByArea(area).isEmpty()) {
            return paisRepository.findByArea(area);
        }
        throw new RecursoNaoEncontradoException("Pais", "subregiao", area);
    }

    /**
     * Atualiza um pais da Base de dados pelo id informado
     * 
     * @param pais Objecto que contem as informacoes da classe Pais
     * @param id   Atributo da classe Pais
     * @return O Pais atualizado
     **/
    @Override
    public Pais updatePais(Pais pais, long id) {
        // Checamos se o pais com esse id dado existe na base de dados
        Pais paisExistente = paisRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pais", "Id", id));
        // Fim da checagem
        paisExistente.setNome(pais.getNome());
        paisExistente.setCapital(pais.getCapital());
        paisExistente.setRegiao(pais.getRegiao());
        paisExistente.setSubRegiao(pais.getSubRegiao());
        paisExistente.setArea(pais.getArea());
        // Salvamos na Base de dados
        paisRepository.save(paisExistente);
        return paisExistente;
    }

    /**
     * Deleta um pais da Base de dados pelo id informado
     * 
     * @param pais Objecto que contem as informacoes da classe Pais
     * @param id   Atributo da classe Pais
     * @return O Pais deletado
     **/
    @Override
    public void deletePais(long id) {
        // Checamos se o pais com esse id dado existe na base de dados
        paisRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pais", "Id", id));
        // Fim da checagem
        paisRepository.deleteById(id);
    }

       /**
     * Verifica se ja existe um pais com essa capital que queremos inserir
     * @param pais Instancia de Pais
     * @return true se existe e false se nao
     */
    @Override
    public boolean sameCapitalInPaises(Pais pais) {
        List<Pais> paises = this.getAllPaises();
        int flag = 0;
        for (int i = 0; i < paises.size(); i++) {
            if (paises.get(i).getCapital().equals(pais.getCapital())) {
                flag++;
            }
        }
        if (flag > 0) {
            return true;
        }
        return false;
    }
}
