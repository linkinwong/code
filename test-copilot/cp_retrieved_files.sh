#!/bin/bash

# 指定的目标文件夹
dest_dir="$2"

# 源文件，就是导出文件的日志
input_file="$1"

prev_line=""
while IFS= read -r line
do
  if [[ $line == "(standard input):"* ]]; then
    if [[ -f "$prev_line" ]]; then

      # 获取文件的上级目录
      parent_dir=$(dirname "$prev_line")
      # 获取上级目录的名称
      parent_dir_name=$(basename "$parent_dir")
      # 创建目标路径中的上级目录
      mkdir -p "$dest_dir/$parent_dir_name"
      # 复制文件到新的上级目录中
      cp "$prev_line" "$dest_dir/$parent_dir_name"
    fi
  fi
  prev_line="$line"
done < "$input_file"