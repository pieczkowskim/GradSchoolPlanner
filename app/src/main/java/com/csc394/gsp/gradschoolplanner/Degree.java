package com.csc394.gsp.gradschoolplanner;

import java.lang.Override;import java.lang.String; /**
 * Created by MP on 10/4/15.
 */
public class Degree {

    private long degreeId;
    private String name;

    public long getDegreeId(){

        return degreeId;
    }

    public void setDegreeId(long degreeId){

        this.degreeId = degreeId;
    }

    public String getDegreeName(){

        return name;
    }

    public void setDegreeName(String name){

        this.name = name;
    }

    @Override
    public String toString() {

        return name;
    }

}
