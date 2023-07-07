from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import requests
from bs4 import BeautifulSoup
import time

interval = 2
#### Chrome headless 설정 ####
options = webdriver.ChromeOptions()
options.headless = True
options.add_argument("window-size=3072x1920")
options.add_argument(
    "user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36"
)

browser = webdriver.Chrome('./chromedriver.exe', options=options)
# browser.maximize_window()

url = "https://www.daum.net/"
browser.get(url)

elem = browser.find_element_by_id("q")
elem.send_keys("송파 헬리오시티")
elem.send_keys(Keys.ENTER)

# 검색시간 로딩처리
time.sleep(interval)

soup = BeautifulSoup(browser.page_source, "lxml")

# with open("real.html", "w", encoding="utf8") as f:
#     f.write(soup.prettify())  # html문서 예쁘게 출력

## 매물 정보 확인
infos = soup.find("table", attrs={"class": "tbl"}).find("tbody").find_all("tr")

for idx, product in enumerate(infos):
    columns = product.find_all("td")
    print(f"============== 매물 {idx+1} =============")
    print(f"거래 : {columns[0].get_text().strip()}")
    print(f"면적 : {columns[1].get_text().strip()} (공급/전용)")
    print(f"가격 : {columns[2].get_text().strip()} (만원)")
    print(f"층 : {columns[4].get_text().strip()}")
    print(f"동 : {columns[3].get_text().strip()}")
