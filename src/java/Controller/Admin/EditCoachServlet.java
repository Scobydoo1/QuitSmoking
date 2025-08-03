/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import DAO.CoachDao;
import DTO.Coach;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nguyen Tien Dat
 */
public class EditCoachServlet extends HttpServlet {

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

        String id = request.getParameter("id");

        try {
            CoachDao dao = new CoachDao();
            Coach coach = dao.getCoachById(id);
            request.setAttribute("coach", coach);
            request.getRequestDispatcher("adminEditCoach.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Lỗi khi lấy thông tin Coach.");    
        }
    }

    // Xử lý lưu thông tin sau khi chỉnh sửa
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=UTF-8");
        Coach coach = new Coach();
        coach.setIDCoach(request.getParameter("IDCoach"));
        coach.setPassword(request.getParameter("password"));
        coach.setCoachName(request.getParameter("coachName"));
        coach.setGender(request.getParameter("gender"));
        coach.setPhone(request.getParameter("phone"));
        coach.setEmail(request.getParameter("email"));
        coach.setAddress(request.getParameter("address"));

// Parse date
        String dobStr = request.getParameter("dateOfBirth");
        if (dobStr != null && !dobStr.isEmpty()) {
            coach.setDateOfBirth(Date.valueOf(dobStr));
        }

// Optional
        coach.setSpecialization(request.getParameter("specialization"));
        coach.setExperienceYears(Integer.parseInt(request.getParameter("experienceYears")));
        coach.setImage(request.getParameter("image"));

        CoachDao dao = new CoachDao();
        boolean success = false;
        try {
            success = dao.updateCoachProfile(coach);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditCoachServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (success) {
            response.sendRedirect("ManageCoachServlet");
        } else {
            request.setAttribute("error", "Cập nhật thất bại!");
            request.getRequestDispatcher("adminEditCoach.jsp").forward(request, response);
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
