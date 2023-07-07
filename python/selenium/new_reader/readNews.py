import requests
from bs4 import BeautifulSoup
import re
import datetime
import csv

headers = {
    "User-Agent":
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36"
}

## 스크래핑 날짜
gIndex = 0
now = datetime.datetime.now()
nowDate = now.strftime('%Y년 %m월 %d일 (%a)')

## CSV 파일 생성
fileDate = now.strftime('%Y%m%d')
filename = f"data/{fileDate}.csv"
f = open(filename, "w", encoding="utf8", newline="")
writer = csv.writer(f)

## CSV 열 목록 생성
title = "N#\t카테고리#\t내용#\t링크".split("\t")
writer.writerow(title)

print(f"안녕하세요. 오늘은 {nowDate} 입니다.")  # 2021-05-112

res = f"{gIndex}#-----[오늘의 날짜]#-----"
res += f"{nowDate}"
writer.writerow(res.split("-----"))


def create_soup(url):
    res = requests.get(url, headers=headers)
    res.raise_for_status()
    soup = BeautifulSoup(res.text, "lxml")
    return soup


############### 날씨 ###############
def scrap_weather():
    weather_url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=%EC%84%9C%EC%9A%B8+%EB%82%A0%EC%94%A8"
    soup = create_soup(weather_url)

    weather_data = soup.find("div", attrs={"class": "info_data"})

    # 현재 날씨 정보
    weather_info = weather_data.find("p", attrs={
        "class": "cast_txt"
    }).get_text()

    # 온도
    pos_degree = weather_data.find("p", attrs={
        "class": "info_temperature"
    }).get_text().replace("도씨", "")
    min_degree = weather_data.find("span", attrs={"class": "min"}).get_text()
    max_degree = weather_data.find("span", attrs={"class": "max"}).get_text()

    # 강수 확률
    rainy_data = soup.find("li", attrs={
        "class": "date_info today"
    }).find_all("span", attrs={"class": "num"})

    # 미세먼지 정보
    dust_data = soup.find("dl", attrs={
        "class": "indicator"
    }).find_all("dd", attrs={"class": re.compile("^lv")})

    # dust_data2 = soup.find("dl", attrs={
    #     "class": "indicator"
    # }).find_all("dd", attrs={"class": "lv2"})

    dust_data1 = ""
    dust_data2 = ""
    if dust_data[0].get_text():
        dust_data1 += dust_data[0].get_text()

    if dust_data[1].get_text():
        dust_data2 += dust_data[1].get_text()

    # if dust_data[1].get_text():
    #     dust_data2 += dust_data[1].get_text()
    # else :
    #     dust_data2 += dust_data2[1].get_text()
    # 출력
    # print("[오늘의 날씨]")
    # print(f"현재 {pos_degree} (최저 {min_degree}/ 최고 {max_degree})")
    # print(weather_info)
    # print(
    #     f"오전 강수확률 {rainy_data[0].get_text()}% / 오후 강수확률 {rainy_data[1].get_text()}%"
    # )
    # print(f"미세먼지 {dust_data[0].get_text()}")
    # print(f"초미세먼지 {dust_data[1].get_text()}")
    # print()
    gIdx = gIndex + 1
    res = f"{gIdx}-----[오늘의 날씨]-----"
    res += f"현재 {pos_degree} (최저 {min_degree}/ 최고 {max_degree})"
    writer.writerow(res.split("-----"))

    res = f"{gIdx+1}-----[오늘의 날씨]-----"
    res += f"{weather_info}"
    writer.writerow(res.split("-----"))

    res = f"{gIdx+2}-----[오늘의 날씨]-----"
    res += f"오전 강수확률 {rainy_data[0].get_text()}% / 오후 강수확률 {rainy_data[1].get_text()}%"
    writer.writerow(res.split("-----"))

    res = f"{gIdx+3}-----[오늘의 날씨]-----"
    res += f"미세먼지 {dust_data1} 초미세먼지 {dust_data2}"
    writer.writerow(res.split("-----"))


