package fr.iut.mvcshop.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import fr.iut.mvcshop.model.Fournisseur;
import fr.iut.mvcshop.model.Produit;
import fr.iut.mvcshop.repository.ProduitRepository;
import fr.iut.mvcshop.repository.FournisseurRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProduitController {

    private ProduitRepository produitRepository;
    private FournisseurRepository fournisseurRepository;

    public ProduitController(ProduitRepository produitRepository, FournisseurRepository fournisseurRepository) {
        this.produitRepository = produitRepository;
        this.fournisseurRepository = fournisseurRepository;
    }

    @GetMapping("/produits")
    public String listerProduits(Model model, 
                                @RequestParam(value="p", defaultValue = "0") int page, 
                                @RequestParam(value="s", defaultValue = "5") int size,
                                @RequestParam(value="mc", defaultValue = "") String mots,
                                @RequestParam(value="act", defaultValue = "") String action,
                                @RequestParam(value="id", defaultValue = "0") Long id){

        Pageable pageable = PageRequest.of(page, size);
        Page<Produit> produitsPage;

        if(mots.length()>0) {
            produitsPage = this.produitRepository.rechercherProduitsParNom("%"+mots+"%", pageable);
        } else {
            produitsPage = this.produitRepository.findAll(pageable);
        }

        if(id>0 && (action.equals("new") || action.equals("mod"))) {
            this.produitRepository.findById(id).ifPresent(produit -> model.addAttribute("produit", produit));
        }

        model.addAttribute("produits", produitsPage.getContent());
        model.addAttribute("infoPage", produitsPage);
        model.addAttribute("mots", mots);
        model.addAttribute("action", action);
        return "produits";
    }

    @GetMapping("/produitDelete")
    public String supprimerProduits(int p, int s, String mc, Long id, RedirectAttributes redirectAttributes) {

        this.produitRepository.deleteById(id);

        redirectAttributes.addAttribute("p", p);
        redirectAttributes.addAttribute("s", s);
        redirectAttributes.addAttribute("mc", mc);
        redirectAttributes.addAttribute("act", "del");

        return "redirect:/produits";
    }

    @GetMapping("/produitEdit")
    public String modifierProduits(Model model, int p, int s, String mc, @RequestParam(defaultValue = "0") Long id) {

        if(id > 0) {
            Optional<Produit> optionalProduit = this.produitRepository.findById(id);
            if(optionalProduit.isPresent()) {
                Produit produit = optionalProduit.get();
                model.addAttribute("produit", produit);
            } else {
                return "redirect:/produits";
            }
        } else {
            model.addAttribute("produit", new Produit());
        }

       

        List<Fournisseur> fournisseurs = this.fournisseurRepository.findAll();
        model.addAttribute("fournisseurs", fournisseurs);

        model.addAttribute("p", p);
        model.addAttribute("s", s);
        model.addAttribute("mc", mc);
        return "produitEdit";
    }

    @PostMapping("/produitSave")   
    public String sauverProduit(@Valid Produit produit, BindingResult bindingResult, int p, int s, String mc, Model model, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("p", p);
            model.addAttribute("s", s);
            model.addAttribute("mc", mc);
           

            List<Fournisseur> fournisseurs = this.fournisseurRepository.findAll();
            model.addAttribute("fournisseurs", fournisseurs);

            return "produitEdit";
        }

        if(produit.getId()==null) {
            redirectAttributes.addAttribute("act", "new");
        } else {
            redirectAttributes.addAttribute("act", "mod");
        }

        this.produitRepository.save(produit);

        redirectAttributes.addAttribute("p", p);
        redirectAttributes.addAttribute("s", s);
        redirectAttributes.addAttribute("mc", mc);
        redirectAttributes.addAttribute("id", produit.getId());

        return "redirect:/produits";
    }

}
