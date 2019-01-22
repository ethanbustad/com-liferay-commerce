/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.media.dl.internal;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.configuration.CommerceMediaDefaultImageConfiguration;
import com.liferay.commerce.constants.CommerceMediaConstants;
import com.liferay.commerce.media.CommerceMediaResolver;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.CPAttachmentFileEntryLocalService;
import com.liferay.commerce.product.service.CPAttachmentFileEntryService;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPRuleLocalService;
import com.liferay.commerce.product.service.CProductLocalService;
import com.liferay.commerce.product.util.CPRulesThreadLocal;
import com.liferay.commerce.user.segment.util.CommerceUserSegmentHelper;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.util.File;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Html;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(service = CommerceMediaResolver.class)
public class DLCommerceMediaResolver implements CommerceMediaResolver {

	@Override
	public String getDownloadUrl(long cpAttachmentFileEntryId)
		throws PortalException {

		return getUrl(cpAttachmentFileEntryId, true, false);
	}

	@Override
	public byte[] getMediaBytes(FileEntry fileEntry)
		throws IOException, PortalException {

		return _file.getBytes(fileEntry.getContentStream());
	}

	@Override
	public String getThumbnailUrl(long cpAttachmentFileEntryId)
		throws PortalException {

		return getUrl(cpAttachmentFileEntryId, false, true);
	}

	@Override
	public String getUrl(long cpAttachmentFileEntryId) throws PortalException {
		return getUrl(cpAttachmentFileEntryId, false, false);
	}

	@Override
	public String getUrl(
			long cpAttachmentFileEntryId, boolean download, boolean thumbnail)
		throws PortalException {

		return getUrl(cpAttachmentFileEntryId, download, thumbnail, true);
	}

	@Override
	public String getUrl(
			long cpAttachmentFileEntryId, boolean download, boolean thumbnail,
			boolean secure)
		throws PortalException {

		StringBundler sb = new StringBundler(13);

		sb.append(_portal.getPathModule());
		sb.append(StringPool.SLASH);
		sb.append(CommerceMediaConstants.SERVLET_PATH);

		CPAttachmentFileEntry cpAttachmentFileEntry = null;

		if (secure) {
			cpAttachmentFileEntry =
				_cpAttachmentFileEntryService.fetchCPAttachmentFileEntry(
					cpAttachmentFileEntryId);
		}
		else {
			cpAttachmentFileEntry =
				_cpAttachmentFileEntryLocalService.fetchCPAttachmentFileEntry(
					cpAttachmentFileEntryId);
		}

		if (cpAttachmentFileEntry == null) {
			sb.append("/default/?groupId=");

			HttpSession httpSession = PortalSessionThreadLocal.getHttpSession();

			sb.append(
				GetterUtil.getLong(
					httpSession.getAttribute(WebKeys.VISITED_GROUP_ID_RECENT)));

			return _html.escape(sb.toString());
		}

		Locale siteDefaultLocale = _portal.getSiteDefaultLocale(
			cpAttachmentFileEntry.getGroupId());

		String className = cpAttachmentFileEntry.getClassName();

		if (className.equals(CPDefinition.class.getName())) {
			sb.append("/products/");

			CPDefinition cpDefinition =
				_cpDefinitionLocalService.getCPDefinition(
					cpAttachmentFileEntry.getClassPK());

			sb.append(cpDefinition.getCProductId());

			sb.append(StringPool.SLASH);

			Map<Locale, String> titleMap = cpDefinition.getUrlTitleMap();

			sb.append(titleMap.get(siteDefaultLocale));
		}
		else {
			throw new UnsupportedOperationException();
		}

		sb.append(StringPool.SLASH);
		sb.append(cpAttachmentFileEntry.getFileEntryId());
		sb.append(StringPool.SLASH);
		sb.append(
			URLCodec.encodeURL(
				cpAttachmentFileEntry.getTitle(siteDefaultLocale), true));

		sb.append("?download=");
		sb.append(download);

		return _html.escape(sb.toString());
	}

	@Override
	public void sendMediaBytes(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		sendMediaBytes(httpServletRequest, httpServletResponse, null);
	}

	@Override
	public void sendMediaBytes(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String contentDisposition)
		throws IOException {

		String path = _http.fixPath(httpServletRequest.getPathInfo());

		String[] pathArray = StringUtil.split(path, CharPool.SLASH);

		if (pathArray.length < 2) {
			long groupId = ParamUtil.getLong(httpServletRequest, "groupId", 0);

			if (groupId == 0) {
				httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);

				return;
			}

			sendDefaultMediaBytes(
				httpServletRequest, httpServletResponse, contentDisposition,
				groupId);

			return;
		}

