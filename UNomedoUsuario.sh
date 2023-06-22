#!/bin/bash

PS1='$'
N=$(basename "$0" .sh)
cd exerciciochatseguro/target/classes
clear
java aprendendo/exerciciochatseguro/Chat "$N"
cd ..
cd ..
cd ..
clear