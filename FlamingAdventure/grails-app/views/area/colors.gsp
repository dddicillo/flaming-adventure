<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'colors.css')}" type="text/css">
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
                        <div id="whitetile"><%= "$j $i $entry" %></div>
                        </g:if>
                        <g:elseif test="${entry == '1'}">
                            <div id="redtile"><%= "$j $i $entry" %></div>
                        </g:elseif>
                        <g:elseif test="${entry == '2'}">
                            <div id="greentile"><%= "$j $i $entry" %></div>
                        </g:elseif>
                        <g:elseif test="${entry == '3'}">
                            <div id="bluetile"><%= "$j $i $entry" %></div>
                        </g:elseif> 
                        <g:elseif test="${entry == '4'}">
                            <div id="yellowtile"><%= "$j $i $entry" %></div>
                        </g:elseif> 
                        <g:elseif test="${entry == '5'}">
                            <div id="orangetile"><%= "$j $i $entry" %></div>
                        </g:elseif> 
                        <g:elseif test="${entry == '6'}">
                            <div id="purpletile"><%= "$j $i $entry" %></div>
                        </g:elseif> 
                        <g:elseif test="${entry == '7'}">
                            <div id="pinktile"><%= "$j $i $entry" %></div>
                        </g:elseif> 
                        <g:elseif test="${entry == '8'}">
                            <div id="blacktile"><%= "$j $i $entry" %></div>
                        </g:elseif> 
                        <g:elseif test="${entry == '9'}">
                            <div id="browntile"><%= "$j $i $entry" %></div>
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
