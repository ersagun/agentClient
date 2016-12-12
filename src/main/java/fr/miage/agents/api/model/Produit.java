package fr.miage.agents.api.model;

import java.io.Serializable;

/**
 * Created by Guillaume on 07/11/2016.
 */
public class Produit implements Serializable {
    public long idProduit;

    public String nomProduit;

    public String descriptionProduit;

    public float prixProduit;

    public Categorie idCategorie;

    public String marque;
}
