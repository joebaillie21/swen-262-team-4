package com.swen.comix.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Author Angela Ngo 
 * This searches solely within the personal collection for runs that have gaps in them 
 */
public class SearchForGaps implements SearchStrategy{
    private PersonalCollection personalCollection;
    final int MAX_MISSING = 2, MIN_CONSEC_ISSUES = 12; 
    ArrayList<ComicBook> runs; 

    /**
     * Takes in personal collection to copy it over to the runs, this is so the personal collection wont be affected
     * @param personalCollection - personal collection to copy for the runs 
     */
    public SearchForGaps(PersonalCollection personalCollection){
        this.personalCollection = personalCollection;
        runs = new ArrayList<>();

        for(int i = 0; i < personalCollection.getPersonalCollection().size(); i++){
            runs.add(personalCollection.getPersonalCollection().get(i));
        }
    }
    
    /***
     * Searches through a copy of personal collection to find runs that have gaps 
     * @param toBeSearched - String will be null in this case 
     * @param isSearchDb - boolean will be false in this case cause its on personal collection
     */
    @Override
    public ArrayList<ComicBook> algorithm(String toBeSearched, boolean isSearchDb) {
        ArrayList<ComicBook> comics = new ArrayList<>(); 
        if(isSearchDb == false){
            comics = searchOnPC(); 
        }
        return comics; 
    } 

    /**
     * Search on the personal collection for the runs with gaps
     */
    private ArrayList<ComicBook> searchOnPC(){
        sort(); 
    
        ArrayList<ArrayList<ComicBook>> sortedByTitle = splitBySeries(); 
        ArrayList<ArrayList<ComicBook>> sortedByVolNum = splitByVolNum(sortedByTitle); 
        ArrayList<ArrayList<ComicBook>> sortedByRuns = splitByIssueNum(sortedByVolNum);
        
        ArrayList<ComicBook> searchedGaps = new ArrayList<>(); 
        for(int i = 0; i < sortedByRuns.size(); i++){
            for(int k = 0; k < sortedByRuns.get(i).size(); k++){
                searchedGaps.add(sortedByRuns.get(i).get(k));
            }
        }

        return searchedGaps; 
    }

    /**
     * This sorts the arraylist by series title, volume number and issue number 
     * @return ArrayList<ComicBook> - returns sorted arraylist sorted by series title, volume number and issue number
     */
    public ArrayList<ComicBook> sort(){
        Comparator<ComicBook> bySeriesTitle = (book1, book2) -> book1.getSeriesTitle().compareTo(book2.getSeriesTitle());
        Comparator<ComicBook> byVolumeNum = (book1, book2) -> Integer.compare(Integer.valueOf(book1.getVolNum()), Integer.valueOf(book2.getVolNum()));
        Comparator<ComicBook> byIssueNum = (book1, book2) -> Integer.compare(Integer.valueOf(book1.getIssueNumber()), Integer.valueOf(book2.getIssueNumber()));

        Comparator<ComicBook> chain = bySeriesTitle
            .thenComparing(byVolumeNum)
            .thenComparing(byIssueNum); 
        
        Collections.sort(runs,chain);
        return (ArrayList<ComicBook>)runs;

        // write tests that test that this change persists in the object 

    }

    

    public ArrayList<ArrayList<ComicBook>> splitBySeries(){
        ArrayList<ArrayList<ComicBook>> series = new ArrayList<>(); 
        ArrayList<ComicBook> serie = new ArrayList<>(); 

        String title = runs.get(0).getSeriesTitle(); 
        for(int i = 0 ; i < runs.size(); i++){
            
            if(title.equals(runs.get(i).getSeriesTitle())){
                serie.add(runs.get(i));
        
            }else if(!(title.equals(runs.get(i).getSeriesTitle()))){
                //System.out.println(runs.get(i).getSeriesTitle());
                series.add(serie); 
                serie = new ArrayList<>(); 
                serie.add(runs.get(i));
                title = runs.get(i).getSeriesTitle();
            }
        }

        series.add(serie);
        return series; 
    }

