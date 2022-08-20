package com.mycompany.interfaces.carpetPrices;

import com.mycompany.enums.City;

import java.math.BigDecimal;

public interface Carpet {

    BigDecimal getSqFtPrice(City city);

}
