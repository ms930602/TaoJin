package com.ms.taojin.permission.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.taojin.common.service.BaseService;
import com.ms.taojin.common.vo.BaseRespVO;
import com.ms.taojin.common.vo.Param;
import com.ms.taojin.common.vo.ListVo.ListReqVO;
import com.ms.taojin.common.vo.ListVo.ListRespVO;
import com.ms.taojin.permission.bo.TsysButtonBO;
import com.ms.taojin.permission.bo.TsysModuleBO;
import com.ms.taojin.permission.bo.TsysRoleButtonPermissionBO;
import com.ms.taojin.permission.bo.TsysRoleModlePermissionBO;
import com.ms.taojin.permission.entity.MenuItem;
import com.ms.taojin.permission.entity.TreeNode;
import com.ms.taojin.permission.entity.TsysButtonEntity;
import com.ms.taojin.permission.entity.TsysModuleEntity;
import com.ms.taojin.permission.entity.TsysRoleButtonPermissionEntity;
import com.ms.taojin.permission.entity.TsysRoleModlePermissionEntity;
import com.ms.taojin.permission.entity.TsysRoleUserEntity;
import com.ms.taojin.pre.api.ISessionHandler;

/**
 * 用户详细信息接口实现
 *
 * @author Administrator
 * @Date 2017年3月27日
 * @since 1.0
 */
@Service("tsysRoleModlePermissionService")
public class TsysRoleModlePermissionService extends BaseService {

	@Autowired
	private TsysRoleModlePermissionBO tsysRoleModlePermissionBO;
	@Autowired
	private ISessionHandler sessionHandler;
	@Resource
	TsysRoleService tsysRoleService;
	@Autowired
	TsysRoleButtonPermissionBO tsysRoleButtonPermissionBO;
	@Resource
	public TsysModuleService tsysModuleService;
	@Resource
	TsysButtonService tsysButtonService;
	@Autowired
	private TsysButtonBO tsysButtonBO;
	@Autowired
	TsysModuleBO tsysModuleBO;
	@Resource
	TsysRoleButtonPermissionService tsysRoleButtonPermissionService;

	@Resource
	public TsysRoleUserService iTsysRoleUserService;

	/**
	 * 分页查询列表
	 * 
	 * @param reqVO
	 * @return
	 */
	public ListRespVO list(ListReqVO reqVO) {
		List<TsysRoleModlePermissionEntity> userDetaliList = tsysRoleModlePermissionBO.queryByPage(reqVO);
		int dataCount = tsysRoleModlePermissionBO.queryCount(reqVO);

		ListRespVO respVO = new ListRespVO();
		respVO.setAaData(userDetaliList);
		respVO.setDataCount(dataCount);

		return respVO;
	}

	/**
	 * 新增
	 * 
	 * @param reqVO
	 * @return
	 */
	public BaseRespVO create(TsysRoleModlePermissionEntity tsysRoleModlePermission) {
		tsysRoleModlePermissionBO.create(tsysRoleModlePermission);
		return new BaseRespVO();
	}

