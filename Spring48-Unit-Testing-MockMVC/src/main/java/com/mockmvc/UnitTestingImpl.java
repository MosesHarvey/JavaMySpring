package com.mockmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Arrays;

public class UnitTestingImpl {



     DataRepository dataRepository;

    public UnitTestingImpl() {

    }

    public UnitTestingImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public int calculateSum(int[] data){
        int sum= 0;
        return Arrays.stream(data).sum();
    }

    public int calculateSumUsingDataService(){
        int sum= 0;
        return Arrays.stream(dataRepository.findAll()).sum();
    }
}
