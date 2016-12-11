package org.miage.m2sid.agentClient.behaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.miage.agents.api.message.recherche.Rechercher;
import fr.miage.agents.api.message.recherche.ResultatRecherche;
import fr.miage.agents.api.message.relationclientsupermarche.Achat;
import fr.miage.agents.api.message.relationclientsupermarche.ResultatAchat;
import fr.miage.agents.api.model.Produit;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.util.HashMap;
import org.miage.m2sid.agentClient.ClientAgent;

public class Supermarche {

    private final double COUTDISTANCE = 1.5;

    private AID aid;
    private ClientAgent clientAgent;
    private MessageTemplate mt; // The template to receive replies

    //TODO ajouter les arguments qui permettent de communiquer avec le supermarche
    float distance;
    List<Produit> produits;

    public Supermarche(AID aid, ClientAgent ca) {
        this.aid = aid;
        this.clientAgent = ca;

        distance = 0;
        produits = new ArrayList<Produit>();
    }

    /*
	 * [placeholder]retourne une valeur random entre 10 et 50
	 * Retourne la distance à un supermarché
     */
    public float getDistance() {
        //random*(max-min)
        int min = 10;
        int max = 50;
        return (float) Math.random() * (max - min) + min;
    }

    public Produit rechercher(Long idProduit) {
        Produit resultat = null;
        ResultatRecherche received = null;
        Rechercher send;
        send = new Rechercher();
        send.idProduit = idProduit;
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
        
        //TODO récupérer demande dans ResultatRecherche
        ACLMessage reply;
        
        do
        {reply = clientAgent.receive(mt);}    
        while(reply != null);
         
              if (reply.getPerformative() == ACLMessage.PROPOSE) {
                        AID supermarcheChoisi = reply.getSender();
                        try {
                            received = (ResultatRecherche) reply.getContentObject();
                            System.out.println("supermarché "+ supermarcheChoisi.getName());
                            System.out.println("marque prod " + received.produitList.get(0).marque);
                            System.out.println("prix prod " + received.produitList.get(0).prixProduit);
                            System.out.println("nomprod " + received.produitList.get(0).nomProduit);
                            System.out.println("categorie " + received.produitList.get(0).idCategorie);
                        } catch (UnreadableException e) {
                            e.printStackTrace();
                        }
                    }
                
                                                  
        if (!received.produitList.isEmpty()) {
            resultat = received.produitList.get(0);
        }
        return resultat;        
    }
    
    

    public float prixTotal() {
        float total = 0;
        for (Produit produit : produits) {
            total += produit.prixProduit;
        }
        return total + (float) (distance * COUTDISTANCE);
    }

    public int nbProduit() {
        return produits.size();
    }

    public Map<Produit, Integer> acheterCourses(Map<Long, Integer> courses) {
        Achat send = new Achat();
        send.listeCourses=courses;
        
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
        
        
        
        ResultatAchat received = new ResultatAchat();
        

 ACLMessage reply;
        
        do
        {reply = clientAgent.receive(mt);}    
        while(reply != null);
         
              if (reply.getPerformative() == ACLMessage.PROPOSE) {
                        AID supermarcheChoisi = reply.getSender();
                        try {
                            received = (ResultatAchat) reply.getContentObject();
                            System.out.println("supermarché courses: "+ received.courses.toString());
                                                        
                        } catch (UnreadableException e) {
                            e.printStackTrace();
                        }
                    }
// TODO envoyer achat et recuperer  resultatAchat
        return received.courses;
    }
}
