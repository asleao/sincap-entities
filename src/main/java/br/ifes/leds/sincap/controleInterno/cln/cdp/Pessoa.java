package br.ifes.leds.sincap.controleInterno.cln.cdp;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaRealizadaDoacaoAutorizada;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaRealizadaDoacaoNaoAutorizada;
import br.ifes.leds.sincap.validacao.groups.obito.ObitoPNI;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

/**
 * Pessoa.java
 *
 * @author 20091BSI0273
 *         Classe abstrata que representa uma Pessoa, herda de objetoPersistente.
 */
@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class Pessoa extends ObjetoPersistente {

    @Column
    @NotNull(groups = {ObitoPNI.class})
    private String nome;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = true)
    private Telefone telefone;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = true)
    private Endereco endereco;
}
