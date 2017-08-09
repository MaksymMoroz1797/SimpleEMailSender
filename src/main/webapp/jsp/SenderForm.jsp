<%--
  Created by IntelliJ IDEA.
  User: Moroz
  Date: 08.08.2017
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="SendMessage" method = "POST">
        <table>
            <tr>
                <td>Reciever adress: </td>
                <td><input type="text" name = "adress"></td>
            </tr>
            <tr>
                <td>Subject</td>
                <td><input type="text" name="subject"></td>
            </tr>
        </table>
        <hr/>
        <textarea type = "text" name="msg" rows = "7" cols = "45">Message text</textarea>
        <input type="submit" value = "Send"/>
        <br/>
    </form>
</body>
</html>
