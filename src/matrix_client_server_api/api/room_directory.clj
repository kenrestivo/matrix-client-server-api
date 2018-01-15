(ns matrix-client-server-api.api.room-directory
  (:require [matrix-client-server-api.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn delete-room-alias-with-http-info
  "Remove a mapping of room alias to room ID.
  Remove a mapping of room alias to room ID.

Servers may choose to implement additional access control checks here, for instance that room aliases can only be deleted by their creator or a server administrator."
  [room-alias ]
  (check-required-params room-alias)
  (call-api "/_matrix/client/r0/directory/room/{roomAlias}" :delete
            {:path-params   {"roomAlias" room-alias }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn delete-room-alias
  "Remove a mapping of room alias to room ID.
  Remove a mapping of room alias to room ID.

Servers may choose to implement additional access control checks here, for instance that room aliases can only be deleted by their creator or a server administrator."
  [room-alias ]
  (:data (delete-room-alias-with-http-info room-alias)))

(defn get-room-id-by-alias-with-http-info
  "Get the room ID corresponding to this room alias.
  Requests that the server resolve a room alias to a room ID.

The server will use the federation API to resolve the alias if the
domain part of the alias does not correspond to the server's own
domain."
  [room-alias ]
  (check-required-params room-alias)
  (call-api "/_matrix/client/r0/directory/room/{roomAlias}" :get
            {:path-params   {"roomAlias" room-alias }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    []}))

(defn get-room-id-by-alias
  "Get the room ID corresponding to this room alias.
  Requests that the server resolve a room alias to a room ID.

The server will use the federation API to resolve the alias if the
domain part of the alias does not correspond to the server's own
domain."
  [room-alias ]
  (:data (get-room-id-by-alias-with-http-info room-alias)))

(defn set-room-alias-with-http-info
  "Create a new mapping from room alias to room ID."
  [room-alias room-info ]
  (check-required-params room-alias room-info)
  (call-api "/_matrix/client/r0/directory/room/{roomAlias}" :put
            {:path-params   {"roomAlias" room-alias }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    room-info
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn set-room-alias
  "Create a new mapping from room alias to room ID."
  [room-alias room-info ]
  (:data (set-room-alias-with-http-info room-alias room-info)))

