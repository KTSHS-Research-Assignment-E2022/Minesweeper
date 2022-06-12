const CACHE_NAME = 'SW-Cache';
const CACHE_VERSION = 1;
const CACHE_KEY = `${CACHE_NAME}:v${CACHE_VERSION}`;
// キャッシュに登録するもの
const urlsToCache = [
  'index.html',
  'ress.min.css',
  'fonts/M_PLUS_2/MPLUS2-VariableFont_wght.ttf',
  'fonts/M_PLUS_Rounded_1c/MPLUSRounded1c-Medium.ttf',
  'icons/icon-32.png',
  'icons/icon-64.png',
  'icons/icon-128.png',
  'icons/icon-256.png',
];

self.addEventListener('install', (event) => {
  console.log('sw event: install called');

  event.waitUntil(
    caches.open(CACHE_KEY)
    .then((cache) => {
      return cache.addAll(urlsToCache);
    })
  );
});

self.addEventListener('activate', (event) => {
  event.waitUntil(
    caches.keys().then((keyList) => {
      return Promise.all(keyList.map((key) => {
        if(key !== CACHE_KEY) {
          return caches.delete(key);
        }
      }));
    })
  );
});

self.addEventListener('fetch', (event) => {
  event.respondWith(
    caches.match(event.request)
    .then((response) => {
      // キャッシュ内に該当レスポンスがあれば、それを返す
      if (response) {
        return response;
      }

      // 重要：リクエストを clone する。リクエストは Stream なので
      // 一度しか処理できない。ここではキャッシュ用、fetch 用と2回
      // 必要なので、リクエストは clone しないといけない
      let fetchRequest = event.request.clone();

      return fetch(fetchRequest)
        .then((response) => {
          if (!response || response.status !== 200 || response.type !== 'basic') {
            // キャッシュする必要のないタイプのレスポンスならそのまま返す
            return response;
          }

          // 重要：レスポンスを clone する。レスポンスは Stream で
          // ブラウザ用とキャッシュ用の2回必要。なので clone して
          // 2つの Stream があるようにする
          let responseToCache = response.clone();

          caches.open(CACHE_KEY)
            .then((cache) => {
              cache.put(event.request, responseToCache);
            });

          return response;
        });
    })
  );
});
