package fr.miage.agents.client413;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.miage.agents.api.message.recherche.Rechercher;
import fr.miage.agents.api.message.recherche.ResultatRecherche;
import fr.miage.agents.api.message.relationclientsupermarche.Achat;
import fr.miage.agents.api.message.relationclientsupermarche.ResultatAchat;
import fr.miage.agents.api.model.Produit;

public class Supermarche
{

	private final double COUTDISTANCE=1.5;
	
	//TODO ajouter les arguments qui permettent de communiquer avec le supermarche
	float distance;
	List<Produit> produits;
	public Supermarche()
	{
		distance=0;
		produits = new ArrayList<Produit>();
	}

	/*
	 * [placeholder]retourne une valeur random entre 10 et 50
	 * Retourne la distance à un supermarché
	 */
	public float getDistance()
	{
		//random*(max-min)
		int min = 10;
		int max = 50;
		return (float)Math.random() * ( max - min ) + min;
	}

	public Produit rechercher(int idProduit)
	{
		Produit resultat = null;
		ResultatRecherche received = null;
		Rechercher send;
		send = new Rechercher();
		send.idProduit=idProduit;
		
		//TODO envoyer demande
		//TODO récupérer demande dans ResultatRecherche
		
		if(!received.produitList.isEmpty())
			resultat=received.produitList.get(0);
		return resultat;
	}
	
	public float prixTotal()
	{
		float total = 0;
		for (Produit produit : produits)
		{
			total += produit.prixProduit;
		}
		return  total+(float)(distance*COUTDISTANCE);
	}
	
	public int nbProduit()
	{
		return produits.size();
	}

	public Map<Produit, Integer> acheterCourses(Map<Integer, Integer> courses)
	{
		Achat send = new Achat();
		send.listeCourses=courses;
		ResultatAchat received = new ResultatAchat();
		// TODO envoyer achat et recuperer  resultatAchat
		return received.courses;
	}
}