    public ArrayList<ArrayList<ComicBook>> splitByVolNum(ArrayList<ArrayList<ComicBook>> sortedByTitle){
        ArrayList<ArrayList<ComicBook>> volNums = new ArrayList<>(); 
        ArrayList<ComicBook> vol = new ArrayList<>(); 

        for(int i = 0; i < sortedByTitle.size(); i ++){
            String volNum = sortedByTitle.get(i).get(0).getVolNum();

            for( int k = 0; k < sortedByTitle.get(i).size(); k++){
                ComicBook cb = sortedByTitle.get(i).get(k);
                if(volNum.equals(cb.getVolNum())){
                    vol.add(cb);
                }else if(!volNum.equals(cb.getVolNum())){
                    volNums.add(vol);
                    vol = new ArrayList<>(); 
                    vol.add(cb);
                    volNum = cb.getVolNum();
                }
            }
            volNums.add(vol);
            vol = new ArrayList<>(); 
        }
        return volNums; 
    }

    /**
     * this is supposed to only return the series vol issues if considered a run
     * @param sortedByVolNum
     * @return
     */
    public ArrayList<ArrayList<ComicBook>> splitByIssueNum(ArrayList<ArrayList<ComicBook>> sortedByVolNum){
        ArrayList<ArrayList<ComicBook>> consecutiveNums = new ArrayList<>(); 
        ArrayList<ComicBook> issues = new ArrayList<>(); 
        
        for(int i = 0; i < sortedByVolNum.size(); i++){
            
            String issueNum = sortedByVolNum.get(i).get(0).getIssueNumber();
            int count = 0; 
            int missing = 0;  
            for(int k = 0; k < sortedByVolNum.get(i).size(); k++){ //basic
                ComicBook cb = sortedByVolNum.get(i).get(k);
                String currIssue = cb.getIssueNumber();  

                if(issueNum.equals(currIssue)){
                    count++; 
                    issues.add(cb);
                    int prevNum = Integer.parseInt(issueNum);
                    issueNum = String.valueOf(prevNum+1); 
                }else if(!issueNum.equals(currIssue)){
                    if(missing != MAX_MISSING){
                        missing += Integer.parseInt(currIssue) - Integer.parseInt(issueNum); 
                        issueNum = String.valueOf(Integer.parseInt(currIssue)); 
                        k--; // go back one to get skipped book 
                    }
                }
            }

            if(MAX_MISSING >= missing && count >= MIN_CONSEC_ISSUES){ 
                consecutiveNums.add(issues);   
            }
            issues = new ArrayList<>(); // reset 
        }

        return consecutiveNums; 
    }



