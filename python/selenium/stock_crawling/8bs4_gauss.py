import requests
from bs4 import BeautifulSoup

url = "https://comic.naver.com/webtoon/list.nhn?titleId=675554"
res = requests.get(url)
res.raise_for_status()

soup = BeautifulSoup(res.text, "lxml")

cartoons = soup.find_all("td", attrs={"class": "title"})

####연습###
# title = cartoons[0].a.get_text()
# link = cartoons[0].a["href"]

# print(title)
# print("https://comic.naver.com" + link)
##########

# 만화 제목 + 링크 가져오기
for cartoon in cartoons:
    title = cartoon.a.get_text()
    link = "https://comic.naver.com" + cartoon.a["href"]
    # print(title, link)

# 평점 구하기
total_rates = 0
c_rating_list = soup.find_all("div", attrs={"class": "rating_type"})
for c_rating in c_rating_list:
    rate = c_rating.find("strong").get_text()
    print(rate)
    total_rates += float(rate)
print("전체 점수 : ", round(total_rates, 2))
print("평균 점수 : ", round(total_rates / len(cartoons), 3))
