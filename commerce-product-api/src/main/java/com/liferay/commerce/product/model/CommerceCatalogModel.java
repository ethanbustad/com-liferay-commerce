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

package com.liferay.commerce.product.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the CommerceCatalog service. Represents a row in the &quot;CommerceCatalog&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.commerce.product.model.impl.CommerceCatalogModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.commerce.product.model.impl.CommerceCatalogImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceCatalog
 * @see com.liferay.commerce.product.model.impl.CommerceCatalogImpl
 * @see com.liferay.commerce.product.model.impl.CommerceCatalogModelImpl
 * @generated
 */
@ProviderType
public interface CommerceCatalogModel extends AuditedModel,
	BaseModel<CommerceCatalog>, LocalizedModel, ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce catalog model instance should use the {@link CommerceCatalog} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce catalog.
	 *
	 * @return the primary key of this commerce catalog
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce catalog.
	 *
	 * @param primaryKey the primary key of this commerce catalog
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the commerce catalog ID of this commerce catalog.
	 *
	 * @return the commerce catalog ID of this commerce catalog
	 */
	public long getCommerceCatalogId();

	/**
	 * Sets the commerce catalog ID of this commerce catalog.
	 *
	 * @param commerceCatalogId the commerce catalog ID of this commerce catalog
	 */
	public void setCommerceCatalogId(long commerceCatalogId);

	/**
	 * Returns the company ID of this commerce catalog.
	 *
	 * @return the company ID of this commerce catalog
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce catalog.
	 *
	 * @param companyId the company ID of this commerce catalog
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce catalog.
	 *
	 * @return the user ID of this commerce catalog
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce catalog.
	 *
	 * @param userId the user ID of this commerce catalog
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce catalog.
	 *
	 * @return the user uuid of this commerce catalog
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce catalog.
	 *
	 * @param userUuid the user uuid of this commerce catalog
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce catalog.
	 *
	 * @return the user name of this commerce catalog
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce catalog.
	 *
	 * @param userName the user name of this commerce catalog
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce catalog.
	 *
	 * @return the create date of this commerce catalog
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce catalog.
	 *
	 * @param createDate the create date of this commerce catalog
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce catalog.
	 *
	 * @return the modified date of this commerce catalog
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce catalog.
	 *
	 * @param modifiedDate the modified date of this commerce catalog
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the parent commerce catalog ID of this commerce catalog.
	 *
	 * @return the parent commerce catalog ID of this commerce catalog
	 */
	public long getParentCommerceCatalogId();

	/**
	 * Sets the parent commerce catalog ID of this commerce catalog.
	 *
	 * @param parentCommerceCatalogId the parent commerce catalog ID of this commerce catalog
	 */
	public void setParentCommerceCatalogId(long parentCommerceCatalogId);

	/**
	 * Returns the name of this commerce catalog.
	 *
	 * @return the name of this commerce catalog
	 */
	public String getName();

	/**
	 * Returns the localized name of this commerce catalog in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this commerce catalog
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this commerce catalog in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this commerce catalog. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this commerce catalog in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this commerce catalog
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this commerce catalog in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this commerce catalog
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this commerce catalog.
	 *
	 * @return the locales and localized names of this commerce catalog
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this commerce catalog.
	 *
	 * @param name the name of this commerce catalog
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this commerce catalog in the language.
	 *
	 * @param name the localized name of this commerce catalog
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this commerce catalog in the language, and sets the default locale.
	 *
	 * @param name the localized name of this commerce catalog
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this commerce catalog from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this commerce catalog
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this commerce catalog from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this commerce catalog
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the catalog default language ID of this commerce catalog.
	 *
	 * @return the catalog default language ID of this commerce catalog
	 */
	@AutoEscape
	public String getCatalogDefaultLanguageId();

	/**
	 * Sets the catalog default language ID of this commerce catalog.
	 *
	 * @param catalogDefaultLanguageId the catalog default language ID of this commerce catalog
	 */
	public void setCatalogDefaultLanguageId(String catalogDefaultLanguageId);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public Object clone();

	@Override
	public int compareTo(CommerceCatalog commerceCatalog);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CommerceCatalog> toCacheModel();

	@Override
	public CommerceCatalog toEscapedModel();

	@Override
	public CommerceCatalog toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}