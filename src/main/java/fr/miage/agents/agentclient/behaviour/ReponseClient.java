package fr.miage.agents.agentclient.behaviour;

import fr.miage.agents.agentclient.ClientAgent;
import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.TypeMessage;
import fr.miage.agents.api.message.interClients.DemandeEchange;
import fr.miage.agents.api.message.interClients.ReponseEchange;
import fr.miage.agents.api.model.Produit;

import java.util.HashMap;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CompositeBehaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReponseClient extends CyclicBehaviour {

    private AID aid;

    public ReponseClient(ClientAgent maitre) {
        super();
        this.clientAgent = maitre;
    }

    public AID getAid() {
        return aid;
    }

    public void setAid(AID aid) {
        this.aid = aid;
    }

    public ClientAgent getClientAgent() {
        return clientAgent;
    }

    public void setClientAgent(ClientAgent clientAgent) {
        this.clientAgent = clientAgent;
    }

    public ClientAgent getMaitre() {
        return clientAgent;
    }

    public void setMaitre(ClientAgent maitre) {
        this.clientAgent = maitre;
    }

    public MessageTemplate getMt() {
        return mt;
    }

    public void setMt(MessageTemplate mt) {
        this.mt = mt;
    }

    public Agent getMyAgent() {
        return myAgent;
    }

    public void setMyAgent(Agent myAgent) {
        this.myAgent = myAgent;
    }

    public RunnableChangedEvent getMyEvent() {
        return myEvent;
    }

    public void setMyEvent(RunnableChangedEvent myEvent) {
        this.myEvent = myEvent;
    }

    public CompositeBehaviour getParent() {
        return parent;
    }

    public void setParent(CompositeBehaviour parent) {
        this.parent = parent;
    }

    private ClientAgent clientAgent;

    private MessageTemplate mt; // The template to receive replies

    public void action() {
        ReponseEchange retour = new ReponseEchange();
        DemandeEchange message = new DemandeEchange();
        retour.possedeProduit = false;

        //TODO renvoyer retour a l'autre client
        ACLMessage msg = myAgent.receive(mt);
        Message m = null;
        if (msg != null) {
            // CFP Message received. Process it

            try {
                m = (Message) msg.getContentObject();
            } catch (UnreadableException ex) {
                Logger.getLogger(ReponseClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            aid = msg.getSender();
            ACLMessage reply = msg.createReply();
            System.out.println("YOLOOOO ya un demande message " + m.type);
            if (m.type == TypeMessage.DemandeEchange) {

                if (clientAgent.getDisponible()) {
                    Produit resultat = clientAgent.getSupermarcheMin().rechercher((Long) message.produitDemande.idProduit);
                    if (!(resultat == null)) {
                        retour.possedeProduit = true;
                        retour.prix = resultat.prixProduit * message.quantiteDemande;
                        retour.produits = new HashMap<Produit, Integer>();
                        retour.produits.put(resultat, message.quantiteDemande);
                    }

                    ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
                    cfp.addReceiver(aid);
                    //TODO envoyer demande
                    try {
                        cfp.setContentObject(retour);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    cfp.setConversationId("produit");
                    cfp.setReplyWith("cfp" + System.currentTimeMillis()); // Unique
                    // value

                    clientAgent.send(cfp);
                }

            }
            clientAgent.send(reply);

        }
    }
}
