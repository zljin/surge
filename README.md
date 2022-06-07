# surge
功能介绍

1. com.example.service.ZhiHuSpiderService 知乎用户信息爬虫
    1. 线程池数量过多会出现Connection reset与Connection shutdown异常，爬虫设定核心池数为4
    2. 每次解析url地址时，自动设置动态IP代理，否则会报429连接数量过多异常
    3. 开了一个监听线程，当爬虫线程池中线程出现异常中断时，新开线程加入线程池中，保证线程池数量稳定在4个
    4. 去重策略运用Bloom Filter算法，提供稳定的去重服务，内存消耗较少
    5. 递归用户信息放入redis 队列
    6. 定时器异步消费队列信息
    6. Jsoup解析网页
