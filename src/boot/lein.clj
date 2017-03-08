(ns boot.lein
  {:boot/export-tasks true}
  (:require [clojure.java.io :as io]
            [boot.core :as boot]
            [boot.util :as util]))

(defn- pom-task-option
  "Helper to grab a config option from the `pom` builtin task"
  ([key] (pom-task-option key nil))
  ([key not-found]
   (let [pom-options (:task-options (meta #'boot.task.built-in/pom))]
     (get pom-options key not-found))))


(defn- base-project-info
  "Builds a map describing the values that will go into project.clj, by
  scraping various bits of information out of the Boot environment

  Mapping from https://github.com/boot-clj/boot/wiki/Boot-Environment to
  https://github.com/technomancy/leiningen/blob/master/sample.project.clj"
  []
  {;grab some values from `pom`
   :url            (pom-task-option :url)
   :description    (pom-task-option :description)
   :scm            (pom-task-option :scm)
   ;pomegranate/dependency related
   :repositories   (into [] (boot/get-env :repositories))
   :mirrors        (into [] (boot/get-env :mirrors))
   :dependencies   (into [] (boot/get-env :dependencies))
   :exclusions     (into [] (boot/get-env :exclusions))
   :offline?       (boot/get-env :offline? false)
   ;filesystem layout
   :source-paths   (into [] (boot/get-env :source-paths))
   :resource-paths (into [] (boot/get-env :resource-paths))})

(defn- remove-nil-values
  "Given a map, this will return a new map which omits any entries where the value is nil."
  [m]
  (into {} (remove (fn [[k v]] (nil? v)) m)))

(defn- write-project-clj!
  "Internal helper function that does the actual work of formatting and writing the project."
  []
  (let [pfile (io/file "project.clj")
        info (remove-nil-values (base-project-info))
        project (pom-task-option :project 'boot-project)
        version (pom-task-option :version "0.0.0-SNAPSHOT")
        symbs (apply concat ['defproject project version] info)]
    (spit pfile (util/pp-str symbs))))

(boot/deftask write-project-clj
  "Generate a leiningen `project.clj` file.
   This task generates a leiningen `project.clj` file based on the boot
   environment configuration, including project name and version (generated
   if not present), dependencies, and source paths. Additional keys may be added
   to the generated `project.clj` file by specifying a `:lein` key in the boot
   environment whose value is a map of keys-value pairs to add to `project.clj`."
  []
  (write-project-clj!))
