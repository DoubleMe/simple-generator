# simple-generator
#### 改造自 mybatis-generator(https://github.com/mybatis/generator) 
#### 改动如下：<br> 
* 修改了生成的字段和表名没有注释问题<br> 
* 修改了mapper 的方法。目前保留方法 insert、getById、update、list、delById<br> 
* 修改DTD 简化了配置<br> 
* 修改了java -jar启动参数 目前支持-configfile(配置文件路径) -project（工程路径）<br> 

#### 使用方法：
下载jar 或者下载工程代码打包<br> 
将jar,DTD文件，配置文件信息放到d盘 data目录下<br> 

//gradle 工程mybaits生成，在build.gradle 下添加task mybatisGenerator 参数替换成自己实际参数<br> 
```java
task mybatisGenerator(){
    //jar 路径 
    def jarFile = "D:\\data\\mybatis-generator-1.0.0.jar"
    //配置文件路径
    def configFile = "D:\\data\\config.xml" 
    //工程路径 
    def projectPath = rootDir.getAbsolutePath()
    def cmd = "java -jar " + jarFile + " -configfile " + configFile + " -project " + projectPath 
    println cmd
    Process p = cmd.execute()
    println "${p.text}"
} 
```

执行mybatisGenerator<br> 

cmd 执行<br> 
打开cmd 执行 java -jar D:\\data\\mybatis-generator-1.0.0.jar -config D:\\data\\config.xml<br>

jar 和 configfile 地址替换成实际地址<br>

如有建议联系QQ 382391977<br>
