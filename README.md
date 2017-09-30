# mybatis-generator
改造 mybatis-generator 
改动如下：
1、修改了生成的字段和表名没有注释问题
2、修改了mapper 的方法。目前保留方法 insert、getById、update、list、delById
3、修改DTD 简化了配置
4、修改了java -jar启动参数 目前支持-configfile(配置文件路径) -project（工程路径）

使用方法：
下载jar 或者下载工程代码打包
将jar,DTD文件，配置文件信息放到d盘 data目录下

gradle工程
//gradle 工程mybaits生成
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

执行mybatisGenerator

cmd 执行同上
