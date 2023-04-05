package com.swen.comix.model;

import java.util.ArrayList;

public interface ComicBook {
    public void gradeAlgorithm();
    public Publisher getPublisher(); 
    public ArrayList<Author> getAuthors();
    public String getSeriesTitle();
    public String getPublicationDate();
    public String getDescription();
    public int getVolNum();
    public int getIssueNum();
    public int getGrade();
    public int getSignatures();
    public ArrayList<String> getPrincipleCharacters();
    public boolean isAuthenticated();
    public double getValue();
}