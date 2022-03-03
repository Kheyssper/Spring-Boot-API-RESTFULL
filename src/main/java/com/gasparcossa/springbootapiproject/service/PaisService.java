package com.gasparcossa.springbootapiproject.service;

import com.gasparcossa.springbootapiproject.model.Pais;
import java.util.List;

/**
 * Classe que define a logica de negocio CRUD para a nossa API REST
 */
public interface PaisService {

   Pais savePais(Pais pais);

   List<Pais> getAllPaises();

   Pais getPaisById(long id);

   List<Pais> getAllPaisesByNome(String nome);

   List<Pais> getAllPaisesByCapital(String capital);

   List<Pais> getAllPaisesByRegiao(String regiao);

   List<Pais> getAllPaisesBySubRegiao(String subRegiao);

   List<Pais> getAllPaisesByArea(double area);

   Pais updatePais(Pais pais, long id);

   void deletePais(long id);
   
   boolean sameCapitalInPaises(Pais pais);

}
