/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.member;

import DAO.BadgeDetailDAO;
import DAO.NotificationDao;
import DAO.ProgressLogDAO;
import DTO.ProgressLog;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nguyen Tien Dat
 */
public class SubmitProgressLogServlet extends HttpServlet {

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

        try {
            String idLogParam = request.getParameter("idLog");
            if (idLogParam == null || idLogParam.isEmpty()) {
                request.setAttribute("errorMessage", "Thiếu IDLog.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            int idLog = Integer.parseInt(idLogParam);
            ProgressLogDAO dao = new ProgressLogDAO();
            ProgressLog log = dao.getById(idLog);

            if (log == null) {
                request.setAttribute("errorMessage", "Không tìm thấy bản ghi.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            request.setAttribute("log", log);
            request.getRequestDispatcher("progressAnswer.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi hệ thống: " + e.getMessage());
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

        try {
            int idLog = Integer.parseInt(request.getParameter("idLog"));
            String action = request.getParameter("action"); // "save" hoặc "submit"

            ProgressLogDAO dao = new ProgressLogDAO();
            ProgressLog log = dao.getById(idLog);

            if (log == null) {
                request.setAttribute("errorMessage", "Không tìm thấy bản ghi.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            for (int i = 1; i <= 5; i++) {
                String answer = request.getParameter("as" + i);
                if (answer != null) {
                    log.setAnswer(i, answer.trim());
                }
            }

            log.setStatus("save".equalsIgnoreCase(action) ? "save" : "submit");

            boolean updated = ProgressLogDAO.update(log);
            if (updated) {
                if ("submit".equalsIgnoreCase(action)) {
                    try {
                        BadgeDetailDAO badgeDAO = new BadgeDetailDAO();
                        badgeDAO.checkAndGrantBadges(log.getIdMember());
                    } catch (Exception ex) {
                        ex.printStackTrace(); // chỉ log lỗi, không dừng tiến trình
                    }
                }
                response.sendRedirect("progressList.jsp");
            } else {
                request.setAttribute("errorMessage", "Cập nhật không thành công.");
                request.setAttribute("log", log);
                request.getRequestDispatcher("progressAnswer.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi xử lý: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
