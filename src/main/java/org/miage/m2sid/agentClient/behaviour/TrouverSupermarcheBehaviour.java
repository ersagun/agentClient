package org.miage.m2sid.agentClient.behaviour;

import org.miage.m2sid.agentClient.ClientAgent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class TrouverSupermarcheBehaviour extends OneShotBehaviour {

	ClientAgent client;
	
	public TrouverSupermarcheBehaviour(Agent a) {
		client= (ClientAgent) a;
	}
	@Override
	public void action() {
		System.out.println("Trying to buy XXXX");
		// Update the list of seller agents
		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		// sd.setType("book-selling");
		template.addServices(sd);
		try {
			DFAgentDescription[] result = DFService.search(myAgent, template);
			System.out.println("Les agents trouvés : ");
			client.setSellerAgents(new AID[result.length]);
			for (int i = 0; i < result.length; ++i) {
				client.getSellerAgents()[i] = result[i].getName();
				System.out.println(client.getSellerAgents()[i].getName());
			}
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
		
		myAgent.addBehaviour(new RechercherBehaviour(client));
	}

	
	
}
