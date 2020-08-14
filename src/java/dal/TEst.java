/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.ArrayList;
import model.Client;

/**
 *
 * @author admin
 */
public class TEst {

    public static void main(String[] args) {
        ArrayList<Client> ar = new ArrayList<>();
        Client a1 = new Client();
        a1.setId(1);
        ar.add(a1);
        Client a2 = new Client();
        a2.setId(1);
        if(ar.contains(a2))ar.add(a2);
        System.out.println(ar.size());
    }
}
