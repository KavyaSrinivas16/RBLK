package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_Utilities.WebDriverUtility;

public class LoginPage extends WebDriverUtility {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()='Login']")
	private WebElement LoginBtn;

	public WebElement getLoginBtn() {
		return LoginBtn;
	}

	@FindBy(xpath = "//a[@id='header-login-cb' and @aria-label='Corporate Banking']")
	private WebElement corporateBankingOption;

	public WebElement getcorporateBankingOption() {
		return corporateBankingOption;
	}

	@FindBy(xpath = "//input[@name='AuthenticationFG.CUSTOM_USER_PRINCIPAL']")
	private WebElement UserIdEdt;

	public WebElement getUserIdEdt() {
		return UserIdEdt;
	}
	
	@FindBy(xpath = "//input[@type='Submit']")
	private WebElement submitBtnInUserId;

	public WebElement getsubmitBtnInUserId() {
		return submitBtnInUserId;
	}

	@FindBy(className = "waves-button-input")
	private WebElement UserIdNextBtn;

	public WebElement getUserIdNextBtn() {
		return UserIdNextBtn;
	}

	@FindBy(className = "span-checkbox")
	private WebElement confirmCheckbox;

	public WebElement getconfirmCheckbox() {
		return confirmCheckbox;
	}

	@FindBy(xpath = "//input[@name='AuthenticationFG.ACCESS_CODE']")
	private WebElement passwordEdt;

	public WebElement getpasswordEdt() {
		return passwordEdt;
	}

	// input[@value='Login']

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginBtnAftherPassword;

	public WebElement getloginBtnAftherPassword() {
		return loginBtnAftherPassword;
	}

	// ul[@id='flexiselDemo4']/li[@id='parent_CACTS']/a[text()='Accounts']

	@FindBy(xpath = "//ul[@id='flexiselDemo4']/li[@id='parent_CACTS']/a[text()='Accounts']")
	private WebElement accountBtn;

	public WebElement getaccountBtn() {
		return accountBtn;
	}

	// ul[@id='flexiselDemo4']/li[@id='parent_CACTS']/a[text()='Accounts']/following-sibling::ul/li[@id='IL_CACTS_20']/a[@id='Account-Statement_Account-Statement']

	@FindBy(xpath = "//ul[@id='flexiselDemo4']/li[@id='parent_CACTS']/a[text()='Accounts']/following-sibling::ul/li[@id='IL_CACTS_20']/a[@id='Account-Statement_Account-Statement']")
	private WebElement accountStatementoption;

	public WebElement getaccountStatementoption() {
		return accountStatementoption;
	}

	// table/tbody[6]/tr[1]/td[1]

	@FindBy(xpath = "//table/tbody[6]/tr[1]/td[1]")
	private WebElement accountNum3;

	public WebElement getaccountNum3() {
		return accountNum3;
	}

	// span[text()='Advance Search for 409002366181']
	@FindBy(xpath = "//span[text()='Advance Search for 409002366181']")
	private WebElement advanceSearchForAccountNum3;

	public WebElement getadvanceSearchForAccountNum3() {
		return advanceSearchForAccountNum3;
	}
	
	
	// Below elements are for payout account
	@FindBy(xpath = "//table/tbody[4]/tr[1]/td[1]")
	private WebElement payoutAccountNum;

	public WebElement getPayoutAccountNum() {
		return payoutAccountNum;
	}

	// span[text()='Advance Search for 409002366181']
	@FindBy(xpath = "//span[text()='Advance Search for 409002362954']")
	private WebElement advanceSearchForPayout;

	public WebElement getadvanceSearchForPayout() {
		return advanceSearchForPayout;
	}
	

	// input[@id='PageConfigurationMaster_CXACBSW__1:txnDateRadioButton']/following-sibling::span[@class='span-radiobutton']

	@FindBy(xpath = "//input[@id='PageConfigurationMaster_CXACBSW__1:txnDateRadioButton']/following-sibling::span[@class='span-radiobutton']")
	private WebElement transactionDateRadioBtn;

	public WebElement gettransactionDateRadioBtn() {
		return transactionDateRadioBtn;
	}

	// input[@name='TransactionHistoryFG.FROM_TXN_DATE_submit']/following-sibling::span[@class='picker-icon']

	@FindBy(xpath = "//input[@name='TransactionHistoryFG.FROM_TXN_DATE_submit']/following-sibling::span[@class='picker-icon']")
	private WebElement fromDate;

	public WebElement getfromDate() {
		return fromDate;
	}

	// input[@name='TransactionHistoryFG.TO_TXN_DATE_submit']/following-sibling::span[@class='picker-icon']

	@FindBy(xpath = "//input[@name='TransactionHistoryFG.TO_TXN_DATE_submit']/following-sibling::span[@class='picker-icon']")
	private WebElement toDate;

	public WebElement gettoDate() {
		return toDate;
	}

	// input[@value='Search']

	@FindBy(xpath = "//input[@value='Search']")
	private WebElement searchBtn;

	public WebElement getsearchBtn() {
		return searchBtn;
	}

	// ul[@id='flexiselDemo4']/li[@id='parent_CACTS']/a[text()='Accounts']/following-sibling::ul/li[@id='IL_CACTS_60']/a[@id='Download-Center_Download-Center']

	@FindBy(xpath = "//ul[@id='flexiselDemo4']/li[@id='parent_CACTS']/a[text()='Accounts']/following-sibling::ul/li[@id='IL_CACTS_60']/a[@id='Download-Center_Download-Center']")
	private WebElement downloadCenterOption;

	public WebElement getdownloadCenterOption() {
		return downloadCenterOption;
	}

	// Following are the calendar pop WebElement

	// FromDate Calendar to select month
	@FindBy(xpath = "(//select[@class='picker__select--month browser-default'])[1]")
	private WebElement selectMonthDDinFromCal;

	public WebElement getselectMonthDDinFromCal() {
		return selectMonthDDinFromCal;
	}

	// ToDate Calendar to select month
	@FindBy(xpath = "(//select[@class='picker__select--month browser-default'])[2]")
	private WebElement selectMonthDDinTocal;

	public WebElement getselectMonthDDinTocal() {
		return selectMonthDDinTocal;
	}

	// FromDate Calendar to select the year
	@FindBy(xpath = "(//select[@class='picker__select--year browser-default'])[1]")
	private WebElement selectYearinFromCal;

	public WebElement getselectYearinFromCal() {
		return selectYearinFromCal;
	}

	// ToDate Calendar to select the year
	@FindBy(xpath = "(//select[@class='picker__select--year browser-default'])[2]")
	private WebElement selectYearinToCal;

	public WebElement getselectYearinToCal() {
		return selectYearinToCal;
	}

	// FromDate Calendar to select the date
	@FindBy(xpath = "(//tbody)[1]/tr")
	private List<WebElement> fromDateRows;

	public List<WebElement> getDatesInFromCalasRows() {
		return fromDateRows;
	}

	// ToDate Calendar to select the date
	@FindBy(xpath = "(//tbody)[2]/tr")
	private List<WebElement> toDateRows;

	public List<WebElement> getDatesInToCalasRows() {
		return toDateRows;
	}

	@FindBy(xpath = "//input[@type='Submit' and @value = 'Search']")
	private WebElement stmtSearchBtn;

	public WebElement getStmtSearchBtn() {
		return stmtSearchBtn;
	}

//	public void loginToCorporateBanking(String url, String userid, String password) {
//		waitForPageToLoad(driver);
////		driver.get(url);
//		driver.manage().window().maximize();
//		getLoginBtn().click();
//		getcorporateBankingOption().click();
//		UserIdEdt.sendKeys(userid);
//		getconfirmCheckbox().click();
//		passwordEdt.sendKeys(password);
//		loginBtnAftherPassword.click();
//      }
	
	@FindBy(xpath = "//a[@class='icon-logout headerLogoutLink']")
	private WebElement logoutBtn;

	public WebElement getlogoutBtn() {
		return logoutBtn;
	}
	
	
	
	@FindBy(id = "span_LOG_OUT")
	private WebElement logoutConfirmationBtn;

	public WebElement getlogoutConfirmationBtn() {
		return logoutConfirmationBtn;
	}
	
	@FindBy(xpath = "//a[text()='Go to Login Page']")
	private WebElement goToLoginPageBtn;
	
	public WebElement getGoToLoginPageBtn() {
		return goToLoginPageBtn;
	}
	
	// Downloaded Statement list 
	@FindBy(xpath = "//tbody")
	private List<WebElement> statementList;
	
	public List<WebElement> getStatementList(){
		return statementList;
	}
	

}
