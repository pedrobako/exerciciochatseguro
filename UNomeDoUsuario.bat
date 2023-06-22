echo off
set str=%~nx0
set str=%str:.bat=%
prompt \
cls
cd exerciciochatseguro\target\classes
java aprendendo/exerciciochatseguro/Chat %str%
cd..
cd..
cd..