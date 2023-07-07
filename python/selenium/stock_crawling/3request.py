import requests

res = requests.get("http://google.com")
print("응답코드 : ", res.status_code)  #200 정상

res.raise_for_status()

with open("mygoogle.html", "w", encoding="utf8") as f:
    f.write(res.text)