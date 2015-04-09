package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * @author aleao on 24/03/2015.
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Comentario extends ObjetoPersistente  {

    @OneToOne
    private Funcionario funcionario;

    @Temporal(TIMESTAMP)
    private Calendar dataComentario;

    @Column
    private String momento;

    @Column
    private String descricao;

    @ManyToOne
    @JoinTable(
            name = "processonotificacao_comentario",
            joinColumns = @JoinColumn(name = "comentarios_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "processonotificacao_id", referencedColumnName = "id")
    )
    private ProcessoNotificacao processo;
}
