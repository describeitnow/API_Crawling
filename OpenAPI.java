package OpenAPI;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.lang.Exception;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class OpenAPI {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String xmlPage=getXMLPage();
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("data.txt"));
		
		String[] fieldname = new String[] {"transaction_amount", "year_of_construction", "year", "address", "apartment", "month", "exclusive_area", "lot_number", "area_code", "floor"};
		String[] Prefieldname_1 = new String[] {"<transaction_amount>", "<year_of_construction>", "<year>", "<address>", "<apartment>", "<month>", "<exclusive_area>", "<lot_number>", "<area_code>", "<floor>"};
		String[] fieldname_1 = new String[] {"< ŷ  ݾ >", "<    ⵵>", "<  >", "<      >", "<    Ʈ>", "<  >", "<       >", "<    >", "<     ڵ >", "<  >"};
		String[] Prefieldname_2 = new String[] {"</transaction_amount>", "</year_of_construction>", "</year>", "</address>", "</apartment>", "</month>", "</exclusive_area>", "</lot_number>", "</area_code>", "</floor>"};
		String[] fieldname_2 = new String[] {"</ ŷ  ݾ >", "</    ⵵>", "</  >", "</      >", "</    Ʈ>", "</  >", "</       >", "</    >", "</     ڵ >", "</  >"};
		
		String colName="";
		for(int i=0;i<10;i++) colName+=fieldname[i]+" t";
		colName+=" r n";
		out.write(colName);
		
		for(int i=0;i<10;i++) {
			xmlPage = xmlPage.replace(fieldname_1[i], Prefieldname_1[i]);
			xmlPage = xmlPage.replace(fieldname_2[i], Prefieldname_2[i]);
		}

		Document doc = Jsoup.parse(xmlPage);
		Element data = doc.select("items").get(0);
		Elements itemList = data.select("item");
		
		for(Element item:itemList) {
			String aRow="";
			for(int i=0;i<10;i++) {
				aRow+=item.select(fieldname[i]).text()+" t";
			}
			aRow+=" r n";
			out.write(aRow);
		}
		out.close();
	}

	public static String getXMLPage() throws Exception
	{
		String serviceKey
		="";
		StringBuilder urlBuilder 
		= new StringBuilder("국토교통부_아파트매매 실거래 상세 자료 site 주소");
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
		urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode("11350", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode("202211", "UTF-8"));
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-type", "application/json");
		BufferedReader br;
		if(con.getResponseCode() >= 200 && con.getResponseCode() < 300)
		{
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else
		{
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		String xmlPage="";
		String line;
		while ((line = br.readLine()) != null)
		{
			xmlPage+=line;
		}
		br.close();
		con.disconnect();
		return xmlPage;
	}
}
