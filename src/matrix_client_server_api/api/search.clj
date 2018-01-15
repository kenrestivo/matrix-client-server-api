(ns matrix-client-server-api.api.search
  (:require [matrix-client-server-api.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn search-with-http-info
  "Perform a server-side search.
  Performs a full text search across different categories."
  ([] (search-with-http-info nil))
  ([{:keys [next-batch body ]}]
   (call-api "/_matrix/client/r0/search" :post
             {:path-params   {}
              :header-params {}
              :query-params  {"next_batch" next-batch }
              :form-params   {}
              :body-param    body
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn search
  "Perform a server-side search.
  Performs a full text search across different categories."
  ([] (search nil))
  ([optional-params]
   (:data (search-with-http-info optional-params))))

