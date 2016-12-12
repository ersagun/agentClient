package main.java.fr.miage.agents.api.message.negociation;

import java.util.UUID;

import main.java.fr.miage.agents.api.message.Message;
import main.java.fr.miage.agents.api.message.TypeMessage;

/**
 * Created by nitix on 30/11/16.
 */
public class InitierAchat extends Message {

    public UUID session;

    public long idProduit;

    public int quantite;

    public InitierAchat() {
        super(TypeMessage.InitierAchat);
    }
}
