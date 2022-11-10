import com.sun.source.tree.AssertTree;
import com.sun.source.tree.Tree;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlinaTest {
    /*   TC_1_1  - Тест кейс:
     * 1. Открыть страницу https://openweathermap.org/
     * 2. Набрать в строке поиска город Paris
     * 3. Нажать пункт меню Search
     * 4. Из выпадающего списка выбрать Paris, FR
     * 5. Подтвердить, что заголовок изменился на "Paris, FR"
     */

    @Test
    public void testH2TagTextWhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseURL = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(baseURL);
        Thread.sleep(10000);
        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder= 'Search city']"
                )
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        Thread.sleep(2000);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']"
                )
        );
        searchButton.click();
        Thread.sleep(1000);

        WebElement parisFRChooseInDropdownMenu = driver.findElement(By.xpath(
                        "//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']"
                )
        );
        parisFRChooseInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2"
                )
        );

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /*TC_11_01
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на пункт меню Guide
     * 3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
     *  и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap
     */

    @Test

    public void testWhenSearchingGuideMenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseURL = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(baseURL);
        Thread.sleep(10000);
        WebElement guideButton = driver.findElement(
                By.xpath("//div[@id= 'desktop-menu']//a[@href= '/guide']"
                )
        );
        guideButton.click();
        Thread.sleep(1000);

        String actualResult1 = driver.getCurrentUrl();
        String actualResult2 = driver.getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);

        driver.quit();
    }

    /* TC_11_02
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     * 3.  Подтвердить, что температура для города показана в Фарингейтах
     */

    @Test

    public void testWhenSelectTemperatureInFahrenheit() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseURL = "https://openweathermap.org/";
        String symbolFTemp = "°F";
        String expectedResult = "°F";

        driver.get(baseURL);
        Thread.sleep(10000);

        WebElement selectTempInF = driver.findElement(
                By.xpath("//div[text()='Imperial: °F, mph']"
                )
        );
        Thread.sleep(2000);
        selectTempInF.click();

        WebElement verifyImperialSystem = driver.findElement(
                By.xpath("//div[@class='current-temp']//span[@class='heading']"
                )
        );
        Thread.sleep(2000);

        String tempInF = verifyImperialSystem.getText();
        String actualResult = tempInF.substring(tempInF.length() - 2);

        Thread.sleep(1000);

        Assert.assertTrue(verifyImperialSystem.getText().contains(symbolFTemp));
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /*TC_11_03
     * 1.  Открыть базовую ссылку
     * 2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to work.
     *  We also use non-essential cookies to help us improve our services. Any data collected is anonymised.
     *  You can allow all cookies or manage them individually.”
     * 3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
     */

    @Test

    public void testCookiesBlockWithTextAndButtonsIsShown_WhenOpenTheBaseURL() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseURL = "https://openweathermap.org/";
        String textButton1 = "Allow all";
        String textButton2 = " Manage cookies ";
        String expectedResult1 = "We use cookies which are essential for the site to work. We also use non-essential"
                + " cookies to help us improve our services. Any data collected is anonymised. You can allow all "
                + "cookies or manage them individually.";

        driver.get(baseURL);
        Thread.sleep(10000);

        Assert.assertTrue(driver.findElement(
                        By.className("stick-footer-panel__container"
                        )
                ).isDisplayed()
        );

        WebElement cookiesText = driver.findElement(
                By.className("stick-footer-panel__description"
                )
        );
        String actualResult1 = cookiesText.getText();
        Assert.assertEquals(actualResult1, expectedResult1);

        Assert.assertEquals(
                driver.findElements(
                        By.xpath("//div[@class='stick-footer-panel__btn-container']/*")).size(), 2
        );

        Assert.assertTrue(
                driver.findElement(
                        By.xpath("//div[@class='stick-footer-panel__btn-container']/*[text() = "
                                + "'" + textButton1 + "']"
                        )
                ).isDisplayed()
        );

        Assert.assertTrue(
                driver.findElement(
                        By.xpath("//div[@class='stick-footer-panel__btn-container']/*[text() ="
                                + " '" + textButton2 + "']"
                        )
                ).isDisplayed()
        );

        driver.quit();
    }

    /* TC11_04
     * 1.  Открыть базовую ссылку
     * 2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”
     */

    @Test
    public void testThreeSubMenusAreDisplayed_WhenClickingSupportMenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseURL = "https://openweathermap.org/";
        String expectedSubmenu1 = "FAQ";
        String expectedSubmenu2 = "How to start";
        String expectedSubmenu3 = "Ask a question";

        driver.get(baseURL);
        driver.manage().window().maximize();
        Thread.sleep(10000);

        WebElement supportMenu = driver.findElement(By.id("support-dropdown"
                )
        );
        supportMenu.click();
        Thread.sleep(1000);

        Assert.assertEquals(
                driver.findElements(
                        By.xpath("//ul[@class= 'dropdown-menu dropdown-visible']/li")).size(), 3
        );
        Thread.sleep(2000);

        String actualSubmenu1 = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li[1]"
                )
        ).getText();
        String actualSubmenu2 = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li[2]"
                )
        ).getText();
        String actualSubmenu3 = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li[3]"
                )
        ).getText();

        Assert.assertEquals(actualSubmenu1, expectedSubmenu1);
        Assert.assertEquals(actualSubmenu2, expectedSubmenu2);
        Assert.assertEquals(actualSubmenu3, expectedSubmenu3);

        driver.quit();

    }

    /* TC_11_05
     * 1. Открыть базовую ссылку
     * 2. Нажать пункт меню Support → Ask a question
     * 3. Заполнить поля Email, Subject, Message
     * 4. Не подтвердив CAPTCHA, нажать кнопку Submit
     * 5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
     */

    @Test
    public void testConfirmReCaptchaVerificationIsFailedWhenClickSubmit() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseURL = "https://openweathermap.org/";
        String email = "tester@test.com";
        String message = "I can not find my city.";
        String expectedResult = "reCAPTCHA verification failed, please try again.";
        String originalWindow = driver.getWindowHandle();

        driver.get(baseURL);
        driver.manage().window().maximize();
        Thread.sleep(10000);

        WebElement supportMenu = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        supportMenu.click();

        WebElement dropDownAskAQuestion = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']"
                        + "/li/a[@href='https://home.openweathermap.org/questions']"
                )
        );
        dropDownAskAQuestion.click();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        WebElement emailField = driver.findElement(
                By.xpath("//input[@id='question_form_email']"
                )
        );
        emailField.click();
        emailField.sendKeys(email);
        Thread.sleep(500);

        WebElement subjectField = driver.findElement(
                By.xpath("//select[@id='question_form_subject']"
                )
        );
        subjectField.click();
        Thread.sleep(500);

        WebElement subjectDropDownMenu = driver.findElement(
                By.xpath("//option[@value='Other']"
                )
        );
        subjectDropDownMenu.click();
        Thread.sleep(500);

        WebElement messageField = driver.findElement(
                By.xpath("//textarea[@id='question_form_message']"
                )
        );
        messageField.click();
        messageField.sendKeys(message);
        Thread.sleep(500);

        WebElement submitButton = driver.findElement(
                By.xpath("//input[@value='Submit']"
                )
        );
        submitButton.click();

        WebElement captchaFailedText = driver.findElement(
                By.xpath("//div[text()='reCAPTCHA verification failed, please try again.']"
                )
        );
        Thread.sleep(500);

        String actualResult = captchaFailedText.getText();
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /* TC_11_06
     * 1.  Открыть базовую ссылку
     * 2.  Нажать пункт меню Support → Ask a question
     * 3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
     * 4. Оставить пустым поле Email
     * 5. Заполнить поля  Subject, Message
     * 6. Подтвердить CAPTCHA
     * 7. Нажать кнопку Submit
     * 8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”
     */

    @Test
    public void testEmailCheck_WhenSkipEmailField() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseURL = "https://openweathermap.org/";
        String message = "I can not find my city.";
        String expectedResult = "can't be blank";
        String originalWindow = driver.getWindowHandle();

        driver.get(baseURL);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement supportMenu = driver.findElement(
                By.xpath("//div[@id='support-dropdown']"
                )
        );
        supportMenu.click();

        WebElement dropDownAskAQuestion = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li"
                        + "/a[@href='https://home.openweathermap.org/questions']"
                )
        );
        dropDownAskAQuestion.click();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        WebElement subjectField = driver.findElement(
                By.xpath("//select[@id='question_form_subject']"
                )
        );
        subjectField.click();
        Thread.sleep(500);

        WebElement subjectDropDownMenu = driver.findElement(
                By.xpath("//option[@value='Other']"
                )
        );
        subjectDropDownMenu.click();
        Thread.sleep(500);

        WebElement messageField = driver.findElement(
                By.xpath("//textarea[@id='question_form_message']"
                )
        );
        messageField.click();
        messageField.sendKeys(message);
        Thread.sleep(500);

        String window2 = driver.getWindowHandle();
        driver.switchTo().frame(driver.findElement(
                        By.cssSelector("iframe[title = 'reCAPTCHA']"
                        )
                )
        );

        WebElement clickCaptcha = driver.findElement(
                By.xpath(
                        "//span[@class='recaptcha-checkbox goog-inline-block"
                                + " recaptcha-checkbox-unchecked rc-anchor-checkbox']"
                )
        );

        clickCaptcha.click();
        Thread.sleep(500);

        driver.switchTo().window(window2);
        WebElement submitButton = driver.findElement(
                By.xpath("//input[@value='Submit']"
                )
        );
        submitButton.click();
        Thread.sleep(500);

        WebElement confirmEmailError = driver.findElement(
                By.xpath("//span[@class='help-block']"
                )
        );
        String actualResult = confirmEmailError.getText();
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /* TC_11_07
     * 1. Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     * 3.  Нажать на единицы измерения Metric: °C, m/s
     * 4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С
     */

    @Test
    public void testWhenSelectTemperatureInFahrenheitAndCelsius() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseURL = "https://openweathermap.org/";
        String expectedResult = "°C";

        driver.get(baseURL);
        driver.manage().window().maximize();
        Thread.sleep(10000);

        WebElement clickFahrenheit = driver.findElement(
                By.xpath("//div[@class = 'option'][contains(text(),'°F')]"
                )
        );
        clickFahrenheit.click();
        Thread.sleep(2000);

        WebElement clickCelsius = driver.findElement(
                By.xpath("//div[@class= 'option'][contains(text(),'°C')]"
                )
        );
        clickCelsius.click();
        Thread.sleep(2000);

        WebElement celsius = driver.findElement(
                By.xpath("//span[@class = 'heading'][contains(text(),'°C')]"
                )
        );
        celsius.getText();

        String tempInCelsius = celsius.getText();

        String actualResult = tempInCelsius.substring(tempInCelsius.length() - 2);
        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertTrue(celsius.getText().contains(expectedResult));

        driver.quit();
    }

    /* TC_11_08
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на лого компании
     * 3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась
     */

    @Test
    public void testLinkDoesntChange_WhenRefreshPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseURL = "https://openweathermap.org/";

        driver.get(baseURL);
        driver.manage().window().maximize();
        Thread.sleep(10000);

        WebElement clickLogo = driver.findElement(
                By.className("logo"
        )
        );
        clickLogo.click();
        Thread.sleep(2000);
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, baseURL);

        driver.quit();
    }

    /* TC_11_09
     * 1.  Открыть базовую ссылку
     * 2.  В строке поиска в навигационной панели набрать “Rome”
     * 3.  Нажать клавишу Enter
     * 4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
     * 5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”
     */
    @Test
    public void testEnterInNavigationBarCityName() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String cityName = "Rome";
        String searchValue = "find";
        String searchValue2 = "Rome";
        String baseURL = "https://openweathermap.org/";

        driver.get(baseURL);
        driver.manage().window().maximize();
        Thread.sleep(10000);

        WebElement navigationBarSearch = driver.findElement(
                By.xpath("//input[@placeholder='Weather in your city']"
                )
        );
        navigationBarSearch.click();
        Thread.sleep(2000);
        navigationBarSearch.sendKeys(cityName);
        Thread.sleep(2000);
        navigationBarSearch.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        String strUrl = driver.getCurrentUrl();
        Boolean actualResultRomeAndFind = strUrl.contains(searchValue) && strUrl.contains(searchValue2);
        Assert.assertTrue(actualResultRomeAndFind);

        String actualResultSearchBar = driver.findElement(
                By.xpath("//input[@class]"
        )
        ).getAttribute("value");
        Assert.assertEquals(actualResultSearchBar, cityName);

        driver.quit();
    }

    /* TC_11_10
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на пункт меню API
     * 3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок
     */

    @Test
    public void testDesktopMenuFindThirtyButtons() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseURL = "https://openweathermap.org/";
        int expectedResult = 30;

        driver.get(baseURL);
        driver.manage().window().maximize();
        Thread.sleep(10000);

        WebElement button = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[text() = 'API']"
                )
        );
        button.click();

        int countButton = driver.findElements(
                By.xpath("//a[contains(@class,'orange')]"
                )
        ).size();

        int actualResult = countButton;
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

}
