(ns matrix-client-server-api.api.room-participation
  (:require [matrix-client-server-api.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn define-filter-with-http-info
  "Upload a new filter.
  Uploads a new filter definition to the homeserver.
Returns a filter ID that may be used in future requests to
restrict which events are returned to the client."
  [user-id filter ]
  (check-required-params user-id filter)
  (call-api "/_matrix/client/r0/user/{userId}/filter" :post
            {:path-params   {"userId" user-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    filter
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn define-filter
  "Upload a new filter.
  Uploads a new filter definition to the homeserver.
Returns a filter ID that may be used in future requests to
restrict which events are returned to the client."
  [user-id filter ]
  (:data (define-filter-with-http-info user-id filter)))

(defn get-event-context-with-http-info
  "Get events and state around the specified event.
  This API returns a number of events that happened just before and
after the specified event. This allows clients to get the context
surrounding an event."
  ([room-id event-id ] (get-event-context-with-http-info room-id event-id nil))
  ([room-id event-id {:keys [limit ]}]
   (check-required-params room-id event-id)
   (call-api "/_matrix/client/r0/rooms/{roomId}/context/{eventId}" :get
             {:path-params   {"roomId" room-id "eventId" event-id }
              :header-params {}
              :query-params  {"limit" limit }
              :form-params   {}
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn get-event-context
  "Get events and state around the specified event.
  This API returns a number of events that happened just before and
after the specified event. This allows clients to get the context
surrounding an event."
  ([room-id event-id ] (get-event-context room-id event-id nil))
  ([room-id event-id optional-params]
   (:data (get-event-context-with-http-info room-id event-id optional-params))))

(defn get-events-with-http-info
  "Listen on the event stream.
  This will listen for new events and return them to the caller. This will
block until an event is received, or until the ``timeout`` is reached.

This endpoint was deprecated in r0 of this specification. Clients
should instead call the |/sync|_ API with a ``since`` parameter. See
the `migration guide
<https://matrix.org/docs/guides/client-server-migrating-from-v1.html#deprecated-endpoints>`_."
  ([] (get-events-with-http-info nil))
  ([{:keys [from timeout ]}]
   (call-api "/_matrix/client/r0/events" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"from" from "timeout" timeout }
              :form-params   {}
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn get-events
  "Listen on the event stream.
  This will listen for new events and return them to the caller. This will
block until an event is received, or until the ``timeout`` is reached.

This endpoint was deprecated in r0 of this specification. Clients
should instead call the |/sync|_ API with a ``since`` parameter. See
the `migration guide
<https://matrix.org/docs/guides/client-server-migrating-from-v1.html#deprecated-endpoints>`_."
  ([] (get-events nil))
  ([optional-params]
   (:data (get-events-with-http-info optional-params))))

(defn get-filter-with-http-info
  "Download a filter"
  [user-id filter-id ]
  (check-required-params user-id filter-id)
  (call-api "/_matrix/client/r0/user/{userId}/filter/{filterId}" :get
            {:path-params   {"userId" user-id "filterId" filter-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn get-filter
  "Download a filter"
  [user-id filter-id ]
  (:data (get-filter-with-http-info user-id filter-id)))

(defn get-joined-members-by-room-with-http-info
  "Gets the list of currently joined users and their profile data.
  This API returns a map of MXIDs to member info objects for members of the room. The current user must be in the room for it to work, unless it is an Application Service in which case any of the AS's users must be in the room. This API is primarily for Application Services and should be faster to respond than ``/members`` as it can be implemented more efficiently on the server."
  [room-id ]
  (check-required-params room-id)
  (call-api "/_matrix/client/r0/rooms/{roomId}/joined_members" :get
            {:path-params   {"roomId" room-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn get-joined-members-by-room
  "Gets the list of currently joined users and their profile data.
  This API returns a map of MXIDs to member info objects for members of the room. The current user must be in the room for it to work, unless it is an Application Service in which case any of the AS's users must be in the room. This API is primarily for Application Services and should be faster to respond than ``/members`` as it can be implemented more efficiently on the server."
  [room-id ]
  (:data (get-joined-members-by-room-with-http-info room-id)))

(defn get-members-by-room-with-http-info
  "Get the m.room.member events for the room.
  Get the list of members for this room."
  [room-id ]
  (check-required-params room-id)
  (call-api "/_matrix/client/r0/rooms/{roomId}/members" :get
            {:path-params   {"roomId" room-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    []}))

(defn get-members-by-room
  "Get the m.room.member events for the room.
  Get the list of members for this room."
  [room-id ]
  (:data (get-members-by-room-with-http-info room-id)))

(defn get-one-event-with-http-info
  "Get a single event by event ID.
  Get a single event based on ``event_id``. You must have permission to
retrieve this event e.g. by being a member in the room for this event.

This endpoint was deprecated in r0 of this specification. Clients
should instead call the |/rooms/{roomId}/context/{eventId}|_ API."
  [event-id ]
  (check-required-params event-id)
  (call-api "/_matrix/client/r0/events/{eventId}" :get
            {:path-params   {"eventId" event-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn get-one-event
  "Get a single event by event ID.
  Get a single event based on ``event_id``. You must have permission to
retrieve this event e.g. by being a member in the room for this event.

This endpoint was deprecated in r0 of this specification. Clients
should instead call the |/rooms/{roomId}/context/{eventId}|_ API."
  [event-id ]
  (:data (get-one-event-with-http-info event-id)))

(defn get-room-events-with-http-info
  "Get a list of events for this room
  This API returns a list of message and state events for a room. It uses
pagination query parameters to paginate history in the room."
  ([room-id from dir ] (get-room-events-with-http-info room-id from dir nil))
  ([room-id from dir {:keys [to limit filter ]}]
   (check-required-params room-id from dir)
   (call-api "/_matrix/client/r0/rooms/{roomId}/messages" :get
             {:path-params   {"roomId" room-id }
              :header-params {}
              :query-params  {"from" from "to" to "dir" dir "limit" limit "filter" filter }
              :form-params   {}
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn get-room-events
  "Get a list of events for this room
  This API returns a list of message and state events for a room. It uses
pagination query parameters to paginate history in the room."
  ([room-id from dir ] (get-room-events room-id from dir nil))
  ([room-id from dir optional-params]
   (:data (get-room-events-with-http-info room-id from dir optional-params))))

(defn get-room-state-with-http-info
  "Get all state events in the current state of a room.
  Get the state events for the current state of a room."
  [room-id ]
  (check-required-params room-id)
  (call-api "/_matrix/client/r0/rooms/{roomId}/state" :get
            {:path-params   {"roomId" room-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn get-room-state
  "Get all state events in the current state of a room.
  Get the state events for the current state of a room."
  [room-id ]
  (:data (get-room-state-with-http-info room-id)))

(defn get-room-state-by-type-with-http-info
  "Get the state identified by the type, with the empty state key.
  Looks up the contents of a state event in a room. If the user is
joined to the room then the state is taken from the current
state of the room. If the user has left the room then the state is
taken from the state of the room when they left.

This looks up the state event with the empty state key."
  [room-id event-type ]
  (check-required-params room-id event-type)
  (call-api "/_matrix/client/r0/rooms/{roomId}/state/{eventType}" :get
            {:path-params   {"roomId" room-id "eventType" event-type }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn get-room-state-by-type
  "Get the state identified by the type, with the empty state key.
  Looks up the contents of a state event in a room. If the user is
joined to the room then the state is taken from the current
state of the room. If the user has left the room then the state is
taken from the state of the room when they left.

This looks up the state event with the empty state key."
  [room-id event-type ]
  (:data (get-room-state-by-type-with-http-info room-id event-type)))

(defn get-room-state-with-key-with-http-info
  "Get the state identified by the type and key.
  Looks up the contents of a state event in a room. If the user is
joined to the room then the state is taken from the current
state of the room. If the user has left the room then the state is
taken from the state of the room when they left."
  [room-id event-type state-key ]
  (check-required-params room-id event-type state-key)
  (call-api "/_matrix/client/r0/rooms/{roomId}/state/{eventType}/{stateKey}" :get
            {:path-params   {"roomId" room-id "eventType" event-type "stateKey" state-key }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn get-room-state-with-key
  "Get the state identified by the type and key.
  Looks up the contents of a state event in a room. If the user is
joined to the room then the state is taken from the current
state of the room. If the user has left the room then the state is
taken from the state of the room when they left."
  [room-id event-type state-key ]
  (:data (get-room-state-with-key-with-http-info room-id event-type state-key)))

(defn initial-sync-with-http-info
  "Get the user's current state.
  This returns the full state for this user, with an optional limit on the
number of messages per room to return.

This endpoint was deprecated in r0 of this specification. Clients
should instead call the |/sync|_ API with no ``since`` parameter. See
the `migration guide
<https://matrix.org/docs/guides/client-server-migrating-from-v1.html#deprecated-endpoints>`_."
  ([] (initial-sync-with-http-info nil))
  ([{:keys [limit archived ]}]
   (call-api "/_matrix/client/r0/initialSync" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"limit" limit "archived" archived }
              :form-params   {}
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn initial-sync
  "Get the user's current state.
  This returns the full state for this user, with an optional limit on the
number of messages per room to return.

This endpoint was deprecated in r0 of this specification. Clients
should instead call the |/sync|_ API with no ``since`` parameter. See
the `migration guide
<https://matrix.org/docs/guides/client-server-migrating-from-v1.html#deprecated-endpoints>`_."
  ([] (initial-sync nil))
  ([optional-params]
   (:data (initial-sync-with-http-info optional-params))))

(defn post-receipt-with-http-info
  "Send a receipt for the given event ID.
  This API updates the marker for the given receipt type to the event ID
specified."
  ([room-id receipt-type event-id ] (post-receipt-with-http-info room-id receipt-type event-id nil))
  ([room-id receipt-type event-id {:keys [receipt ]}]
   (check-required-params room-id receipt-type event-id)
   (call-api "/_matrix/client/r0/rooms/{roomId}/receipt/{receiptType}/{eventId}" :post
             {:path-params   {"roomId" room-id "receiptType" receipt-type "eventId" event-id }
              :header-params {}
              :query-params  {}
              :form-params   {}
              :body-param    receipt
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn post-receipt
  "Send a receipt for the given event ID.
  This API updates the marker for the given receipt type to the event ID
specified."
  ([room-id receipt-type event-id ] (post-receipt room-id receipt-type event-id nil))
  ([room-id receipt-type event-id optional-params]
   (:data (post-receipt-with-http-info room-id receipt-type event-id optional-params))))

(defn redact-event-with-http-info
  "Strips all non-integrity-critical information out of an event.
  Strips all information out of an event which isn't critical to the
integrity of the server-side representation of the room.

This cannot be undone.

Users may redact their own events, and any user with a power level
greater than or equal to the `redact` power level of the room may
redact events there."
  ([room-id event-id txn-id ] (redact-event-with-http-info room-id event-id txn-id nil))
  ([room-id event-id txn-id {:keys [body ]}]
   (check-required-params room-id event-id txn-id)
   (call-api "/_matrix/client/r0/rooms/{roomId}/redact/{eventId}/{txnId}" :put
             {:path-params   {"roomId" room-id "eventId" event-id "txnId" txn-id }
              :header-params {}
              :query-params  {}
              :form-params   {}
              :body-param    body
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn redact-event
  "Strips all non-integrity-critical information out of an event.
  Strips all information out of an event which isn't critical to the
integrity of the server-side representation of the room.

This cannot be undone.

Users may redact their own events, and any user with a power level
greater than or equal to the `redact` power level of the room may
redact events there."
  ([room-id event-id txn-id ] (redact-event room-id event-id txn-id nil))
  ([room-id event-id txn-id optional-params]
   (:data (redact-event-with-http-info room-id event-id txn-id optional-params))))

(defn room-initial-sync-with-http-info
  "Snapshot the current state of a room and its most recent messages.
  Get a copy of the current state and the most recent messages in a room.

This endpoint was deprecated in r0 of this specification. There is no
direct replacement; the relevant information is returned by the
|/sync|_ API. See the `migration guide
<https://matrix.org/docs/guides/client-server-migrating-from-v1.html#deprecated-endpoints>`_."
  [room-id ]
  (check-required-params room-id)
  (call-api "/_matrix/client/r0/rooms/{roomId}/initialSync" :get
            {:path-params   {"roomId" room-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn room-initial-sync
  "Snapshot the current state of a room and its most recent messages.
  Get a copy of the current state and the most recent messages in a room.

This endpoint was deprecated in r0 of this specification. There is no
direct replacement; the relevant information is returned by the
|/sync|_ API. See the `migration guide
<https://matrix.org/docs/guides/client-server-migrating-from-v1.html#deprecated-endpoints>`_."
  [room-id ]
  (:data (room-initial-sync-with-http-info room-id)))

(defn send-message-with-http-info
  "Send a message event to the given room.
  This endpoint is used to send a message event to a room. Message events
allow access to historical events and pagination, making them suited
for \"once-off\" activity in a room.

The body of the request should be the content object of the event; the
fields in this object will vary depending on the type of event. See
`Room Events`_ for the m. event specification."
  ([room-id event-type txn-id ] (send-message-with-http-info room-id event-type txn-id nil))
  ([room-id event-type txn-id {:keys [body ]}]
   (check-required-params room-id event-type txn-id)
   (call-api "/_matrix/client/r0/rooms/{roomId}/send/{eventType}/{txnId}" :put
             {:path-params   {"roomId" room-id "eventType" event-type "txnId" txn-id }
              :header-params {}
              :query-params  {}
              :form-params   {}
              :body-param    body
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn send-message
  "Send a message event to the given room.
  This endpoint is used to send a message event to a room. Message events
allow access to historical events and pagination, making them suited
for \"once-off\" activity in a room.

The body of the request should be the content object of the event; the
fields in this object will vary depending on the type of event. See
`Room Events`_ for the m. event specification."
  ([room-id event-type txn-id ] (send-message room-id event-type txn-id nil))
  ([room-id event-type txn-id optional-params]
   (:data (send-message-with-http-info room-id event-type txn-id optional-params))))

(defn set-room-state-with-http-info
  "Send a state event to the given room.
  State events can be sent using this endpoint. This endpoint is
equivalent to calling `/rooms/{roomId}/state/{eventType}/{stateKey}`
with an empty `stateKey`. Previous state events with matching
`<roomId>` and `<eventType>`, and empty `<stateKey>`, will be overwritten.

Requests to this endpoint **cannot use transaction IDs**
like other ``PUT`` paths because they cannot be differentiated from the
``state_key``. Furthermore, ``POST`` is unsupported on state paths.

The body of the request should be the content object of the event; the
fields in this object will vary depending on the type of event. See
`Room Events`_ for the ``m.`` event specification."
  ([room-id event-type ] (set-room-state-with-http-info room-id event-type nil))
  ([room-id event-type {:keys [body ]}]
   (check-required-params room-id event-type)
   (call-api "/_matrix/client/r0/rooms/{roomId}/state/{eventType}" :put
             {:path-params   {"roomId" room-id "eventType" event-type }
              :header-params {}
              :query-params  {}
              :form-params   {}
              :body-param    body
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn set-room-state
  "Send a state event to the given room.
  State events can be sent using this endpoint. This endpoint is
equivalent to calling `/rooms/{roomId}/state/{eventType}/{stateKey}`
with an empty `stateKey`. Previous state events with matching
`<roomId>` and `<eventType>`, and empty `<stateKey>`, will be overwritten.

Requests to this endpoint **cannot use transaction IDs**
like other ``PUT`` paths because they cannot be differentiated from the
``state_key``. Furthermore, ``POST`` is unsupported on state paths.

The body of the request should be the content object of the event; the
fields in this object will vary depending on the type of event. See
`Room Events`_ for the ``m.`` event specification."
  ([room-id event-type ] (set-room-state room-id event-type nil))
  ([room-id event-type optional-params]
   (:data (set-room-state-with-http-info room-id event-type optional-params))))

(defn set-room-state-with-key-with-http-info
  "Send a state event to the given room.
  State events can be sent using this endpoint.  These events will be
overwritten if ``<room id>``, ``<event type>`` and ``<state key>`` all
match.

Requests to this endpoint **cannot use transaction IDs**
like other ``PUT`` paths because they cannot be differentiated from the
``state_key``. Furthermore, ``POST`` is unsupported on state paths.

The body of the request should be the content object of the event; the
fields in this object will vary depending on the type of event. See
`Room Events`_ for the ``m.`` event specification."
  ([room-id event-type state-key ] (set-room-state-with-key-with-http-info room-id event-type state-key nil))
  ([room-id event-type state-key {:keys [body ]}]
   (check-required-params room-id event-type state-key)
   (call-api "/_matrix/client/r0/rooms/{roomId}/state/{eventType}/{stateKey}" :put
             {:path-params   {"roomId" room-id "eventType" event-type "stateKey" state-key }
              :header-params {}
              :query-params  {}
              :form-params   {}
              :body-param    body
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn set-room-state-with-key
  "Send a state event to the given room.
  State events can be sent using this endpoint.  These events will be
overwritten if ``<room id>``, ``<event type>`` and ``<state key>`` all
match.

Requests to this endpoint **cannot use transaction IDs**
like other ``PUT`` paths because they cannot be differentiated from the
``state_key``. Furthermore, ``POST`` is unsupported on state paths.

The body of the request should be the content object of the event; the
fields in this object will vary depending on the type of event. See
`Room Events`_ for the ``m.`` event specification."
  ([room-id event-type state-key ] (set-room-state-with-key room-id event-type state-key nil))
  ([room-id event-type state-key optional-params]
   (:data (set-room-state-with-key-with-http-info room-id event-type state-key optional-params))))

(defn set-typing-with-http-info
  "Informs the server that the user has started or stopped typing.
  This tells the server that the user is typing for the next N
milliseconds where N is the value specified in the ``timeout`` key.
Alternatively, if ``typing`` is ``false``, it tells the server that the
user has stopped typing."
  [user-id room-id typing-state ]
  (check-required-params user-id room-id typing-state)
  (call-api "/_matrix/client/r0/rooms/{roomId}/typing/{userId}" :put
            {:path-params   {"userId" user-id "roomId" room-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    typing-state
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn set-typing
  "Informs the server that the user has started or stopped typing.
  This tells the server that the user is typing for the next N
milliseconds where N is the value specified in the ``timeout`` key.
Alternatively, if ``typing`` is ``false``, it tells the server that the
user has stopped typing."
  [user-id room-id typing-state ]
  (:data (set-typing-with-http-info user-id room-id typing-state)))

(defn sync-with-http-info
  "Synchronise the client's state and receive new messages.
  Synchronise the client's state with the latest state on the server.
Clients use this API when they first log in to get an initial snapshot
of the state on the server, and then continue to call this API to get
incremental deltas to the state, and to receive new messages."
  ([] (sync-with-http-info nil))
  ([{:keys [filter since full-state set-presence timeout ]}]
   (call-api "/_matrix/client/r0/sync" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"filter" filter "since" since "full_state" full-state "set_presence" set-presence "timeout" timeout }
              :form-params   {}
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn sync
  "Synchronise the client's state and receive new messages.
  Synchronise the client's state with the latest state on the server.
Clients use this API when they first log in to get an initial snapshot
of the state on the server, and then continue to call this API to get
incremental deltas to the state, and to receive new messages."
  ([] (sync nil))
  ([optional-params]
   (:data (sync-with-http-info optional-params))))

