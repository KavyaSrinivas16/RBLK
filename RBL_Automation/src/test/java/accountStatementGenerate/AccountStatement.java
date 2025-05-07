package accountStatementGenerate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import generic_Utilities.BaseClass;
import pom.LoginPage;

public class AccountStatement extends BaseClass {

	private String payoutFileName;
	private String collectFileName;
	private String timeToDownloadStmt = "06:00";
	private long statmentCheckWatingTime = 3l;
	
	@Test
	public void downloadCollect_PayoutStmt() throws Throwable {
		LoginPage lp = new LoginPage(driver);

		wlib.waitForPageToLoad(driver);
		String URL = fLib.getDataFromPropertiesFile("url");
		driver.get(URL);
		driver.manage().window().maximize();

		lp.getLoginBtn().click();

		lp.getcorporateBankingOption().click();
		Thread.sleep(2000);

		// Switching to child browser
		String parentAddress = driver.getWindowHandle();
		Set<String> allTabsAddress = driver.getWindowHandles();

		for (String tabAddress : allTabsAddress) {

			if (!tabAddress.equals(parentAddress)) {
				driver.switchTo().window(tabAddress);
				break;
			}
		}
		
		while(true) {
			
			LocalDateTime systemDate =  LocalDateTime.now();
			DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
			
			//CurrentTime
			LocalTime currentTime = LocalTime.parse(systemDate.format(timeFormat),timeFormat);
			
			//Parsing current date to previous one day to fetch the statement
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String stmtDate = systemDate.minusDays(1).format(dateFormatter);
			String lastStmtFetchDate = fLib.getDataFromPropertiesFile("stmtDownloaded");
			
			LocalDate lastFetcteDate = LocalDate.parse(lastStmtFetchDate,dateFormatter);
			LocalDate crrentDate = LocalDate.parse(systemDate.format(dateFormatter),dateFormatter);
			LocalTime executeTime = LocalTime.parse(timeToDownloadStmt,timeFormat);
			
			if(lastFetcteDate.isBefore(crrentDate) && currentTime.isAfter(executeTime)) {
				GenerateAccountStatement(lp,lastStmtFetchDate);	
			}else if(lastFetcteDate.isEqual(crrentDate)) {
				System.out.println("Sleeping for 1 hour, current time: "+currentTime.format(timeFormat));
				TimeUnit.HOURS.sleep(1);
			}
		}
		
	}

