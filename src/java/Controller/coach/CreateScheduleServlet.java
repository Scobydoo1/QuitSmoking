/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.coach;

import DAO.MemberDao;
import DAO.NotificationDao;
import DAO.ScheduleDAO;
import DTO.Member;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nguyen Tien Dat
 */
public class CreateScheduleServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String idMember = request.getParameter("idMember");
        String selectedDays = request.getParameter("days");
        String startTimeStr = request.getParameter("startTime");
        String startDateStr = request.getParameter("startDate");
        NotificationDao noti=new NotificationDao();
        try {
            LocalDate startDate = LocalDate.parse(startDateStr);
            LocalTime startTime = LocalTime.parse(startTimeStr);

            MemberDao memberDAO = new MemberDao();
            Member member = memberDAO.getMemberById(idMember);

            if (member == null) {
                request.setAttribute("errorMessage", "Không tìm thấy thành viên.");
                request.getRequestDispatcher("schedulesetup.jsp").forward(request, response);
                return;
            }

            ScheduleDAO scheduleDAO = new ScheduleDAO();
            scheduleDAO.createScheduleForMember(member, startDate, startTime, selectedDays);
            scheduleDAO.updateLatestStatusToStudying(member.getIDMember());
         
            response.sendRedirect("ManageMemberServlet");
              noti.sendNotificationToMember("NT12", idMember);
              
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi khi tạo lịch: " + e.getMessage());
            request.getRequestDispatcher("schedulesetup.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
