package fr.iut.mvcshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.iut.mvcshop.model.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
    
    @Query("select f from Fournisseur f where f.nom like :motsCles")
    Page<Fournisseur> rechercherFournisseurParNom(@Param("motsCles") String mc, Pageable pageable);
}
