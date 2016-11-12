ejb3-maven
==========

EJB3-Maven is a quick start J2EE project helping with initial EJB3 scaffolding, out-of-container Unit testing and smartly building all artifacts using Maven

The project generates all the artifacts helping to get started quickly with the initial J2EE scaffolding, hiding all J2EE JAR dependencies

It consist of three sub-projects:

 # *roussev-war* - This is web archive project featuring a Servlet accessing ejb stateless DAO bean. 
 # *roussev-ejb* - contains all the business domain EJB3 classes and tests. It features a sample JPA entity, a stateless DAO bean and JUnit4 tests running in a out-of-container environment.
 # *roussev-ear* - This is the master EAR project describing and binding the WAR and EJB-JAR archives altogether. 

===Requirements:===
 * Java 5+
 * Maven 2
 * MySQL
 * (_Optional_) JBoss 5+ (It may work with JBoss 4x, yet this has not been tested)

===Steps to build:===
 * Start MySQL
{{{
net start MySQL
}}}

 * Create the project database
{{{
CREATE DATABASE `ejb3-maven`
}}}

 * Create the sample table
{{{
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL auto_increment, 
  `first_name` varchar(50) default NULL, 
  `last_name` varchar(50) default NULL, 
  `birth_day` timestamp NULL default NULL, 
  PRIMARY KEY  (`id`)
)
}}}

 * Update user database passwords at:
{{{
./ejb3-maven/misc/roussev-ds.xml
./ejb3-maven/roussev-ejb/src/test/resources/META-INF/persistence.xml
}}}

 * Build and test project using Maven
{{{
mvn clean install
}}}

 * After successful build, you will notice the master EAR archive being created:
{{{
./ejb3-maven/roussev-ear/target:
     ejb3-maven-1.0.ear
         # containing:
            roussev-1.0.war # the web archive
            roussev-ejb-1.0.jar # the ejb archive
            # and all dependencies such as jdbc driver..
}}}
 * Unit test results can be found at:
{{{
./ejb3-maven/roussev-ejb/target/surefire-reports
}}}

 * Copy the EAR artifact to JBoss deploy
{{{
cp ./ejb3-maven/roussev-ear/target/ejb3-maven-1.0.ear $JBOSS_HOME/server/default/deploy/ejb3-maven-1.0.ear
}}}

 * Copy the datasource configuration to JBoss deploy (_You need to do this only once_)
{{{
cp ./ejb3-maven/misc/roussev-ds.xml  $JBOSS_HOME/server/default/deploy/roussev-ds.xml
}}}

 * (_Optional_) Copy log4j configuration (_You need to do this only once_)
{{{
cp ./ejb3-maven/misc/jboss-log4j.xml  $JBOSS_HOME/server/default/conf/jboss-log4j.xml
}}}

 * Access the web application at [http://localhost:8080/ejb3-maven/]


===Using a non MySQL database===
Be aware this project can run on any database. To port it to a different database configure:
{{{
#JBoss JPA datasource configuration
./ejb3-maven/misc/roussev-ds.xml

# Unit test local JPA configuration
./ejb3-maven/roussev-ejb/src/test/resources/META-INF/persistence.xml

# Describe the jdbc driver at
./ejb3-maven/pom.xml
                # E.g.  
		# <dependency>
		# 	<groupId>mysql</groupId>
		# 	<artifactId>mysql-connector-java</artifactId>
		# 	<version>5.0.2</version>
		# </dependency>

# And repeat the build steps
}}}






###########################################################
##                                                       ##
## Assuming you have Java 5+, MySQL and Maven2 installed ##
##                                                       ##
###########################################################

#__1__ start MySQL
net start MySQL

#__2__ create the db
CREATE DATABASE `ejb3-maven`

#__3__ Create the sample table
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `first_name` varchar(50) default NULL,
  `last_name` varchar(50) default NULL,
  `birth_day` timestamp NULL default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1

#__4__ update user DB passwords at:
./ejb3-maven/misc/roussev-ds.xml
./ejb3-maven/roussev-ejb/src/test/resources/META-INF/persistence.xml

#__5__ Run Maven2
mvn clean install

#__6__ Copy the EAR artifact to JBoss deploy
cp ./ejb3-maven/roussev-ear/target/ejb3-maven-1.0.ear $JBOSS_HOME/server/default/deploy/ejb3-maven-1.0.ear

#__7__ Copy the datasource configuration to JBoss deploy (You need to do this only once)
cp ./ejb3-maven/misc/roussev-ds.xml  $JBOSS_HOME/server/default/deploy/roussev-ds.xml

#__8__ Copy the datasource configuration to JBoss deploy (You need to do this only once)
cp ./ejb3-maven/misc/jboss-log4j.xml  $JBOSS_HOME/server/default/conf/jboss-log4j.xml

#__9__ Access the web applciation at http://localhost:8080/ejb3-maven/user
cp ./ejb3-maven/misc/jboss-log4j.xml  $JBOSS_HOME/server/default/conf/jboss-log4j.xml

