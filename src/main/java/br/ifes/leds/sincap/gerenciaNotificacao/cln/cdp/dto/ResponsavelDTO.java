/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import br.ifes.leds.reuse.endereco.cdp.dto.EnderecoDTO;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoCivil;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Parentesco;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.TelefonesResponsavelInterface;
import br.ifes.leds.sincap.validacao.annotations.TelefoneResponsavelConsistentes;
import lombok.*;
import lombok.experimental.Builder;

/**
 *
 * @author leds-6752
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"telefone", "telefone2"})
@TelefoneResponsavelConsistentes
public class ResponsavelDTO implements TelefonesResponsavelInterface {

    private Long id;
    private String nome;
    private String nacionalidade;
    private String profissao;
    private String documentoSocial;
    private EstadoCivil estadoCivil;
    private Sexo sexo;
    private Parentesco parentesco;
    private Telefone telefone;
    private Telefone telefone2;
    private EnderecoDTO endereco;
}
