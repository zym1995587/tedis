# Tedis

Tedis����һ��[redis](http://redis.io "redis")��java�ͻ���

Tedis��Ŀ���Ǵ���һ��������������ֱ��ʹ�õĸ߿���Redis����������μ�[��������](https://github.com/taobao/tedis/wiki "more info")

## Feature

- �߿��ã�Tedisʹ�ö�д�������HAȷ��redis�ĸ߿���
- �����ܣ�ʹ��������߳�ģ�ͣ�ʹredis�����ܲ������ڿͻ���
- ����ʹ�÷�ʽ�������ֻ��һ��redisʵ����������Ҫtedis��HA���ܣ�����ֱ��ʹ��tedis-atomic��ʹ��tedis�ĸ߿��ù�����Ҫ������redisʵ��ʹ��tedis-group
- ����API���������byte�ĵײ�api������object�ĸ߲�api
- ���ַ���ʹ��redis�Ĺ��߼��ϣ�����mysql����ͬ����redis���ߣ�����redis���������ߵ�

## QuickStart

����Maven������
```xml
<dependency>
  <groupId>com.taobao.common</groupId>
  <artifactId>tedis-group</artifactId>
  <version>1.1.0</version>
</dependency>
```

```java
Group tedisGroup = new TedisGroup(appName, version);
tedisGroup.init();
ValueCommands valueCommands = new DefaultValueCommands(tedisGroup.getTedis());
// д��һ������
valueCommands.set(1, "test", "test value object");
// ��ȡһ������
valueCommands.get(1, "test");
```

Tedis��GPL version 2Э���¿�Դ��Ŀǰ���в����ƣ��������ʲô�õ��뷨���߷������κ�bug���벻Ҫ����������:)

