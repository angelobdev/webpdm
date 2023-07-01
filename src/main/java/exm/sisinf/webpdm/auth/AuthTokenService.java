package exm.sisinf.webpdm.auth;

import exm.sisinf.webpdm.model.Utente;
import exm.sisinf.webpdm.repository.UtenteRepository;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    public void store(String token) {
        httpSession.setAttribute(tokenSessionKey, token);
    }

    public String retrieve() {
        return (String) httpSession.getAttribute(tokenSessionKey);
    }

    public Utente getUtente() {
        try {
            String username = jwtUtils.getUserNameFromJwtToken(retrieve());
            return utenteRepository.findByUsername(username).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    public void invalidate() {
        httpSession.invalidate();
    }

    public String getTokenSessionKey() {
        return tokenSessionKey;
    }
}
