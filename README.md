I am using Maven2. 

Execute in Linux with root
apt-get install maven2 to install maven2.

Make download wildfly
```curl -O http://download.jboss.org/wildfly/9.0.1.Final/wildfly-9.0.1.Final.tar.gz```

Extract the file inside /opt, use sudo to this.
```sudo tar -zxvf wildfly-9.0.1.Final.tar.gz -C /opt```

Create a symbolic link with name /opt/wildfly
```sudo ln -s /opt/wildfly-9.0.1.Final /opt/wildfly```

Export the variables
```export JBOSS_HOME=/opt/wildfly```
```export PATH=$JBOSS_HOME/bin:$PATH```

Initialize the wildfly with profile full, because jax-rs use this profile
```$JBOSS-HOME/bin/standalone.sh --server-config=standalone-full.xml -b 0.0.0.0```

After wildfly was initialized, execute the maven. 
```mvn clean compile package wildfly:deploy-only -Dmaven.test.skip=true```

Use the -Dmaven.test.skip=true, when a test is done by first time, a error can happen, because the endpoint it is not running.

Execute the tests after do deployment the application
Can you to use mvn test, but the wildfly must be started.
```mvn test```

or you can to use a curl to visualize result.
```curl -H "Content-Type:application/json" -i -X POST -d '{"commands": "5 5 \n 1 2 N \n LMLMLMLMM \n 3 3 E \n MMRMMRMRRM"}' http://localhost:8080/Sonda/api/rest```' 

Use \n to do a breakline.


