package com.ms.taojin.permission.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.taojin.common.service.BaseService;
import com.ms.taojin.common.vo.ListVo.ListRespVO;
import com.ms.taojin.permission.api.IPermissionSService;
import com.ms.taojin.permission.bo.TsysModuleBO;
import com.ms.taojin.permission.bo.TsysSysBO;
import com.ms.taojin.permission.entity.MenuItem;
import com.ms.taojin.permission.entity.PermissonInfo;
import com.ms.taojin.permission.entity.TsysButtonEntity;
import com.ms.taojin.permission.entity.TsysModuleEntity;
import com.ms.taojin.permission.entity.TsysRoleEntity;
import com.ms.taojin.permission.entity.TsysRoleUserEntity;
import com.ms.taojin.permission.entity.TsysSysEntity;

@Service("permissionSService")
public class PermissionSService extends BaseService implements IPermissionSService{

	@Resource
	public TsysRoleModlePermissionService tsysRoleModlePermissionService;
	@Resource
	public TsysRoleUserService iTsysRoleUserService;
	@Resource
	TsysModuleService tsysModuleService;
	@Resource
	TsysRoleUserService tsysRoleUserService;
	@Resource
	TsysRoleService tsysRoleService;
	@Autowired
	private TsysModuleBO tsysModuleBO;
	@Autowired
	TsysButtonService  tsysButtonService;
	@Autowired
	private TsysSysBO tsysSysBO;
	
	
	
	/**
	 * 根据用户信息,主目录id获取模块权限
	 */
	public PermissonInfo initMenusService(String userId,String sysId,String flag){
		
		TsysSysEntity sys=tsysSysBO.queryById(Long.parseLong(sysId));
		PermissonInfo info=new PermissonInfo();
		info.setSysEntity(sys);
		List<MenuItem> list=new ArrayList<MenuItem>();
		List<MenuItem> list1=new ArrayList<MenuItem>();
		ListRespVO vo=new ListRespVO();
		List<MenuItem> listAll=new ArrayList<MenuItem>();
		Map<String,MenuItem> mapMeu=new HashMap<String,MenuItem>();
		Set<String> singSet=new HashSet<String>();
		if(!(userId.equals("0"))){
		List<TsysRoleUserEntity> tsysRoleUserEntityList=iTsysRoleUserService.queryByUserId(userId);
		if(tsysRoleUserEntityList==null||tsysRoleUserEntityList.size()<1){
			return null;
		}
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
		if(!(flag.equals("1"))){
			List<MenuItem> listBak2=tsysRoleModlePermissionService.initMenus(null, null, null,tsysRoleUserEntity.getRoleId()+"","0");
			listAll.addAll(listBak2);

		}
		listAll.addAll(listBak);
		}
		for(MenuItem menuItem:listAll){
			if(mapMeu.get(menuItem.getCaption())==null){
				mapMeu.put(menuItem.getCaption(),menuItem);
			}else{
				MenuItem menuItemBak=mapMeu.get(menuItem.getCaption());
				if(menuItemBak.getButList().size()<menuItem.getButList().size()){
					mapMeu.put(menuItem.getCaption(),menuItem);
				}
			}
		}
		Iterator it=mapMeu.keySet().iterator();
		while(it.hasNext()){
			list.add(mapMeu.get(it.next()));
		}
		/*for(MenuItem menuItem:listAll){
			if(singSet.add(menuItem.getCaption())){
				list.add(menuItem);
			}
		}*/
		}
		//用户userID=0返回所有模块
		else{
			list=tsysRoleModlePermissionService.initTMenusByUserId(null,null,null,sysId);
			//list1=tsysRoleModlePermissionService.initTMenusByUserId(null,null,null,"0");

			for(MenuItem m:list1){
				list.add(m);
			}
		
			
		}
		if(list.size()<1){
			return null;
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
		if(!(flag.equals("1"))){
		list.add(comm);
		}
		}
		if((flag.equals("1"))){
			List<MenuItem> listres=new ArrayList<MenuItem>();
			for(MenuItem m:list){
				System.out.println(m.getSysId());
				if((m.getSysId()+"").equals(sysId))
					listres.add(m);
			}

			 Collections.sort(listres, new Comparator<MenuItem>() {  
		            @Override  
		            public int compare(MenuItem o1, MenuItem o2) {  
		                int i = o1.getSort()- o2.getSort();  
		                if(i == 0){  
		                    return o1.getId().intValue() - o2.getId().intValue();  
		                }  
		                return i;  
		            }  
		        }); 
			
			
			 info.setListMenu(listres);
			 return info;
		}else{
			 Collections.sort(list, new Comparator<MenuItem>() {  
		            @Override  
		            public int compare(MenuItem o1, MenuItem o2) {  
		                int i = o1.getSort()- o2.getSort();  
		                if(i == 0){  
		                    return o1.getId().intValue() - o2.getId().intValue();  
		                }  
		                return i;  
		            }  
		        });
			 info.setListMenu(list);
			return info;
		}
		
	}
	public List<TsysRoleUserEntity> getUsersByRoleId(String roleId){
		return tsysRoleUserService.queryByRoleId(roleId);
	}
	
	
	/**
	 * 向t_sys_role_user表中添加数据
	 */
	@Override
	public void createTsysRoleUser(Long userId, Long roleId) {
		TsysRoleUserEntity entity = new TsysRoleUserEntity();
		entity.setUserId(userId);
		entity.setRoleId(roleId);
		tsysRoleUserService.create(entity);
	}
	
	
	/**
	 * 测试方法
	 */
	
	/*public ListRespVO testPermission(@Param("userId") String userId,@Param("sysId") String sysId) {
		List<MenuItem> list=initMainMenusService(userId,sysId);
		ListRespVO v= new ListRespVO();
		v.setAaData(list);
		return v;
		
	}*/
}
