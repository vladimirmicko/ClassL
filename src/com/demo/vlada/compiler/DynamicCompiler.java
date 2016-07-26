package com.demo.vlada.compiler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import hello.LocalModule;
import hello.Micko;

public class DynamicCompiler implements LocalModule {

	private static String classOutputFolder = "/classes/demo";

	public static class MyDiagnosticListener implements DiagnosticListener<JavaFileObject> {
		public void report(Diagnostic<? extends JavaFileObject> diagnostic) {
			System.out.println("Line Number->" + diagnostic.getLineNumber());
			System.out.println("code->" + diagnostic.getCode());
			System.out.println("Message->" + diagnostic.getMessage(Locale.ENGLISH));
			System.out.println("Source->" + diagnostic.getSource());
			System.out.println(" ");
		}
	}

	public static class InMemoryJavaFileObject extends SimpleJavaFileObject {
		private String contents = null;

		public InMemoryJavaFileObject(String className, String contents) throws Exception {
			super(URI.create("string:///" + className.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
			this.contents = contents;
		}

		public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
			return contents;
		}
	}

	private static JavaFileObject getJavaFileObject(String code) {
		StringBuilder contents = new StringBuilder("package hello;" + "public class Micko implements LocalModule {"
				+ "  public void testAdd() { " + "    System.out.println(201+300); " + "} }");

		JavaFileObject so = null;
		try {
			so = new InMemoryJavaFileObject("hello.Micko", code);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return so;
	}

	/** compile your files by JavaCompiler */
	public static void compile(Iterable<? extends JavaFileObject> files) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		MyDiagnosticListener c = new MyDiagnosticListener();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(c, Locale.ENGLISH, null);
		Iterable options = Arrays.asList("-d", classOutputFolder);
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, c, options, null, files);
		Boolean result = task.call();
		if (result == true) {
			System.out.println("Succeeded");
		}
	}

	/** run class from the compiled byte code file by URLClassloader */
	public static void runIt() {
//		classOutputFolder = "/classes/demo";
//		File file = new File(classOutputFolder);

		try {
//			URL url = file.toURL();
//			URL[] urls = new URL[] { url };
//			CustomClassLoader loader = new CustomClassLoader(ClassLoader.getSystemClassLoader());
			
			CustomClassLoader loader = new CustomClassLoader(hello.LocalModule.class.getClassLoader());
//			Class lm = loader.loadClass("hello.LocalModule");
			Class mickoClass = loader.loadClass("hello.Micko");
			Class cickoClass = loader.loadClass("hello.Cicko");
			System.out.println("loaded hello.Micko and hello.LocalModule");
			
			Object instanceMicko = mickoClass.newInstance();
			Object instanceCicko = cickoClass.newInstance();

			// ClassLoader loader = new URLClassLoader(urls);
			// Class lm = loader.loadClass("hello.LocalModule");
			// System.out.println("loaded LocalModule");
			// Class thisClass = loader.loadClass("hello.Micko");
			// System.out.println("loaded hello.Micko");

//			System.out.println("------------------------ Reflections");
//			Class params[] = {};
//			Object paramsObj[] = {};
//			Method thisMethod = mickoClass.getDeclaredMethod("testAdd", params);
//			thisMethod.invoke(instanceMicko, paramsObj);
//			System.out.println("------------------------ Reflections");
			
			
			System.out.println("------------------------ ClassLoader");
			System.out.println(hello.LocalModule.class.getClassLoader());
			System.out.println(hello.Micko.class.getClassLoader());
			System.out.println(hello.Cicko.class.getClassLoader());
			System.out.println("------------------------ ClassLoader");

			LocalModule lmMicko = ((hello.LocalModule) instanceMicko);
			LocalModule lmCicko = ((hello.LocalModule) instanceCicko);
			lmMicko.testAdd();
			lmCicko.testAdd();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		JavaFileObject file = getJavaFileObject("");
		Iterable<? extends JavaFileObject> files = Arrays.asList(file);
		compile(files);
		runIt();
	}

	public static void activate(String code) throws Exception {
		JavaFileObject file = getJavaFileObject(code);
		Iterable<? extends JavaFileObject> files = Arrays.asList(file);
		compile(files);
		runIt();
	}

	@Override
	public void testAdd() {
		System.out.println("Dynamic Compiler class --------------------------------");

	}

}