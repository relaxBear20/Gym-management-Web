<%-- 
    Document   : StaffEditPack
    Created on : Mar 28, 2020, 8:50:57 PM
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
            </div></div>
        <div class="mid_Container">
            <div class="my-container">
                <div class="node_header"><b>ALL AVALIABLE PACKS</b></div>
                <c:if test="${requestScope.success != null} "><div class="w3-panel w3-red" ><h3>SUCCESS</h3>${requestScope.success}</div></c:if>
                <c:if test="${requestScope.warning != null} "><div class="w3-panel w3-red" ><h3>WARNING</h3>${requestScope.warning}</div></c:if>
                
                <div class="table_dis_div">
                    <table class="w3-table-all w3-hoverable">
                        <c:forEach items="${requestScope.packs}" var="e" varStatus="loop">
                            <tr>
                                <td>${e.id}</td>
                                <td>${e.name}</td>
                                <td>${e.price}.000</td>
                                <td>${e.sale}%</td>
                                <td><form method="POST" action="StaffEditPack?id=${e.id}&addName=delete" ><input class="w3-button w3-white w3-border" type="submit" value="Delete"></form></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>              
            </div>

            <div class="my-container">
                <div class="node_header"><b>ADD A NEW PACK</b></div>
                <div class="node_add_pack">
                    <form method="POST" action="StaffEditPack">
                        <table class="w3-table">
                            <tr>
                                <td style="width: 200px"><div class="node_Container">Pack Name:</div></td>
                                <td><div class="node_Container"><input class="w3-input w3-border" type="text" name="addName"></div></td>
                            </tr>
                            <tr>
                                <td><div class="node_Container">Pack Price:</div></td>
                                <td><div class="node_Container"><input type="text " class="w3-input w3-border" name="price"></div> </td>
                            </tr>
                            <tr>
                                <td><div class="node_Container">Pack Sale:</div></td>
                                <td><div class="node_Container"><input type="text" class="w3-input w3-border" name="sale"></div></td>
                            </tr>
                            <tr>
                                
                                <td colspan="2">
                                    <div class="node_Container"><input class="w3-input w3-hover-white w3-black w3-padding-16 w3-border" type="submit" value="Add"></div>
                                </td>
                            </tr>
                        </table>
                         
                        
                        
                        
                    </form>
                    <div class="node_warning">${requestScope.warningAdd}</div>
                </div>
            </div>
    </body>
</html>
