<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'storage.css')}" type="text/css">
</head>
    <body>
        <div id="sidepanel">
            <p>X-Coordinate: <input type="textbox" class="textbox"/></p>
            <p>Y-Coordinate: <input type="textbox" class="textbox"/></p>
            <p><g:link controller="area" action="select">
                <input type="button" value="Submit" class="button"/>
            </g:link></p>
            <p>Rows: <%= "$rows" %></p>
            <p>Columns: <%= "$columns" %></p>
        </div>
        <div id="mainframe">
            <div id="gamearea" name="gamearea">
        
            <% i = 0 %>
            <% grid.each { row -> %>
                <div id="row" width="<%= columns * 20 %>px">
            
                <% j = 0 %>
                <% row.each { entry -> %>
                    <g:if test="${playerX == j && playerY == i}">
                        <div id="selectedtile"><%= "$j $i $entry" %></div>
                    </g:if>
                    <g:else>
                        <div id="datatile"><%= "$entry" %></div> 
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
