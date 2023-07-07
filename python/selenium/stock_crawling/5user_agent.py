import requests

url = "http://nadocoding.tistory.com"

# WhatIsMyUserAgent Site
# https://www.whatismybrowser.com/detect/what-is-my-user-agent
headers = {
    "User-Agent":
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36"
}

res = requests.get(url, headers)
res.raise_for_status()

with open("nadocoding.html", "w", encoding="utf8") as f:
    f.write(res.text)