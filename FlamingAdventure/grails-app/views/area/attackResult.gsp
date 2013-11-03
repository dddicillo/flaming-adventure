<html>
	<body>
		<% if (result) { %>
			<p>You Won!!!</p>
		<%} else {%>
			<p>... you lost</p>
		<%}%>
		<g:link controller="area" action="dungeon"><input type="button" value="Back To World" class="button"/></g:link>
	</body>
</html>
