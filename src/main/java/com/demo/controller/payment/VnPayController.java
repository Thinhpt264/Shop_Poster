package com.demo.controller.payment;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.entities.Item;
import com.demo.entities.Order;
import com.demo.entities.OrderDetail;
import com.demo.entities.Payment;
import com.demo.service.OrderDetailService;
import com.demo.service.OrderService;
import com.demo.service.PaymentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping({"payment"})
public class VnPayController {
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PaymentService paymentService;
	@PostMapping("/pay")
	public String payment( @RequestParam("amount") int money,
			@RequestParam("orderId") int orderId,
            @RequestParam(value = "bankCode", required = false) String bankCode,
            @RequestParam(value = "currency", defaultValue = "USD") String currency,
            @RequestParam(value = "language", required = false) String locate, HttpServletRequest request, HttpSession session) throws Exception {
		session.setAttribute("orderId", orderId);
		String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        double exchangeRate = getExchangeRate(currency, "VND");  // Tỷ giá này có thể lấy từ một API
        // Convert amount to VND (assuming amount is in USD)
        double amountInUSD = money;
        long amountInVND = (long) (amountInUSD * exchangeRate * 100);  // VNPay expects amount in smallest unit (e.g., 100 VND)
        
        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_IpAddr = Config.getIpAddress(request);

        String vnp_TmnCode = Config.vnp_TmnCode;
        
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amountInVND));
        vnp_Params.put("vnp_CurrCode", "VND");
        
        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

      
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        System.out.println(paymentUrl);
		return "redirect:" + paymentUrl;
	}
	private double getExchangeRate(String fromCurrency, String toCurrency) {
	    // Giả sử tỷ giá 1 USD = 23000 VND
	    if (fromCurrency.equalsIgnoreCase("USD") && toCurrency.equalsIgnoreCase("VND")) {
	        return 24000.0;
	    }
	    // Có thể thêm nhiều tỷ giá khác nếu cần
	    return 1.0;  // Trả về 1 nếu không tìm thấy tỷ giá
	}
	
	@GetMapping({"paymentInfo"})
	public String PaymentInfo(@RequestParam("vnp_Amount") String vnp_Amount,
			@RequestParam("vnp_ResponseCode") String vnp_ResponseCode,
			@RequestParam("vnp_TransactionNo") String vnp_TransactionNo,
			@RequestParam("vnp_PayDate") String vnp_PayDate,
			HttpSession httpSession
			) {
		int orderId = (int) httpSession.getAttribute("orderId");
		Order order = orderService.findById(orderId);
		List<Item> cart = (List<Item>) httpSession.getAttribute("cart");
		for (Item item : cart) {
			OrderDetail o = new OrderDetail();
			o.setOrder(order);
			o.setPoster(item.getPoster());
			o.setSize(item.getSelected_size());
			o.setFrame(item.getSelected_frame());
			orderDetailService.save(o);	
		}
		Payment payment = new Payment();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		Date parsedDate;
		try {
			parsedDate = dateFormat.parse(vnp_PayDate);
			payment.setOrder(order);
			payment.setCreated(parsedDate);
			payment.setQR(null);
			payment.setTransactionNo(vnp_TransactionNo);
			payment.setStatus(Integer.parseInt(vnp_ResponseCode));
			payment.setPrice(Integer.parseInt(vnp_Amount)/100);
			paymentService.save(payment);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "redirect:/checkout/checkoutsuccess";
		
	}
	
}
