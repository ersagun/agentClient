package main.java.fr.miage.agents.api.message.interClients;

import main.java.fr.miage.agents.api.message.Message;
import main.java.fr.miage.agents.api.message.TypeMessage;
import main.java.fr.miage.agents.api.model.Produit;

import java.util.HashMap;

/**
 * Created by jerome on 06/12/2016.
 */
public class ReponseEchange extends Message{

    public boolean possedeProduit;

    // Integer pour la quantité
    public HashMap<Produit, Integer> produits;
    public double prix;


    public ReponseEchange() {
        super(TypeMessage.ReponseEchange);
    }

    public ReponseEchange(boolean possedeProduit, HashMap<Produit, Integer> produits, int proposeQuantite, double prix) {
        super(TypeMessage.ReponseEchange);
        this.possedeProduit = possedeProduit;
        this.produits = produits;
        this.prix = prix;
    }
}
