package it.esercitazine.federicoii.federicoii.repository;

import it.esercitazine.federicoii.federicoii.entity.CorsiDiLaurea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorsiDiLaureRepository extends JpaRepository<CorsiDiLaurea, Long> {
    CorsiDiLaurea findCorsiDiLaureaByNome(String nome);

}
