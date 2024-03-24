package fr.iut.mvcshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 25)
    private String designation;

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("1000000.00")
    private double prix;

    @NotNull
    @DecimalMin("0")
    private int quantite;

    @ManyToOne
    @JoinColumn(name = "id_fournisseur")
    private Fournisseur fournisseur;

    public Produit(String designation, double prix, int quantite, Fournisseur fournisseur) {
        this.designation = designation;
        this.prix = prix;
        this.quantite = quantite;
        this.fournisseur = fournisseur;
    }

    public Produit() {
        this("", 0, 0, null);
    }

    @Override
    public String toString() {
        return "Produit [id=" + id + ", designation=" + designation + ", prix=" + prix + ", quantite=" + quantite + ", fournisseur=" + fournisseur +"]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

}