	/**
	 * 修改
	 * 
	 * @param reqVO
	 * @return
	 */
	public BaseRespVO update(TsysRoleModlePermissionEntity tsysRoleModlePermission) {
		tsysRoleModlePermissionBO.update(tsysRoleModlePermission);
		return new BaseRespVO();
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public BaseRespVO delete(TsysRoleModlePermissionEntity tsysRoleModlePermission) {
		tsysRoleModlePermissionBO.deleteById(tsysRoleModlePermission.getId());
		return new BaseRespVO();
	}

	/**
	 * 增加修改角色权限
	 * 
	 * @param moduIds
	 * @param roleId
	 * @param sysId
	 * @return
	 */
	public BaseRespVO updateModlePermission(@Param("moduIds") String[] moduIds, @Param("roleId") String[] roleId, @Param("sysId") String sysId) {
		if (roleId == null || sysId == null) {
			return BaseRespVO.error();
		}
		List<Long> userIdList = new ArrayList<Long>();
		for (int k = 0; k < roleId.length; k++) {
			
			
			List<TsysRoleUserEntity> userRoleList=iTsysRoleUserService.queryByRoleId(roleId[k]);
			for(TsysRoleUserEntity e1:userRoleList){
				userIdList.add(e1.getUserId());
			}
			
			deleteByRoleIdAndSysId(roleId[k], sysId);
			tsysRoleButtonPermissionBO.deleteByRoleIdAndSysId(roleId[k], sysId);
			List<TsysRoleModlePermissionEntity> listModle = new ArrayList<TsysRoleModlePermissionEntity>();
			List<TsysRoleButtonPermissionEntity> listBut = new ArrayList<TsysRoleButtonPermissionEntity>();
			Set<String> set = new HashSet<String>();
			for (String modId : moduIds) {
				if (modId.contains("_b")) {
					TsysRoleButtonPermissionEntity but = new TsysRoleButtonPermissionEntity();
					modId = modId.replaceAll("_b", "");
					but.setButtonId(Long.valueOf(modId));
					but.setRoleId(Long.valueOf(roleId[k]));
					but.setSysId(Long.valueOf(sysId));
					listBut.add(but);
					TsysButtonEntity butE = tsysButtonBO.queryById(Long.valueOf(modId));
					TsysModuleEntity modelE = tsysModuleBO.queryById(butE.getModuleId());
					if (modelE != null && set.add(modelE.getId() + "")) {
						TsysRoleModlePermissionEntity entity = new TsysRoleModlePermissionEntity();
						entity.setModuleId(Long.valueOf(modelE.getId()));
						entity.setRoleId(Long.valueOf(roleId[k]));
						entity.setSysId(Long.valueOf(sysId));
						listModle.add(entity);
						List<String> listId = getCheckedModelIds(modelE, null);
						for (int i = 0; i < listId.size(); i++) {
							if (set.add(listId.get(i))) {
								TsysRoleModlePermissionEntity entity1 = new TsysRoleModlePermissionEntity();
								entity1.setModuleId(Long.valueOf(listId.get(i)));
								entity1.setRoleId(Long.valueOf(roleId[k]));
								entity1.setSysId(Long.valueOf(sysId));
								listModle.add(entity1);
							}
						}
					}

				} else {
					modId = modId.replaceAll("_m", "");
					TsysModuleEntity modelE = tsysModuleBO.queryById(Long.valueOf(modId));
					if (modelE != null && set.add(modelE.getId() + "")) {
						TsysRoleModlePermissionEntity entity = new TsysRoleModlePermissionEntity();
						entity.setModuleId(Long.valueOf(modId));
						entity.setRoleId(Long.valueOf(roleId[k]));
						entity.setSysId(Long.valueOf(sysId));
						listModle.add(entity);
						List<String> listId = getCheckedModelIds(modelE, null);
						for (int i = 0; i < listId.size(); i++) {
							if (set.add(listId.get(i))) {
								TsysRoleModlePermissionEntity entity1 = new TsysRoleModlePermissionEntity();
								entity1.setModuleId(Long.valueOf(listId.get(i)));
								entity1.setRoleId(Long.valueOf(roleId[k]));
								entity1.setSysId(Long.valueOf(sysId));
								listModle.add(entity1);
							}
						}
					}
				}
			}
			if (listModle.size() > 0) {
				insertList(listModle);
			}
			if (listBut.size() > 0) {
				tsysRoleButtonPermissionBO.insertList(listBut);
			}
		}
		if(userIdList.size()>0){
		sessionHandler.deleteSession(userIdList);
		}
		return new BaseRespVO();
	}

	public List<String> getCheckedModelIds(TsysModuleEntity modelE, List list) {
		if (list == null) {
			list = new ArrayList<String>();
		}
		if (modelE.getParentid() != null) {
			TsysModuleEntity modelE1 = tsysModuleBO.queryById(modelE.getParentid());
			getCheckedModelIds(modelE1, list);
			list.add(modelE1.getId() + "");
		} else {
			return list;
		}
		return list;
	}

	/**
	 * 查看角色权限
	 * 
	 * @param roleId
	 * @param sysId
	 * @return
	 */

	public List<TsysRoleModlePermissionEntity> queryByRoleIdAndsysId(String roleId, String sysId) {
		// TODO Auto-generated method stub
		return tsysRoleModlePermissionBO.queryByRoleIdAndsysId(roleId, sysId);
	}

	/**
	 * 修改或添加权限
	 * 
	 * @param list
	 */
	public void insertList(List<TsysRoleModlePermissionEntity> list) {
		tsysRoleModlePermissionBO.insertList(list);

	}

	/**
	 * 删除角色权限
	 * 
	 * @param roleId
	 * @param sysId
	 */
	public void deleteByRoleIdAndSysId(String roleId, String sysId) {
		tsysRoleModlePermissionBO.deleteByRoleIdAndSysId(roleId, sysId);

	}

	/**
	 * 根据用户信息获取模块权限
	 */
	public ListRespVO initMainMenusService(@Param("userId") String userId, @Param("sysId") String sysId) {
		MenuItem equMenu = null;
		List<MenuItem> listM = new ArrayList<MenuItem>();
		List<TsysModuleEntity> mainMenuItems = tsysModuleService.queryMainMenuItem(sysId);
		List<TsysRoleUserEntity> tsysRoleUserEntityList = iTsysRoleUserService.queryByUserId(userId);
		Set<String> singSet = new HashSet<String>();
		for (TsysRoleUserEntity tsysRoleUserEntity : tsysRoleUserEntityList) {
			List<TsysRoleModlePermissionEntity> listT = queryByRoleIdAndsysId(tsysRoleUserEntity.getRoleId() + "", sysId);
			for (TsysRoleModlePermissionEntity t : listT) {
				if (singSet.add(t.getModuleId() + "")) {
					for (TsysModuleEntity m : mainMenuItems) {
						if ((t.getModuleId() + "").equals(m.getId() + "")) {
							int sort=0;
							if(m.getSort()!=null){
								sort=m.getSort().intValue();
							}
							equMenu = new MenuItem(m.getName(), m.getCaption(), m.getIconCls(), m.getIndexT(),m.getSysId(),sort);
							equMenu.setId(m.getId());
						}
					}
				}
				if (equMenu != null) {
					listM.add(equMenu);
				}
				equMenu = null;
			}

		}
		ListRespVO vo = new ListRespVO();
		vo.setAaData(listM);
		return vo;

	}

	/**
	 * 根据用户信息,主目录id获取模块权限要删除
	 */

/*	public ListRespVO initMenusService(@Param("userId") String userId ,@Param("sysId") String sysId) {
		List<MenuItem> list=new ArrayList<MenuItem>();
		List<MenuItem> list1=new ArrayList<MenuItem>();
		ListRespVO vo=new ListRespVO();
		List<MenuItem> listAll=new ArrayList<MenuItem>();
		Set<String> singSet=new HashSet<String>();
		if(!(userId.equals("0"))){
		List<TsysRoleUserEntity> tsysRoleUserEntityList=iTsysRoleUserService.queryByUserId(userId);
		for(TsysRoleUserEntity tsysRoleUserEntity:tsysRoleUserEntityList){
			TsysRoleEntity t=tsysRoleService.queryById(tsysRoleUserEntity.getRoleId());
			if((t.getStatu()+"").equals("0")){
				continue;
			}
			if(!((t.getSysId()+"").equals("0"))){
				if(!((t.getSysId()+"").equals(sysId))){
					continue;
				}
			}
		List<MenuItem> listBak=tsysRoleModlePermissionService.initMenus(null, null, null,tsysRoleUserEntity.getRoleId()+"",sysId);
		List<MenuItem> listBak2=tsysRoleModlePermissionService.initMenus(null, null, null,tsysRoleUserEntity.getRoleId()+"","0");
		listAll.addAll(listBak);
		listAll.addAll(listBak2);
		}
		for(MenuItem menuItem:listAll){
			if(singSet.add(menuItem.getCaption())){
				list.add(menuItem);
			}
		}
		}
		//用户userID=0返回所有模块
		else{
			list=tsysRoleModlePermissionService.initTMenusByUserId(null,null,null,sysId);
			list1=tsysRoleModlePermissionService.initTMenusByUserId(null,null,null,"0");
			for(MenuItem m:list1){
				list.add(m);
			}
		}
		
		MenuItem comm=new MenuItem();
		TsysModuleEntity t=tsysModuleBO.queryById(0);
		if(t!=null){
		comm.setId(t.getId());
		comm.setIconCls(t.getIconCls());
		comm.setName(t.getName());
		comm.setIndex(t.getIndexT());
		comm.setCaption(t.getCaption());
		List<TsysButtonEntity> buttlist=tsysButtonService.queryBymoduleId(t.getId()+"");
		for(TsysButtonEntity b:buttlist){
			comm.getButList().add(b);
		}
		list.add(comm);
		}
		
		vo.setAaData(list);
		return vo;
	}*/
	
	public List<MenuItem> initTMenusByUserId(TsysModuleEntity parent, List<TsysModuleEntity> models, List<TsysModuleEntity> rolePermissions,String sysId) {
		List<MenuItem> menus = new ArrayList<MenuItem>();
		if (models == null) {
			int i=0;
			models = tsysModuleService.queryNot(sysId);
		
			rolePermissions = tsysModuleService.query(sysId);
		}

		if (rolePermissions == null || rolePermissions.size() < 1 || models == null || models.size() < 1) {
			return menus;
		}
		for (int index = 0; index < models.size(); index++) {
			TsysModuleEntity model = models.get(index);
			MenuItem equMenu = null;
			if (parent == null) {
				if (model.getParentid() == null || model.getParentid() < 1) {
					int sort=0;
					if(model.getSort()!=null){
						sort=model.getSort().intValue();
					}
					equMenu = new MenuItem(model.getName(), model.getCaption(), model.getIconCls(), model.getIndexT(),model.getSysId(),sort);;
				}
			} else {
				if (parent.getId().equals(model.getParentid())) {
					int sort=0;
					if(model.getSort()!=null){
						sort=model.getSort().intValue();
					}
					equMenu = new MenuItem(model.getName(), model.getCaption(), model.getIconCls(), model.getIndexT(),model.getSysId(),sort);
				}
			}
			if (equMenu != null) {
				List<MenuItem> children = initTMenusByUserId(model, models, rolePermissions,sysId);
				if (children == null || children.size() < 1) {
					for (int i = 0; i < rolePermissions.size(); i++) {
						TsysModuleEntity rolePermission = rolePermissions.get(i);
						if (model.getId().equals(rolePermission.getId())) {

							List<TsysButtonEntity> listBu = tsysButtonService.queryBymoduleId(model.getId() + "");
							for (TsysButtonEntity t : listBu) {
								
									equMenu.getButList().add(t);
								
							}

							equMenu.setId(model.getId());
							equMenu.setSysId(model.getSysId());
							menus.add(equMenu);
							break;
						}
					}
				} else {
					List<TsysButtonEntity> listBu = tsysButtonService.queryBymoduleId(model.getId() + "");
					for (TsysButtonEntity t : listBu) {
									equMenu.getButList().add(t);
						}
					equMenu.setHasChildren(1);
					equMenu.setChildren(children);
					equMenu.setId(model.getId());
					if (model.getParentid() == null) {
						equMenu.setDefaultUrl(getDefaultUrl(children, null));
					}
					// equMenu.setParentId(model.getParentid()+"");
					equMenu.setSysId(model.getSysId());
					menus.add(equMenu);
				}
			}
		}
		System.out.println(menus.size() + "======================");
		return menus;

	}

	/**
	 * 返回用户角色模块权限
	 * 
	 * @param parent
	 * @param models
	 * @param rolePermissions
	 * @param roleId
	 * @param sysId
	 * @return
	 */

	List<MenuItem> initMenus(TsysModuleEntity parent, List<TsysModuleEntity> models, List<TsysRoleModlePermissionEntity> rolePermissions, String roleId, String sysId) {
		List<MenuItem> menus = new ArrayList<MenuItem>();
		boolean flag = true;
		if (models == null) {
			models = tsysModuleService.query(sysId);
			if (roleId != null)
				rolePermissions = queryByRoleIdAndsysId(roleId, sysId);
		}
		if (rolePermissions == null || rolePermissions.size() < 1 || models == null || models.size() < 1) {
			return menus;
		}
		for (int index = 0; index < models.size(); index++) {
			TsysModuleEntity model = models.get(index);
			MenuItem equMenu = null;
			if (parent == null) {
				if (model.getParentid() == null || model.getParentid() < 1) {
					int sort=0;
					if(model.getSort()!=null){
						sort=model.getSort().intValue();
					}
					equMenu = new MenuItem(model.getName(), model.getCaption(), model.getIconCls(), model.getIndexT(),model.getSysId(),sort);
				}
			} else {
				if (parent.getId().equals(model.getParentid())) {
					int sort=0;
					if(model.getSort()!=null){
						sort=model.getSort().intValue();
					}
					equMenu = new MenuItem(model.getName(), model.getCaption(), model.getIconCls(), model.getIndexT(),model.getSysId(),sort);
				}
			}
			if (equMenu != null) {
				List<MenuItem> children = initMenus(model, models, rolePermissions, roleId, sysId);
				if (children == null || children.size() < 1) {
					for (int i = 0; i < rolePermissions.size(); i++) {
						TsysRoleModlePermissionEntity rolePermission = rolePermissions.get(i);
						if (model.getId().equals(rolePermission.getModuleId())) {
							List<TsysRoleButtonPermissionEntity> listButs = tsysRoleButtonPermissionService.queryByRoleId(roleId, sysId);
							List<TsysButtonEntity> listBu = tsysButtonService.queryBymoduleId(model.getId() + "");
							for (TsysButtonEntity t : listBu) {
								if((t.getLag() + "").equals("1")){
								for (TsysRoleButtonPermissionEntity e : listButs) {
									
										if ((e.getButtonId() + "").equals(t.getId() + ""))
											equMenu.getButList().add(t);
									}
									
								}
								else  {
									equMenu.getButList().add(t);
								}
							}
							equMenu.setId(model.getId());
							int sort=0;
							if(model.getSort()!=null){
								sort=model.getSort().intValue();
							}
							equMenu.setSort(sort);
							menus.add(equMenu);
							break;
						}
					}
				} else {
					List<TsysRoleButtonPermissionEntity> listButs = tsysRoleButtonPermissionService.queryByRoleId(roleId, sysId);
					List<TsysButtonEntity> listBu = tsysButtonService.queryBymoduleId(model.getId() + "");
						for (TsysButtonEntity t : listBu) {
							if((t.getLag() + "").equals("1")){
							for (TsysRoleButtonPermissionEntity e : listButs) {
								
									if ((e.getButtonId() + "").equals(t.getId() + ""))
										equMenu.getButList().add(t);
								}
								
							}
							else  {
								equMenu.getButList().add(t);
							}
						}
					equMenu.setHasChildren(1);
					equMenu.setChildren(children);
					equMenu.setId(model.getId());
					int sort=0;
					if(model.getSort()!=null){
						sort=model.getSort().intValue();
					}
					equMenu.setSort(sort);
					if (model.getParentid() == null) {
						equMenu.setDefaultUrl(getDefaultUrl(children, null));
					}
					// equMenu.setParentId(model.getParentid()+"");
					menus.add(equMenu);

				}
			}
		}
		return menus;
	}

	public String getDefaultUrl(List<MenuItem> children, List<String> listUrl) {
		if (listUrl == null) {
			listUrl = new ArrayList<String>();
		}
		for (MenuItem m : children) {
			if (m.getChildren() != null && m.getChildren().size() > 0) {
				getDefaultUrl(m.getChildren(), listUrl);
			} else {
				listUrl.add(m.getCaption());
			}
		}
		if (listUrl.size() > 0)
			return listUrl.get(0);
		else
			return null;
	}

	public ListRespVO initMenusAllTree(@Param("sysId") String sysId) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		List<TreeNode> list1 = new ArrayList<TreeNode>();
		list = initTMenus(null, null, null,sysId);
		/*list1 = initTMenus(null, null, null,"0");
		for(TreeNode t:list1){
			list.add(t);
		}*/
	
		ListRespVO respVO = new ListRespVO();
		respVO.setAaData(list);
		return respVO;
	}

