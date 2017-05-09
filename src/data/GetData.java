package data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.KeyBean;

public class GetData {
	
	/*从数据库中读取列表，列表的元素用于读取网站时候进行判断，获取需要的信息*/
	public static List<String> getKeyList(String tableName) {//tableName是需要读的数据库名
		QueryRunner runner = new QueryRunner();
		Connection conn = null;
		String sql = "Select * From " + tableName;
		BeanListHandler<KeyBean> handler = new BeanListHandler<KeyBean>(
				KeyBean.class);
		List<KeyBean> keyList = null;//每个KeyBean只有一个字符串属性，我们需要吧这个属性封装成字符串list
		try {
			conn = JdbcUtil.getConn();
			keyList = runner.query(conn, sql, handler);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		List<String> outlList = new ArrayList<String>();
		for (KeyBean key : keyList) {
			outlList.add(key.getKeyWord());
		}
		return outlList;
	}
}
