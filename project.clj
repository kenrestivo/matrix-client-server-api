(defproject org.restivo/matrix-client-server-api "r0.3.0"
  :description "Client library of matrix-client-server-api"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [clj-http "3.7.0"]
                 [cheshire "5.8.0"]]
  :codox {:output-path "docs"}
  :profiles {:dev {:plugins [[lein-codox "0.10.3"]
                             [lein-marginalia "0.9.1"]]}}
  )