	public List<TreeNode> initTMenus(TsysModuleEntity parent, List<TsysModuleEntity> models, List<TsysModuleEntity> rolePermissions,String sysId) {
		List<TreeNode> menus = new ArrayList<TreeNode>();
		if (models == null) {
			int i=0;
			models = tsysModuleService.queryNot(sysId);
		
			rolePermissions = tsysModuleService.query(sysId);
		}

		if (rolePermissions == null || rolePermissions.size() < 1 || models == null || models.size() < 1) {
			return menus;
		}
		for (int index = 0; index < models.size(); index++) {
			TsysModuleEntity model = models.get(index);
			TreeNode equMenu = null;
			if (parent == null) {
				if (model.getParentid() == null || model.getParentid() < 1) {
					equMenu = new TreeNode(model.getId() + "_m", model.getName());
				}
			} else {
				if (parent.getId().equals(model.getParentid())) {
					equMenu = new TreeNode(model.getId() + "_m", model.getName());
				}
			}
			if (equMenu != null) {
				List<TreeNode> children = initTMenus(model, models, rolePermissions,sysId);
				if (children == null || children.size() < 1) {
					for (int i = 0; i < rolePermissions.size(); i++) {
						TsysModuleEntity rolePermission = rolePermissions.get(i);
						if (model.getId().equals(rolePermission.getId())) {

							List<TsysButtonEntity> listBu = tsysButtonService.queryBymoduleId(model.getId() + "");
							for (TsysButtonEntity t : listBu) {
								if ((t.getLag() + "").equals("1")) {
									equMenu.getChildren().add(new TreeNode(t.getId() + "_b", t.getZhName()));

								}
							}

							equMenu.setId(model.getId() + "_m");
							menus.add(equMenu);
							break;
						}
					}
				} else {
					List<TsysButtonEntity> listBu = tsysButtonService.queryBymoduleId(model.getId() + "");
					for (TsysButtonEntity t : listBu) {
						if ((t.getLag() + "").equals("1")) {
							equMenu.getChildren().add(new TreeNode(t.getId() + "_b", t.getZhName()));

						}
					}
					equMenu.setId(model.getId() + "_m");
					menus.add(equMenu);
					// equMenu.setHasChildren(1);

					equMenu.setChildren(children);
				}
			}
		}
		System.out.println(menus.size() + "======================");
		return menus;

	}

