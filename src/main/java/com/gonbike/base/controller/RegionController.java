package com.gonbike.base.controller;

import com.gonbike.base.domain.RegionDO;
import com.gonbike.base.service.RegionService;
import com.gonbike.common.controller.BaseController;
import com.gonbike.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 省市区
 * Created by Shuaige on 2018/12/16.
 */
@Controller
@RequestMapping("/region")
@Api(value="省市区管理",tags={"省市区操作接口"})
public class RegionController extends BaseController {

    @Autowired
    RegionService regionService;

    @ResponseBody
    @GetMapping("/pub/list/{parentId}")
    @ApiOperation(value="获取省市区的下属列表",httpMethod="GET",notes ="parentId可以是省Id,市Id，0是中国")
    R getRegionListByParentId(@PathVariable String parentId){
        Map<String,Object> map=new HashMap<String,Object>();
        if(parentId==null){
            parentId="0";
        }
        List<RegionDO> list=regionService.getlistByParentId(parentId);
        map.put("list",list);
        return R.ok(map);
    }
}
