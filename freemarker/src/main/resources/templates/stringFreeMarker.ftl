字符串输出:
${"Hello ${name} !"} / ${"Hello " + name + " !"}
<#assign cname=r"特殊字符完成输出(http:\www.baidu.com)">
${cname}

字符串截取 ：
通过下标直接获取下标对应的字母： ${name[2]}
起点下标..结尾下标截取字符串：${name[0..5]}

算数运算：
<#-- 支持"+"、"－"、"*"、"/"、"%"运算符 -->
<#assign number1 = 10>
<#assign number2 = 5>
"+" : ${number1 + number2}
"－" : ${number1 - number2}
"*" : ${number1 * number2}
"/" : ${number1 / number2}
"%" : ${number1 % number2}

比较运算符：
<#if number1 + number2 gte 12 || number1 - number2 lt 6>
"*" : ${number1 * number2}
<#else>
"/" : ${number1 / number2}
</#if>

内建函数：
<#assign data = "abcd1234">
第一个字母大写：${data?cap_first}
所有字母小写：${data?lower_case}
所有字母大写：${data?upper_case}
<#assign floatData = 12.34>
数值取整数：${floatData?int}
获取集合的长度：${users?size}
时间格式化：${dateTime?string("yyyy-MM-dd")}

