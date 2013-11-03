<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'area.css')}" type="text/css">
</head>
	<body>
		<div id="gamearea" name="gamearea">
		
		<% grid.each { row -> %>
			<div id="row">

			<% row.each { entry -> %>
				<g:if test="${entry == '0'}">
					<div id="watertile"></div>
				</g:if>
				<g:elseif test="${entry == '1'}">
					<div id="grasstile"></div>
				</g:elseif>
			<% } %>

			</div>
		<% } %>

		</div>
	</body>
</html>
