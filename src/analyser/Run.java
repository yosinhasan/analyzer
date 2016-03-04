/**
 * 
 */
package analyser;

import analyser.fileHandler.FileHandler;

/**
 * @author elpai
 * 
 */
public class Run {

	/**
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		show("Task1");
		if (args.length > 0) {
			show("FileInput: " + args[0]);
			show("FileOutput: " + args[1]);
			show("Processing...");
			FileHandler fileHandler = new FileHandler(args[0], args[1]);
			fileHandler.readAndWrite();
			fileHandler.processRootFolder(args[2], args[3]);
			fileHandler.findMatches();
		} else {
			show("No input arguments.");
		}
		show("Job done.");

	}

	/**
	 * Show message.
	 * 
	 * @param msg
	 *            string message
	 */
	private static void show(String msg) {
		System.out.println(msg);
	}

	/**
	 * Constructor.
	 */
	private Run() {

	}
}
