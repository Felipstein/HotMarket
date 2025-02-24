package com.hotmarket.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Coded by FarRed
 */
public class FileManager {

	public static Serializable readObject(File file) {
		if(!file.exists()) {
			return null;
		}
		Object imported = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream obj = new ObjectInputStream(fis);
			imported = obj.readObject();
			obj.close();
		} catch(IOException | ClassNotFoundException e) {
			return null;
		}
		return imported instanceof Serializable ? (Serializable) imported : null;
	}

	public static void writeObject(File file, Serializable serializable) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream obj = new ObjectOutputStream(fos);
		obj.writeObject(serializable);
		obj.close();
	}
	
	public static void overwriteText(File file, String text) throws IOException {
		assertExistence(file, false);
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(text);
		}
	}
	
	public static void appendText(File file, String text, boolean skipLine) throws IOException {
		appendText(file, text, skipLine, true);
	}
	
	public static void appendText(File file, String text, boolean skipLine, boolean duplicate) throws IOException {
		assertExistence(file, false);
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
			String append;
			boolean clone;
			if(lines(file).size() == 0) {
				append = text;
				clone = false;
			} else {
				append = skipLine ? "\n" + text : text;
				clone = readText(file).contains(text) && !duplicate;
			}
			writer.write(clone ? "" : append);
		}
	}
	
	public static String readLine(File file, int index) throws IOException {
		exists(file);
		try {
			return lines(file).get(index);
		} catch(IndexOutOfBoundsException e) {
			if(e.getMessage().equals("Index: 0, Size: 0")) {
				return "";
			} else {
				throw e;
			}
		}
	}
	
	public static List<String> lines(File file) throws IOException {
		exists(file);
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			return reader.lines().collect(Collectors.toList());
		}
	}
	
	public static String readText(File file) throws IOException {
		exists(file);
		return new String(readBytes(file));
	}
	
	public static void setLine(File file, int index, String newLine) throws IOException {
		String text = newLine == null ? "" : newLine;
		List<String> lines = lines(file);
		int size = lines.size();
		if(index < 0 || index >= size) {
			throw new IllegalArgumentException("O índice da linha não pode ser menor que 0 ou maior"
					+ " ou igual à quantidade de linhas do arquivo.");
		}
		lines.set(index, text);
		for(int i = 0; i < size; i++) {
			if(i == 0) {
				overwriteText(file, lines.get(i));
			} else {
				appendText(file, lines.get(i), true);
			}
		}
	}
	
	public static void removeLine(File file, int index) throws IOException {
		List<String> lines = lines(file);
		int size = lines.size();
		if(index < 0 || index >= size) {
			throw new IllegalArgumentException("O índice da linha não pode ser menor que 0 ou maior"
					+ " ou igual à quantidade de linhas do arquivo.");
		}
		lines.remove(index);
		if(size == 1) {
			overwriteText(file, "");
		} else {
			for(int i = 0; i < size - 1; i++) {
				if(i == 0) {
					overwriteText(file, lines.get(i));
				} else {
					appendText(file, lines.get(i), true);
				}
			}
		}
	}
	
	public static synchronized void copyDirectoryToDirectory(File srcDir, File destDir, 
			boolean replaceIfExists, boolean preserveFilesDate) throws IOException {
		if(srcDir.isFile() || destDir.isFile()) {
			throw new IOException("Os parâmetros do tipo File não podem ser arquivos.");
		}
		File newDir = new File(destDir, srcDir.getName());
		if(!deleteIfExists(replaceIfExists, newDir)) {
			return;
		}
		newDir.mkdir();
		File[] subFiles = srcDir.listFiles();
		for(File file : subFiles) {
			File copy = new File(newDir, file.getName());
			if(file.isFile()) {
				copyFileToFile(file, copy, true, preserveFilesDate);
			} else {
				copyDirectoryToDirectory(file, newDir, true, preserveFilesDate);
			}
		}
		if(preserveFilesDate) {
			destDir.setLastModified(srcDir.lastModified());
		}
	}
	
	public static synchronized void copyFileToDirectory(File file, File directory, 
			boolean replaceIfExists, boolean preserveFileDate) throws IOException {
		exists(file);
		FileManager.assertExistence(directory, true);
		if(file.isDirectory()) {
			throw new IOException("O parâmetro file não pode ser um diretório.");
		}
		if(directory.isFile()) {
			throw new IOException("O parâmetro directory não pode ser um arquivo.");
		}
		File copy = new File(directory, file.getName());
		if(!deleteIfExists(replaceIfExists, copy)) {
			return;
		}
		copy.createNewFile();
		writeBytes(copy, readBytes(file));
		if(preserveFileDate) {
			copy.setLastModified(file.lastModified());
		}
	}
	
	public static synchronized void copyFileToFile(File srcFile, File destFile, 
			boolean replaceIfExists, boolean preserveFileDate) throws IOException {
		exists(srcFile);
		if(srcFile.isDirectory() || destFile.isDirectory()) {
			throw new IOException("Os parâmetros do tipo File não podem ser diretórios.");
		}
		if(!deleteIfExists(replaceIfExists, destFile)) {
			return;
		}
		destFile.createNewFile();
		writeBytes(destFile, readBytes(srcFile));
		if(preserveFileDate) {
			destFile.setLastModified(srcFile.lastModified());
		}
	}
	
	public static synchronized void cutDirectoryToDirectory(File srcDir, File destDir, 
			boolean replaceIfExists, boolean preserveFilesDate) throws IOException {
		copyDirectoryToDirectory(srcDir, destDir, replaceIfExists, preserveFilesDate);
		File newDir = new File(destDir, srcDir.getName());
		if(!replaceIfExists && newDir.exists()) {
			return;
		}
		srcDir.delete();
	}
	
	public static synchronized void cutFileToDirectory(File file, File directory, 
			boolean replaceIfExists, boolean preserveFileDate) throws IOException {
		copyFileToDirectory(file, directory, replaceIfExists, preserveFileDate);
		File newFile = new File(directory, file.getName());
		if(!replaceIfExists && newFile.exists()) {
			return;
		}
		file.delete();
	}
	
	public static synchronized void cutFileToFile(File srcFile, File destFile, 
			boolean replaceIfExists, boolean preserveFileDate) throws IOException {
		copyFileToFile(srcFile, destFile, replaceIfExists, preserveFileDate);
		if(!replaceIfExists && destFile.exists()) {
			return;
		}
		srcFile.delete();
	}

	public static synchronized void writeBytes(File file, byte... bytes) throws IOException {
		assertExistence(file, false);
		try(FileOutputStream outputStream = new FileOutputStream(file)) {
			outputStream.write(bytes);
		}
	}
	
	public static synchronized byte[] readBytes(File file) throws IOException {
		if(!file.exists()) {
			return null;
		}
		long length = file.length();
		if(length > Integer.MAX_VALUE) {
			throw new IOException("O arquivo é muito grande para ser copiado.");
		}
		try(FileInputStream inputStream = new FileInputStream(file)) {
			byte[] array = new byte[(int) length];
			inputStream.read(array);
			return array;
		}
	}
	
	public static void renameFile(File file, String newName) {
		String parent = file.getParent();
		file.renameTo(new File((parent == null ? "" : parent) + "\\" + newName));
	}
	
	public static void setHidden(File file, boolean hidden) throws IOException {
		exists(file);
		Path path = file.toPath();
		Files.setAttribute(path, "dos:hidden", hidden);
	}
	
	public static void exists(File... files) throws IOException {
		for(File file : files) {
			if(!file.exists()) {
				throw new IOException("O " + (file.isFile() ? "arquivo " : "diretório ") + file.getName() + " não existe.");
			}
		}
	}

	public static boolean assertExistence(File file, boolean folder) throws IOException {
		boolean exists = file.exists();
		if(!exists) {
			if(folder) {
				file.mkdir();
			} else {
				file.createNewFile();
			}
			exists = true;
		}
		return exists;
	}
	
	private static boolean deleteIfExists(boolean deleteIfExists, File file) {
		if(!deleteIfExists && file.exists()) {
			return false;
		} else {
			if(file.exists()) {
				file.delete();
			}
			return true;
		}
	}
}
