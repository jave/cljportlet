(ns cljportlet.Portlet
  (:require clojure.contrib.server-socket)
  (:gen-class :extends javax.portlet.GenericPortlet
              :exposes-methods {init superInit})

  (:import
   [javax.portlet RenderRequest RenderResponse]
   [javax.servlet.http Cookie])
  )

;;(defservice application)
(def *myrepl*)

;;TODO *test* should be portlet state
(def *test* 1)

;;TODO should handle more acctions. maybe as a ring handler?
(defn -processAction  [this request
                response]
  (def *test* (+ 1 *test*))
  (. System/out (println (str "processaction called" *test*)))
  )

(defn start-repl []
  (. System/out (println "starting a REPL"))
  (def *myrepl* (clojure.contrib.server-socket/create-repl-server 9098)))

  ;;it felt posible to overide only the (-init [this]) signature, but that didnt work well for some reason
  ;;  (. this SuperClass/init config) ;;this seems to call the method recursively?

;;i tried numerous implementations for -init, its not completely obvious why it turned out this way
(defn -init
  ([this config]
     (. System/out (println "init 2"))
     (start-repl)
     (.superInit this config))
  ([this]
     (. System/out (println "init 1"))
     ;;(start-repl)
     (.superInit this))
)

;;we need to destroy the socket repl when undeploying the portlet
(defn -destroy [this]
  (. System/out (println "destroying cljportlet"))
  (clojure.contrib.server-socket/close-server *myrepl*))


(defn -doView  [this request
                response]
  (.setContentType response "text/html")
    ;;TODO use hiccup to make html
  (.println (.getWriter response) (str "Hello clojure world ! Counter: <a href=\"" (. response createActionURL) "\">url</a>" *test*))
  
  )