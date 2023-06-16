package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.Coupon;
import exm.sisinf.webpdm.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService {

    @Autowired
    CouponRepository couponRepository;

    //CREATE

    public Coupon createCoupon(Coupon coupon){
        return couponRepository.save(coupon);
    }

    // READ

    // TODO: FINIRE


}
