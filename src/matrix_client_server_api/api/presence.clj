(ns matrix-client-server-api.api.presence
  (:require [matrix-client-server-api.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn get-presence-with-http-info
  "Get this user's presence state.
  Get the given user's presence state."
  [user-id ]
  (check-required-params user-id)
  (call-api "/_matrix/client/r0/presence/{userId}/status" :get
            {:path-params   {"userId" user-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    []}))

(defn get-presence
  "Get this user's presence state.
  Get the given user's presence state."
  [user-id ]
  (:data (get-presence-with-http-info user-id)))

(defn get-presence-for-list-with-http-info
  "Get presence events for this presence list.
  Retrieve a list of presence events for every user on this list."
  [user-id ]
  (check-required-params user-id)
  (call-api "/_matrix/client/r0/presence/list/{userId}" :get
            {:path-params   {"userId" user-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    []}))

(defn get-presence-for-list
  "Get presence events for this presence list.
  Retrieve a list of presence events for every user on this list."
  [user-id ]
  (:data (get-presence-for-list-with-http-info user-id)))

(defn modify-presence-list-with-http-info
  "Add or remove users from this presence list.
  Adds or removes users from this presence list."
  [user-id presence-diff ]
  (check-required-params user-id presence-diff)
  (call-api "/_matrix/client/r0/presence/list/{userId}" :post
            {:path-params   {"userId" user-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    presence-diff
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn modify-presence-list
  "Add or remove users from this presence list.
  Adds or removes users from this presence list."
  [user-id presence-diff ]
  (:data (modify-presence-list-with-http-info user-id presence-diff)))

(defn set-presence-with-http-info
  "Update this user's presence state.
  This API sets the given user's presence state. When setting the status,
the activity time is updated to reflect that activity; the client does
not need to specify the ``last_active_ago`` field. You cannot set the
presence state of another user."
  [user-id presence-state ]
  (check-required-params user-id presence-state)
  (call-api "/_matrix/client/r0/presence/{userId}/status" :put
            {:path-params   {"userId" user-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    presence-state
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn set-presence
  "Update this user's presence state.
  This API sets the given user's presence state. When setting the status,
the activity time is updated to reflect that activity; the client does
not need to specify the ``last_active_ago`` field. You cannot set the
presence state of another user."
  [user-id presence-state ]
  (:data (set-presence-with-http-info user-id presence-state)))

