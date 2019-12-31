/*
 * Mark Fuller

 * Dr.Cerny
 * Java ast generator
 */
package Baylor.AST;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimplePropertyDescriptor;

/*
 * Java AST class
 * contains the functionality to generate an ast given a .java source code file name.
 */
public class JavaAST {

	static StringBuilder sb;

	/*
	 * Given the root, return all of the .java files within that dir recursively
	 */
	static ArrayList<String> getSrcCode(File root) {

		ArrayList<String> srcFiles = new ArrayList<String>();

		for (File sub : root.listFiles()) {
			// add to solution if ending in .java and file
			if (sub.isFile() && sub.getName().endsWith(".java")) {
				srcFiles.add(sub.getAbsolutePath());
			} else if (sub.isDirectory()) {
				// recurse on dirs
				srcFiles.addAll(getSrcCode(sub));
			}
		}
		return srcFiles;
	}

	public static void main(String args[]) throws IOException {
		// error in command line arguments, print usage
		if (args.length != 1) {
			System.err.println("Usage: java -jar JavaAST.jar [dir]");
			System.exit(2);
		}

		// get all src files
		ArrayList<String> files = getSrcCode(new File(args[0]));

		// for each file get the ast and generate a string from the ast
		for (String file : files) {
			// build the ast
			ASTParser parser = ASTParser.newParser(AST.JLS3);
			parser.setSource(new String(Files.readAllBytes(Paths.get(file))).toCharArray());
			parser.setKind(ASTParser.K_COMPILATION_UNIT);

			final ASTNode node = (CompilationUnit) parser.createAST(null);

			// generate the string
			sb = new StringBuilder();
			print(node);
			// write that string to a file with ending .ast
			write(file + ".ast", sb.toString());
			sb = null;
		}
		System.out.println("Done");
	}

	/*
	 * Write This function writes a string to a file given a file names
	 */
	private static void write(String fileName, String con) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(con);

		writer.close();
	}

	/*
	 * Print Given the root node, iterate though each child node and get pertinent
	 * info, and append to string
	 */
	public static void print(ASTNode node) {
		// get the properties for the root node
		List properties = node.structuralPropertiesForType();

		// iterate through each of the properties and print the important information
		for (Iterator iterator = properties.iterator(); iterator.hasNext();) {
			Object descriptor = iterator.next();
			if (descriptor instanceof SimplePropertyDescriptor) {
				SimplePropertyDescriptor simple = (SimplePropertyDescriptor) descriptor;
				Object value = node.getStructuralProperty(simple);
				// System.out.println(simple.getId() + " (" + value.toString() + ")");
				sb.append(simple.getId() + " (" + value.toString() + ")");
			} else if (descriptor instanceof ChildPropertyDescriptor) {
				ChildPropertyDescriptor child = (ChildPropertyDescriptor) descriptor;
				ASTNode childNode = (ASTNode) node.getStructuralProperty(child);
				if (childNode != null) {
					// System.out.println("Child (" + child.getId() + ") {");
					sb.append("Child (" + child.getId() + ") {");
					print(childNode);
					// System.out.println("}");
					sb.append("}");
				}
			} else {
				ChildListPropertyDescriptor list = (ChildListPropertyDescriptor) descriptor;
				// System.out.println("List (" + list.getId() + "){");
				sb.append("List (" + list.getId() + "){");
				print((List) node.getStructuralProperty(list));
				// System.out.println("}");
				sb.append("}");
			}
		}
	}

	/*
	 * Print Given a list of nodes call print on each one. This is used in the above
	 * print function to iterate through each child
	 */
	public static void print(List nodes) {
		for (Iterator iterator = nodes.iterator(); iterator.hasNext();) {
			print((ASTNode) iterator.next());
		}
	}
}