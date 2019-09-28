#!/usr/bin/env sh

set -e

USERNAME="$1"
PASSWORD="$2"

TAG="$3"
TAGCOMMENT="$4"

if [ "${TAGCOMMENT}" == "" ]; then
	TAGCOMMENT="${TAG}"
fi

repos=(
	fusion-splicer-svc
	fusion-splicer-app
	exe-model
)


MAIN_DIR="`readlink -f ../`"

for r in ${repos[@]}; do
	REPO_URL_USER_PASSWD=http://${USERNAME}:${PASSWORD}@gitlab.dev-srv.com/a7_platform/${r}.git

	echo "update ${r}..."
	if ! [ -d ${MAIN_DIR}/${r} ]; then
		cd ${MAIN_DIR}
		git clone ${REPO_URL_USER_PASSWD}
	fi

	cd ${MAIN_DIR}/${r}
	git pull --tags

	if [ "`git tag -l "${TAG}"`" != "" ]; then
		git tag -d "${TAG}"
		git push `git ls-remote --get-url` ":${TAG}"
	fi

	git tag -a "${TAG}" -m "${TAGCOMMENT}"
	git push --tags `git ls-remote --get-url`
done
