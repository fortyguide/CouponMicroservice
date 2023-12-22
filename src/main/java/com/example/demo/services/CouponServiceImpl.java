package com.example.demo.services;

import com.example.demo.daos.CouponDao;
import com.example.demo.daos.CouponInsertDao;
import com.example.demo.entities.Coupon;
import com.example.demo.entities.JsonResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService{

    @Autowired
    CouponInsertDao couponInsertDao;

    @Autowired
    CouponDao couponDao;

    @Override
    public Coupon insertCoupon(Coupon coupon) {
        return couponInsertDao.insertCoupon(coupon);
    }

    @Override
    public String getAvailableCoupons(String jwt) {
        List<LinkedHashMap> accounts = getAccountsGivenJwt(jwt);
        if(accounts != null && accounts.size() > 0){
            String availableCoupons = "";
            for(int i = 0; i < accounts.size(); i++){
                LinkedHashMap account = accounts.get(i);
                String idAccount = (String) account.get("id");
                Optional<Coupon> couponn = couponDao.findByAccount(idAccount);
                if(couponn.isPresent()){
                    availableCoupons = availableCoupons + "CouponCode " +
                            couponn.get().getCouponCode() + " " +
                            "(for Account: " + idAccount + ") ";
                }
            }
            return "Available coupons: " + availableCoupons;
        }
        return "No coupon available for the user";
    }


    /**
     * Il metodo getAccountsGivenJwt() effettua la chiamata al servizio AccountMicroservice
     * @param jwt
     * @return
     */
    private List<LinkedHashMap> getAccountsGivenJwt(String jwt){
        //Preparazione dell'intestazione della request, con l'aggiunta del jwt.
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("jwt", jwt);
        HttpEntity<?> request = new HttpEntity<>(String.class, headers);

        /*RestTemplate è una classe di Spring che si occupa di consumare (effettuare chiamate)
        * i web service REST che sono esterni.*/
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonResponseBody> responseEntity = restTemplate.exchange("http://localhost:8080/accounts/user",
                                                                                 HttpMethod.POST,
                                                                                 request,
                                                                                 JsonResponseBody.class);

        List<LinkedHashMap> accounts = (List) responseEntity.getBody().getResponse();
        return accounts;
    }
}
