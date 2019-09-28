DESCRIPTION = "GNU Scientific Library"
HOMEPAGE = "http://www.gnu.org/software/gsl/"
SECTION = "libs"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

inherit autotools pkgconfig binconfig

LEAD_SONAME = "libgsl.so"

SRC_URI = "${GNU_MIRROR}/gsl/gsl-${PV}.tar.gz"

SRC_URI[md5sum] = "dba736f15404807834dc1c7b93e83b92"
SRC_URI[sha256sum] = "4d46d07b946e7b31c19bbf33dda6204d7bedc2f5462a1bae1d4013426cd1ce9b"
