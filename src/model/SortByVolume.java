package model;
import java.util.*;

public class SortByVolume implements SortStrategy{

    private PersonalCollection personalCollection;

    public SortByVolume(PersonalCollection pc){
        this.personalCollection = pc;
    }

    @Override
    public ArrayList<ComicBook> algorithm(String toBeSorted, boolean isSortDb) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'algorithm'");
    }
    
}
