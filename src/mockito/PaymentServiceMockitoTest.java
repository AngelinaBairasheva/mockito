package mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * Created by SDS on 04.03.2016.
 */
@PrepareForTest(PaymentService.class)
@RunWith(PowerMockRunner.class)
public class PaymentServiceMockitoTest {
    public static class StubPaymentDAO implements PaymentDAO {

        @Override
        public void transferPayment(Account from, Account to, int amount) throws SQLException, ClassNotFoundException {
            System.out.println("Hello world");
        }

        @Override
        public void updateAccounts(Account from, Account to, int amount) throws Exception {
            System.out.println("updateAccounts");
        }
    }
    @Test
    public void testPayment() throws Exception {

        PaymentService service = new PaymentService();

        URL urlMock= PowerMockito.mock(URL.class);
        whenNew(URL.class).withAnyArguments().thenReturn(urlMock);

        HttpURLConnection urlConnectionMock=PowerMockito.mock(HttpURLConnection.class);
                when(urlConnectionMock.getResponseCode()).thenReturn(200);
        when(urlMock.openConnection()).thenReturn(urlConnectionMock);
        //System.out.println(urlConnectionMock.getResponseCode());


        Account account1 = new Account();
        account1.setName("John");
        account1.setMail("John.Smith@gmail.com");
        account1.setBalance(100);
        Account account2 = new Account();
        account2.setName("Mike");
        account2.setMail("Mike.Tester@gmail.com");
        account2.setBalance(100);



        service.setPaymentDAOImpl(new StubPaymentDAO());
        service.sendPayment(account1, account2, 100);
        //Assert.assertEquals(0, account1.getBalance());
        //Assert.assertEquals(200, account2.getBalance());

    }
@Test(expected = IllegalStateException.class)
    public void testM() throws Exception {

    PaymentService service = new PaymentService();

    URL urlMock= PowerMockito.mock(URL.class);
    whenNew(URL.class).withAnyArguments().thenReturn(urlMock);

    HttpURLConnection urlConnectionMock=PowerMockito.mock(HttpURLConnection.class);
    when(urlConnectionMock.getResponseCode()).thenReturn(505);
    when(urlMock.openConnection()).thenReturn(urlConnectionMock);


    Account account1 = new Account();
    account1.setName("John");
    account1.setMail("John.Smith@gmail.com");
    account1.setBalance(100);
    Account account2 = new Account();
    account2.setName("Mike");
    account2.setMail("Mike.Tester@gmail.com");
    account2.setBalance(100);



    service.setPaymentDAOImpl(new StubPaymentDAO());
    service.sendPayment(account1, account2, 100);
}
    @Test
    public void test3() throws Exception {

            PaymentService service = new PaymentService();

            URL urlMock= PowerMockito.mock(URL.class);
            whenNew(URL.class).withAnyArguments().thenReturn(urlMock);

            HttpURLConnection urlConnectionMock=PowerMockito.mock(HttpURLConnection.class);
            when(urlConnectionMock.getResponseCode()).thenReturn(200);
            when(urlMock.openConnection()).thenReturn(urlConnectionMock);


            Account account1 = new Account();
            account1.setName("John");
            account1.setMail("John.Smith@gmail.com");
            account1.setBalance(100);
            Account account2 = new Account();
            account2.setName("Mike");
            account2.setMail("Mike.Tester@gmail.com");
            account2.setBalance(100);



            service.setPaymentDAOImpl(new StubPaymentDAO());
            service.sendPayment(account1, account2, 100);
        Mockito.verify(urlConnectionMock, Mockito.atLeast(2)).getResponseCode();
    }

}
