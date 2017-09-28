# RotateDialogDemo
Only rotate Dialog without change app's orientation.

在不修改app的Activity朝向的情况下,只改变Dialog的方向的方法.

### 效果图
![AlertDialog的旋转图](https://raw.githubusercontent.com/ZhengShang/RotateDialogDemo/master/app/release/2017-09-27-05mzalertdialog.gif)

![Custom的旋转图](https://raw.githubusercontent.com/ZhengShang/RotateDialogDemo/master/app/release/2017-09-27-06mzcustomdialog.gif)

Demo[下载](https://raw.githubusercontent.com/ZhengShang/RotateDialogDemo/master/app/release/demo.apk)

### 思路

1. 设置不同方向上Window的window.setLayout(width,height)大小
2. 设置Dialog的根布局View旋转后的大小
3. 旋转(可能伴随x轴和y轴的平移)根布局View

更多详情可以查看简书[文章](http://www.jianshu.com/p/b986df7951a4)