############### 회화 ###############
def scrap_english():
    eng_url = "https://www.hackers.co.kr/?c=s_eng/eng_contents/I_others_english&keywd=haceng_submain_lnb_eng_I_others_english&logger_kw=haceng_submain_lnb_eng_I_others_english"
    soup = create_soup(eng_url)

    sentences = soup.find_all("div", attrs={"id": re.compile("^conv_kor_t")})

    # print("[오늘의 영어 회화]")
    # print("(영어지문)")

    gIdx = gIndex

    # 8문장이 있다고 가정, 5~8까지 영어문장 (idx: 4~7)
    for txt in sentences[len(sentences) // 2:]:
        gIdx = gIdx + 1
        res = f"{gIdx}-----[오늘의 영어 회화]-----"
        res += f"{txt.get_text().strip()}"
        writer.writerow(res.split("-----"))

    # print("(한글지문)")
    # 8문장이 있다고 가정, 0~3까지 한글문장 (idx: 0~3)

    for txt in sentences[:len(sentences) // 2:]:
        gIdx = gIdx + 1
        res = f"{gIdx}-----[오늘의 영어 회화]-----"
        res += f"{txt.get_text().strip()}"
        writer.writerow(res.split("-----"))


############### 국내 News ###############
# sector
# politics : 정치
# economy : 경제
# it : IT/과학
# society : 사회
# life : 생활/문화
# world : 세계
def scrap_news(sector):
    news_url = "https://news.naver.com/"
    soup = create_soup(news_url)

    # 뉴스 데이터
    news_data = soup.find("div", attrs={"id": f"section_{sector}"})
    # 섹터 이름
    sector_name = news_data.find("h4", attrs={"class": "tit_sec"}).a.get_text()
    # 헤드라인 목록
    news_list = news_data.find("ul", attrs={
        "class": "mlist2 no_bg"
    }).find_all("li")

    # 출력
    # print(f"[국내 {sector_name} 헤드라인 뉴스]")

    gIdx = gIndex
    for idx, article in enumerate(news_list):
        headline = article.a.get_text().strip()
        news_link = article.a["href"]

        gIdx = gIdx + 1
        res = f"{gIdx}#-----[뉴스 {sector_name}]#-----"
        res += f"{headline}#-----"
        res += f"{news_link}"

        writer.writerow(res.split("-----"))


############### 해외 News ###############
# CNBC Trendig News
def scrap_global_news(sector):
    news_url = f"https://www.cnbc.com/{sector}/"
    soup = create_soup(news_url)

    # 섹터 이름
    # sector_name = soup.find("h1", attrs={
    #     "class": "PageHeader-title"
    # }).get_text()

    # 트랜딩 뉴스 데이터
    news_data = soup.find("ul", attrs={"class": "TrendingNow-storyContainer"})
    # 헤드라인 목록
    news_list = news_data.find_all("li")

    # 출력
    res = ""
    gIdx = gIndex
    for idx, article in enumerate(news_list):
        headline = article.a.get_text().strip()
        news_link = article.a["href"]

        gIdx = gIdx + 1
        res = f"{gIdx}#-----[뉴스 CNBC]#-----"
        res += f"{headline}#-----"
        res += f"{news_link}"

        writer.writerow(res.split("-----"))


##### 경제 관련 데이터 - 원달러 환율, 해외 대표 지수 변동률 #####
def print_economic_data():
    scrap_exchange_rate()  # 원달러 환율
    scrap_nasdaq_index()  #나스닥 지수
    scrap_sp500_index()  #S&P500 지수


def scrap_exchange_rate():
    exchange_rate_url = "https://finance.naver.com/marketindex/exchangeDetail.nhn?marketindexCd=FX_USDKRW"
    soup = create_soup(exchange_rate_url)

    # 원달러 환율
    exchange_rate = soup.find("p", attrs={
        "class": "no_today"
    }).em.get_text().strip()

    # 전일 대비
    exday = soup.find("p", attrs={"class": "no_exday"})
    exday_up_list = exday.find_all("em", attrs={"class": "no_up"})
    exday_down_list = exday.find_all("em", attrs={"class": "no_down"})

    gIdx = gIndex
    res = f"{gIdx}#-----[경제 환율]#-----"
    res += f"{exchange_rate}"
    data = ""
    if exday_up_list:

        for ex in exday_up_list:
            data += ex.get_text().strip().replace("\n", "")
        data = data.split("\t")
        res += f"@{data[0]} 상승"
    elif exday_down_list:
        for ex in exday_down_list:
            data += ex.get_text().strip().replace("\n", "")
        data = data.split("\t")
        res += f"@{data[0]} 하락"
    else:
        exday_zero_list = exday.find_all("em")
        for ex in exday_zero_list:
            data += ex.get_text().strip().replace("\n", "")
        data = data.split("\t")
        res += f"@{data[0]} 변화없음"

    # 출력
    # print(f"원달러 환율 {exchange_rate}원 | 전일 대비 {data[0]} 상승")
    writer.writerow(res.split("-----"))


def scrap_nasdaq_index():
    nasdaq_index_url = "https://finance.naver.com/world/sise.nhn?symbol=NAS@IXIC"
    soup = create_soup(nasdaq_index_url)

    # 해외 주요 지수
    nasdaq = soup.find("p", attrs={"class": "no_today"}).em.get_text().strip()

    # 전일 대비
    exday = soup.find("p", attrs={"class": "no_exday"})
    exday_up_list = exday.find_all("em", attrs={"class": "no_up"})
    exday_down_list = exday.find_all("em", attrs={"class": "no_down"})

    gIdx = gIndex
    res = f"{gIdx}#-----[경제 나스닥]#-----"
    res += f"{nasdaq}"
    data = ""
    if exday_up_list:

        for ex in exday_up_list:
            data += ex.get_text().strip().replace("\n", "")
        data = data.split("\t")
        res += f"@{data[0]} 상승"
    else:
        for ex in exday_down_list:
            data += ex.get_text().strip().replace("\n", "")
        data = data.split("\t")
        res += f"@{data[0]} 하락"

    # 출력
    # print(f"오늘 나스닥 지수 : {nasdaq} | 전일 대비 {data[0]} 상승")
    writer.writerow(res.split("-----"))


def scrap_sp500_index():
    sp500_index_url = "https://finance.naver.com/world/sise.nhn?symbol=SPI@SPX"
    soup = create_soup(sp500_index_url)

    # 해외 주요 지수
    sp500 = soup.find("p", attrs={"class": "no_today"}).em.get_text().strip()

    # 전일 대비
    exday = soup.find("p", attrs={"class": "no_exday"})
    exday_up_list = exday.find_all("em", attrs={"class": "no_up"})
    exday_down_list = exday.find_all("em", attrs={"class": "no_down"})

    gIdx = gIndex
    res = f"{gIdx}#-----[경제 SP]#-----"
    res += f"{sp500}"
    data = ""
    if exday_up_list:

        for ex in exday_up_list:
            data += ex.get_text().strip().replace("\n", "")
        data = data.split("\t")
        res += f"@{data[0]} 상승"
    else:
        for ex in exday_down_list:
            data += ex.get_text().strip().replace("\n", "")
        data = data.split("\t")
        res += f"@{data[0]} 하락"

    # 출력
    # print(f"오늘 S&P500 지수 : {sp500} | 전일 대비 {data[0]} 상승")
    writer.writerow(res.split("-----"))


## main
if __name__ == "__main__":

    # 오늘의 날씨 정보 가져오기
    # scrap_weather()
    # gIndex += 4  # CSV완료

    # 영어 회화
    # scrap_english()
    # gIndex += 8  # CSV완료

    # # 국내 분야별 뉴스
    scrap_news("politics")
    gIndex += 5
    scrap_news("economy")
    gIndex += 5
    scrap_news("it")
    gIndex += 5  # CSV완료

    # # 해외 트렌딩 뉴스
    scrap_global_news("economy")
    gIndex += 5  # CSV완료

    # # 해외 경제 데이터
    scrap_exchange_rate()  # 원달러 환율
    gIndex += 1
    scrap_nasdaq_index()  #나스닥 지수
    gIndex += 1
    scrap_sp500_index()  #S&P500 지수
