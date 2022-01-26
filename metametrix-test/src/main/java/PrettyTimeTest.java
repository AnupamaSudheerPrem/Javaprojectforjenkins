import org.ocpsoft.prettytime.nlp.PrettyTimeParser;

public class PrettyTimeTest {

	public static void main(String[] args) {
		PrettyTimeParser p = new PrettyTimeParser();
		String content= "the article published on: 2021-01-22 11:00:00.000 ";
		System.out.println("content:"+content+", Dates:"+p.parse(content));
		content=  "The article will be published 1 day from today";
		System.out.println("content:"+content+", Dates:"+p.parse(content));
		content=  "The incident happened on january 1st 2018";
		System.out.println("content:"+content+", Dates:"+p.parse(content));
		content= "I'll be there at two";
		System.out.println("content:"+content+", Dates:"+p.parse(content));
		content= "Let's get lunch at two pm";
		System.out.println("content:"+content+", Dates:"+p.parse(content));
		content= "I did it 3 days ago";
		System.out.println("content:"+content+", Dates:"+p.parse(content));
		content= "I has been snowing for the last three days";
		System.out.println("content:"+content+", Dates:"+p.parse(content));
		content= "I visited my freind's house a couple of days ago";
		System.out.println("content:"+content+", Dates:"+p.parse(content));
		content= "I visited my parent's house last week";
		System.out.println("content:"+content+", Dates:"+p.parse(content));
		
	}
		
	

}