	public void GenerateAccountStatement(LoginPage lp,String stmtDate) throws Throwable {

		String stmtFetchDate = stmtDate;

		String collectAccountNo = "409002366181";
		String payoutAccountNo = "409002362954";

		String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String previouDate = LocalDate.parse(stmtFetchDate, dateFormatter).minusDays(1).format(dateFormatter);

		// Formating dates to check the file name
		DateTimeFormatter dateFromatterAccToFileName = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String fileFromDate = LocalDate.parse(previouDate, dateFormatter).format(dateFromatterAccToFileName);
		String fileToDate = LocalDate.parse(stmtFetchDate, dateFormatter).format(dateFromatterAccToFileName);
		
		String USERID = fLib.getDataFromPropertiesFile("userid");

		lp.getUserIdEdt().sendKeys(USERID);

		lp.getsubmitBtnInUserId().click();

		lp.getconfirmCheckbox().click();

		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		lp.getpasswordEdt().click();
		lp.getpasswordEdt().sendKeys(PASSWORD);

		lp.getloginBtnAftherPassword().click();
		Thread.sleep(2000);

		lp.getaccountBtn().click();
		Thread.sleep(2000);

		lp.getaccountStatementoption().click();
		Thread.sleep(2000);

		for (int stmtDownload = 1; stmtDownload <= 2; stmtDownload++) {

			if (stmtDownload == 1) {

				lp.getaccountNum3().click();
				Thread.sleep(5000);

				lp.getadvanceSearchForAccountNum3().click();

			} else {
				lp.getPayoutAccountNum().click();
				Thread.sleep(5000);

				lp.getadvanceSearchForPayout().click();
			}

			lp.gettransactionDateRadioBtn().click();

			lp.getfromDate().click();

			// From Calendar Actions

			// To select the year in the From Calendar
			String fromYear = previouDate.split("-")[0].trim();

			lp.getselectYearinFromCal().click();

			Select selectYear = new Select(lp.getselectYearinFromCal());
			selectYear.selectByVisibleText(fromYear);

			// To select the month in the From Calendar
			lp.getselectMonthDDinFromCal().click();

			int monthIndex = previouDate.split("-")[1].startsWith("0")
					? Integer.parseInt(previouDate.split("-")[1].substring(1))
					: Integer.parseInt(previouDate.split("-")[1]);
			monthIndex--;
			String monthInAlpha = months[monthIndex];

			Select selectMonth = new Select(lp.getselectMonthDDinFromCal());
			selectMonth.selectByVisibleText(monthInAlpha);

			// To select the date in the from Calendar
			for (int fromDateRows = 0; fromDateRows < lp.getDatesInFromCalasRows().size(); fromDateRows++) {
				List<WebElement> datesInRow = lp.getDatesInFromCalasRows().get(fromDateRows)
						.findElements(By.tagName("td"));

				for (int dateIndex = 0; dateIndex < datesInRow.size(); dateIndex++) {
					String datesInCal = datesInRow.get(dateIndex).getText().trim();
					String date = previouDate.split("-")[2].startsWith("0") ? previouDate.split("-")[2].substring(1)
							: previouDate.split("-")[2];

					if (datesInCal.equals(date)) {
						datesInRow.get(dateIndex).click();
						break;
					}

				}
			}

			// To Calendar Actions
			Thread.sleep(2000);
			lp.gettoDate().click();

			// To select the year in the From Calendar
			String toYear = stmtFetchDate.split("-")[0].trim();

			lp.getselectYearinToCal().click();

			Select selectToYear = new Select(lp.getselectYearinToCal());
			selectToYear.selectByVisibleText(toYear);

			// To select the month in the From Calendar
			lp.getselectMonthDDinTocal().click();

			int toMonthIndex = stmtFetchDate.split("-")[1].startsWith("0")
					? Integer.parseInt(stmtFetchDate.split("-")[1].substring(1))
					: Integer.parseInt(stmtFetchDate.split("-")[1]);
			toMonthIndex--;
			String toMonthInAlp = months[toMonthIndex];

			Select selectToMonth = new Select(lp.getselectMonthDDinTocal());
			selectToMonth.selectByVisibleText(toMonthInAlp);

			// To select the date in the from Calendar
			for (int fromDateRows = 0; fromDateRows < lp.getDatesInToCalasRows().size(); fromDateRows++) {
				List<WebElement> datesInRow = lp.getDatesInToCalasRows().get(fromDateRows)
						.findElements(By.tagName("td"));

				for (int dateIndex = 0; dateIndex < datesInRow.size(); dateIndex++) {
					String datesInCal = datesInRow.get(dateIndex).getText().trim();
					String date = stmtFetchDate.split("-")[2].startsWith("0") ? stmtFetchDate.split("-")[2].substring(1)
							: stmtFetchDate.split("-")[2];

					if (datesInCal.equals(date)) {
						datesInRow.get(dateIndex).click();
						break;
					}

				}
			}

			wlib.scrollToElement(driver, lp.gettoDate());
			Thread.sleep(2000);
			lp.getStmtSearchBtn().click();

			Thread.sleep(2000);
			lp.getaccountBtn().click();
			Thread.sleep(2000);

			lp.getaccountStatementoption().click();
			Thread.sleep(2000);

		}

		Thread.sleep(2000);
		lp.getlogoutBtn().click();
		lp.getlogoutConfirmationBtn().click();
		lp.getGoToLoginPageBtn().click();

		TimeUnit.MINUTES.sleep(statmentCheckWatingTime);

		// Post Script to Validate the Statement got downloaded or not
		lp.getUserIdEdt().sendKeys(USERID);
		lp.getsubmitBtnInUserId().click();

		lp.getconfirmCheckbox().click();

		lp.getpasswordEdt().click();
		lp.getpasswordEdt().sendKeys(PASSWORD);

		lp.getloginBtnAftherPassword().click();
		Thread.sleep(2000);

		lp.getaccountBtn().click();
		Thread.sleep(2000);

		lp.getdownloadCenterOption().click();
		Thread.sleep(2000);

		boolean collectFlag = false;
		boolean payoutFlag = false;
		for (int stmtsIndex = 0; stmtsIndex < lp.getStatementList().size(); stmtsIndex++) {
			List<WebElement> statement = lp.getStatementList().get(stmtsIndex).findElement(By.tagName("tr"))
					.findElements(By.tagName("td"));

			for (int statementIndex = 0; statementIndex < statement.size(); statementIndex++) {
				String fileFormat = statement.get(statementIndex).findElement(By.tagName("h1")).getText();

				if (fileFormat.equals("XLS")) {
					String fileName = statement.get(statementIndex).findElement(By.tagName("span")).getText();
					String statment_num = fileName.split("_")[2];

					if (fileName.contains(fileFromDate) && fileName.contains(fileToDate)
							&& fileName.contains(collectAccountNo)) {
						statement.get(statementIndex).findElement(By.tagName("span")).findElement(By.tagName("a"))
								.click();

						collectFileName = "MYGRU0317.ANURAGSH_409002366181_" + statment_num + ".csv";
						collectFlag=true;
					}
					if (fileName.contains(fileFromDate) && fileName.contains(fileToDate)
							&& fileName.contains(payoutAccountNo)) {
						statement.get(statementIndex).findElement(By.tagName("span")).findElement(By.tagName("a"))
								.click();
						payoutFileName = "MYGRU0317.ANURAGSH_409002362954_" + statment_num + ".csv";
						payoutFlag=true;
					}

				}
			}
		}

		Thread.sleep(2000);
		lp.getlogoutBtn().click();
		lp.getlogoutConfirmationBtn().click();
		lp.getGoToLoginPageBtn().click();

		if(collectFlag&&payoutFlag) {
			System.out.println("Downloaded Stement for "+stmtFetchDate+", Below are the file details");
			System.out.println("Collect File Name --> "+collectFileName);
			System.out.println("Payout File Name --> "+payoutFileName);
			System.out.println("--------------------------------------------------------------");
			String nextStmtDownloadDate = LocalDate.parse(stmtFetchDate, dateFormatter).plusDays(1).format(dateFormatter);
			fLib.writeToProperties("stmtDownloaded",nextStmtDownloadDate );			
		}
	}


}
