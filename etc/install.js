importPackage( Packages.com.openedit.util );
importPackage( Packages.java.util );
importPackage( Packages.java.lang );
importPackage( Packages.com.openedit.modules.update );


var war = "http://dev.entermediasoftware.com/jenkins/job/extension-cmis/lastSuccessfulBuild/artifact/deploy/extension-cmis.zip";

var root = moduleManager.getBean("root").getAbsolutePath();
var web = root + "/WEB-INF";
var tmp = web + "/tmp";

log.add("1. GET THE LATEST WAR FILE");
var downloader = new Downloader();
downloader.download( war, tmp + "/extension-cmis.zip");

log.add("2. UNZIP WAR FILE");
var unziper = new ZipUtil();
unziper.unzip(  tmp + "/extension-cmis.zip",  tmp );

log.add("3. REPLACE LIBS");
var files = new FileUtils();
files.deleteMatch( web + "/lib/extension-cmis*.jar");

filles.deleteMatch("activation*.jar");
filles.deleteMatch("antlr*.jar");
filles.deleteMatch("antlr*.jar");
filles.deleteMatch("chemistry*.jar");
filles.deleteMatch("commons-lang*.jar");
filles.deleteMatch("jaxb-api*.jar");
filles.deleteMatch("jaxb-impl*.jar");
filles.deleteMatch("jaxws-api*.jar");
filles.deleteMatch("jaxws-rt*.jar");
filles.deleteMatch("mimepull*.jar");
filles.deleteMatch("resolver*.jar");
filles.deleteMatch("saaj-api*.jar");
filles.deleteMatch("saaj-impl*.jar");
filles.deleteMatch("slf4j-api*.jar");
filles.deleteMatch("slf4j-log4j12*.jar");
filles.deleteMatch("stax-ex*.jar");
filles.deleteMatch("stax2-api*.jar");
filles.deleteMatch("streambuffer*.jar");
filles.deleteMatch("stringtemplate*.jar");
filles.deleteMatch("woodstox-core-asl*.jar");




files.copyFileByMatch( tmp + "/WEB-INF/lib/*.jar", web + "/lib/");

