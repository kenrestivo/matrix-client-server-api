(ns matrix-client-server-api.api.push-notifications
  (:require [matrix-client-server-api.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn delete-push-rule-with-http-info
  "Delete a push rule.
  This endpoint removes the push rule defined in the path."
  [scope kind rule-id ]
  (check-required-params scope kind rule-id)
  (call-api "/_matrix/client/r0/pushrules/{scope}/{kind}/{ruleId}" :delete
            {:path-params   {"scope" scope "kind" kind "ruleId" rule-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn delete-push-rule
  "Delete a push rule.
  This endpoint removes the push rule defined in the path."
  [scope kind rule-id ]
  (:data (delete-push-rule-with-http-info scope kind rule-id)))

(defn get-notifications-with-http-info
  "Gets a list of events that the user has been notified about
  This API is used to paginate through the list of events that the
user has been, or would have been notified about."
  ([] (get-notifications-with-http-info nil))
  ([{:keys [from limit only ]}]
   (call-api "/_matrix/client/r0/notifications" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"from" from "limit" limit "only" only }
              :form-params   {}
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn get-notifications
  "Gets a list of events that the user has been notified about
  This API is used to paginate through the list of events that the
user has been, or would have been notified about."
  ([] (get-notifications nil))
  ([optional-params]
   (:data (get-notifications-with-http-info optional-params))))

(defn get-push-rule-with-http-info
  "Retrieve a push rule.
  Retrieve a single specified push rule."
  [scope kind rule-id ]
  (check-required-params scope kind rule-id)
  (call-api "/_matrix/client/r0/pushrules/{scope}/{kind}/{ruleId}" :get
            {:path-params   {"scope" scope "kind" kind "ruleId" rule-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn get-push-rule
  "Retrieve a push rule.
  Retrieve a single specified push rule."
  [scope kind rule-id ]
  (:data (get-push-rule-with-http-info scope kind rule-id)))

(defn get-push-rules-with-http-info
  "Retrieve all push rulesets.
  Retrieve all push rulesets for this user. Clients can \"drill-down\" on
the rulesets by suffixing a ``scope`` to this path e.g.
``/pushrules/global/``. This will return a subset of this data under the
specified key e.g. the ``global`` key."
  []
  (call-api "/_matrix/client/r0/pushrules/" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn get-push-rules
  "Retrieve all push rulesets.
  Retrieve all push rulesets for this user. Clients can \"drill-down\" on
the rulesets by suffixing a ``scope`` to this path e.g.
``/pushrules/global/``. This will return a subset of this data under the
specified key e.g. the ``global`` key."
  []
  (:data (get-push-rules-with-http-info)))

(defn get-pushers-with-http-info
  "Gets the current pushers for the authenticated user
  Gets all currently active pushers for the authenticated user"
  []
  (call-api "/_matrix/client/r0/pushers" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn get-pushers
  "Gets the current pushers for the authenticated user
  Gets all currently active pushers for the authenticated user"
  []
  (:data (get-pushers-with-http-info)))

(defn post-pusher-with-http-info
  "Modify a pusher for this user on the homeserver.
  This endpoint allows the creation, modification and deletion of `pushers`_
for this user ID. The behaviour of this endpoint varies depending on the
values in the JSON body."
  [pusher ]
  (check-required-params pusher)
  (call-api "/_matrix/client/r0/pushers/set" :post
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    pusher
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn post-pusher
  "Modify a pusher for this user on the homeserver.
  This endpoint allows the creation, modification and deletion of `pushers`_
for this user ID. The behaviour of this endpoint varies depending on the
values in the JSON body."
  [pusher ]
  (:data (post-pusher-with-http-info pusher)))

(defn set-push-rule-with-http-info
  "Add or change a push rule.
  This endpoint allows the creation, modification and deletion of pushers
for this user ID. The behaviour of this endpoint varies depending on the
values in the JSON body."
  ([scope kind rule-id pushrule ] (set-push-rule-with-http-info scope kind rule-id pushrule nil))
  ([scope kind rule-id pushrule {:keys [before after ]}]
   (check-required-params scope kind rule-id pushrule)
   (call-api "/_matrix/client/r0/pushrules/{scope}/{kind}/{ruleId}" :put
             {:path-params   {"scope" scope "kind" kind "ruleId" rule-id }
              :header-params {}
              :query-params  {"before" before "after" after }
              :form-params   {}
              :body-param    pushrule
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn set-push-rule
  "Add or change a push rule.
  This endpoint allows the creation, modification and deletion of pushers
for this user ID. The behaviour of this endpoint varies depending on the
values in the JSON body."
  ([scope kind rule-id pushrule ] (set-push-rule scope kind rule-id pushrule nil))
  ([scope kind rule-id pushrule optional-params]
   (:data (set-push-rule-with-http-info scope kind rule-id pushrule optional-params))))

(defn set-push-rule-actions-with-http-info
  "Set the actions for a push rule.
  This endpoint allows clients to change the actions of a push rule.
This can be used to change the actions of builtin rules."
  [scope kind rule-id body ]
  (check-required-params scope kind rule-id body)
  (call-api "/_matrix/client/r0/pushrules/{scope}/{kind}/{ruleId}/actions" :put
            {:path-params   {"scope" scope "kind" kind "ruleId" rule-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    body
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn set-push-rule-actions
  "Set the actions for a push rule.
  This endpoint allows clients to change the actions of a push rule.
This can be used to change the actions of builtin rules."
  [scope kind rule-id body ]
  (:data (set-push-rule-actions-with-http-info scope kind rule-id body)))

(defn set-push-rule-enabled-with-http-info
  "Enable or disable a push rule.
  This endpoint allows clients to enable or disable the specified push rule."
  [scope kind rule-id body ]
  (check-required-params scope kind rule-id body)
  (call-api "/_matrix/client/r0/pushrules/{scope}/{kind}/{ruleId}/enabled" :put
            {:path-params   {"scope" scope "kind" kind "ruleId" rule-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    body
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn set-push-rule-enabled
  "Enable or disable a push rule.
  This endpoint allows clients to enable or disable the specified push rule."
  [scope kind rule-id body ]
  (:data (set-push-rule-enabled-with-http-info scope kind rule-id body)))

