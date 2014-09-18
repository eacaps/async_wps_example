package gov.ic.wic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetPostTestClient {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		GetPostTestClient http = new GetPostTestClient();

		String wpsurl = "http://localhost:8080/geoserver2.5/wps";
		String query = "?service=WPS&version=1.0.0&request=Execute&Identifier=gs:HelloWPS&DataInputs=name%3Dbill";
		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet(wpsurl+query);

		
		System.out.println("\nTesting 2 - Send Http POST request");
		String postdoc = "";
//		postdoc = readFile("src/test/resources/fullresponse.xml");
//		postdoc = readFile("src/test/resources/simple.xml");
		postdoc = readFile("src/test/resources/async1.xml");
		http.sendPost(wpsurl, postdoc);

	}

	// HTTP GET request
	private void sendGet(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}

	// HTTP POST request
	private void sendPost(String url, String doc) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", "application/xml");

		// Send post request
		con.setDoOutput(true);
		con.setDoInput(true);/*
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
*/
		
		OutputStream out = con.getOutputStream();
		OutputStreamWriter wout = new OutputStreamWriter(out, "UTF-8");

		wout.write(doc);

		wout.flush();
		out.close();

	/*	InputStream in = con.getInputStream();
		int c;
		while ((c = in.read()) != -1) System.out.write(c);
		System.out.println();
		in.close();
		out.close();
		connection.disconnect();
		}*/
		
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}

	private static String readFile(String filename) throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line = null;
		while ((line = reader.readLine()) != null) {
		    sb.append(line);
		}
		return sb.toString();
	}
}