    public static void main(String []  args){
        PersonalCollection pc = new PersonalCollection("meow"); 

        Publisher p1 = new Publisher("meo");
        Publisher p2 = new Publisher("nana"); 
        String title1 = "The Amazing Spider-man"; 
        String title2 = "Madoka Majika"; 
        String pubdate = "1-2-03"; 
        ArrayList<Author> author = new ArrayList<>(); 
        author.add(new Author("lalo"));
        author.add(new Author("papa"));  
        ComicBook cb1 = new ComicBookComponent(p1, title1, "1", "2", pubdate, author, null,  "example"  );
        ComicBook cb2 = new ComicBookComponent(p2, title2, "1", "1", pubdate, author, null,  "example"  );
        ComicBook cb3 = new ComicBookComponent(p1, title1, "4", "2", pubdate, author, null,  "example"  );
        ComicBook cb4 = new ComicBookComponent(p2, title2, "3", "1", pubdate, author, null,  "example"  );
        ComicBook cb5 = new ComicBookComponent(p1, title1, "1", "3", pubdate, author, null,  "example"  );
        ComicBook cb6 = new ComicBookComponent(p2, title2, "3", "3", pubdate, author, null,  "example"  );
        // the run 
        ComicBook cb7 = new ComicBookComponent(p1, title1, "1", "1", pubdate, author, null,  "example"  );
        ComicBook cb8 = new ComicBookComponent(p1, title1, "1", "4", pubdate, author, null,  "example"  );
        ComicBook cb9 = new ComicBookComponent(p1, title1, "1", "5", pubdate, author, null,  "example"  );
        ComicBook cb10 = new ComicBookComponent(p1, title1, "1", "6", pubdate, author, null,  "example"  );
        ComicBook cb11 = new ComicBookComponent(p1, title1, "1", "7", pubdate, author, null,  "example"  );
        ComicBook cb12 = new ComicBookComponent(p1, title1, "1", "8", pubdate, author, null,  "example"  );
        ComicBook cb13 = new ComicBookComponent(p1, title1, "1", "9", pubdate, author, null,  "example"  );
        ComicBook cb14 = new ComicBookComponent(p1, title1, "1", "10", pubdate, author, null,  "example"  );
        ComicBook cb15 = new ComicBookComponent(p1, title1, "1", "11", pubdate, author, null,  "example"  );
        ComicBook cb16 = new ComicBookComponent(p1, title1, "1", "12", pubdate, author, null,  "example"  );
        ComicBook cb17 = new ComicBookComponent(p1, title1, "2", "12", pubdate, author, null,  "example"  );
        ComicBook cb18 = new ComicBookComponent(p1, title1, "1", "14", pubdate, author, null,  "example"  );
        ComicBook cb19 = new ComicBookComponent(p1, title1,"1" , "15", pubdate, author, null,  "example"  );
        ComicBook cb20 = new ComicBookComponent(p1, title1,"1" , "13", pubdate, author, null,  "example"  );
        
        pc.add(cb16);
        pc.add(cb1);
        pc.add(cb2);
        pc.add(cb3);
        pc.add(cb4);
        pc.add(cb5); 
        pc.add(cb6); 
        pc.add(cb11);
        pc.add(cb9);
        pc.add(cb14);
        pc.add(cb8);
        pc.add(cb12);
        pc.add(cb10);
        pc.add(cb7);
        pc.add(cb18);
        pc.add(cb19);
        pc.add(cb17);
        pc.add(cb20);


        SearchForGaps sfg = new SearchForGaps(pc);
        ArrayList<ComicBook> sorted = sfg.sort();
        // System.out.println(sorted.size());

        // for(int i = 0; i < sorted.size(); i++){
        //     System.out.println(sorted.get(i));
        // }

        ArrayList<ArrayList<ComicBook>> splitSeries = sfg.splitBySeries();

        // for(int i = 0; i < splitSeries.size(); i++){
        //     System.out.println("mooo");
        //     for(int k = 0; k < splitSeries.get(i).size(); k++){
        //         System.out.println(splitSeries.get(i).get(k)); 
        //     }
        // }

        ArrayList<ArrayList<ComicBook>> splitVolNum = sfg.splitByVolNum(splitSeries);
        
        // for(int i = 0; i < splitVolNum.size(); i++){
        //     System.out.println("meo");
        //     for(int k = 0; k < splitVolNum.get(i).size(); k++){
        //         System.out.println(splitVolNum.get(i).get(k).toString()); 
        //     }
        // }


        ArrayList<ArrayList<ComicBook>> splitIssueNum = sfg.splitByIssueNum(splitVolNum); 

        System.out.println(splitIssueNum.size());
        for(int i = 0; i < splitIssueNum.size(); i++){
            System.out.println("meo");
            for(int k = 0; k < splitIssueNum.get(i).size(); k++){
                System.out.println(splitIssueNum.get(i).get(k).toString()); 
            }
        }
    
        

    }

}