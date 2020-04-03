package mypackage;

public class StringMatching
{
	
	   void processArr(String pattern, int pattern_len, int arr[]) 
	    { 
	        int len = 0; 
	        arr[0] = 0; 
	        int i = 1; 
	        
	        while (i < pattern_len) 
	        { 
	            if (pattern.charAt(i) == pattern.charAt(len)) 
	            { 
	            	len++; 
	                arr[i++] = len; 
	                
	            } 
	            
	            else
	            { 
	                
	                if (len != 0) 
	                    len = arr[len - 1]; 

	                else 
	                    arr[i++] = len; 
	            } 
	        } 
	    } 
	   
	   
	void KMPSearch(String content, String pattern) 
    { 
        int pattern_len = pattern.length(); 
        int content_len = content.length(); 
        int arr[] = new int[pattern_len]; 
        
        
        processArr(pattern, pattern_len, arr); 
  
        int content_index = 0; // index for content[] 
        int pattern_index = 0; // index for pattern[] 
        for (;content_index < content_len;) 
        { 
            if (pattern.charAt(pattern_index) == content.charAt(content_index))
            { 
            	pattern_index++; 
                content_index++; 
            } 
            if (pattern_index == pattern_len)
            { 
                System.out.println("Found pattern at index " + (content_index - pattern_index)); 
                pattern_index = arr[pattern_index - 1]; 
            } 
  
            else if (content_index < content_len && pattern.charAt(pattern_index) != content.charAt(content_index)) 
            { 
                
                if (pattern_index != 0) 
                	pattern_index = arr[pattern_index - 1]; 
                else
                	content_index = content_index + 1; 
            } 
        } 
    } 
  
 
  
//    // Driver program to test above function 
//    public static void main(String args[]) 
//    { 
//        String content = "ABABDABACDABABCABAB"; 
//        String pattern = "ABABCABAB"; 
//        new StringMatching().KMPSearch(pattern, content); 
//    } 
} 

