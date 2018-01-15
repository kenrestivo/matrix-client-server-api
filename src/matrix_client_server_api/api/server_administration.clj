(ns matrix-client-server-api.api.server-administration
  (:require [matrix-client-server-api.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn get-versions-with-http-info
  "Gets the versions of the specification supported by the server.
  Gets the versions of the specification supported by the server.

Values will take the form ``rX.Y.Z``.

Only the latest ``Z`` value will be reported for each supported ``X.Y`` value.
i.e. if the server implements ``r0.0.0``, ``r0.0.1``, and ``r1.2.0``, it will report ``r0.0.1`` and ``r1.2.0``."
  []
  (call-api "/_matrix/client/versions" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    []}))

(defn get-versions
  "Gets the versions of the specification supported by the server.
  Gets the versions of the specification supported by the server.

Values will take the form ``rX.Y.Z``.

Only the latest ``Z`` value will be reported for each supported ``X.Y`` value.
i.e. if the server implements ``r0.0.0``, ``r0.0.1``, and ``r1.2.0``, it will report ``r0.0.1`` and ``r1.2.0``."
  []
  (:data (get-versions-with-http-info)))

(defn get-who-is-with-http-info
  "Gets information about a particular user.
  Gets information about a particular user.

This API may be restricted to only be called by the user being looked
up, or by a server admin. Server-local administrator privileges are not
specified in this document."
  [user-id ]
  (check-required-params user-id)
  (call-api "/_matrix/client/r0/admin/whois/{userId}" :get
            {:path-params   {"userId" user-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn get-who-is
  "Gets information about a particular user.
  Gets information about a particular user.

This API may be restricted to only be called by the user being looked
up, or by a server admin. Server-local administrator privileges are not
specified in this document."
  [user-id ]
  (:data (get-who-is-with-http-info user-id)))

