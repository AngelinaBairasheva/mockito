package mockito;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by SDS on 04.03.2016.
 */
public class PaymentServiceTest {

    @Test
    public void testSendPayment() throws Exception {
        PaymentService service = new PaymentService();

        Account account1 = new Account();
        account1.setName("John");
        account1.setMail("John.Smith@gmail.com");
        account1.setBalance(100);
        Account account2 = new Account();
        account2.setName("Mike");
        account2.setMail("Mike.Tester@gmail.com");
        account2.setBalance(100);


        //TODO: assert there was request to DAO
        //TODO: assert the mail was sent
        service.setPaymentDAOImpl(new ClassToTestWithMocks3.StubPaymentDAO());
        service.sendPayment(account1, account2, 100);
        Assert.assertEquals(0, account1.getBalance());
        Assert.assertEquals(200, account2.getBalance());
    }

    @Test
    public void testSendNonExistingPayment() throws Exception {
        PaymentService service = new PaymentService();

        Account account1 = new Account();
        account1.setName("John");
        account1.setMail("John.Smith@gmail.com");
        account1.setBalance(100);
        Account account2 = new Account();
        account2.setName("Mike");
        account2.setMail("Mike.Tester@gmail.com");
        account2.setBalance(100);


        //TODO: assert mail was sent anyway

        service.setPaymentDAOImpl(new ClassToTestWithMocks3.StubPaymentDAO());
        // account1 doesn't have such amount: 500
        service.sendPayment(account1, account2, 500);
        Assert.assertEquals(100, account1.getBalance());
        Assert.assertEquals(100, account2.getBalance());
    }
}