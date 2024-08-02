package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Alert;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class BankingPage {
    private final SelenideElement
            userSelect = $("#userSelect"),
            loginBtn = $(byTagAndText("button", "Login")),
            customerTitle = $("div"),
            accountSelect = $("#accountSelect"),
            accountDescription = $("div.center"),
            depositMenu = $(byTagAndText("button", "Deposit")),
            depositForm = $(".form-group input"),
            depositInput = $("input[ng-model='amount']"),
            depositBtn = $("[name=myForm] button"),
            message = $(".error[ng-show=\"message\"]"),
            addCustomerMenu = $(byTagAndText("button", "Add Customer")),
            firstNameInput = $("[ng-model=\"fName\"]"),
            lastNameInput = $("[ng-model=\"lName\"]"),
            postCodeInput = $("[ng-model=\"postCd\"]"),
            addCustomerBtn = $("form button"),
            openAccountMenu = $(byTagAndText("button", "Open Account")),
            currencySelect = $("#currency"),
            processBtn = $(byTagAndText("button", "Process")),
            customersMenu = $(byTagAndText("button", "Customers")),
            postCodeCol = $(byTagAndText("a", "Post Code")),
            customersTable = $("tbody");

    public BankingPage openCustomerPage() {
        open("/#/customer");
        return this;
    }

    public BankingPage openManagerPage() {
        open("/#/manager");
        return this;
    }

    public BankingPage selectCustomer(String accountName) {
        userSelect.selectOption(accountName);
        return this;
    }

    public BankingPage login() {
        loginBtn.click();
        return this;
    }

    public BankingPage checkCustomer(String accountName) {
        customerTitle.shouldHave(text(accountName));
        return this;
    }

    public BankingPage accountSelect(String accountNumber) {
        accountSelect.selectOption(accountNumber);
        return this;
    }

    public BankingPage checkCurrency(String currency) {
        accountDescription.shouldHave(text(currency));
        return this;
    }

    public BankingPage checkBalance(int amount) {
        accountDescription.shouldHave(text("Balance : " + amount));
        return this;
    }

    public BankingPage chooseDepositMenu() {
        depositMenu.click();
        return this;
    }

    public BankingPage checkDepositFormVisible() {
        depositForm.shouldBe(visible);
        return this;
    }

    public BankingPage setDeposit(String amount) {
        depositInput.setValue(amount);
        return this;
    }

    public BankingPage applyDeposit() {
        depositBtn.click();
        return this;
    }

    public BankingPage checkSuccessDeposit() {
        message.shouldHave(text("Deposit Successful"));
        return this;
    }

    public BankingPage chooseAddCustomerMenu() {
        addCustomerMenu.click();
        return this;
    }

    public BankingPage setFirstName(String fname) {
        firstNameInput.setValue(fname);
        return this;
    }

    public BankingPage setLastName(String lname) {
        lastNameInput.setValue(lname);
        return this;
    }

    public BankingPage setPostCode(String postCode) {
        postCodeInput.setValue(postCode);
        return this;
    }

    public BankingPage addCustomer() {
        addCustomerBtn.click();
        return this;
    }

    public BankingPage checkSuccessAddingCustomer() {
        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assert (alertText.contains("Customer added successfully with customer id"));
        alert.accept();
        return this;
    }

    public BankingPage checkSuccessAddingAccount(String accountNumber) {
        Alert alertCurrency = switchTo().alert();
        String alertCurrencyText = alertCurrency.getText();
        assert (alertCurrencyText.equals("Account created successfully with account Number :" + accountNumber));
        alertCurrency.accept();
        return this;
    }

    public BankingPage chooseOpenAccountMenu() {
        openAccountMenu.click();
        return this;
    }

    public BankingPage selectCurrency(String currency) {
        currencySelect.selectOption(currency);
        return this;
    }

    public BankingPage addAccount() {
        processBtn.click();
        return this;
    }

    public BankingPage chooseCustomersMenu() {
        customersMenu.click();
        return this;
    }

    public BankingPage sortCustomersTable() {
        postCodeCol.doubleClick();
        return this;
    }

    public BankingPage checkCustomersTable(
            String fname,
            String lname,
            String postCode,
            String accountNumber) {
        customersTable.shouldHave(text(fname + " " + lname + " " + postCode + " " + accountNumber + " Delete"));
        return this;
    }
}
