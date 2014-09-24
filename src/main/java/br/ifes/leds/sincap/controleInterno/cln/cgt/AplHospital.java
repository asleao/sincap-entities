package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;

import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import org.springframework.data.domain.Pageable;



@Service
public class AplHospital {

    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private TelefoneRepository telefoneRepository;
    @Autowired
    private ProcessoNotificacaoRepository notificacaoRepository;

    /** Método para cadastrar um Hospital.
    *   @param hospital - objeto Hospital. */
    
    public void cadastrar(Hospital hospital) {

        telefoneRepository.save(hospital.getTelefone());
        enderecoRepository.save(hospital.getEndereco());
        hospitalRepository.save(hospital);
    }
    
    /** Método para atualizar um Hospital já cadastrado.
    *   @param hospital - objeto Hospital. */
    
    public void update(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    /** Método para obter um Hospital pelo nome.
    *   @param nome - nome do Hospital.
    *   @return Objeto Hospital. 
    */
    
    public Hospital obter(String nome) {
        return hospitalRepository.findByNome(nome);
    }
    
    /** Método para obter um Hospital pelo id.
    *   @param id - id do Hospital.
    *   @return String - nome do Hospital. 
    */
    
    public Hospital obter(Long id) {
        return hospitalRepository.findOne(id);
    }
    
    /** Método para obter um Hospital ?.
    *   @param pageable - objeto Pageable.
    *   @return List - lista de Hospitais. 
    */
    
    public List<Hospital> obter(Pageable pageable) {
        return hospitalRepository.findAll(pageable).getContent();
    }
    
    /** Método para obter um Hospital uma lista de todos os hospitais.
    *   @return List - lista de Hospitais. 
    */
    
    public List<Hospital> obter() {
        return hospitalRepository.findAll();
    }
    
    /** Método para obter a quantidade hospitais existentes.
    *   @return Long - Quantidade de Hospitais. 
    */
    public Long quantidade() {
        return hospitalRepository.count();
    }

    /** Método para adicionar um setor a um hospital.
     * @param setor - objeto setor.
     * @param idHospital - id do hospital.
    */
    public void addSetor(Setor setor, Long idHospital) {
        Hospital hospital = this.hospitalRepository.findOne(idHospital);

        if (hospital.getSetores() == null) {
            hospital.setSetores(new HashSet<Setor>());
        }

        hospital.addSetor(setor);
        this.hospitalRepository.save(hospital);
    }
    
    /** Método para remover um setor de um hospital.
     * @param setor - objeto Setor.
     * @param idHospital - id do Hospital.
    */
    public void removerSetor(Setor setor, Long idHospital) {
        Hospital hospital = this.hospitalRepository.findOne(idHospital);
        hospital.removeSetor(setor);
    }

    /** Método para remover um hospital e seus setores.
     * @param hospital - Objeto Hospital.
     */
    public void exlcuir(Hospital hospital) {
        hospitalRepository.delete(hospital);
        telefoneRepository.save(hospital.getTelefone());
        enderecoRepository.save(hospital.getEndereco());
    }
}
