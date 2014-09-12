package fr.ifremer.snanny.batchImport;


public class ObservationRecord {
		  private final String Authorname;
		  private final String Filejson;
		
		 
		  public ObservationRecord(String Authorname, String Filejson) {
		    this.Authorname = Authorname;
		    this.Filejson = Filejson;		
		  }
		 
		  public String getAuthorname() {
		    return Authorname;
		  }
		 
		  public String getFilejson() {
		    return Filejson;
		  }
		
		
		}
	