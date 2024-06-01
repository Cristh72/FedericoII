package it.esercitazine.federicoii.federicoii.repository;

import it.esercitazine.federicoii.federicoii.entity.Docenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocentiRepository extends JpaRepository<Docenti, Long> {
    Docenti findDocentiByCf(String cf);
}
