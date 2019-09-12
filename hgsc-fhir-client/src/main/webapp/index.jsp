<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>BCM HGSC HL7 FHIR</title>

    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="./resources/css/common.css" rel="stylesheet">
</head>

<body>
    <img src="./images/BCM_Logo.png" width="100" height="80"/>
    <img src="./images/HGSC_Logo.png" width="150" height="80"/>
    <img src="./images/FHIR_Logo.png" width="180" height="80" hspace="40"/>

    <h1>Please upload your eMerge report below:</h1>
    <form action="fileupload"  method="post" enctype="multipart/form-data">
        <input class="btn btn-lg btn-primary" type="file" name="filename"><br>
        <input class="btn btn-lg btn-primary" type="submit" name="submit" value="submit">
    </form>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="./resources/js/bootstrap.min.js"></script>
</body>
</html>