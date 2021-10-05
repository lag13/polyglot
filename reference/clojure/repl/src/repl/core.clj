(ns repl.core
  (:gen-class))

(defn tokenize [s]
  (remove empty? (clojure.string/split (clojure.string/trim s) #" ")))

(defn repl [{:keys [dispatcher keep-looping] :as world}]
  (when keep-looping
    (let [input (read-line)
          [cmd & args] (tokenize input)]
      (if-let [cmd-fn (get dispatcher cmd)]
        (recur (try
                 (merge world (cmd-fn world args))
                 (catch Exception e
                   (do (println (.getMessage e))
                       world))))
        (do (println (str "command \"" cmd "\" not known"))
            (recur world))))))

(defn cmd-exit-loop [world args]
  (assoc world :keep-looping false))

(defn cmd-echo [world args]
  (println (clojure.string/join " " args)))

(defn -main [& args]
  (repl {:dispatcher {"exit" #'cmd-exit-loop
                      "echo" #'cmd-echo}
         :keep-looping true}))
