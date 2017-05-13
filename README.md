# HeaderCarousel
图片自动轮播器（多用于头部或底部展示）

#### ImageBanner继承自 ViewPager
- 默认滑动间隔为2秒，可通过xml 或 setScrollTime（int Millisecond）根据需要进行设置，单位毫秒
- start(ViewPager viewPager, ArrayList viewList)开启图片的轮播
  该方法中使用Timert开启定时器，适配视图并开始循环轮播
  
- 内部设置触摸监听，确保手动滑动图片时移除定时器，并在结束时开始新的定时器

- 可以给imageBanner设置OnItemClickListener设置点击事件的监听
  OnItemClickListener为自定义接口在com.example.bannerlibrary包中
  
```
imageBanner.setBannerItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
	    
            }
	    
        });
```

#### IndicatorLayout继承自LinearLayout

- 实现了OnPageChangeListener接口，用以监听ImageBanner的滑动事件，来实现指示器的联动

#### IndicatorImageBanner

- 带指示器的ImageBanner
- 继承自RelativeLayout，将ImageBanner和IndicatorLayout再次封装
- 在attrs中设置了：指示器颜色、位置、透明度等属性，具体可看代码，注释非常完整
- 同样实现了点击事件的监听，可参考ImageBanner的使用。
- 不需要指示器时，可直接使用ImageBanner;或者设置IndicatorImageBanner 透明度为0

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
	        compile 'com.github.BeijingSnail:ImageBanner:v1.1'
	}
```

![Image text](https://github.com/BeijingSnail/ImageBanner/blob/master/images/ImageBanner.png)


