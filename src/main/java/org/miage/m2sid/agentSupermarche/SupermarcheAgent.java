package org.miage.m2sid.agentSupermarche;

import java.io.IOException;
import java.util.ArrayList;
/**
 * ***************************************************************
 * JADE - Java Agent DEvelopment Framework is a framework to develop multi-agent
 * systems in compliance with the FIPA specifications. Copyright (C) 2000 CSELT
 * S.p.A. * GNU Lesser General Public License
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, version 2.1 of the License. * This library is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 * ***************************************************************
 */
import java.util.Hashtable;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.TypeMessage;
import fr.miage.agents.api.message.recherche.Rechercher;
import fr.miage.agents.api.message.recherche.ResultatRecherche;
import fr.miage.agents.api.model.Categorie;
import fr.miage.agents.api.model.Produit;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class SupermarcheAgent extends Agent {
    // The catalogue of books for sale (maps the title of a book to its price)

    private Hashtable catalogue;
    // The GUI by means of which the user can add books in the catalogue
    //private BookSellerGui myGui;

    // Put agent initializations here
    protected void setup() {
        // Create the catalogue
        catalogue = new Hashtable();

        // Create and show the GUI
        //myGui = new BookSellerGui(this);
        //myGui.show();
        System.out.println("sa mere ");
        // Register the book-selling service in the yellow pages
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("supermarche");
        sd.setName("Supermarche");
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }

        // Add the behaviour serving queries from buyer agents
        addBehaviour(new ProposerOffre());

        // Add the behaviour serving purchase orders from buyer agents
        // addBehaviour(new AcheterOffre());
    }

    // Put agent clean-up operations here
    protected void takeDown() {
        // Deregister from the yellow pages
        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
        // Close the GUI
        //myGui.dispose();
        // Printout a dismissal message
        System.out.println("Agent supermarché " + getAID().getName() + "  correctement terminé.");
    }

    private class ProposerOffre extends CyclicBehaviour {

        public void action() {
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
            ACLMessage msg = myAgent.receive(mt);
            Message m = null;
            if (msg != null) {
                // CFP Message received. Process it
                try {
                    m = (Message) msg.getContentObject();
                    ACLMessage reply = msg.createReply();
                    System.out.println("YOLOOOO ya un message "+ m.type);
                    if (m.type == TypeMessage.Recherche) {
                        Rechercher r = (Rechercher) m;
                        System.out.println(m.type);
                        System.out.println(r.nomCategorie);
                        
                        ResultatRecherche rr = new ResultatRecherche();
                        rr.produitList = new ArrayList<Produit>();
                        Produit p=null;
                        if(r.idProduit==1){
                        p = new Produit();
                        p.descriptionProduit = "yolo sa mere la descri";
                        Categorie c = new Categorie();
                        c.idCategorie = 11;
                        c.nomCategorie = "sa mere";
                        p.idCategorie = c;
                        p.idProduit = 1;
                        p.marque = "heineken";
                        p.nomProduit = "nike sa mere";
                        p.prixProduit = 30; 
                         rr.produitList.add(p);
                        }
                        
                        Produit p2=null;
                        if(r.idProduit==2){
                        p2 = new Produit();
                        p2.descriptionProduit = "yolo sa mere la descri2";
                        Categorie c2 = new Categorie();
                        c2.idCategorie = 112;
                        c2.nomCategorie = "sa mere2";
                        p2.idCategorie = c2;
                        p2.idProduit = 2;
                        p2.marque = "heineken2";
                        p2.nomProduit = "nike sa mere2";
                        p2.prixProduit = 30;
                        rr.produitList.add(p2);
                        }
                        
                        
                       
                       
                        reply.setPerformative(ACLMessage.PROPOSE);
                        reply.setContentObject(rr);

                    }
                    myAgent.send(reply);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                block();
            }
        }
    }
}
