<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'area.css')}" type="text/css">
</head>
	<body>
		<div id="mainframe">
			<div id="gamearea" name="gamearea">
		
			<% i = 0 %>
			<% grid.each { row -> %>
				<div id="row" width="<%= columns * 20 %>px">
			
				<% j = 0 %>
				<% row.each { entry -> %>
					<g:if test="${playerX == j && playerY == i}">
						<div id="playertile"><%= "$j $i $entry" %></div>
					</g:if>
					<g:else>
						<g:if test="${entry == '0'}">
						<div id="watertile"><%= "$j $i $entry" %></div>
						</g:if>
						<g:elseif test="${entry == '1'}">
							<div id="grasstile"><%= "$j $i $entry" %></div>
						</g:elseif>
						<g:elseif test="${entry == ' '}">
							<div id="emptytile"></div>
						</g:elseif>					
					</g:else>
					<% j++ %>
				<% } %>

				</div>
				<% i++ %>
			<% } %>

			</div>
		</div>
	</body>
</html>
