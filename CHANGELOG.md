# Changelog

This project adheres to [Semantic Versioning](http://semver.org/).

<!--
0.0.0 (YYYY-MM-DD)
------------------

### Breaking Changes
- ...

### Added
- ...

### Changed
- ...

### Fixed
- ...
-->

0.4.0 (2017-12-15)
------------------

### Breaking Changes
- Namespace changed from `boot.lein` to `sparkfund.boot-lein`
- Boot env setting `:boot.lein/project-clj` renamed to `:sparkfund.boot-lein/project-clj` (to match the namespace change)
- Maven coordinates changed from `sparkfund/boot-lein-generate` to `sparkfund/boot-lein` (to match the namespace change)


0.3.0 (2017-03-08)
------------------

### Breaking Changes
- `:url`, `:description`, and `:license` are no longer copied from Boot environment values of the same name
- `:url`, `:description`, and `:scm` are now inferred from `pom` task-options by default

### Added
- It's now possible to add/modify the values put into the `project.clj`.  See [README.md](README.md#usage) for details.


0.2.0 (2017-03-08)
------------------

### Breaking Changes
- Boot task renamed from `generate` to `write-project-clj`

### Changed
- Updated Clojure to `1.9.0-alpha14`
- Updated Boot to `2.7.1`


0.1.3 (2016-10-11)
------------------

### Added
- Emit `(get-env :repositories)`


0.1.2 (2016-10-11)
------------------

### Changed
- Clojure 1.9.0-alpha13 compatibility
