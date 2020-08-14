<%-- 
    Document   : StaffList
    Created on : Mar 27, 2020, 7:45:34 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/basic.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>
        
        <div class="w3-sidebar w3-bar-block w3-border-right" style="display:none" id="mySidebar">
            <button onclick="w3_close()" class="w3-bar-item w3-large">Close &times;</button>
            <a href="StaffAddCustomer" id="link_active" class="w3-bar-item w3-button">Add Client</a>
            <a href="StaffSearch" class="w3-bar-item w3-button">Search For Client</a>
            <a href="StaffEditPack" class="w3-bar-item w3-button">Detail Packs</a>
            <a href="StaffEditTrainer" class="w3-bar-item w3-button">Detail Trainers</a>
        </div>
        <div class="top_Container" >
            
            <div class="w3-bar w3-black" style=" padding: 10px">
                <button class="w3-bar-item w3-button" onclick="location.href='StaffList'">List</button>
                <button class="w3-bar-item w3-button" onclick="location.href='StaffAddCustomer'">Add Client</button>
                <button class="w3-bar-item w3-button" onclick="location.href='StaffSearch'">Search For Client</button>
                <button class="w3-bar-item w3-button" onclick="location.href='StaffEditPack'">Detail Packs</button>
                <button class="w3-bar-item w3-button" onclick="location.href='StaffEditTrainer'">Detail Trainers</button>
            </div>
            
        </div>
        <div class="mid_Container">
            <div class="my-container" >
                <div class="node_header" ><b>Client</b></div>
                <form>
                    <table class="w3-table-all">
                        <tr>
                            <th><a class="table_hear" style="text-decoration: none; color: #111"  href="StaffList?sort=ID&ASC=${requestScope.ASC}">ID</a></th>
                            <th><a class="table_hear" style="text-decoration: none; color: #111"  href="StaffList?sort=address&ASC=${requestScope.ASC}">Name</a></th>
                            <th>Address</th>
                            <th>Date Of Birth</th>
                            <th>TEL</th>
                            <th>VIP</th>
                            <th>Detail</th>
                        </tr>
                        <c:forEach items="${requestScope.clients}" var="e" varStatus="loop">
                            <tr>
                                <td>${e.id}</td>
                                <td>${e.name}</td>
                                <td>${e.address}</td>
                                <td>${e.dob}</td>
                                <td>${e.tel}</td>
                                <td><c:if test="${e.isVip}">Checked</c:if></td>
                                <td><a href="detail?id=${e.id}">detail</a>  <a href="StaffAddCliPck?id=${e.id}">Add Pack</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>

            </div>
        </div>
    </body>
</html>
