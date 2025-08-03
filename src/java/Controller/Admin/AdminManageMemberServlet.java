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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nghia
 */
@WebServlet(name = "AdminManageMemberServlet", urlPatterns = {"/AdminManageMemberServlet"})
public class AdminManageMemberServlet extends HttpServlet {

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
            out.println("<title>Servlet ManageMemberServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageMemberServlet at " + request.getContextPath() + "</h1>");
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
        MemberDao admin=new MemberDao();
             List<Member> members = admin.getAllMembers();
        request.setAttribute("members", members);
        request.getRequestDispatcher("adminManagerMember.jsp")
               .forward(request, response);
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
        String idMember = request.getParameter("IDMember");
        MemberDao admin=new MemberDao();
        // 2. Gọi DAO để khôi phục
        boolean success = false;
        try {
            success = admin.restoreMember(idMember);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 3. Tùy chọn: lưu thông báo vào session hoặc request
        HttpSession session = request.getSession();
        if (success) {
            session.setAttribute("msg", "Khôi phục thành công member " + idMember);
        } else {
            session.setAttribute("msg", "Không thể khôi phục member " + idMember);
        }

        // 4. Redirect về chính servlet để load lại danh sách (GET sẽ gọi getAllMembers())
        response.sendRedirect(request.getContextPath() + "/AdminManageMemberServlet");
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
