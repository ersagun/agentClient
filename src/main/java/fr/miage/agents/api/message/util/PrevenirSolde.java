package fr.miage.agents.api.message.util;

import java.util.HashMap;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.TypeMessage;

/**
 * Created by nitix on 14/11/16.
 */
public class PrevenirSolde extends Message {
	// HashMap contenant les categories soldées (String) et le pourcentage de solde appliqué (Double)
	public HashMap<String, Double> categoriesSoldees;
	
    public PrevenirSolde() {
        super(TypeMessage.PrevenirSolde);
        categoriesSoldees = new HashMap<>();
    }
}
