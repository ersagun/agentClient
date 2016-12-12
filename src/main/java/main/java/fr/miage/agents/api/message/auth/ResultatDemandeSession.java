package main.java.fr.miage.agents.api.message.auth;

import main.java.fr.miage.agents.api.message.Message;
import main.java.fr.miage.agents.api.message.TypeMessage;

import java.util.UUID;

/**
 * Created by Guillaume on 04/12/2016.
 */
public class ResultatDemandeSession extends Message {

    public UUID session;

    /**
     * Message retransmit depuis ping
     */
    public String pong;

    public ResultatDemandeSession() {
        super(TypeMessage.ResultatDemandeSession);
    }
}
