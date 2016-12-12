package fr.miage.agents.api.message.interClients;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.TypeMessage;
import fr.miage.agents.api.model.Produit;

/**
 * Created by jerome on 06/12/2016.
 */
public class DemandeEchange extends Message{

    public Produit produitDemande;
    public int quantiteDemande;

    public DemandeEchange() {
        super(TypeMessage.DemandeEchange);
    }

    public DemandeEchange(Produit produitDemande, int quantiteDemande) {
        super(TypeMessage.DemandeEchange);
        this.produitDemande = produitDemande;
        this.quantiteDemande = quantiteDemande;
    }
}
