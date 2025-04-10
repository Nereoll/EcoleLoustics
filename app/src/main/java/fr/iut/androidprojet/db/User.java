package fr.iut.androidprojet.db;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String prenom;

    @NonNull
    public String nom;

    public int meilleurScoreAddition;
    public int meilleurScoreQuizz;
    // Tu peux ajouter d'autres scores si besoin
}
