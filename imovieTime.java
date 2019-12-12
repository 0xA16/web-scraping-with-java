package movieScraping;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class imovieTime {
	
	private String imgLink;
	private ArrayList<String> movieWatchList;
	private String movieStory;
	
	imovieTime(String gettext) {
		Document doc = null;
		try {
			imgLink = "";
			movieWatchList = new ArrayList<String>();
			movieStory = "";
			
			doc = Jsoup.connect("https://www.google.com/search?q="+ gettext +"+inurl:lodynet.tv").userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21").get();
			Elements links = doc.select(".r a");
			String linkHref = "";
			for (Element link : links) {
				linkHref = link.attr("href");
				if (linkHref.contains("http://lodynet.tv/") && linkHref.contains("http://lodynet.tv/tag/")
						&& !linkHref.contains("webcache")) {
					break;

				}
		    }
			
			
			if (linkHref.contains("http://lodynet.tv/tag/")) {
				linkHref = findInTheTagPage(linkHref);
			}
			
			//System.out.println(linkHref);
			
			Document lodyNet = Jsoup.connect(linkHref).userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21").get();
			imgLink = lodyNet.select(".Poster img").first().attr("src");
			movieStory = lodyNet.select(".DetailsBoxContentInner").first().text();
			Elements players = lodyNet.select(".ServersList li");
			for(Element player : players) {
				movieWatchList.add(player.attr("data-embed"));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private String findInTheTagPage(String linkHref) throws IOException {
		
		Document lodyNet = Jsoup.connect(linkHref).userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21").get();
		String movieLink = lodyNet.select(".LodyBlock a").first().attr("href");
		
		return movieLink;
	}

	public String getImgLink() {
		return imgLink;
	}


	public String getMovieStory() {
		return movieStory;
	}

	public ArrayList<String> getMovieWatchList() {
		return movieWatchList;
	}

}
