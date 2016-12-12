package main.java.fr.miage.agents.api.message.negociation;

import main.java.fr.miage.agents.api.message.Message;
import main.java.fr.miage.agents.api.message.TypeMessage;

import java.util.UUID;

/**
 * Created by nitix on 30/11/16.
 */
public class ResultatInitiationAchat extends Message{

    public UUID session;
    
    public boolean success;

    public int quantiteDisponible;

    public float prixFixe;

    public ResultatInitiationAchat() {
        super(TypeMessage.ResultatInitiationAchat);
    }
}
