#!/usr/bin/expect -f

set timeout -1
spawn ks pip run --project test -p simple
expect "Please select workspace name:"
send -- "simple\r"
expect eof
