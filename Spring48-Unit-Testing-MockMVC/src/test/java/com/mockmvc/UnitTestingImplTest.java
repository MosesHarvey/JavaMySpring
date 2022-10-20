package com.mockmvc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UnitTestingImplTest {

    @Mock
    DataRepository dataRepository;

    @InjectMocks
    UnitTestingImpl unitTesting;

    @Test
    void calculateSum() {

        UnitTestingImpl unitTesting = new UnitTestingImpl();
        int actual = unitTesting.calculateSum(new int[]{1,2,3});
        assertEquals(6, actual);
    }

    @Test
    void calculateSumUsingDataService(){
        DataRepositoryImpl dataRepository = new DataRepositoryImpl();
        UnitTestingImpl unitTesting = new UnitTestingImpl(dataRepository);
        int actual = unitTesting.calculateSumUsingDataService();
        assertEquals(6,actual);

    }

    @Test
    void calculateSumUsingDataServiceMock(){
        when(dataRepository.findAll()).thenReturn(new int[]{1,2,3});

        int actual = unitTesting.calculateSumUsingDataService();
        assertEquals(6,actual);

    }

    @Test
    void calculateSumUsingDataServiceWithParameter() {
        when(dataRepository.findById(2)).thenReturn(new int[]{10,10,10});
        int actual = unitTesting.calculateSumUsingDataServiceWithParameter();
        assertEquals(30,actual);

        verify(dataRepository).findById(2);

    }
}