# 「读知乎」 Android 客户端

### 随时随地获得「知乎」最新精选条目！

欢迎访问「读知乎」官方站 http://z.ihu.im/ 。

「知乎」是一家创立于2011年1月26日的社会化问答网站，产品形态类似于Quora。「知乎」在古汉语里是「知道吗」的意思（取自 维基百科 上的解释）。我个人是「知乎」的重度用户，几乎每天都会访问「知乎」了解些小知识。

「知乎」官方已经有 Android 客户端推出。那么已经有了「知乎」 Android 客户端了，为什么还要开发这个应用呢？

「读知乎」的出发点和官方客户端是有所不同的，它重点在于让用户在于「读」的体验。「读知乎」没有其他繁杂的功能（甚至连登录都没有），就是为了让您专心阅读每篇精选的「知乎」条目。

使用「读知乎」 Android 客户端很简单，安装以后打开就可以阅读了。首次启动需要缓存内容条目和离线图片，所以比较缓慢请耐心等候。同时，推荐使用 Android 4.0 以上版本以及 3G 或者 Wi-fi 网络连接。

关键字： 知乎 读知乎 知识 问答


## 常见问题

### 为什么启动以后显示「Invalid Timestamp」然后就空白了？

出于时效性以及安全考虑，「读知乎」的网络接口需要和本地设备校验当前时间。

因此，如果您本地时间不正确的话，服务器会返回错误给您。如果碰到这种情况，请您先调整好您 Android 设备的时间和日期，然后重新运行「读知乎」即可。

### 为什么使用简繁体转换的时候「么」会替换成「幺」？

简繁体转换使用  jcc https://code.google.com/p/jcc/ 转换类库，可能是原作者出于转换效果的考虑。同时「么」也同「幺」，参见「说文解字」 http://chinese-character.info/content/58.html 。

下面转载原文的说明：

    《簡化字總表》第一表12項註釋：（么[麽]）读me轻声。读yāo(夭)的么应作幺(么本字)。麽读mó(摩)时不简化，如幺麽小丑。
    
    「幺」才是「么」的本字。例如見《說文解字》。《字鑑》及《字彙》等等辭書，也明言「么」是俗寫。類似地，「麼」（下方為幺）字古時也俗寫成「麽」（下方為么），大陸可能因此將「么」當作「麼」的簡化字。
    以上句段取自該網頁的評論中。
    
    附《說文解字》幺「小也。通俗文曰。不長曰幺。細小曰麼。許無麼字。象子初生之形。子初生、甚小也。俗謂一爲幺。亦謂晚生子爲幺。皆謂其小也。於堯切。二部。凡幺之屬皆从幺。」
    
    這兩組字區分很清晰，不要被簡化字的用法混淆。


### 如何使用自定义字体（2013年7月9日更新的功能）？

「读知乎」支持详情页面的自定义字体，您需要在您的手机闪存卡中放入您喜欢的字体，然后在「设置」中打开「使用自定义字体」的开关，然后分别选择对应的字体即可。目前根据 Android 系统的支持情况，字体文件支持 ttf、ttc、otf、fon 等格式。如果您选择的是非中文字体，那么英文则采用您指定的字体显示，而中文则使用系统默认的中文字体。

### 分享以后的条目缩略图保存在哪里？

通常我讨厌给用户的 SD 卡中写入很多无关的文件，但我认为如果您觉得这篇条目好用才会去分享，自然而言您也应该会想保存这篇条目。

条目的缩略图保存在您外部存储卡（通常称之为 SD 卡）的「图片」目录中，一般名称为每个系统不一样，通常可以认定为「Pictures」目录。当然，在选项中您可以关闭此保存功能，或者干脆使用纯文本的分享。

### 为什么目前还是没有分享功能？

<del>抱歉，目前(2013年5月11日)就只有我一个人开发「读知乎」的 Android 版本，而我想完成最主要的功能：阅读，然后才开发其他的附加功能。因此请您稍安勿躁，分享功能我会加紧在计划内完成的。</del>

2013年5月14日 完成了分享功能

### 为什么我的「分享」功能是灰色的

生成缩略图并分享的功能需要外置存储卡（例如 SD 卡）的支持，因此如果您的 Android 设备没有位置存储卡，那么就无法使用。

在这种情况下，您可以设置选项使用纯文本分享即可使用分享功能。

### 缓存文件保存在哪里？

很多 Android 程序总是会往用户的扩展卡（例如 SD 卡）中写入缓存等「垃圾文件」，这让我很反感。

「读知乎」的缓存文件放在 Android 官方指定的内置缓存目录（Cache Directory）中。我提供了个清除缓存功能选项，同时如果您卸载了「读知乎」那么缓存文件也会被系统一并清理。

### 什么是「真正的」中文引号？

