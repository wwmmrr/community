package life.majiang.community.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GiteeUser;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.provider.GiteeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class OAuthController {
    @Value("${gitee.oauth.redirect-uri}")
    String redirect_uri;
    @Value("${gitee.oauth.client-id}")
    String client_id;
    @Value("${gitee.oauth.client-secret}")
    String client_secret;

    @Autowired
    GiteeProvider giteeProvider;
    @Autowired
    private UserMapper userMapper;


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           HttpServletRequest request, HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        System.out.println(accessTokenDTO.toString());
        String accessToken = giteeProvider.getAccessToken(accessTokenDTO);
        System.out.println("accessToken=" + accessToken);
        GiteeUser giteeUser = giteeProvider.getUser(accessToken);
        if (giteeUser == null) {
            //登录失败,重新登录
            return "redirect:/";
        }

        User user = new User();
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        user.setName(giteeUser.getName());
        user.setAccountId(String.valueOf(giteeUser.getId()));
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());

        userMapper.insert(user);

        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(60*60*24);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/";
    }
}
