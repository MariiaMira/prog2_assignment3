
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Exercise3 {

	private final List<Recording> recordings = new ArrayList<>();

	public static void main(String[] args) {
		Exercise3 app = new Exercise3();
        app.importRecordings("recording_input.txt");
        //app.exportRecordings("recording_output.txt");
        //app.importSales("sales_input.bin");
		System.out.println(app.getRecordings());
    }

	public void exportRecordings(String fileName) {

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

				recordings.add(new Recording(artist, title, year, genres));
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

