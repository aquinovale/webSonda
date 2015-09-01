# webSonda 0.1

I am using Maven2. 

You must have done the download wildfly 9.1
Initialize the wildfly with profile full, because jax-rs use this profile

'''$JBOSS-HOME/bin/standalone.sh --server-config=standalone-full.xml -b 0.0.0.0'''

Execute in Linux with root
apt-get install maven2 to install maven2.

'''mvn clean compile package wildfly:deploy-only'''

Can you to use a 'curl -H "Content-Type:application/json" -i -X POST -d '{"commands": "5 5 \n 1 2 N \n LMLMLMLMM \n 2 2 S \n MMMLMRMM"}' http://localhost:8080/Sonda/api/rest' to visualize result.
Use \n to do a breakline.


