<%-- 
    Document   : editProfile
    Created on : Jun 8, 2025, 12:39:23 PM
    Author     : Nguyen Tien Dat
--%>

<%@page import="DTO.Member"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <%
        Member member = (Member) request.getAttribute("member");
        if (member == null) {
            out.println("<p>No member data found!</p>");
            return;
        }
    %>
    <link href="css/styleeditprofile.css" rel="stylesheet" type="text/css"/>
</head>

<body>
    <style>
        /* Home Button */
        .home-button {
            position: fixed; /* C? ??nh v? trí */
            top: 20px; /* Cách mép trên 20px */
            left: 20px; /* Cách mép trái 20px */
            background-color: #28a745; /* Màu xanh lá ch? ??o */
            color: white; /* Màu ch? */
            padding: 10px 20px; /* Kho?ng cách bên trong nút */
            font-size: 16px; /* Kích th??c ch? */
            font-weight: bold; /* Ch? ??m */
            border: none; /* Không vi?n */
            border-radius: 8px; /* Bo góc */
            text-decoration: none; /* Lo?i b? g?ch chân */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* ?? bóng nh? */
            transition: all 0.3s ease; /* Hi?u ?ng chuy?n ??ng */
            z-index: 999; /* Hi?n th? trên cùng */
        }

        .home-button:hover {
            background-color: #218838; /* Màu ??m h?n khi hover */
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2); /* ?? bóng t?ng khi hover */
            transform: scale(1.05); /* Hi?u ?ng phóng to nh? */
            text-decoration: none;
        }

    </style>
    <a href="homepage.jsp" class="home-button">Home</a>
    <div class="container">
        <h1>Edit Your Profile</h1>
        <form action="UpdateProfileServlet" method="post" enctype="multipart/form-data">

<label>Account</label>
<input type="text" name="idMember" value="<%= member.getIDMember() %>" readonly />

            <label>Full Name</label>
            <input type="text" name="memberName" value="<%= member.getMemberName()%>" />


            <label>Gender:</label>
            <select name="gender" required>
                <option value="" disabled hidden>Select Gender</option>
                <option value="Male" <%= "male".equalsIgnoreCase(member.getGender()) ? "selected" : ""%>>Male</option>
                <option value="Female" <%= "female".equalsIgnoreCase(member.getGender()) ? "selected" : ""%>>Female</option>
                <option value="Other" <%= "other".equalsIgnoreCase(member.getGender()) ? "selected" : ""%>>Other</option>
            </select>

            <label>Email</label>
            <input type="email" name="email" value="<%= member.getEmail()%>" required/>

            <label>Phone</label>
            <input type="text" name="phone" value="<%= member.getPhone()%>"/>

            <label>Address</label>
            <input  type="text" name="address" rows="3" value="<%= member.getAddress()%>" ></input>

            <label>Date of Birth</label>        
            <%
                String formattedDate = "";
                if (member.getDateOfBirth() != null) {
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    formattedDate = sdf.format(member.getDateOfBirth());
                }
            %>
            <input type="date" name="dateOfBirth" value="<%= member.getDateOfBirth()%>" />

            <label>Subscription</label>
            <input type="text" name="subcription" value="<%= member.getSubscription()%>" />


            <div style="display: flex; justify-content: start;">
                <label>Current Avatar</label>
                <img class="avatar-preview" src="<%= (member.getImage() != null) ? member.getImage() : "images/avata/nullavata.png"%>" alt="Profile Image"/>
            </div>



            <label>Change Avatar</label>
            <input type="file" name="avatarFile" accept="image/*"/>

            <div class="button-group">
                <button type="submit">Save Changes</button>
                <a href="DetailMemberProfile" class="cancel-btn">Cancel</a>
            </div>
        </form>
    </div>   


</body>

