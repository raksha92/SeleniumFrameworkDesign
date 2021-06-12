package com.opencart.tests;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.opencart.base.BaseTest;
import com.opencart.utils.Constants;
import com.opencart.utils.CustomError;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetUp() {
		accPage = lp.doLogin(prop.getProperty("userId"), prop.getProperty("pwd"));
	}
	
	@Test
	public void accPageHeaderTest() {
		String header = accPage.getAccPageHeader();
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER, CustomError.ACC_PAGE_HEADER_ERROR);
	}
	
	@Test
	public void accPageTitleTest() {
		String title = accPage.getAccPageTitle();
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void logOutExistTest() {
		boolean b = accPage.isLogoutExist();
		Assert.assertTrue(b);
	}
	
	@Test
	public void sectionListCountTest() {
		int size = accPage.getSectionsTextList().size();
		Assert.assertEquals(size, 4);
	}
	
	@Test
	public void sectionListTextTest() {
		List<String> actualSecList = accPage.getSectionsTextList();
		Collections.sort(Constants.ACC_SEC_LIST);
		Assert.assertEquals(actualSecList, Constants.ACC_SEC_LIST);
	}
}
