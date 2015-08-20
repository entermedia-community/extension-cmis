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

files.deleteMatch("activation*.jar");
files.deleteMatch("antlr*.jar");
files.deleteMatch("antlr*.jar");
files.deleteMatch("chemistry*.jar");
files.deleteMatch("commons-lang*.jar");
files.deleteMatch("jaxb-api*.jar");
files.deleteMatch("jaxb-impl*.jar");
files.deleteMatch("jaxws-api*.jar");
files.deleteMatch("jaxws-rt*.jar");
files.deleteMatch("mimepull*.jar");
files.deleteMatch("resolver*.jar");
files.deleteMatch("saaj-api*.jar");
files.deleteMatch("saaj-impl*.jar");
files.deleteMatch("slf4j-api*.jar");
files.deleteMatch("slf4j-log4j12*.jar");
files.deleteMatch("stax-ex*.jar");
files.deleteMatch("stax2-api*.jar");
files.deleteMatch("streambuffer*.jar");
files.deleteMatch("stringtemplate*.jar");
files.deleteMatch("woodstox-core-asl*.jar");




files.copyFileByMatch( tmp + "/WEB-INF/lib/*.jar", web + "/lib/");

