<%--
  Created by IntelliJ IDEA.
  User: SebastianLos
  Date: 22.04.2017
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="vendor/bootstrap-3.3.7-dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="vendor/bootstrap-3.3.7-dist/css/bootstrap-theme.css"/>
    <link rel="stylesheet" href="vendor/bootstrap-select-1.12.2/js/bootstrap-select.js"/>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker.css" />
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.css" />

    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id"
          content="447589672882-lon09s9eq542cpusfm4njbkjcuhpgif7.apps.googleusercontent.com">
</head>
<body>
    <div>
        <img id="logo" alt="Trip calculator" src="img/logo.png"/>
        <h2>${title}</h2>
    </div>



