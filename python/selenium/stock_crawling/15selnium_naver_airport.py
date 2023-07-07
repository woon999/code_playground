from selenium import webdriver

## 로딩시간 처리 by, webdriverwait, ec
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

browser = webdriver.Chrome("./chromedriver.exe")
browser.maximize_window()  # 창 최대화

url = "https://flight.naver.com/flights/"
browser.get(url)

# '가는날 선택' 클릭
browser.find_element_by_link_text("가는날 선택").click()

# 이번달 29일, 30일 선택
browser.find_elements_by_link_text("29")[0].click()  # [0] -> 이번달
browser.find_elements_by_link_text("3")[1].click()  # [1] -> 다음달

# 제주도 선택
browser.find_element_by_xpath("//*[@id='recommendationList']/ul/li[1]").click()

# 항공권 검색 클릭
browser.find_element_by_link_text("항공권 검색").click()

## 로딩 시간 처리
# 1. time.sleep(random.uniform(1,3)): 로딩시간이 정해져있지 않아서 정확한 시간을 세우지 못함

# ❄︎❄︎❄︎ 2. elem이 나올 때 까지 기다리라고 설정 ❄︎❄︎❄︎
# 최대 10초 동안 Xpath 값이 나올 때까지 webdrirver 기다림
try:
    elem = WebDriverWait(browser, 10).until(
        EC.presence_of_all_elements_located(
            (By.XPATH, "//*[@id='content']/div[2]/div/div[4]/ul/li[1]")))
    # 성공했을 때 동작 수행
    for el in elem:
        print(el.text)
finally:
    browser.quit()

# 첫 번째 결과 출력
# elem = browser.find_element_by_xpath('//*[@id="content"]/div[2]/div/div[4]/ul/li[1]')
# print(elem.text)