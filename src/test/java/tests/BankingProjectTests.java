package tests;

import data.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.BankingPage;

@DisplayName("Тесты банковского проекта")
public class BankingProjectTests extends TestBase {

    BankingPage bankingPage = new BankingPage();

    @ParameterizedTest
    @DisplayName("Успешная авторизация пользователя в системе")
    @ValueSource(strings = {
            "Hermoine Granger",
            "Harry Potter",
            "Ron Weasly"
    })
    void customerSuccessLoginTest(String searchQuery) {
        bankingPage.openCustomerPage()
                .selectCustomer(searchQuery)
                .login()
                .checkCustomer(searchQuery);
    }

    @ParameterizedTest
    @DisplayName("Успешное пополнение счета пользователя")
    @EnumSource(Account.class)
    void customerAccountFundingTest(Account account) {
        bankingPage.openCustomerPage()
                .selectCustomer(account.getCustomerName())
                .login()
                .accountSelect(account.getAccountNumber())
                .checkCurrency(account.getCurrency())
                .checkBalance(account.getCurrentAmount())
                .chooseDepositMenu()
                .checkDepositFormVisible()
                .setDeposit(account.getIncreaseAmount())
                .applyDeposit()
                .checkSuccessDeposit()
                .checkBalance(account.getFinalAmount());
    }

    @ParameterizedTest
    @DisplayName("Открытие счёта нового пользователя Менеджером")
    @CsvFileSource(resources = "/openAccountByManagerTest.csv", delimiter = ',')
    void openAccountByManagerTest(
            String customerFirstName,
            String customerLastName,
            String customerPostCode,
            String customerAccountNumber,
            String customerCurrency) {
        bankingPage.openManagerPage()
                .chooseAddCustomerMenu()
                .setFirstName(customerFirstName)
                .setLastName(customerLastName)
                .setPostCode(customerPostCode)
                .addCustomer()
                .checkSuccessAddingCustomer()
                .chooseOpenAccountMenu()
                .selectCustomer(customerFirstName + " " + customerLastName)
                .selectCurrency(customerCurrency)
                .addAccount()
                .checkSuccessAddingAccount(customerAccountNumber)
                .chooseCustomersMenu()
                .sortCustomersTable()
                .checkCustomersTable(customerFirstName, customerLastName, customerPostCode, customerAccountNumber);
    }
}
