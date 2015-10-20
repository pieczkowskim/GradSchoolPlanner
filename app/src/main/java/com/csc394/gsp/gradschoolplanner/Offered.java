package com.csc394.gsp.gradschoolplanner;

/**
 * Created by Gosia_Samosia on 10/10/15.
 */
public class Offered {
    private long offeredId;
    private String term;
    private long year;

    public long getOfferedId(){

        return offeredId;
    }

    public void setOfferedId(long offeredId){

        this.offeredId = offeredId;
    }

    public String getTerm(){

        return term;
    }

    public void setTerm(String term){

        this.term = term;
    }

    public long getYear(){

        return year;
    }

    public void setYear(long year){

        this.year = year;
    }
}

