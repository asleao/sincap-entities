package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadora;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/**
 * Notificacao.java
 *
 * @author 20091BSI0273 Classe que representa a notificacao de obito de um
 * paciente.
 *
 * Alterado por: Lucas Coutinho de Souza Oliveira em 09/12/2013. Alterado por
 * 20112bsi0083 em 29/11/2013
 */
@Setter
@Getter
@Entity
public class ProcessoNotificacao extends ObjetoPersistente {

    // TODO: Cria regra de negocio para geracao do codigo
    @Column(unique = true, nullable = false)
    private String codigo;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataAbertura;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataArquivamento;
    
    @Column
    private boolean arquivado;
    
    @OneToMany(fetch = FetchType.EAGER)
    private List<AtualizacaoEstado> historico;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Notificador notificador;
    
    @OneToOne
    @JoinColumn(nullable = false)
    @Cascade({CascadeType.SAVE_UPDATE})
    private Obito obito;
    
    @OneToOne
    @JoinColumn(nullable = false)
    @Cascade({CascadeType.SAVE_UPDATE})
    private Entrevista entrevista;

    @OneToOne
    @JoinColumn(nullable = false)
    @Cascade({CascadeType.SAVE_UPDATE})
    private Captacao captacao;
    
    @OneToOne
    private CausaNaoDoacao causaNaoDoacao;
}
