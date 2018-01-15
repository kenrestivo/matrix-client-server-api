(ns matrix-client-server-api.api.device-management
  (:require [matrix-client-server-api.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn delete-device-with-http-info
  "Delete a device
  This API endpoint uses the `User-Interactive Authentication API`_.

Deletes the given device, and invalidates any access token assoicated with it."
  ([device-id ] (delete-device-with-http-info device-id nil))
  ([device-id {:keys [body ]}]
   (check-required-params device-id)
   (call-api "/_matrix/client/r0/devices/{deviceId}" :delete
             {:path-params   {"deviceId" device-id }
              :header-params {}
              :query-params  {}
              :form-params   {}
              :body-param    body
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn delete-device
  "Delete a device
  This API endpoint uses the `User-Interactive Authentication API`_.

Deletes the given device, and invalidates any access token assoicated with it."
  ([device-id ] (delete-device device-id nil))
  ([device-id optional-params]
   (:data (delete-device-with-http-info device-id optional-params))))

(defn get-device-with-http-info
  "Get a single device
  Gets information on a single device, by device id."
  [device-id ]
  (check-required-params device-id)
  (call-api "/_matrix/client/r0/devices/{deviceId}" :get
            {:path-params   {"deviceId" device-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn get-device
  "Get a single device
  Gets information on a single device, by device id."
  [device-id ]
  (:data (get-device-with-http-info device-id)))

(defn get-devices-with-http-info
  "List registered devices for the current user
  Gets information about all devices for the current user."
  []
  (call-api "/_matrix/client/r0/devices" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn get-devices
  "List registered devices for the current user
  Gets information about all devices for the current user."
  []
  (:data (get-devices-with-http-info)))

(defn update-device-with-http-info
  "Update a device
  Updates the metadata on the given device."
  [device-id body ]
  (check-required-params device-id body)
  (call-api "/_matrix/client/r0/devices/{deviceId}" :put
            {:path-params   {"deviceId" device-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    body
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn update-device
  "Update a device
  Updates the metadata on the given device."
  [device-id body ]
  (:data (update-device-with-http-info device-id body)))

