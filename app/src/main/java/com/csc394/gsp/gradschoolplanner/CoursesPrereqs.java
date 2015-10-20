package com.csc394.gsp.gradschoolplanner;

/**
 * Created by Gosia_Samosia on 10/10/15.
 */
public class CoursesPrereqs {
    private long fCourseId;
    private long fPrereqId;

    public long getFCourseId(){

        return fCourseId;
    }

    public void setFCourseId(long fCourseId){

        this.fCourseId = fCourseId;
    }

    public long getFPrereqId(){

        return fPrereqId;
    }

    public void setFPrereqId(long fPrereqId){

        this.fPrereqId = fPrereqId;
    }
}
