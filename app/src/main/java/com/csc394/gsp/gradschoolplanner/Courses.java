package com.csc394.gsp.gradschoolplanner;

import android.support.annotation.IntegerRes;

import com.orm.SugarRecord;

/**
 * Created by MP on 10/4/15.
 */
public class Courses  {
    private long courseId;
    private String description;
    private long degreeId;
    private long hasPrereq;
    private String type;
    private long creditHours;

    public long getCourseId(){

        return courseId;
    }

    public void setCourseId(long courseId){

        this.courseId = courseId;
    }

    public String getDescription(){

        return description;
    }

    public void setDescription(String description){

        this.description = description;
    }

    public long getDegreeId(){

        return degreeId;
    }

    public void setDegreeId(long degreeId){

        this.degreeId = degreeId;
    }

    public long getHasPrereq(){

        return hasPrereq;
    }

    public void setHasPrereq(long hasPrereq){

        this.hasPrereq = hasPrereq;
    }
    public String getType(){

        return type;
    }

    public void setType(String type){
        this.type = type;

    }

    public long getCreditHours(){

        return creditHours;
    }

    public void setCreditHours(long creditHours){

        this.creditHours = creditHours;
    }




}
