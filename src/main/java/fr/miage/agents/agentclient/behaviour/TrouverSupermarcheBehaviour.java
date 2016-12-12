package fr.miage.agents.agentclient.behaviour;



import fr.miage.agents.agentclient.ClientAgent;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CompositeBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class TrouverSupermarcheBehaviour extends OneShotBehaviour {

    public ClientAgent getClient() {
        return clientAgent;
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

    public TrouverSupermarcheBehaviour(Agent a) {
        clientAgent = (ClientAgent) a;
    }

    @Override
    public void action() {
        System.out.println("Trouver supermarcher : " + clientAgent.getCourses());
        // Update the list of seller agents
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        // sd.setType("book-selling");
        template.addServices(sd);
        try {
            DFAgentDescription[] result = DFService.search(myAgent, template);
            System.out.println("Les agents supermarché trouvés : ");
            clientAgent.setSellerAgents(new AID[result.length]);
            AID[] temp = new AID[result.length];
			for (int i = 0; i < result.length; ++i) {
				temp[i] = result[i].getName();
			}
                        clientAgent.setSellerAgents(temp);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
        myAgent.addBehaviour(new TrouverClientBehaviour(clientAgent));
        //myAgent.addBehaviour(new RoutineEbdomadaireBehaviour(clientAgent));
        //myAgent.addBehaviour(new RechercherBehaviour(clientAgent,listeDeCourses));
    }

}
