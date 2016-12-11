package org.miage.m2sid.agentClient.behaviour;

import static fr.miage.agents.api.message.TypeMessage.ResultatRecherche;
import java.io.IOException;

import org.miage.m2sid.agentClient.ClientAgent;

import fr.miage.agents.api.message.recherche.Rechercher;
import fr.miage.agents.api.message.recherche.ResultatRecherche;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CompositeBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import java.util.HashMap;
import java.util.Map.Entry;

public class RechercherBehaviour extends Behaviour {

    private AID supermarcheChoisi; // The agent who provides the best offer
    private MessageTemplate mt; // The template to receive replies
    private int step = 0;
    private int repliesCnt = 0;
    private ClientAgent clientAgent;
    private HashMap<Integer, Integer> listeDeCourses;

    public AID getSupermarcheChoisi() {
        return supermarcheChoisi;
    }

    public void setSupermarcheChoisi(AID supermarcheChoisi) {
        this.supermarcheChoisi = supermarcheChoisi;
    }

    public MessageTemplate getMt() {
        return mt;
    }

    public void setMt(MessageTemplate mt) {
        this.mt = mt;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getRepliesCnt() {
        return repliesCnt;
    }

    public void setRepliesCnt(int repliesCnt) {
        this.repliesCnt = repliesCnt;
    }

    public ClientAgent getClient() {
        return clientAgent;
    }

    public void setClient(ClientAgent client) {
        this.clientAgent = client;
    }

    public HashMap<Integer, Integer> getListeDeCourses() {
        return listeDeCourses;
    }

    public void setListeDeCourses(HashMap<Integer, Integer> listeDeCourses) {
        this.listeDeCourses = listeDeCourses;
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

    public RechercherBehaviour(Agent a) {
        clientAgent = (ClientAgent) a;
    }

    public RechercherBehaviour(Agent a, HashMap<Integer, Integer> ldc) {
        clientAgent = (ClientAgent) a;
        listeDeCourses = ldc;
    }

    public void action() {
        ACLMessage cfp = null;
        switch (step) {
            case 0:
                System.out.println("Envoi de recherche");

                for (Entry<Integer, Integer> entry : listeDeCourses.entrySet()) {
                    Integer idProduit = entry.getKey();
                    Integer qte = entry.getValue();

                    cfp = new ACLMessage(ACLMessage.CFP);
                    for (int i = 0; i < clientAgent.getSellerAgents().length; ++i) {
                        cfp.addReceiver(clientAgent.getSellerAgents()[i]);
                    }
                    Rechercher r = new Rechercher();
                    r.idProduit = idProduit;
                    try {
                        cfp.setContentObject(r);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    
                    cfp.setConversationId("produit");
                    cfp.setReplyWith("cfp" + System.currentTimeMillis()); // Unique
                    // value
                    clientAgent.send(cfp);
                }
                System.out.println("Liste de course "+listeDeCourses+" envoyé aux supermarches");
                // Prepare the template to get proposals    
                mt = MessageTemplate.and(MessageTemplate.MatchConversationId("produit"),
                        MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));

                step = 1;

                break;

            case 1:
                ACLMessage reply = clientAgent.receive(mt);
                if (reply != null) {
                    
                    if (reply.getPerformative() == ACLMessage.PROPOSE) {
                        supermarcheChoisi = reply.getSender();

                        try {
                            ResultatRecherche rs = (ResultatRecherche) reply.getContentObject();
                            System.out.println("liste des produits " + rs.produitList.size());
                            System.out.println("liste des produits " + rs.produitList.get(0).descriptionProduit);
                            System.out.println("liste des produits " + rs.produitList.get(1).descriptionProduit);
                        } catch (UnreadableException e) {
                            e.printStackTrace();
                        }
                    }

                    if (repliesCnt >= clientAgent.getSellerAgents().length) {
                        // We received all replies
                        step = 2;
                    }
                    repliesCnt++;
                } else {
                    block();
                }
                break;
        }
    }

    @Override
    public boolean done() {
        if (step == 2 && supermarcheChoisi == null) {
        }
        return ((step == 2 && supermarcheChoisi == null) || step == 4);
    }

}
