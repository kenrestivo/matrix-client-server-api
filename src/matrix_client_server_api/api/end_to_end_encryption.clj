(ns matrix-client-server-api.api.end-to-end-encryption
  (:require [matrix-client-server-api.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn claim-keys-with-http-info
  "Claim one-time encryption keys.
  Claims one-time keys for use in pre-key messages."
  ([] (claim-keys-with-http-info nil))
  ([{:keys [query ]}]
   (call-api "/_matrix/client/r0/keys/claim" :post
             {:path-params   {}
              :header-params {}
              :query-params  {}
              :form-params   {}
              :body-param    query
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn claim-keys
  "Claim one-time encryption keys.
  Claims one-time keys for use in pre-key messages."
  ([] (claim-keys nil))
  ([optional-params]
   (:data (claim-keys-with-http-info optional-params))))

(defn get-keys-changes-with-http-info
  "Query users with recent device key updates.
  Gets a list of users who have updated their device identity keys since a
previous sync token.

The server should include in the results any users who:

* currently share a room with the calling user (ie, both users have
  membership state ``join``); *and*
* added new device identity keys or removed an existing device with
  identity keys, between ``from`` and ``to``."
  [from to ]
  (check-required-params from to)
  (call-api "/_matrix/client/r0/keys/changes" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"from" from "to" to }
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn get-keys-changes
  "Query users with recent device key updates.
  Gets a list of users who have updated their device identity keys since a
previous sync token.

The server should include in the results any users who:

* currently share a room with the calling user (ie, both users have
  membership state ``join``); *and*
* added new device identity keys or removed an existing device with
  identity keys, between ``from`` and ``to``."
  [from to ]
  (:data (get-keys-changes-with-http-info from to)))

(defn query-keys-with-http-info
  "Download device identity keys.
  Returns the current devices and identity keys for the given users."
  ([] (query-keys-with-http-info nil))
  ([{:keys [query ]}]
   (call-api "/_matrix/client/r0/keys/query" :post
             {:path-params   {}
              :header-params {}
              :query-params  {}
              :form-params   {}
              :body-param    query
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn query-keys
  "Download device identity keys.
  Returns the current devices and identity keys for the given users."
  ([] (query-keys nil))
  ([optional-params]
   (:data (query-keys-with-http-info optional-params))))

(defn upload-keys-with-http-info
  "Upload end-to-end encryption keys.
  Publishes end-to-end encryption keys for the device."
  ([] (upload-keys-with-http-info nil))
  ([{:keys [keys ]}]
   (call-api "/_matrix/client/r0/keys/upload" :post
             {:path-params   {}
              :header-params {}
              :query-params  {}
              :form-params   {}
              :body-param    keys
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn upload-keys
  "Upload end-to-end encryption keys.
  Publishes end-to-end encryption keys for the device."
  ([] (upload-keys nil))
  ([optional-params]
   (:data (upload-keys-with-http-info optional-params))))

