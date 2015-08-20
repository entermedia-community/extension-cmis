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
files.deleteMatch( web +"/lib/activation*.jar");
files.deleteMatch( web +"/lib/antlr*.jar");
files.deleteMatch( web +"/lib/antlr*.jar");
files.deleteMatch( web +"/lib/chemistry*.jar");
files.deleteMatch( web +"/lib/commons-lang*.jar");
files.deleteMatch( web +"/lib/jaxb-api*.jar");
files.deleteMatch( web +"/lib/jaxb-impl*.jar");
files.deleteMatch( web +"/lib/jaxws-api*.jar");
files.deleteMatch( web +"/lib/jaxws-rt*.jar");
files.deleteMatch( web +"/lib/mimepull*.jar");
files.deleteMatch( web +"/lib/resolver*.jar");
files.deleteMatch( web +"/lib/saaj-api*.jar");
files.deleteMatch( web +"/lib/saaj-impl*.jar");
files.deleteMatch( web +"/lib/slf4j-api*.jar");
files.deleteMatch( web +"/lib/slf4j-log4j12*.jar");
files.deleteMatch( web +"/lib/stax-ex*.jar");
files.deleteMatch( web +"/lib/stax2-api*.jar");
files.deleteMatch( web +"/lib/streambuffer*.jar");
files.deleteMatch( web +"/lib/stringtemplate*.jar");
files.deleteMatch( web +"/lib/woodstox-core-asl*.jar");


files.deleteMatch( web +"/web.xml");

files.copyFileByMatch( tmp + "/web.xml", web + "/web.xml");
files.copyFileByMatch( tmp + "/lib/*.jar", web + "/lib/");

