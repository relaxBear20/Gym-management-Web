/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.PackDAO;
import dal.TrainerDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pack;
import model.Trainer;
import model.Trainer_Clients;

/**
 *
 * @author admin
 */
public class StaffEditTrainer extends BaseLoginedRe {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ArrayList<Trainer> trainers = new TrainerD
        ArrayList<Trainer_Clients> trianers = new TrainerDAO().getListClient_Trainers();
        ArrayList<Trainer> avlTrainers = new TrainerDAO().getAllTrainers();
        ArrayList<Pack> packs = new PackDAO().getAllPacks();

        request.setAttribute("avlTrainer", avlTrainers);
        request.setAttribute("trainers", trianers);
        request.setAttribute("packs", packs);
        request.getRequestDispatcher("StaffEditTrainer.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("addName");
        String[] spec = request.getParameterValues("pack");
        System.out.println("Name");
        boolean c = false;
        if (name.equals("")) {
            request.setAttribute("warning", "Null Found!");
            processGet(request, response);
        }
        if (spec != null) {
            for (String string : spec) {
                System.out.println(string);
            }
            c = new TrainerDAO().addTrainer(name, spec);
        } else {
            request.setAttribute("warning", "No specs found");
            processGet(request, response);
        }
        if (c) {
            request.setAttribute("success", "Add Success");
            processGet(request, response);
        } else {
            request.setAttribute("warning", "Something went wrong!");
            processGet(request, response);
        }
        request.setAttribute("warning", "Add Success");
        processGet(request, response);

    }

}
