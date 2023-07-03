package exm.sisinf.webpdm.auth;

import exm.sisinf.webpdm.model.Utente;
import exm.sisinf.webpdm.repository.UtenteRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthTokenService {

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenService.class);

    @Autowired
    private HttpSession httpSession;

    @Value("${webpdm.app.jwtSessionAttribute}")
    private String tokenSessionKey;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UtenteRepository utenteRepository;

    public void store(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(tokenSessionKey, token);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);

        httpSession.setAttribute(tokenSessionKey, token);
    }

    public String retrieve(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> tokenSessionKey.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findAny().orElse(null);
    }

    public Utente getUtente(String token) {
        try {
            String username = jwtUtils.getUserNameFromJwtToken(token);
            return utenteRepository.findByUsername(username).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}
