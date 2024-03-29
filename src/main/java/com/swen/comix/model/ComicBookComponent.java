package com.swen.comix.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @Author Angela Ngo
 */
public class ComicBookComponent implements ComicBook {
    static final String STRING_FORMAT = "[Publisher=%s, Author=%s, Title=%s, Description=%s, VolNum=%s, IssueNum=%s, Characters=%s]";

    private Publisher publisher; 
    private ArrayList<Author> authors; 
    private String seriesTitle, publicationDate, description, issueNumber, volNum; 
    private int grade, signatures; 

    private ArrayList<String> principleCharacters; 
    private boolean isAuthenticated, isSlabbed; 
    private double value; 
    final int DEFAULT_SIG_AMT = 0, DEFAULT_GRADE = 1, DEFAULT_VALUE = -1; 


    public ComicBookComponent(Publisher publisher, String seriesTitle, String volNum, String issueNumber, String publicationDate, ArrayList<Author> authors, ArrayList<String> principleCharacters, String description){
        this.publisher = publisher; 
        this.authors = authors; 
        this.seriesTitle = seriesTitle; 
        this.volNum = volNum;
        this.issueNumber = issueNumber;  
        this.publicationDate = publicationDate; 
        this.principleCharacters = principleCharacters; 
        this.description = description; 
        this.signatures = DEFAULT_SIG_AMT;
        this.grade = DEFAULT_GRADE;
        this.value = DEFAULT_VALUE;
        isAuthenticated = false; 
        isSlabbed = false; 

    }

    // need to do something about the grading stuff since it has to directly impact
    // the comicbook components
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getPrincipleCharacters() {
        return principleCharacters;
    }

    public int getSignatures() {
        return signatures;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public String getVolNum() {
        return volNum;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public int getGrade() {
        return grade;
    }

    public double getValue() {
        return value;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public boolean getSlabbed() {
        return isSlabbed;
    }

    public void setAuthentication(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public void setGrade(int newGrade) {
        this.grade = newGrade;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    public void setSlabbed(boolean isSlabbed) {
        this.isSlabbed = isSlabbed;
    }

    public void setSignatures(int signatures) {
        this.signatures = signatures;
    }

    public boolean equals(Object o) {
        if (!(o instanceof ComicBookComponent)) {
            return false;
        }

        ComicBookComponent other = (ComicBookComponent) o;

        ArrayList<Author> auth = other.getAuthors();
        for(int i = 0; i < auth.size(); i++){
            if(!auth.get(i).getName().equals(authors.get(i).getName())){
                return false;
            }
        }

        return (other.getPublicationDate().equals(publicationDate) && other.getGrade() == grade
                && other.getIssueNumber().equals(issueNumber) && other.getVolNum().equals(volNum)
                && other.getPublisher().getName().equals(publisher.getName())
                && other.isAuthenticated() == isAuthenticated
                && other.getSeriesTitle().equals(seriesTitle)
                && other.getSlabbed() == isSlabbed);

    }

    public String toString() {
        return String.format(STRING_FORMAT, publisher.getName(), authors.toString(), seriesTitle, description, volNum,
                issueNumber, principleCharacters);
    }
}
