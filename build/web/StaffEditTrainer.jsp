<%-- 
    Document   : StaffEditTrainer
    Created on : Mar 29, 2020, 8:35:37 PM
    Author     : admin
--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            <div class="top_Container">
                <div class="w3-bar w3-black" style=" padding: 10px">
                    <button class="w3-bar-item w3-button" onclick="location.href = 'StaffList'">List</button>
                    <button class="w3-bar-item w3-button" onclick="location.href = 'StaffAddCustomer'">Add Client</button>
                    <button class="w3-bar-item w3-button" onclick="location.href = 'StaffSearch'">Search For Client</button>
                    <button class="w3-bar-item w3-button" onclick="location.href = 'StaffEditPack'">Detail Packs</button>
                    <button class="w3-bar-item w3-button" onclick="location.href = 'StaffEditTrainer'">Detail Trainers</button>
                </div>
            </div>
        </div>
        <div class="mid_Container">
            <div class="my-container">

                <div  class="node_header" ><b>ADD NEW TRAINER</b></div>
                <div class="table_dis_div" >
                    <form method="POST" action="StaffEditTrainer">
                        <table class="w3-table">
                            <tr>
                                <td><div class="node_Container">Name:</div></td>
                                <td><div class="node_Container"><input class="w3-input w3-border" id="name" name="addName"></div></td>
                            </tr>
                            <tr>
                                <td><div class="node_Container">Spec:</div></td>
                                <td>
                                    <div class="node_Container" style="object-fit: cover">
                                        <c:forEach var="e" varStatus="loop" items="${requestScope.packs}">
                                            <input class="w3-check" type="checkbox" id="${e.id}" name="pack" value="${e.id}" >
                                            <label for="${e.id}">${e.name}</label><br>
                                        </c:forEach>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"><div class="node_Container"><input class="w3-input w3-hover-white w3-black w3-padding-16 w3-border" type="submit" value="Add"></div></td>
                            </tr>
                            <tr>
                                <td colspan="2"><c:if test="${requestScope.warning!= null}"><div style="padding: 20px" class="w3-panel w3-red"><b>WARNING</b><br>${requestScope.warning}</div></c:if></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
                            
            <div class="my-container" >
                <div class="node_header" ><b>ALL AVAILABLE TRAINER</b></div>
                <table class="w3-table-all w3-hoverable" >
                    <tr>
                        <th>ID:</th>
                        <th>Name:</th>
                        <th>Specs</th>

                        <th>Current is avaliable:</th>
                    </tr>
                    <c:forEach var="e" varStatus="loop" items="${requestScope.avlTrainer}" >

                        <tr>
                            <td><div class="node_Container" >${e.id}</div></td>
                            <td><div class="node_Container" >${e.name}</div></td>
                            <td><div class="node_Container" >
                                    <c:forEach var="spcId" varStatus="spcLoop" items="${e.specs}">
                                        <c:forEach var="pack" varStatus="packLoop" items="${requestScope.packs}">
                                            <c:if test="${spcId == pack.id}" >${pack.name}</c:if>
                                        </c:forEach><br>
                                    </c:forEach></div></td>
                            <td><div class="node_Container" ><c:if test="${e.isAval}" >YES</c:if></div></td>

                            </tr>
                    </c:forEach>

                </table>

            </div>

            <div class="my-container" >
                <div class="node_header" ><b>ACTIVE TRAINER</b></div>
                <table class="w3-table-all w3-hoverable" >
                    <tr>
                        <th>Name:</th>
                        <th>Specialized:</th>
                        <th>PT for:</th>
                        <th>With:</th>
                        <th>Current is avaliable:</th>
                    </tr>
                    <c:forEach var="e" varStatus="loop" items="${requestScope.trainers}" >

                        <tr>
                            <td><div class="node_Container" >${e.trainerName}</div></td>
                            <td><div class="node_Container" ><c:forEach var="es" varStatus="loop" items="${e.specs}">${es}<br></c:forEach></div></td>
                            <td><div class="node_Container" ><c:forEach var="ec" varStatus="loop" items="${e.clients}">${ec.name}<br></c:forEach></div></td>
                            <td><div class="node_Container" ><c:forEach var="ep" varStatus="loop" items="${e.packs}">${ep.name}<br></c:forEach></div></td>
                            <td><div class="node_Container" >${e.trainerAvaliable}</div></td>

                        </tr>
                    </c:forEach>

                </table>

            </div>

        </div>
        <script type="text/javascript">

        </script>
    </body>
</html>
