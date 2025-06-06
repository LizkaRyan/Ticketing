<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="mg.itu.ticketing.dto.Parametre" %>
<%
    Parametre parametre = (Parametre) request.getAttribute("parametre");
    List<String> messages = (List<String>) request.getAttribute("error.messages");
    if (messages == null) {
        messages = new ArrayList<>();
    }
%>
<form action="/Ticketing/parametre" method="post">
    <div class="login-container">
        <div class="login-box">
            <h1>Insertion Paramètre</h1>
            <% for (String message : messages) { %>
            <p style="color: red"><%=message%>
            </p>
            <%}%>
            <div class="input-group">
                <label>Dernière réservation heure avant vol</label>
                <input
                        name="parametre.reservation"
                        value="<%= parametre.getReservation() %>"
                        type="number"
                    <% if(request.getAttribute("reservation")!=null) {%>
                        style="border-color: red"
                    <% } %>
                >
            </div>
            <div class="input-group">
                <label>Annulation réservation heure avant vol</label>
                <input
                        name="parametre.annulation"
                        value="<%= parametre.getAnnulation() %>"
                        type="number"
                    <% if(request.getAttribute("annulation")!=null) {%>
                        style="border-color: red"
                    <% } %>
                >
            </div>
            <button type="submit" class="login-button">Modifier</button>
        </div>
    </div>
</form>