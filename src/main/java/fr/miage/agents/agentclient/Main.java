/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.agents.agentclient;

import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ersagun
 */
public class Main {
    public static void main(String[]Args) throws StaleProxyException{
         String adressIP = JOptionPane.showInputDialog("Donner l'adresse IP");
            jade.core.Runtime runtime = jade.core.Runtime.instance();
            ProfileImpl profileImpl = new ProfileImpl(false);
            profileImpl.setParameter(ProfileImpl.MAIN_HOST, adressIP);
            System.out.println("Main lancé");
            AgentContainer agentContainer = runtime.createAgentContainer(profileImpl);
            String className = ClientAgent.class.getName();
            String nickname = "Supermarché " + Math.floor(100 * Math.random() % 1000);
            AgentController agentController = agentContainer.createNewAgent(nickname, className, new Object[]{});
            agentController.start();
    }

}
