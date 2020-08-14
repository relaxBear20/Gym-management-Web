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
public class DetailControl extends BaseLoginedRe {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String packId = request.getParameter("packId");
        if (packId != null) {

        } else {
            String id = request.getParameter("id");
            
            Client c = new CustomerDAO().getClient(Integer.parseInt(id));;
            
            if (c == null) {
                
            } else {
                request.setAttribute("c", c);
                request.getRequestDispatcher("detail.jsp").forward(request, response);
            }
        }

    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("packId");
        new CustomerDAO().deletePack(0, 0);
        
    }

}
