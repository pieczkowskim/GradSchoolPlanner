package com.csc394.gsp.gradschoolplanner;


import java.lang.String; /**
 * Created by MP on 10/4/15.
 */
public class Students {
    private long studentId;
    private String firstName;
    private String lastName;
    private String email;
    private long degreeId;
    private String startTermQ;
    private long startTermY;
    private long coursesPerQ;
    private long permissionsId;


    public long getStudentId(){

        return studentId;
    }

    public void setStudentId(long studentId){

        this.studentId = studentId;
    }

    public String getFirstName(){

        return firstName;
    }

    public void setFirstName(String firstName){

        this.firstName = firstName;
    }

    public String getLastName(){

        return lastName;
    }

    public void setLastName(String lastName){

        this.lastName = lastName;
    }

    public String getEmail(){

        return email;
    }

    public void setEmail(String email){

        this.email = email;
    }

    public long getDegreeId(){

        return degreeId;
    }

    public void setDegreeId(long degreeId){

        this.degreeId = degreeId;
    }

    public void setStartTermQ(String startTermY){

        this.startTermQ = startTermQ;
    }

    public String getStartTermQ(){

        return startTermQ;
    }

    public void setStartTermY(long startTermY){

        this.startTermY = startTermY;
    }

    public long getStartTermY(){

        return startTermY;
    }

    public long getcoursesPerQ(){

        return coursesPerQ;
    }

    public void setcoursesPerQ(long coursesPerQ){

        this.coursesPerQ = coursesPerQ;
    }

    public long getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(long permissionsId){
        this.permissionsId = permissionsId;
    }


}
