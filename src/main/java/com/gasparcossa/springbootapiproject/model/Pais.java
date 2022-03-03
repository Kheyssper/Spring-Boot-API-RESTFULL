package com.gasparcossa.springbootapiproject.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

/**
 * Entidade com propriedades do um pais
 */
@Data
@Entity
@Table(name = "pais")
public class Pais {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy ="increment")
    private long id;

    @Column(name = "nome")
    @NotNull(message = "O nome do pais nao deve ser nullo")
    @NotBlank(message = "O nome do pais tem que ter pelo menos uma letra")
    @Pattern(regexp = "^[a-zA-Z_\\s]*$", message = "O nome do pais deve conter somente letras")
    private String nome;

    @Column(name = "capital")
    @NotNull(message = "O nome do pais nao deve ser nullo")
    @NotBlank(message = "A capital do pais tem que ter pelo menos uma letra")
    @Pattern(regexp = "^[a-zA-Z_\\s]*$", message = "A capital do pais deve conter somente letras")
    private String capital;

    @Column(name = "regiao")
    @NotNull(message = "A regiao do pais nao deve ser nullo")
    @NotBlank(message = "A regiao do pais tem que ter pelo menos uma letra ")
    @Pattern(regexp = "^[a-zA-Z_\\s]*$", message = "A regiao do pais deve conter somente letras")
    private String regiao;

    @Column(name = "subRegiao")
    @NotNull(message = "A sub-regiao do pais nao deve ser nullo")
    @NotBlank(message = "A sub-regiao do paistem que ter pelo menos uma letra")
    @Pattern(regexp = "^[a-zA-Z_\\s]*$", message = "A sub-regiao do pais deve conter somente letras")
    private String subRegiao;

    @Column(name = "area")
    @DecimalMin(value = "0.4", inclusive = true, message = "O menor pais tem 0.49 de area")
    @NotNull(message = "A area do pais nao deve ser nullo")
    // @Pattern(regexp = "^\\d*\\.\\d+$", message = "A area do pais deve ser do
    // formato decimal")
    // @DecimalMax(value = "18,000,000", message = "O maior pais tem 17,098,242 de
    // area")
    // Medido em km^2, (Quilometros ao quadrado)
    private double area;

}
