; To inform IntelliJ explicitely about deftask, set-env!, task-options!
(require '[boot.core :refer :all]
         '[boot.task.built-in :refer :all])

(set-env!
  :source-paths #{"src"}
  :dependencies '[[org.clojure/clojure "1.9.0-alpha14" :scope "provided"]
                  [boot/core "2.7.1" :scope "provided"]
                  [adzerk/bootlaces "0.1.13" :scope "test"]])

(require '[adzerk.bootlaces :refer :all]
         '[boot.lein :refer :all])

(def +version+ "0.1.4")

(bootlaces! +version+)

(task-options!
  pom {:project     'sparkfund/boot-lein-generate
       :version     +version+
       :description "Boot task to generate a project.clj from your Boot project, for slightly better interop with Cursive IDE"
       :url         "https://github.com/SparkFund/boot-lein-generate"
       :scm         {:url "https://github.com/SparkFund/boot-lein-generate"}
       :license     {"Eclipse Public License" "http://www.eclipse.org/legal/epl-v10.html"}})


(deftask deps
  "A no-op task you can run to get Boot to install its dependencies.  Helpful in CI"
  []
  nil)
