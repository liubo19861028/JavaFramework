package com.framework.comm.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * 接口或类的说明: 文件读写操作工具类
 *
 * <br>
 * ========================== <br>
 * 公司：南京壹号家信息科技有限公司 <br>
 * 开发：yisheng.lu@yihaojiaju.com <br>
 * 版本：1.0 <br>
 * 创建时间：2017-11-15 上午9:25:35 <br>
 * ==========================
 *
 */
@SuppressWarnings("restriction")
public class FileUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public static final String SUFFIX = ".log"; // 用于日志文件的后缀

	/** 文件大小 **/
	private static final int BUFFER_SIZE = 16 * 1024;

	/**
	 * 读取指定文件的文本内容
	 *
	 * @param filepath
	 *            文件的路径
	 * @return
	 */
	public static String readFile(String filepath) {
		StringBuffer buffer = new StringBuffer();
		try {
			File file = new File(filepath);
			FileReader fr = new FileReader(file);
			BufferedReader in = new BufferedReader(fr);
			String line = in.readLine();
			while (line != null) {
				buffer.append(line);
				buffer.append("\n");
				line = in.readLine();
			}
			in.close();
			fr.close();
		} catch (IOException e) {
			logger.error("the file name is error");
		}
		return buffer.toString();
	}

	/**
	 * 把字符串写入文本文件
	 *
	 * @param fileName
	 * @param content
	 * @param append
	 * @throws IOException
	 */
	public static void write(String fileName, String content, boolean append) throws IOException {
		File file = loadFile(fileName, true);
		BufferedWriter out = new BufferedWriter(new FileWriter(file, append));
		out.write(content);
		out.newLine();
		out.close();
	}

	/**
	 * 将数组中的内容以文件行形式（数组的每个元素为一行）写入文件写文件
	 * 
	 * @param fileName
	 * @param content
	 * @throws IOException
	 */
	public static void write(String fileName, String content[]) throws IOException {
		if (content == null) {
			logger.warn("需要写入文件的内容为空！");
			return;
		}
		File file = loadFile(fileName, true);
		BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
		for (int i = 0; i < content.length; i++) {
			out.newLine();
			out.write(content[i]);
		}
		out.close();
	}

	/**
	 * 将数组中的内容以文件行形式（数组的每个元素为一行）写入文件写文件
	 * 
	 * @param fileName
	 * @param contents
	 * @throws IOException
	 */
	public static void write(String fileName, Collection<String> contents) throws IOException {
		if (contents == null || contents.isEmpty()) {
			logger.warn("需要写入文件的内容为空！");
			return;
		}
		File file = loadFile(fileName, true);
		BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
		if (file.length() != 0) {
			out.newLine();
		}
		Iterator<String> iterator = contents.iterator();
		while (iterator.hasNext()) {
			out.write(iterator.next());
			out.newLine();
		}
		out.close();
	}

	/**
	 * 将数组中的内容以文件行形式（数组的每个元素为一行）写入文件写文件
	 * 
	 * @param fileName
	 * @param contents
	 * @throws IOException
	 */
	public static void write(String fileName, Set<Long> contents) throws IOException {
		if (contents == null || contents.isEmpty()) {
			logger.warn("需要写入文件的内容为空！");
			return;
		}
		File file = loadFile(fileName, true);
		BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
		if (file.length() != 0) {
			out.newLine();
		}
		Iterator<Long> iterator = contents.iterator();
		while (iterator.hasNext()) {
			out.write(iterator.next().longValue() + "");
			out.newLine();
		}
		out.close();
	}

	/**
	 * 根据文件名获取指定的文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param isCreate
	 *            如果文件不存在，是否需要创建
	 * @return
	 */
	public static File loadFile(String fileName, boolean isCreate) throws IOException {
		File file = new File(fileName);
		if (file.exists())
			return file;

		if (!isCreate)
			return null;

		File parentFile = file.getParentFile();
		if (!parentFile.exists() && parentFile.getPath() != null && !"".equals(parentFile.getPath()))
			if (!parentFile.mkdirs())
				return null;

		if (file.createNewFile())
			return file;

		return null;
	}

	public static void write(String fileName, String content) throws IOException {
		write(fileName, content, true);
	}

	/**
	 * 将数据写入文件中
	 * 
	 * @param fileName
	 * @param data
	 * @throws IOException
	 */
	public static void write(String fileName, byte[] data) throws IOException {
		File file = loadFile(fileName, true);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
		bufferedOutputStream.write(data);
		bufferedOutputStream.close();
	}

	/**
	 * 路径指向的是否是一个文件件
	 *
	 * @param path
	 * @return
	 */
	public static boolean mkDirs(String path) {
		File file = new File(path);
		if (!file.isDirectory()) {
			return file.mkdirs();
		}
		return file.isDirectory();
	}

	/**
	 * 删除指定的文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean delete(String filePath) {
		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			return file.delete();
		} else {
			return false;
		}
	}

	/**
	 * 删除文件名包含指定字符的文件
	 * 
	 * @param file
	 * @param likeName
	 */
	public static void deleteFilesLikeName(String path, String likeName) {
		File file = new File(path);
		if (file.isFile() && file.exists()) {
			// 是文件
			String temp = file.getName().substring(0, file.getName().lastIndexOf("."));
			if (temp.indexOf(likeName) != -1) {
				file.delete();
			}
		} else {
			// 是目录
			File[] files = file.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					deleteFilesLikeName(files[i].getPath(), likeName);
				}
			}
		}
	}

	/**
	 * 删除某目录下文件名字包含指定字符的文件
	 * 
	 * @param dir
	 * @param likeName
	 */
	public static void deleteDirFilesLikeName(String dir, String likeName) {
		File file = new File(dir);
		if (file.exists()) {
			deleteFilesLikeName(dir, likeName);
		} else {
			System.out.println("路径不存在");
		}
	}

	/**
	 * 文件是否存在
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isFileExists(String path) {
		if (path == null || path.trim().equals(""))
			return false;
		File file = new File(path);
		if (file.exists()) {
			return file.isDirectory() ? false : true;
		} else {
			return false;
		}
	}

	/**
	 * 是否是文本文件名称
	 * 
	 * @param filename
	 * @return
	 */
	public static boolean isTextFilename(String filename) {
		if (filename == null)
			return false;
		filename = filename.toLowerCase();
		return filename.endsWith("txt") || filename.endsWith("text") || filename.endsWith("log");
	}

	/**
	 * 文件拷贝
	 * 
	 * @param src
	 * @param dst
	 * @return
	 */
	public static boolean copy(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.flush();
					out.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return true;
	}

	/**
	 * 文件拷贝
	 * 
	 * @param oldPath旧文件路径
	 * @param dst文件路径
	 * @return
	 */
	public static void copyFile(String oldPath, String newPath, String newName) {
		File sFile = new File(oldPath); // 创建文件对象
		File dFile = new File(newPath + "/" + newName);

		File crateFile = new File(newPath);
		if (!crateFile.exists())
			crateFile.mkdirs();

		try {
			dFile.createNewFile(); // 新建文件
			FileInputStream fis = new FileInputStream(sFile);
			FileOutputStream fout = new FileOutputStream(dFile);
			byte[] date = new byte[512]; // 创建字节数组
			int rs = -1;
			while ((rs = fis.read(date)) > 0) { // 循环读取文件
				fout.write(date, 0, rs); // 向文件写数据
			}
			fout.close();
			fis.close(); // 关闭流

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	/**
	 * 保存文件
	 * 
	 * @param path
	 *            保存路径
	 * @param name
	 *            文件名
	 * @param file
	 *            文件
	 */
	public static void saveFile(String path, String name, File file) {
		File newFile = new File(path);
		if (!newFile.exists())
			newFile.mkdirs();
		newFile = new File(path + "/" + name);
		FileUtil.copy(file, newFile);
		file.delete();// 删除上传缓存文件
	}

	/**
	 * 提取后缀名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos + 1);
	}

	/**
	 * 压缩图片
	 * 
	 * @param url
	 *            源图片地址
	 * @param w
	 *            压缩宽度
	 * @param h
	 *            压缩高度
	 * @param percentage
	 *            是否等比缩放
	 * @return
	 * @throws Exception
	 */
	public static String doCompress(String url, int w, int h, boolean percentage) throws Exception {
		if (url != null && w > 0 && h > 0) {
			String newImage = null;
			try {
				File file = new File(url);
				// 文件不存在
				if (!file.exists()) {
					return null;
				}
				/* 读取图片信息 */
				Image srcFile = ImageIO.read(file);
				int new_w = w;
				int new_h = h;
				if (percentage) {
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) srcFile.getWidth(null)) / (double) w + 0.1;
					double rate2 = ((double) srcFile.getHeight(null)) / (double) h + 0.1;
					double rate = rate1 > rate2 ? rate1 : rate2;
					new_w = (int) (((double) srcFile.getWidth(null)) / rate);
					new_h = (int) (((double) srcFile.getHeight(null)) / rate);
				}
				/* 宽高设定 */
				BufferedImage tag = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(srcFile, 0, 0, new_w, new_h, null);
				/* 压缩后的文件名 */
				String fileName = file.getName();
				newImage = file.getParent() + "/" + new_w + "X" + new_h + "-" + fileName;
				/* 压缩之后临时存放位置 */
				FileOutputStream out = new FileOutputStream(newImage);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
				/* 压缩质量 */
				jep.setQuality(0.75f, true);
				encoder.encode(tag, jep);
				out.close();
				srcFile.flush();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new Exception();
			}
			return newImage;
		} else {
			return null;
		}
	}

	/**
	 * 删除文件及其压缩
	 * 
	 * @param filePath
	 * @param name
	 */
	public static void delete(String filePath, String name) {
		File file = new File(filePath);
		if (!file.exists())
			return;
		File directory = file.getParentFile();
		File[] files = directory.listFiles();
		for (File f : files) {
			if (f.getName().endsWith(name)) {
				f.delete();
			}
		}
		file.delete();
	}
}
