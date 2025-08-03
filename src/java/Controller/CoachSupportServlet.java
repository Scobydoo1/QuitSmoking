/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MemberDao;
import DAO.SupportDAO;
import DTO.Member;
import DTO.Support;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nguyen Tien Dat
 */
public class CoachSupportServlet extends HttpServlet {

    
     private MemberDao memberDao = new MemberDao();
    private SupportDAO supportDao = new SupportDAO();

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
       HttpSession session = request.getSession();
        String coachId = (String) session.getAttribute("coachId");

        if (coachId == null) {
            response.sendRedirect("login.jsp"); // hoặc trang khác tùy bạn
            return;
        }

        // Lấy danh sách thành viên
        List<Member> memberList = memberDao.getMembersForCoach(coachId);
        if (memberList == null) {
            memberList = new ArrayList<>();
        }
        request.setAttribute("memberList", memberList);

        // Lấy tin nhắn với thành viên đang chọn
        String memberId = request.getParameter("memberId");
        if (memberId != null && !memberId.isEmpty()) {
            List<Support> chatMessages = supportDao.getChatBetween(memberId, coachId);
            request.setAttribute("chatMessages", chatMessages);
            request.setAttribute("selectedMemberId", memberId);
        }

        request.getRequestDispatcher("supportCoach.jsp").forward(request, response);
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
         request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String coachId = (String) session.getAttribute("coachId");

        if (coachId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String memberId = request.getParameter("memberId");
        String content = request.getParameter("message");

        if (memberId != null && content != null && !content.trim().isEmpty()) {
            Support message = new Support();
            message.setIdMember(memberId);
            message.setIdCoach(coachId);
            message.setAuthorSend("coach");
            message.setContent(content);
            message.setFeedbackDate(new java.sql.Date(System.currentTimeMillis()));

            supportDao.insertMessage(message);
        }

        response.sendRedirect("CoachSupportServlet?memberId=" + memberId);

        // Quay lại trang với memberId vừa chọn
        
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
