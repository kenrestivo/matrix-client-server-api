(ns matrix-client-server-api.api.voip
  (:require [matrix-client-server-api.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn get-turn-server-with-http-info
  "Obtain TURN server credentials.
  This API provides credentials for the client to use when initiating
calls."
  []
  (call-api "/_matrix/client/r0/voip/turnServer" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn get-turn-server
  "Obtain TURN server credentials.
  This API provides credentials for the client to use when initiating
calls."
  []
  (:data (get-turn-server-with-http-info)))

