package fr.iut.androidprojet.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Questions")
public class Questions {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String question;
    public String reponse1;
    public String reponse2;
    public String reponse3;
    public String bonneReponse;

    // Constructeur
    public Questions(String question, String reponse1, String reponse2, String reponse3, String bonneReponse) {
        this.question = question;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.bonneReponse = bonneReponse;
    }
}
