package NaverSearch;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NaverSearch {

	public static void main(String[] args) throws Exception {
		String keyword = URLEncoder.encode("겨울여행", "UTF-8");
		String apiurl = 
				"https://openapi.naver.com/v1/search/blog.xml?start=1&display=100&query=" + keyword;
		OutputStreamWriter out = 
				new OutputStreamWriter(new FileOutputStream("naver.txt"));

		String page=getSearchResult(apiurl);
		String text=getText(page);
		out.write(text);
		out.close();
	}
	
	public static String getSearchResult(String apiurl) throws Exception {
		String page="";
		String clientId = "";
		String clientSecret = "";
		URL url = new URL(apiurl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("X-Naver-Client-Id", clientId);
		con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		con.setRequestProperty("Content-type", "application/xml");
		BufferedReader br;
		if(con.getResponseCode() == 200)
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		else 
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		String line;
		while ((line = br.readLine()) != null) page += line;
		br.close();
		con.disconnect();
		return page;
	}

	public static String getText(String page) {
		String text="";
		Document doc=Jsoup.parse(page);
		Elements items=doc.select("description");
		for(Element i : items) text+=i.text()+"/r/n";
		return text;
	}
}
