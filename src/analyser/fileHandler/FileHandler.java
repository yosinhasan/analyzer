/**
 * 
 */
package analyser.fileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author elpai
 * 
 */
public class FileHandler {
	/**
	 * Input file.
	 */
	private String fileForRead;
	/**
	 * Output file.
	 */
	private String fileForWrite;

	private Set<String> listFirst;
	private Set<String> listSecond;

	/**
	 * Constructor without args. Set default value for properties.
	 */
	public FileHandler() {
		setFileForRead("");
		setFileForWrite("");
		listFirst = new HashSet<String>();
		listSecond = new HashSet<String>();
	}

	/**
	 * Constructor with params. Set values of params into respective properties.
	 * 
	 * @param fileRead
	 *            input file for read
	 * @param fileWrite
	 *            output file for write
	 */
	public FileHandler(final String fileRead, final String fileWrite) {
		if (!fileRead.equals("") && !fileWrite.equals("")) {
			setFileForRead(fileRead);
			setFileForWrite(fileWrite);
			listFirst = new HashSet<String>();
			listSecond = new HashSet<String>();
		}
	}

	/**
	 * Getter.
	 * 
	 * @return String
	 */
	public final String getFileForRead() {
		return fileForRead;
	}

	/**
	 * Setter.
	 * 
	 * @param fileForRead2
	 *            input file
	 */
	public final void setFileForRead(final String fileForRead2) {
		this.fileForRead = fileForRead2;
	}

	/**
	 * Getter.
	 * 
	 * @return String
	 */
	public final String getFileForWrite() {
		return fileForWrite;
	}

	/**
	 * Setter.
	 * 
	 * @param fileForWrite2
	 *            output file
	 */
	public final void setFileForWrite(final String fileForWrite2) {
		this.fileForWrite = fileForWrite2;
	}

	/**
	 * Read from input file and write to output file.
	 */
	public final void readAndWrite() {
		try (InputStream in = new FileInputStream(getFileForRead());
				Reader fileReader = new InputStreamReader(in,
						StandardCharsets.UTF_8);
				Scanner reader = new Scanner(fileReader);
				OutputStream out = new FileOutputStream(getFileForWrite());
				Writer fileWriter = new OutputStreamWriter(out,
						StandardCharsets.UTF_8);
				Formatter writer = new Formatter(fileWriter)) {

			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				listFirst.add(line);
				writer.format("%s\n", line);
			}
			System.out.println("Read and write: job done.");
		} catch (IOException e) {
			System.err.println("Error in fileHandler: " + e.getMessage());
		}
	}

	/**
	 * Read files from root folder and write to output file.
	 * 
	 * @param dirName
	 *            directory name
	 * @param outputFile
	 *            output file
	 */
	public final void processRootFolder(final String dirName,
			final String outputFile) {
		File dir = new File(dirName);
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			if (files.length > 0) {
				try (OutputStream out = new FileOutputStream(outputFile);
						Writer fileWriter = new OutputStreamWriter(out,
								StandardCharsets.UTF_8);
						Formatter writer = new Formatter(fileWriter)) {
					for (File name : files) {
						if (name.isFile()) {
							processReadAndWrite(name, writer);
						}
					}
					System.out.println("processing root folder: job done.");
				} catch (IOException e) {
					System.err.println("Error in fileHandler: "
							+ e.getMessage());
				}

			}

		}
	}

	/**
	 * Read lines from input file and write lines to output file.
	 * 
	 * @param readName
	 *            input file
	 * @param writer
	 *            output file
	 */
	private void processReadAndWrite(final File readName, Formatter writer) {
		try (InputStream in = new FileInputStream(readName);
				Reader fileReader = new InputStreamReader(in,
						StandardCharsets.UTF_8);
				Scanner reader = new Scanner(fileReader);) {
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				listSecond.add(line);
				writer.format("%s\n", line);
			}
		} catch (IOException e) {
			System.err.println("Error in fileHandler: " + e.getMessage());
		}
	}

	/**
	 * Find matches of values listFirst from listSecond hash set.
	 */
	public final void findMatches() {
		System.out.println("Matches: ");
		// Iterator<String> iterator = listFirst.iterator();
		// while (iterator.hasNext()) {
		// String string = iterator.next();
		// if (listSecond.contains(string)) {
		// System.out.println(string);
		// }
		// }
		int count = 0;
		for (String string : listFirst) {
			if (listSecond.contains(string)) {
				count++;
				System.out.println(string);
			}
		}
		System.out.println("Matches: " + count + " strings.");
		System.out.println("Find matches: job done.");
	}

}
