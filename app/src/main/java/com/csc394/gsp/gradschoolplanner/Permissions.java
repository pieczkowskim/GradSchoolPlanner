package com.csc394.gsp.gradschoolplanner;


import java.lang.Override;import java.lang.String; /**
 * Created by MP on 10/4/15.
 */
public class Permissions {
    private long permissionsId;
    private String type;

    public long getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(long permissionsId){
        this.permissionsId = permissionsId;
    }

    public String getPermissionsType(){
        return type;
    }

    public void setPermissionsType(String type){
        this.type = type;

    }

    @Override
    public String toString() {

        return type;
    }

}
