package com.ecompany.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class DebitAccount extends Account{
    private BigDecimal overDRaftFee;
}
