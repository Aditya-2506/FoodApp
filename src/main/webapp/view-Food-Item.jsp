<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="dto.FoodItem"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Food Item</title>
<style type="text/css">
table {
	width: 100%;
	border-collapse: collapse;
	margin: 20px 0;
	font-size: 1em;
	font-family: Arial, sans-serif;
	min-width: 400px;
	box-shadow: 0 5px 10px rgba(0, 0, 0, 0.15);
}

th, td {
	padding: 12px 15px;
	text-align: center;
}

th {
	background-color: #333;
	color: #ffffff;
	text-transform: uppercase;
}

tr {
	border-bottom: 1px solid #dddddd;
}

tr:nth-of-type(even) {
	background-color: #f3f3f3;
}

tr:last-of-type {
	border-bottom: 2px solid #333;
}

tr:hover {
	background-color: #f1f1f1;
}

td img {
	width: 100px;
	height: auto;
}

button {
    background: tomato;
	color: #fff;
	border: none;
	padding: 10px;
	border-radius: 4px;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

button:hover {
	background-color: #111111;
}
</style>
</head>
<body>
	<%
	List<FoodItem> foodItems = (List<FoodItem>) request.getAttribute("foodItems");
	%>
	<div align="center">
		<h1>View Food Items</h1>
		<table border="1">
			<tr>
				<th>Name</th>
				<th>Stock</th>
				<th>Image</th>
				<th>Price</th>
				<th>Hotel Name</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>

			<%
			for (FoodItem item : foodItems) {
			%>
			<tr>
				<td><%=item.getName()%></td>
				<td><%=item.getStock()%></td>
				<td><img
					src="data:image/jpeg;base64,<%=Base64.encodeBase64String(item.getImage())%>"
					alt="<%=item.getName()%>" height="100px" width="100px"></td>
				<td><%=item.getPrice()%></td>
				<td><%=item.getHotel().getName()%></td>
				<td><a href="edit-food-item?id=<%=item.getId()%>"><button>Edit</button></a></td>
				<td><a href="delete-food-item?id=<%=item.getId()%>"><button>Delete</button></a></td>
			</tr>
			<%
			}
			%>

		</table>

		<br> <a href="hotel-Home.html"><button>Back</button></a>
	</div>
</body>
</html>