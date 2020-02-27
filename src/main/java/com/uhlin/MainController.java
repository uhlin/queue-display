package com.uhlin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
public class MainController {
	@RequestMapping("/")
	public String index(Model m) {
		Gson gson = new Gson();
		//final String s1 = "{\"nr\":4690,\"pos\":1},{\"nr\":1668,\"pos\":2},{\"nr\":531,\"pos\":3},";
		//final String s2 = "{\"nr\":1406,\"pos\":4},{\"nr\":1968,\"pos\":5},{\"nr\":1469,\"pos\":6},";
		//final String s3 = "{\"nr\":315,\"pos\":7},{\"nr\":4968,\"pos\":8},{\"nr\":3585,\"pos\":9}";
		Type type = new TypeToken<ArrayList<Patient>>(){}.getType();
		//List<Patient> queue = gson.fromJson('[' + s1 + s2 + s3 + ']', type);
		List<Patient> queue = gson.fromJson(getRemoteData(), type);

		m.addAttribute("queue", queue);
		m.addAttribute("queueNumbers", getNumbers(queue));
		m.addAttribute("queuePositions", getPositions(queue));

		return "index";
	}



	String getRemoteData() {
		String data = null;

		try {
			URL url = new URL("https://www.nifty-networks.net/stuff/dump.json");
			URLConnection conn = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			if ((data = br.readLine()) != null)
				br.close();

			System.out.println("MainController: getRemoteData: OK");
			//System.out.println(data);
		} catch (Exception ex) {
			System.err.println("MainController: getRemoteData: " + ex.getMessage());
			return null;
		}

		return data;
	}



	String[] getNumbers(final List<Patient> queue) {
		ArrayList<String> arraylist = new ArrayList<String>();

		for (Patient x : queue)
			arraylist.add(x.getNr().toString());

		String[] numbers = new String[arraylist.size()];

		for (int i = 0; i < arraylist.size(); i++)
			numbers[i] = arraylist.get(i);

		return numbers;
	}



	String[] getPositions(final List<Patient> queue) {
		ArrayList<String> arraylist = new ArrayList<String>();

		for (Patient x : queue)
			arraylist.add(x.getPos().toString());

		String[] numbers = new String[arraylist.size()];

		for (int i = 0; i < arraylist.size(); i++)
			numbers[i] = arraylist.get(i);

		return numbers;
	}
}
