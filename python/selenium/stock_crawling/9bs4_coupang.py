import requests
import re
from bs4 import BeautifulSoup

url = "https://www.coupang.com/np/search?component=&q=%EB%85%B8%ED%8A%B8%EB%B6%81&channel=user"

headers = {
    "User-Agent":
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36"
}

res = requests.get(url, headers=headers)
res.raise_for_status()

soup = BeautifulSoup(res.text, "lxml")
# print(res.text)
items = soup.find_all("li", attrs={"class": re.compile("^search-product")})
# print(items[0].find("div", attrs={"class": "name"}).get_text())
for item in items:

    # 광고 제품은 제외
    ad_badge = item.find("span", attrs={"class": "ad-badge-text"})
    if ad_badge:
        print(" <광고 상품 제외합니다>")
        continue

    name = item.find("div", attrs={"class": "name"}).get_text()  # 제품명
    price = item.find("strong", attrs={
        "class": "price-value"
    }).get_text()  # 가격

    # 애플 제품 제외
    if "Apple" in name:
        print(" < Apple 상품 제외합니다>")
        continue

    # 리뷰 100개 이상 평점 4.5이상만 조회
    rate = item.find("em", attrs={"class": "rating"})  # 평점
    if rate:
        rate = rate.get_text()
    else:
        print(" <평점 없는 상품 제외합니다>")
        continue
    rate_cnt = item.find("span", attrs={"class": "rating-total-count"})  # 평점수
    if rate_cnt:
        rate_cnt = rate_cnt.get_text()  # ex : (321)
        rate_cnt = rate_cnt[1:-1]
    else:
        print(" <평점 수 없는 상품 제외합니다>")
        continue
    if float(rate) >= 4.5 and int(rate_cnt) >= 100:
        print(name, price, rate, rate_cnt)
