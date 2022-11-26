
SRC_URI = "${GITHUB_MIRROR}/opencv/opencv.git;name=opencv;branch=master;protocol=https \
           ${GITHUB_MIRROR}/opencv/opencv_contrib.git;destsuffix=git/contrib;name=contrib;branch=master;protocol=https \
           ${GITHUB_MIRROR}/opencv/opencv_3rdparty.git;branch=ippicv/master_20191018;destsuffix=git/ipp;name=ipp;protocol=https \
           ${GITHUB_MIRROR}/opencv/opencv_3rdparty.git;branch=contrib_xfeatures2d_boostdesc_20161012;destsuffix=git/boostdesc;name=boostdesc;protocol=https \
           ${GITHUB_MIRROR}/opencv/opencv_3rdparty.git;branch=contrib_xfeatures2d_vgg_20160317;destsuffix=git/vgg;name=vgg;protocol=https \
           ${GITHUB_MIRROR}/opencv/opencv_3rdparty.git;branch=contrib_face_alignment_20170818;destsuffix=git/face;name=face;protocol=https \
           ${GITHUB_MIRROR}/WeChatCV/opencv_3rdparty.git;branch=wechat_qrcode;destsuffix=git/wechat_qrcode;name=wechat-qrcode;protocol=https \
           file://0001-3rdparty-ippicv-Use-pre-downloaded-ipp.patch \
           file://0003-To-fix-errors-as-following.patch \
           file://0001-Temporarliy-work-around-deprecated-ffmpeg-RAW-functi.patch \
           file://0001-Dont-use-isystem.patch \
           file://download.patch \
           file://0001-Make-ts-module-external.patch \
           "
