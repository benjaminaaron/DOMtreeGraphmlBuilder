package domtreegraphmlbuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HtmlImporter {

	ArrayList<String> content = new ArrayList<>();
	
	public HtmlImporter(String filename) {	
		FileReader fr = null;
		try {
			fr = new FileReader(filename);
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		BufferedReader br = new BufferedReader(fr);			
		String line;
		
		try {
			while((line = br.readLine()) != null)	
				content.add(line);
		} catch (IOException e) {e.printStackTrace();}		
	}
		
	public ArrayList<Tag> getTagStack(){		
		ArrayList<Tag> tagStack = new ArrayList<>();
    	
		boolean inTag = false;
		boolean inSingleQuotes = false;
		boolean inDoubleQuotes = false;

		String tagContent  = "";

		for(String line : content){
			//inTag = false;
			for (int i = 0; i < line.length(); i++){
			    char c = line.charAt(i);
		    
			    if(c == '\'')
			    	inSingleQuotes = !inSingleQuotes;
			    if(c == '\"')
			    	inDoubleQuotes = !inDoubleQuotes;   
			    
			    
			    if(c == '<' && !inSingleQuotes && !inDoubleQuotes)
			    	inTag = true; 	
			    
			    if(inTag)
			    	tagContent += c;
			    
			    if(c == '>' && inTag && !inSingleQuotes && !inDoubleQuotes){
			    	inTag = false;
			    	Tag tag = new Tag(tagContent);
			    	tagStack.add(tag);
			    	tagContent = "";
			    } 
			}	
		}
		return tagStack;	
	}
}
