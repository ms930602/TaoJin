package com.ms.center.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.ms.taojin.common.utils.StringUtils;
import com.ms.taojin.permission.api.IPermissionSService;
import com.ms.taojin.permission.entity.MenuItem;
import com.ms.taojin.permission.entity.PermissonInfo;
import com.ms.taojin.permission.entity.TsysButtonEntity;
import com.ms.taojin.pre.api.ISessionHandler;
import com.ms.taojin.user.api.IUserRouteService;
import com.ms.taojin.user.vo.AuthCheckVo;
import com.ms.taojin.user.vo.LoginRespVo;

@SuppressWarnings({ "rawtypes", "unchecked" })
@ContextConfiguration(locations = { "/consumer-test.xml" })
public class ConsumerTest extends AbstractJUnit4SpringContextTests {

	// private static Logger logger = LoggerFactory.getLogger();

	@Autowired
	private IUserRouteService userRouteService;

	@Autowired
	private IPermissionSService menuItemService;

	@Autowired
	private ISessionHandler sessionHandler;

	@BeforeClass
	public static void setup() {
		System.setProperty("dubbo.application.name", "consumer-test-lansongtao");
		System.setProperty("dubbo.consumer.timeout", "60000");
		System.setProperty("dubbo.consumer.retries", "0");
		System.setProperty("dubbo.registry.address", "zookeeper://10.2.15.82:2181");
	}

	@Test
	public void update() throws Exception {
		Map reqMap = new Hashtable();

		reqMap.put("userId", "242");
		reqMap.put("status", "0");
		// reqMap.put("testStr", "啊啊啊啊");

		System.out.println(userRouteService.webProcess("user", "user", "update", reqMap));
	}

	@Test
	public void testMenu() {
		PermissonInfo permissonInfo = menuItemService.initMenusService("0", "2", "null");

		LoginRespVo respVo = new LoginRespVo();
		formatMenuItemList(respVo, permissonInfo);

		System.out.println(respVo);

	}

	/**
	 * 格式化权限菜单内容
	 * 
	 * @param menuItemList
	 * @return
	 */
	private void formatMenuItemList(LoginRespVo respVo, PermissonInfo permissonInfo) {
		respVo.setMenuItemLlist(permissonInfo.getListMenu());
		respVo.setButtonItemMap(new HashMap<String, List<TsysButtonEntity>>());
		respVo.setAuthCheckVo(new AuthCheckVo());
		respVo.getAuthCheckVo().setButtonCheck(permissonInfo.getSysEntity().getType());

		extractMenuItem(respVo.getMenuItemLlist(), respVo);
	}

	/**
	 * 递归提取菜单内容
	 * 
	 * @param menuItemList
	 * @param authMap
	 * @return
	 */
	private void extractMenuItem(List<MenuItem> menuItemList, LoginRespVo respVo) {
		AuthCheckVo authCheckVo = respVo.getAuthCheckVo();
		Map<String, List<TsysButtonEntity>> buttonItemMap = respVo.getButtonItemMap();

		for (Iterator<MenuItem> iterMenu = menuItemList.iterator(); iterMenu.hasNext();) {
			MenuItem menuItem = iterMenu.next();
			// comm模块不需要展示在菜单中
			if (0 == menuItem.getId() || StringUtils.isEmpty(menuItem.getCaption())) {
				iterMenu.remove();
			}
			// 添加菜单权限(暂时没用，前端调接口没有传菜单信息)
			if (authCheckVo.getMenus() == null) {
				authCheckVo.setMenus(new HashMap<String, String>());
			}
			authCheckVo.getMenus().put(menuItem.getCaption(), null);

			// 提取按钮信息
			List<TsysButtonEntity> butList = menuItem.getButList();
			if("400901".equals(menuItem.getId())){
				System.out.println();
			}
			if (butList != null && !butList.isEmpty()) {
				menuItem.setButList(null);
				// 该系统是否需要校验按钮权限
				if (!authCheckVo.isButtonCheck()) {
					continue;
				}
				if (!StringUtils.isEmpty(menuItem.getCaption())) {
					buttonItemMap.put(menuItem.getCaption(), butList);
				}
				// 提取按钮中的URI访问地址
				extractButton(butList, authCheckVo);
			}
			if (menuItem.getChildren() != null && !menuItem.getChildren().isEmpty()) {
				extractMenuItem(menuItem.getChildren(), respVo);
			}
		}
	}

	/**
	 * 提取菜单中的接口权限
	 * 
	 * @param menuItem
	 * @param authMap
	 */
	private void extractButton(List<TsysButtonEntity> butList, AuthCheckVo authCheckVo) {
		if (butList == null || butList.isEmpty()) {
			return;
		}
		for (Iterator<TsysButtonEntity> iter = butList.iterator(); iter.hasNext();) {
			TsysButtonEntity button = iter.next();
			// 页面不需要创建时间
			button.setCreatetime(null);

			if (authCheckVo.isButtonCheck() && !StringUtils.isEmpty(button.getApi())) {
				if (authCheckVo.getButtons() == null) {
					authCheckVo.setButtons(new HashMap<String, String>());
				}
				authCheckVo.getButtons().put(button.getApi(), null);
			}

		}

	}

	@Test
	public void testUser() {
		userRouteService.webProcess("user", "userDetail", "list", null);
	}

	@Test
	public void deleteSessionTest() {
		List<Long> idList = new ArrayList<Long>();
		idList.add(0l);
		sessionHandler.deleteSession(idList);
	}

}
