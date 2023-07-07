from selenium import webdriver

#### Chrome headless 설정 ####
options = webdriver.ChromeOptions()
options.headless = True
options.add_argument("window-size=3072x1920")
options.add_argument(
    "user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36"
)

browser = webdriver.Chrome('./chromedriver.exe', options=options)
browser.maximize_window()

url = "https://www.whatismybrowser.com/detect/what-is-my-user-agent"
browser.get(url)

# Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)
# AppleWebKit/537.36 (KHTML, like Gecko)
# Chrome/89.0.4389.90 Safari/537.36
id = browser.find_element_by_id("detected_value")

# 원래는 Headless Chrome 뜸
# Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)
# AppleWebKit/537.36 (KHTML, like Gecko)
# HeadlessChrome/89.0.4389.90 Safari/537.36 >> Headless라고 뜸
print(id.text)  ## add_argument로 넣어주면 원래 user-agent값 나옴

browser.quit()