(defproject navis/untangled-client "0.4.5-SNAPSHOT"
  :description "Client-side code for Untangled Webapps"
  :url ""
  :license {:name "NAVIS"
            :url  "http://www.thenavisway.com"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [differ "0.2.1"]
                 [lein-doo "0.1.6" :scope "test"]
                 [com.lucasbradstreet/cljs-uuid-utils "1.0.2"]
                 [navis/untangled-spec "0.3.5" :scope "test"]
                 [org.omcljs/om "1.0.0-alpha30" :scope "test"]
                 [figwheel-sidecar "0.5.0-3" :scope "provided"]
                 [camel-snake-kebab "0.3.2"]]

  :jvm-opts ["-XX:-OmitStackTraceInFastThrow"]
  :clean-targets ^{:protect false} ["resources/private/js" "resources/public/js/test" "resources/public/js/compiled" "target"]
  :source-paths ["src" "spec"]
  :test-paths ["spec"]
  :test-refresh {:report untangled-spec.reporters.terminal/untangled-report
                 :changes-only true
                 :with-repl true}
  :resource-paths ["src" "resources"]

  :plugins [[lein-cljsbuild "1.1.2"]
            [lein-doo "0.1.6"]
            [lein-figwheel "0.5.0-3"]
            [com.jakemccrary/lein-test-refresh "0.14.0"]]

  :doo {:build "automated-tests"
        :paths {:karma "node_modules/karma/bin/karma"}}

  :cljsbuild {:builds
              [{:id           "test"
                :source-paths ["src" "dev" "spec"]
                :figwheel     {:on-jsload "cljs.user/on-load"}
                :compiler     {:main                 cljs.user
                               :output-to            "resources/public/js/test/test.js"
                               :output-dir           "resources/public/js/test/out"
                               :recompile-dependents true
                               :asset-path           "js/test/out"
                               :optimizations        :none}}
               {:id           "automated-tests"
                :source-paths ["spec" "src"]
                :compiler     {:output-to     "resources/private/js/unit-tests.js"
                               :main          untangled.all-tests
                               :output-dir    "resources/private/js/out"
                               :asset-path    "js/out"
                               :optimizations :none}}]}

  :profiles {:dev {:source-paths ["src" "test" "dev"]
                   :repl-options {:init-ns          clj.user
                                  :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :env          {:dev true}
                   :dependencies [[figwheel-sidecar "0.5.0-3"]
                                  [binaryage/devtools "0.5.2"]
                                  [com.cemerick/piggieback "0.2.1"]
                                  [org.clojure/tools.namespace "0.2.11"]
                                  [org.clojure/tools.nrepl "0.2.12"]]}}

  :figwheel {:server-port 3450
             :css-dirs    ["resources/public/css"]})
