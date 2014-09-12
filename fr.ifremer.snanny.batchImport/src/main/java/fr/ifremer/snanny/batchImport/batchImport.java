package fr.ifremer.snanny.batchImport;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.Arrays;
import java.util.List;

import com.couchbase.client.CouchbaseClient;


import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

//import net.spy.memcached.internal.OperationFuture;
//import net.spy.memcached.ops.OperationStatus;
//import net.spy.memcached.internal.GetFuture;

import fr.ifremer.snanny.batchImport.ObservationRecord;





public class batchImport {

	/**
	 * @param args
	 */
	public static void main(String[] args)  throws Exception {
		// TODO Auto-generated method stub
		  // (Subset) of nodes in the cluster to establish a connection
	    List<URI> hosts = Arrays.asList(
	      new URI("http://134.246.144.131:8091/pools")      //8091/pools      //visi-common-db
	    );
	    
	 
	    // Name of the Bucket to connect to is the first argument
	    String jsonInputFile = args[0];
	    String bucket = args[1];
	    String author = args[2];
	 
	    // Password of the bucket (empty) string if none
	    String password = "";
	 
	   
	   
	 
	// definition le contenu du fichier json
	String jsonString = readFile(jsonInputFile);
	
	  
	ObservationRecord observationRecord = new ObservationRecord(author, jsonString);
	
	
	// get UUID from O&M
	 JsonParser parser = new JsonParser();
	 JsonArray array = parser.parse(jsonString).getAsJsonArray();
	 Iterator<JsonElement> jsonRootLoop = array.iterator();
	 JsonArray smallArray = null;	
	 JsonElement currentJsonElement;
	 String IDENTIFIER_JSONNAME = new String("gml:identifier");
	 System.out.println("debut recherche identifier");
	 do {
		 
		 currentJsonElement = jsonRootLoop.next();
		 
		 if(currentJsonElement.isJsonArray()){			 
			 smallArray = currentJsonElement.getAsJsonArray();						 
		 }else{			 
			 smallArray = null;			 
		 }
			
	 }while ( jsonRootLoop.hasNext() 
			 && ((smallArray==null) 
					 || !(IDENTIFIER_JSONNAME.equals(smallArray.get(0).getAsString()))
				 )
			);
     String uuid = 	smallArray.get(2).getAsString();
	 System.out.println(uuid);
	 //System.out.print(IdentifierJsonElement.getAsString());
	 	
	 Gson gson = new Gson();
	 // Connect to the Cluster
	 CouchbaseClient client = new CouchbaseClient(hosts, bucket, password);
	 client.set(uuid, gson.toJson(observationRecord)).get();

	    client.shutdown();
	}
	



		 static String readFile(String fileName) throws IOException {
	    		BufferedReader br = new BufferedReader(new FileReader(fileName));
	    		try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null) {
				    sb.append(line);
				    sb.append("\n");
				    line = br.readLine();
				}
	        		return sb.toString();
	   		 } finally {
	        		br.close();
	   		}
		}


}
