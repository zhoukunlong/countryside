package com.country.countryside.countryside.service;

import com.country.countryside.countryside.vo.CountryInVo;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public interface TbCountryService {

    /**
     * 添加村庄
     * @param inVo
     */
    void addCountry(HttpServletRequest request, CountryInVo inVo);

    /**
     * 申请加入村庄
     * @param userId
     * @param countryId
     */
    String joinCountry(String userId, String countryId);

    /**
     * 审批工单
     * @param id
     */
    void approve(String id, Integer status);
}
