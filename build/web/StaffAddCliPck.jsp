<%-- 
    Document   : StaffAddCliPck
    Created on : Mar 29, 2020, 1:19:41 AM
    Author     : admin
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/basic.css">
        <%
            ArrayList<Pack> aPacks
                    = (ArrayList<Pack>) request.getAttribute("packs");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="top_Container" >
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
            
            <div class="my-container" >
                <div class="node_header"><b>CLIENT INFO</b></div>
                <table class="w3-table w3-bordered" >
                    <tr>
                        <td><div class="node_Container">ID</div></td>
                        <td><div class="node_Container">${requestScope.client.id}</div></td>
                    </tr>
                    <tr>
                        <td><div class="node_Container">Name</div></td>
                        <td><div class="node_Container">${requestScope.client.name}</div></td>
                    </tr>
                    <tr>
                        <td><div class="node_Container">Address</div></td>
                        <td><div class="node_Container">${requestScope.client.address}</div></td>
                    </tr>
                    <tr>
                        <td><div class="node_Container">Date of birth</div></td>
                        <td><div class="node_Container">${requestScope.client.dob}</div></td>
                    </tr>
                    <tr>
                        <td><div class="node_Container">Create Date</div></td>
                        <td><div class="node_Container">${requestScope.client.createDate}</div></td>
                    </tr>
                    <tr>
                        <td><div class="node_Container">Tel</div></td>
                        <td><div class="node_Container">${requestScope.client.tel}</div></td>
                    </tr>
                    <c:forEach var="e" varStatus="loop" items="${requestScope.client.packs}">
                        <tr>
                            <td><c:if test="${loop.index eq 0}"><div class="node_Container">Current Pack</div></c:if></td>
                            <td><div class="node_Container">${e.name}</div></td>
                        </tr>
                    </c:forEach>
                    <c:forEach var="e" varStatus="loop" items="${requestScope.client.trainers}">
                        <tr>
                            <td><c:if test="${loop.index eq 0}"><div class="node_Container">Current Trainer:</div></c:if></td>
                            <td><div class="node_Container">${e.name}</div></td>
                        </tr>
                    </c:forEach>

                    <tr>
                        <td><div class="node_Container">VIP</div></td>
                        <td> <div class="node_Container"><c:if test="${requestScope.client.isVip}">Checked</c:if></div></td>
                        </tr>
                    </table>
                </div>

                <div class="my-container">
                    <div  class="node_header"><b>ADD NEW PACK</b></div>
                    <form method="POST" action="StaffAddCliPck?mode=check&id=${requestScope.client.id}">
                    <table class="w3-table-all" >
                        <tr>
                            <td>
                                <div class="node_Container">Avaliable Pack</div>
                            </td>
                            <td>
                                <div class="node_Container">

                                    <select class="drop_select" onchange="chossePack()" id="pack" name="pack">
                                        <c:forEach var="e" varStatus="loop" items="${requestScope.packs}">
                                            <option class="dorp_option" value="${e.id}">${e.name}</option>
                                        </c:forEach>
                                    </select>



                                </div>
                            </td>
                        </tr>
                        <tr> 
                            <td>
                                <div class="node_Container">For:</div>
                            </td>
                            <td>
                                <div id="packPrice" class="node_Container" >
                                    <select class="drop_select" id="" name="time">
                                        <c:forEach var="e" varStatus="loop" begin="1" end="12" >
                                            <option class="dorp_option" value="${loop.index}">${loop.index}</option>
                                        </c:forEach>
                                    </select>Month(s)</div>
                            </td>
                        </tr>
                        <tr> 
                            <td>
                                <div class="node_Container">For:</div>
                            </td>
                            <td>
                                <div id="trianers" class="node_Container" >
                                    <select class="drop_select" id="trainer" name="trainer">
                                        <c:forEach var="e" varStatus="tier1" items="${requestScope.trainers}" >
                                            <c:forEach var="es" varStatus="tier2" items="${e.specs}" >
                                                <c:if test="${es == requestScope.packs[0].id}" >
                                                    <option class="dorp_option" value="${e.id}">${e.name}</option>
                                                </c:if> 
                                            </c:forEach>

                                        </c:forEach>
                                    </select> Trainers</div>
                            </td>
                        </tr>
                        <tr> 
                            <td>
                                <input class="w3-input w3-hover-white w3-black w3-padding-16 w3-border" type="submit" value="Check" >
                            </td>
                            <td>
                                <c:if test="${requestScope.warning != null}"> <div class="w3-panel w3-red"><h3>WARNING</h3><br>${requestScope.warning}</div></c:if>
                            </td>
                            
                        </tr>
                        <script>
                            function chossePack() {
                                var trainers = [
                            <c:forEach var="e" varStatus="tier1" items="${requestScope.trainers}" >
                                <c:forEach var="ep" varStatus="tier2" items="${e.specs}">['${ep}','${e.id}', '${e.name}']<c:if test="${fn:length(e.specs) != tier2.count}">,</c:if></c:forEach>

                                <c:if test="${fn:length(requestScope.trainers) != tier1.count}">,</c:if>
                            </c:forEach>
                                ];
                                var select = document.getElementById("trainer");
                                var pack = document.getElementById("pack");
                                var packID = pack.options[pack.selectedIndex].value;
                                while (select.lastChild) {
                                    select.removeChild(select.lastChild);
                                }
                                for (var i = 0, max = trainers.length; i < max; i++) {
                                    var specId = trainers[i][0];
                                    if (trainers[i][0] === packID ) {
                                        var op = document.createElement("option");
                                        op.setAttribute("value", trainers[i][1]);
                                        op.setAttribute("class", "dorp_option");
                                        op.innerHTML = trainers[i][2];
                                        select.appendChild(op);
                                    }

                                }
                            }
                        </script>
                    </table>
                </form>
            </div>

        </div>
                    <div class="node-container"></div>
    </body>
</html>
