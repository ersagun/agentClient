package fr.miage.agents.agentclient.behaviour;

import fr.miage.agents.agentclient.ClientAgent;
import fr.miage.agents.api.message.interClients.DemandeEchange;
import fr.miage.agents.api.message.interClients.ReponseEchange;
import fr.miage.agents.api.model.Produit;
import java.util.HashMap;


import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import java.io.IOException;


public class Client {

    private MessageTemplate mt; // The template to receive replies
    ClientAgent clientAgent;
    AID aid;

    public Client(AID aid, ClientAgent ac) {
        this.clientAgent = ac;
        this.aid = aid;

    }

    public HashMap<Produit, Integer> askProduit(Long idProduit, Integer quantite) {
        ReponseEchange received = new ReponseEchange();
        DemandeEchange send = new DemandeEchange();
        Produit p = new Produit();
        p.idProduit = idProduit;
        send.produitDemande = p;
        send.quantiteDemande = quantite;

        ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
        cfp.addReceiver(aid);
        //TODO envoyer demande
       
        try {
            cfp.setContentObject(send);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        cfp.setConversationId("produit");
        cfp.setReplyWith("cfp" + System.currentTimeMillis()); // Unique
        // value

        clientAgent.send(cfp);

        mt = MessageTemplate.and(MessageTemplate.MatchConversationId("produit"),
                MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
// TODO envoyer la demande d'echange et recuperer la reponseEchange
ACLMessage reply;
        
        do
        {reply = clientAgent.receive(mt);}    
        while(reply != null);
         
              if (reply.getPerformative() == ACLMessage.PROPOSE) {
                        AID supermarcheChoisi = reply.getSender();
                        try {
                            received = (ReponseEchange) reply.getContentObject();
                            System.out.println("supermarché possede le produit: "+ received.possedeProduit+received.prix+received.produits.toString());
                                                        
                        } catch (UnreadableException e) {
                            e.printStackTrace();
                        }
                    }

        return received.produits;
    }
}
