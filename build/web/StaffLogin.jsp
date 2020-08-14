<%-- 
    Document   : StaffLogin
    Created on : Mar 27, 2020, 6:55:21 PM
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
        <div class="mid_container">
            <div class="login" >
                <h1><b>FIT n FAB</b></h1>

                <form  method="POST" action="StaffLogin">
                    <div class="my-container">
                        <table>
                            <tr>
                                <td><div class="node_Container"><label>Username:</label></div></td>
                                <td><input class="w3-input" type="text" name="username" value="long"></td>
                            </tr>
                            <tr>
                                <td><label>Password:</label></td>
                                <td><input class="w3-input" type="password" name="password" value="long"></td>
                            </tr>
                            <tr >
                                <td colspan="2"><div class="node_Container"><input class="w3-button w3-block w3-black" type="submit" value="Login"></div></td>
                            </tr>
                            <tr>
                                <td colspan="2"><c:if test="${requestScope.invalid}"><div class="w3-panel w3-red ">Invalid account!</div></c:if></td>
                            </tr>
                        </table>
                        <br>
                       

                    </div>
                </form>
                
                
            </div>
        </div>
    </body>
</html>
