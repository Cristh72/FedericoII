package it.esercitazine.federicoii.federicoii.repository;

import it.esercitazine.federicoii.federicoii.entity.Anagrafica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnagraficaRepository  extends JpaRepository<Anagrafica, Long> {
}
