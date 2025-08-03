/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MemberDao;
import DAO.NotificationDao;
import DTO.Notification;
import Utils.DBUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Nghia
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

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
            request.setCharacterEncoding("UTF-8");

            // 1. Lấy tham số
            String id = request.getParameter("memberid");
            String fullName = request.getParameter("fullname");
            String gender = request.getParameter("gender");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirmPassword");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String dob = request.getParameter("dob");

//             2. Giữ lại giá trị đã nhập để forward lại form khi có lỗi
            request.setAttribute("memberid", id);
            request.setAttribute("fullname", fullName);
            request.setAttribute("gender", gender);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            request.setAttribute("dob", dob);
            String status = "1";

            try {
                MemberDao memDao = new MemberDao();

                // 3. Kiểm tra định dạng ID Member
                String idPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z0-9]{8,}$";
                if (!id.matches(idPattern)) {
                    request.setAttribute("errorMessage",
                            "Account phải ≥8 ký tự, chứa ít nhất 1 chữ hoa, 1 chữ thường, 1 số, và không có ký tự đặc biệt.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    return;
                }

                // 4. Kiểm tra mật khẩu khớp và định dạng
                if (!password.equals(confirm)) {
                    request.setAttribute("errorMessage", "Password và Confirm Password không khớp.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    return;
                }
                String pwdPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$";
                if (!password.matches(pwdPattern)) {
                    request.setAttribute("errorMessage",
                            "Mật khẩu phải ≥8 ký tự, chứa ít nhất 1 chữ hoa, 1 chữ thường, 1 số và 1 ký tự đặc biệt.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    return;
                }

                // 5. Kiểm tra trùng ID
                if (memDao.getAllMemberIds().contains(id)) {
                    request.setAttribute("errorMessage", "Account đã tồn tại, vui lòng chọn khác.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    return;
                }

                // 6. Thực hiện đăng ký
                memDao.resigter(id, password, fullName, gender, phone, email, address, dob, status);

                // 7. Gửi thông báo chào mừng
                new NotificationDao().sendNotificationToMember("NT01", id);

                // 8. Thành công
                request.setAttribute("successMessage", "Đăng ký thành công! Vui lòng đăng nhập.");
                request.getRequestDispatcher("register.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Lỗi hệ thống: " + e.getMessage());
                request.getRequestDispatcher("register.jsp").forward(request, response);
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
