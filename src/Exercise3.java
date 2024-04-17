import java.io.*;
import java.util.*;

public class Exercise3 {

	private final List<Recording> recordings = new ArrayList<>();

	public static void main(String[] args) {
		Exercise3 app = new Exercise3();
        app.importRecordings("recording_input.txt");
        app.exportRecordings("recording_output.txt");
        //app.importSales("sales_input.bin");
		System.out.println(app.getRecordings());
		for (Recording rec : app.getRecordings()){
			System.out.println(rec.getArtist());
			System.out.println(rec.getTitle());
			System.out.println(rec.getYear());
		}
    }

	public void exportRecordings(String fileName) {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(fileName));
			StringBuilder allRecords = new StringBuilder();
			for (Recording r : recordings) {
				String[] genres = r.getGenre().toArray(new String[0]);
				StringBuilder sb = new StringBuilder();
				sb.append("<recording>\n")
						.append("\t<artist>").append(r.getArtist()).append("</artist>\n")
						.append("\t<title>").append(r.getTitle()).append("</title>\n")
						.append("\t<year>").append(r.getYear()).append("</year>\n")
						.append("\t<genres>\n")
						.append(printGenres(genres))
						.append("\t</genres>\n")
						.append("</recording>\n");
				allRecords.append(sb);
			}
			writer.write(allRecords.toString()); // writes to file
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String printGenres(String[] g) { //slippa for loop
		StringBuilder sb = new StringBuilder();
		for (String genre : g) {
			sb.append(String.format("\t\t<genre>%s</genre>\n", genre));
		}
		return sb.toString();
	}


	public void importRecordings(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			int numberOfRecordings = Integer.parseInt(reader.readLine().trim());
			for (int i = 0; i < numberOfRecordings; i++) {
				String[] details = reader.readLine().trim().split(";");
				String artist = details[0];
				String title = details[1];
				int year = Integer.parseInt(details[2]);

				int genreCount = Integer.parseInt(reader.readLine().trim());
				Set<String> genres = new HashSet<>();
				for (int j = 0; j < genreCount; j++) {
					genres.add(reader.readLine().trim());
				}

				recordings.add(new Recording(title, artist, year, genres));
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file " + fileName + " was not found.");
		} catch (IOException e) {
			System.out.println("An error occurred while reading the file " + fileName);
		}
    }

	public Map<Integer, Double> importSales(String fileName) {
		return null;
	}

	public List<Recording> getRecordings() {
		return Collections.unmodifiableList(recordings);
	}

	public void setRecordings(List<Recording> recordings) {
		this.recordings.clear();
		this.recordings.addAll(recordings);
	}
}

