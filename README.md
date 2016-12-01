#这是一个日常练习的程序

##TableLayout
Tablelayout类以行和列的形式对控件进行管理，每一行为一个TableRow对象，或一个View控件。

当为TableRow对象时，可在TableRow下添加子控件，默认情况下，每个子控件占据一列。

当为View时，该View将独占一行。


TableRow中的控件不能指定宽度，宽度永远是match_parent。

列的宽度由该列所有行的一个单元格决定。

shrinkColumns="0,1" 表示第0列和第1列可以收缩

stretchColumns="0,1" 表示第0列和第1列可以拉伸

collapseColumns="0,1" 表示隐藏第0列和第1列，相当于gone，不占空间

layout_span="2" 表示该控件占据两列的空间

words：

row 行

column 列

shrink 收缩

stretch 拉伸
