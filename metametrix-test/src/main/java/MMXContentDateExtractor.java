import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ocpsoft.prettytime.nlp.PrettyTimeParser;

public class MMXContentDateExtractor {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
		
		PrintWriter out = new PrintWriter("C:\\Anu\\mmx\\result.csv");
		
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://10.20.30.206:5432/MMX-DB?schema=metametrix";
		String userName = "mmx-user";
		String password = "welcome01";
		/*String sql = "select sq.id as \"query_id\", sq.query_string,sr.id as \"search_result_id\",\r\n"
				+ "sr.document_id,SR.last_crawled_date,regexp_replace(sd.content, '[\\n\\r]+', ' ', 'g' ) as Content\r\n"
				+ "from metametrix.search_results sr\r\n"
				+ "inner join metametrix.search_query sq on sq.id=sr.query_id\r\n"
				+ "inner join metametrix.search_documents sd on sd.search_result_id=sr.id\r\n"
				+ "where sq.id in (15651,15652,15653,15654,15655,15656,15657,15658,15659,15660,15661,15662,15663,15664,15665,\r\n"
				+ "   15666,15667,15668,15669,15670,15671,15672,15673,15674,15675,15676,15677,15678,15679,15680,\r\n"
				+ "   15681,15682,15683,15684,15685,15686,15687,15688,15689,15690,15691,15692,15693,15694,15695)\r\n" ; */
		
		String sql = "select sq.id as \"query_id\", sq.query_string,sr.id as \"search_result_id\",\r\n"
				+ "sr.document_id,SR.last_crawled_date,regexp_replace(sd.content, '[\\n\\r]+', ' ', 'g' ) as Content\r\n"
				+ "from metametrix.search_results sr\r\n"
				+ "inner join metametrix.search_query sq on sq.id=sr.query_id\r\n"
				+ "inner join metametrix.search_documents sd on sd.search_result_id=sr.id\r\n"
				+ "where sq.id in (?)";
		Connection con = DriverManager.getConnection(url,userName,password);
		out.println("queryId\tqueryString\tsearchResultId\tdocumentId\tlastCrawlDate\tdate1\tdate2\tdate3\tdate4\tdate5\tcontent");
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1,15651);
		ResultSet resultSet = stmt.executeQuery();
		PrettyTimeParser p = new PrettyTimeParser();
		while(resultSet.next()) {
			int queryId = resultSet.getInt(1);
			String queryString = resultSet.getString(2);
			int searchResultId = resultSet.getInt(3);
			String documentId = resultSet.getString(4);
			Timestamp lastCrawlDate = resultSet.getTimestamp(5);
			String content = resultSet.getString(6);
			List<Date> dates = new ArrayList<Date>();
			try {
				 dates = p.parse(content);
			}catch(Exception e) {
				}
			int size = dates.size();
			if(size>5) {
				size =5;
				System.out.println("Size is more than 5:"+size +",for searchresultId"+searchResultId);
			}
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<size; i++) {
				sb.append(dates.get(i));
				sb.append("\t");
				
			}
			for(int i=size;i<5; i++) {
				sb.append(" ");
				sb.append("\t");
			}
			
			out.println(queryId+"\t"+queryString+
					"\t"+searchResultId+"\t"+documentId+"\t"+lastCrawlDate+"\t"+sb.toString()+ content);
			out.flush();
			System.out.println("SearchResultId :"+searchResultId );
		}
		resultSet.close();
		stmt.close();
		con.close();
		out.close();
		
	}

}
