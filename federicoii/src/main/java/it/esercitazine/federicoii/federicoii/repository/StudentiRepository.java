package it.esercitazine.federicoii.federicoii.repository;

import it.esercitazine.federicoii.federicoii.entity.Studenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentiRepository extends JpaRepository<Studenti, Long> {
    Studenti findStudentiByCf( String cf);


}
