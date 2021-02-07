SUMMARY = "Integrated Development Environment + qt5 support"
DESCRIPTION = "Meta package for ensuring the build directory contains all appropriate toolchain packages for using an IDE & QTE"
LICENSE = "MIT"

DEPENDS = "virtual/libc \
           bc-native \
           coreutils-native \
           qttools-native \
           u-boot-mkimage-native \
           gdb-cross-${TARGET_ARCH} \
           \
	postgresql \
	opencv \
	gsl \
	lua \
	libwebsockets \
	libpng \
	libpqxx \
	xlslib \
	\
                  qtbase qtbase qtsvg \
                  qtdeclarative \
                  qtgraphicaleffects \
                  qtvirtualkeyboard \
                  qtquickcontrols2 \
                  qtwebsockets \
                  qtsvg \
                  qtwebsockets \
                  qtvirtualkeyboard \
                  source-han-sans-cn-fonts \
"

PR = "r1"
RM_WORK_EXCLUDE += "${PN}"

inherit meta toolchain-scripts nopackages

inherit qmake5_paths

QT_TOOLS_PREFIX = "${STAGING_DIR_NATIVE}${OE_QMAKE_PATH_HOST_BINS}"

toolchain_create_tree_env_script_append() {
    #mv $script $script-qt5
    #script=$script-qt5
    #
    #echo 'export QT_DIR_NAME="${QT_DIR_NAME}"' >> $script
    #
    #echo 'export OE_QMAKE_CFLAGS="$CFLAGS"' >> $script
    #echo 'export OE_QMAKE_CXXFLAGS="$CXXFLAGS"' >> $script
    #echo 'export OE_QMAKE_LDFLAGS="$LDFLAGS"' >> $script
    #echo 'export OE_QMAKE_CC="$CC"' >> $script
    #echo 'export OE_QMAKE_CXX="$CXX"' >> $script
    #echo 'export OE_QMAKE_LINK="$CXX"' >> $script
    #echo 'export OE_QMAKE_AR="$AR"' >> $script
    #echo 'export OE_QMAKE_STRIP="$STRIP"' >> $script
    #echo 'export OE_QMAKE_LIBDIR_QT=${STAGING_DIR_TARGET}${OE_QMAKE_PATH_LIBS}' >> $script
    #echo 'export OE_QMAKE_INCDIR_QT=${STAGING_DIR_TARGET}${OE_QMAKE_PATH_QT_HEADERS}' >> $script
    #echo 'export OE_QMAKE_QMAKE=${QT_TOOLS_PREFIX}/qmake' >> $script
    #echo 'export OE_QMAKE_MOC=${QT_TOOLS_PREFIX}/moc' >> $script
    #echo 'export OE_QMAKE_UIC=${QT_TOOLS_PREFIX}/uic' >> $script
    #echo 'export OE_QMAKE_RCC=${QT_TOOLS_PREFIX}/rcc' >> $script
    #echo 'export OE_QMAKE_QDBUSCPP2XML=${QT_TOOLS_PREFIX}/qdbuscpp2xml' >> $script
    #echo 'export OE_QMAKE_QDBUSXML2CPP=${QT_TOOLS_PREFIX}/qdbusxml2cpp' >> $script
    #echo 'export OE_QMAKE_LRELEASE=${QT_TOOLS_PREFIX}/lrelease' >> $script
    #echo 'export OE_QMAKE_LUPDATE=${QT_TOOLS_PREFIX}/lupdate' >> $script
    #echo 'export OE_QMAKE_LCONVERT=${QT_TOOLS_PREFIX}/lconvert' >> $script
    #echo 'export OE_QMAKE_QT_CONFIG=${STAGING_DIR_TARGET}${OE_QMAKE_PATH_LIBS}/${QT_DIR_NAME}/mkspecs/qconfig.pri' >> $script
    #echo 'export QMAKESPEC=${STAGING_DIR_TARGET}${OE_QMAKE_PATH_LIBS}/${QT_DIR_NAME}/mkspecs/linux-oe-g++' >> $script
    #echo 'export OE_QMAKESPEC=${QMAKE_MKSPEC_PATH_NATIVE}/mkspecs/linux-oe-g++' >> $script
    #echo 'export OE_XQMAKESPEC=${QMAKE_MKSPEC_PATH}/mkspecs/linux-oe-g++' >> $script
    #echo 'export QT_CONF_PATH=${QT_TOOLS_PREFIX}/qt.conf' >> $script
    #
    ## make a symbolic link to mkspecs for compatibility with QTCreator
    #(cd ${STAGING_DIR_NATIVE}; \
    #     ln -sf ${STAGING_DIR_TARGET}${OE_QMAKE_PATH_LIBS}/${QT_DIR_NAME}/mkspecs mkspecs;)

    mkdir -p ${STAGING_DIR_TARGET}/environment-setup.d/
    script=${STAGING_DIR_TARGET}/environment-setup.d/qt5.sh

    echo 'export PATH=${STAGING_DIR_NATIVE}${OE_QMAKE_PATH_HOST_BINS}:$PATH' >> $script
    echo 'export OE_QMAKE_CFLAGS="$CFLAGS"' >> $script
    echo 'export OE_QMAKE_CXXFLAGS="$CXXFLAGS"' >> $script
    echo 'export OE_QMAKE_LDFLAGS="$LDFLAGS"' >> $script
    echo 'export OE_QMAKE_CC="$CC"' >> $script
    echo 'export OE_QMAKE_CXX="$CXX"' >> $script
    echo 'export OE_QMAKE_LINK="$CXX"' >> $script
    echo 'export OE_QMAKE_AR=$AR' >> $script
    echo 'export OE_QMAKE_STRIP=$STRIP' >> $script
    echo 'export QT_CONF_PATH=${QT_TOOLS_PREFIX}/qt.conf' >> $script
    echo 'export OE_QMAKE_LIBDIR_QT=`qmake -query QT_INSTALL_LIBS`' >> $script
    echo 'export OE_QMAKE_INCDIR_QT=`qmake -query QT_INSTALL_HEADERS`' >> $script
    echo 'export OE_QMAKE_MOC=${QT_TOOLS_PREFIX}/moc' >> $script
    echo 'export OE_QMAKE_UIC=${QT_TOOLS_PREFIX}/uic' >> $script
    echo 'export OE_QMAKE_RCC=${QT_TOOLS_PREFIX}/rcc' >> $script
    echo 'export OE_QMAKE_QDBUSCPP2XML=${QT_TOOLS_PREFIX}/qdbuscpp2xml' >> $script
    echo 'export OE_QMAKE_QDBUSXML2CPP=${QT_TOOLS_PREFIX}/qdbusxml2cpp' >> $script
    echo 'export OE_QMAKE_QT_CONFIG=`qmake -query QT_INSTALL_LIBS`${QT_DIR_NAME}/mkspecs/qconfig.pri' >> $script
    echo 'export OE_QMAKE_PATH_HOST_BINS=${QT_TOOLS_PREFIX}' >> $script
    echo 'export QMAKESPEC=`qmake -query QT_INSTALL_LIBS`${QT_DIR_NAME}/mkspecs/linux-oe-g++' >> $script

    # make a symbolic link to mkspecs for compatibility with QTCreator
    (cd ${STAGING_DIR_NATIVE}${OE_QMAKE_PATH_LIBS}/${QT_DIR_NAME}; \
          mv mkspecs mkspecs.native; \
          ln -sf ${STAGING_DIR_TARGET}${OE_QMAKE_PATH_LIBS}/${QT_DIR_NAME}/mkspecs mkspecs;)

    # Use relocable sysroot
    sed -i -e 's:${STAGING_DIR_NATIVE}/:$OECORE_NATIVE_SYSROOT/:g' $script
    sed -i -e 's:${OSTAGING_DIR_TARGET}/:$OECORE_TARGET_SYSROOT/:g' $script

    # Generate a qt.conf file to be deployed with the SDK
    qtconf=${QT_TOOLS_PREFIX}/qt.conf
    rm -rf $qtconf
    touch $qtconf
    echo '[Paths]' >> $qtconf
    echo 'Prefix = ${OE_QMAKE_PATH_PREFIX}' >> $qtconf
    echo 'Headers = ${OE_QMAKE_PATH_QT_HEADERS}' >> $qtconf
    echo 'Libraries = ${OE_QMAKE_PATH_LIBS}' >> $qtconf
    echo 'ArchData = ${OE_QMAKE_PATH_QT_ARCHDATA}' >> $qtconf
    echo 'Data = ${OE_QMAKE_PATH_QT_DATA}' >> $qtconf
    echo 'Binaries = ${OE_QMAKE_PATH_QT_BINS}' >> $qtconf
    echo 'LibraryExecutables = ${OE_QMAKE_PATH_LIBEXECS}' >> $qtconf
    echo 'Plugins = ${OE_QMAKE_PATH_PLUGINS}' >> $qtconf
    echo 'Qml2Imports = ${OE_QMAKE_PATH_QML}' >> $qtconf
    echo 'Translations = ${OE_QMAKE_PATH_QT_TRANSLATIONS}' >> $qtconf
    echo 'Documentation = ${OE_QMAKE_PATH_QT_DOCS}' >> $qtconf
    echo 'Settings = ${OE_QMAKE_PATH_QT_SETTINGS}' >> $qtconf
    echo 'Examples = ${OE_QMAKE_PATH_QT_EXAMPLES}' >> $qtconf
    echo 'Tests = ${OE_QMAKE_PATH_QT_TESTS}' >> $qtconf
    echo 'HostPrefix = ${STAGING_DIR_NATIVE}' >> $qtconf
    echo 'HostData = ${STAGING_DIR_NATIVE}${OE_QMAKE_PATH_QT_ARCHDATA}' >> $qtconf
    echo 'HostBinaries = ${STAGING_DIR_NATIVE}${OE_QMAKE_PATH_HOST_BINS}' >> $qtconf
    echo 'HostLibraries = ${STAGING_DIR_NATIVE}${OE_QMAKE_PATH_LIBS}' >> $qtconf
    echo 'Sysroot = ${STAGING_DIR_TARGET}' >> $qtconf
}

do_populate_ide_support () {
  toolchain_create_tree_env_script
}

addtask populate_ide_support before do_build after do_install

