<%-- 
    Document   : StaffCheckOut
    Created on : Mar 29, 2020, 5:27:42 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/basic.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="top_Container" >
            
                <div class="w3-bar w3-black" style=" padding: 10px">
                    <button class="w3-bar-item w3-button" onclick="location.href = 'StaffList'">List</button>
                    <button class="w3-bar-item w3-button" onclick="location.href = 'StaffAddCustomer'">Add Client</button>
                    <button class="w3-bar-item w3-button" onclick="location.href = 'StaffSearch'">Search For Client</button>
                    <button class="w3-bar-item w3-button" onclick="location.href = 'StaffEditPack'">Detail Packs</button>
                    <button class="w3-bar-item w3-button" onclick="location.href = 'StaffEditTrainer'">Detail Trainers</button>
                </div>
          
        </div>
        <div class="mid_Container">
            
            <div class="my-container">
                <div class="node_header"><b>CHECK OUT</b></div>
                <form method="POST" action ="StaffAddCliPck?mode=add&id=${requestScope.id}&packName=${requestScope.packName}&time=${requestScope.time}&tid=${requestScope.tid}&trainer=${requestScope.tid}">
                    <table class="w3-table" >
                        <tr>
                            <td class="node_Container" >Client name:</td>
                            <td><div class="node_Container">${requestScope.client.name}</div></td> 
                        </tr>
                        <tr>
                            <td class="node_Container" >Client Date of Birth</td>
                            <td><div class="node_Container">${requestScope.client.dob}</div></td>
                        </tr>
                        <tr>
                            <td class="node_Container" >Client Tel:</td>
                            <td> <div class="node_Container">${requestScope.client.tel}</div></td>
                        </tr>

                        <tr>
                            <td class="node_Container" >Pack to Add: </td>
                            <td><div class="node_Container">${requestScope.packName}</div></td>
                        </tr>
                        <tr>
                            <td class="node_Container" >PT: </td>
                            <td><div class="node_Container">${requestScope.tname}</div></td>
                        </tr>
                        <tr>
                            <td class="node_Container" >Time: </td>
                            <td><div class="node_Container" name="time"  >${requestScope.time}</div></td>
                        </tr>
                        <tr>
                            <td class="node_Container" >Total: </td>
                            <td><div class="node_Container">${requestScope.price}.000</div></td>
                        </tr>
                        <tr>
                            <td  ><div class="node_Container"><input class="w3-input w3-hover-white w3-black w3-padding-16 w3-border" type="button" value="Back" onclick="location.href = 'StaffAddCliPck?id=${requestScope.id}';"></div></td>
                            <td><div class="node_Container"><input  class="w3-input w3-hover-white w3-black w3-padding-16 w3-border"type="submit" name="OK" value="OK"></div></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
