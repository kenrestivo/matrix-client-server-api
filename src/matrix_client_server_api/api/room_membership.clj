(ns matrix-client-server-api.api.room-membership
  (:require [matrix-client-server-api.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn ban-with-http-info
  "Ban a user in the room.
  Ban a user in the room. If the user is currently in the room, also kick them.

When a user is banned from a room, they may not join it or be invited to it until they are unbanned.

The caller must have the required power level in order to perform this operation."
  [room-id body ]
  (check-required-params room-id body)
  (call-api "/_matrix/client/r0/rooms/{roomId}/ban" :post
            {:path-params   {"roomId" room-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    body
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn ban
  "Ban a user in the room.
  Ban a user in the room. If the user is currently in the room, also kick them.

When a user is banned from a room, they may not join it or be invited to it until they are unbanned.

The caller must have the required power level in order to perform this operation."
  [room-id body ]
  (:data (ban-with-http-info room-id body)))

(defn forget-room-with-http-info
  "Stop the requesting user remembering about a particular room.
  This API stops a user remembering about a particular room.

In general, history is a first class citizen in Matrix. After this API
is called, however, a user will no longer be able to retrieve history
for this room. If all users on a homeserver forget a room, the room is
eligible for deletion from that homeserver.

If the user is currently joined to the room, they will implicitly leave
the room as part of this API call."
  [room-id ]
  (check-required-params room-id)
  (call-api "/_matrix/client/r0/rooms/{roomId}/forget" :post
            {:path-params   {"roomId" room-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn forget-room
  "Stop the requesting user remembering about a particular room.
  This API stops a user remembering about a particular room.

In general, history is a first class citizen in Matrix. After this API
is called, however, a user will no longer be able to retrieve history
for this room. If all users on a homeserver forget a room, the room is
eligible for deletion from that homeserver.

If the user is currently joined to the room, they will implicitly leave
the room as part of this API call."
  [room-id ]
  (:data (forget-room-with-http-info room-id)))

(defn get-joined-rooms-with-http-info
  "Lists the user's current rooms.
  This API returns a list of the user's current rooms."
  []
  (call-api "/_matrix/client/r0/joined_rooms" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn get-joined-rooms
  "Lists the user's current rooms.
  This API returns a list of the user's current rooms."
  []
  (:data (get-joined-rooms-with-http-info)))

(defn invite-by3-pid-with-http-info
  "Invite a user to participate in a particular room.
  .. _invite-by-third-party-id-endpoint:

*Note that there are two forms of this API, which are documented separately.
This version of the API does not require that the inviter know the Matrix
identifier of the invitee, and instead relies on third party identifiers.
The homeserver uses an identity server to perform the mapping from
third party identifier to a Matrix identifier. The other is documented in the*
`joining rooms section`_.

This API invites a user to participate in a particular room.
They do not start participating in the room until they actually join the
room.

Only users currently in a particular room can invite other users to
join that room.

If the identity server did know the Matrix user identifier for the
third party identifier, the homeserver will append a ``m.room.member``
event to the room.

If the identity server does not know a Matrix user identifier for the
passed third party identifier, the homeserver will issue an invitation
which can be accepted upon providing proof of ownership of the third
party identifier. This is achieved by the identity server generating a
token, which it gives to the inviting homeserver. The homeserver will
add an ``m.room.third_party_invite`` event into the graph for the room,
containing that token.

When the invitee binds the invited third party identifier to a Matrix
user ID, the identity server will give the user a list of pending
invitations, each containing:

- The room ID to which they were invited

- The token given to the homeserver

- A signature of the token, signed with the identity server's private key

- The matrix user ID who invited them to the room

If a token is requested from the identity server, the homeserver will
append a ``m.room.third_party_invite`` event to the room.

.. _joining rooms section: `invite-by-user-id-endpoint`_"
  [room-id body ]
  (check-required-params room-id body)
  (call-api "/_matrix/client/r0/rooms/{roomId}/invite" :post
            {:path-params   {"roomId" room-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    body
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn invite-by3-pid
  "Invite a user to participate in a particular room.
  .. _invite-by-third-party-id-endpoint:

*Note that there are two forms of this API, which are documented separately.
This version of the API does not require that the inviter know the Matrix
identifier of the invitee, and instead relies on third party identifiers.
The homeserver uses an identity server to perform the mapping from
third party identifier to a Matrix identifier. The other is documented in the*
`joining rooms section`_.

This API invites a user to participate in a particular room.
They do not start participating in the room until they actually join the
room.

Only users currently in a particular room can invite other users to
join that room.

If the identity server did know the Matrix user identifier for the
third party identifier, the homeserver will append a ``m.room.member``
event to the room.

If the identity server does not know a Matrix user identifier for the
passed third party identifier, the homeserver will issue an invitation
which can be accepted upon providing proof of ownership of the third
party identifier. This is achieved by the identity server generating a
token, which it gives to the inviting homeserver. The homeserver will
add an ``m.room.third_party_invite`` event into the graph for the room,
containing that token.

When the invitee binds the invited third party identifier to a Matrix
user ID, the identity server will give the user a list of pending
invitations, each containing:

- The room ID to which they were invited

- The token given to the homeserver

- A signature of the token, signed with the identity server's private key

- The matrix user ID who invited them to the room

If a token is requested from the identity server, the homeserver will
append a ``m.room.third_party_invite`` event to the room.

.. _joining rooms section: `invite-by-user-id-endpoint`_"
  [room-id body ]
  (:data (invite-by3-pid-with-http-info room-id body)))

(defn invite-user-with-http-info
  "Invite a user to participate in a particular room.
  .. _invite-by-user-id-endpoint:

*Note that there are two forms of this API, which are documented separately.
This version of the API requires that the inviter knows the Matrix
identifier of the invitee. The other is documented in the*
`third party invites section`_.

This API invites a user to participate in a particular room.
They do not start participating in the room until they actually join the
room.

Only users currently in a particular room can invite other users to
join that room.

If the user was invited to the room, the homeserver will append a
``m.room.member`` event to the room.

.. _third party invites section: `invite-by-third-party-id-endpoint`_"
  [room-id body ]
  (check-required-params room-id body)
  (call-api "/_matrix/client/r0/rooms/{roomId}/invite " :post
            {:path-params   {"roomId" room-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    body
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn invite-user
  "Invite a user to participate in a particular room.
  .. _invite-by-user-id-endpoint:

*Note that there are two forms of this API, which are documented separately.
This version of the API requires that the inviter knows the Matrix
identifier of the invitee. The other is documented in the*
`third party invites section`_.

This API invites a user to participate in a particular room.
They do not start participating in the room until they actually join the
room.

Only users currently in a particular room can invite other users to
join that room.

If the user was invited to the room, the homeserver will append a
``m.room.member`` event to the room.

.. _third party invites section: `invite-by-third-party-id-endpoint`_"
  [room-id body ]
  (:data (invite-user-with-http-info room-id body)))

(defn join-room-with-http-info
  "Start the requesting user participating in a particular room.
  *Note that this API takes either a room ID or alias, unlike* ``/room/{roomId}/join``.

This API starts a user participating in a particular room, if that user
is allowed to participate in that room. After this call, the client is
allowed to see all current state events in the room, and all subsequent
events associated with the room until the user leaves the room.

After a user has joined a room, the room will appear as an entry in the
response of the |/initialSync|_ and |/sync|_ APIs.

If a ``third_party_signed`` was supplied, the homeserver must verify
that it matches a pending ``m.room.third_party_invite`` event in the
room, and perform key validity checking if required by the event."
  ([room-id-or-alias ] (join-room-with-http-info room-id-or-alias nil))
  ([room-id-or-alias {:keys [third-party-signed ]}]
   (check-required-params room-id-or-alias)
   (call-api "/_matrix/client/r0/join/{roomIdOrAlias}" :post
             {:path-params   {"roomIdOrAlias" room-id-or-alias }
              :header-params {}
              :query-params  {}
              :form-params   {}
              :body-param    third-party-signed
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn join-room
  "Start the requesting user participating in a particular room.
  *Note that this API takes either a room ID or alias, unlike* ``/room/{roomId}/join``.

This API starts a user participating in a particular room, if that user
is allowed to participate in that room. After this call, the client is
allowed to see all current state events in the room, and all subsequent
events associated with the room until the user leaves the room.

After a user has joined a room, the room will appear as an entry in the
response of the |/initialSync|_ and |/sync|_ APIs.

If a ``third_party_signed`` was supplied, the homeserver must verify
that it matches a pending ``m.room.third_party_invite`` event in the
room, and perform key validity checking if required by the event."
  ([room-id-or-alias ] (join-room room-id-or-alias nil))
  ([room-id-or-alias optional-params]
   (:data (join-room-with-http-info room-id-or-alias optional-params))))

(defn join-room-by-id-with-http-info
  "Start the requesting user participating in a particular room.
  *Note that this API requires a room ID, not alias.* ``/join/{roomIdOrAlias}`` *exists if you have a room alias.*

This API starts a user participating in a particular room, if that user
is allowed to participate in that room. After this call, the client is
allowed to see all current state events in the room, and all subsequent
events associated with the room until the user leaves the room.

After a user has joined a room, the room will appear as an entry in the
response of the |/initialSync|_ and |/sync|_ APIs.

If a ``third_party_signed`` was supplied, the homeserver must verify
that it matches a pending ``m.room.third_party_invite`` event in the
room, and perform key validity checking if required by the event."
  ([room-id ] (join-room-by-id-with-http-info room-id nil))
  ([room-id {:keys [third-party-signed ]}]
   (check-required-params room-id)
   (call-api "/_matrix/client/r0/rooms/{roomId}/join" :post
             {:path-params   {"roomId" room-id }
              :header-params {}
              :query-params  {}
              :form-params   {}
              :body-param    third-party-signed
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn join-room-by-id
  "Start the requesting user participating in a particular room.
  *Note that this API requires a room ID, not alias.* ``/join/{roomIdOrAlias}`` *exists if you have a room alias.*

This API starts a user participating in a particular room, if that user
is allowed to participate in that room. After this call, the client is
allowed to see all current state events in the room, and all subsequent
events associated with the room until the user leaves the room.

After a user has joined a room, the room will appear as an entry in the
response of the |/initialSync|_ and |/sync|_ APIs.

If a ``third_party_signed`` was supplied, the homeserver must verify
that it matches a pending ``m.room.third_party_invite`` event in the
room, and perform key validity checking if required by the event."
  ([room-id ] (join-room-by-id room-id nil))
  ([room-id optional-params]
   (:data (join-room-by-id-with-http-info room-id optional-params))))

(defn kick-with-http-info
  "Kick a user from the room.
  Kick a user from the room.

The caller must have the required power level in order to perform this operation."
  [room-id body ]
  (check-required-params room-id body)
  (call-api "/_matrix/client/r0/rooms/{roomId}/kick" :post
            {:path-params   {"roomId" room-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    body
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn kick
  "Kick a user from the room.
  Kick a user from the room.

The caller must have the required power level in order to perform this operation."
  [room-id body ]
  (:data (kick-with-http-info room-id body)))

(defn leave-room-with-http-info
  "Stop the requesting user participating in a particular room.
  This API stops a user participating in a particular room.

If the user was already in the room, they will no longer be able to see
new events in the room. If the room requires an invite to join, they
will need to be re-invited before they can re-join.

If the user was invited to the room, but had not joined, this call
serves to reject the invite.

The user will still be allowed to retrieve history from the room which
they were previously allowed to see."
  [room-id ]
  (check-required-params room-id)
  (call-api "/_matrix/client/r0/rooms/{roomId}/leave" :post
            {:path-params   {"roomId" room-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn leave-room
  "Stop the requesting user participating in a particular room.
  This API stops a user participating in a particular room.

If the user was already in the room, they will no longer be able to see
new events in the room. If the room requires an invite to join, they
will need to be re-invited before they can re-join.

If the user was invited to the room, but had not joined, this call
serves to reject the invite.

The user will still be allowed to retrieve history from the room which
they were previously allowed to see."
  [room-id ]
  (:data (leave-room-with-http-info room-id)))

(defn unban-with-http-info
  "Unban a user from the room.
  Unban a user from the room. This allows them to be invited to the room,
and join if they would otherwise be allowed to join according to its join rules.

The caller must have the required power level in order to perform this operation."
  [room-id body ]
  (check-required-params room-id body)
  (call-api "/_matrix/client/r0/rooms/{roomId}/unban" :post
            {:path-params   {"roomId" room-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    body
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn unban
  "Unban a user from the room.
  Unban a user from the room. This allows them to be invited to the room,
and join if they would otherwise be allowed to join according to its join rules.

The caller must have the required power level in order to perform this operation."
  [room-id body ]
  (:data (unban-with-http-info room-id body)))

