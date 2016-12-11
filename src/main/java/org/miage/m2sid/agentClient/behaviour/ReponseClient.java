package org.miage.m2sid.agentClient.behaviour;


import java.util.HashMap;

import fr.miage.agents.api.message.interClients.DemandeEchange;
import fr.miage.agents.api.message.interClients.ReponseEchange;
import fr.miage.agents.api.model.Produit;
import jade.core.behaviours.CyclicBehaviour;
import org.miage.m2sid.agentClient.ClientAgent;

public class ReponseClient extends CyclicBehaviour
{
	ClientAgent maitre;

	public void action()
	{
		ReponseEchange retour = new ReponseEchange();
		DemandeEchange message = new DemandeEchange();
		retour.possedeProduit=false;
		if(maitre.getDisponible())
		{
			Produit resultat = maitre.getSupermarcheMin().rechercher((int) message.produitDemande.idProduit);
			if (!(resultat == null))
			{
				retour.possedeProduit=true;
				retour.prix=resultat.prixProduit*message.quantiteDemande;
				retour.produits=new HashMap<Produit, Integer>();
				retour.produits.put(resultat, message.quantiteDemande);
			}
		}
		//TODO renvoyer retour a l'autre client
		
	}

	public ReponseClient(ClientAgent maitre)
	{
		super();
		this.maitre = maitre;
	}
}
