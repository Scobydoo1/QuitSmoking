/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CoachDao;
import DAO.MemberDao;
import DAO.NotificationDao;
import DAO.SystemDao;
import DTO.Coach;
import DTO.Member;
import DTO.Payment;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nghia
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {

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
            out.println("<title>Servlet PaymentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentServlet at " + request.getContextPath() + "</h1>");
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
        String goal = request.getParameter("goal");
        String price = request.getParameter("price");
         String date = request.getParameter("date");
        HttpSession session = request.getSession();
        String idMember = (String) session.getAttribute("id");
        String role = (String) session.getAttribute("role");
        String mycoach = (String) session.getAttribute("coachId");

        MemberDao memberDao = new MemberDao();
        Member member = memberDao.getMemberById(idMember);
        SystemDao SystemDao = new SystemDao();
        // Lấy danh sách phương thức thanh toán từ DAO
        if (idMember == null || role.equalsIgnoreCase("coach")) {
            // Nếu không có idMember trong session, chuyển hướng đến trang login.jsp
            response.sendRedirect("login.jsp");
            return; // Dừng các thao tác tiếp theo để tránh lỗi
        }
        // Set danh sách payments vào request để chuyển tiếp vào JSP
        try {

            request.setAttribute("goal", goal);
            request.setAttribute("price", price);
            request.setAttribute("date", date);
            request.setAttribute("member", member);
            List<Payment> payments = SystemDao.getAllPayments();
            request.setAttribute("payments", payments);
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
        } catch (Exception e) {
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
        MemberDao mem = new MemberDao();
        NotificationDao nofiDao = new NotificationDao();
        CoachDao coachDAO = new CoachDao();

        String goal = request.getParameter("goal");
        SystemDao dao = new SystemDao();
        HttpSession session = request.getSession();
        String idMember = (String) session.getAttribute("id");
        String quitPlan = "QP02";  // Giá trị mặc định
        String IDpaymentMethod = request.getParameter("paymentMethod");
        // Kiểm tra giá trị của goal và gán quitPlan tương ứng
        if ("silver".trim().equalsIgnoreCase(goal.trim())) {
            quitPlan = "QP01";  // Nếu goal là silver, quitPlan là QP01
        } else if ("gold".trim().equalsIgnoreCase(goal.trim())) {
            quitPlan = "QP02";  // Nếu goal là gold, quitPlan là QP02
        } else {
            quitPlan = "QP03";  // Nếu goal là diamond, quitPlan là QP03
        }
        String status = "1";

        try {
            dao.insertQuitPlanRegistration(idMember, "PM01", quitPlan, "success");
            mem.updateCoachForMember(idMember);
            mem.updateMemberStatus(idMember, status);
            String coachId = coachDAO.getCoachIdFromMember(idMember);
            Coach coach = coachDAO.getCoachById(coachId);

            nofiDao.sendNotificationToMember("NT07", idMember);
            nofiDao.sendNotificationToCoach("NT08", coachId);
            request.setAttribute("coachName", coach.getCoachName());
            request.getRequestDispatcher("AfterPayment.jsp").forward(request, response);

        } catch (Exception e) {
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
