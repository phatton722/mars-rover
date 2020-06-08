package src.nasaimage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NasaImageFinder {
    private final String apiURL;

    public NasaImageFinder() {
	this.apiURL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos";
    }

    public Map<String, ArrayList<String>> downloadAndStoreImagesByEarthDate(ArrayList<String> dates, String imageCacheDir) throws IOException, MalformedURLException, ProtocolException, JSONException {
	Map<String, ArrayList<String>> mapDateToImageFiles = new HashMap<String, ArrayList<String>>();
	for (String date : dates) {
	    // get response from nasa api
	    String jsonString = this.getImageJSON(date);	
	    if (jsonString == "") {
		continue;
	    }

	    // parse response and get all the `img_src` values
	    ArrayList<String> imageURLs = this.getImageURLs(jsonString);

	    // download each image by url and then store it
	    ArrayList<String> fileNames = this.downloadAndStoreImages(imageURLs, imageCacheDir);

	    if (fileNames.size() != 0) {
		mapDateToImageFiles.put(date, fileNames);
	    }
	}

	return mapDateToImageFiles;
    }

    private String getImageJSON(String date) throws MalformedURLException, ProtocolException, IOException {
	URL url = new URL(this.apiURL+"?api_key=DEMO_KEY&earth_date="+date);
	HttpURLConnection con = (HttpURLConnection) url.openConnection();
	con.setRequestMethod("GET");
	con.setRequestProperty("Content-Type", "application/json");

	BufferedReader in = null;
	try {
	    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	} catch (IOException e) {
	    // don't care why it failed, just log and keep going
	    System.out.println(String.format("error when finding image(s) for '%s'", date));
	    return "";
	}

	String inputLine = null;
	StringBuffer content = new StringBuffer();
	while ((inputLine = in.readLine()) != null) {
	    content.append(inputLine);
	}
	in.close();
	con.disconnect();

	return content.toString();
    }

    private ArrayList<String> getImageURLs(String json) throws JSONException {
	ArrayList<String> urls = new ArrayList<String>();
	JSONObject jo = new JSONObject(json);
	JSONArray ja = jo.getJSONArray("photos");

	for (int i = 0; i < ja.length(); i++) {
	    try {
		JSONObject obj = ja.getJSONObject(i);
		urls.add(obj.getString("img_src"));
	    } catch (JSONException e) {
		// log and keep going
		System.out.println(String.format("JSON Exception caught: '%s'", e.toString()));
		continue;
	    }
	}
	return urls;
    }

    private ArrayList<String> downloadAndStoreImages(ArrayList<String> imageURLs, String imageCacheDir) throws MalformedURLException, IOException {
	ArrayList<String> fileNames = new ArrayList<String>();
	for (String imageURL : imageURLs) {
	    // fetching these images by http returns 304 so we're going to use https
	    String urlString = imageURL.replaceAll("http://", "https://");

	    URL url = new URL(urlString);
	    String filePath = url.getFile();
	    String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
	    String fileNameAndPath = String.format("%s/%s", imageCacheDir, fileName);
	    fileNames.add(fileNameAndPath);

	    File f = new File(fileNameAndPath);
	    if (f.exists()) {
		System.out.println(String.format("image '%s' already exists, skipping download", fileNameAndPath));
		continue;
	    }

	    HttpURLConnection con = (HttpURLConnection) url.openConnection();
	    con.setRequestMethod("GET");
	    con.setRequestProperty("Content-Type", "image/jpeg");

	    InputStream is = con.getInputStream();
	    int length = 0;
	    int i = 1;
	    byte[] b = new byte[2048];

	    FileOutputStream fos = new FileOutputStream(fileNameAndPath);
	    while((length = is.read(b)) != -1) {
		fos.write(b, 2048*(i-1), length);
	    }

	    fos.close();
	    is.close();
	    con.disconnect();
	}

	return fileNames;
    }
}
