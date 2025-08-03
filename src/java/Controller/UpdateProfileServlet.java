

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CoachDao;
import DAO.MemberDao;
import DAO.NotificationDao;
import DTO.Coach;
import DTO.Member;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import javax.servlet.http.Part;

/**
 *
 * @author Nguyen Tien Dat
 */

@WebServlet(name = "UpdateProfileServlet", urlPatterns = {"/UpdateProfileServlet"})

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)    // 50MB

public class UpdateProfileServlet extends HttpServlet {

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
        String id = request.getParameter("idMember");

        if (id == null || id.trim().isEmpty()) {
            response.getWriter().println("No member ID provided.");
            return;
        }

        Member member = null;
        MemberDao a = new MemberDao();
        member = a.getMemberById(id);
        if (member != null) {
            request.setAttribute("member", member);
            request.getRequestDispatcher("editProfile.jsp").forward(request, response);
        } else {
            response.getWriter().println("No member found with ID: " + id);
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
        NotificationDao noti=new NotificationDao();
        try {
            request.setCharacterEncoding("UTF-8");
            String AVATAR_UPLOAD_DIR = "images/avata";
            
            String idMember = request.getParameter("idMember");
            CoachDao coachDao = new CoachDao();
            MemberDao memdao = new MemberDao();
            Member member = memdao.getMemberById(idMember);
            
            String memberName = request.getParameter("memberName");
            String gender = request.getParameter("gender");
            
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            //Date dateOfBirth = request.getParameter("dateOfBirth");
            String subscription = request.getParameter("subcription");
            
            String dateOfBirthStr = request.getParameter("dateOfBirth");
            java.sql.Date dateOfBirth = null;
            
            if (dateOfBirthStr != null && !dateOfBirthStr.isEmpty()) {
                try {
                    // parse sang java.util.Date trước
                    java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirthStr);
                    // chuyển sang java.sql.Date
                    dateOfBirth = new java.sql.Date(utilDate.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            
            member.setDateOfBirth(dateOfBirth);
            // Lấy file upload
            Part filePart = request.getPart("avatarFile");
            
            String avatarPath = null; // Đường dẫn avatar mới
            
            if (filePart != null && filePart.getSize() > 0) {
                // Lấy tên file
                String fullFileName = filePart.getSubmittedFileName();
                String fileName = fullFileName.substring(fullFileName.lastIndexOf("\\") + 1);
                
                // Đường dẫn lưu file trên server
                String appPath = request.getServletContext().getRealPath("");
                String uploadDir = appPath + File.separator + AVATAR_UPLOAD_DIR;
                
                // Tạo thư mục nếu chưa tồn tại
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }
                
                // Đường dẫn file lưu
                String filePath = uploadDir + File.separator + fileName;
                
                // Lưu file
                filePart.write(filePath);
                
                // Đường dẫn avatar dùng để lưu trong database (dùng đường dẫn tương đối để truy cập từ web)
                avatarPath = AVATAR_UPLOAD_DIR + "/" + fileName;
            }
            
            String idCoach=coachDao.getCoachIdFromMember(idMember);
            Coach coachDTO= coachDao.getCoachById(idCoach);
            // Cập nhật thông tin Member (ở đây bạn phải gọi DAO hoặc service cập nhật database)
            member.setIDMember(idMember);
            member.setMemberName(memberName);
            member.setGender(gender);
            member.setEmail(email);
            member.setPhone(phone);
            member.setAddress(address);
            member.setDateOfBirth(dateOfBirth);
            member.setSubscription(subscription);
            
            // Nếu upload file thành công thì cập nhật avatar
            if (avatarPath != null) {
                
                member.setImage(avatarPath);
                
                member.setImage(avatarPath);
                
            } else {
                // Nếu không upload file mới, giữ nguyên avatar cũ
                // Bạn nên lấy avatar cũ từ DB hoặc request attribute trước đó để set lại
                // Ví dụ: member.setAvarta(oldAvatar);
            }
            
            // Gọi DAO cập nhật database (bạn phải viết hàm này)
            boolean success = false;
            try {
                success = MemberDao.updateMember(member);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (success) {
                // Cập nhật thành công, chuyển về trang profile với thông tin mới
                request.setAttribute("member", member);
                 request.setAttribute("coach", coachDTO);
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            } else {
                // Xử lý lỗi cập nhật
                request.setAttribute("error", "Update failed");
                request.getRequestDispatcher("editProfile.jsp").forward(request, response);
            }
            noti.sendNotificationToMember("NT19", idMember);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
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
