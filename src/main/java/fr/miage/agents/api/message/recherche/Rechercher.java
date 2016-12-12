package main.java.fr.miage.agents.api.message.recherche;

import java.util.UUID;

import main.java.fr.miage.agents.api.message.Message;
import main.java.fr.miage.agents.api.message.TypeMessage;

/**
 * Created by nitix on 14/11/16.
 */
public class Rechercher extends Message {

    public long idProduit;

    public String nomCategorie;

    public String marque;

    public float prixMax;

    public float prixMin;

    public UUID session;
    
    public Rechercher() {
        super(TypeMessage.Recherche);
    }
}
