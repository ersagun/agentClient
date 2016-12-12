package main.java.fr.miage.agents.api.message.relationclientsupermarche;

import java.util.Map;
import java.util.UUID;

import main.java.fr.miage.agents.api.message.Message;
import main.java.fr.miage.agents.api.message.TypeMessage;

/**
 * Created by nitix on 30/11/16.
 */
public class Achat extends Message {
	
	/**
	 * La clé correspond à l'id du produit
	 * La valeur correspond à la quantité demandée
	 */
	public Map<Long,Integer> listeCourses;
	
	public UUID Session;

	public Achat() {
        super(TypeMessage.AchatClient);
    }
}
