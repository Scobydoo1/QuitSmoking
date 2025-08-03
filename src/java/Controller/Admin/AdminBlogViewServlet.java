/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import DAO.MemberDao;
import DAO.SystemDao;
import DTO.BlogPost;
import DTO.Member;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nghia
 */
@WebServlet(name = "AdminBlogViewServlet", urlPatterns = {"/AdminBlogViewServlet"})
public class AdminBlogViewServlet extends HttpServlet {

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
            out.println("<title>Servlet AdminBlogViewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminBlogViewServlet at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
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
            request.getRequestDispatcher("AdminBlogManager.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error retrieving blog posts: " + e.getMessage());

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
        String action = request.getParameter("action");  // Lấy hành động từ request

        if ("delete".equals(action)) {  // Nếu hành động là xóa
            String idPost = request.getParameter("blogPostId");
            SystemDao dao = new SystemDao();

            try {
                // Gọi phương thức deletePost trong DAO để xóa bài viết
                boolean isDeleted = dao.deletePost(idPost);

                // Kiểm tra nếu xóa thành công
                if (isDeleted) {
                    request.setAttribute("message", "Blog post deleted successfully.");  // Đặt thông báo thành công
                } else {
                    request.setAttribute("error", "Failed to delete blog post.");  // Đặt thông báo lỗi
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error deleting blog post: " + e.getMessage());
            }
        }

        response.sendRedirect("AdminBlogViewServlet");

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
