from selenium import webdriver
from selenium.webdriver.common.keys import Keys

browser = webdriver.Chrome("./chromedriver.exe")
browser.get("http://naver.com")

# 로그인 창 클릭하기
# elem = browser.find_element_by_class_name("link_login")
# elem.click()

# 브라우저 뒤로가기, 앞으로가기, 새로고침
# browser.back()
# browser.forward()
# browser.refresh()

# 검색창에 값 입력 
# elem = browser.find_element_by_id("query")
# elem.send_keys("가나다라")
# elem.send_keys(Keys.ENTER)

# a태그 list로 뽑고 그 안의 요소 출력하기
# elem = browser.find_elements_by_tag_name("a") # elements : list 
# for e in elem:
#     src = e.get_attribute("href")
#     if src:
#         print(src)
#     else:
#         continue

# 다른 페이지 이동 
# browser.get("http://daum.net")
# elem = browser.find_element_by_name("q")
# elem.send_keys("가나다라")
# elem.send_keys(Keys.ENTER)


# x_path로 값 찾아서 버튼 클릭하기 
# elem = browser.find_element_by_id("query")
# elem.send_keys("가나다라")
# elem = browser.find_element_by_xpath('//*[@id="search_btn"]').click()


# 현재 탭 브라우저 종료
# browser.close()

# 모든 브라우저 종료
# browser.quit()


