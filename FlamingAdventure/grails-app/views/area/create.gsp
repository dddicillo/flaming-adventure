<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'area.css')}" type="text/css">
</head>
	<body>
		<div id="gamearea" name="gamearea" height="<%= rows * 50 %>px" depth="<%= columns * 50 %>px">
		
		<% i = 0 %>
		<% grid.each { row -> %>
			<div id="row">
			
			<% j = 0 %>
			<% row.each { entry -> %>
				<g:if test="${playerX == j && playerY == i}">
					<div id="playertile"><%= "$i $j" %></div>
				</g:if>
				<g:else>
					<g:if test="${entry == '0'}">
						<div id="watertile"><%= "$i $j" %></div>
					</g:if>
					<g:elseif test="${entry == '1'}">
						<div id="grasstile"><%= "$i $j" %></div>
					</g:elseif>					
				</g:else>
				<% j++ %>
			<% } %>

			</div>
			<% i++ %>
		<% } %>

		</div>
		<%= "$rows $columns" %>
	</body>
</html>
