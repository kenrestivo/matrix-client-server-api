(ns matrix-client-server-api.api.send-to-device-messaging
  (:require [matrix-client-server-api.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn send-to-device-with-http-info
  "Send an event to a given set of devices.
  This endpoint is used to send send-to-device events to a set of
client devices."
  [event-type txn-id body ]
  (check-required-params event-type txn-id body)
  (call-api "/_matrix/client/r0/sendToDevice/{eventType}/{txnId}" :put
            {:path-params   {"eventType" event-type "txnId" txn-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    body
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn send-to-device
  "Send an event to a given set of devices.
  This endpoint is used to send send-to-device events to a set of
client devices."
  [event-type txn-id body ]
  (:data (send-to-device-with-http-info event-type txn-id body)))

