<%@page import="DAO.SavingsCalculationDAO"%>
<%@page import="DTO.SavingsCalculation"%>
<%@page import="java.util.List"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tính Toán Tiền Tiết Kiệm - Health Center</title>
    <link href="css/stylehomepage.css" rel="stylesheet" type="text/css">
    <link href="css/savings-calculator.css" rel="stylesheet" type="text/css">
    <%@include file="information/bootstrap.jspf" %>
</head>
<body>
    <%@include file="information/header.jspf" %>
    
    <div class="savings-calculator-container">
        <div class="container">
            <!-- Header Section -->
            <div class="calculator-header">
                <h1 class="calculator-title">💰 Máy Tính Tiền Tiết Kiệm</h1>
                <p class="calculator-subtitle">Tính toán số tiền bạn đã tiết kiệm được khi cai thuốc lá</p>
            </div>
            
            <!-- Calculator Form -->
            <div class="row">
                <div class="col-lg-6">
                    <div class="calculator-form-card">
                        <h3 class="form-title">Nhập Thông Tin</h3>
                        <form action="SavingsCalculator" method="post" class="calculator-form">
                            <div class="form-group">
                                <label for="cigarettesPerDay">
                                    <i class="fas fa-smoking"></i> Số điếu thuốc/ngày (trước khi cai):
                                </label>
                                <input type="number" id="cigarettesPerDay" name="cigarettesPerDay" 
                                       class="form-control" min="1" max="100" required>
                            </div>
                            
                            <div class="form-group">
                                <label for="pricePerPack">
                                    <i class="fas fa-money-bill-wave"></i> Giá 1 gói thuốc (VNĐ):
                                </label>
                                <input type="number" id="pricePerPack" name="pricePerPack" 
                                       class="form-control" min="1000" step="1000" required>
                            </div>
                            
                            <div class="form-group">
                                <label for="cigarettesPerPack">
                                    <i class="fas fa-box"></i> Số điếu/gói:
                                </label>
                                <select id="cigarettesPerPack" name="cigarettesPerPack" class="form-control" required>
                                    <option value="20">20 điếu (phổ biến)</option>
                                    <option value="10">10 điếu</option>
                                    <option value="25">25 điếu</option>
                                </select>
                            </div>
                            
                            <div class="form-group">
                                <label for="daysSinceQuit">
                                    <i class="fas fa-calendar-check"></i> Số ngày đã cai thuốc:
                                </label>
                                <input type="number" id="daysSinceQuit" name="daysSinceQuit" 
                                       class="form-control" min="1" required>
                            </div>
                            
                            <button type="submit" class="btn btn-calculate">
                                <i class="fas fa-calculator"></i> Tính Toán
                            </button>
                        </form>
                    </div>
                </div>
                
                <div class="col-lg-6">
                    <!-- Results Section -->
                    <% if (request.getAttribute("totalSaved") != null) { %>
                        <div class="results-card">
                            <h3 class="results-title">🎉 Kết Quả Tính Toán</h3>
                            <div class="result-amount">
                                <%
                                    double totalSaved = (Double) request.getAttribute("totalSaved");
                                    NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
                                %>
                                <span class="amount"><%=formatter.format(totalSaved)%> VNĐ</span>
                            </div>
                            <p class="result-message">
                                Chúc mừng! Bạn đã tiết kiệm được <strong><%=formatter.format(totalSaved)%> VNĐ</strong> 
                                kể từ khi cai thuốc lá.
                            </p>
                            
                            <div class="achievement-badges">
                                <% if (totalSaved >= 1000000) { %>
                                    <span class="badge badge-gold">🏆 Triệu phú</span>
                                <% } else if (totalSaved >= 500000) { %>
                                    <span class="badge badge-silver">🥈 Tiết kiệm cao</span>
                                <% } else if (totalSaved >= 100000) { %>
                                    <span class="badge badge-bronze">🥉 Khởi đầu tốt</span>
                                <% } %>
                            </div>
                        </div>
                    <% } %>
                    
                    <!-- Tips Section -->
                    <div class="tips-card">
                        <h4 class="tips-title">💡 Mẹo Hay</h4>
                        <ul class="tips-list">
                            <li>Hãy dùng số tiền tiết kiệm để mua những thứ bạn yêu thích</li>
                            <li>Theo dõi tiến trình hàng ngày để có động lực</li>
                            <li>Chia sẻ thành tích với gia đình và bạn bè</li>
                        </ul>
                    </div>
                </div>
            </div>
            
            <!-- History Section -->
            <% 
                List<SavingsCalculation> history = (List<SavingsCalculation>) request.getAttribute("savingsHistory");
                if (history != null && !history.isEmpty()) {
            %>
            <div class="history-section">
                <h3 class="history-title">📊 Lịch Sử Tính Toán</h3>
                <div class="table-responsive">
                    <table class="table history-table">
                        <thead>
                            <tr>
                                <th>Ngày tính</th>
                                <th>Điếu/ngày</th>
                                <th>Giá/gói</th>
                                <th>Ngày cai</th>
                                <th>Tiết kiệm</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
                                for (SavingsCalculation calc : history) { 
                            %>
                            <tr>
                                <td><%=calc.getCalculationDate()%></td>
                                <td><%=calc.getCigarettesPerDay()%></td>
                                <td><%=formatter.format(calc.getPricePerPack())%> VNĐ</td>
                                <td><%=calc.getDaysSinceQuit()%> ngày</td>
                                <td class="amount-cell"><%=formatter.format(calc.getTotalSaved())%> VNĐ</td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
            <% } %>
        </div>
    </div>
    
    <%@include file="information/footer.jspf" %>
</body>
</html>
