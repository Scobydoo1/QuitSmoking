package Controller;

import DAO.MemberDao;
import DAO.TokenForgotPasswordDAO;
import DTO.TokenForgotPasswordDTO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/ResetPasswordServlet"})
public class ResetPasswordServlet extends HttpServlet {

    private final String RESET_PAGE = "resetPassword.jsp";     // form nhập mật khẩu mới
    private final String SUCCESS_PAGE = "login.jsp";           // sau khi đổi mật khẩu thành công
    private final String ERROR_PAGE = "error.jsp";             // nếu token sai hoặc hết hạn

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        String url = ERROR_PAGE;
        try {
            if (token == null || token.trim().isEmpty()) {
                request.setAttribute("ERROR", "Invalid token.");
                url = ERROR_PAGE;
            }
            
            TokenForgotPasswordDAO tokenDao = new TokenForgotPasswordDAO();
            TokenForgotPasswordDTO resetToken = tokenDao.getToken(token);

            if (resetToken == null || resetToken.getExpiryTime().isBefore(LocalDateTime.now())) {
                request.setAttribute("ERROR", "Token is invalid or has expired.");
                url = ERROR_PAGE;
                return;
            }
            // Token hợp lệ, forward tới form đổi mật khẩu
            request.setAttribute("token", token);
            url = RESET_PAGE;

        } catch (SQLException ex) {
            ex.printStackTrace();  
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        String newPassword = request.getParameter("newPassword");
        String url = ERROR_PAGE;
        try {
            TokenForgotPasswordDAO tokenDao = new TokenForgotPasswordDAO();
            TokenForgotPasswordDTO resetToken = tokenDao.getToken(token);

            if (resetToken == null || resetToken.getExpiryTime().isBefore(LocalDateTime.now())) {
                request.setAttribute("ERROR", "Token is invalid or has expired.");
                url = ERROR_PAGE;
                return;
            }

            String idMember = resetToken.getIDMember();

            // Cập nhật mật khẩu mới
            MemberDao member = new MemberDao();
            member.updatePassword(idMember, newPassword);

            // Xóa token sau khi sử dụng
            tokenDao.deleteToken(token);

            request.setAttribute("message", "Password reset successful. Please login again.");
            request.getRequestDispatcher(SUCCESS_PAGE).forward(request, response);

        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("ERROR", "Database error during password reset.");
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            request.setAttribute("ERROR", "System error during password reset.");
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
