package com.cao.xps.common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件处理工具类
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	/**
	 * commons-io 工具类常用方法使用
	 * @param args
	 */
	public static void main(String[] args) {
		//FileUtils 常用方法
		try {
			File file = new File("C:\\Users\\peng\\Desktop\\dir测试\\01\\0123.txt");
			File file2 = new File("C:\\Users\\peng\\Desktop\\dir测试\\01\\02\\01.txt");
			File file3 = new File("C:\\Users\\peng\\Desktop\\dir测试\\01\\02");
			File file4 = new File("C:\\Users\\peng\\Desktop\\dir测试\\01\\022");
			File file5 = new File("C:\\Users\\peng\\Desktop\\dir测试\\01\\022\\baidu_logo.gif");
			File file6 = new File("C:\\Users\\peng\\Desktop\\dir测试\\01\\023");
			File file7 = new File("C:\\Users\\peng\\Desktop\\dir测试\\01\\02\\03.txt");

			//复制文件  (preserveFileDate:true(默认):带文件创建时间，false：文件创建时间为当前时间戳)
			//FileUtils.copyFile(file,file2,false);

			//复制文件到文件夹 （文件名称相同，已存在则覆盖）
			//FileUtils.copyFileToDirectory(file,file3,true);

			//将文件夹下所有内容复制到另一文件夹下（已有文件不会被删除，文件名称重复则覆盖）
			//FileUtils.copyDirectory(file3,file4);

			//将url内容复制进文件中
			//URL url=new URL("https://www.baidu.com/img/baidu_logo.gif");
			//FileUtils.copyURLToFile(url,file5);

			//创建多级文件夹
			//FileUtils.forceMkdir(new File("C:\\Users\\peng\\Desktop\\test测试\\01\\02\\03"));

			//移动文件夹（目标文件夹路径不能存在）
			//FileUtils.moveDirectory(file4,file6);

			//移动文件夹到文件夹下（createDestDir：是否创建文件夹false：直接移动）
			//FileUtils.moveDirectoryToDirectory(file6,file3,false);

			//移动文件（会覆盖名称）
			//FileUtils.moveFile(file7,file2);

			//静态删除（无法删除时不会抛出异常,直接删除）
			//FileUtils.deleteQuietly(file3);

			//删除文件或文件夹（会抛异常）
			//FileUtils.forceDelete(file3);

			//递归删除一个目录(只能放文件夹)。
			//FileUtils.deleteDirectory(file3);

			//清理文件夹下所有内容，不删除该文件夹(只能放文件夹)。。
			//FileUtils.cleanDirectory(file3);

			//读取文件内容为String
			//System.out.println(FileUtils.readFileToString(file,"utf-8"));

			//读取文件内容为List<String>数组
			// List<String> list=new ArrayList<String>();
			// list=FileUtils.readLines(file,"utf-8");


            /*
            将list<String>写入文件
            List<String> list=new ArrayList<String>();
            list.add("123");
            list.add("456");
            list.add("789");
            FileUtils.writeLines(file,list);*/

			//对比两个文件的内容
			//System.out.println(FileUtils.contentEquals(file,file2));

		} catch (Exception e) {
			e.printStackTrace();
		}


		//IOUtils 常用方法
		try {
			InputStream inputStream=new FileInputStream("C:\\Users\\peng\\Desktop\\dir测试\\01\\0123.txt");
			InputStream inputStream2=new FileInputStream("C:\\Users\\peng\\Desktop\\dir测试\\01\\01.txt");
			OutputStream outputStream=new FileOutputStream("C:\\Users\\peng\\Desktop\\dir测试\\01\\01.txt");
			BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
			BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(outputStream);

			Reader reader=new FileReader("C:\\Users\\peng\\Desktop\\dir测试\\01\\0123.txt");
			BufferedReader bufferedReader=new BufferedReader(reader);
			Writer writer=new FileWriter("");
			BufferedWriter bufferedWriter=new BufferedWriter(writer);

			//复制文件内容到指定文件中
			//IOUtils.copy(inputStream,outputStream);

			//复制文件内容到指定文件中(大文件（默认传输大小1024*4）)
			//IOUtils.copyLarge(inputStream,outputStream);

			//读取文件内容
			//IOUtils.readLines(reader);
			//IOUtils.readLines(inputStream);

			//写内容(已存在则清空重新写入 lineEnding:分隔符，null：回车)
			/*List<String> list=new ArrayList<>();
			list.add("ceshi");
			list.add("12364987");
			IOUtils.writeLines(list,null,outputStream);*/

			//关闭流
			//IOUtils.closeQuietly(outputStream);

			//对比两个输入流是否一致
			//System.out.println(IOUtils.contentEquals(inputStream,inputStream2));
			//System.out.println(IOUtils.contentEquals(reader,reader));

			//对比两个输入流是否一致(忽略分割符)
			//IOUtils.contentEqualsIgnoreEOL(reader,reader);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件/文件夹
	 * @param dir 文件/文件夹路径
	 */
	public static void deleteDir(String dir){
		try {
			File file=new File(dir);
			if(file.exists()){
				if(file.isDirectory()){
					File[] files = file.listFiles();
					for(int i=0; i<files.length; i++) {
						deleteDir(files[i].getPath());
					}
				}
				file.delete();
				logger.info(dir+"删除成功");
			}
		}catch (Exception e){
			logger.info("删除文件夹异常"+e.getMessage());
		}
	}

	/**
	 * 浏览器导出文件
	 * @param response
	 * @param path 文件路径
	 */
	public void fileExport(HttpServletResponse response, String path) {
		File localFile = new File(path);
		if(!localFile.exists()) {
			logger.info("文件不存在");
			return;
		}
		InputStream stream = null;
		OutputStream os=null;
		/* 设置文件ContentType类型，这样设置，会自动判断下载文件类型 */
		response.setContentType("multipart/form-data");
		/* 设置文件头：最后一个参数是设置下载文件名 */
		response.setHeader("Content-Disposition", "attachment;filename="+localFile.getName());
		try{
			stream=new FileInputStream(localFile);
			os = response.getOutputStream();
			byte[] b = new byte[1024];
			int len;
			while((len = stream.read(b)) > 0){
				os.write(b,0,len);
			}
			os.flush();
			logger.info(localFile.getName()+"文件导出成功");
		}catch (IOException ioe){
			ioe.printStackTrace();
		}finally {
			try {
				os.close();
				stream.close();
			} catch (IOException e) {
				logger.info("文件流关闭异常"+e.getMessage());
			}
		}

	}

	/**
	 * 获取文件中内容（行）
	 * @param path
	 * @return
	 */
	public static List<String> getFileContent(String path){
		FileInputStream fis=null;
		try {
			List mPaths = new ArrayList<>();
			File file = new File( path);
			if(!file.exists()){
				logger.info("文件不存在");
				return null;
			}
			fis= new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
			String line;
			while ((line = br.readLine()) != null) {
				mPaths.add(line);
			}
			return mPaths;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * copy文件夹下所有内容
	 * @param path 源文件夹路径
	 * @param copyPath 目的文件夹路径
	 * @throws IOException
	 */
	public static void copyDir(String path, String copyPath) throws IOException {
		File filePath = new File(path);
		if(filePath.isDirectory()){
			File[] list = filePath.listFiles();
			for(int i=0; i<list.length; i++){
				String newPath = path + File.separator + list[i].getName();
				String newCopyPath = copyPath + File.separator + list[i].getName();
				File newFile = new File(copyPath);
				if(!newFile.exists()){
					newFile.mkdirs();
				}
				copyDir(newPath, newCopyPath);
			}
			logger.info(path+"-->"+copyPath+"复制成功");
		}else if(filePath.isFile()){
			org.apache.commons.io.FileUtils.copyFile(filePath,new File(copyPath));
		}
	}

	/**
	 * 复制文件到指定文件夹下
	 * @param srcPathStr 文件路径
	 * @param desPathStr 移动的文件夹名称(无需包含文件名)
	 */
	public static void copyfile(String srcPathStr, String desPathStr){
		//获取源文件的名称
		String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("\\")+1); //目标文件地址
		System.out.println("源文件:"+newFileName);
		desPathStr = desPathStr + File.separator + newFileName; //源文件地址
		System.out.println("目标文件地址:"+desPathStr);
		try{
			FileInputStream fis = new FileInputStream(srcPathStr);//创建输入流对象
			FileOutputStream fos = new FileOutputStream(desPathStr); //创建输出流对象
			byte datas[] = new byte[1024*8];//创建搬运工具
			int len = 0;//创建长度
			//循环读取数据
			while((len = fis.read(datas))!=-1){
				fos.write(datas,0,len);
			}
			fis.close();//释放资源
			fis.close();//释放资源
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 复制文件到指定文件夹下
	 * @param srcPathStr 文件路径
	 * @param desPathStr 移动的文件夹名称(无需包含文件名)
	 */
	public static void copyfileAddFileName(String srcPathStr, String desPathStr){
		//获取源文件的名称
		String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("\\")+1); //目标文件地址
		System.out.println("源文件:"+srcPathStr);
		System.out.println("目标文件路径:"+desPathStr);
		try{
			FileInputStream fis = new FileInputStream(srcPathStr);//创建输入流对象
			FileOutputStream fos = new FileOutputStream(desPathStr); //创建输出流对象
			byte datas[] = new byte[1024*8];//创建搬运工具
			int len = 0;//创建长度
			//循环读取数据
			while((len = fis.read(datas))!=-1){
				fos.write(datas,0,len);
			}
			fis.close();//释放资源
			fis.close();//释放资源
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * zip文件解压
	 * @param sourceZip
	 * @throws Exception
	 */
	public static void unzip(String sourceZip) throws Exception{
		try{
			Project p = new Project();
			Expand e = new Expand();
			e.setProject(p);
			e.setSrc(new File(sourceZip));
			e.setOverwrite(false);
			String destDir=sourceZip.replace(".zip","");
			e.setDest(new File(destDir));
			e.setEncoding("gbk");
			e.execute();
		}catch(Exception e){
			throw e;
		}
	}

	/*
	 * 读取txt文件的内容
	 * @param  path 路径
	 * @return 返回文件内容
	 */
	public static String txtToString(String path){
		StringBuilder result = new StringBuilder();
		BufferedReader br = null;
		try{
			File file=new File(path);
			if (!file.exists()){
				return null;
			}
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf-8"));//构造一个BufferedReader类来读取文件
			String s = null;
			while((s = br.readLine())!=null){//使用readLine方法，一次读一行
				result.append(s);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}

	/**
	 * 读取word文件的内容
	 * @param file file
	 * @return 返回文件内容
	 */
	public static String readWordToString(File file) {
		String wordContent = "";
		if(file!=null && file.exists()){
			String path = file.getAbsolutePath();
			if(StringUtils.isNotBlank(path)){
				try {
					if (path.endsWith(".doc")) {
						InputStream is = new FileInputStream(file);
						WordExtractor ex = new WordExtractor(is);
						wordContent = ex.getText();
						ex.close();
					} else if (path.endsWith("docx")) {
						OPCPackage opcPackage = POIXMLDocument.openPackage(path);
						POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
						wordContent = extractor.getText();
						extractor.close();
					} else {
						System.out.println("此文件不是word文件！");
					}
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}
		return wordContent;
	}

	/**
	 * 将list数据写入文件
	 * @param fileInfo
	 * @param path
	 */
	public void writeObject(List<String> fileInfo,String path) {
		OutputStreamWriter osw =null;
		try {
			File file=new File(path);
			if(file.exists()) {
				file.delete();
			}
			//参数true:覆盖文件中内容，反之
			FileOutputStream fos = new FileOutputStream(path,true);
			//将信息写入文件之后出现乱码情况需要配置字体编码
			osw = new OutputStreamWriter(fos, "UTF-8");
			StringBuffer infoValue=new StringBuffer();
			if(fileInfo!=null&&!fileInfo.isEmpty()) {
				for(String info:fileInfo){
					infoValue.append(info+"\r\n");//  '\r\n' 是用换行使用
				}
			}
			osw.write(infoValue.toString());
			osw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				osw.close();
			} catch (IOException e) {
				logger.info("文件流关闭异常"+e.getMessage());
			}
		}
	}

	/**
	 * 字节转成 MB GB
	 * @param size
	 * @return
	 */
	public static String readableFileSize(long size) {
		if (size <= 0) return "0";
		final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}

	/**
	 * base64编码字符串转换为图片,并写入文件
	 *
	 * @param imgStr base64编码字符串
	 * @param path   图片路径
	 * @return
	 */
	public static boolean base64StrToImage(String imgStr, String path) {
		if (imgStr == null)
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// 解密
			byte[] b = decoder.decodeBuffer(imgStr);
			// 处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			//文件夹不存在则自动创建
			File tempFile = new File(path);
			if (!tempFile.getParentFile().exists()) {
				tempFile.getParentFile().mkdirs();
			}
			OutputStream out = new FileOutputStream(tempFile);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 图片转base64字符串
	 *
	 * @param imgFile 图片路径
	 * @return
	 */
	public static String imageToBase64Str(String imgFile) {
		InputStream inputStream = null;
		byte[] data = null;
		try {
			inputStream = new FileInputStream(imgFile);
			data = new byte[inputStream.available()];
			inputStream.read(data);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 加密
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}

	/**
	 * 压缩整个文件夹中的所有文件，生成指定名称的zip压缩包
	 * @param sourcePath 文件所在目录
	 * @param outPath 压缩后zip文件绝对路径（包含文件名）
	 * @param dirFlag zip文件中第一层是否包含一级目录，true包含；false没有
	 * 2015年6月9日
	 */
	public static void fileToZip(String sourcePath ,String outPath, boolean dirFlag) {
		try {
			File file = new File(sourcePath);// 要被压缩的文件夹
			File zipFile = new File(outPath);
			ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
			if(file.isDirectory()){
				File[] files = file.listFiles();
				for(File fileSec:files){
					if(dirFlag){
						recursionZip(zipOut, fileSec, file.getName() + File.separator);
					}else{
						recursionZip(zipOut, fileSec, "");
					}
				}
			}
			zipOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * MultipartFile 转 File
	 *
	 * @param file
	 * @throws Exception
	 */
	public static File multipartFileToFile(MultipartFile file, String toPath) throws Exception {

		File toFile = null;
		if (file.equals("") || file.getSize() <= 0) {
			file = null;
		} else {
			InputStream ins = null;
			ins = file.getInputStream();
			toFile = new File(toPath);
			inputStreamToFile(ins, toFile);
			ins.close();
		}
		return toFile;
	}

	//获取流文件
	private static void inputStreamToFile(InputStream ins, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void recursionZip(ZipOutputStream zipOut, File file, String baseDir) throws Exception{
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File fileSec:files){
				recursionZip(zipOut, fileSec, baseDir + file.getName() + File.separator);
			}
		}else{
			byte[] buf = new byte[1024];
			InputStream input = new FileInputStream(file);
			zipOut.putNextEntry(new ZipEntry(baseDir + file.getName()));
			int len;
			while((len = input.read(buf)) != -1){
				zipOut.write(buf, 0, len);
			}
			input.close();
		}
	}

	/**
	 * linux 移动tar至指定目录 文件
	 * @param sourcePath
	 * @param destPath
	 * @return
	 */
	public static boolean moveTarFile(String sourcePath,String destPath){
		boolean flag = false;
		try {
			Long startTime = System.currentTimeMillis();
			StringBuffer sb = new StringBuffer();
			sb.append("mv ");
			sb.append(sourcePath);
			sb.append(" ");
			sb.append(destPath);
			String cmdarray [] = {"/bin/sh","-c",sb.toString()};
			logger.info("移动tar至指定目录命令： "+sb.toString());
			Process ps = Runtime.getRuntime().exec(cmdarray);
			ps.waitFor(1, TimeUnit.HOURS);//移动文件设定1h为超时时长
			Long endTime = System.currentTimeMillis();
			logger.info("移动tar至指定目录成功，耗时："+(endTime - startTime)/1000 +"秒");
			flag = true;
		} catch (Exception e) {
			logger.error("移动tar至指定目录异常"+e.getMessage(),e);
		}
		return flag;
	}



}
