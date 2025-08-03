package Filter;

import DAO.CoachDao;
import DAO.MemberDao;
import DTO.Coach;
import DTO.Member;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        // Nếu session tồn tại, tiếp tục đăng nhập
        if (session != null && session.getAttribute("id") != null) {
            chain.doFilter(request, response);
            return;
        }

        // Nếu session hết, lấy cookie đăng nhập
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) {
            MemberDao memberDao = new MemberDao();
            CoachDao coachDao = new CoachDao();

            for (Cookie cookie : cookies) {
                String id = cookie.getName();
                String password = cookie.getValue();

                try {
                    Member member = memberDao.checkLogin(id, password);
                    if (member != null) {
                        session = httpRequest.getSession(true);
                        session.setAttribute("id", member.getIDMember());
                        session.setAttribute("role", "member");
                        session.setAttribute("username", member.getMemberName());
                        session.setAttribute("coachId", member.getIDCoach());
                        break;
                    }

                    Coach coach = coachDao.checkLogin(id, password);
                    if (coach != null) {
                        session = httpRequest.getSession(true);
                        session.setAttribute("id", coach.getIDCoach());
                        session.setAttribute("role", "coach");
                        session.setAttribute("username", coach.getCoachName());
                        session.setAttribute("coachId", coach.getIDCoach());
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
