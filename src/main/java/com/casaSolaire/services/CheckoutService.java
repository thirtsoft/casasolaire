package com.casaSolaire.services;

import com.casaSolaire.dto.checkout.Purchase;
import com.casaSolaire.dto.checkout.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeToOrder(Purchase purchase);

    PurchaseResponse placeToOrderWithUser(Purchase purchase);

}
