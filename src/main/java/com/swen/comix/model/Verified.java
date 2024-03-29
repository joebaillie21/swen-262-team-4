package com.swen.comix.model;

public class Verified extends ComicDecorator{

    private double verifiedValueIncrease = 1.2;

    protected Verified(ComicBook comicBook) {
        super(comicBook);
    }
    
    @Override
    public double getValue() {
        return super.getValue()*verifiedValueIncrease;
    }
}
