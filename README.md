 # Projet SpringBoot - Guilhamassé Quentin - 3B

## Améliorations

### Entité

Création de l'entité `Fournisseur` avec ses attributs : 
 *id : Long
 *nom : String ( 3 à 30 caractères)
 *siteweb : String (null ou 10 à 100 caractères)

Modification de l'entité `Produit` avec : 
 * id_fournisseur : Ajout d'une référence vers Fournisseur (peut être null)

### Page des produits
 *Affichage du fournisseur dans la liste des produits avec lien cliquable vers le fournisseur en question
 *Modification possible du fournisseur quand on modifie un produit
 *Possibilité d'ajouter un fournisseur quand on crée un nouveau produit

### Page des fournisseurs
 *Affichage des fournisseurs avec leur(s) produit(s) associés avec lien cliquable vers le produit en question
 *Modification possible d'un fournisseur
 *Possibilité de supprimer un fournisseur (seulement si il n'a pas de produit associé)

### Création de données fictives
*Utilisation de CommandLineRunner pour créer : 
 * 3 fournisseurs
 * un produit associé à chaque fournisseur
