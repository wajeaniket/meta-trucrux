FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://trucrux-blacklist.conf \
"

do_install_append() {
	install -m 0755 -d ${D}${sysconfdir}/modprobe.d
	install -m 0644 ${WORKDIR}/trucrux-blacklist.conf ${D}${sysconfdir}/modprobe.d
}
