/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Trainer {
    int id; 
    String name;
    boolean isAval;
    ArrayList<Integer> specs = new ArrayList<>();

    public ArrayList<Integer> getSpecs() {
        return specs;
    }

    public void setSpecs(ArrayList<Integer> specs) {
        this.specs = specs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsAval() {
        return isAval;
    }

    public void setIsAval(boolean isAval) {
        this.isAval = isAval;
    }
}
