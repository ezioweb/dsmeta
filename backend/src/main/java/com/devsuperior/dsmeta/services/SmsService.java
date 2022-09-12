package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;

    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;
    @Autowired
    private SaleRepository saleRepository;

    public void sendSms(Long saleId) {
        Sale sale = saleRepository.findById(saleId).get();
        String date = sale.getDate().getDayOfMonth()+"/"+sale.getDate().getMonthValue()+"/"+sale.getDate().getYear();
        //String msg = "O vendedor " + sale.getSellerName() + " foi destaque em "+date + " com o total de R$ "+String.format("%.2f", sale.getAmount());
        //String msg = String.format("O vendedor %s foi destaque em %s com um total de R$ %.2f", sale.getSellerName(), date, sale.getAmount());
        String msg = "Vendedor " + sale.getSellerName()+ " vendeu R$ " + String.format("%.2f",sale.getAmount()) + " em " + date;

        Twilio.init(twilioSid, twilioKey);

        PhoneNumber to = new PhoneNumber(twilioPhoneTo);
        PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

        Message message = Message.creator(to, from, "teste").create();

        System.out.println(message.getSid());
    }
}
