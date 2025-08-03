/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MemberDao;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;
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
 * @author Nghia
 */
@MultipartConfig
@WebServlet(name = "PostBlogServlet", urlPatterns = {"/PostBlogServlet"})
public class PostBlogServlet extends HttpServlet {

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
            out.println("<title>Servlet PostBlogServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostBlogServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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

        HttpSession session = request.getSession(false);
        String username = (String) request.getSession().getAttribute("username");
 if (session == null || session.getAttribute("username") == null) {
        // Chưa login → chuyển hướng về trang login
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
        String title = "";
        String content = "";
        String imagePath = "";

        // Lấy dữ liệu từ multipart form
        for (Part part : request.getParts()) {
            switch (part.getName()) {
                case "title":
                    title = readPartAsString(part);
                    break;
                case "content":
                    content = readPartAsString(part);
                    break;
                case "image":
                    if (part.getSize() > 0) {
                        imagePath = extractFileName(part);
                    }
                    break;
            }
        }

        String idPost = "P" + UUID.randomUUID().toString().substring(0, 8);

        LocalDate publishDate = LocalDate.now();

        MemberDao dao = new MemberDao();
        try {
            String idMember = (String) session.getAttribute("id");
            dao.insertBlogPost(idPost, idMember, title, content, imagePath, publishDate);
            request.setAttribute("message", "🟢 Bài viết đã đăng thành công!");
            request.getRequestDispatcher("PostNewBlog.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Lỗi khi đăng bài viết: " + e.getMessage());
        }
    }

    private String readPartAsString(Part part) throws IOException {
        Scanner scanner = new Scanner(part.getInputStream(), "UTF-8").useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    private String extractFileName(Part part) {
        if (part == null) {
            return "";
        }
        String contentDisp = part.getHeader("content-disposition");
        if (contentDisp == null) {
            return "";
        }

        for (String token : contentDisp.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 2, token.length() - 1)
                        .replace("\\", "/"); // tránh lỗi Windows path
            }
        }
        return "";
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
