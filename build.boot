; To inform IntelliJ explicitely about deftask, set-env!, task-options!
(def +version+ "0.4.0")

(require '[boot.core :refer :all])
(task-options!
  pom {:project     'sparkfund/boot-lein
       :version     +version+
       :description "Boot task to generate a project.clj from your Boot project, for slightly better interop with Cursive IDE"
       :url         "https://github.com/SparkFund/boot-lein-generate"
       :scm         {:url "https://github.com/SparkFund/boot-lein-generate"}
       :license     {"Eclipse Public License" "https://www.eclipse.org/legal/epl-v10.html"}})

(set-env!
  :source-paths #{"src"}
  :dependencies '[[org.clojure/clojure "1.9.0" :scope "provided"]
                  [boot/core "2.7.2" :scope "provided"]
                  [adzerk/bootlaces "0.1.13" :scope "test"]])

(require '[adzerk.bootlaces :refer :all]
         '[sparkfund.boot-lein :refer :all])


(bootlaces! +version+)


(deftask deps
  "A no-op task you can run to get Boot to install its dependencies.  Helpful in CI"
  []
  nil)
