/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MemberDao;
import DAO.TokenForgotPasswordDAO;
import DTO.Member;
import DTO.TokenForgotPasswordDTO;
import Utils.EmailUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thinkpad
 */
@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/ForgotPasswordServlet"})
public class ForgotPasswordServlet extends HttpServlet {

    private static final String FORGOT_PASSWORD_PAGE = "forgotPassword.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(FORGOT_PASSWORD_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email");
        String url = FORGOT_PASSWORD_PAGE;
        try {
            if (email == null || email.trim().isEmpty()) {
                request.setAttribute("ERROR", "Vui lòng nhập email!!!");
            } else {
                MemberDao dao = new MemberDao();
                Member member = dao.getEmailByMember(email);
                if (member != null) {
                    String token = UUID.randomUUID().toString();
                    LocalDateTime expiryDate = LocalDateTime.now().plusHours(1);
                    TokenForgotPasswordDTO resetToken = new TokenForgotPasswordDTO();
                    resetToken.setIDMember(member.getIDMember());
                    resetToken.setToken(token);
                    resetToken.setExpiryTime(expiryDate);

                    TokenForgotPasswordDAO daoReset = new TokenForgotPasswordDAO();
                    daoReset.saveToken(resetToken);

                    String resetLink = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                            + request.getContextPath() + "/ResetPasswordServlet?token=" + token;

                    String subject = "Yêu cầu đặt lại mật khẩu";
                    String body = "<p>Xin chào " + member.getMemberName() + 
                            ",</p>" + "<p>Bạn có yêu cầu đặt lại mật khẩu. Vui lòng nhấn vào đường dẫn bên dưới để đặt lại mật khẩu:</p>" + 
                            "<a href=\"" + resetLink + "\">" + resetLink + "</a>" + 
                            "<p>Đường dẫn này sẽ hết hạn trong 1 giờ.</p>";
                    EmailUtils.sendEmail(member.getEmail(), subject, body);
                    request.setAttribute("message", "Liên kết đặt lại mật khẩu đã được gửi đến email của bạn");
                } else {
                    request.setAttribute("ERROR", "Email không tồn tại vui lòng kiểm tra lại!!!");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
