#!/usr/bin/env bash

filenames=('ionosphere' 'isolet' 'liver' 'mnist' 'mushroom')
gamma=(0.25 0.01 13 0.00000001 1)       ## best gamma found by built-in LOOCV method
declare -a err_linear
declare -a err_kernel

i=0
for file in ${filenames[*]}
do
    ./svm_learn -v 0 ${file}_train.dat ${file}.model
    ./svm_classify ${file}_test.dat ${file}.model ${file}.output > ${file}.text
    accuracy=`sed -n  '4 s/.*set: \(.*\)%.*/\1/p' ${file}.text`
    err_linear[$i]=`echo "scale=4;1-$accuracy/100" | bc`
    let "i=$i+1"
done



j=0
for file in ${filenames[*]}
do
    ./svm_learn -v 0 -t 2 -g ${gamma[$j]}  ${file}_train.dat ${file}.model
    ./svm_classify ${file}_test.dat ${file}.model ${file}.output > ${file}.text
    accuracy=`sed -n  '4 s/.*set: \(.*\)%.*/\1/p' ${file}.text`
    err_kernel[$j]=`echo "scale=4;1-$accuracy/100" | bc`
    let "j=$j+1"
done

echo "The error rates of linear svm are:"
for err in ${err_linear[*]}
do
    echo $err
done

echo "The error rates of svm with kernel are:"
for err in ${err_kernel[*]}
do
    echo $err
done
