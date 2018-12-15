package com.gonbike.common.config;

import com.gonbike.common.utils.HelpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.List;

@Component
class WebConfigurer  implements WebMvcConfigurer {
	@Autowired
	GonbikeConfig gonbikeConfig;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("系统类型是："+ HelpUtil.getOsType()+"，上传路径："+gonbikeConfig.getUploadPath());
		//判断路径是否存在，不存在则创建
		try {
			File file = new File(gonbikeConfig.getUploadPath());
			if (!file.exists()){
				file.mkdirs();
			}
		}catch(Exception e){

		}
		registry.addResourceHandler("/files/**").addResourceLocations("file:///"+gonbikeConfig.getUploadPath());
	}

}