		long cProductId = GetterUtil.getLong(pathArray[1]);

		CProduct cProduct = _cProductLocalService.fetchCProduct(cProductId);

		if (cProduct == null) {
			httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);

			return;
		}

		try {
			setCPRules(httpServletRequest, cProduct.getGroupId());

			if (!_cpDefinitionModelResourcePermission.contains(
					PermissionThreadLocal.getPermissionChecker(),
					cProduct.getPublishedCPDefinitionId(), ActionKeys.VIEW)) {

				httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);

				return;
			}

			FileEntry fileEntry = getFileEntry(httpServletRequest);

			if (fileEntry == null) {
				sendDefaultMediaBytes(
					httpServletRequest, httpServletResponse, contentDisposition,
					cProduct.getGroupId());

				return;
			}

			ServletResponseUtil.sendFile(
				httpServletRequest, httpServletResponse,
				fileEntry.getFileName(), getMediaBytes(fileEntry),
				fileEntry.getMimeType(), contentDisposition);
		}
		catch (PortalException pe) {
			_log.error(pe, pe);

			httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	protected FileEntry getFileEntry(HttpServletRequest httpServletRequest)
		throws PortalException {

		String path = _http.fixPath(httpServletRequest.getPathInfo());

		String[] pathArray = StringUtil.split(path, CharPool.SLASH);

		if (pathArray.length < 2) {
			return null;
		}

		long fileEntryId = GetterUtil.getLong(pathArray[pathArray.length - 2]);

		return _dlAppLocalService.getFileEntry(fileEntryId);
	}

	protected FileEntry getFileEntry(long fileEntryId) throws PortalException {
		return _dlAppLocalService.getFileEntry(fileEntryId);
	}

	protected void sendDefaultMediaBytes(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String contentDisposition,
			long groupId)
		throws IOException {

		try {
			CommerceMediaDefaultImageConfiguration
				commerceMediaDefaultImageConfiguration =
					ConfigurationProviderUtil.getConfiguration(
						CommerceMediaDefaultImageConfiguration.class,
						new GroupServiceSettingsLocator(
							groupId,
							CommerceMediaDefaultImageConfiguration.class.
								getName()));

			FileEntry fileEntry = getFileEntry(
				commerceMediaDefaultImageConfiguration.defaultFileEntryId());

			if (fileEntry == null) {
				httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);

				return;
			}

			ServletResponseUtil.sendFile(
				httpServletRequest, httpServletResponse,
				fileEntry.getFileName(), getMediaBytes(fileEntry),
				fileEntry.getMimeType(), contentDisposition);
		}
		catch (PortalException pe) {
			_log.error(pe, pe);

			httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	protected void setCPRules(
			HttpServletRequest httpServletRequest, long groupId)
		throws PortalException {

		CommerceAccount commerceAccount =
			_commerceAccountHelper.getCurrentCommerceAccount(
				groupId, httpServletRequest);

		long commerceAccountId = 0;

		if (commerceAccount != null) {
			commerceAccountId = commerceAccount.getCommerceAccountId();
		}

		long userId = _portal.getUserId(httpServletRequest);

		if (userId == 0) {
			userId = _userLocalService.getDefaultUserId(
				_portal.getCompanyId(httpServletRequest));
		}

		long[] commerceUserSegmentEntryIds =
			_commerceUserSegmentHelper.getCommerceUserSegmentIds(
				groupId, commerceAccountId, userId);

		List<CPRule> cpRules = _cpRuleLocalService.getCPRules(
			groupId, commerceUserSegmentEntryIds);

		CPRulesThreadLocal.setCPRules(cpRules);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DLCommerceMediaResolver.class);

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private CommerceUserSegmentHelper _commerceUserSegmentHelper;

	@Reference
	private CPAttachmentFileEntryLocalService
		_cpAttachmentFileEntryLocalService;

	@Reference
	private CPAttachmentFileEntryService _cpAttachmentFileEntryService;

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.product.model.CPDefinition)"
	)
	private ModelResourcePermission<CPDefinition>
		_cpDefinitionModelResourcePermission;

	@Reference
	private CProductLocalService _cProductLocalService;

	@Reference
	private CPRuleLocalService _cpRuleLocalService;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private File _file;

	@Reference
	private Html _html;

	@Reference
	private Http _http;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}