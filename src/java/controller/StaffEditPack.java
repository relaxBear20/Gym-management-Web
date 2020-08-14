/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.PackDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pack;

/**
 *
 * @author admin
 */
public class StaffEditPack extends BaseLoginedRe {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String addName = request.getParameter("addName");

        ArrayList<Pack> packs = new PackDAO().getAllPacks();
        request.getSession().setAttribute("packs", packs);
        request.setAttribute("packs", packs);
        request.getRequestDispatcher("StaffEditPack.jsp").forward(request, response);

    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String addName = request.getParameter("addName");
        ArrayList<Pack> packs = (ArrayList<Pack>) request.getSession().getAttribute("packs");
        request.setAttribute("packs", packs);
        
        if (addName.equals("delete")) {
            System.out.println("a");
            int id = Integer.parseInt(request.getParameter("id"));
            boolean c = new PackDAO().delete(id);
            if (c) {
                request.setAttribute("success", "Delete Success");
                processGet(request, response);
            } else {
                request.setAttribute("warning", "Something went wrong");
                processGet(request, response);
            }
        } else {
            if (addName.equals("")) {
            request.setAttribute("warningAdd", "Please enter a Name");
            request.getRequestDispatcher("StaffEditPack.jsp").forward(request, response);
        } else if (addName != null) {
            String price = request.getParameter("price");
            String sale = request.getParameter("sale");
            if (price.equals("") || sale.equals("")) {
                request.setAttribute("warningAdd", "Null found!");
                request.getRequestDispatcher("StaffEditPack.jsp").forward(request, response);
            }
            try {
                int nPrice = Integer.parseInt(price);
                int nSale = Integer.parseInt(sale);
                boolean s = new PackDAO().addPacks(addName, nPrice, nSale);
                if (s) {
                    packs = new PackDAO().getAllPacks();
                    request.setAttribute("packs", packs);
                    request.getRequestDispatcher("StaffEditPack.jsp").forward(request, response);

                } else {
                    request.setAttribute("warningAdd", "Add Failed");
                    request.getRequestDispatcher("StaffEditPack.jsp").forward(request, response);

                }
            } catch (Exception e) {
                request.setAttribute("warningAdd", "Word found in int field!");
                request.getRequestDispatcher("StaffEditPack.jsp").forward(request, response);

            }
        }
        }
        
        
    }

}
