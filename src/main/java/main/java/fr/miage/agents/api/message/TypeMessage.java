package main.java.fr.miage.agents.api.message;

/**
 * Created by nitix on 14/11/16.
 */
public enum TypeMessage {

    //Session
    DemanderSession,
    ResultatDemandeSession,

    //Négociation
    InitierAchat,
    ResultatInitiationAchat,
    NegocierPrix,
    ResultatNegociation,
    AnnulerAchat,
    ResultatAnnulationAchat,
    FinaliserAchat,
    ResultatFinalisationAchat,

    //Recherche
    Recherche,

    ResultatRecherche,

    //Util
    Aide,
    DemandeCategorie,
    DemandeDistance,
    PrevenirSolde,
    ResultatCategorie,
    AppelMethodeIncorrect,
    ResultatAide,

    //interClients
    DemandeEchange,
    ReponseEchange,

    //Production
    Production,
	
	//Courses
	AchatClient,
	ResultatAchatClient;
}
