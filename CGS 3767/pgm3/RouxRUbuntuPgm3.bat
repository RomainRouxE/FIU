::*********************************************************************
::       Author    : Romain ROUX 
::       Course    : CGS  3767
::       Professor : Michael Robinson 
::       Program :: : PGM 3 
::                   { Shell scirpt to excecute multiple action }
::                
::       Due Date  : 03/30/2022
::                 
::       Certification: 
::       I hereby certify that this work is my own and none of it is the work of any other person. 
::             
::*********************************************************************
@echo off

:: Get date and write in file
set date=%date%
set time=%time%

echo CURRENT running date %date% %time% > results.txt

:: Loop to divide number until user want to stop
:while
set /p firstNumber="Enter firstNumber value: "
echo %firstNumber%
if %firstNumber%==1 ( exit 0 )
    :: Loop to get second number
    :loop
    set /p secondNumber="Enter secondNumber value: "
    if %secondNumber%==2 ( exit 1 ) 
    if %secondNumber%==0 (
        echo You can t divide by 0
        goto loop
    ) 
       
    set /a divid= %firstNumber%/%secondNumber%
    echo %firstNumber% divided by %secondNumber% = %divid%
    echo %firstNumber% divided by %secondNumber% = %divid% >> results.txt
    
set /p choice="Do you want to do another division? "
if "%choice%"=="yes" ( goto while )
