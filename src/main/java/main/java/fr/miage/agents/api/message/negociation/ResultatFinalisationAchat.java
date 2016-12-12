package main.java.fr.miage.agents.api.message.negociation;

import main.java.fr.miage.agents.api.message.Message;
import main.java.fr.miage.agents.api.message.TypeMessage;

import java.util.UUID;

/**
 * Created by Arthur on 25/11/2016.
 */
public class ResultatFinalisationAchat extends Message {

    public UUID session;

    public long idProduit;

    public int quantiteProduit;

    public float prixFinal;

    public ResultatFinalisationAchat() {
        super(TypeMessage.ResultatFinalisationAchat);
    }
}