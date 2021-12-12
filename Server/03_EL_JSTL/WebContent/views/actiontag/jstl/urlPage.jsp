<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>urlPage</title>
</head>
<body>
	<%-- * get 방식으로 넘어왔으므로 인코딩 정보 필요 없음 --%>
	
	상품명  :  ${ param.pName } <br>
	개수   :  ${ param.pCount } <br>
	옵션   :  ${ paramValues.option[0] } <br>
	옵션   :  ${ paramValues.option[1] } <br>

</body>
</html>