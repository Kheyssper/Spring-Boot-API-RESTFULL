package com.gasparcossa.springbootapiproject.repository;

import java.util.List;
import java.util.Optional;
import com.gasparcossa.springbootapiproject.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Interface que define operações CRUD e mais em nossa entidade
 * Pais. Lógica para a camada de persistência
 */

// Nao precisamos de por a anotacao de @Repository pois o JpaRepository ja
// implementa essa anotacao internamente
public interface PaisRepository extends JpaRepository<Pais, Long>, JpaSpecificationExecutor<Pais> {

    /**
     * Ordena uma lista de paises pelo nome
     * 
     * @param nome Atributo do objecto Pais
     * @return Uma lista de paises
     */
    List<Pais> findByNome(String nome);

    /**
     * Ordena uma lista de paises pela capital
     * 
     * @param capital Atributo do objecto Pais
     * @return Uma lista de paises
     */
    List<Pais> findByCapital(String capital);

    /**
     * Ordena uma lista de paises pela regiao
     * 
     * @param regiao Atributo do objecto Pais
     * @return Uma lista de paises
     */
    List<Pais> findByRegiao(String regiao);

    /**
     * Ordena uma lista de paises pela sub-regiao
     * 
     * @param subRegiao Atributo do objecto Pais
     * @return Uma lista de paises
     */
    List<Pais> findBySubRegiao(String subRegiao);

    /**
     * Ordena uma lista de paises pela area
     * 
     * @param area Atributo do objecto Pais
     * @return Uma lista de paises
     */
    List<Pais> findByArea(double area);

    /**
     * Encontra um pais pelo id informado
     * 
     * @param id Atributo do objecto Pais
     * @return Um pais || Um nullo
     */
    Optional<Pais> findById(long id);
}
