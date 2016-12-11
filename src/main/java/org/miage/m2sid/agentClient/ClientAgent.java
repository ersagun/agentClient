package org.miage.m2sid.agentClient;

import java.util.Map;

import org.miage.m2sid.agentClient.behaviour.TrouverSupermarcheBehaviour;

import jade.core.AID;
import jade.core.Agent;
import org.miage.m2sid.agentClient.behaviour.Supermarche;

public class ClientAgent extends Agent {

	private AID[] sellerAgents;
	private int repliesCnt;
	
	
	private Map<Integer, Integer> courses;
	private Supermarche supermarcheMin;
	private Map<Integer, Integer> produitsRestant;//produit indisponible au supermarché
	private Boolean disponible;//l'état actuel de l'agent (indisponible ou disponible(pret a communiquer avec d'aurtres clients)
	private Map<Integer, Integer> produitEchange;//liste des produits a acheter

	public Map<Integer, Integer> getCourses() {
		return courses;
	}



	public void setCourses(Map<Integer, Integer> courses) {
		this.courses = courses;
	}



	public Supermarche getSupermarcheMin() {
		return supermarcheMin;
	}



	public void setSupermarcheMin(Supermarche supermarcheMin) {
		this.supermarcheMin = supermarcheMin;
	}



	public Map<Integer, Integer> getProduitsRestant() {
		return produitsRestant;
	}



	public void setProduitsRestant(Map<Integer, Integer> produitsRestant) {
		this.produitsRestant = produitsRestant;
	}



	public Boolean getDisponible() {
		return disponible;
	}



	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}



	public Map<Integer, Integer> getProduitEchange() {
		return produitEchange;
	}



	public void setProduitEchange(Map<Integer, Integer> produitEchange) {
		this.produitEchange = produitEchange;
	}



	protected void setup() {

		System.out.println("Agent Client est  " + getAID().getName() + " prêt.");
		addBehaviour(new TrouverSupermarcheBehaviour(this));

	}



	protected void takeDown() {
		System.out.println("Agent Client " + getAID().getName() + " est prêt.");
	}

	/**
	 * Inner class RequestPerformer. This is the behaviour used by Book-buyer
	 * agents to request seller agents the target book.
	 */
	/**
	private class RechercherBehaviour extends Behaviour {
		private AID supermarcheChoisi; // The agent who provides the best offer
		private MessageTemplate mt; // The template to receive replies
		private int step = 0;

		public void action() {
			switch (step) {
			case 0:
				System.out.println("Case 0");
				
				ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
				for (int i = 0; i < sellerAgents.length; ++i) {
					cfp.addReceiver(sellerAgents[i]);
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
				
					if (repliesCnt >= sellerAgents.length) {
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
	**/
	public AID[] getSellerAgents() {
		return sellerAgents;
	}

	public void setSellerAgents(AID[] sellerAgents) {
		this.sellerAgents = sellerAgents;
	}

	public int getRepliesCnt() {
		return repliesCnt;
	}

	public void setRepliesCnt(int repliesCnt) {
		this.repliesCnt = repliesCnt;
	}

}