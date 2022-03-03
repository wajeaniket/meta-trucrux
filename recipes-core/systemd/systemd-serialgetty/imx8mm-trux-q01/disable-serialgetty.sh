#!/bin/sh

UART0_SERVICE=serial-getty@ttymxc0.service
UART3_SERVICE=serial-getty@ttymxc3.service

tty_service_enabled()
{
	test -f /etc/systemd/system/getty.target.wants/$1
}

som_is_trux_mx8mm()
{
        grep -q TRUX-MX8M-MINI /sys/devices/soc0/machine
}
elif som_is_trux_mx8mm && tty_service_enabled ${UART3_SERVICE}; then
	systemctl stop ${UART3_SERVICE}
	systemctl disable ${UART3_SERVICE}
	systemctl enable ${UART0_SERVICE}
fi



