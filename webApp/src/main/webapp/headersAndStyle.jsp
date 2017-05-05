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
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <meta charset="UTF-8">
    <title>Trip calculator</title>
    <link href="https://fonts.googleapis.com/css?family=Bree+Serif|Gloria+Hallelujah|Indie+Flower|Pacifico|Shadows+Into+Light" rel="stylesheet">
    <link rel="stylesheet" href="vendor/css/bootstrap.css">
    <link rel="stylesheet" href="vendor/css/bootstrap-theme.css">
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="vendor/bootstrap-3.3.7-dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="vendor/bootstrap-3.3.7-dist/css/bootstrap-theme.css"/>
    <link rel="stylesheet" href="vendor/bootstrap-select-1.12.2/js/bootstrap-select.js"/>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker.css" />
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.bundle.js" />

    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id"
          content="447589672882-lon09s9eq542cpusfm4njbkjcuhpgif7.apps.googleusercontent.com">
</head>
<body>
    <div id="header">
        <div id="logo">
            <p>Trip Calculator</p>
        </div>
        <div class="car">
            <img  id="car" src="img/moving-car-mirror.png" alt="car">
        </div>
    </div>



