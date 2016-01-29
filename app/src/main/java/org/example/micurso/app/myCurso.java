package org.example.micurso.app;

/**
 * Created by SNARAUZ on 01/24/2016.
 */
public class myCurso {

    int id;
    String website;
    String abrev;
    myCurso(){

    }
    myCurso(String website,String abrev){
        this.website=website;
        this.abrev=abrev;
    }
    myCurso(int id,String website,String abrev){
        this.id=id;
        this.website=website;
        this.abrev=abrev;
    }
    public int getID(){
        return id;
    }
    public String getWebsite(){
        return website;
    }
    public String getAbrev(){
        return abrev;
    }

}
