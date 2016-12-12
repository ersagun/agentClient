package fr.miage.agents.agentclient.behaviour;

import fr.miage.agents.agentclient.ClientAgent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



import main.java.fr.miage.agents.api.model.Produit;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import java.util.ArrayList;
import java.util.Iterator;

public class RoutineEbdomadaireBehaviour extends OneShotBehaviour {

    /* TODO ajouter les attributs permettant de communiquer avec les autres agents */
    ClientAgent clientAgent;
    //HashMap<Long, Integer> listeDeCourses;

    public RoutineEbdomadaireBehaviour(ClientAgent maitre) {
        super();
        this.clientAgent = maitre;
    }

    public void action() {
            // on récupère la liste des autres agents en vie
            List<Supermarche> supermarches = listerSupermarche();
            if (!supermarches.isEmpty()) {

            //generateCourse();
            // on demande aux supermarches les prix pour les produits qui nous interessent
            /* =============================GET INFO SUPERMARCHE===================================== */
            for (Supermarche supermarche : supermarches) {
                float distance = supermarche.getDistance();
                supermarche.distance = distance;
                // on compte les produits disponibles ainsi que le prixtotal
                System.out.println("supermarche charlie : " + supermarche.getAid().getName());

                Iterator i = clientAgent.getCourses().keySet().iterator();
                Integer cle;
                Integer val = 0;
                while (i.hasNext()) {
                    cle = (Integer) i.next();
                    val = (Integer) clientAgent.getCourses().get(cle);
                    Long idProduit = Long.parseLong(String.valueOf(cle));
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
                } else if (clientAgent.getSupermarcheMin().nbProduit() > supermarche.nbProduit()) {
                    clientAgent.setSupermarcheMin(supermarche);
                }
            }

            // on liste les produits indisponibles dans le supermarche
            /* =================================GET PRODUITS INDISPONIBLES====================================== */
            Iterator i = clientAgent.getCourses().keySet().iterator();
            Integer cle;
            Integer val = 0;
            HashMap<Long, Integer> temp = new HashMap<Long, Integer>();
            while (i.hasNext()) {
                cle = (Integer) i.next();
                val = (Integer) clientAgent.getCourses().get(cle);
                Long idProduit = Long.parseLong(String.valueOf(cle));

                int quantite = val;
                if (clientAgent.getSupermarcheMin().produits.contains(idProduit)) {
                    temp.put(idProduit, quantite);
                }

            }
            clientAgent.setProduitsRestant(temp);

            /* =================================ECHANGES CLIENTS======================================== */
            clientAgent.setDisponible(true);

            // on envois un message a chaque client un par un pour savoir s'ils n'ont pas un des produits qui nous manque
            List<Client> clients = listerClient();

            // on demande aux clients les prix pour les produits qui nous interessent et on retire les echanges fructueux
            // des produits manquants
            if (!clients.isEmpty()) {
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
            }

            clientAgent.setDisponible(false);
            /*================================ACHAT SUPERMARCHE=====================================*/

 /*-------------------------------ACHAT PERSONNEL-------------------------------------*/
            // on achete les produits du supermarche le moins cher
            Map<Produit, Integer> resultatAchat = clientAgent.getSupermarcheMin().acheterCourses(clientAgent.getCourses());
            clientAgent.setProduitAchete((HashMap)resultatAchat);
            // on supprime les produits achetés de la liste
            
            Iterator i2 = resultatAchat.keySet().iterator();
            Produit cle3;
            Integer val3 = 0;
           
            while (i2.hasNext()) {
                cle3 = (Produit) i2.next();
                val3 = (Integer) ((HashMap)clientAgent.getCourses()).get(cle3.idProduit);
              //  Long idProduit = Long.parseLong(String.valueOf(cle3.idProduit));

                Produit produit = cle3;
                int quantite = val3;
                
                System.out.println(produit.idProduit+"smare");
                 System.out.println(clientAgent.getCourses());
                
               // 
                //System.out.println((()clientAgent.getCourses()).get((Long)produit.idProduit));
                Integer q;
                HashMap hm =((HashMap)clientAgent.getCourses());
                q = ((Integer)hm.get(produit.idProduit)) - quantite;
                ((HashMap)clientAgent.getCourses()).put(produit.idProduit,q);
                if (clientAgent.getCourses().get(produit.idProduit) == 0) {
                    clientAgent.getCourses().remove(produit.idProduit);
                }
            }
            
            
            /**
            
            for (Entry<Produit, Integer> entry : resultatAchat.entrySet()) {
                Produit produit = entry.getKey();
                int quantite = entry.getValue();
                System.out.println(produit.idProduit+"smare");
                 System.out.println(clientAgent.getCourses());
                System.out.println(clientAgent.getCourses().get(produit.idProduit));
                clientAgent.getCourses().put(produit.idProduit,
                        clientAgent.getCourses().get(produit.idProduit) - quantite);
                if (clientAgent.getCourses().get(produit.idProduit) == 0) {
                    clientAgent.getCourses().remove(produit.idProduit);
                }

            }**/
            /*-------------------------------ACHAT ECHANGE-------------------------------------*/
            resultatAchat = clientAgent.getSupermarcheMin().acheterCourses(clientAgent.getProduitEchange());
            
            
            clientAgent.setListeProduitEchange((HashMap)resultatAchat);            
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
            String yolos="Vous avez acheté les produits suivant dans le supermarché "+clientAgent.getSupermarcheMin().getAid().getName()+": \n";
            
            for(Entry<Produit, Integer> entry3 : clientAgent.getProduitAchete().entrySet()) {
                Produit cle2 = entry3.getKey();
                Integer valeur = entry3.getValue();
                yolos+= "Nom produit : "+cle2.nomProduit+", "+"Prix : "+cle2.prixProduit+"; la quantite: "+valeur+"/n";
            }        
            clientAgent.getGui().setResultats(yolos);
                System.out.println("SAAAAAAAMEEEEEERE "+yolos);
                
        }else {System.err.println("Il n'existe pas de supermarché sur le platforme jad ");}
    }

    /*
        * Génére une liste de produits souhaités ajoute entre 1 et 20 produit selectionné aleatoirement parmis les produits
        * existants
     */
    /**
     * private void generateCourse() { int max = 20; int min = 1; int maxcpt =
     * (int) (Math.random() * (max - min) + min); for (int cpt = 0; cpt <
     * maxcpt; cpt++) { max = 149; min = 1; clientAgent.getCourses().put((Long)
     * (Math.random() * (max - min) + min), (int) (Math.random() * (max - min) +
     * min)); } }*
     */

    /*
	 * retourne la liste des supermarchés
     */
    private List<Supermarche> listerSupermarche() {
        AID[] aid = clientAgent.getSellerAgents();
        List<Supermarche> ls = new ArrayList<Supermarche>();
        for (int i = 0; i < aid.length; i++) {
            ls.add(new Supermarche(aid[i], clientAgent));
        }
        return ls;
    }

    /*
	 * retourne la liste des clients
     */
    private List<Client> listerClient() {
        AID[] aid = clientAgent.getClientAgents();
        List<Client> ls = new ArrayList<Client>();
        for (int i = 0; i < aid.length; i++) {
            ls.add(new Client(aid[i], clientAgent));
        }
        return ls;
    }

}
