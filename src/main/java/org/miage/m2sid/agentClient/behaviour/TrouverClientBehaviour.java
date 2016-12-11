/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.m2sid.agentClient.behaviour;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CompositeBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import java.util.HashMap;
import org.miage.m2sid.agentClient.ClientAgent;

/**
 *
 * @author Ersagun
 */
public class TrouverClientBehaviour extends OneShotBehaviour {
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
        private HashMap<Integer,Integer> listeDeCourses;
	
	public TrouverClientBehaviour(Agent a) {
		clientAgent= (ClientAgent) a;
	}
        public TrouverClientBehaviour(Agent a, HashMap<Integer,Integer> ldc) {
		clientAgent= (ClientAgent) a;
                listeDeCourses=ldc;
	}
	@Override
	public void action() {
                System.out.println("Trouver client : "+ listeDeCourses);
		// Update the list of seller agents
		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		// sd.setType("book-selling");
		template.addServices(sd);
		try {
			DFAgentDescription[] result = DFService.search(myAgent, template);
			System.out.println("Les agents trouvés : ");
			clientAgent.setSellerAgents(new AID[result.length]);
			for (int i = 0; i < result.length; ++i) {
				clientAgent.getSellerAgents()[i] = result[i].getName();
				System.out.println(clientAgent.getSellerAgents()[i].getName());
			}
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
		myAgent.addBehaviour(new RoutineEbdomadaireBehaviour(clientAgent, (HashMap)clientAgent.getCourses()));
		//myAgent.addBehaviour(new RechercherBehaviour(clientAgent,listeDeCourses));
	}

	   
}
