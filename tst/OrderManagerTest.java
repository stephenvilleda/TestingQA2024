import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class OrderManagerTest {

    private OrderManager orderManager;
    private ProcessOrders processorMock;


    @Before
    public void setup() {
        // create a ProcessOrders mock object
        processorMock = Mockito.mock(ProcessOrders.class);

        // create orderManager instance with mock object
        orderManager = new OrderManager(processorMock);
    }

    @Test
    public void testProcessValidOrder() {
        //***
        //GIVEN - setup mock and other necessary objects
        //***
        boolean result = orderManager.processOrder(Mockito.anyInt());

        //***
        //WHEN - have the mock object do something (that you want to test)
        //***
        Assert.assertTrue(result);

        //***
        //THEN - verify expected result
        //***
        Mockito.verify(processorMock).shipOrder(Mockito.anyInt());
        Mockito.verifyNoMoreInteractions(processorMock);
    }

    @Test
    public void testProcessInvalidOrder() {
        //***
        //GIVEN - setup mock and define expected behavior
        //***
        Mockito.doThrow(new InvalidOrderException()).when(processorMock).shipOrder(666);

        //***
        //WHEN - have the mock object do something (that you want to test)
        //***
        boolean result = orderManager.processOrder(666);

        //***
        //THEN - verify expected result
        //***
        Assert.assertFalse(result);
        Mockito.verify(processorMock).shipOrder(666);
        Mockito.verifyNoMoreInteractions(processorMock);
    }


    @Test
    public void testProfitHappyCase() {
        //***
        //GIVEN - setup mock and define expected behavior
        //***
        Mockito.when(processorMock.getFraudulentOrders()).thenReturn(new ArrayList<>());

        //***
        //WHEN - have the mock object do something (that you want to test)
        //***
        orderManager.processOrder(7);
        orderManager.processOrder(77);
        orderManager.processOrder(777);

        double expectedProfit = 3*1.11;
        double actualProfit = orderManager.calculateProfit();

        //***
        //THEN - verify expected result
        //***
        Assert.assertEquals(expectedProfit, actualProfit, 0.001);
        Mockito.verify(processorMock).shipOrder(7);
        Mockito.verify(processorMock).shipOrder(77);
        Mockito.verify(processorMock).shipOrder(777);
        Mockito.verify(processorMock, Mockito.times(2)).getFraudulentOrders();
        Mockito.verifyNoMoreInteractions(processorMock);

    }


    @Test
    public void testProfitWithFrauds() {
        //***
        //GIVEN - setup mock and define expected behavior
        //***
        List<Order> invalidOrders = new ArrayList<>();
        invalidOrders.add(new Order(6));
        invalidOrders.add(new Order(66));
        invalidOrders.add(new Order(666));
        Mockito.when(processorMock.getFraudulentOrders()).thenReturn(invalidOrders);

        //***
        //WHEN - have the mock object do something (that you want to test)
        //***
        orderManager.processOrder(7);
        orderManager.processOrder(77);
        orderManager.processOrder(777);
        orderManager.processOrder(6);
        orderManager.processOrder(66);
        orderManager.processOrder(666);

        double expectedProfit = 3*1.11 - 3*1.33;
        double actualProfit = orderManager.calculateProfit();

        //***
        //THEN - verify expected result
        //***
        Assert.assertEquals(expectedProfit, actualProfit, 0.001);
        Mockito.verify(processorMock).shipOrder(7);
        Mockito.verify(processorMock).shipOrder(77);
        Mockito.verify(processorMock).shipOrder(777);
        Mockito.verify(processorMock).shipOrder(6);
        Mockito.verify(processorMock).shipOrder(66);
        Mockito.verify(processorMock).shipOrder(666);
        Mockito.verify(processorMock, Mockito.times(2)).getFraudulentOrders();
        Mockito.verifyNoMoreInteractions(processorMock);

    }

}
