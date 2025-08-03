/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.coach;

import DAO.NotificationDao;
import DAO.ProgressLogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
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
@WebServlet(name = "SaveQuestionsServlet", urlPatterns = {"/SaveQuestionsServlet"})
public class SaveQuestionsServlet extends HttpServlet {

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
      private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");

        // 1. Lấy idMember, idCoach
        String idMember = request.getParameter("idMember");
        
        HttpSession session = request.getSession(false);
         String idCoach = (String) session.getAttribute("id");
       String progress = request.getParameter("progress");

        // 2. Lấy option planDays và tính EndDate
        String planDaysStr=request.getParameter("planDays");
        int planDays = Integer.parseInt(request.getParameter("planDays"));
        LocalDate  today   = LocalDate.now();
        LocalDate  endDate = today.plusDays(planDays);
        Date       sqlEnd  = Date.valueOf(endDate);

        // 3. Lấy câu hỏi
        int    questionCount = Integer.parseInt(request.getParameter("questionCount"));
        String[] questions   = new String[questionCount];
        for (int i = 1; i <= questionCount; i++) {
            String raw = request.getParameter("qs" + i);
            questions[i - 1] = (raw != null && !raw.trim().isEmpty())
                               ? raw.trim()
                               : null;
        }

        // 4. Gọi DAO
        boolean success;
        String  message;
        try {
            ProgressLogDAO.insertQuestions(idMember, idCoach, sqlEnd,progress, questions, planDaysStr +" day");
            success = true;
            message = "Đã lưu " + questionCount 
                    + " câu hỏi; kế hoạch đến " + endDate;
        } catch (Exception e) {
            success = false;
            message = "Lỗi khi lưu: " + e.getMessage();
            e.printStackTrace();
        }

        // 5. Forward kết quả
        request.setAttribute("success", success);
        request.setAttribute("message", message);
        NotificationDao.sendNotificationToMember("NT18", idMember);
          NotificationDao.sendNotificationToMember("NT20", idMember);
        request.getRequestDispatcher("CreateQuestion.jsp").forward(request, response);
        
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
        processRequest(request, response);
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
