from selenium import webdriver

#### Chrome headless 설정 ####
options = webdriver.ChromeOptions()
options.headless = True
options.add_argument("window-size=3072x1920")

browser = webdriver.Chrome('./chromedriver.exe', options=options)
browser.maximize_window()

# 페이지 이동
url = "https://play.google.com/store/movies/top"
browser.get(url)

import time

interval = 2  # 2초에 한 번씩 스크롤 내림

# 현재 문서 높이를 가져와서 저장
prev_height = browser.execute_script("return document.body.scrollHeight")

# 반복 수행
while True:
    # 스크롤을 가장 아래로 내림
    browser.execute_script("window.scrollTo(0, document.body.scrollHeight)")

    # 페이지 로딩 대기
    time.sleep(interval)

    # 현재 문서 높이를 가져와서 저장
    current_height = browser.execute_script(
        "return document.body.scrollHeight")
    if current_height == prev_height:
        break

    prev_height = current_height

print("스크롤 완료")
#### headless상태에서 크롬 작동 스크린샷 찍어서 확인하기 ####
browser.get_screenshot_as_file("google_move.png")

import requests
from bs4 import BeautifulSoup

# selenium을 통해 페이지 소스 가져오기
soup = BeautifulSoup(browser.page_source, "lxml")

movies = soup.find_all("div", attrs={"class": "Vpfmgd"})
print(len(movies))

for movie in movies:
    title = movie.find("div", attrs={"class": "WsMG1c nnK0zc"}).get_text()
    # print(title)

    # 할인 전 가격
    original_price = movie.find("span", attrs={"class": "SUZt4c djCuy"})
    if original_price:
        original_price = original_price.get_text()
    else:
        # print(title, "<할인되지 않은 영화는 제외합니다.>")
        continue

    # 할인된 가격
    sale_price = movie.find("span", attrs={
        "class": "VfPpfd ZdBevf i5DZme"
    }).get_text()

    # 링크
    link = movie.find("a", attrs={"class": "JC71ub"})["href"]
    # https://play.google.com + link

    print(f"제목 : {title}")
    print(f"할인 전 금액 : {original_price}")
    print(f"할인 후 금액 : {sale_price}")
    print("링크 : ", "https://play.google.com" + link)
    print("-" * 100)

browser.quit()
