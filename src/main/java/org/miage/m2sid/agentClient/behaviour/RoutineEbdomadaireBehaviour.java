package org.miage.m2sid.agentClient.behaviour;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.miage.m2sid.agentClient.ClientAgent;

import fr.miage.agents.api.model.Produit;
import jade.core.behaviours.CyclicBehaviour;

public class RoutineEbdomadaireBehaviour extends CyclicBehaviour
{
	/* TODO ajouter les attributs permettant de communiquer avec les autres agents */
	ClientAgent maitre;
	
	public RoutineEbdomadaireBehaviour(ClientAgent maitre)
	{
		super();
		this.maitre = maitre;
	}
	
	public void action()
	{
		
		// on récupère la liste des autres agents en vie
		List<Supermarche> supermarches = listerSupermarche();
		generateCourse();
		
		// on demande aux supermarches les prix pour les produits qui nous interessent
		/* =============================GET INFO SUPERMARCHE===================================== */
		for (Supermarche supermarche : supermarches)
		{
			float distance = supermarche.getDistance();
			
			supermarche.distance = distance;
			// on compte les produits disponibles ainsi que le prixtotal
			for (Map.Entry<Integer, Integer> entry : maitre.getCourses().entrySet())
			{
				int idProduit = entry.getKey();
				//int quantite = entry.getValue();
				Produit resultat = supermarche.rechercher(idProduit);
				if (!(resultat == null))
				{
					supermarche.produits.add(resultat);
				}
			}
		}
		
		/* ================================GET BEST SUPERMARCHE======================================== */
		maitre.setSupermarcheMin(supermarches.get(0));
		// on garde le supermarché le moins cher
		for (Supermarche supermarche : supermarches)
		{
			if (maitre.getSupermarcheMin().nbProduit() == supermarche.nbProduit())
			{
				if (maitre.getSupermarcheMin().prixTotal() > supermarche.prixTotal())
				{
					maitre.setSupermarcheMin(supermarche);
				}
			}
			else
				if (maitre.getSupermarcheMin().nbProduit() > supermarche.nbProduit())
				{
					maitre.setSupermarcheMin(supermarche);
				}
		}
		
		// on liste les produits indisponibles dans le supermarche
		/* =================================GET PRODUITS INDISPONIBLES====================================== */
		for (Entry<Integer, Integer> entry : maitre.getCourses().entrySet())
		{
			Integer idProduit = entry.getKey();
			int quantite = entry.getValue();
			if (maitre.getSupermarcheMin().produits.contains(idProduit))
			{
				maitre.getProduitsRestant().put(idProduit, quantite);
			}
			
		}
		
		/* =================================ECHANGES CLIENTS======================================== */
		maitre.setDisponible(true);
		
		// on envois un message a chaque client un par un pour savoir s'ils n'ont pas un des produits qui nous manque
		
		List<Client> clients = listerClient();
		
		// on demande aux clients les prix pour les produits qui nous interessent et on retire les echanges fructueux
		// des produits manquants
		for (Client client : clients)
		{
			for (Entry<Integer, Integer> entry : maitre.getProduitsRestant().entrySet())
			{
				Integer idProduit = entry.getKey();
				Integer quantite = entry.getValue();
				HashMap<Produit, Integer> echange = client.askProduit(idProduit, quantite);
				for (Entry<Produit, Integer> entry2 : echange.entrySet())
				{
					Produit produit = entry2.getKey();
					Integer quantite2 = entry2.getValue();
					
					if (produit.idProduit == idProduit)
					{
						maitre.getProduitsRestant().replace(idProduit, maitre.getProduitsRestant().get(idProduit) - quantite2);
						maitre.getCourses().replace(idProduit, maitre.getProduitsRestant().get(idProduit) - quantite2);
					}
				}
			}
		}
		
		maitre.setDisponible(false);
		/*================================ACHAT SUPERMARCHE=====================================*/
		
		/*-------------------------------ACHAT PERSONNEL-------------------------------------*/
		// on achete les produits du supermarche le moins cher
		Map<Produit, Integer> resultatAchat = maitre.getSupermarcheMin().acheterCourses(maitre.getCourses());
		// on supprime les produits achetés de la liste
		for (Entry<Produit, Integer> entry : resultatAchat.entrySet())
		{
			Produit Produit = entry.getKey();
			int quantite = entry.getValue();
			maitre.getCourses().replace((int) Produit.idProduit, maitre.getCourses().get(Produit.idProduit) - quantite);
			if (maitre.getCourses().get(Produit.idProduit) == 0)
			{
				maitre.getCourses().remove(Produit.idProduit);
			}
			
		}
		/*-------------------------------ACHAT ECHANGE-------------------------------------*/
		resultatAchat = maitre.getSupermarcheMin().acheterCourses(maitre.getProduitEchange());
		// on supprime les produits achetés de la liste
		for (Entry<Produit, Integer> entry : resultatAchat.entrySet())
		{
			Produit Produit = entry.getKey();
			int quantite = entry.getValue();
			maitre.getProduitEchange().replace((int) Produit.idProduit, maitre.getProduitEchange().get(Produit.idProduit) - quantite);
			if (maitre.getProduitEchange().get(Produit.idProduit) == 0)
			{
				maitre.getProduitEchange().remove(Produit.idProduit);
			}
			
		}
		// TO/!\Impossible/!\DO echanger les produits 
		// TODO reinitialiser les attributs
		maitre.setSupermarcheMin(null);
		maitre.setProduitsRestant(new HashMap<Integer, Integer>());
	}
	
	/*
	 * Génére une liste de produits souhaités ajoute entre 1 et 20 produit selectionné aleatoirement parmis les produits
	 * existants
	 */
	private void generateCourse()
	{
		int max = 20;
		int min = 1;
		int maxcpt = (int) (Math.random() * (max - min) + min);
		for (int cpt = 0; cpt < maxcpt; cpt++)
		{
			max = 149;
			min = 1;
			maitre.getCourses().put((int) (Math.random() * (max - min) + min), (int) (Math.random() * (max - min) + min));
		}
	}
	
	/*
	 * retourne la liste des supermarchés
	 */
	private List<Supermarche> listerSupermarche()
	{
		// TODO
		return null;
	}
	
	/*
	 * retourne la liste des clients
	 */
	private List<Client> listerClient()
	{
		// TODO
		return null;
	}
	
}
