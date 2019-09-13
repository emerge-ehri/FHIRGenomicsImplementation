<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<body>
    <h2>Upload eMerge report and Parsing successfully!</h2>
    <h2>Below FHIR resources have been created:</h2>
    <table>
        <c:forEach  var="url" items="${resultURLArr}">
            <tr>
                <td>
                    <a href="${url}">${url}</a>
                </td>
            </tr>
        </c:forEach>
    </table><br />
    <h3>
        <a href="index.jsp">Return to HomePage</a>
    </h3>
</body>
</html>