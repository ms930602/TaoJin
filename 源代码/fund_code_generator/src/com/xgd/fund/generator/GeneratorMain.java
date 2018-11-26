package com.xgd.fund.generator;

/**
 * 生成文件.
 * <p>
 * 
 * @author lansongtao
 * @Date 2015年8月15日
 * @since 1.0
 */
public class GeneratorMain {

	/**
	 * 请直接修改以下代码调用不同的方法以执行相关生成任务.
	 */
	public static void main(String[] args) throws Exception {

		GeneratorFacade g = new GeneratorFacade();
		// 打印数据库中的表名称
		// g.printAllTableNames();

		// 删除生成器的输出目录
		g.deleteOutRootDir();
		// 通过数据库表生成文件,template为模板的根目录
		// g.generateByTable(new String[] { "t_bill_type_conf", "t_bill_info" }, "resource/template/mybatis");
		// g.generateByTable(new String[] { "t_account_info", "t_account_list", "t_acoount_daily_balance" },
		// "resource/template/mybatis");
		// g.generateByTable(args, "resource/template/mybatis");
		g.generateByTable(new String[] {  "t_ms_opinion" }, "resource/template/mybatis");
		
//		g.generateByTable(new String[] { "t_sto_stockdetail" }, "resource/template/mybatis");

		// 自动搜索数据库中的所有表并生成文件,template为模板的根目录
		 //g.generateByAllTable("resource/template/mybatis");

		// 按表名删除生成的文件
		// g.deleteByTable("table_name", "resource/template/mybatis"); //删除生成的文件

	}
}
