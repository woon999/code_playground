import requests
from bs4 import BeautifulSoup

url = "https://comic.naver.com/webtoon/weekday.nhn"
res = requests.get(url)
res.raise_for_status()

soup = BeautifulSoup(res.text, "lxml")

# print(soup.title)
# print(soup.title.get_text())
# print(soup.a)  # soup객체에서 처음 발견되는 a element 출력
# print(soup.a.attrs) # a element의 속성 정보를 출력
# print(soup.a["href"])  # a element의 href 속성 '값' 정보를 출력

# print(soup.find("a", attrs={"class": "Nbtn_upload"})) # class="Nbtn_upload"인 a_element를 찾음
# print(soup.find(attrs={"class": "Nbtn_upload"})) # class="Nbtn_upload"인 어떤 element를 찾음

rank1 = soup.find("li", attrs={"class": "rank01"})
# print(rank1.a.get_text())
# # print(rank1.next_sibling.next_sibling)
# rank2 = rank1.next_sibling.next_sibling
# rank3 = rank2.next_sibling.next_sibling
# print(rank3.a.get_text())

# rank2 = rank3.previous_sibling.previous_sibling
# print(rank2.a.get_text())

# rank2 = rank1.find_next_sibling("li")
# print(rank2.a.get_text())
# rank3 = rank2.find_next_sibling("li")
# print(rank3.a.get_text())
# rank2 = rank3.find_previous_sibling("li")
# print(rank2.a.get_text())

# print(rank1.find_next_siblings("li"))

webtoon = soup.find("a", text="모죠의 일지-198화. 타투 결심")
print(webtoon)