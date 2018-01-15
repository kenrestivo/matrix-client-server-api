(ns matrix-client-server-api.api.user-data
  (:require [matrix-client-server-api.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn change-password-with-http-info
  "Changes a user's password.
  Changes the password for an account on this homeserver.

This API endpoint uses the `User-Interactive Authentication API`_.

An access token should be submitted to this endpoint if the client has
an active session.

The homeserver may change the flows available depending on whether a
valid access token is provided."
  ([] (change-password-with-http-info nil))
  ([{:keys [body ]}]
   (call-api "/_matrix/client/r0/account/password" :post
             {:path-params   {}
              :header-params {}
              :query-params  {}
              :form-params   {}
              :body-param    body
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn change-password
  "Changes a user's password.
  Changes the password for an account on this homeserver.

This API endpoint uses the `User-Interactive Authentication API`_.

An access token should be submitted to this endpoint if the client has
an active session.

The homeserver may change the flows available depending on whether a
valid access token is provided."
  ([] (change-password nil))
  ([optional-params]
   (:data (change-password-with-http-info optional-params))))

(defn deactivate-account-with-http-info
  "Deactivate a user's account.
  Deactivate the user's account, removing all ability for the user to
login again.

This API endpoint uses the `User-Interactive Authentication API`_.

An access token should be submitted to this endpoint if the client has
an active session.

The homeserver may change the flows available depending on whether a
valid access token is provided."
  ([] (deactivate-account-with-http-info nil))
  ([{:keys [body ]}]
   (call-api "/_matrix/client/r0/account/deactivate" :post
             {:path-params   {}
              :header-params {}
              :query-params  {}
              :form-params   {}
              :body-param    body
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn deactivate-account
  "Deactivate a user's account.
  Deactivate the user's account, removing all ability for the user to
login again.

This API endpoint uses the `User-Interactive Authentication API`_.

An access token should be submitted to this endpoint if the client has
an active session.

The homeserver may change the flows available depending on whether a
valid access token is provided."
  ([] (deactivate-account nil))
  ([optional-params]
   (:data (deactivate-account-with-http-info optional-params))))

(defn delete-room-tag-with-http-info
  "Remove a tag from the room.
  Remove a tag from the room."
  [user-id room-id tag ]
  (check-required-params user-id room-id tag)
  (call-api "/_matrix/client/r0/user/{userId}/rooms/{roomId}/tags/{tag}" :delete
            {:path-params   {"userId" user-id "roomId" room-id "tag" tag }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn delete-room-tag
  "Remove a tag from the room.
  Remove a tag from the room."
  [user-id room-id tag ]
  (:data (delete-room-tag-with-http-info user-id room-id tag)))

(defn get-account3-pi-ds-with-http-info
  "Gets a list of a user's third party identifiers.
  Gets a list of the third party identifiers that the homeserver has
associated with the user's account.

This is *not* the same as the list of third party identifiers bound to
the user's Matrix ID in Identity Servers.

Identifiers in this list may be used by the homeserver as, for example,
identifiers that it will accept to reset the user's account password."
  []
  (call-api "/_matrix/client/r0/account/3pid" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn get-account3-pi-ds
  "Gets a list of a user's third party identifiers.
  Gets a list of the third party identifiers that the homeserver has
associated with the user's account.

This is *not* the same as the list of third party identifiers bound to
the user's Matrix ID in Identity Servers.

Identifiers in this list may be used by the homeserver as, for example,
identifiers that it will accept to reset the user's account password."
  []
  (:data (get-account3-pi-ds-with-http-info)))

(defn get-avatar-url-with-http-info
  "Get the user's avatar URL.
  Get the user's avatar URL. This API may be used to fetch the user's
own avatar URL or to query the URL of other users; either locally or
on remote homeservers."
  [user-id ]
  (check-required-params user-id)
  (call-api "/_matrix/client/r0/profile/{userId}/avatar_url" :get
            {:path-params   {"userId" user-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    []}))

(defn get-avatar-url
  "Get the user's avatar URL.
  Get the user's avatar URL. This API may be used to fetch the user's
own avatar URL or to query the URL of other users; either locally or
on remote homeservers."
  [user-id ]
  (:data (get-avatar-url-with-http-info user-id)))

(defn get-display-name-with-http-info
  "Get the user's display name.
  Get the user's display name. This API may be used to fetch the user's
own displayname or to query the name of other users; either locally or
on remote homeservers."
  [user-id ]
  (check-required-params user-id)
  (call-api "/_matrix/client/r0/profile/{userId}/displayname" :get
            {:path-params   {"userId" user-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    []}))

(defn get-display-name
  "Get the user's display name.
  Get the user's display name. This API may be used to fetch the user's
own displayname or to query the name of other users; either locally or
on remote homeservers."
  [user-id ]
  (:data (get-display-name-with-http-info user-id)))

(defn get-room-tags-with-http-info
  "List the tags for a room.
  List the tags set by a user on a room."
  [user-id room-id ]
  (check-required-params user-id room-id)
  (call-api "/_matrix/client/r0/user/{userId}/rooms/{roomId}/tags" :get
            {:path-params   {"userId" user-id "roomId" room-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn get-room-tags
  "List the tags for a room.
  List the tags set by a user on a room."
  [user-id room-id ]
  (:data (get-room-tags-with-http-info user-id room-id)))

(defn get-token-owner-with-http-info
  "Gets information about the owner of an access token.
  Gets information about the owner of a given access token."
  []
  (call-api "/_matrix/client/r0/account/whoami" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn get-token-owner
  "Gets information about the owner of an access token.
  Gets information about the owner of a given access token."
  []
  (:data (get-token-owner-with-http-info)))

(defn get-user-profile-with-http-info
  "Get this user's profile information.
  Get the combined profile information for this user. This API may be used
to fetch the user's own profile information or other users; either
locally or on remote homeservers. This API may return keys which are not
limited to ``displayname`` or ``avatar_url``."
  [user-id ]
  (check-required-params user-id)
  (call-api "/_matrix/client/r0/profile/{userId}" :get
            {:path-params   {"userId" user-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    []}))

(defn get-user-profile
  "Get this user's profile information.
  Get the combined profile information for this user. This API may be used
to fetch the user's own profile information or other users; either
locally or on remote homeservers. This API may return keys which are not
limited to ``displayname`` or ``avatar_url``."
  [user-id ]
  (:data (get-user-profile-with-http-info user-id)))

(defn post3-pi-ds-with-http-info
  "Adds contact information to the user's account.
  Adds contact information to the user's account."
  ([] (post3-pi-ds-with-http-info nil))
  ([{:keys [body ]}]
   (call-api "/_matrix/client/r0/account/3pid" :post
             {:path-params   {}
              :header-params {}
              :query-params  {}
              :form-params   {}
              :body-param    body
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn post3-pi-ds
  "Adds contact information to the user's account.
  Adds contact information to the user's account."
  ([] (post3-pi-ds nil))
  ([optional-params]
   (:data (post3-pi-ds-with-http-info optional-params))))

(defn register-with-http-info
  "Register for an account on this homeserver.
  This API endpoint uses the `User-Interactive Authentication API`_.

Register for an account on this homeserver.

There are two kinds of user account:

- `user` accounts. These accounts may use the full API described in this specification.

- `guest` accounts. These accounts may have limited permissions and may not be supported by all servers.

If registration is successful, this endpoint will issue an access token
the client can use to authorize itself in subsequent requests.

If the client does not supply a ``device_id``, the server must
auto-generate one.

The server SHOULD register an account with a User ID based on the
``username`` provided, if any. Note that the grammar of Matrix User ID
localparts is restricted, so the server MUST either map the provided
``username`` onto a ``user_id`` in a logical manner, or reject
``username``\\s which do not comply to the grammar, with
``M_INVALID_USERNAME``.

Matrix clients MUST NOT assume that localpart of the registered
``user_id`` matches the provided ``username``.

The returned access token must be associated with the ``device_id``
supplied by the client or generated by the server. The server may
invalidate any access token previously associated with that device. See
`Relationship between access tokens and devices`_."
  ([] (register-with-http-info nil))
  ([{:keys [kind body ]}]
   (call-api "/_matrix/client/r0/register" :post
             {:path-params   {}
              :header-params {}
              :query-params  {"kind" kind }
              :form-params   {}
              :body-param    body
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    []})))

(defn register
  "Register for an account on this homeserver.
  This API endpoint uses the `User-Interactive Authentication API`_.

Register for an account on this homeserver.

There are two kinds of user account:

- `user` accounts. These accounts may use the full API described in this specification.

- `guest` accounts. These accounts may have limited permissions and may not be supported by all servers.

If registration is successful, this endpoint will issue an access token
the client can use to authorize itself in subsequent requests.

If the client does not supply a ``device_id``, the server must
auto-generate one.

The server SHOULD register an account with a User ID based on the
``username`` provided, if any. Note that the grammar of Matrix User ID
localparts is restricted, so the server MUST either map the provided
``username`` onto a ``user_id`` in a logical manner, or reject
``username``\\s which do not comply to the grammar, with
``M_INVALID_USERNAME``.

Matrix clients MUST NOT assume that localpart of the registered
``user_id`` matches the provided ``username``.

The returned access token must be associated with the ``device_id``
supplied by the client or generated by the server. The server may
invalidate any access token previously associated with that device. See
`Relationship between access tokens and devices`_."
  ([] (register nil))
  ([optional-params]
   (:data (register-with-http-info optional-params))))

(defn set-account-data-with-http-info
  "Set some account_data for the user.
  Set some account_data for the client. This config is only visible to the user
that set the account_data. The config will be synced to clients in the
top-level ``account_data``."
  [user-id type content ]
  (check-required-params user-id type content)
  (call-api "/_matrix/client/r0/user/{userId}/account_data/{type}" :put
            {:path-params   {"userId" user-id "type" type }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    content
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn set-account-data
  "Set some account_data for the user.
  Set some account_data for the client. This config is only visible to the user
that set the account_data. The config will be synced to clients in the
top-level ``account_data``."
  [user-id type content ]
  (:data (set-account-data-with-http-info user-id type content)))

(defn set-account-data-per-room-with-http-info
  "Set some account_data for the user.
  Set some account_data for the client on a given room. This config is only
visible to the user that set the account_data. The config will be synced to
clients in the per-room ``account_data``."
  [user-id room-id type content ]
  (check-required-params user-id room-id type content)
  (call-api "/_matrix/client/r0/user/{userId}/rooms/{roomId}/account_data/{type}" :put
            {:path-params   {"userId" user-id "roomId" room-id "type" type }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    content
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn set-account-data-per-room
  "Set some account_data for the user.
  Set some account_data for the client on a given room. This config is only
visible to the user that set the account_data. The config will be synced to
clients in the per-room ``account_data``."
  [user-id room-id type content ]
  (:data (set-account-data-per-room-with-http-info user-id room-id type content)))

(defn set-avatar-url-with-http-info
  "Set the user's avatar URL.
  This API sets the given user's avatar URL. You must have permission to
set this user's avatar URL, e.g. you need to have their ``access_token``."
  [user-id avatar-url ]
  (check-required-params user-id avatar-url)
  (call-api "/_matrix/client/r0/profile/{userId}/avatar_url" :put
            {:path-params   {"userId" user-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    avatar-url
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn set-avatar-url
  "Set the user's avatar URL.
  This API sets the given user's avatar URL. You must have permission to
set this user's avatar URL, e.g. you need to have their ``access_token``."
  [user-id avatar-url ]
  (:data (set-avatar-url-with-http-info user-id avatar-url)))

(defn set-display-name-with-http-info
  "Set the user's display name.
  This API sets the given user's display name. You must have permission to
set this user's display name, e.g. you need to have their ``access_token``."
  [user-id display-name ]
  (check-required-params user-id display-name)
  (call-api "/_matrix/client/r0/profile/{userId}/displayname" :put
            {:path-params   {"userId" user-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    display-name
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn set-display-name
  "Set the user's display name.
  This API sets the given user's display name. You must have permission to
set this user's display name, e.g. you need to have their ``access_token``."
  [user-id display-name ]
  (:data (set-display-name-with-http-info user-id display-name)))

(defn set-room-tag-with-http-info
  "Add a tag to a room.
  Add a tag to the room."
  [user-id room-id tag body ]
  (check-required-params user-id room-id tag body)
  (call-api "/_matrix/client/r0/user/{userId}/rooms/{roomId}/tags/{tag}" :put
            {:path-params   {"userId" user-id "roomId" room-id "tag" tag }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :body-param    body
             :content-types ["application/json"]
             :accepts       ["application/json"]
             :auth-names    ["accessToken"]}))

(defn set-room-tag
  "Add a tag to a room.
  Add a tag to the room."
  [user-id room-id tag body ]
  (:data (set-room-tag-with-http-info user-id room-id tag body)))

