<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Price Monitor | Monitor List</title>
</head>
<body>
  <g:each in="${monitorList}" var="item">
    <p>${item.name}</p>
  </g:each>
</body>
</html>