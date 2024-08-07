<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="dto.FoodItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Food Item</title>
<style type="text/css">
body {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: 150vh;
	margin: 0;
	background-image:
		url("https://www.salisbury.sa.gov.au/assets/resized/images/Libraries/179259/Libraries-Salisbury-Community-Markets_W1280_H602.jpg");
	background-repeat: no-repeat;
	background-size: cover;
	font-family: Arial, sans-serif;
}

.navbar {
	width: 100%;
	background-color: #333;
	display: flex;
	justify-content: flex-end;
	align-items: center;
	padding: 10px 20px;
	margin-right: 20px;
	position: fixed;
	top: 0;
}

.navbar-button {
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	background: tomato;
	color: #fff;
	font-size: 1rem;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.navbar-button:hover {
	background-color: #111111;
}

.form-container {
	background-color: #333;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	width: 300px;
	margin-top: 65px;
}

form {
	display: flex;
	flex-direction: column;
}

h2 {
	color: #fff;
	text-align: center;
	margin-bottom: 20px;
}

label {
	color: #fff;
	margin-bottom: 5px;
}

input {
	margin-bottom: 15px;
	padding: 10px;
	border: none;
	border-radius: 4px;
	background-color: #555;
	color: #fff;
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

.form-link {
	color: white;
	text-align: center;
	text-decoration: none;
	margin-top: 10px;
	transition: color 0.3s ease;
}

.form-link:hover {
	background-color: #111111;
}
</style>
</head>
<body>
	<%
	FoodItem item = (FoodItem) request.getAttribute("item");
	%>
	<div class="form-container">
		<h2>Enter Updated Food Details</h2>
		<form action="edit-food-item" method="post"
			enctype="multipart/form-data">
			<label>Id:</label><input type="text" name="id"
				value="<%=item.getId()%>" readonly="readonly"> <label>Name:</label><input
				type="text" name="name" value="<%=item.getName()%>"> <label
				for="cost">Cost:</label><input type="text" name="price"
				value="<%=item.getPrice()%>">
			<%
 if (item.getType().equals("veg")) {
 %>         
            <label>
			<input type="radio" name="type" value="veg" checked="checked">VEG </label>
			<label>
			<input type="radio" name="type" value="non-veg">NON-VEG </label>
			<%
			} else {
			%>
			<label>
			<input type="radio" name="type" value="veg">VEG </label>
			<label>
			<input type="radio" name="type" value="non-veg" checked="checked">NON-VEG </label>
			<%
			}
			%>

			<label for="stock">Stock:</label><input type="number" name="stock"
				value="<%=item.getStock()%>"> <label>Picture:</label><input
				type="file" name="image"> <img
				src="data:image/jpeg;base64,<%=Base64.encodeBase64String(item.getImage())%>"
				alt="<%=item.getName()%>" height="100px" width="100px">
			<br>	
			<button>Update</button>
		</form>
	</div>
</body>
</html>