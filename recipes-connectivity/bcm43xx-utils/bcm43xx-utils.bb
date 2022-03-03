DESCRIPTION = "Startup and config files for use with BCM43XX WIFI and Bluetooth"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES','systemd','','update-rc.d-native',d)}"

SRC_URI = " \
	file://trucrux-wifi \
	file://trucrux-wifi.service \
	file://trucrux-bt \
	file://trucrux-bt.service \
"

FILES_${PN} = " \ 
	${sysconfdir}/wifi/*  \
	${sysconfdir}/bluetooth/*  \
	${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${systemd_unitdir}/system/* ${sysconfdir}/systemd/system/multi-user.target.wants/*', \
													   '${sysconfdir}/init.d ${sysconfdir}/rcS.d', d)} \
"

RDEPENDS_${PN}_imx8mq-trux-q01 = "i2c-tools"
RDEPENDS_${PN}_imx8mm-trux-q01 = "i2c-tools"
RDEPENDS_${PN}_append = " bash base-files"

S = "${WORKDIR}"

do_install() {
	install -d ${D}${sysconfdir}/wifi
	install -m 0755 ${WORKDIR}/trucrux-wifi ${D}/${sysconfdir}/wifi

	install -d ${D}${sysconfdir}/bluetooth
	install -m 0755 ${WORKDIR}/trucrux-bt ${D}/${sysconfdir}/bluetooth

	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		install -d ${D}${systemd_unitdir}/system
		install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants
		install -m 0644 ${WORKDIR}/trucrux-wifi.service ${D}/${systemd_unitdir}/system
		install -m 0644 ${WORKDIR}/trucrux-bt.service ${D}/${systemd_unitdir}/system
		ln -sf ${systemd_unitdir}/system/trucrux-wifi.service \
			${D}${sysconfdir}/systemd/system/multi-user.target.wants/trucrux-wifi.service
		ln -sf ${systemd_unitdir}/system/trucrux-bt.service \
			${D}${sysconfdir}/systemd/system/multi-user.target.wants/trucrux-bt.service
	else
		install -d ${D}${sysconfdir}/init.d
		ln -s ${sysconfdir}/wifi/trucrux-wifi ${D}${sysconfdir}/init.d/trucrux-wifi
		update-rc.d -r ${D} trucrux-wifi start 5 S .
		ln -s ${sysconfdir}/bluetooth/trucrux-bt ${D}${sysconfdir}/init.d/trucrux-bt
		update-rc.d -r ${D} trucrux-bt start 99 2 3 4 5 .
	fi
}

COMPATIBLE_MACHINE = "(imx8mq-trux-q01|imx8mm-trux-q01)"
