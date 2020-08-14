<%-- 
    Document   : StaffAddCustomer
    Created on : Mar 30, 2020, 8:32:46 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/basic.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="top_Container"  >
            <div class="w3-bar w3-black" style=" padding: 10px">
                <button class="w3-bar-item w3-button" onclick="location.href = 'StaffList'">List</button>
                <button class="w3-bar-item w3-button" onclick="location.href = 'StaffAddCustomer'">Add Client</button>
                <button class="w3-bar-item w3-button" onclick="location.href = 'StaffSearch'">Search For Client</button>
                <button class="w3-bar-item w3-button" onclick="location.href = 'StaffEditPack'">Detail Packs</button>
                <button class="w3-bar-item w3-button" onclick="location.href = 'StaffEditTrainer'">Detail Trainers</button>
            </div>
        </div>
        <div class="mid_Container">

            <div class="my-container" >

                <div class="node_header"  ><b>ADD CLIENT</b></div>

                <form method="POST" action="StaffAddCustomer">
                    <table class="table_dis">
                        <tr>
                            <td>
                                <div class="node_Container">First name*</div>
                                <div class="node_Container"><input style=" padding: 20px;width: 250px" type="text" placeholder="Long" class="w3-input w3-border"  name="fname" ></div>
                            </td> 
                            <td>
                                <div class="node_Container">Last name*</div>
                                <div class="node_Container"><input style=" padding: 20px;" type="text" placeholder="Lai" class="w3-input w3-border" name="lname" ></div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div class="node_Container">Date of birth*</div>
                                <input class="w3-input w3-padding-16 w3-border" style="padding: 20px;" type="date" v="Date and time" required="" name="dob" value="1999-11-16">
                            </td> 
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div class="node_Container">Address*</div>
                                <div class="node_Container"><input style="padding: 20px;" type="text" class="w3-input w3-border" placeholder="14 Hang Ma" name="address" ></div>
                            </td> 
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div class="node_Container">Phone number*</div>
                                <div class="node_Container"><input style="padding: 20px;" placeholder="Eg 0900000000" class="w3-input w3-border" type="text" name="tel" ></div>
                            </td> 
                        </tr>
                        

                        <tr>

                            <td colspan="2">
                                <div class="node_Container"></div>
                                <div class="node_Container"><input style="padding: 20px" class="w3-button w3-block w3-black" type="submit" value="Submit"  ></div>
                            </td> 
                        </tr>
                        <tr>
                            <td colspan="2"> 
                                <div class="node_Container">
                                    <c:if test="${requestScope.warning != null}"> 
                                        <div class="w3-panel w3-red w3-round" ><h6>${requestScope.warning}</h6></div>
                                            </c:if> 
                                            <c:if test="${requestScope.success != null}"> 
                                        <div class="w3-panel w3-green w3-round" ><h6>Add Success</h6></div>
                                    </c:if> 
                                </div>
                                
                            </td>

                        </tr>
                    </table>

                </form>


            </div>
        </div>
    </body>
</html>
