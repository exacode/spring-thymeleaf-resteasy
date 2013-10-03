Example of Spring web application with rest api
===============================================
Simple web application that uses Thymeleaf as template engine, RestEasy as rest provider, and Spring, Hibernate validation framework.

Project uses RestEasy instead of native Spring rest mechanisms because RestEasy is a standard and is more powerful (Sharing interfaces between server and client was crucial). 

Project uses Thymeleaf instead of simple JSP, because JSP is almost always too simple;) 

Frameworks, libraries
---------------------
* Spring MVC - MVC, IoC framework
* Thymeleaf - template engine
* Twitter Bootstrap - HTML5 CSS3 great template

Features
--------
* Exception handling - according to best practices from: http://www.stormpath.com/blog/spring-mvc-rest-exception-handling-best-practices-part-1
* Hibernate validation - simple validation made by Hibernate Validation framework
* Spring profiles - project uses spring profiles to manage enviroments. You can change profile by maven property (just look into pom.xml). 
	* development - in this profile thymeleaf templates are not cached. This way it is easier and faster to develop web app. 
	* production - in this profile thymeleaf templates are cached.

Donation
--------

I hope you found here something useful and/or interesting.
Help keep this repository growing in more and better projects. 

<a href='http://www.pledgie.com/campaigns/22261'><img alt='Click here to lend your support to: mendlik-open-repository and make a donation at www.pledgie.com !' src='http://www.pledgie.com/campaigns/22261.png?skin_name=chrome' border='0' /></a>
