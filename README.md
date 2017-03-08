# boot-lein-generate

Generate a leiningen `project.clj` file from your boot environment.  Cursive IDE can then
read that and understand at least some things about your project.

Forked from [onetom's repo](https://github.com/onetom/boot-lein-generate) (which was itself forked
from [darongmean's repo](https://github.com/darongmean/boot-lein-generate)) to add some extra
properties and configurability in scenarios where `boot.core/get-env` doesn't include all the
information you need it to include in the `project.clj`.  As an example, you might wish to add
`:repl-options` into `project.clj`.

## Usage

Add boot-lein-generate to your build.boot dependencies and require the namespace:

```clj
(set-env! :dependencies '[[sparkfund/boot-lein-generate "0.1.3"]])
(require 'boot.lein)
(boot.lein/generate)
```

This ensures updating `project.clj` on every run of boot.
This way we can add `project.clj` to our `.gitignore` and have IntelliJ+Cursive
see the latest dependencies.

## License

Copyright 2016-2017
[Darong Mean](https://github.com/darongmean/boot-lein-generate),
[Tamas Herman](https://github.com/onetom/boot-lein-generate),
and [SparkFund](https://github.com/SparkFund/boot-lein-generate)

Distributed under the Eclipse Public License, the same as Clojure.
