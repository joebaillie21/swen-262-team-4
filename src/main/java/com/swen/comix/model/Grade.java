package com.swen.comix.model;

public class Grade extends ComicDecorator{
    public Grade(ComicBook comicBook){
        super(comicBook);
    }

    @Override
    public double getValue() {
        if(this.comicBook.getGrade() == 1){
            return super.getValue()*0.1;
        }
        else if(this.comicBook.getGrade()>1 && this.comicBook.getGrade()<=10){
            return Math.log10(super.getGrade())*super.getValue();
        }
        else{
            return super.getValue();
        }
    }
}
