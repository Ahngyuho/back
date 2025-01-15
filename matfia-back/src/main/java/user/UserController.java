package user;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.JsonParser;
import utils.JwtUtil;

import java.io.IOException;

@WebServlet("/user/*")
public class UserController extends HttpServlet {

    private UserService userService;

    public UserController()
    {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        if (action.equals("/signup")) {
            // JSON 형식으로 요청 받는 부분
            UserReqDto userReqDto = JsonParser.parse(req, UserReqDto.class);

            // JSON 형식으로 응답 주는 부분
            UserResDto userResDto = new UserResDto(userReqDto);
            JsonParser.parse(resp, userResDto);

            userService.signup(userReqDto);
        } else if (action.equals("/login")) {
            UserReqDto userReqDto = JsonParser.parse(req, UserReqDto.class);

            int idx = userService.login(userReqDto);
            if (idx > 0) {
                String token = JwtUtil.generateToken(userReqDto.getId(),idx);

                Cookie cookie = new Cookie("ATOKEN", token);
                cookie.setHttpOnly(true);
                cookie.setSecure(false);
                cookie.setMaxAge(30 * 60);
                cookie.setPath("/");

                resp.addCookie(cookie);

                LoginResDto loginResDto = new LoginResDto(true, token);
                JsonParser.parse(resp, loginResDto);

                return;
            }
        }
    }
}
