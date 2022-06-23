SRC_URI = "${GITHUB_MIRROR}/opencv/opencv.git;name=opencv;branch=master;protocol=${GITHUB_PROTOCOL} \
           ${GITHUB_MIRROR}/opencv/opencv_contrib.git;destsuffix=contrib;name=contrib;branch=master;protocol=${GITHUB_PROTOCOL} \
           ${GITHUB_MIRROR}/opencv/opencv_3rdparty.git;branch=ippicv/master_20191018;destsuffix=ipp;name=ipp;protocol=${GITHUB_PROTOCOL} \
           ${GITHUB_MIRROR}/opencv/opencv_3rdparty.git;branch=contrib_xfeatures2d_boostdesc_20161012;destsuffix=boostdesc;name=boostdesc;protocol=${GITHUB_PROTOCOL} \
           ${GITHUB_MIRROR}/opencv/opencv_3rdparty.git;branch=contrib_xfeatures2d_vgg_20160317;destsuffix=vgg;name=vgg;protocol=${GITHUB_PROTOCOL} \
           ${GITHUB_MIRROR}/opencv/opencv_3rdparty.git;branch=contrib_face_alignment_20170818;destsuffix=face;name=face;protocol=${GITHUB_PROTOCOL} \
           ${GITHUB_MIRROR}/WeChatCV/opencv_3rdparty.git;branch=wechat_qrcode;destsuffix=wechat_qrcode;name=wechat-qrcode;protocol=${GITHUB_PROTOCOL} \
           file://0001-3rdparty-ippicv-Use-pre-downloaded-ipp.patch \
           file://0003-To-fix-errors-as-following.patch \
           file://0001-Temporarliy-work-around-deprecated-ffmpeg-RAW-functi.patch \
           file://0001-Dont-use-isystem.patch \
           file://download.patch \
           file://0001-Make-ts-module-external.patch \
           file://0001-core-vsx-update-vec_absd-workaround-condition.patch \
           "
