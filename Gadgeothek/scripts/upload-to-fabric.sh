#!/usr/bin/env bash

set -o errexit
set -o pipefail
set -o nounset

dir=$(cd -P -- "$(dirname -- "$0")" && pwd -P)
root="${dir}/.."


version_code=$(git rev-list --all --count)

(cd "${root}"; ./gradlew -PversionCode=${version_code} clean assembleDebug crashlyticsUploadDistributionFullDebug)
