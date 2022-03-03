FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_imx8mq-trux-q01 = " file://imx-mkimage-imx8m-soc.mak-add-trux-mx8m-support.patch"
SRC_URI_append_imx8mm-trux-q01 = " file://imx-mkimage-imx8m-soc.mak-add-trucrux-imx8mm-suppo.patch"

