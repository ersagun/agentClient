package org.miage.m2sid.agentClient.behaviour;

import org.miage.m2sid.agentClient.ClientAgent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CompositeBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import java.util.HashMap;

public class TrouverSupermarcheBehaviour extends OneShotBehaviour {

    public ClientAgent getClient() {
        return client;
    }

    public void setClient(ClientAgent client) {
        this.client = client;
    }

    public HashMap<Integer, Integer> getListeDeCourses() {
        return listeDeCourses;
    }

    public void setListeDeCourses(HashMap<Integer, Integer> listeDeCourses) {
        this.listeDeCourses = listeDeCourses;
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

	private ClientAgent client;
        private HashMap<Integer,Integer> listeDeCourses;
	
	public TrouverSupermarcheBehaviour(Agent a) {
		client= (ClientAgent) a;
	}
        public TrouverSupermarcheBehaviour(Agent a, HashMap<Integer,Integer> ldc) {
		client= (ClientAgent) a;
                listeDeCourses=ldc;
	}
	@Override
	public void action() {
                System.out.println("Trouver supermarcher : "+ listeDeCourses);
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
		
		myAgent.addBehaviour(new RechercherBehaviour(client,listeDeCourses));
	}

	
	
}
