#!/bin/sh

. /etc/bluetooth/trucrux-bt.conf
. /etc/bluetooth/trucrux-bt-common.sh

case $1 in

"suspend")
        bt_stop
        ;;
"resume")
        bt_start
        ;;
esac

