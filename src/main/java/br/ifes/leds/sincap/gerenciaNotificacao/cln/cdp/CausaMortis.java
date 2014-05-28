package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;


import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import lombok.Getter;
import lombok.Setter;

/**
 * CausaMortis.java
 * @author 20091BSI0273
 * Classe que representa o motivo que levou ao aobito de um paciente
 */
@Setter
@Getter
@Entity
public class CausaMortis extends ObjetoPersistente {
    @Column
    private String nome;
}
