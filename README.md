# boot-lein-generate

[![Clojars Project](https://img.shields.io/clojars/v/sparkfund/boot-lein-generate.svg)](https://clojars.org/sparkfund/boot-lein-generate)

[Boot](https://github.com/boot-clj/boot) task for generating a `project.clj` from your Boot project.
This will allow [Cursive IDE](https://cursive-ide.com/) to work with your Boot project.

```clojure
;in your build.boot
(set-env! :dependencies '[[sparkfund/boot-lein-generate "0.1.3"]])
(require '[boot.lein :refer :all])

;now you can run `boot write-project-clj`
```

Forked from [onetom's repo](https://github.com/onetom/boot-lein-generate) (which was itself forked
from [darongmean's repo](https://github.com/darongmean/boot-lein-generate)) to add some extra
properties and configurability in scenarios where `boot.core/get-env` doesn't include all the
information you need it to include in the `project.clj`.  As an example, you might wish to add
`:repl-options` into `project.clj`, or extend the set of directories Cursive will tell IntelliJ to
mark as source roots.


## Usage

Add it to your `build.boot` (see above) and then run `boot write-project.clj`.


## License

Copyright 2016-2017
[Darong Mean](https://github.com/darongmean/boot-lein-generate),
[Tamas Herman](https://github.com/onetom/boot-lein-generate),
and [SparkFund](https://github.com/SparkFund/boot-lein-generate)

Distributed under the [Eclipse Public License, Version 1.0](LICENSE.md), the same as Clojure.
