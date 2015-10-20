package com.csc394.gsp.gradschoolplanner;

import com.orm.SugarRecord;

/**
 * Created by MP on 10/4/15.
 */
public class Faculty {
    private long facultyId;
    private String firstName;
    private String lastName;
    private String email;
    private long permissionsId;

    public long getFacultyId(){

        return facultyId;
    }

    public void setFacultyId(long facultyId){

        this.facultyId = facultyId;
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

    public long getPermissionsId(){

        return permissionsId;
    }

    public void setPermissionsId(long permissionsId){

        this.permissionsId = permissionsId;
    }
}
