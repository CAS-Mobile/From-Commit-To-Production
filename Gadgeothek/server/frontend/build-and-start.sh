#!/usr/bin/env bash

set -o errexit
set -o pipefail
set -o nounset

dir=$(cd -P -- "$(dirname -- "$0")" && pwd -P)


docker build -t static-frontend .
docker run -p 8080:80 -d static-frontend
