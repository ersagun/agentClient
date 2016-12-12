package main.java.fr.miage.agents.api.message.negociation;

import main.java.fr.miage.agents.api.message.Message;
import main.java.fr.miage.agents.api.message.TypeMessage;

import java.util.UUID;

/**
 * Created by nitix on 14/11/16.
 */
public class FinaliserAchat extends Message {

    public UUID session;

    public FinaliserAchat() {
        super(TypeMessage.FinaliserAchat);
    }
}
