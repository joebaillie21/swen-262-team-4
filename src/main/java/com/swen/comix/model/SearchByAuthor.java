package com.swen.comix.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.swen.comix.db.*;

/**
 * @Author Angela and Joe
 */
public class SearchByAuthor implements SearchStrategy {
    private PersonalCollection personalCollection;
    private Database db;

    public SearchByAuthor(PersonalCollection pc, Database db){
        this.db = db; 
        this.personalCollection = pc; 
    }
    /* For Testing purposes */
    public SearchByAuthor(PersonalCollection pc) {
        this.personalCollection = pc;
    }

    public SearchByAuthor(Database db) {
        this.db = db;
    }

    @Override
    public ArrayList<ComicBook> algorithm(String toBeSearched, boolean isSearchDb) throws Exception {
        if (toBeSearched.isEmpty()) {
            System.out.println("Invalid input.");
            return null;
        }
        ArrayList<ComicBook> comics = new ArrayList<>();
        String[] toBeSearchedSplit = toBeSearched.split(",");
        if (isSearchDb == true) {
            comics = searchOnDb(toBeSearched);
        } else if (isSearchDb == false) {
            comics = searchOnPC(toBeSearchedSplit);
        }
        return comics;
    }

    /**
     * @param toBeSearched
     * @return ArrayList of comics with the given author name
     * @throws Exception
     * @author Joe
     */

    private ArrayList<ComicBook> searchOnDb(String toBeSearched) throws Exception {

        ResultSet res = db.getTable("SELECT * FROM comics WHERE author LIKE '%" + toBeSearched + "%'");

        return db.resToArrayList(res);
    }

    /**
     * This searches for comic books that has the same list of authors
     * 
     * @param toBeSearched
     * @return Arraylist of comic books
     * @Author Angela
     */
    private ArrayList<ComicBook> searchOnPC(String[] toBeSearched) {
        ArrayList<ComicBook> comics = new ArrayList<>();
        ArrayList<ComicBookComponent> pc = (ArrayList<ComicBookComponent>) personalCollection.getPersonalCollection();

        boolean isMatch = true; // reset everytime new comic iterate
        for (int i = 0; i < pc.size(); i++) {
            ComicBookComponent comic = (ComicBookComponent) pc.get(i);
            ArrayList<Author> authors = comic.getAuthors();

            for (int k = 0; k < toBeSearched.length; k++) {
                String name = authors.get(k).getName();
                if (!name.equals(toBeSearched[k])) {
                    isMatch = false;
                    break;
                }
            }

            if (isMatch == true) {
                comics.add(comic);
            }

        }
        // return the ones even if it doesn reach the max NUM_COUNT
        return comics;
    }
}
