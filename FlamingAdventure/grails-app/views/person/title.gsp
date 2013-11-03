<html>
	<body>
		<table border = "1">
		
		<% list.each { column -> %>
			<tr>
			<% column.each { entry -> %>
			

				<td><%= "${entry}" %></td>
			



			<%}%>
			</tr>
		<%}%>
	</body>
</html>

