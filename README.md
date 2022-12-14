# Weather_iTW

**這是一個天氣觀測app，主要使用Navigation Bottom Bar進行頁面切換，  
並使用簡潔且直觀的方式讓使用者可以知道目前的天氣狀況及AQI相關數值**

## 使用說明
在local.properties中加入：
```sh
WEATHER_API="Your API"
AQI_API="Your API"
```
`WEATHER_API請至中央氣象局申請(註冊登入後即可申請)`  
[中央氣象局](https://opendata.cwb.gov.tw/userLogin)  
`AQI_API請至行政院環境保護署申請(註冊登入後即可申請)`  
[行政院環境保護署](https://data.epa.gov.tw/paradigm)

# 使用/參考的專案
    1.頁面 [當日天氣] 中的垂直跑馬燈
      [參考來源](https://github.com/sunfusheng/MarqueeView)
      
    2.頁面 [日出/日落] 中的太陽顯示動畫
      [參考來源](https://www.freesion.com/article/29391006731/)
      
    3.頁面 [AQI] 中的垂直進度條(*修改當各項觀測數值在不同區間時，進度條會顯示不同顏色)
      [參考來源](https://www.cnblogs.com/blogzhangwei/p/12106049.html)
      
    4.頁面 [AQI] 中的CircleProgress(*修改AQI數值在不同區間時，進度條會顯示不同顏色)
      [參考來源](https://github.com/MyLifeMyTravel/CircleProgress)

## 文字說明
##### 第一頁：當日天氣
&nbsp;
&nbsp;&nbsp;**查看目前天氣資訊及逐三小時天氣資訊，包括天氣現象、平均溫度、降雨機率、相對濕度、風向、風速等天氣狀態  
&nbsp;&nbsp;&nbsp;&nbsp;其中天氣現象使用圖片提示使用者天氣狀況**
##### 第二頁：一周天氣
&nbsp;
&nbsp;&nbsp;**查看一周天氣資訊，包括最高溫度、最低溫度、降雨機率、相對溼度、風向、風速等天氣狀態  
&nbsp;&nbsp;&nbsp;&nbsp;點擊ExpandableListView中的child list可以Toast出天氣資訊**
##### 第三頁：日出/日落
&nbsp;
&nbsp;&nbsp;**圖片提示目前為白天/夜晚，並顯示溫度，中間區域則依據白天/夜晚有不同效果  
&nbsp;&nbsp;&nbsp;&nbsp;白天：顯示太陽目前位置  
&nbsp;&nbsp;&nbsp;&nbsp;夜晚：顯示 charging gif 動畫  
&nbsp;&nbsp;&nbsp;&nbsp;下方可以查看日出、日落、月出、月落時間**
##### 第四頁：AQI
&nbsp;
&nbsp;&nbsp;**查看AQI資訊，當每次點擊至AQI頁面時，上方Toolbar會出現字樣提醒使用者點選頁面中觀測站，右側問號圖示  
&nbsp;&nbsp;&nbsp;&nbsp;可以顯示各AQI數值危害程度(數值僅供參考，詳細狀況依據現場環境為主)，中央有CircleProgress提示使用者目前  
&nbsp;&nbsp;&nbsp;&nbsp;AQI數值在什麼危害區間，下方則有PM2.5、PM10、S02、NO2、CO、O3等數值顯示並且各項數值會依據官網  
&nbsp;&nbsp;&nbsp;&nbsp;提供的危害程度進行顏色的區別判定**

## 圖片說明
##### 第一頁：當日天氣
&nbsp;
![image](https://raw.githubusercontent.com/zernkzta/Weather_iTW/master/images/1.%E7%95%B6%E6%97%A5%E5%A4%A9%E6%B0%A3.png)
##### 第二頁：一周天氣
&nbsp;
![image](https://raw.githubusercontent.com/zernkzta/Weather_iTW/master/images/2.%E4%B8%80%E5%91%A8%E5%A4%A9%E6%B0%A3.png)
##### 第三頁：日出/日落
&nbsp;
![image](https://raw.githubusercontent.com/zernkzta/Weather_iTW/master/images/3.%E6%97%A5%E5%87%BA%26%E6%9C%88%E8%90%BD.png)
##### 第四頁：AQI
&nbsp;
![image](https://raw.githubusercontent.com/zernkzta/Weather_iTW/master/images/4.AQI.png)
## 測試平台
### Virtual Device
**Device Name：pixel 5  
Resolution：1080x2340  
API Level：31  
Android 12.0**

### Physical Device
**Device Name：POCO M3  
Resolution：1080x2340  
API Level：29  
Android 10.0**
