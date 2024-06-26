package com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities.Equipe;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_funcionario;

    private String nome;
    private String apelido;

    @Column(nullable = true, unique = true)
    private String cpf;

    private String endereco;
    private String oficio;
    private int cargaHorariaSemanal;

    @Column(nullable = true, unique = true)
    private String email;
    private String telefone;

    @ManyToMany(mappedBy = "membros", cascade = CascadeType.DETACH)
    @JsonIgnore
    private List<Equipe> equipes;

}
