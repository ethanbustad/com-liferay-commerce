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

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.base.CommerceCatalogLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;

import java.util.Locale;
import java.util.Map;

/**
 * @author Alec Sloan
 */
public class CommerceCatalogLocalServiceImpl
	extends CommerceCatalogLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceCatalog addCommerceCatalog(
			String languageId, Map<Locale, String> nameMap,
			long parentCatalogId, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		long commerceCatalogId = counterLocalService.increment();

		CommerceCatalog commerceCatalog = commerceCatalogPersistence.create(
			commerceCatalogId);

		commerceCatalog.setParentCommerceCatalogId(parentCatalogId);
		commerceCatalog.setNameMap(nameMap);
		commerceCatalog.setCatalogDefaultLanguageId(languageId);
		commerceCatalog.setCompanyId(user.getCompanyId());
		commerceCatalog.setUserId(user.getUserId());
		commerceCatalog.setUserName(user.getFullName());

		return commerceCatalogPersistence.update(commerceCatalog);
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceCatalog deleteCommerceCatalog(long commerceCatalogId)
		throws PortalException {

		return commerceCatalogPersistence.remove(commerceCatalogId);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceCatalog updateCommerceCatalog(
			long commerceCatalogId, String languageId,
			Map<Locale, String> nameMap, long parentCatalogId,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		CommerceCatalog commerceCatalog =
			commerceCatalogPersistence.findByPrimaryKey(commerceCatalogId);

		commerceCatalog.setParentCommerceCatalogId(parentCatalogId);
		commerceCatalog.setNameMap(nameMap);
		commerceCatalog.setCatalogDefaultLanguageId(languageId);
		commerceCatalog.setCompanyId(user.getCompanyId());
		commerceCatalog.setUserId(user.getUserId());
		commerceCatalog.setUserName(user.getFullName());

		return commerceCatalogPersistence.update(commerceCatalog);
	}

}