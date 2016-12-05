import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphDecorator {
	private static ParagraphDecorator paragraphDecorator = null;
	private Pattern domElementPattern;
	private Pattern hashTagPattern;
	
	// 작명이유: w3schools에서 말하는 용어(http://www.w3schools.com/html/html_intro.asp)
	// Tip: The start tag is also called the opening tag, and the end tag the closing tag.
	private final String HASH_OPENING_TAG = "<span class=\"__se3tag\">";
	private final String HASH_CLOSING_TAG = "</span>";
	
	private ParagraphDecorator() {
		// html의 각종 document의 tag 패턴 
		domElementPattern = Pattern.compile("<\\/?\\w*(\\s\\w*=[^>]*)?\\s*\\/?>");
		
		// "#을 1개를 가지고 이후 문자들은 #과 공백문자를 포함 하지 않는 길이가 1이상인 문자열(해시태그)"
		hashTagPattern = Pattern.compile("(#[^#\\s]*)");
	}
	
	public static ParagraphDecorator builder() {
		if(paragraphDecorator == null) {
			paragraphDecorator = new ParagraphDecorator();
		}
		return paragraphDecorator;
	}
	
	private String[] splitElement(String input, Pattern pattern) {
		if (input == null) {
			return null;
		}

		int startIndex = 0;
		LinkedList<String> splittedElements = new LinkedList<String>();
		
		Matcher matcher = pattern.matcher(input);
		
		while (matcher.find()) {
			String line = input.substring(startIndex, matcher.start());
			if (!line.equals("")) {
				splittedElements.add(line);
			}
			splittedElements.add(matcher.group());
			startIndex = matcher.end();
		}

		String line = input.substring(startIndex);
		if (!line.equals("")) {
			splittedElements.add(line);
		}

		return splittedElements.toArray(new String[splittedElements.size()]);
	}
	
	public String process(String input) {
		String output = "";
		String[] splitedByDomTagElements = splitElement(input, domElementPattern);
		
		for(String element : splitedByDomTagElements) {
			if(element.startsWith("<")) {
				output += element;
			} else {
				String[] splitedByHashTagElements = splitElement(element, hashTagPattern);
				for(String tag : splitedByHashTagElements) {
					if(tag.startsWith("#") && !tag.equals("#")) {
						output += HASH_OPENING_TAG + tag + HASH_CLOSING_TAG;
					} else {
						output += tag;
					}
				}
			}
		}
		return output;
	}
}