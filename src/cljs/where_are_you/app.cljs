(ns where-are-you.app
  (:require
    [reagent.core :as reagent]
    [reanimated.core :as anim]))

(defn main []
  (let [size (reagent/atom 1000)
        roam (reagent/atom 0)
        size-to (anim/interpolate-to size {:duration 10000})
        roam-to (anim/interpolate-to roam {:duration 1000000})
        zoom (reagent/atom 0)
        zoom-spring (anim/spring zoom)]
    (reset! size 20) 10000
    (fn []
      [:div
       {:style {:display "flex"
                :justify-content "center"
                :align-items "center"
                :height "100vh"}}
       [:div
        {:style {:position "fixed"
                 :z-index 0
                 :top "-25vh"
                 :left "-25vw"
                 :width "150vw"
                 :height "150vh"
                 :background-image "url(http://legendsrevealed.com/entertainment/wp-content/uploads/2009/06/atthebeach.jpg)"
                 :transform (str "rotate(" (/ @roam-to 4) "deg)")
                 :background-size (str @size-to "% " @size-to "%")
                 :background-position (str "right " @roam-to "px bottom " (/ @roam-to 4) "px")}}]
       [:div
        {:style {:z-index 1
                 :background "white"
                 :padding-left "50px"
                 :padding-right "50px"
                 :border "1px solid black"
                 :zoom @zoom-spring}}
        [anim/interval #(swap! size (fn [x] (if (= 1000 x) 20 1000))) 10000]
        [anim/timeline
         10000
         [:h1 "Reanimated 0.5.0 is out"]
         #(reset! zoom 1)
         10000
         #(reset! roam 100000)
         #(reset! zoom 0)
         300
         [:h1 "There are some new examples on the github site"]
         #(reset! zoom 1)
         10000
         #(reset! zoom 0)
         300
         [:h1 "Add some pop to your Reagent app"]
         #(reset! zoom 1)
         10000
         #(reset! zoom 0)
         300
         [:p "Where are you Waldo?"]
         #(reset! zoom 1)]]])))

(defn init []
  (reagent/render-component
    [main]
    (.getElementById js/document "container")))
