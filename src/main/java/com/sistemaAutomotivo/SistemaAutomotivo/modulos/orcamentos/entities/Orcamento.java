package com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.entities.enums.Status;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.ServicoOrcamento;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.ProdutoOrcamento;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.entities.Veiculo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_orcamento;

    private LocalDate data;

    private Status status;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_responsavel", referencedColumnName = "id_funcionario")
    private Funcionario responsavel;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_veiculo", referencedColumnName = "id_veiculo")
    private Veiculo veiculo;

    @OneToMany(mappedBy = "orcamento", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ProdutoOrcamento> produtos_orcamentos;

    @OneToMany(mappedBy = "orcamento", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ServicoOrcamento> servicos_orcamentos;
}
