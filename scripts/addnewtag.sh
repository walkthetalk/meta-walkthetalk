#!/usr/bin/env sh

set -e

print_help ()
{
	echo "usage: $0 [option] <user> <password> <tag> <commit>"
	echo "option:"
	echo "     -d    just delete tag."
}

JUST_DELETE="false"
while getopts ":dh" opt
do
	case $opt in
		h )
			print_help
			exit 0
			;;
		d )
			JUST_DELETE="true"
			;;
		? )
			print_help
			exit 1
			;;
	esac
done
shift $(($OPTIND - 1))


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
	kl360-drv
)


MAIN_DIR="`readlink -f ../`"

for r in ${repos[@]}; do
	REPO_URL_USER_PASSWD=http://${USERNAME}:${PASSWORD}@gitlab.dev-srv.com/jl/${r}.git

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

	if [ "${JUST_DELETE}" == "false" ]; then
		git tag -a "${TAG}" -m "${TAGCOMMENT}"
		git push --tags `git ls-remote --get-url`
	fi
done
