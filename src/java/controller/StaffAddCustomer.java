/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CustomerDAO;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class StaffAddCustomer extends BaseLoginedRe {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("StaffAddCustomer.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("lname") +" " + request.getParameter("fname");
        String address = request.getParameter("address");
        String tel = request.getParameter("tel");
        String dob = request.getParameter("dob");
        System.out.println(dob);
        if (name.equals("") || tel.equals("") || address.equals("")) {
            request.setAttribute("warning", "Null Found!");
            processGet(request, response);
        } else {
            if (!tel.matches("\\d{10}")) {
                request.setAttribute("warning", "Wrong phone format");
                processGet(request, response);
            }
            boolean s = new CustomerDAO().addCustomer(name, address, dob, tel);
            if (!s) {
                request.setAttribute("warning", "Something went wrong!");
                processGet(request, response);
            } else {
                request.setAttribute("success", "Add Success!");
                processGet(request, response);
            }
        }
    }

}
