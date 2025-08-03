/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import DAO.QuitPlanDAO;
import DTO.QuitPlan;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nghia
 */
@WebServlet(name = "EditQuitPlanServlet", urlPatterns = {"/EditQuitPlanServlet"})
public class EditQuitPlanServlet extends HttpServlet {

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
            out.println("<title>Servlet EditQuitPlanServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditQuitPlanServlet at " + request.getContextPath() + "</h1>");
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
          String id = request.getParameter("idQuitPlan");
QuitPlanDAO dao= new QuitPlanDAO();
        // 2. Lấy đối tượng QuitPlan từ DAO
        QuitPlan qp = dao.getPlanById(id);

        // 3. Set vào request
        request.setAttribute("qp", qp);

        // 4. Forward sang JSP chỉnh sửa
        request.getRequestDispatcher("EditQuitPlan.jsp")
               .forward(request, response);
    
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
    response.setContentType("text/html; charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
       String id = request.getParameter("idQuitPlan");
        int period = Integer.parseInt(request.getParameter("periodOfTime"));
        String goals = request.getParameter("goals");
        String progress = request.getParameter("progress");
        double price = Double.parseDouble(request.getParameter("price"));

        // 2. Tạo DTO và gán
        QuitPlan qp = new QuitPlan();
        qp.setIdQuitPlan(id);
        qp.setPeriodOfTime(period);
        qp.setGoals(goals);
        qp.setProgress(progress);
        qp.setPrice(price);
        QuitPlanDAO dao = new QuitPlanDAO();
        // 3. Gọi DAO
        boolean ok = dao.updateQuitPlan(qp);

        // 4. Redirect hoặc forward tuỳ kết quả
        if (ok) {
            response.sendRedirect(request.getContextPath()
                    + "/QuitplanManagerServlet?msg=updated");
        } else {
            request.setAttribute("error", "Cập nhật thất bại!");
            request.setAttribute("qp", qp);
            request.getRequestDispatcher("AdminQuitPlan.jsp")
                    .forward(request, response);
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
