import requests
from bs4 import BeautifulSoup

url = "https://play.google.com/store/movies/top"
headers = {
    "User-Agent":
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36",
    "Accept-Language": "ko-KR,ko"
}
res = requests.get(url, headers=headers)
res.raise_for_status()
soup = BeautifulSoup(res.text, "lxml")

movies = soup.find_all("div", attrs={"class": "ImZGtf mpg5gc"})
# print(len(movies))

# with open("movie.html", "w", encoding="utf8") as f:
# f.write(res.text)
# f.write(soup.prettify())  # html문서 예쁘게 출력

for movie in movies:
    title = movie.find("div", attrs={"class": "WsMG1c nnK0zc"}).get_text()
    print(title)
