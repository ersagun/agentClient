package fr.miage.agents.client413;

import java.util.HashMap;

import fr.miage.agents.api.message.interClients.DemandeEchange;
import fr.miage.agents.api.message.interClients.ReponseEchange;
import fr.miage.agents.api.model.Produit;

public class Client
{
	public Client()
	{
		//TODO
	}

	public HashMap<Produit, Integer> askProduit(Integer idProduit, Integer quantite)
	{
		ReponseEchange received = new ReponseEchange();
		DemandeEchange send = new DemandeEchange();
		Produit p = new Produit();
		p.idProduit=idProduit;
		send.produitDemande=p;
		send.quantiteDemande=quantite;
		// TODO envoyer la demande d'echange et recuperer la reponseEchange
		return received.produits;
	}
}
