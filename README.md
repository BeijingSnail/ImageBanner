# HeaderCarousel
图片自动轮播器（多用于头部或底部展示）
#### ImageBanner继承自ViewPager
- 默认滑动间隔为2秒，可通过xml 或 setScrollTime（int Millisecond）根据需要进行设置，单位毫秒
- start(ViewPager vp, ArrayList viewList)方法中使用Timert开启定时器，适配视图并开始循环轮播
内部设置触摸监听，确保手动滑动图片时移除定时器，并在结束时开始新的定时器
- ImageBanner实现了点击事件，并给子每张图片设置了id(从0开始)
可通过v.getId()获取
```
imageBanner.setBannerClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
             Toast.makeText(MainActivity.this, "点击第" + v.getId() + "张", Toast.LENGTH_SHORT).show();
        }
    });
```


#### IndicatorLayout继承自LinearLayout
- 可以根据需要选择是否添加该布局
- 内部实现了OnPageChangeListener接口，用以监听ImageBanner的滑动事件，来实现指示器的联动
- 在xml中可以更改defaultColor和currentColor的颜色（默认颜色为白、红）

#### 可在项目中添加依赖
1 Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2 Add the dependency
```
dependencies {
	        compile 'com.github.BeijingSnail:HeaderCarousel:1.0'
	}
```

![Image text](https://github.com/BeijingSnail/ImageBanner/blob/master/images/ImageBanner.png)


