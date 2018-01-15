(ns matrix-client-server-api.api.media
  (:require [matrix-client-server-api.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn get-content-with-http-info
  "Download content from the content repository."
  [server-name media-id ]
  (check-required-params server-name media-id)
  (call-api "/_matrix/media/r0/download/{serverName}/{mediaId}" :get
            {:path-params   {"serverName" server-name "mediaId" media-id }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["*/*"]
             :auth-names    []}))

(defn get-content
  "Download content from the content repository."
  [server-name media-id ]
  (:data (get-content-with-http-info server-name media-id)))

(defn get-content-override-name-with-http-info
  "Download content from the content repository as a given filename."
  [server-name media-id file-name ]
  (check-required-params server-name media-id file-name)
  (call-api "/_matrix/media/r0/download/{serverName}/{mediaId}/{fileName}" :get
            {:path-params   {"serverName" server-name "mediaId" media-id "fileName" file-name }
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types ["application/json"]
             :accepts       ["*/*"]
             :auth-names    []}))

(defn get-content-override-name
  "Download content from the content repository as a given filename."
  [server-name media-id file-name ]
  (:data (get-content-override-name-with-http-info server-name media-id file-name)))

(defn get-content-thumbnail-with-http-info
  "Download a thumbnail of the content from the content repository."
  ([server-name media-id ] (get-content-thumbnail-with-http-info server-name media-id nil))
  ([server-name media-id {:keys [width height method ]}]
   (check-required-params server-name media-id)
   (call-api "/_matrix/media/r0/thumbnail/{serverName}/{mediaId}" :get
             {:path-params   {"serverName" server-name "mediaId" media-id }
              :header-params {}
              :query-params  {"width" width "height" height "method" method }
              :form-params   {}
              :content-types ["application/json"]
              :accepts       ["image/jpeg" "image/png"]
              :auth-names    []})))

(defn get-content-thumbnail
  "Download a thumbnail of the content from the content repository."
  ([server-name media-id ] (get-content-thumbnail server-name media-id nil))
  ([server-name media-id optional-params]
   (:data (get-content-thumbnail-with-http-info server-name media-id optional-params))))

(defn get-url-preview-with-http-info
  "Get information about a URL for a client"
  ([url ] (get-url-preview-with-http-info url nil))
  ([url {:keys [ts ]}]
   (check-required-params url)
   (call-api "/_matrix/media/r0/preview_url" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"url" url "ts" ts }
              :form-params   {}
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn get-url-preview
  "Get information about a URL for a client"
  ([url ] (get-url-preview url nil))
  ([url optional-params]
   (:data (get-url-preview-with-http-info url optional-params))))

(defn upload-content-with-http-info
  "Upload some content to the content repository."
  ([content ] (upload-content-with-http-info content nil))
  ([content {:keys [content-type filename ]}]
   (check-required-params content)
   (call-api "/_matrix/media/r0/upload" :post
             {:path-params   {}
              :header-params {"Content-Type" content-type }
              :query-params  {"filename" filename }
              :form-params   {}
              :body-param    content
              :content-types ["application/json"]
              :accepts       ["application/json"]
              :auth-names    ["accessToken"]})))

(defn upload-content
  "Upload some content to the content repository."
  ([content ] (upload-content content nil))
  ([content optional-params]
   (:data (upload-content-with-http-info content optional-params))))

