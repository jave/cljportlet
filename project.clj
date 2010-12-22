(defproject cljportlet "1.0.0-SNAPSHOT"
  :description "FIXME: write"
  :aot [cljportlet.Portlet]
  :dev-dependencies [[swank-clojure "1.2.0"]
                     [uk.org.alienscience/leiningen-war "0.0.10"]
                     [javax.portlet/portlet-api "2.0"]
                     [javax.servlet/servlet-api "2.5"]                 
]
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]])