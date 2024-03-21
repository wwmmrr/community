package life.majiang.community.controller;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GiteeUser;
import life.majiang.community.provider.GiteeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OAuthController {

    @Autowired
    GiteeProvider giteeProvider;

    @Value("${gitee.oauth.redirect-uri}")
    String redirect_uri;
    @Value("${gitee.oauth.client-id}")
    String client_id;
    @Value("${gitee.oauth.client-secret}")
    String client_secret;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
//        accessTokenDTO.setState(state);
        System.out.println(accessTokenDTO.toString());
        String accessToken = giteeProvider.getAccessToken(accessTokenDTO);
        System.out.println("accessToken="+accessToken);
        GiteeUser user = giteeProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
