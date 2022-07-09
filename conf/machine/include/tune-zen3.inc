require conf/machine/include/tune-amd64.inc

TUNEVALID[zen3] = "Enable AMD x86 (64 bit) Zen 3 Core Architecture specific optimizations"
TUNE_FEATURES:tune-amd64 = "m64 zen3"
TUNE_CCARGS .= "${@bb.utils.contains('TUNE_FEATURES', 'zen3', ' -march=znver3', '', d)}"

