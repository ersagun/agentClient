package main.java.fr.miage.agents.api.message.util;

import main.java.fr.miage.agents.api.message.Message;
import main.java.fr.miage.agents.api.message.TypeMessage;

import java.util.List;

/**
 * Created by nitix on 01/12/16.
 */
public class ResultatAide extends Message {

    public List<TypeMessage> supportedActions;

    public ResultatAide() {
        super(TypeMessage.ResultatAide);
    }
}
