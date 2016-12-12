package main.java.fr.miage.agents.api.message.negociation;

import main.java.fr.miage.agents.api.message.Message;
import main.java.fr.miage.agents.api.message.TypeMessage;

import java.util.UUID;

/**
 * Created by nitix on 14/11/16.
 */
public class NegocierPrix extends Message {

    public UUID session;

    public long idProduit;

    public float prixDemande;

    public int quantiteDemande;

    public NegocierPrix() {
        super(TypeMessage.NegocierPrix);
    }
}
