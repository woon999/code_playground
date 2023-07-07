from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import random
import time

browser = webdriver.Chrome("./chromedriver.exe")

# 자동화탐지를 우회하기 위한 delay
time.sleep(random.uniform(1, 3))
browser.get("http://naver.com")

# 로그인 버튼 클릭
elem = browser.find_element_by_class_name("link_login")
elem.click()

# id, pw 입력
# naver captcha 탐지 우회방법 -> js로 입력 값 넣어주기
input_js = '\
        document.getElementById("id").value = "{id}"; \
        document.getElementById("pw").value = "{pw}"; \
    '.format(id="test_id", pw="test_pw")
time.sleep(random.uniform(1, 3))
# browser.find_element_by_id("id").send_keys("id")
# browser.find_element_by_id("pw").send_keys("pw")
browser.execute_script(input_js)

# 로그인 버튼 클릭
time.sleep(random.uniform(1, 3))
browser.find_element_by_id("log.login").click()

# html 정보 출력
time.sleep(random.uniform(1, 3))
print(browser.page_source)

# 브라우저 종료
time.sleep(random.uniform(1, 3))
browser.quit()