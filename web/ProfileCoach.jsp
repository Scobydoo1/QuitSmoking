
<%-- 
    Document   : ProfileCoach
    Created on : Jun 19, 2025, 11:11:02 AM
    Author     : Nghia
--%>

<%@page import="DTO.Coach"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            // Get the coach object from request attributes
            Coach coach = (Coach) request.getAttribute("coach");
        %>
        <link rel="stylesheet" href="css/CoachProfile.css">
    </head>
    <body>
        <div class="container">
             <div class="profile-header">
                    <h3>Coach Profile</h3>
                </div>

            <div class="profile-card">
               
                <!-- Profile Image -->
                <div class="profile-image-container">
                    <img class="profile-image" src="images/avata/nullavata.png" alt="Coach Image">
                </div>

                <!-- Profile Body -->
                <div class="profile-body">
                    <h4 class="card-title text-center">Personal Information</h4>

                    <div class="detail-row">
                        <div class="detail-label">Coach ID:</div>
                        <div class="detail-value"><%= coach.getIDCoach()%></div>
                    </div>

                    <div class="detail-row">
                        <div class="detail-label">Password:</div>
                        <div class="detail-value"><%= coach.getPassword()%></div>
                    </div>

                    <div class="detail-row">
                        <div class="detail-label">Name:</div>
                        <div class="detail-value"><%= coach.getCoachName()%></div>
                    </div>

                    <div class="detail-row">
                        <div class="detail-label">Gender:</div>
                        <div class="detail-value"><%= coach.getGender()%></div>
                    </div>

                    <div class="detail-row">
                        <div class="detail-label">Phone:</div>
                        <div class="detail-value"><%= coach.getPhone()%></div>
                    </div>

                    <div class="detail-row">
                        <div class="detail-label">Email:</div>
                        <div class="detail-value"><%= coach.getEmail()%></div>
                    </div>

                    <div class="detail-row">
                        <div class="detail-label">Address:</div>
                        <div class="detail-value"><%= coach.getAddress()%></div>
                    </div>

                    <div class="detail-row">
                        <div class="detail-label">Date of Birth:</div>
                        <div class="detail-value"><%= coach.getDateOfBirth()%></div>
                    </div>

                    <div class="detail-row">
                        <div class="detail-label">Specialization:</div>
                        <div class="detail-value"><%= coach.getSpecialization()%></div>
                    </div>

                    <div class="detail-row">
                        <div class="detail-label">Experience Years:</div>
                        <div class="detail-value"><%= coach.getExperienceYears()%></div>
                    </div>
                </div>

                <!-- Edit Profile Button -->
                <div style="text-align: center; margin-top: 20px;">
                    <a href="UpdateProfileCoach" class="btn btn-success">Edit Profile</a>
                </div>
            </div>
        </div>
    </body>
</html>

