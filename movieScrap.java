package movieScraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class movieScrap {
	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter fgygy : ");
		String input = s.nextLine().replace(" " , "+");
		imovieTime i = new imovieTime(input);
		System.out.println(i.getImgLink());
		System.out.println(i.getMovieStory());
		for (String player : i.getMovieWatchList()) {
			System.out.println(player);
		}
	}
}
