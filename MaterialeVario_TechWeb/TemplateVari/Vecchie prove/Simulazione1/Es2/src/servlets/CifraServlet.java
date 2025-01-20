package	servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;

public class CifraServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public boolean done = false;
	private Gson gson;
	
	@Override
	public void init() {
		gson = new Gson();
	}
	
	public String[] permutazioneCons(String parola) {
		String cons = "bcdfghjklmnpqrstvwxyz";
        List<String> allPermutations = new ArrayList<>();
        List<String> result = new ArrayList<>();

        // Generate all permutations
        permute(parola.toCharArray(), 0, allPermutations);

        // Filter permutations that start with a vowel
        for (String perm : allPermutations) {
            if (cons.contains(String.valueOf(perm.charAt(0)))) {
                result.add(perm);
                if (result.size() == 5) {
                    break; // Stop after finding 5 permutations
                }
            }
        }

        // Convert the result list to an array
        return result.toArray(new String[0]);
    }
	
	public String[] permutazioneVoc(String parola) {
        String voc = "aeiou";
        List<String> allPermutations = new ArrayList<>();
        List<String> result = new ArrayList<>();

        // Generate all permutations
        permute(parola.toCharArray(), 0, allPermutations);

        // Filter permutations that start with a vowel
        for (String perm : allPermutations) {
            if (voc.contains(String.valueOf(perm.charAt(0)))) {
                result.add(perm);
                if (result.size() == 5) {
                    break; // Stop after finding 5 permutations
                }
            }
        }

        // Convert the result list to an array
        return result.toArray(new String[0]);
    }

    private static void permute(char[] chars, int index, List<String> result) {
        if (index == chars.length - 1) {
            result.add(new String(chars));
            return;
        }
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            permute(chars, index + 1, result);
            swap(chars, index, i); // Backtrack
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
	
	synchronized private void changeDone()
	{
		this.done = true;
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
	throws ServletException, IOException {
		
		String word = request.getParameter("word");
		String type = request.getParameter("tipo");
		String[] result = null;
		
		if (type.equals("cons"))
			result = this.permutazioneCons(word);
		else
			result = this.permutazioneVoc(word);
		
		changeDone();
		// li stampo su out
		response.getWriter().println(gson.toJson(result));
	}
}
