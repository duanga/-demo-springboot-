package bingo.link.linkopenuamsyncdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bingo.link.uam.sync.demo.LinkOpenUamSyncDemoApplication;
import link.uam.sync.param.OrganizationSyncParam;
import link.uam.sync.param.SyncFailLog;
import link.uam.sync.param.UserSyncParam;
import link.uam.sync.service.OrganizationSyncService;
import link.uam.sync.service.UserSyncService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=LinkOpenUamSyncDemoApplication.class)
public class LinkOpenUamSyncDemoApplicationTests {
	@Autowired
	private UserSyncService userSyncService;
	@Autowired
	private OrganizationSyncService orgSyncService;
	
	private static String newOrgId = "";
	
	@Test
	public void testOrgSync() {
		String orgId = UUID.randomUUID().toString();
		newOrgId = orgId;
		System.out.println("同步部门ID：" + orgId);
		List<OrganizationSyncParam> params = new ArrayList<>();
		OrganizationSyncParam param = new OrganizationSyncParam();
		param.setLinkOrgId(orgId);
		param.setOrgId(orgId);
		param.setName("行政部");
		params.add(param);
		List<SyncFailLog> logs = orgSyncService.sync(params);
		if (null != logs && !logs.isEmpty()) {
			logs.forEach(log -> {
				System.out.println(log.getId() + ":" + log.getMessage());
			});
		}
	}

	@Test
	public void testUserSync() {
		String userId = UUID.randomUUID().toString();
		System.out.println("同步用户ID：" + userId);
		List<UserSyncParam> params = new ArrayList<>();
		UserSyncParam user = new UserSyncParam();
		user.setLinkUserId(userId);
		user.setUserId(userId);
		user.setName("张三");
		user.setPassword("abc123");
		user.setSex(1);
		user.setMobile("" + Math.round((Math.random() * 1000000000)));
		user.setOrgId(newOrgId);
		params.add(user);
		List<SyncFailLog> logs = userSyncService.sync(params);
		if (null != logs && !logs.isEmpty()) {
			logs.forEach(log -> {
				System.out.println(log.getId() + ":" + log.getMessage());
			});
		}
	}

}
