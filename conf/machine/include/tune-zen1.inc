require conf/machine/include/tune-amd64.inc

TUNEVALID[zen1] = "Enable AMD x86 (64 bit) Zen 1 Core Architecture specific optimizations"
TUNE_FEATURES:tune-amd64 = "m64 zen1"
TUNE_CCARGS .= "${@bb.utils.contains('TUNE_FEATURES', 'zen1', ' -march=znver1', '', d)}"

