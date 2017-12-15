(ns sparkfund.boot-lein
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
  scraping various bits of information out of the Boot environment"
  ;Mapping drawn roughly from https://github.com/boot-clj/boot/wiki/Boot-Environment
  ;to https://github.com/technomancy/leiningen/blob/master/sample.project.clj
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
   :offline?       (boot/get-env :offline?)
   ;filesystem layout
   :source-paths   (into [] (boot/get-env :source-paths))
   :resource-paths (into [] (boot/get-env :resource-paths))})

(defn- remove-nil-values
  "Given a map, this will return a new map which omits any entries where the value is nil."
  [m]
  (into {} (remove (fn [[k v]] (nil? v)) m)))

(defn- write-project-clj!
  "Internal helper function that does the actual work of formatting and writing the project."
  [info-overrides]
  (let [pfile (io/file "project.clj")
        info-base (base-project-info)
        info-env (boot/get-env :sparkfund.boot-lein/project-clj)
        info (merge info-base info-env info-overrides)
        project (pom-task-option :project 'boot-project)
        version (pom-task-option :version "0.0.0-SNAPSHOT")
        symbs (apply concat ['defproject project version] (remove-nil-values info))]
    (spit pfile (util/pp-str symbs))))

(boot/deftask write-project-clj
  "Generates a simple `project.clj` file and writes it to disk.  This can help e.g. Cursive to detect dependencies correctly.

  The generated file is based upon the boot environment configuration; the project name/version are drawn from the
  task-options that have been set for the `pom` built-in task.

  You can override / add more values to your `project.clj` in one of two ways.  You can either pass a map as an
  option (`override`) to this task, or you can set a Boot environment property:  (set-env! :sparkfund.boot-lein/project-clj {:key :val})
  The override map passed directly to this task takes precedence over the values from the Boot environment property.

  Note that these maps are combined with (merge), so you will override values, not extend them."
  [o override PROP=VAL {code code} "Values to override in the project.clj"]
  (write-project-clj! override))
