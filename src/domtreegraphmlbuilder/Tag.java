package domtreegraphmlbuilder;

public class Tag {

	
	public enum TagCommands {
		OPENING, CLOSING, SELFCLOSING;
	}
	TagCommands tagCommand = TagCommands.OPENING;
	
	String type = "";
	String id = "";
	String classes = "";
	String wholeTag = "";
	
	Node node = null;
	
	public Tag(String content){		
		content = content.substring(1, content.length() - 1); //cut off < and >
		wholeTag = content;
		
		if(content.charAt(0) == '/')
			tagCommand = TagCommands.CLOSING;
		else
			if(content.charAt(content.length() - 1) == '/')
				tagCommand = TagCommands.SELFCLOSING;
		
		this.type = content.split(" ")[0];
		
		String[] parts = content.split("\"");
					
		for(int i = 0; i < parts.length - 1; i++){
			String part = parts[i];
			if(part.length() > 3)
				if(part.substring(part.length() - 3, part.length()).equals("id="))
					id = "#" + parts[i + 1];			
			if(part.length() > 6)
				if(part.substring(part.length() - 6, part.length()).equals("class=")){
					String classesTmp = parts[i + 1];	
			    	if(classesTmp.length() > 0)
				    	for(String s : classesTmp.split(" "))
				    		if(s.length() > 0)
				    			classes += "." + s + " ";
				}
		}
		
		//System.out.println(type + "  " + id + "  " + classes);		
		
		if(tagCommand != TagCommands.CLOSING)
			node = new Node(type, id, classes, wholeTag);
	}
	
	public String show(){		
		return tagCommand + ": <" + type + (tagCommand == TagCommands.SELFCLOSING ? "/>" : ">") + (id.equals("") ? "" : ", id: " + id) + (classes.equals("") ? "" : ", classes: " + classes);
	}
	
	
}
