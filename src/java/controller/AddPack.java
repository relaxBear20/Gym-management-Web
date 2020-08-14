/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CustomerDAO;
import dal.PackDAO;
import dal.TrainerDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Client;
import model.Pack;
import model.Trainer;
import model.Trainer_Clients;

/**
 *
 * @author admin
 */
public class AddPack extends BaseLoginedRe {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Client c = new CustomerDAO().getClient(Integer.parseInt(id));
        ArrayList<Pack> clientPacks = null;
        if (!c.getPacks().isEmpty()) {
            clientPacks = c.getPacks();
        }
        ArrayList<Pack> packs = new PackDAO().getAllPacks();
        ArrayList<Trainer> trainers = new TrainerDAO().getAllTrainers();
        request.getSession().setAttribute("trainers", trainers);
        if (clientPacks != null) {
            for (int i = 0; i < packs.size(); i++) {
                for (int j = 0; j < clientPacks.size(); j++) {
                    if (clientPacks.get(j).getName().equals(packs.get(i).getName())) {
                        packs.remove(i);
                    }
                }
            }
        }
        request.setAttribute("trainers", trainers);
        request.setAttribute("id", id);
        request.setAttribute("packs", packs);
        request.setAttribute("client", c);
        request.getRequestDispatcher("StaffAddCliPck.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");
        switch (mode) {
            case "check": {
                int packId = Integer.parseInt(request.getParameter("pack"));
                System.out.println(packId);
                String packName = "";
                String time = request.getParameter("time");
                String id = request.getParameter("id");
                String trianerId = request.getParameter("trainer");
                if (trianerId == null) {
                    request.setAttribute("warning", "No Trainer found");
                    processGet(request, response);
                }
                int tid = 0;
                Client c = new CustomerDAO().getClient(Integer.parseInt(id));
                ArrayList<Pack> packs = new PackDAO().getAllPacks();
                ArrayList<Trainer> trainers = (ArrayList<Trainer>) request.getSession().getAttribute("trainers");
                String tname = "";
                for (Trainer trainer1 : trainers) {
                    if (trainer1.getId() == Integer.parseInt(trianerId)) {
                        tname = trainer1.getName();
                        tid = trainer1.getId();
                    }

                }
                int nTime = Integer.parseInt(time);
                int price = 0;
                int count = 0;
                Pack addPack = new Pack();
                for (Pack pack : packs) {
                    if (pack.getId() == packId) {
                        addPack = pack;
                        packName = pack.getName();
                        price = pack.getPrice() * nTime;
                        price -= price * (pack.getSale() / 100);
                        break;
                    }
                    count++;
                }
                if (c.isIsVip()) {
                    price -= price * 10 / 100;
                }

                if (count == packs.size()) {
                }
                request.setAttribute("tid", tid);
                request.setAttribute("tname", tname);
                request.getSession().setAttribute("addPack", addPack);
                request.setAttribute("packId", packId);
                request.setAttribute("id", id);
                request.setAttribute("time", time);
                request.setAttribute("packName", packName);
                request.setAttribute("price", price);
                request.setAttribute("client", c);
                request.getRequestDispatcher("StaffCheckOut.jsp").forward(request, response);
            }
            case "add": {

                Pack p = (Pack) request.getSession().getAttribute("addPack");
                String id = request.getParameter("id");
                String time = request.getParameter("time");
                try {
                    if (p == null) {
                        request.setAttribute("warning", "Wrong type of input");
                        processGet(request, response);
                    }
                    int nTime = Integer.parseInt(time);
                    int nId = Integer.parseInt(id);
                    int tId = Integer.parseInt(request.getParameter("tid"));
                    boolean c = new CustomerDAO().addPack(nId, tId, p.getId(), nTime);

                    if (c) {
                        request.setAttribute("success", "Success");
                        processGet(request, response);
                    } else {
                        request.setAttribute("warning", "Something went wrong!");
                        processGet(request, response);
                    }

                    System.out.println(time);
                } catch (IOException | NumberFormatException e) {
                    request.setAttribute("warning", "Wrong type of input");
                    processGet(request, response);
                }

            }
        }
    }

}
