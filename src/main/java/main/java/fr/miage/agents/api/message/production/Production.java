package main.java.fr.miage.agents.api.message.production;

import main.java.fr.miage.agents.api.message.Message;
import main.java.fr.miage.agents.api.message.TypeMessage;

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