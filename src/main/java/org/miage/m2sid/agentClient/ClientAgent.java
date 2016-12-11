package org.miage.m2sid.agentClient;

import java.util.Map;

import org.miage.m2sid.agentClient.behaviour.TrouverSupermarcheBehaviour;

import jade.core.AID;
import jade.core.Agent;
import java.util.HashMap;
import org.miage.m2sid.agentClient.behaviour.Supermarche;
import org.miage.m2sid.agentClient.ui.Gui;

public class ClientAgent extends Agent {

    private AID[] sellerAgents;
    private AID[] clientAgents;
    private int repliesCnt;

    private Gui gui;

    private Map<Long, Integer> courses;
    private Supermarche supermarcheMin;
    private Map<Long, Integer> produitsRestant;//produit indisponible au supermarché
    private Boolean disponible;//l'état actuel de l'agent (indisponible ou disponible(pret a communiquer avec d'aurtres clients)
    private Map<Long, Integer> produitEchange;//liste des produits a acheter

    public Gui getGui() {
        return gui;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    public Map<Long, Integer> getCourses() {
        return courses;
    }

    public void setCourses(Map<Long, Integer> courses) {
        this.courses = courses;
    }

    public Supermarche getSupermarcheMin() {
        return supermarcheMin;
    }

    public void setSupermarcheMin(Supermarche supermarcheMin) {
        this.supermarcheMin = supermarcheMin;
    }

    public Map<Long, Integer> getProduitsRestant() {
        return produitsRestant;
    }

    public void setProduitsRestant(Map<Long, Integer> produitsRestant) {
        this.produitsRestant = produitsRestant;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Map<Long, Integer> getProduitEchange() {
        return produitEchange;
    }

    public void setProduitEchange(Map<Long, Integer> produitEchange) {
        this.produitEchange = produitEchange;
    }

    protected void setup() {
        gui=new Gui(this);
        System.out.println("Agent Client est  " + getAID().getName() + " prêt.");
    }
    

    public void demandeProduit(HashMap<Integer,Integer> listeDeCourses ) {
        addBehaviour(new TrouverSupermarcheBehaviour(this, (HashMap)this.courses));
    }

    protected void takeDown() {
        System.out.println("Agent Client " + getAID().getName() + " est prêt.");
    }

    public AID[] getSellerAgents() {
        return sellerAgents;
    }

    public AID[] getClientAgents() {
        return clientAgents;
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
