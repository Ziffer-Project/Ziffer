<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 18/05/15
  Time: 10:15 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <title>User Login</title>
</head>
<body>
<div class="body">
    <g:form controller = 'login' action="doLogin" method="post">
        <div class="dialog">
            <p>Enter your login details below:</p>
            <table  class="userForm">
                <tr class='prop'>
                    <td valign='top' style='text-align:left;' width='20%'>
                        <label>Email:</label>
                    </td>
                    <td valign='top' style='text-align:left;' width='80%'>
                        <input id="username" type='text' name='username' value='${user?.username}' />
                    </td>
                </tr>
                <tr class='prop'>
                    <td valign='top' style='text-align:left;' width='20%'>
                        <label for='password'>Password:</label>
                    </td>
                    <td valign='top' style='text-align:left;' width='80%'>
                        <input id="password" type='password' name='password'
                               value='${user?.password}' />
                    </td>
                </tr>
            </table>
        </div>
        <div class="buttons">
            <span class="formButton">
                <input type="submit" value="Login"> </input>
            </span>
        </div>
    </g:form>
</div>
</body>
</html>
