package fr.miage.agents.api.message.auth;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.TypeMessage;

/**
 * Created by Guillaume on 04/12/2016.
 */
public class DemanderSession extends Message {

    /**
     * Message que vas retransmettre le serveur
     */
    public String ping;

    public DemanderSession() {
        super(TypeMessage.DemanderSession);
    }
}
