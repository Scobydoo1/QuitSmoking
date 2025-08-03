/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.coach;

import DAO.CoachDao;
import DTO.Coach;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Nguyen Tien Dat
 * 
 */
@MultipartConfig  
public class UpdateProfileCoach extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        // Lấy coachId từ session
        HttpSession session = request.getSession(false);
        String coachId = (session != null) ? (String) session.getAttribute("coachId") : null;

        System.out.println("GET - Session coachId: " + coachId);

        if (coachId == null || coachId.trim().isEmpty()) {
            request.setAttribute("error", "Coach ID is missing from session.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        CoachDao dao = new CoachDao();
        Coach coach = dao.getCoachById(coachId);

        if (coach == null) {
            request.setAttribute("error", "Coach not found.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("coach", coach);
            request.getRequestDispatcher("editProfileCoach.jsp").forward(request, response);
        }

    } catch (Exception e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error: " + e.getMessage());
    }
    
       
    

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String coachId = (String) session.getAttribute("coachId");

        System.out.println("POST - Session coachId = " + coachId);
        if (coachId == null || coachId.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Session expired or coach not found.");
            return;
        }

        CoachDao dao = new CoachDao();
        Coach coach = dao.getCoachById(coachId);
        if (coach == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Coach not found.");
            return;
        }

        // Lấy dữ liệu từ form
        String password = request.getParameter("password");
        String name = request.getParameter("coachName");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String specialization = request.getParameter("specialization");
        String dateOfBirthStr = request.getParameter("dateOfBirth");
        String expStr = request.getParameter("experienceYears");

        java.sql.Date dateOfBirth = null;
        if (dateOfBirthStr != null && !dateOfBirthStr.isEmpty()) {
            dateOfBirth = java.sql.Date.valueOf(dateOfBirthStr);
        }

        int experienceYears = 0;
        if (expStr != null && !expStr.isEmpty()) {
            experienceYears = Integer.parseInt(expStr);
        }

        // Avatar (nếu có)
        Part filePart = request.getPart("avatarFile");
        String avatarPath = null;
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = new File(filePart.getSubmittedFileName()).getName();
            String realPath = request.getServletContext().getRealPath("/images/avata");
            File dir = new File(realPath);
            if (!dir.exists()) dir.mkdirs();
            String savePath = realPath + File.separator + fileName;
            filePart.write(savePath);
            avatarPath = "images/avata/" + fileName;
        }

        // Cập nhật dữ liệu
        coach.setPassword(password);
        coach.setCoachName(name);
        coach.setGender(gender);
        coach.setPhone(phone);
        coach.setEmail(email);
        coach.setAddress(address);
        coach.setSpecialization(specialization);
        coach.setDateOfBirth(dateOfBirth);
        coach.setExperienceYears(experienceYears);
        if (avatarPath != null) coach.setImage(avatarPath);

        boolean updated = dao.updateCoachProfile(coach);
        if (updated) {
            request.setAttribute("coach", coach);
            request.getRequestDispatcher("ProfileCoach.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Update failed.");
            request.getRequestDispatcher("editProfileCoach.jsp").forward(request, response);
        }

    } catch (Exception e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal error: " + e.getMessage());
    }
    }
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
