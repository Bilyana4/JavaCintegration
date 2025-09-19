@echo off
rem setting of PATH 
set PATH=C:\Program Files\Microsoft Visual Studio\2022\Community\VC\Tools\MSVC\14.29.30133\bin\HostX86\x86; C:\Users\ASUS\Desktop\PP_proj\libraries\gauss.dll%PATH%

rem setting of INCLUDE
set INCLUDE=C:\Program Files\Microsoft Visual Studio\2022\Community\VC\Tools\MSVC\14.29.30133\include;C:\Program Files (x86)\Windows Kits\10\Include\10.0.22621.0\ucrt;%INCLUDE%

rem setting of LIB
set LIB=C:\Program Files\Microsoft Visual Studio\2022\Community\VC\Tools\MSVC\14.29.30133\lib\x86;C:\Program Files (x86)\Windows Kits\10\Lib\10.0.22621.0\ucrt\x86;C:\Program Files (x86)\Windows Kits\10\Lib\10.0.22621.0\um\x86;%LIB%

echo The environment is set successfullly
