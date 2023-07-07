from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
import urllib.request

url = "https://www.youtube.com/"
driver = webdriver.Chrome("./chromedriver.exe")
driver.get(url)
elem = driver.find_element_by_name("search_query")

keyword = "해외 chill 플레이리스트"

elem.send_keys(keyword)
nPage = elem.send_keys(Keys.RETURN)

# 썸네일 클릭
# thumbnail_xpath= '/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[1]/div[1]/ytd-thumbnail/a'
# driver.find_element_by_xpath(thumbnail_xpath).click()
time.sleep(3)

# 목록
# list_url = '/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[1]/div[1]/ytd-thumbnail/a/yt-img-shadow/img'
# list_url = '//[id="contents"]'
cont_list = driver.find_element_by_css_selector("contents")
print(cont_list)

# for image in cont_list:
#     thumb_image_url= image.find_element_by_css_selector(".style-scope.yt-img-shadow").get_attribute("src")
#     print(thumb_image_url)

# 썸네일
# thumbnail_image = '/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[1]/div[1]/ytd-thumbnail/a/yt-img-shadow/img'
# print(driver.find_element_by_xpath(thumbnail_image).get_attribute("src"))
# thumb_image_url = driver.find_element_by_xpath(thumbnail_image).get_attribute("src")

# 썸네일 이미지 다운
# urllib.request.urlretrieve(thumb_image_url, "test.jpg")

# thumb_image_urls = driver.find_elements_by_xpath(thumbnail_image).get_attribute("src")
