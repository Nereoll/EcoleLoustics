package fr.iut.androidprojet.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import fr.iut.androidprojet.db.User;
import fr.iut.androidprojet.db.UserDao;

@Database(entities = {User.class, Questions.class}, version =5)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract QuestionsDAO questionsDAO();
}
