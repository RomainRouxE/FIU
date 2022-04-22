#*********************************************************************
#       Author    : Romain ROUX 
#       Course    : CGS  3767
#       Professor : Michael Robinson 
#       Program # : PGM 3 
#                   { Shell scirpt to excecute multiple action }
#                
#       Due Date  : 03/30/2022
#                 
#       Certification: 
#       I hereby certify that this work is my own and none of it is the work of any other person. 
#             
#*********************************************************************

# Get date and write in file
now="$(date)"
echo "CURRENT running date $now" > results.txt

# Loop to divide number
choice="yes"
while [ $choice == "yes" ]
do
    echo "Enter value for firstNumber"
    read firstNumber
    echo $firstNumber

    if [ $firstNumber == 23 ]
    then
        exit 1
    fi

    # Loop to get secondNumber, ask again if number entered is 0 else do the division
    secondNumber=0
    while [ $secondNumber == 0 ]
    do
        echo "Enter value for secondNumber"
        read secondNumber
        if [ $secondNumber == 44 ]
        then
            exit 1
        elif [ $secondNumber == 0 ]
        then
            echo "Can t divide by 0"
        else
            echo "$firstNumber divied by $secondNumber = $((firstNumber/secondNumber))"
            echo "$firstNumber divied by $secondNumber = $((firstNumber/secondNumber))" >> results.txt
        fi
    done
    
    echo "Do you want to do a new division ? Enter yes or no"
    read choice
done

# Command to get number of time ACGT is repeated
count=$(cat hs_alt_HuRef_chr10.fa | head -2000 | grep ACGT | wc -w)

#prit number of times ACGT was found in the 2000th first line
echo "The string ACGT was found $count times"