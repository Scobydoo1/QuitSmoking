<%@ page import="DTO.Coach" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Coach coach = (Coach) request.getAttribute("coach");
    System.out.println("editProfileCoach.jsp - Coach: " + coach);

    if (coach == null) {
%>
    <h2 style="color:red; text-align:center;">Coach data is missing. Please try again later.</h2>
<%
        return;
    }    String imagePath = (coach.getImage() != null && !coach.getImage().isEmpty())
                        ? coach.getImage()
                        : "images/avata/nullavata.png";
    String dateOfBirthStr = (coach.getDateOfBirth() != null)
                            ? coach.getDateOfBirth().toString()
                            : "";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Coach Profile</title>
    <link rel="stylesheet" href="css/CoachProfile.css">
</head>
<body>
<div class="container">
    <h3>Edit Profile</h3>
    <form action="UpdateProfileCoach" method="post" enctype="multipart/form-data">
        <div class="profile-card">
            <div class="profile-image-container">
                <img class="profile-image" src="<%= imagePath %>" alt="Coach Image">
            </div>

            <div class="profile-body">
                <div class="detail-row">
                    <div class="detail-label">Name:</div>
                    <div class="detail-value">
                        <input type="text" name="coachName" value="<%= coach.getCoachName() %>">
                    </div>
                </div>

                <div class="detail-row">
                    <div class="detail-label">Password:</div>
                    <div class="detail-value">
                        <input type="text" name="password" value="<%= coach.getPassword() %>">
                    </div>
                </div>

                <div class="detail-row">
                    <div class="detail-label">Gender:</div>
                    <div class="detail-value">
                        <input type="text" name="gender" value="<%= coach.getGender() %>">
                    </div>
                </div>

                <div class="detail-row">
                    <div class="detail-label">Phone:</div>
                    <div class="detail-value">
                        <input type="text" name="phone" value="<%= coach.getPhone() %>">
                    </div>
                </div>

                <div class="detail-row">
                    <div class="detail-label">Email:</div>
                    <div class="detail-value">
                        <input type="email" name="email" value="<%= coach.getEmail() %>">
                    </div>
                </div>

                <div class="detail-row">
                    <div class="detail-label">Address:</div>
                    <div class="detail-value">
                        <input type="text" name="address" value="<%= coach.getAddress() %>">
                    </div>
                </div>

                <div class="detail-row">
                    <div class="detail-label">Date of Birth:</div>
                    <div class="detail-value">
                        <input type="date" name="dateOfBirth" value="<%= dateOfBirthStr %>">
                    </div>
                </div>

                <div class="detail-row">
                    <div class="detail-label">Specialization:</div>
                    <div class="detail-value">
                        <input type="text" name="specialization" value="<%= coach.getSpecialization() %>">
                    </div>
                </div>

                <div class="detail-row">
                    <div class="detail-label">Experience Years:</div>
                    <div class="detail-value">
                        <input type="number" name="experienceYears" value="<%= coach.getExperienceYears() %>">
                    </div>
                </div>

                <div class="detail-row">
                    <div class="detail-label">Avatar:</div>
                    <div class="detail-value">
                        <input type="file" name="avatarFile">
                    </div>
                </div>
            </div>

            <div style="text-align:center;margin-top:20px;">
                <button type="submit" class="btn btn-success">Save Changes</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
