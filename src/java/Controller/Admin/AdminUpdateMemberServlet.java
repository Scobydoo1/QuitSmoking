/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import DAO.AdminDao;
import DAO.MemberDao;
import DTO.Member;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nghia
 */
@WebServlet(name = "AdminUpdateMemberServlet", urlPatterns = {"/AdminUpdateMemberServlet"})
public class AdminUpdateMemberServlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminUpdateMemberServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminUpdateMemberServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String id = request.getParameter("IDMember");
        MemberDao admin = new MemberDao();
        try {
            Member m = admin.getMemberById(id);
            request.setAttribute("member", m);
            request.getRequestDispatcher("/adminUpdateMember.jsp")
                    .forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
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
        request.setCharacterEncoding("UTF-8");
        MemberDao admin = new MemberDao();
        // 1. Lấy dữ liệu từ form
        Member member = new Member();
        member.setIDMember(request.getParameter("IDMember"));
        member.setPassword(request.getParameter("password"));
        member.setMemberName(request.getParameter("memberName"));
        member.setGender(request.getParameter("gender"));
        member.setPhone(request.getParameter("phone"));
        member.setEmail(request.getParameter("email"));
        member.setAddress(request.getParameter("address"));
        member.setDateOfBirth(java.sql.Date.valueOf(request.getParameter("dateOfBirth")));
        member.setJoinDate(java.sql.Date.valueOf(request.getParameter("joinDate")));
        member.setImage(request.getParameter("image"));
        member.setPoint(Integer.parseInt(request.getParameter("point")));
        member.setIDCoach(request.getParameter("IDCoach"));
        member.setSubscription(request.getParameter("subcription"));
        member.setStatus(request.getParameter("status"));

        // 2. Gọi DAO để cập nhật
        try {
            admin.updateMember(member);
        } catch (Exception e) {
            throw new ServletException(e);
        }

        // 3. Chuyển hướng về trang danh sách
        response.sendRedirect("AdminManageMemberServlet");
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
