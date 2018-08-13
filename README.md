# solr-test
这是个测试项目



配置Solr
在conf目录下，增加 Catalina\localhost\solr.xml 文件，如果conf文件夹下没有Catalina，新建它。
solr.xml内容：
<Context docBase="E:\apache-tomcat-7.0.59\webapps\solr.war" debug="0" crossContext="true" >
    <Environment name="solr/home" type="java.lang.String" value="E:\apache-tomcat-7.0.59\solr" override="true" />
</Context>


建设自己的solrHome文件夹E:\apache-tomcat-7.0.59\solr


将一些配置和index库文件放到解压好的solrHome下。

将apache-solr-3.4.0\example\solr目录下的conf#和data(可能没有)，solr.xml复制到刚才的solrhome目录下。

配置multicore
找到solr下载包中的example文件夹，在它的下面有个multicore文件夹，将这个文件夹下面的core0、core1和solr.xml(可替换或直接修改原来的solr.xml)拷贝到solrhome下面；

solrhome目录下的solr.xml文件进行配置
<cores adminPath="/admin/cores">  
   <core name="core0" instanceDir="core0" />  
   <core name="core1" instanceDir="core1" />  
</cores>
或
<cores adminPath="/admin/cores">
  <core name="core0" instanceDir="core0">
    <property name="dataDir" value="/data/core0" />
  </core>
  <core name="core1" instanceDir="core1" >
    <property name="dataDir" value="/data/core1" />
  </core>
</cores>

配置每个code的schema.xml，使每个实例都能支持更多Type和Field，
建设统一的公共的词库目录E:\apache-tomcat-7.0.59\solr\words
#