我想这可能会引起争论，但我们的目标就是能或许更好的阅读和排版，而使用「」以及『』等这些引号将获得最大的识别度，同时这也是众多输入法中（例如百度输入法）默认的。

详细更多的信息您可以参考：http://apple4us.com/2011/06/chinese-quotation-mark-etc.html 。

### 为什么只支持 Android 4.0 以上的系统？

1. Android 4.0 全新的设计以及开发便捷，个人不想在这个应用上加入针对兼容老版本的大量「垃圾」代码（虽然这技术上不是问题）。
2. 相信使用「知乎」的用户对品质是有所追求的。

### 这款应用包含广告代码吗？

编写这个应用只是出于个人爱好，所以不包含任何隐形以及显性的盈利性质的第三方代码。

### 这款应用会读取通讯录等隐私信息吗？

我个人对于收集隐私讯息的事情非常反感，同时我个人的价值观底线告诉我绝对不会做这样的事情。不过为了改进应用，我是用了 友盟（umeng） 服务用于匿名的统计，如果您在意这点您可以在选项中关闭它。


## 更新记录

### 2013年8月1日

* 增加音量键翻页功能
* 增加自动恢复到上次读取的位置功能

### 2013年7月28日

* 修复中文标点替换的问题

### 2013年7月24日

* 增加搜索功能
* 修复分页功能丢失条目的问题

### 2013年7月21日

* 增加跳转客户端的功能
* 修复部分文案方面的错误

### 2013年7月11日

* 优化网络链接，加快请求速度
* 修复自定义字体部分路径无法应用的问题

### 2013年7月9日

* 支持自定义字体阅读
* 修改「捐赠」界面为支付宝的移动版
* 调整界面细节
* 修复部分问题

### 2013年6月28日

欢迎访问「读知乎」官方站 http://z.ihu.im/

* 修复部分界面方面的问题，增强稳定性
* 数据库操作优化

### 2013年6月9日

* 改进刷新界面，现在刷新可以不被打断了
* 修复分页逻辑错误的问题
* 重构代码，优化列表性能

### 2013年5月30日

* 优化列表，使用上拉载入更多

### 2013年5月25日

* 增加简繁体互换输出功能

### 2013年5月24日

* 优化界面，配色方面调整
* 修复友盟报告的一些错误

### 2013年5月16日

* 现在您可以再详情页中滑动翻阅上一篇、下一篇了！（可以在设置中关闭）
* 修复按下刷新按钮以后，条目列表不会滚动到最上端的问题

### 2013年5月14日

* 增加分享功能，支持系统级别的全局分享
* 增加「屏幕常亮」功能，避免阅读的时候被打断
* 优化代码，体验更流畅

### 2013年5月10日

* 增加离线图片下载功能
* 细调阅读界面
* 修复部分 Bug

### 2013年5月7日

* 增加列表页面手势滑动选项
* 增加详细阅读界面「上次恢复到」选项
* 修复部分 Bug 以及优化性能

### 2013年5月6日

* 更新列表的性能
* 更新阅读界面的样式
* 增加「使用中文引号」选项

### 2013年5月4日

* 完成基本功能

### 2013年4月28日

* 开始编写「读知乎」的代码


## 感谢

本应用是免费软件，无任何广告完全无偿使用。如果您能请我喝杯咖啡的话，我会更开心；同时您的名字会在下面的列表中。

* 感谢 小虎 同学提供的数据接口以及原始文档
* 感谢设计师 Fen 对于应用界面提出的些改造和建议
* 使用 JCC 正簡體字转换工具 https://code.google.com/p/jcc/
* 使用 https://github.com/chrisbanes/Android-PullToRefresh 下拉刷新控件
* 使用 友盟(umeng.com) 统计工具

感谢以下兄弟的捐助和支持（按照捐款时间排序，不定期更新）：

* 乌金一 (jin**@gmail.com)
* 杨志安 (yzh**@foxmail.com)
* 沈超 (she**@qq.com)
* 梁骏峰 (jus**@gmail.com)

截至目前（2013年7月11日）累计收到捐助 37.24RMB 整。


## 联系

* lucky@gracecode.com
* Twitter: @feelinglucky
* Weibo: @手气还不错
* //gracecode.com
* //zhihu.com/people/mingcheng


## 版权声明

「读知乎」程序本身作者拥有著作权，其中数据为「知乎」官方所有。享用任何其中的数据以及其他任何形式的载体，您可能需要阅读「知乎」相关的版权条例，在此不再重复声明。


## 免责声明

「读知乎」不反对、不鼓励、不赞成任何其中包含的内容以及任何引导，不对其中「知乎」提供的内容有效性以及准确性负责。不对任何因「读知乎」其中的内容（包括数据、程序本身）造成的任何后果承担任何责任，同时保留解释的权利。
