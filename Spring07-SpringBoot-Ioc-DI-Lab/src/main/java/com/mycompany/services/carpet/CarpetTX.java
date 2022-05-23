package com.mycompany.services.carpet;

import com.mycompany.enums.City;
import com.mycompany.interfaces.carpetPrices.Carpet;

import java.math.BigDecimal;

public class CarpetTX implements Carpet {
    @Override
    public BigDecimal getSqFtPrice(City city) {
        return null;
    }
}
