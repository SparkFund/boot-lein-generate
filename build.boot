; To inform IntelliJ explicitely about deftask, set-env!, task-options!
(require '[boot.core :refer :all]
         '[boot.task.built-in :refer :all])

(set-env!
  :source-paths #{"src"}
  :dependencies '[[org.clojure/clojure "1.9.0-alpha13" :scope "provided"]
                  [boot/core "2.6.0" :scope "provided"]
                  [adzerk/bootlaces "0.1.13" :scope "test"]])

(require '[adzerk.bootlaces :refer :all]
         '[boot.lein :refer :all])

(def +version+ "0.1.3")

(bootlaces! +version+)

(task-options!
  pom {:project     'onetom/boot-lein-generate
       :version     +version+
       :description "Boot task to generate project.clj file"
       :url         "https://github.com/onetom/boot-lein-generate"
       :scm         {:url "https://github.com/onetom/boot-lein-generate"}
       :license     {"Eclipse Public License" "http://www.eclipse.org/legal/epl-v10.html"}})
