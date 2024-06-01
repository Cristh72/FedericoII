package it.esercitazine.federicoii.federicoii.service;

import it.esercitazine.federicoii.federicoii.dto.*;
import it.esercitazine.federicoii.federicoii.entity.Anagrafica;
import it.esercitazine.federicoii.federicoii.entity.CorsiDiLaurea;
import it.esercitazine.federicoii.federicoii.entity.Docenti;
import it.esercitazine.federicoii.federicoii.entity.Studenti;
import it.esercitazine.federicoii.federicoii.mapper.*;
import it.esercitazine.federicoii.federicoii.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FedericoIIService {
    @Autowired
    DocentiSenzaCDLMapper docentiSenzaCDLMapper;
    @Autowired
    DocentiMapper docentiMapper;
    @Autowired
    DocentiRepository docentiRepository;
    @Autowired
    AnagraficaRepository anagraficaRepository;
    @Autowired
    StudentiRepository studentiRepository;
    @Autowired
    StudentiMapper studentiMapper;
    @Autowired
    CorsiDiLaureRepository corsiDiLaureRepository;
    @Autowired
    CorsiDiLaureaMapper corsiDiLaureaMapper;
    @Autowired
    CdlConStudentiDTOMapper cdlConStudentiDTOMapper;
    @Autowired
    StudenteTrovatoPerCFMapper studenteTrovatoPerCFMapper;
    @Autowired
    CdlStudentiEDocentiMapper cdlStudentiEDocentiMapper;
    @Autowired
    CdlDocentiMapper cdlDocentiMapper;
    public List<StudentiDTO> getAllStudenti() {
        List<StudentiDTO> listaStudenti = studentiMapper.studentiToDTOs(studentiRepository.findAll());
        System.out.println("Sono dentro getAllStudenti nel sevice!!!!!!!!!!!!!!!!!!!!!!!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("pausa finita!!!!!!!!!!!!!!!");
        return listaStudenti;
    }

    public List<CdlStudentiEDocentiDTO> getallcorsidilaureaconstudenti() {
        List<CdlStudentiEDocentiDTO> listaCdl = cdlStudentiEDocentiMapper.cdlToDTOs(corsiDiLaureRepository.findAll());
        System.out.println("Sono dentro getallcorsidilaureaconstudenti() nel sevice");
        return listaCdl;
    }

    public StudentiDTO getStudenteByCf(String cf) {
        StudentiDTO studenteTrovato = studentiMapper.studenteToDTO(studentiRepository.findStudentiByCf(cf));
        return studenteTrovato;
    }

    public CdlDocentiDTO getCdlByNome(String nome) {
        CdlDocentiDTO cdlTrovato = cdlDocentiMapper.cdlToDTO(corsiDiLaureRepository.findCorsiDiLaureaByNome(nome));
        return cdlTrovato;
    }


    public StudenteTrovatoPerCFDTO getControllareStudenti(String cf) {
        StudenteTrovatoPerCFDTO studenteTrovato = studenteTrovatoPerCFMapper.studenteTrovatoToDTO(studentiRepository.findStudentiByCf(cf));
        return studenteTrovato;
    }

    public StudenteTrovatoPerCFDTO creareNewStudent(StudenteTrovatoPerCFDTO nuovoStudente) {
        Studenti studenteDaInserire = studenteTrovatoPerCFMapper.studenteTrovatoToEntenty(nuovoStudente);
       // Anagrafica anaEnty = studenteDaInserire.getAnagrafica();
      //  anaEnty = anagraficaRepository.save(anaEnty);
       // studenteDaInserire.setAnagrafica(anaEnty);
        studenteDaInserire=studentiRepository.save(studenteDaInserire);

        return  studenteTrovatoPerCFMapper.studenteTrovatoToDTO(studenteDaInserire);
    }

    //-Iscrivere uno studente già registrato ad un cdl dato il suo CF e il nome del cdl, assicurandosi che non sia già iscritto a qualche altro cdl
    public StudentiDTO creareNewStudenteACdl(String cf, String nomeCdl) {
        Studenti studenteTrovato = studentiRepository.findStudentiByCf(cf);
        System.out.println(studenteTrovato.getId());
        CorsiDiLaurea corsiDiLaurea = corsiDiLaureRepository.findCorsiDiLaureaByNome(nomeCdl);
        System.out.println(corsiDiLaurea.getId());
        if(studenteTrovato.getCorsiDiLaurea()==null){
            studenteTrovato.setCorsiDiLaurea(corsiDiLaurea);
            studenteTrovato=studentiRepository.save(studenteTrovato);
            System.out.println(studenteTrovato.getCorsiDiLaurea().getId());
            System.out.println("studente inscrito a CDL");
            StudentiDTO studentiInseritoDTO = studentiMapper.studenteToDTO(studenteTrovato);
            return studentiInseritoDTO;
        }else if(!studenteTrovato.getCorsiDiLaurea().getNome().equals(null) || studenteTrovato.getCorsiDiLaurea().getNome().equals(nomeCdl)){
            System.out.println("lo studente e gia inscrito");
            return null;
        }else {
            System.out.println("Lo studente non sta inscrito nel database");
            return null;
        }
    }

    public CorsiDiLaureaDTO crearNewCdl(CorsiDiLaureaDTO newCorso) {
        CorsiDiLaurea cdlDaInserire = corsiDiLaureaMapper.corsiToEntity(newCorso);
        cdlDaInserire=corsiDiLaureRepository.save(cdlDaInserire);

        return corsiDiLaureaMapper.corsiToDTO(cdlDaInserire);
    }

    public Boolean anulareStudente(String cf, String cdl) {
        Studenti studenteTrovato = studentiRepository.findStudentiByCf(cf);
        System.out.println(studenteTrovato.getId());
        CorsiDiLaurea corsiDiLaurea = corsiDiLaureRepository.findCorsiDiLaureaByNome(cdl);
        System.out.println(corsiDiLaurea.getNome());
        if(studenteTrovato.getCf().equals(cf) && (corsiDiLaurea.getNome().equals(studenteTrovato.getCorsiDiLaurea().getNome()))){
            studentiRepository.delete(studenteTrovato);
            return true;
        }else if(studenteTrovato.getCf().equals(cf) || (corsiDiLaurea.getNome().equals(studenteTrovato.getCorsiDiLaurea().getNome()))){
            System.out.println("lo studente è inscrito in altro cdl");
            return false;
        }else if(studenteTrovato==null) {
            System.out.println("Lo studente non sta inscrito nel database");
            return false;
        }else {return false;}
    }

    public List<DocentiSenzaCDLDTO> getAllTeacher() {
        List<DocentiSenzaCDLDTO> listaDocenti = docentiSenzaCDLMapper.docentiSenzaToDTOs(docentiRepository.findAll());
        return listaDocenti;
    }

    public List<DocentiDTO> getAllTeacherConCdl() {
        List<DocentiDTO> listaDocenti = docentiMapper.docentiToDTOs(docentiRepository.findAll());
        return listaDocenti;
    }

    //Controllare se un docente, dato il suo CF è già responsabile di un cdl
    public Boolean getResponsabile(String cf) {

        Docenti docenteRisponsabile = docentiRepository.findDocentiByCf(cf);
        if (docenteRisponsabile.getCorsiDiLaurea()==null||docenteRisponsabile.equals(null)){
            return false;
        }else if (docenteRisponsabile.getCorsiDiLaurea()!=null) {
            return true;
        }else{
            return false;
        }
    }

    public DocentiSenzaCDLDTO creareNewProf(DocentiSenzaCDLDTO nuovoProf) {
        Docenti newProf = docentiMapper.docentiToEntity(nuovoProf);
        Anagrafica anaEnty = newProf.getAnagrafica();
        anaEnty=anagraficaRepository.save(anaEnty);
        newProf.setAnagrafica(anaEnty);
        newProf=docentiRepository.save(newProf);

        return docentiSenzaCDLMapper.docentiSenzaToDTO(newProf);
    }

    public DocentiDTO creareNewProfACdl(String cf, String cdl) {
        Docenti docenteTrovato = docentiRepository.findDocentiByCf(cf);
        CorsiDiLaurea corsiDiLaurea = corsiDiLaureRepository.findCorsiDiLaureaByNome(cdl);
        if (docenteTrovato.getCorsiDiLaurea()==null){
            docenteTrovato.setCorsiDiLaurea(corsiDiLaurea);
            docenteTrovato=docentiRepository.save(docenteTrovato);
            return docentiMapper.docentiToDTO(docenteTrovato);
        }else if (!docenteTrovato.getCorsiDiLaurea().getNome().equals(null) || docenteTrovato.getCorsiDiLaurea().getNome().equals(cdl)){
            System.out.println("Il Docente è gia inscrito");
            return null;
        }else {
            System.out.println("Il docente non è inscrito nel database");
            return null;
        }
    }

    public Boolean anulareProf(String cf, String cdl) {
        Docenti docenteTrovato = docentiRepository.findDocentiByCf(cf);
        CorsiDiLaurea corsiDiLaurea = corsiDiLaureRepository.findCorsiDiLaureaByNome(cdl);
        if(docenteTrovato==null){
            System.out.println("Lo Docente non sta inscrito nel database");
            return false;
        }else if(docenteTrovato.getCf().equals(cf) || (corsiDiLaurea.getNome().equals(docenteTrovato.getCorsiDiLaurea().getNome()))){
            System.out.println("Il Docente non può essere espulso");
            return false;
        }else if (docenteTrovato.getCf().equals(cf) && (corsiDiLaurea.getNome().equals(docenteTrovato.getCorsiDiLaurea().getNome()))){
            docentiRepository.delete(docenteTrovato);
            return true;
        }else {return false;}
    }

    public Boolean aggiornareNumStu() {

        List<CorsiDiLaurea> corso = corsiDiLaureRepository.findAll();
        List<CdlConStudentiDTO> crorsoStudente = cdlConStudentiDTOMapper.cdlToDTOs(corso);
        corso = cdlConStudentiDTOMapper.cdlToEntities(crorsoStudente);

        for (CorsiDiLaurea c:corso) {
            c.setNumStudenti(cdlConStudentiDTOMapper.numeris(c.getStudenti()));
            corsiDiLaureRepository.save(c);
        }

        return true;
    }

    public List<StudenteTrovatoPerCFDTO> getAllStudentiNoCdl() {
        List<StudenteTrovatoPerCFDTO> listaStudenti = studenteTrovatoPerCFMapper.studenteTrovatoToDTOs(studentiRepository.findAll());
        return listaStudenti;
    }
}
