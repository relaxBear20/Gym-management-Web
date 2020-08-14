<%-- 
    Document   : StaffSearch
    Created on : Mar 28, 2020, 7:34:09 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/basic.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="top_Container">
            <div class="w3-bar w3-black" style=" padding: 10px">
                <button class="w3-bar-item w3-button" onclick="location.href = 'StaffList'">List</button>
                <button class="w3-bar-item w3-button" onclick="location.href = 'StaffAddCustomer'">Add Client</button>
                <button class="w3-bar-item w3-button" onclick="location.href = 'StaffSearch'">Search For Client</button>
                <button class="w3-bar-item w3-button" onclick="location.href = 'StaffEditPack'">Detail Packs</button>
                <button class="w3-bar-item w3-button" onclick="location.href = 'StaffEditTrainer'">Detail Trainers</button>
            </div>
        </div>
        <div class="my-container">
            <div class="node-container">
                <div class="node_header" ><b>SEARCH CLIENT</b></div>
                <form method="POST" action="StaffSearch">
                    <table>
                        <tr>

                            <td><div class="node_Container">Customer ID:</div></td>
                            <td><div class="node_Container"><input type="text" class="w3-input w3-padding-16 w3-border" name="id" value="${requestScope.id}"  ></div></td>
                        </tr>
                        <tr>

                            <td><div class="node_Container">Name:</div></td>
                            <td><div class="node_Container"><input type="text" class="w3-input w3-padding-16 w3-border" name="name" value="${requestScope.name}"></div></td>
                        </tr>
                        <tr>

                            <td><div class="node_Container">Phone Number:</div></td>
                            <td><div class="node_Container"><input type="text" class="w3-input w3-padding-16 w3-border" name="tel" value="${requestScope.tel}"></td>
                                    </tr>

                                    <tr>

                                        <td>
                                            <div class="search_tool">
                                                <input type="button"style="width: 150px"  class="w3-input w3-hover-white w3-black w3-padding-16 w3-border"  value="Back" onclick="location.href = 'StaffList';">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="search_tool">
                                                <input type="submit" class="w3-input w3-hover-white w3-black w3-padding-16 w3-border" value="Search">

                                            </div>

                                        </td>
                                    </tr>
                    </table>

                </form> 
            </div>
        </div>
        <c:if test="${requestScope.clients!= null}" >
            <div class="my-container">    
                <table class="w3-table-all w3-hoverable">
                    <c:forEach items="${requestScope.clients}" var="e" varStatus="loop">
                        <tr>
                            <td class="w3-left-align">${e.id}</td>
                            <td class="w3-left-align">${e.name}</td>
                            <td class="w3-left-align">${e.address}</td>
                            <td class="w3-left-align">${e.dob}</td>
                            <td class="w3-left-align">${e.tel}</td>
                            <td class="w3-left-align"><c:if test="${e.isVip}">Checked</c:if></td>
                            <td class="w3-left-align"><a href="detail?id=${e.id}">detail</a>  <a href="StaffAddCliPck?id=${e.id}">Add Pack</a></td>
                        </tr>
                    </c:forEach>
                </table>

            </div>
        </c:if>



    </body>
</html>
