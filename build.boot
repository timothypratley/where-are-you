(set-env!
 :source-paths    #{"src/cljs"}
 :resource-paths  #{"resources"}
 :dependencies '[[adzerk/boot-cljs          "1.7.228-1"  :scope "test"]
                 [adzerk/boot-reload        "0.4.12"      :scope "test"]
                 [pandeiro/boot-http        "0.7.3"      :scope "test"]
                 [org.clojure/clojurescript "1.9.89"]
                 [reanimated "0.5.0"]])

(require
 '[adzerk.boot-cljs      :refer [cljs]]
 '[adzerk.boot-reload    :refer [reload]]
 '[pandeiro.boot-http    :refer [serve]])

(deftask build []
  (comp (speak)
        (cljs)))

(deftask run []
  (comp (serve)
        (watch)
        (reload)
        (build)))

(deftask production []
  (task-options! cljs {:optimizations :advanced})
  identity)

(deftask development []
  (task-options! cljs {:optimizations :none :source-map true}
                 reload {:on-jsload 'where-are-you.app/init})
  identity)

(deftask dev
  "Simple alias to run application in development mode"
  []
  (comp (development)
        (run)))

(deftask site
  "Build clojurescript needed for site in production, using advanced compilation."
  []
  (comp (production)
        (build)
        (sift :invert true :include #{#"js/app\.out"})
        (target :dir #{"public"})))
