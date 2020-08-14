/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CustomerDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Client;

/**
 *
 * @author admin
 */
public class StaffSearch extends BaseLoginedRe {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("StaffSearch.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        int ind = 0;
        if (id == null || id.equals("")) {
            ind = 0;
        } else {
            ind = Integer.parseInt(id);
        }
        ArrayList<Client> clients = new CustomerDAO().getClients(ind, name, tel);
        if (clients != null) {
            for (Client client : clients) {
                System.out.println(client);
            }
        } else {
            request.setAttribute("warning", "Not Found!");
        }
        request.setAttribute("id", id);
        request.setAttribute("name", name);
        request.setAttribute("tel", tel);
        request.setAttribute("clients", clients);

        request.getRequestDispatcher("StaffSearch.jsp").forward(request, response);
    }

}
