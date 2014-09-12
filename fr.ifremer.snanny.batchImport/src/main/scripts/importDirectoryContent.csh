#!/bin/bash

source ~/tools/env/java_1.7.sh
for file in /home/isi-projets/sensorNanny/data/vaimos/jsonOM/*.json
do
 # do something on $file
 echo $file
 java -cp ./lib/httpcore-4.3.jar:./lib/httpcore-nio-4.3.jar:./lib/commons-codec-1.5.jar:./lib/couchbase-client-1.4.4.jar:.lib/netty-3.5.5.Final.jar:./lib/jettison-1.1.jar:./lib/stax-api-1.0.1.jar:./lib/spymemcached-2.11.4.jar:./lib/gson-2.3.jar:./fr.ifremer.snanny.batchImport-0.0.1-SNAPSHOT.jar fr.ifremer.snanny.batchImport.batchImport $file snanny_observations kai 
done
