package fr.miage.agents.api.message.relationclientsupermarche;

import java.util.Map;
import java.util.UUID;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.TypeMessage;
import fr.miage.agents.api.model.Produit;

/**
 * Created by nitix on 30/11/16.
 */
public class ResultatAchat extends Message{

    public UUID session;

    /**
     * La premiere clé correspond à l'id du produit 
     * La valeur correspond à la quantité disponible si la quantité demandée était > au stock
     * ou bien la quantité demandée
     */
    public Map<Produit,Integer> courses;

    public ResultatAchat() {
        super(TypeMessage.ResultatAchatClient);
    }
}
