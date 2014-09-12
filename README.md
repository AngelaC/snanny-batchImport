snanny-batchImport
==================

batch import of json files in couchBase

Build
--------

mvn install
mvn assembly:single


Deploy
---------

cp target/fr.ifremer.snanny.batchImport-0.0.1-SNAPSHOT-bin.zip <install dir>
cd <install dir>
unzip fr.ifremer.snanny.batchImport-0.0.1-SNAPSHOT-bin.zip


Execute
---------
cd fr.ifremer.snanny.batchImport-0.0.1-SNAPSHOT-bin
./bin/importDirectoryContent.csh <directory where json files are> <authorname>


