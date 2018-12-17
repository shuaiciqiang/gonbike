package com.gonbike.system.service.impl;

import com.gonbike.common.utils.HelpUtil;
import com.gonbike.system.domain.ItemLabelDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.gonbike.system.dao.ItemsDao;
import com.gonbike.system.domain.ItemsDO;
import com.gonbike.system.service.ItemsService;



@Service
public class ItemsServiceImpl implements ItemsService {
	@Autowired
	private ItemsDao itemsDao;
	
	@Override
	public ItemsDO get(Long id){
		return itemsDao.get(id);
	}
	
	@Override
	public List<ItemsDO> list(Map<String, Object> map){
		return itemsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return itemsDao.count(map);
	}
	
	@Override
	public int save(ItemsDO items){
		//System.out.println(items.getItemLabels());

		int vT= itemsDao.save(items);
		if (items.getId()!=null&&items.getItemLabels()!=null){
			String[] labelIds=items.getItemLabels().split(",");
			for(String vLabelId:labelIds){
				if (vLabelId!=null&&!vLabelId.trim().equals("")){
					ItemLabelDO vItemLabel=new ItemLabelDO();
					vItemLabel.setLabelId(Integer.valueOf(vLabelId));
					vItemLabel.setItemId(items.getId());
					vItemLabel.setCreateDate(HelpUtil.getDateTime());
					vItemLabel.setCreateUser(items.getCreateUser());
					vItemLabel.setCreateUserId(items.getCreateUserId());
					itemsDao.insertItemLabel(vItemLabel);
				}
			}
			itemsDao.updateItemLabelByItemId(Long.valueOf(items.getId()));
		}

		return vT;
	}
	
	@Override
	public int update(ItemsDO items){
		//System.out.println(items.getItemLabels());
		if (items.getId()!=null) {
			itemsDao.deleteItemLabelByItemId(Long.valueOf(items.getId()));
		}
		if (items.getId()!=null&&items.getItemLabels()!=null){
			String[] labelIds=items.getItemLabels().split(",");
			for(String vLabelId:labelIds){
				if (vLabelId!=null&&!vLabelId.trim().equals("")){
					ItemLabelDO vItemLabel=new ItemLabelDO();
					vItemLabel.setLabelId(Integer.valueOf(vLabelId));
					vItemLabel.setItemId(items.getId());
					vItemLabel.setCreateDate(HelpUtil.getDateTime());
					vItemLabel.setCreateUser(items.getModifyUser());
					vItemLabel.setCreateUserId(items.getModifyUserId());
					itemsDao.insertItemLabel(vItemLabel);
				}
			}
			itemsDao.updateItemLabelByItemId(Long.valueOf(items.getId()));
		}
		return itemsDao.update(items);
	}
	
	@Override
	public int remove(Long id){
		itemsDao.deleteItemLabelByItemId(id);
		return itemsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		for(Long id:ids){
			itemsDao.deleteItemLabelByItemId(id);
		}
		return itemsDao.batchRemove(ids);
	}
	
}
