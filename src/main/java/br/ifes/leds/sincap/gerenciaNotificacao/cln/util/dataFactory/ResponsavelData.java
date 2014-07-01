/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.endereco.cgd.BairroRepository;
import br.ifes.leds.reuse.endereco.cgd.CidadeRepository;
import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.reuse.endereco.cgd.EstadoRepository;
import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ResponsavelRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoCivil;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Parentesco;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Responsavel;
import java.util.Calendar;
import java.util.List;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aleao
 */
@Service
public class ResponsavelData {

    @Autowired
    private ResponsavelRepository responsavelRepository;
    @Autowired
    TelefoneRepository telefoneRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    BairroRepository bairroRepository;
    @Autowired
    CidadeRepository cidadeRepository;
    @Autowired
    EstadoRepository estadoRepository;
    @Autowired
    private Factory fabrica;

    private Responsavel responsavel;
    private Sexo sexo;
    private EstadoCivil estadoCivil;
    private Calendar dataNascimento;
    private Calendar dataInternacao;
    private List<String> listaProfissao;
    private List<Parentesco> listaParentesco;
    private List<EstadoCivil> listaEstadoCivil;
    private List<Sexo> listaSexo;
    private Endereco endereco;
    private Telefone telefone;
    private Listas list = Listas.INSTANCE;

    public void criaResponsavelRandom(DataFactory df, Integer qtdRes) {
        for (int i = 0; i < qtdRes; i++) {

            criarResponsavel(df);
            enderecoRepository.save(endereco);

            telefoneRepository.save(telefone);

            responsavelRepository.save(responsavel);
        }
    }

    public Responsavel criarResponsavel(DataFactory df) {
        responsavel = fabrica.criaObjeto(Responsavel.class);
        dataNascimento = Calendar.getInstance();
        dataInternacao = Calendar.getInstance();
        listaProfissao = list.getListProf();
        listaSexo = list.getListSex();
        listaEstadoCivil = list.getListEst();
        listaParentesco = list.getListPar();
        endereco = fabrica.criaObjeto(Endereco.class);
        telefone = fabrica.criaObjeto(Telefone.class);

        gerarDadosResponsavel(df);

        gerarDadosEndereco(df);
        // Telefone
        telefone.setNumero("(" + df.getNumberText(2) + ")"
                + df.getNumberText(4) + "-" + df.getNumberText(4));
        responsavel.setTelefone(telefone);

        return responsavel;
    }

    private void gerarDadosEndereco(DataFactory df) {
        // Endereco
        endereco.setLogradouro(df.getStreetName());
        endereco.setEstado(estadoRepository.findOne(new Long(1)));
        endereco.setCidade(cidadeRepository.findOne(new Long(1)));
        endereco.setBairro(bairroRepository.findOne(new Long(1)));
        endereco.setNumero(df.getNumberText(5));
        endereco.setComplemento(df.getStreetSuffix());
        endereco.setCep(df.getNumberText(8));
        responsavel.setEndereco(endereco);
    }

    private void gerarDadosResponsavel(DataFactory df) {
        // Dados do Paciente.
        responsavel.setNome(df.getName());
        responsavel.setNacionalidade("Brasileiro");
        responsavel.setEstadoCivil(df.getItem(listaEstadoCivil));
        responsavel.setSexo(df.getItem(listaSexo));
        dataNascimento.setTime(df.getBirthDate());
        dataInternacao.setTime(df.getDateBetween(df.getDate(2000, 01, 01),
                df.getDate(2014, 01, 01)));
        responsavel.setDocumentoSocial(df.getNumberText(9));
        responsavel.setProfissao(df.getItem(listaProfissao));
        responsavel.setParentesco(df.getItem(listaParentesco));
    }

}