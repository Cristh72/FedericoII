package it.esercitazine.federicoii.federicoii.controller;

import it.esercitazine.federicoii.federicoii.dto.*;
import it.esercitazine.federicoii.federicoii.entity.CorsiDiLaurea;
import it.esercitazine.federicoii.federicoii.entity.Studenti;
import it.esercitazine.federicoii.federicoii.mapper.DocentiMapper;
import it.esercitazine.federicoii.federicoii.mapper.StudentiMapper;
import it.esercitazine.federicoii.federicoii.repository.DocentiRepository;
import it.esercitazine.federicoii.federicoii.service.FedericoIIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/v0")
public class FedericoIIController {
    @Autowired
    FedericoIIService federicoIIService;

    @Autowired
    StudentiMapper studentiMapper;

//Restituire tutti gli studenti con il proprio cdl
    //-Modifica l’API 1. e 3. includendo anche la tabella anagrafica (opzionale)

    @GetMapping(value = "/getallstudenti")
    public ResponseEntity<List<StudentiDTO>> getAllStudenti(){
        List<StudentiDTO> listaStudenti = federicoIIService.getAllStudenti();
        System.out.println("Sono dentro il metodo getAllStudenti nel controller");
        aggiornareNumStu();
        return new ResponseEntity<>(listaStudenti, HttpStatus.FOUND);
    }

    @GetMapping(value = "/getallstudentinocdl")
    public ResponseEntity<List<StudenteTrovatoPerCFDTO>> getAllStudentiNoCdl(){
        List<StudenteTrovatoPerCFDTO> listaStudenti = federicoIIService.getAllStudentiNoCdl();
        System.out.println("Sono dentro il metodo getAllStudenti nel controller");
        aggiornareNumStu();
        return new ResponseEntity<>(listaStudenti, HttpStatus.FOUND);
    }


//Restituire tutti i corsi di laurea con gli studenti iscritti
//-Modifica l’API 2. e 4. facendo restituire anche il docente assegnato ad ogni cdl (opzionale)
    @GetMapping(value = "/getallcorsidilaureaconstudenti")
    public ResponseEntity<List<CdlStudentiEDocentiDTO>> getAllCorsiDiLaureaConStudenti(){
        List<CdlStudentiEDocentiDTO> listaCdl = federicoIIService.getallcorsidilaureaconstudenti();
        return new ResponseEntity<>(listaCdl, HttpStatus.FOUND);
    }


//-Restituire uno studente dato il suo CF includendo anche il cdl nel caso -fosse già iscritto
    @GetMapping(value = "/getstudentipercf")
    public ResponseEntity<StudentiDTO> getStudentByCf(@RequestParam (name = "cf") String cf){
        if (cf.equals(null)||cf.isEmpty()) {
            return null;
        }
        StudentiDTO studenteTrovato =  federicoIIService.getStudenteByCf(cf);
        return new ResponseEntity<>(studenteTrovato, HttpStatus.BAD_REQUEST);
    }

    //-Restituire un cdl dato il suo nome, senza restituire anche gli studenti
    @GetMapping(value = "/getcdlbynome/{nome}")
    public ResponseEntity<CdlDocentiDTO> getCdlByNome(@PathVariable String nome){
        if (nome==null || nome.isEmpty()){
            return null;
        }
        CdlDocentiDTO cdlTrovato = federicoIIService.getCdlByNome(nome);
        return new ResponseEntity<>(cdlTrovato,HttpStatus.FOUND);
    }


//-Controllare se uno studente, dato il suo CF, è già iscritto o meno ad un cdl
    @GetMapping(value = "/getstudenticf/{cf}")
    public ResponseEntity<StudenteTrovatoPerCFDTO> getControllareStudenti(@PathVariable String cf){
        StudenteTrovatoPerCFDTO studenteTrovatoPerCFDTOTrovato = federicoIIService.getControllareStudenti(cf);
        if (studenteTrovatoPerCFDTOTrovato!=null) {
            System.out.println("Lo studente inscrito");
            return new ResponseEntity<>(studenteTrovatoPerCFDTOTrovato, HttpStatus.FOUND);
        }else {
            System.out.println("Lo studente No inscrito");
            return null;
        }
    }

