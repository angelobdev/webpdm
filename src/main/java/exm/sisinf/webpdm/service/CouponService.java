package exm.sisinf.webpdm.service;

import exm.sisinf.webpdm.model.Coupon;
import exm.sisinf.webpdm.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    //CREATE

    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    // READ

    public Coupon getCoupon(Integer id) {
        return couponRepository.findById(id).orElse(null);
    }

    public List<Coupon> getAllCoupon() {
        return couponRepository.findAll();
    }

    // UPDATE

    public Coupon updateCoupon(Integer id, Coupon coupon) {
        Coupon toUpdate = couponRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            toUpdate.setCodiceSconto(coupon.getCodiceSconto());
            toUpdate.setScontoApplicato(coupon.getScontoApplicato());
            toUpdate.setDataScadenza(coupon.getDataScadenza());
            toUpdate.setPrezzoMinimo(coupon.getPrezzoMinimo());
        }
        return toUpdate;
    }

    // DELETE

    public void deleteCoupon(Integer id) {
        couponRepository.deleteById(id);
    }

}
