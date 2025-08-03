
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, DTO.Member, DTO.Support"%>
<%
    List<Member> memberList = (List<Member>) request.getAttribute("memberList");
    List<Support> chatMessages = (List<Support>) request.getAttribute("chatMessages");
    String selectedMemberId = (String) request.getAttribute("selectedMemberId");
    String selectedMemberName = "";

    if (memberList != null && selectedMemberId != null) {
        for (Member m : memberList) {
            if (m.getIDMember().equals(selectedMemberId)) {
                selectedMemberName = m.getMemberName();
                break;
            }
        }
    }
%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Support - Coach</title>
        <link rel="stylesheet" href="css/support-coach.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="stylesheet" href="css/stylehomepage.css">
        <%@include file="information/bootstrap.jspf" %>

    </head>
    <body>
        
   <style>
            /* Home Button */
            .home-button {
                position: fixed; /* C? ??nh v? trí */
                top: 20px; /* Cách mép trên 20px */
                left: 20px; /* Cách mép trái 20px */
                background-color: #28a745; /* Màu xanh lá ch? ??o */
                color: white; /* Màu ch? */
                padding: 10px 20px; /* Kho?ng cách bên trong nút */
                font-size: 16px; /* Kích th??c ch? */
                font-weight: bold; /* Ch? ??m */
                border: none; /* Không vi?n */
                border-radius: 8px; /* Bo góc */
                text-decoration: none; /* Lo?i b? g?ch chân */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* ?? bóng nh? */
                transition: all 0.3s ease; /* Hi?u ?ng chuy?n ??ng */
                z-index: 999; /* Hi?n th? trên cùng */
            }


            .home-button:hover {
                background-color: #218838; /* Màu ??m h?n khi hover */
                box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2); /* ?? bóng t?ng khi hover */
                transform: scale(1.05); /* Hi?u ?ng phóng to nh? */
                text-decoration: none;
            }

        </style>
        <a href="homepage.jsp" class="home-button">Home</a>


        <!-- Header -->
        <header class="coach-header">
            <div class="header-container">
                <div class="header-left">
                    <div>
                        <h1 class="header-title">Hỗ trợ thành viên</h1>
                        <p class="header-subtitle">Quản lý và hỗ trợ thành viên</p>
                    </div>
                </div>
                <div class="header-right">
                    <div class="coach-info">
                        <div class="coach-avatar">
                            <i class="fas fa-user-tie"></i>
                        </div>
                        <div class="coach-details">
                            <div class="coach-name">Coach Admin</div>
                            <div class="coach-role">Huấn luyện viên</div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <div class="container">
            <!-- Danh sách thành viên -->
            <div class="member-list">
                <h3>Thành viên</h3>
                <%
                    if (memberList != null && !memberList.isEmpty()) {
                        for (Member m : memberList) {
                            String activeClass = m.getIDMember().equals(selectedMemberId) ? "active" : "";
                %>
                <div class="member-item <%= activeClass%>">
                    <a href="CoachSupportServlet?memberId=<%=m.getIDMember()%>">
                        <%= m.getMemberName()%>
                    </a>
                </div>
                <%
                    }
                } else {
                %>
                <div style="padding: 20px; text-align: center; color: #6c757d;">
                    <i class="fas fa-users" style="font-size: 24px; margin-bottom: 10px; opacity: 0.5;"></i>
                    <p>Không có thành viên nào.</p>
                </div>
                <%
                    }
                %>
            </div>

            <!-- Khung chat -->
            <div class="chat-section">
                <% if (selectedMemberId != null && !selectedMemberId.isEmpty()) {%>
                <!-- Chat Header -->
                <div class="chat-section-header">
                    <div class="chat-user-avatar">
                        <%= selectedMemberName.substring(0, 1).toUpperCase()%>
                    </div>
                    <div class="chat-user-info">
                        <h4><%= selectedMemberName%></h4>
                        <p>Thành viên</p>
                    </div>
                </div>

                <!-- Chat Messages -->
                <div class="chat-box" id="chatBox">
                    <% if (chatMessages != null && !chatMessages.isEmpty()) {
                            for (Support msg : chatMessages) {
                                String messageClass = "coach".equals(msg.getAuthorSend()) ? "sent" : "received";
                    %>
                    <div class="message <%= messageClass%>">
                        <div class="message-content">
                            <div class="message-bubble">
                                <p class="message-text"><%= msg.getContent()%></p>
                            </div>
                            <div class="message-info">
                                <span class="message-author"><%= msg.getAuthorSend()%></span>
                                <span class="message-time"><%= msg.getFeedbackDate()%></span>
                            </div>
                        </div>
                    </div>
                    <% }
                    } else { %>
                    <div class="empty-chat">
                        <i class="fas fa-comments"></i>
                        <h3>Chưa có tin nhắn</h3>
                        <p>Hãy bắt đầu cuộc trò chuyện với thành viên</p>
                    </div>
                    <% }%>
                </div>

                <!-- Chat Input -->
                <div class="chat-input">
                    <form action="CoachSupportServlet" method="post" id="chatForm">
                        <input type="hidden" name="memberId" value="<%=selectedMemberId%>">
                        <textarea name="message" id="messageInput" placeholder="Nhập tin nhắn của bạn..." rows="1" required></textarea>
                        <button type="submit" class="send-button">
                            <i class="fas fa-paper-plane"></i>
                        </button>
                    </form>
                </div>
                <% } else { %>
                <div class="empty-chat">
                    <i class="fas fa-user-friends"></i>
                    <h3>Chọn thành viên để bắt đầu</h3>
                    <p>Chọn một thành viên từ danh sách để bắt đầu cuộc trò chuyện</p>
                </div>
                <% }%>
            </div>
        </div>

        <script>
            // Auto scroll to bottom
            const chatBox = document.getElementById('chatBox');
            if (chatBox) {
                chatBox.scrollTop = chatBox.scrollHeight;
            }

            // Auto resize textarea and Enter to send
            const textarea = document.getElementById('messageInput');
            const chatForm = document.getElementById('chatForm');

            if (textarea && chatForm) {
                // Auto resize
                textarea.addEventListener('input', function () {
                    this.style.height = 'auto';
                    this.style.height = Math.min(this.scrollHeight, 120) + 'px';
                });

                // Enter to send message
                textarea.addEventListener('keydown', function (e) {
                    if (e.key === 'Enter' && !e.shiftKey) {
                        e.preventDefault();
                        if (this.value.trim() !== '') {
                            chatForm.submit();
                        }
                    }
                });
            }
        </script>
    </body>
</html>
