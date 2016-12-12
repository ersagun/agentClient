package main.java.fr.miage.agents.api.message.util;

import main.java.fr.miage.agents.api.message.Message;
import main.java.fr.miage.agents.api.message.TypeMessage;

public class DemandeCategorie extends Message {
	
    public DemandeCategorie() {
        super(TypeMessage.DemandeCategorie);
    }
}
