package mockito;

import java.sql.SQLException;

/**
 * Created by SDS on 04.03.2016.
 */
public interface PaymentDAO {

    void transferPayment(final Account from, final Account to,
                         final int amount) throws SQLException,
            ClassNotFoundException;

    void updateAccounts(Account from, Account to, int amount) throws Exception;
}
