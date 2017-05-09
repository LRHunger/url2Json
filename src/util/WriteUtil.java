package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import var.Var;

public class WriteUtil {
	/*对txt进行读写操作*/
	
	/*从txt中读出数据，整理成url的list*/
	public static List<String> getUrls() {
		StringBuilder totalString = readAllData(Var.inTxt);
		//把读出来的整个字符串一局“http://”进行分割
		String[] urls = totalString.toString().split("http://");
		
		List<String> urlList = new ArrayList<String>();
		//split分割把第一个url之前的内容放进了urls[0]，因此我们从1开始
		for (int i = 1; i < urls.length; i++) {
			//重新加上“http://”
			urlList.add("http://" + urls[i]);
		}
		return urlList;
	}
	
	/*从txt读取内容，由于文字可能很多，不宜直接使用string存放，故用StringBuilder来存放*/
	public static StringBuilder readAllData(String filePath) {
		StringBuilder data = new StringBuilder(); // 创建String数组类型List数组
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line = null;
			while ((line = br.readLine()) != null) { // 读取全部内容并添加到List数组
				data.append(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) { // 关闭流
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	/*把string写入txt，并且不覆盖原有的内容*/
	public static void writeLineData(String data, String filePath) {
		File file = new File(filePath); // 创建指定目录的文件
		if (!file.getParentFile().exists()) { // 判断文件是否存在，不存在则新建
			file.getParentFile().mkdirs();
		}
		BufferedWriter bw = null;
		try {
			 // 创建txt并选择不覆盖原有的内容
			bw = new BufferedWriter(new FileWriter(file, true));
				bw.write(data);
			bw.write("\r\n"); // 写入该行最后一个字符串并换行
			bw.flush(); // 刷新缓冲
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) { // 关闭流
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
