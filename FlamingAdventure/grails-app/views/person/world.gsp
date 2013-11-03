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
		</table>
		<g:link controller="person" action="moveDown"><input type="button" value="Down" class="button"/></g:link>
		<g:link controller="person" action="moveUp"><input type="button" value="Up" class="button"/></g:link>
		<g:link controller="person" action="moveRight"><input type="button" value="Right" class="button"/></g:link>
		<g:link controller="person" action="moveLeft"><input type="button" value="Left" class="button"/></g:link>
		<p> PosX = ${posX} </p>
		<p> PosY = ${posY} </p>
		<p> Count = ${count} </p>
		<p> Name = ${name} </p>
	</body>
</html>

