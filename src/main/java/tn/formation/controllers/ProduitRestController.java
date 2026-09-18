package tn.formation.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.formation.entities.Produit;
import tn.formation.services.IProduitService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/produit")
public class ProduitRestController {

    @Autowired
    IProduitService produitService;

    // http://localhost:8089/SpringMVC/produit/retrieve-all-produits
    @GetMapping("/retrieve-all-produits")

    public List<Produit> getProduits() {
        List<Produit> list = produitService.retrieveAllProduits();
        return list;
    }

    // http://localhost:8089/SpringMVC/produit/retrieve-produit/8
    @GetMapping("/retrieve-produit/{produit-id}")

    public Produit retrieveRayon(@PathVariable("produit-id") Long produitId) {
        return produitService.retrieveProduit(produitId);
    }

    /* Ajouter en produit tout en lui affectant la catégorie produit et le stock associés */

    @PostMapping("/add-produit")

    public Produit addProduit(@RequestBody Produit p) {
        Produit produit = produitService.addProduit(p);
        return produit;
    }


    @DeleteMapping("/remove-produit/{produit-id}")

    public void removeProduit(@PathVariable("produit-id") Long produitId) {
        produitService.deleteProduit(produitId);
    }


    @PutMapping("/modify-produit")

    public Produit modifyProduit(@RequestBody Produit p) {
        return produitService.updateProduit(p);
    }

    /*
     * Si le responsable magasin souhaite modifier le stock du produit il peut
     * le faire en l'affectant au stock en question
     */
    // http://localhost:8089/SpringMVC/produit/assignProduitToStock/1/5
    @PutMapping(value = "/assignProduitToStock/{idProduit}/{idStock}")
    public void assignProduitToStock(@PathVariable("idProduit") Long idProduit, @PathVariable("idStock") Long idStock) {
        produitService.assignProduitToStock(idProduit, idStock);
    }

}