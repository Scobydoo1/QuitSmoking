/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MemberDao;
import DAO.SystemDao;
import DTO.BlogPost;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Nghia
 */
@WebServlet(name = "UpdatePostServlet", urlPatterns = {"/UpdatePostServlet"})
@MultipartConfig
public class UpdatePostServlet extends HttpServlet {

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

        String id = request.getParameter("idPost");
        BlogPost post = null;
        SystemDao mem = new SystemDao();
        try {
            post = mem.getBlogById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("post", post);
        request.getRequestDispatcher("updatePost.jsp")
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
        request.setCharacterEncoding("UTF-8");

        String idPost = request.getParameter("IDPost");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String publishDateS = request.getParameter("publishDate");
        String ID = request.getParameter("idMember");
        String existingImage = request.getParameter("image");
        // 1) Chuyển publishDate an toàn
        Date publishDate = null;
        if (publishDateS != null && !publishDateS.trim().isEmpty()) {
            try {
                LocalDate ld = LocalDate.parse(publishDateS);
                publishDate = Date.valueOf(ld);
            } catch (Exception e) {
                // Nếu sai định dạng thì để null hoặc xử lý tuỳ ý
                publishDate = null;
            }
        }
        Part imagePart = request.getPart("image");
        String imagePath = existingImage;  // Nếu không chọn ảnh mới, giữ lại ảnh cũ

        if (imagePart != null && imagePart.getSize() > 0) {
            // Lưu ảnh mới vào thư mục
            String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("/") + "";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            imagePart.write(uploadPath + fileName);
            imagePath = "" + fileName;  // Cập nhật đường dẫn ảnh
        }
        // 2) Tạo DTO và gán dữ liệu
        BlogPost post = new BlogPost();
        post.setIdPost(idPost);
        post.setTitle(title);
        post.setContent(content);
        post.setPublishDate(publishDate);
        post.setImage(imagePath);
        // post.setPublishDate(Date.valueOf("2025-07-11"));
        // LƯU Ý: không gọi setImage(...) nữa, giữ nguyên trong DAO
        SystemDao dao = new SystemDao();
        // 3) Gọi DAO update
        int update = dao.updatePost(post);
        request.setAttribute("post", post);
        request.getRequestDispatcher("updatePost.jsp").forward(request, response);
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
