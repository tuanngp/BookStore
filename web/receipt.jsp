<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment Receipt</title>
<style type="text/css">
	table { border: 0; }
	table td { padding: 5px; }
</style>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
<div align="center">
	<h1>Payment Done. Thank you for purchasing our products</h1>
	<br/>
	<h2>Receipt Details:</h2>
	<table>
		<tr>
			<td><b>Merchant:</b></td>
			<td>Company ABC Ltd.</td>
		</tr>
		<tr>
			<td><b>Payer:</b></td>
			<td>${payer.firstName} ${payer.lastName}</td>		
		</tr>
		<tr>
			<td><b>Description:</b></td>
			<td>${transaction.description}</td>
		</tr>	
		<tr>
			<td><b>Subtotal:</b></td>
			<td>${transaction.amount.details.subtotal} USD</td>
		</tr>
		<tr>
			<td><b>Shipping:</b></td>
			<td>${transaction.amount.details.shipping} USD</td>
		</tr>
		<tr>
			<td><b>Tax:</b></td>
			<td>${transaction.amount.details.tax} USD</td>
		</tr>
		<tr>
			<td><b>Total:</b></td>
			<td>${transaction.amount.total} USD</td>
		</tr>						
	</table>
</div>
</body>
</html>