	/**
	 * 根据角色拿到相关模块树权限
	 * 
	 * @param roleId
	 * @param sysId
	 * @return
	 */
	public ListRespVO initMenusTreeService(@Param("roleId") String roleId, @Param("sysId") String sysId) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		List<TreeNode> listAll = new ArrayList<TreeNode>();
		Set<String> singSet = new HashSet<String>();
		List selectId = new ArrayList();

		Set<String> setId = new HashSet<String>();
		List<TreeNode> listBak = initTreeMenus(null, null, null, roleId, sysId, selectId, setId, null);
		listAll.addAll(listBak);
		for (TreeNode menuItem : listAll) {
			if (singSet.add(menuItem.getLabel())) {
				list.add(menuItem);
			}
		}

		ListRespVO respVO = new ListRespVO();
		respVO.setAaData(list);
		return respVO;
	}

	List<TreeNode> initTreeMenus(TsysModuleEntity parent, List<TsysModuleEntity> models, List<TsysRoleModlePermissionEntity> rolePermissions, String roleId, String sysId, List selectId, Set<String> setId,
			List<TsysRoleButtonPermissionEntity> listButs) {
		List<TreeNode> menus = new ArrayList<TreeNode>();
		if (models == null) {
			models = tsysModuleService.query(sysId);
			rolePermissions = queryByRoleIdAndsysId(roleId, sysId);
			listButs = tsysRoleButtonPermissionService.queryByRoleId(roleId, sysId);

		}
		if (rolePermissions == null || rolePermissions.size() < 1 || models == null || models.size() < 1) {
			return menus;
		}
		for (int index = 0; index < models.size(); index++) {
			TsysModuleEntity model = models.get(index);
			TreeNode equMenu = null;
			if (parent == null) {
				if (model.getParentid() == null || model.getParentid() < 1) {
					equMenu = new TreeNode(model.getId() + "_m", model.getName());
				}
			} else {
				if (parent.getId().equals(model.getParentid())) {
					equMenu = new TreeNode(model.getId() + "_m", model.getName());
				}
			}
			if (equMenu != null) {
				List<TreeNode> children = initTreeMenus(model, models, rolePermissions, roleId, sysId, selectId, setId, listButs);
				if (children == null || children.size() < 1) {
					for (int i = 0; i < rolePermissions.size(); i++) {
						TsysRoleModlePermissionEntity rolePermission = rolePermissions.get(i);
						if (model.getId().equals(rolePermission.getModuleId())) {
							List<TsysButtonEntity> listBu = tsysButtonService.queryBymoduleId(model.getId() + "");

							for (TsysButtonEntity t : listBu) {
								if ((t.getLag() + "").equals("1")) {
									equMenu.getChildren().add(new TreeNode(t.getId() + "_b", t.getZhName()));
									for (TsysRoleButtonPermissionEntity e : listButs) {
										if ((e.getButtonId() + "").equals(t.getId() + "")) {

											if (setId.add(t.getId() + "_b"))
												selectId.add(t.getId() + "_b");

										}
									}
								}
							}
							for (TsysRoleModlePermissionEntity t : rolePermissions) {
								if (t.getModuleId().equals(model.getId())) {
									equMenu.setSelected(true);
									if (setId.add(model.getId() + "")) {
										if (equMenu.getChildren().size() <= 0)
											selectId.add(model.getId() + "_m");
									}
								}
							}
							equMenu.setId(model.getId() + "_m");
							menus.add(equMenu);
							break;
						} else {

							List<TsysButtonEntity> listBu = tsysButtonService.queryBymoduleId(model.getId() + "");
							for (TsysButtonEntity t : listBu) {
								if ((t.getLag() + "").equals("1")) {
									equMenu.getChildren().add(new TreeNode(t.getId() + "_b", t.getZhName()));
									for (TsysRoleButtonPermissionEntity e : listButs) {
										if ((e.getButtonId() + "").equals(t.getId() + "")) {

											if (setId.add(t.getId() + "_b"))
												selectId.add(t.getId() + "_b");

										}
									}
								}
							}
							for (TsysRoleModlePermissionEntity t : rolePermissions) {
								if (t.getModuleId().equals(model.getId())) {
									equMenu.setSelected(true);
									if (setId.add(model.getId() + "")) {
										if (equMenu.getChildren().size() <= 0)
											selectId.add(model.getId() + "_m");
									}
								}
							}
							equMenu.setId(model.getId() + "_m");
							menus.add(equMenu);
							break;
						}
					}
				} else {
					List<TsysButtonEntity> listBu = tsysButtonService.queryBymoduleId(model.getId() + "");

					for (TsysButtonEntity t : listBu) {
						if ((t.getLag() + "").equals("1")) {
							equMenu.getChildren().add(new TreeNode(t.getId() + "_b", t.getZhName()));
							for (TsysRoleButtonPermissionEntity e : listButs) {
								if ((e.getButtonId() + "").equals(t.getId() + "")) {

									if (setId.add(t.getId() + "_b"))
										selectId.add(t.getId() + "_b");

								}
							}
						}
					}
					for (TsysRoleModlePermissionEntity t : rolePermissions) {
						if (t.getModuleId().equals(model.getId()))
							equMenu.setSelected(true);
					}
					equMenu.setId(model.getId() + "_m");
					menus.add(equMenu);
					// equMenu.setHasChildren(1);

					equMenu.setChildren(children);
				}
			}
		}
		System.out.println(menus.size() + "======================");
		for (TreeNode r : menus) {
			r.setSelectId(selectId);
		}
		return menus;
	}

}
