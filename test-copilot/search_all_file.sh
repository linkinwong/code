#!/bin/bash

# 检查是否提供了至少两个参数
# 如果参数数量少于2，打印使用方法并退出脚本
if [ "$#" -lt 2 ]; then
    echo "使用方法: $0 <目录> <文本1> <文本2> ..."
    exit 1
fi

# 将目录赋值给变量
directory=$1

# 将所有的文本放在一个数组中
# shift命令用于移动位置参数，这里用于移除第一个参数（目录），剩下的参数（要搜索的文本）被赋值给texts数组
shift
texts=("$@")

# 在所有.pdf文档中搜索文本，不区分大小写
# find命令用于在指定目录及其子目录下查找所有.pdf文件
# -exec选项用于对每个找到的文件执行一个命令
# 这个命令是一个bash脚本，定义了一个函数search_texts，然后调用这个函数，并根据函数的返回值来决定是否打印文件名和匹配的行
find "$directory" -name '*.pdf' -exec bash -c '
    search_texts() {
        local text
        for text in "${@:2}"; do
            # 如果文本不在给定的文本中，返回1
            if ! grep -qi "$text"; then
                return 1
            fi
        done
        # 如果所有的文本都在给定的文本中，返回0
        return 0
    }
    pdftotext "$1" - | search_texts "${@:2}" && echo "$1" && 
    pdftotext "$1" - | grep -iHn --color "${@:2}"
' bash {} "${texts[@]}" \;


# 在所有.ppt、.pptx PowerPoint文档中搜索文本，不区分大小写
# 这部分的代码和上面的代码类似，只是使用了一个Python脚本（python_pptx_2_txt.py）来处理.ppt和.pptx文件
find "$directory" -name '*.ppt' -o -name '*.pptx' -exec bash -c '
    search_texts() {
        local text
        for text in "${@:2}"; do
            # 如果文本不在给定的文本中，返回1
            if ! grep -qi "$text"; then
                return 1
            fi
        done
        # 如果所有的文本都在给定的文本中，返回0
        return 0
    }
    python /Users/linlin/PycharmProjects/code/test-copilot/python_pptx_2_txt.py "$1" - | search_texts "${@:2}" && echo "$1" && 
    python /Users/linlin/PycharmProjects/code/test-copilot/python_pptx_2_txt.py "$1" - | grep -iHn "${@:2}"
' bash {} "${texts[@]}" \;


# # 在所有.doc、.docx 文档中搜索文本，不区分大小写
# # 这部分的代码和上面的代码类似，只是使用了一个Python脚本（python_pptx_2_txt.py）来处理.ppt和.pptx文件
# find "$directory" -name '*.doc' -o -name '*.docx' -exec bash -c '
#     search_texts() {
#         local text
#         for text in "${@:2}"; do
#             # 如果文本不在给定的文本中，返回1
#             if ! grep -qi "$text"; then
#                 return 1
#             fi
#         done
#         # 如果所有的文本都在给定的文本中，返回0
#         return 0
#     }
#     antiword "$1" - | search_texts "${@:2}" && echo "$1" && 
#     antiword "$1" - | grep -iHn "${@:2}"
# ' bash {} "${texts[@]}" \;