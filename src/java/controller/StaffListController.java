/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CustomerDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Client;

/**
 *
 * @author admin
 */
public class StaffListController extends BaseLoginedRe {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sort = request.getParameter("sort");
        ArrayList<Client> clients =  (ArrayList<Client>)request.getSession().getAttribute("clients");
        if(clients == null){
            clients = new CustomerDAO().getAllClients();
            request.getSession().setAttribute("clients", clients);
        }
        if (sort != null) {
            String ASC = request.getParameter("ASC");

            switch (sort) {
                case "ID": {
                    clients.sort((o1, o2) -> {
                        return ASC.equals("y") ? Integer.compare(o1.getId(), o2.getId()) : -Integer.compare(o1.getId(), o2.getId());
                    });
                    break;
                }
                case "name": {
                    clients.sort((o1, o2) -> {
                        return ASC.equals("y") ? o1.getName().compareTo(o2.getName()) : -o1.getName().compareTo(o2.getName());
                    });
                    break;
                }

            }
            for (Client client : clients) {
                System.out.println(client.getId());
            }

            request.setAttribute("ASC", ASC.equals("y") ? "n" : "y");
            request.setAttribute("clients", clients);
            request.getRequestDispatcher("StaffList.jsp").forward(request, response);

        } else {
            request.setAttribute("ASC", "y");
            request.setAttribute("clients", clients);
            request.getRequestDispatcher("StaffList.jsp").forward(request, response);
        }

    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
