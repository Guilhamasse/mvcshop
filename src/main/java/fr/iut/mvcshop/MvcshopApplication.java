package fr.iut.mvcshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.iut.mvcshop.model.Fournisseur;
import fr.iut.mvcshop.model.Produit;
import fr.iut.mvcshop.repository.FournisseurRepository;
import fr.iut.mvcshop.repository.ProduitRepository;

@SpringBootApplication
public class MvcshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcshopApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase( FournisseurRepository fournisseurRepository, ProduitRepository produitRepository) {
		return args -> {

			Fournisseur four1 = new Fournisseur("four1");
			Fournisseur four2 = new Fournisseur("four2");
			Fournisseur four3 = new Fournisseur("four3");

			fournisseurRepository.save(four1);
			fournisseurRepository.save(four2);
			fournisseurRepository.save(four3);

			produitRepository.save(new Produit("Chocolat extrait orange", 19.95, 12, four1));
			produitRepository.save(new Produit("Chocolat amandes", 9.2, 0, four2));
			produitRepository.save(new Produit("Chocolat ecorce citron", 18, 114, four3));
			produitRepository.save(new Produit("Chocolat blanc", 998.2, 14, null));
			produitRepository.save(new Produit("Chocolat caramel", 9, 8, null));
			produitRepository.save(new Produit("Chocolat piment", 8.2, 0, null ));
			produitRepository.save(new Produit("Chocolat AOP Blagnac", 1.23, 1, null ));
			produitRepository.findAll().forEach(System.out::println);
		};
	}

}
