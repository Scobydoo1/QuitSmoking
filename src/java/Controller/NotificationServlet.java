/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DTO.Notification;
import DAO.NotificationDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nguyen Tien Dat
 */
public class NotificationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String memberId = (String) session.getAttribute("id");

        if (memberId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            List notifications = NotificationDao.getNotificationsByUserId(memberId);
            int unreadCount = NotificationDao.getUnreadCount(memberId);

            request.setAttribute("notifications", notifications);
            request.setAttribute("unreadCount", new Integer(unreadCount));
            request.setAttribute("memberId", memberId);

            request.getRequestDispatcher("viewNotifications.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi xảy ra khi tải thông báo");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String memberId = (String) session.getAttribute("id");

        if (memberId == null) {
            out.print("{\"success\": false, \"message\": \"User not logged in\"}");
            return;
        }

        String action = request.getParameter("action");
        System.out.println("Received action: " + action); // Debug log

        try {
            if ("deleteNotification".equals(action)) {
                String notificationId = request.getParameter("notificationId");
                System.out.println("Deleting notification ID: " + notificationId); // Debug log

                if (notificationId != null && !notificationId.trim().equals("")) {
                    boolean success = NotificationDao.deleteNotification(notificationId, memberId);
                    System.out.println("Delete result: " + success); // Debug log

                    if (success) {
                        out.print("{\"success\": true, \"message\": \"Notification deleted\"}");
                    } else {
                        out.print("{\"success\": false, \"message\": \"Failed to delete notification\"}");
                    }
                } else {
                    out.print("{\"success\": false, \"message\": \"Notification ID is required\"}");
                }
            } else if ("markAllAsRead".equals(action)) {
                boolean success = NotificationDao.markAllAsRead(memberId);
                if (success) {
                    out.print("{\"success\": true, \"message\": \"All notifications marked as read\"}");
                } else {
                    out.print("{\"success\": false, \"message\": \"Failed to mark notifications as read\"}");
                }
            } else {
                out.print("{\"success\": false, \"message\": \"Invalid action: " + action + "\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"success\": false, \"message\": \"Server error: " + e.getMessage() + "\"}");
        }
    }
}
