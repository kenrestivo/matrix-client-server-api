(ns matrix-client-server-api.api.room-creation
  (:require [matrix-client-server-api.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn create-room-with-http-info
  "Create a new room
  Create a new room with various configuration options.

The server MUST apply the normal state resolution rules when creating
the new room, including checking power levels for each event. It MUST
apply the events implied by the request in the following order:

0. A default ``m.room.power_levels`` event, giving the room creator
   (and not other members) permission to send state events.

1. Events set by ``presets``.

2. Events listed in ``initial_state``, in the order that they are
   listed.

3. Events implied by ``name`` and ``topic``.

4. Invite events implied by ``invite`` and ``invite_3pid``."
  ([] (create-room-with-http-info nil))
  ([{:keys [body ]}]
   (call-api "/_matrix/client/r0/createRoom" :post
             {:path-params   {}
              :header-params {}
              :query-params  {}
              :form-params   {}
              :body-param    body
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn create-room
  "Create a new room
  Create a new room with various configuration options.

The server MUST apply the normal state resolution rules when creating
the new room, including checking power levels for each event. It MUST
apply the events implied by the request in the following order:

0. A default ``m.room.power_levels`` event, giving the room creator
   (and not other members) permission to send state events.

1. Events set by ``presets``.

2. Events listed in ``initial_state``, in the order that they are
   listed.

3. Events implied by ``name`` and ``topic``.

4. Invite events implied by ``invite`` and ``invite_3pid``."
  ([] (create-room nil))
  ([optional-params]
   (:data (create-room-with-http-info optional-params))))

