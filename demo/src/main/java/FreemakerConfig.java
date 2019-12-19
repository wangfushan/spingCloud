/**
 * Project Name:pe-admin
 * File Name:FreemakerConfig.java
 * Package Name:com.nio.common.config
 * Date:2017年6月25日下午8:06:42
 * Copyright (c) 2017, China Link Communications LTD All Rights Reserved.
 *
 */

import freemarker.template.Configuration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jagregory.shiro.freemarker.ShiroTags;
/**
 * ClassName: FreemakerConfig <br/>
 * Date: 2017年6月25日 下午8:06:42 <br/>
 * Description: TODO
 *
 * @author dongshun.wang.o
 * @version
 * @see
 */
@Component
public class FreemakerConfig implements InitializingBean {
	@Autowired
	private Configuration configuration;

	@Override
	public void afterPropertiesSet() throws Exception {
		// 加上这句后，可以在页面上使用shiro标签
		configuration.setSharedVariable("shiro", new ShiroTags());
	}
}
