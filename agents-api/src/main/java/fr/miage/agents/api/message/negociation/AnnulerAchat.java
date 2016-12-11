package fr.miage.agents.api.message.negociation;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.TypeMessage;

import java.util.UUID;

/**
 * Created by nitix on 03/12/16.
 */
public class AnnulerAchat extends Message {

    public UUID session;

    public AnnulerAchat() {
        super(TypeMessage.AnnulerAchat);
    }
}
