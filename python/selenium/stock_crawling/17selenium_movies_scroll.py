#########################################################
# 2-1

# import requests
# from bs4 import BeautifulSoup

# url = "https://play.google.com/store/movies/top"
# headers = {
#     "User-Agent":
#     "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36",
#     "Accept-Language": "ko-KR,ko"
# }
# res = requests.get(url, headers=headers)
# res.raise_for_status()
# soup = BeautifulSoup(res.text, "lxml")

# movies = soup.find_all("div", attrs={"class": "ImZGtf mpg5gc"})
# # print(len(movies))

# # with open("movie.html", "w", encoding="utf8") as f:
# # f.write(res.text)
# # f.write(soup.prettify())  # html문서 예쁘게 출력

# for movie in movies:
#     title = movie.find("div", attrs={"class": "WsMG1c nnK0zc"}).get_text()
#     print(title)

#########################################################
# 2-2
from selenium import webdriver

browser = webdriver.Chrome('./chromedriver.exe')
browser.maximize_window()

# 페이지 이동
url = "https://play.google.com/store/movies/top"
browser.get(url)

# 지정한 위치로 스크롤 내리기
# 모니터(해상도) 높이 1920위치로 스크롤 내리기
# browser.execute_script("window.scrollTo(0, 1920)")

# 화면 가장 아래로 스크롤 내리기
# browser.execute_script("window.scrollTo(0, document.body.scrollHeight)")

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

#########################################################
# 2-3 ~ 2-4

import requests
from bs4 import BeautifulSoup

# selenium을 통해 페이지 소스 가져오기
soup = BeautifulSoup(browser.page_source, "lxml")

# movies = soup.find_all("div", attrs={"class": ["ImZGtf mpg5gc", "Vpfmgd"]})
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
