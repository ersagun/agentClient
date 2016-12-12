package fr.miage.agents.api.message.production;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.TypeMessage;

/**
 * Created by Alexandre on 06/12/2016.
 */
public class Production extends Message {

    public long idProduit;

    public int quantiteProduite;

    public Production() {
        super(TypeMessage.Production);
    }



}