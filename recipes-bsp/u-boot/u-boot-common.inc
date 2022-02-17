LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"


DEPENDS += "bison-native"

UBOOT_SRC ?= "git://github.com/Trucrux/uboot-trucrux.git;protocol=https"
SRCBRANCH = "v2020.04_5.4.24_2.1.0_dunfell"
SRCREV = "2121a608df34b75c53b134e8b5dfa45f61ac019e"


UBOOT_INITIAL_ENV_DEVICE = "sd"

SRC_URI = "${UBOOT_SRC};branch=${SRCBRANCH}"

S = "${WORKDIR}/git"
