package org.miage.m2sid.agentClient.behaviour;

import java.io.IOException;

import org.miage.m2sid.agentClient.ClientAgent;

import fr.miage.agents.api.message.recherche.Rechercher;
import fr.miage.agents.api.message.recherche.ResultatRecherche;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class RechercherBehaviour extends Behaviour {

	private AID supermarcheChoisi; // The agent who provides the best offer
	private MessageTemplate mt; // The template to receive replies
	private int step = 0;
	private int repliesCnt=0;
	private ClientAgent client;
	
	public RechercherBehaviour(Agent a){
		client=(ClientAgent) a;
	}
	public void action() {
		switch (step) {
		case 0:
			System.out.println("Case 0");
			
			ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
			for (int i = 0; i < client.getSellerAgents().length; ++i) {
				cfp.addReceiver(client.getSellerAgents()[i]);
			}
			Rechercher r = new Rechercher();
			r.nomCategorie = "yolo";
			try {
				cfp.setContentObject(r);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			cfp.setConversationId("produit");
			cfp.setReplyWith("cfp" + System.currentTimeMillis()); // Unique
																	// value
			myAgent.send(cfp);
			// Prepare the template to get proposals
			mt = MessageTemplate.and(MessageTemplate.MatchConversationId("produit"),
					MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
			step = 1;
			break;
		
		
		case 1:
			ACLMessage reply = myAgent.receive(mt);
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
			
				if (repliesCnt >= client.getSellerAgents().length) {
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

	public boolean done() {
		if (step == 2 && supermarcheChoisi == null) {
		}
		return ((step == 2 && supermarcheChoisi == null) || step == 4);
	}
	
}
