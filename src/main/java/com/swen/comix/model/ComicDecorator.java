package com.swen.comix.model;

import java.util.ArrayList;

public class ComicDecorator implements ComicBook{

    protected ComicBook comicBook;

    protected ComicDecorator(ComicBook comicBook){
        this.comicBook = comicBook;
    }

    @Override
    public Publisher getPublisher() {
        return comicBook.getPublisher();
    }


    @Override
    public ArrayList<Author> getAuthors() {
        return comicBook.getAuthors();
    }


    @Override
    public String getSeriesTitle() {
        return comicBook.getSeriesTitle();

    }


    @Override
    public String getPublicationDate() {
        return comicBook.getPublicationDate();
    }


    @Override
    public String getDescription() {
        return comicBook.getDescription();
    }


    @Override
    public String getVolNum() {
        return comicBook.getVolNum();
    }


    @Override
    public String getIssueNumber() {
        return comicBook.getIssueNumber();
    }


    @Override
    public int getGrade() {
        return comicBook.getGrade();
    }


    @Override
    public ArrayList<String> getPrincipleCharacters() {
        return comicBook.getPrincipleCharacters();
    }


    @Override
    public boolean isAuthenticated() {
        return comicBook.isAuthenticated();
    }


    @Override
    public double getValue() {
        return comicBook.getValue();
    }

    @Override
    public int getSignatures() {
        return comicBook.getSignatures();
    }

    @Override
    public void setGrade(int newGrade) {
        comicBook.setGrade(newGrade);
    }

    @Override
    public void setValue(double value){
        comicBook.setValue(value);
    }

    @Override
    public void setSignatures(int signatures) {
        comicBook.setSignatures(signatures);
    }
}
