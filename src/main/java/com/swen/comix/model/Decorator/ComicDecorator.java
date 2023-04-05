package com.swen.comix.model.Decorator;

import java.util.ArrayList;

import com.swen.comix.model.Author;
import com.swen.comix.model.ComicBook;
import com.swen.comix.model.Publisher;

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
    public int getVolNum() {
        return comicBook.getVolNum();
    }


    @Override
    public int getIssueNum() {
        return comicBook.getIssueNum();
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
    public void gradeAlgorithm() {
        comicBook.gradeAlgorithm();
    }
}