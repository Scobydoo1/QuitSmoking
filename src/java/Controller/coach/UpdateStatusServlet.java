/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.coach;

import DAO.ScheduleDAO;
import DTO.Schedule;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nguyen Tien Dat
 */
public class UpdateStatusServlet extends HttpServlet {

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
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            HttpSession session = request.getSession();
            String idCoach = (String) session.getAttribute("id");

            // Lấy tháng/năm từ request, nếu không có thì dùng tháng hiện tại
            String monthParam = request.getParameter("month");
            String yearParam = request.getParameter("year");

            LocalDate today = LocalDate.now();
            int month = (monthParam != null) ? Integer.parseInt(monthParam) : today.getMonthValue();
            int year = (yearParam != null) ? Integer.parseInt(yearParam) : today.getYear();

            List<Schedule> scheduleList = ScheduleDAO.getScheduleByCoachAndMonth(idCoach, month, year);

            request.setAttribute("scheduleList", scheduleList);
            request.setAttribute("selectedMonth", month);
            request.setAttribute("selectedYear", year);

            request.getRequestDispatcher("coachschedule.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi khi tải lịch: " + e.getMessage());
            request.getRequestDispatcher("coachschedule.jsp").forward(request, response);

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
            String idSchedule = request.getParameter("scheduleId");
            String status = request.getParameter("status");
            String meetLink = request.getParameter("meetLink");

            // Kiểm tra tham số null
            if (idSchedule == null || status == null) {
                request.setAttribute("errorMessage", "Thiếu thông tin để cập nhật lịch.");
                request.getRequestDispatcher("coachschedule.jsp").forward(request, response);
                return;
            }

            boolean updated = ScheduleDAO.updateStatusAndLink(idSchedule, status, meetLink);

            if (updated) {
                request.getSession().setAttribute("successMessage", "Cập nhật lịch thành công.");
            } else {
                request.getSession().setAttribute("errorMessage", "Cập nhật thất bại. Vui lòng thử lại.");
            }

            // Quay lại trang lịch
            response.sendRedirect("CoachScheduleServlet");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi hệ thống: " + e.getMessage());
            request.getRequestDispatcher("coachschedule.jsp").forward(request, response);

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
