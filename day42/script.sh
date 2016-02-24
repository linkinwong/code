row=0
column=5
while read line; do
    let 'row = row +1'
    column=`echo $line | wc -w`
done < file.txt
#echo "$row$column"
declare -A matrix

for ((i=1;i<=row;i++))  do
    line=`sed -n "${i}p" < file.txt`
    for ((j=1;j<=column;j++)) do
        word=`echo $line | cut -d' ' -f $j`
   #     echo $word
        matrix[$i,$j]=$word
    done
done

for ((i=1;i<=column;i++))  do
    for ((j=1;j<=row;j++)) do
        printf "%s " "${matrix[$j,$i]}"
    done
    printf "\n"
done
