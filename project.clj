(defproject
  boot-project
  "0.0.0-SNAPSHOT"
  :comment
  "Generated from build.boot for Cursive"
  :dependencies
  [[adzerk/boot-cljs "1.7.228-1" :scope "test"]
   [adzerk/boot-reload "0.4.12" :scope "test"]
   [pandeiro/boot-http "0.7.3" :scope "test"]
   [org.clojure/clojurescript "1.9.89"]
   [reanimated "0.5.0"]]
  :repositories
  [["clojars" {:url "https://clojars.org/repo/"}]
   ["maven-central" {:url "https://repo1.maven.org/maven2"}]]
  :source-paths
  ["src/cljs" "resources"])