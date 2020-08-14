<%-- 
    Document   : detail
    Created on : Mar 28, 2020, 2:07:46 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/basic.css">
    </head>
    <body>
        <div class="top_Container">
            <div class="top_Container">
                <div class="w3-bar w3-black" style=" padding: 10px">
                    <button class="w3-bar-item w3-button" onclick="location.href = 'StaffList'">List</button>
                    <button class="w3-bar-item w3-button" onclick="location.href = 'StaffAddCustomer'">Add Client</button>
                    <button class="w3-bar-item w3-button" onclick="location.href = 'StaffSearch'">Search For Client</button>
                    <button class="w3-bar-item w3-button" onclick="location.href = 'StaffEditPack'">Detail Packs</button>
                    <button class="w3-bar-item w3-button" onclick="location.href = 'StaffEditTrainer'">Detail Trainers</button>
                </div>
            </div></div>
        <div class="my-container">
            <div class="node_header"><b>${requestScope.c.name}</b></div><br>
            <div class="w3-table">
                <form method="POST" action="detail">
                    <table>
                        <tr>
                            <td><div class="node_Container">Address:</div></td>

                            <td><div class="node_Container"><input type="text" value="${requestScope.c.address}" name="address"></div>

                            </td> 
                        </tr>
                        <tr>
                            <td><div class="node_Container">Date Of Birth:</div></td>
                            <td>  <div class="node_Container">${requestScope.c.dob}"  </div></td>


                        </tr>
                        <tr>
                       <td> <div class="node_Container">Telephone:</div></td>
                        <td><div class="node_Container"><input type="text" value="${requestScope.c.tel}" name="tel"></div></td>


                        </tr>



                        <c:forEach items="${requestScope.c.packs}" var="e" varStatus="loop">

                            <tr>
                            <td><div class="node_Container"><c:if test="${loop.index eq 0}">Pack:</c:if></div>

                                </td>
                                <td>
                                    <div class="node_Container"><div style="float: left">${e.name}</div><div  style="float: right"><a  href="detail?packId=${e.id}">Delete</a></div> </div>

                            </td>
                            </tr>
                        </c:forEach>

                        <c:forEach items="${requestScope.c.trainers}" var="e" varStatus="loop">
                            <tr>
                                <td><div class="node_Container"><c:if test="${loop.index eq 0}">Trainer:</c:if> </div>
                                    </td>
                                    <td>
                                        <div class="node_Container">${e.name}</div>

                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td><div class="node_Container">VIP:</div></td>
                            <td><div class="node_Container"><input type="checkbox" <c:if test="${requestScope.c.isVip}">checked</c:if> name="vip"></div> </td> 
                        </tr>
                    </table>
                    <div class="table_dis_tools" > 
                        <input type="submit" value="Send"> 
                        <input type="button" value="Back" onclick="location.href = 'StaffList';">
                    </div>
                </form>

            </div>


        </div>
    </body>
</html>
