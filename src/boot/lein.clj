(ns boot.lein
  {:boot/export-tasks true}
  (:require [clojure.java.io :as io]
            [boot.core :as core]
            [boot.util :as util]))

(defn- write-project-clj! [& {:keys [keep-project] :or {keep-project true}}]
  (let [pfile (io/file "project.clj")
        ; Only works when pom options are set using task-options!
        {:keys [project version]} (:task-options (meta #'boot.task.built-in/pom))
        prop #(when-let [x (core/get-env %2)] [%1 x])
        head (list* 'defproject (or project 'boot-project) (or version "0.0.0-SNAPSHOT")
                    (concat
                      (prop :url :url)
                      (prop :license :license)
                      (prop :description :description)
                      [:repositories (core/get-env :repositories)
                       :dependencies (core/get-env :dependencies)
                       :source-paths (into [] (core/get-env :source-paths))
                       :resource-paths (into [] (core/get-env :resource-paths))]))
        proj (util/pp-str head)]
    (if-not keep-project (.deleteOnExit pfile))
    (spit pfile proj)))

(core/deftask write-project-clj
  "Generate a leiningen `project.clj` file.
   This task generates a leiningen `project.clj` file based on the boot
   environment configuration, including project name and version (generated
   if not present), dependencies, and source paths. Additional keys may be added
   to the generated `project.clj` file by specifying a `:lein` key in the boot
   environment whose value is a map of keys-value pairs to add to `project.clj`."
  []
  (write-project-clj! :keep-project true))
