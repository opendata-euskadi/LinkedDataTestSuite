# LinkedDataTestSuite (war version)(14/07/2017)
Tests and reporting for Linked Data systems, from version 0.0.5.

## Requirements and installation:
- Spring Framework
- Tomcat 7 or higher with next server.xml configuration included:
	- Context docBase="contentpath/LinkedDataTestSuite/src/test/resources" path="/static"
	where contentpath must be supplied by user's path.
- lod.properties file located in src/main/resources must be modified with the user's path.
- Project contains the file test_template.xml (src/main/resources) for configurating test cases which can be modified
with the application deployed. Test cases declared in the template also have to be declared in LodTest.java.

Report: https://opendata-euskadi.github.io/LinkedDataTestSuite/src/test/resources/report.html
