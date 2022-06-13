Тестовое задание:
---

Endpoints:
---  

**Получение гифки:**  

```
GET api/v1/exchange/rate/{code}
```  
code - код валюты</br>

Пример ответа:
```json
{
  "data":{
    "type":"gif",
    "id":"3o7aCW63oNryEnWKje",
    "url":"https://giphy.com/gifs/wetv-love-reality-3o7aCW63oNryEnWKje",
    "slug":"wetv-love-reality-3o7aCW63oNryEnWKje",
    "bitly_gif_url":"http://gph.is/2vwIAzG",
    "bitly_url":"http://gph.is/2vwIAzG",
    "embed_url":"https://giphy.com/embed/3o7aCW63oNryEnWKje",
    "username":"wetv",
    "source":"http://www.wetv.com/shows/million-dollar-matchmaker",
    "title":"",
    "rating":"g",
    "content_url":"",
    "source_tld":"www.wetv.com",
    "source_post_url":"http://www.wetv.com/shows/million-dollar-matchmaker",
    "is_sticker":0,
    "import_datetime":"2017-08-28 15:05:21",
    "trending_datetime":"0000-00-00 00:00:00",
    "images":{...},
    "user":{...}
  },
  "meta":{
    "msg":"OK",
    "status":200,
    "response_id":"b0853ad0b7836663a1ae1274bc8493251cca58c8"
  }
}
```

Docker:
---

Запуск:
```
docker-compose up
``` 

Описание
---
```
Создать сервис, который обращается к сервису курсов валют, и отображает gif:
если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich
если ниже - отсюда https://giphy.com/search/broke
Ссылки
REST API курсов валют - https://docs.openexchangerates.org/
REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide
Must Have
Сервис на Spring Boot 2 + Java / Kotlin
Запросы приходят на HTTP endpoint (должен быть написан в соответствии с rest conventions), туда передается код валюты по отношению с которой сравнивается USD
Для взаимодействия с внешними сервисами используется Feign
Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки
На сервис написаны тесты (для мока внешних сервисов можно использовать @mockbean или WireMock)
Для сборки должен использоваться Gradle
Результатом выполнения должен быть репо на GitHub с инструкцией по запуску
Nice to Have
Сборка и запуск Docker контейнера с этим сервисом  
```
