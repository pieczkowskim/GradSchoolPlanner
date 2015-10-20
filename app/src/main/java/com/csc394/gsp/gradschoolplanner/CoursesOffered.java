package com.csc394.gsp.gradschoolplanner;

/**
 * Created by Gosia_Samosia on 10/10/15.
 */
public class CoursesOffered {
    private long fCourseId;
    private long fTermId;

    public long getFCourseId(){

        return fCourseId;
    }

     public void setFCourseId(long fCourseId){

         this.fCourseId = fCourseId;
     }

     public long getfTermId(){

         return fTermId;
     }

    public void setfTermId(long fTermId){

        this.fTermId = fTermId;
    }
}
