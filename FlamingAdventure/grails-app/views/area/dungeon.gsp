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
          <g:elseif test="${monsterX == j && monsterY == i}">
		    <div id="monstertile"><%= "$j $i $entry" %></div>
			</g:elseif>
          <g:else>
            <g:if test="${entry == '3'}">
            	<div id="doortile"><%= "$j $i $entry" %></div>
            </g:if>
            <g:elseif test="${entry == '0'}">
            <div id="watertile"><%= "$j $i $entry" %></div>
            </g:elseif>
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
		<g:link controller="area" action="moveDown"><input type="button" value="Down" class="button"/></g:link>
		<g:link controller="area" action="moveUp"><input type="button" value="Up" class="button"/></g:link>
		<g:link controller="area" action="moveRight"><input type="button" value="Right" class="button"/></g:link>
		<g:link controller="area" action="moveLeft"><input type="button" value="Left" class="button"/></g:link>
      </div>
    </div>
  </body>
</html>