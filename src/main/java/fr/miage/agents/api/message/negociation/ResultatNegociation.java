package fr.miage.agents.api.message.negociation;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.TypeMessage;

import java.util.UUID;

/**
 * Created by Arthur on 21/11/2016.
 */
public class ResultatNegociation extends Message {

    public boolean estAccepte;

    public UUID session;

    public long idProduit;

    public float prixNegocie;

    public int quantiteDisponible;

    public ResultatNegociation() {
        super(TypeMessage.ResultatNegociation);
    }
}
