package org.miage.m2sid.agentClient.behaviour;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.miage.m2sid.agentClient.ClientAgent;

import fr.miage.agents.api.model.Produit;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import java.util.ArrayList;

public class RoutineEbdomadaireBehaviour extends CyclicBehaviour {

    /* TODO ajouter les attributs permettant de communiquer avec les autres agents */
    ClientAgent clientAgent;
    //HashMap<Long, Integer> listeDeCourses;
    

    public RoutineEbdomadaireBehaviour(ClientAgent maitre) {
        super();
        this.clientAgent = maitre;
    }

    RoutineEbdomadaireBehaviour(ClientAgent client, HashMap<Long, Integer> ldc) {
        super();
        this.clientAgent = client;
        this.clientAgent.setCourses(ldc);
    }

    public void action() {

        // on récupère la liste des autres agents en vie
        List<Supermarche> supermarches = listerSupermarche();
        //generateCourse();

        // on demande aux supermarches les prix pour les produits qui nous interessent
        /* =============================GET INFO SUPERMARCHE===================================== */
        for (Supermarche supermarche : supermarches) {
            float distance = supermarche.getDistance();

            supermarche.distance = distance;
            // on compte les produits disponibles ainsi que le prixtotal
            for (Map.Entry<Long, Integer> entry : clientAgent.getCourses().entrySet()) {
                Long idProduit = entry.getKey();
                //int quantite = entry.getValue();
                Produit resultat = supermarche.rechercher(idProduit);
                if (!(resultat == null)) {
                    supermarche.produits.add(resultat);
                }
            }
        }

        /* ================================GET BEST SUPERMARCHE======================================== */
        clientAgent.setSupermarcheMin(supermarches.get(0));
        // on garde le supermarché le moins cher
        for (Supermarche supermarche : supermarches) {
            if (clientAgent.getSupermarcheMin().nbProduit() == supermarche.nbProduit()) {
                if (clientAgent.getSupermarcheMin().prixTotal() > supermarche.prixTotal()) {
                    clientAgent.setSupermarcheMin(supermarche);
                }
            } else {
                if (clientAgent.getSupermarcheMin().nbProduit() > supermarche.nbProduit()) {
                    clientAgent.setSupermarcheMin(supermarche);
                }
            }
        }

        // on liste les produits indisponibles dans le supermarche
        /* =================================GET PRODUITS INDISPONIBLES====================================== */
        for (Entry<Long, Integer> entry : clientAgent.getCourses().entrySet()) {
            Long idProduit = entry.getKey();
            int quantite = entry.getValue();
            if (clientAgent.getSupermarcheMin().produits.contains(idProduit)) {
                clientAgent.getProduitsRestant().put(idProduit, quantite);
            }

        }

        /* =================================ECHANGES CLIENTS======================================== */
        clientAgent.setDisponible(true);

        // on envois un message a chaque client un par un pour savoir s'ils n'ont pas un des produits qui nous manque
        List<Client> clients = listerClient();

        // on demande aux clients les prix pour les produits qui nous interessent et on retire les echanges fructueux
        // des produits manquants
        for (Client client : clients) {
            for (Entry<Long, Integer> entry : clientAgent.getProduitsRestant().entrySet()) {
                Long idProduit = entry.getKey();
                Integer quantite = entry.getValue();
                HashMap<Produit, Integer> echange = client.askProduit(idProduit, quantite);
                for (Entry<Produit, Integer> entry2 : echange.entrySet()) {
                    Produit produit = entry2.getKey();
                    Integer quantite2 = entry2.getValue();

                    if (produit.idProduit == idProduit) {
                        clientAgent.getProduitsRestant().replace(idProduit, clientAgent.getProduitsRestant().get(idProduit) - quantite2);
                        clientAgent.getCourses().replace(idProduit, clientAgent.getProduitsRestant().get(idProduit) - quantite2);
                    }
                }
            }
        }

        clientAgent.setDisponible(false);
        /*================================ACHAT SUPERMARCHE=====================================*/

 /*-------------------------------ACHAT PERSONNEL-------------------------------------*/
        // on achete les produits du supermarche le moins cher
        Map<Produit, Integer> resultatAchat = clientAgent.getSupermarcheMin().acheterCourses(clientAgent.getCourses());
        // on supprime les produits achetés de la liste
        for (Entry<Produit, Integer> entry : resultatAchat.entrySet()) {
            Produit Produit = entry.getKey();
            int quantite = entry.getValue();
            clientAgent.getCourses().replace(Produit.idProduit, clientAgent.getCourses().get(Produit.idProduit) - quantite);
            if (clientAgent.getCourses().get(Produit.idProduit) == 0) {
                clientAgent.getCourses().remove(Produit.idProduit);
            }

        }
        /*-------------------------------ACHAT ECHANGE-------------------------------------*/
        resultatAchat = clientAgent.getSupermarcheMin().acheterCourses(clientAgent.getProduitEchange());
        // on supprime les produits achetés de la liste
        for (Entry<Produit, Integer> entry : resultatAchat.entrySet()) {
            Produit Produit = entry.getKey();
            int quantite = entry.getValue();
            clientAgent.getProduitEchange().replace(Produit.idProduit, clientAgent.getProduitEchange().get(Produit.idProduit) - quantite);
            if (clientAgent.getProduitEchange().get(Produit.idProduit) == 0) {
                clientAgent.getProduitEchange().remove(Produit.idProduit);
            }

        }
        // TO/!\Impossible/!\DO echanger les produits 
        clientAgent.setSupermarcheMin(null);
        clientAgent.setProduitsRestant(new HashMap<Long, Integer>());
    }

    /*
	 * Génére une liste de produits souhaités ajoute entre 1 et 20 produit selectionné aleatoirement parmis les produits
	 * existants
     */
    /**
    private void generateCourse() {
        int max = 20;
        int min = 1;
        int maxcpt = (int) (Math.random() * (max - min) + min);
        for (int cpt = 0; cpt < maxcpt; cpt++) {
            max = 149;
            min = 1;
            clientAgent.getCourses().put((Long) (Math.random() * (max - min) + min), (int) (Math.random() * (max - min) + min));
        }
    }**/

    /*
	 * retourne la liste des supermarchés
     */
    private List<Supermarche> listerSupermarche() {
       AID[] aid = clientAgent.getSellerAgents();
       List<Supermarche> ls=new ArrayList<Supermarche>();
       for(int i=0;i<aid.length;i++){
           ls.add(new Supermarche(aid[i],clientAgent));
       }
        return ls;
    }

    /*
	 * retourne la liste des clients
     */
    private List<Client> listerClient() {
        AID[] aid = clientAgent.getSellerAgents();
       List<Client> ls=new ArrayList<Client>();
       for(int i=0;i<aid.length;i++){
           ls.add(new Client(aid[i],clientAgent));
       }
        return ls;
    }

}
