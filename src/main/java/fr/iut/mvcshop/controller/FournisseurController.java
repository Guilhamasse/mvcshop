package fr.iut.mvcshop.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import fr.iut.mvcshop.model.Fournisseur;
import fr.iut.mvcshop.repository.FournisseurRepository;
import fr.iut.mvcshop.repository.ProduitRepository;
import jakarta.validation.Valid;

@Controller
public class FournisseurController {
    
    private FournisseurRepository fournisseurRepository;
    private ProduitRepository produitRepository;

    public FournisseurController(FournisseurRepository fournisseurRepository, ProduitRepository produitRepository){
        this.fournisseurRepository = fournisseurRepository;
        this.produitRepository = produitRepository;
    }

    @GetMapping("/fournisseurs")
    public String listerFournisseurs(Model model,
                                @RequestParam(value="p", defaultValue = "0") int page,
                                @RequestParam(value="s", defaultValue = "1") int size,
                                @RequestParam(value="act", defaultValue = "") String action,
                                @RequestParam(value="mc", defaultValue = "") String mots,
                                @RequestParam(value="id", defaultValue = "0") Long id) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Fournisseur> fournisseursPage;

        if(mots.length()>0) {
            fournisseursPage = this.fournisseurRepository.rechercherFournisseurParNom("%"+mots+"%", pageable);
        } else {
            fournisseursPage = this.fournisseurRepository.findAll(pageable);
        }
        
        if(id>0 && (action.equals("new") || action.equals("mod"))) {
            this.fournisseurRepository.findById(id).ifPresent(fournisseur -> model.addAttribute("fournisseur", fournisseur));
        }

        model.addAttribute("action", action);
        model.addAttribute("mots", mots);
        model.addAttribute("fournisseurs", fournisseursPage.getContent());
        model.addAttribute("infoPage", fournisseursPage);
        return "fournisseurs";
    }

    @GetMapping("/fournisseurDelete")
    public String supprimerFournisseur(int p, int s, String mc, Long id, RedirectAttributes redirectAttributes) {

        this.fournisseurRepository.deleteById(id);

        redirectAttributes.addAttribute("p", p);
        redirectAttributes.addAttribute("s", s);
        redirectAttributes.addAttribute("mc", mc);
        redirectAttributes.addAttribute("act", "del");

        return "redirect:/fournisseurs";
    }

      @GetMapping("/fournisseurEdit")
    public String editerFournisseur(Model model, @RequestParam(defaultValue = "0") Long id) {

        if (id > 0) {
            Optional<Fournisseur> optionalFournisseur = fournisseurRepository.findById(id);
            if (optionalFournisseur.isPresent()) {
                Fournisseur fournisseur = optionalFournisseur.get();
                model.addAttribute("fournisseur", fournisseur);
            } else {
                return "redirect:/fournisseurs";
            }
        } else {
            model.addAttribute("fournisseur", new Fournisseur());
        }
        return "fournisseurEdit";
    }

    @PostMapping("/fournisseurSave")
    public String sauverFournisseur(@Valid Fournisseur fournisseur, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "fournisseurEdit";
        }

        if (fournisseur.getId() == null) {
            redirectAttributes.addAttribute("act", "new");
        } else {
            redirectAttributes.addAttribute("act", "mod");
        }

        this.fournisseurRepository.save(fournisseur);

        redirectAttributes.addAttribute("id", fournisseur.getId());

        return "redirect:/fournisseurs";
    }


}
