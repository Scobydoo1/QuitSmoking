<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, DTO.Support, DAO.SupportDAO" %>
<%
    String idMember = (String) request.getAttribute("idMember");
    String idCoach = (String) request.getAttribute("idCoach");
    String memberName = (String) request.getAttribute("memberName");

    if (memberName == null) {
        memberName = "Thành viên";
    }

    SupportDAO dao = new SupportDAO();
    List<Support> messages = dao.getAllMessagesBetween(idMember, idCoach);
%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Support Chat</title>
        <link rel="stylesheet" href="css/support-member.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="stylesheet" href="css/stylehomepage.css">
        <%@include file="information/bootstrap.jspf" %>

    </head>
    <body>
        <%@include file="information/header.jspf" %>
        <!-- Header -->
        <header class="member-header">
            <div class="header-container">
                <div class="header-left">
                    <div>
                        <h1 class="header-title">Hỗ trợ từ Coach</h1>
                        <p class="header-subtitle">Liên hệ với huấn luyện viên của bạn</p>
                    </div>
                </div>
                <div class="header-right">
                    <div class="member-info">
                        <div class="member-avatar">
                            <i class="fas fa-user"></i>
                        </div>
                        <div class="member-details">
                            <div class="member-name"><%= memberName%></div>
                            <div class="member-role">Thành viên</div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <div class="chat-container">
            <div class="chat-section">
                <!-- Chat Header -->
                <div class="chat-section-header">
                    <div class="coach-avatar">
                        <i class="fas fa-user-tie"></i>
                    </div>
                    <div class="coach-info">
                        <h4>Coach</h4>
                        <p>Huấn luyện viên</p>
                    </div>
                </div>

                <!-- Chat Messages -->
                <div class="chat-box" id="chatBox">
                    <% if (messages != null && !messages.isEmpty()) {
                            for (Support msg : messages) {
                                String messageClass = "member".equals(msg.getAuthorSend()) ? "sent" : "received";
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
                        <p>Hãy bắt đầu cuộc trò chuyện với coach của bạn</p>
                    </div>
                    <% }%>
                </div>

                <!-- Chat Input -->
                <div class="chat-input">
                    <form action="SendSupportServlet" method="post" id="chatForm">
                        <input type="hidden" name="idMember" value="<%= idMember%>">
                        <input type="hidden" name="idCoach" value="<%= idCoach%>">
                        <input type="hidden" name="authorSend" value="member">
                        <textarea name="content" id="messageInput" placeholder="Nhập tin nhắn của bạn..." rows="1" required></textarea>
                        <button type="submit" class="send-button">
                            <i class="fas fa-paper-plane"></i>
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="information/footer.jspf" %>
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
