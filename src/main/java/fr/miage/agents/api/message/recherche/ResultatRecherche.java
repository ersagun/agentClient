package main.java.fr.miage.agents.api.message.recherche;

import java.util.List;
import java.util.UUID;

import main.java.fr.miage.agents.api.message.Message;
import main.java.fr.miage.agents.api.message.TypeMessage;
import main.java.fr.miage.agents.api.model.Produit;

/**
 * Created by nitix on 14/11/16.
 */
public class ResultatRecherche extends Message {
	
    public List<Produit> produitList;
    
    public UUID Session;

    public ResultatRecherche() {
        super(TypeMessage.ResultatRecherche);
    }
}
