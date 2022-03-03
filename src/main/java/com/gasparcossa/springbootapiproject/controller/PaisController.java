package com.gasparcossa.springbootapiproject.controller;

import com.gasparcossa.springbootapiproject.service.PaisService;
import com.gasparcossa.springbootapiproject.model.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Responsável por processar solicitações da API REST recebidas e dar suas
 * respostas
 */

// Essa anotacao combina @Controller e @ResponseBody para evitarmos que em todos
// os metodos de tratamento de requisicoes escrevamos a anotacao @ResponseBody
@RestController
@Validated
@RequestMapping(value = "/pais")
public class PaisController {

    @Autowired
    private PaisService paisService;

    public PaisController() {
    }

    public PaisController(PaisService paisService) {
        super();
        this.paisService = paisService;
    }

    /**
     * Endpoint de inicializacao
     * 
     * @param null
     * @return Uma mensagem de boas vindas
     **/
    @GetMapping("/")
    String inicio() {
        return ("Bem vindo a API RESTFULL: Siga os endpoints no codigo fonte para ve-los a funcionar. \nObrigado");
    }

    /**
     * Endpoint para salvar um pais
     * 
     * @param pais Instancia da classe Pais
     * @return ResponseEntity da entidade pais
     **/
    @PostMapping("/save")
    ResponseEntity<Object> create(@Valid @RequestBody Pais pais) {

        // Antes veriicamos se ja tem um pis com essa capital, para nao repetimos
        // Sabendo que tem paises com mais de uma capital
        List<Pais> paises = paisService.getAllPaises();
        int flag = 0;
        for (int i = 0; i < paises.size(); i++) {
            if (paises.get(i).getNome().equals(pais.getNome())
                    && paises.get(i).getCapital().equals(pais.getCapital())) {
                flag++;
            }
        }
        if (flag > 0) {
            List<String> error = new ArrayList<String>();
            error.add("Nao pode inserir esse pais com essa capital pois ele existe.");
            return new ResponseEntity<Object>(error, HttpStatus.OK);
        }
        return new ResponseEntity<Object>(paisService.savePais(pais), HttpStatus.OK);
    }

    /**
     * Endpoint para ler todos paises que estao na Base de dados
     * 
     * @param null
     * @return Lista dos paises requeridos
     **/
    @GetMapping("/readAll")
    public List<Pais> getAllPaises() {
        return paisService.getAllPaises();
    }

    /**
     * Endpoint para ler um pais pelo id informado
     * 
     * @param id Atributo da classe Pais
     * @return ResponseEntity da entidade pais, com o pais requerido e o seu status
     **/
    @GetMapping("/readById/{id}")
    public ResponseEntity<Pais> getPaisById(
            @PathVariable("id") long id) {
        return new ResponseEntity<Pais>(paisService.getPaisById(id), HttpStatus.OK);
    }

    /**
     * Endpoint para ler todos paises pelo nome informado
     * 
     * @param nome Atributo da classe Pais
     * @return A lista dos paises requeridos e o seu status
     **/
    @GetMapping("/readByNome/{nome}")
    public ResponseEntity<?> getAllPaisesByNome(
            @PathVariable("nome") @Pattern(regexp = "^[a-zA-Z_\\s]*$", message = "O nome do pais deve conter somente letras") String nome) {
        return new ResponseEntity<>(paisService.getAllPaisesByNome(nome), HttpStatus.OK);
    }

    /**
     * Endpoint para ler todos paises pela capital informada
     * 
     * @param capital Atributo da classe Pais
     * @return A lista dos paises requeridos e o seu status
     **/
    @GetMapping("/readByCapital/{capital}")
    public ResponseEntity<?> getAllPaisesByCapital(
            @PathVariable("capital") @Pattern(regexp = "^[a-zA-Z_\\s]*$", message = "A capital do pais deve conter somente letras") String capital) {
        return new ResponseEntity<>(paisService.getAllPaisesByCapital(capital), HttpStatus.OK);
    }

    /**
     * Endpoint para ler todos paises pela regiao informada
     * 
     * @param regiao Atributo da classe Pais
     * @return A lista dos paises requeridos e o seu status
     **/
    @GetMapping("/readByRegiao/{regiao}")
    public ResponseEntity<?> getAllPaisesByRegiao(
            @PathVariable("regiao") @Pattern(regexp = "^[a-zA-Z_\\s]*$", message = "A regiao do pais deve conter somente letras") String regiao) {
        return new ResponseEntity<>(paisService.getAllPaisesByRegiao(regiao), HttpStatus.OK);
    }

    /**
     * Endpoint para ler todos paises pela sub-regiao informada
     * 
     * @param subRegiao Atributo da classe Pais
     * @return A lista dos paises requeridos e o seu status
     **/
    @GetMapping("/readBySubRegiao/{subRegiao}")
    public ResponseEntity<?> getAllPaisesBySubRegiao(
            @PathVariable("subRegiao") @Pattern(regexp = "^[a-zA-Z_\\s]*$", message = "A sub-regiao do pais deve conter somente letras") String subRegiao) {
        return new ResponseEntity<>(paisService.getAllPaisesBySubRegiao(subRegiao), HttpStatus.OK);
    }

    /**
     * Endpoint para ler todos paises pela area informadas
     * 
     * @param area Atributo da classe Pais
     * @return A lista dos paises requeridos e o seu status
     **/
    @GetMapping("/readByArea/{area}")
    public ResponseEntity<?> getAllPaisesByArea(
            @PathVariable("area") double area) {
        return new ResponseEntity<>(paisService.getAllPaisesByArea(area), HttpStatus.OK);
    }

    /**
     * Endpoint que atualiza as informacoes de um pais pelo id informado
     * 
     * @param id   Atributo da classe Pais
     * @param pais Instancia da classe Pais
     * @return ResponseEntity da entidade pais, com o pais atualizado e o seu status
     **/
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatePais(@PathVariable("id") long id, @Valid @RequestBody Pais pais) {
        // Verificamos se o usuario nao vai inserir um pais com a mesma caital
        List<Pais> paises = paisService.getAllPaises();
        int flag = 0;
        for (int i = 0; i < paises.size(); i++) {
            if (paises.get(i).getNome().equals(pais.getNome())
                    && paises.get(i).getCapital().equals(pais.getCapital())) {
                flag++;
            }
        }
        if (flag > 0) {
            List<String> Erro = new ArrayList<String>();
            Erro.add("O pais atualizado ja contem uma capital com esse nome");
            return new ResponseEntity<Object>(Erro, HttpStatus.OK);
        }
        return new ResponseEntity<Object>(paisService.updatePais(pais, id), HttpStatus.OK);
    }

    /**
     * Endpoint para deletar um pais pelo id informado
     * 
     * @param id Atributo da classe Pais
     * @return ResponseEntity da entidade pais, com a informacao de sucesso e o seu
     *         status
     **/
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePais(
            @PathVariable("id") long id) {
        paisService.deletePais(id);
        return new ResponseEntity<String>("Pais deletado com sucesso!. ", HttpStatus.OK);

    }

}
