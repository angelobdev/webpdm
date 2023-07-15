package exm.sisinf.webpdm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exm.sisinf.webpdm.auth.AuthEntryPointJwt;
import exm.sisinf.webpdm.auth.AuthTokenService;
import exm.sisinf.webpdm.model.Coupon;
import exm.sisinf.webpdm.model.Ordine;
import exm.sisinf.webpdm.model.Utente;
import exm.sisinf.webpdm.service.CouponService;
import exm.sisinf.webpdm.service.OrdineService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ContentController {

    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    private AuthTokenService authTokenService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private OrdineService ordineService;

    @GetMapping("/areaclienti")
    public String areaClienti(Model model, HttpServletRequest request) throws JsonProcessingException {
        String token = authTokenService.retrieve(request);
        Utente utente = authTokenService.getUtente(token);

        model.addAttribute("utente", utente);

        String listaCouponJson = new ObjectMapper().writeValueAsString(couponService.getAllCoupon());
        model.addAttribute("couponJson", listaCouponJson);

        List<Ordine> ordiniUtente = ordineService.getAllOrdiniFromUtenteId(utente.getId());
        model.addAttribute("ordini", ordiniUtente);

        return "areaclienti";
    }

}
