package Controller;

import DAO.SavingsCalculationDAO;
import DTO.SavingsCalculation;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SavingsCalculator")
public class SavingsCalculatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("id");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            SavingsCalculationDAO dao = new SavingsCalculationDAO();
            List<SavingsCalculation> history = dao.getSavingsHistoryByMember(userId);
            request.setAttribute("savingsHistory", history);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("savings-calculator.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("id");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Lấy dữ liệu từ form
            int cigarettesPerDay = Integer.parseInt(request.getParameter("cigarettesPerDay"));
            double pricePerPack = Double.parseDouble(request.getParameter("pricePerPack"));
            int cigarettesPerPack = Integer.parseInt(request.getParameter("cigarettesPerPack"));
            int daysSinceQuit = Integer.parseInt(request.getParameter("daysSinceQuit"));

            SavingsCalculationDAO dao = new SavingsCalculationDAO();

            // Tính toán số tiền tiết kiệm
            double totalSaved = dao.calculateSavings(cigarettesPerDay, pricePerPack, cigarettesPerPack, daysSinceQuit);

            // Lưu vào database
            SavingsCalculation calc = new SavingsCalculation(userId, cigarettesPerDay, pricePerPack,
                    cigarettesPerPack, daysSinceQuit, totalSaved);
            dao.saveSavingsCalculation(calc);

            // Gửi kết quả về JSP
            request.setAttribute("totalSaved", totalSaved);
            request.setAttribute("calculation", calc);

            // Lấy lịch sử cập nhật
            List<SavingsCalculation> history = dao.getSavingsHistoryByMember(userId);
            request.setAttribute("savingsHistory", history);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi xảy ra trong quá trình tính toán!");
        }

        request.getRequestDispatcher("savings-calculator.jsp").forward(request, response);
    }
}
