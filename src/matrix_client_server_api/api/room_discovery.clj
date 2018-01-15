(ns matrix-client-server-api.api.room-discovery
  (:require [matrix-client-server-api.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn get-public-rooms-with-http-info
  "Lists the public rooms on the server.
  Lists the public rooms on the server.

This API returns paginated responses. The rooms are ordered by the number
of joined members, with the largest rooms first."
  ([] (get-public-rooms-with-http-info nil))
  ([{:keys [limit since server ]}]
   (call-api "/_matrix/client/r0/publicRooms" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"limit" limit "since" since "server" server }
              :form-params   {}
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    []})))

(defn get-public-rooms
  "Lists the public rooms on the server.
  Lists the public rooms on the server.

This API returns paginated responses. The rooms are ordered by the number
of joined members, with the largest rooms first."
  ([] (get-public-rooms nil))
  ([optional-params]
   (:data (get-public-rooms-with-http-info optional-params))))

(defn query-public-rooms-with-http-info
  "Lists the public rooms on the server with optional filter.
  Lists the public rooms on the server, with optional filter.

This API returns paginated responses. The rooms are ordered by the number
of joined members, with the largest rooms first."
  ([body ] (query-public-rooms-with-http-info body nil))
  ([body {:keys [server ]}]
   (check-required-params body)
   (call-api "/_matrix/client/r0/publicRooms" :post
             {:path-params   {}
              :header-params {}
              :query-params  {"server" server }
              :form-params   {}
              :body-param    body
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn query-public-rooms
  "Lists the public rooms on the server with optional filter.
  Lists the public rooms on the server, with optional filter.

This API returns paginated responses. The rooms are ordered by the number
of joined members, with the largest rooms first."
  ([body ] (query-public-rooms body nil))
  ([body optional-params]
   (:data (query-public-rooms-with-http-info body optional-params))))

