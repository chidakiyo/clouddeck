#!/bin/sh

CURRENT=`dirname $0`
CMD="${CURRENT}/sbt.sh run"
sh -c "nohup $CMD >/dev/null 2>&1 &"