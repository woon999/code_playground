import requests
from bs4 import BeautifulSoup

# 2015~2019년 영화순위 top5 이미지 뽑아오기
for year in range(2015, 2020):
    url = "https://search.daum.net/search?nil_suggest=btn&w=tot&DA=SBC&q={}%EB%85%84+%EC%98%81%ED%99%94%EC%88%9C%EC%9C%84".format(
        year)

    res = requests.get(url)
    res.raise_for_status()

    soup = BeautifulSoup(res.text, "lxml")
    images = soup.find_all("img", attrs={"class": "thumb_img"})

    for idx, image in enumerate(images):
        image_url = image["src"]
        if image_url.startswith("//"):
            image_url = "https:" + image_url
        print(image_url)
        image_res = requests.get(image_url)
        image_res.raise_for_status()

        with open("movie_{}_{}.jpg".format(year, idx + 1), "wb") as f:
            f.write(image_res.content)

        if idx >= 4:  #상위 5개 이미지만 다운로드
            break