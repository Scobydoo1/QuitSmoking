/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MemberDao;
import DAO.SystemDao;
import DTO.BlogPost;
import DTO.Member;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes.Name;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 *
 * @author Nghia
 */
@WebServlet(name = "BlogPostServlet", urlPatterns = {"/BlogPostServlet"})
public class BlogPostServlet extends HttpServlet {

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
  request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BlogPostServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogPostServlet at " + request.getContextPath() + "</h1>");
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
  request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
        MemberDao dao = new MemberDao();
        try {
            List<BlogPost> blogPosts = dao.getAllBlogPosts();
            List<Member> members = new ArrayList<>();

            // Duyệt qua các bài viết để lấy thông tin thành viên
            for (BlogPost post : blogPosts) {
                String memberId = post.getIdMember();
                Member member = dao.getMemberById(memberId);
                if (member != null) {
                    members.add(member);
                }
            }

            // Truyền dữ liệu bài viết và thành viên vào request
            request.setAttribute("blogPosts", blogPosts);
            request.setAttribute("members", members);

            // Chuyển hướng đến trang JSP để hiển thị
            request.getRequestDispatcher("blog.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error retrieving blog posts: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
        String keyword = request.getParameter("keyword");
        MemberDao mem = new MemberDao();
        try {
            SystemDao dao = new SystemDao();
            List<BlogPost> blogPosts = dao.searchByTitle(keyword.trim());
            List<Member> member = new ArrayList<>();
            for (BlogPost blog : blogPosts) {
                String idMem = blog.getIdMember();
                member.add(mem.getMemberById(idMem));

            }
            request.setAttribute("blogPosts", blogPosts);
            request.setAttribute("members", member);
            request.setAttribute("keyword", keyword);
            request.getRequestDispatcher("blog.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
