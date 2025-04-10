package fr.iut.androidprojet.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuestionsDAO {
    @Query("SELECT * FROM Questions")
    List<Questions> getAll();

    @Insert
    void insert(Questions... questions);
}