    //-Controllare se un docente, dato il suo CF è già responsabile di un cdl (opzionale)
    @GetMapping(value = "/getresponsabile/{cf}")
    public ResponseEntity<String> getResponsabile(@PathVariable String cf){

        Boolean docentiResponsabile = federicoIIService.getResponsabile(cf);

        if (docentiResponsabile==true){
            return new ResponseEntity<>("Il Docente e risponsabile di un corso di Laurea",HttpStatus.FOUND);
        }else return new ResponseEntity<>("Il docente ancora non è risponsabile",HttpStatus.BAD_REQUEST);
    }


//-Restituire tutti i docenti con il proprio cdl sia che ce l’abbiano o meno (opzionale)
    @GetMapping(value = "/getallteacher")
    public ResponseEntity<List<DocentiSenzaCDLDTO>> getAllTeacher(){
        List<DocentiSenzaCDLDTO> listaDocenti = federicoIIService.getAllTeacher();
        return new ResponseEntity<>(listaDocenti,HttpStatus.FOUND);
    }
    @GetMapping(value = "/getallteacherconcdl")
    public ResponseEntity<List<DocentiDTO>> getAllTeacherConCdl(){
        List<DocentiDTO> listaDocenti = federicoIIService.getAllTeacherConCdl();
        return new ResponseEntity<>(listaDocenti, HttpStatus.FOUND);
    }

//    POST
//-Registrare un nuovo studente (senza cdl"corso di laurea")
    @PostMapping(value = "/newstudent")
    public ResponseEntity<StudenteTrovatoPerCFDTO> creareNewStudent(@RequestBody StudenteTrovatoPerCFDTO nuovoStudente){
        if(nuovoStudente==null){
            return null;
        }
        StudenteTrovatoPerCFDTO  studenteAppenaInserito = federicoIIService.creareNewStudent(nuovoStudente);
        aggiornareNumStu();
        return new ResponseEntity<>(studenteAppenaInserito, HttpStatus.CONFLICT);
    }

//-Iscrivere uno studente già registrato ad un cdl dato il suo CF e il nome del cdl, assicurandosi che non sia già iscritto a qualche altro cdl

    @PostMapping(value = "/newstudenteacdl/{cf}/{cdl}")
    public ResponseEntity<StudentiDTO> creareNewStudenteACdl(@PathVariable String cf, @PathVariable String cdl){
        StudentiDTO studentiInseritoDTO = federicoIIService.creareNewStudenteACdl(cf, cdl);//Prendo il cdl e lo setto nello studente e lo salvo
        aggiornareNumStu();//Aggiorno DB
        studentiInseritoDTO.getCorsiDiLaureaDTO().setNumStudenti(studentiInseritoDTO.getCorsiDiLaureaDTO().getNumStudenti()+1);//Aggiorno Dto
        return new ResponseEntity<>(studentiInseritoDTO, HttpStatus.BAD_REQUEST);
    }
//    Creare un nuovo cdl

    @PostMapping(value = "/newcdl")
    public ResponseEntity<CorsiDiLaureaDTO> crearNewCdl(@RequestBody CorsiDiLaureaDTO newCorso) {
        CorsiDiLaureaDTO corsoInserito = federicoIIService.crearNewCdl(newCorso);
        return new ResponseEntity<>(corsoInserito,HttpStatus.BAD_REQUEST);
    }
//-Annullare l’iscrizione di uno studente ad un cdl dato il suo CF e il nome del cdl, assicurandosi che sia effettivamente iscritto a quel cdl.
@PostMapping(value = "/anulareStudente/{cf}/{cdl}")
public ResponseEntity<String> anulareStudente(@PathVariable String cf, @PathVariable String cdl){
    Boolean studenteCancellato = federicoIIService.anulareStudente(cf, cdl);
    aggiornareNumStu();
    if(studenteCancellato==true){
        return new ResponseEntity<>("Studente espulso", HttpStatus.FOUND);
    }else {
        return new ResponseEntity<>("Studente non trovato", HttpStatus.BAD_REQUEST);
    }
}

//-Iscrivere un nuovo docente (opzionale)

    @PostMapping(value = "/newprof")
    public ResponseEntity<DocentiSenzaCDLDTO> creareNewProf(@RequestBody DocentiSenzaCDLDTO nuovoProf){
        if(nuovoProf==null){
            return null;
        }
        DocentiSenzaCDLDTO  docenteAppenaInserito = federicoIIService.creareNewProf(nuovoProf);
        return new ResponseEntity<>(docenteAppenaInserito, HttpStatus.CONFLICT);
    }
//-Assegnare un docente ad un cdl già esistente dato il suo CF e il nome del cdl (opzionale)

    @PostMapping(value = "/newprofacdl/{cf}/{cdl}")
    public ResponseEntity<DocentiDTO> creareNewProfACdl(@PathVariable String cf, @PathVariable String cdl){
        DocentiDTO docenteInseritoDTO = federicoIIService.creareNewProfACdl(cf, cdl);
        return new ResponseEntity<>(docenteInseritoDTO, HttpStatus.BAD_REQUEST);
    }
//-Rimuovere un docente da un cdl dato il suo CF e il nome del cdl (opzionale)
    @PostMapping(value = "/anulareprof/{cf}/{cdl}")
    public ResponseEntity<String> anulareProf(@PathVariable String cf, @PathVariable String cdl){
        Boolean profCancellato = federicoIIService.anulareProf(cf, cdl);
        if(profCancellato==true){
            ResponseEntity<String> risposta = new ResponseEntity<>("Docente espulso", HttpStatus.FOUND);
            return risposta;
        }else {
            return new ResponseEntity<>("Docente non trovato", HttpStatus.BAD_REQUEST);
        }
    }

    public Boolean aggiornareNumStu(){
        Boolean aggiorna = federicoIIService.aggiornareNumStu();
        return true;
    }
}
