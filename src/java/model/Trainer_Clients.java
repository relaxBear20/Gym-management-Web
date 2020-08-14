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
public class Trainer_Clients {
    String trainerName;

    
    int trainerId;
    ArrayList<Client> clients = new ArrayList<>();
    boolean trainerAvaliable ;
    ArrayList<Pack> packs = new ArrayList<>();
    ArrayList<String> specs = new ArrayList<>();
    public String getTrainerName() {
        return trainerName;
    }
public ArrayList<String> getSpecs() {
        return specs;
    }

    public void setSpecs(ArrayList<String> specs) {
        this.specs = specs;
    }
    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> Clients) {
        this.clients = Clients;
    }

    public boolean isTrainerAvaliable() {
        return trainerAvaliable;
    }

    public void setTrainerAvaliable(boolean trainerAvaliable) {
        this.trainerAvaliable = trainerAvaliable;
    }

    public ArrayList<Pack> getPacks() {
        return packs;
    }

    public void setPacks(ArrayList<Pack> packs) {
        this.packs = packs;
    }
    
}
