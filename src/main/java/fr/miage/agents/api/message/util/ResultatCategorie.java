package fr.miage.agents.api.message.util;

import java.util.List;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.TypeMessage;
import fr.miage.agents.api.model.Categorie;

public class ResultatCategorie  extends Message{

    public List<Categorie> categorieList;

    public ResultatCategorie() {
        super(TypeMessage.ResultatCategorie);
    }
}
